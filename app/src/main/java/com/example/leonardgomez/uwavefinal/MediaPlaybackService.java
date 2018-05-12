package com.example.leonardgomez.uwavefinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class MediaPlaybackService extends MediaBrowserServiceCompat {
    private static final String MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id";
    private static final String TAG = MediaBrowserService.class.getSimpleName();

    private MediaSessionCompat mSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private Context mContext;
    private IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);

    // Defined elsewhere...
    private AudioManager.OnAudioFocusChangeListener afChangeListener;
    private BroadcastReceiver myNoisyAudioStreamReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mCallback.onStop();
        }
    };
    private NotificationCompat.Builder builder;
    private MediaBrowserServiceCompat service;
    private MediaPlayer player;

    MediaSessionCompat.Callback mCallback = new
            MediaSessionCompat.Callback() {
                @Override
                public void onPlay() {
                    super.onPlay();
                    AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                    // Request audio focus for playback, this registers the afChangeListener
                    int result = am.requestAudioFocus(afChangeListener,
                            // Use the music stream.
                            AudioManager.STREAM_MUSIC,
                            // Request permanent focus.
                            AudioManager.AUDIOFOCUS_GAIN);

                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        // start the player (asynchronously)
                        player.prepareAsync();
                        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                         @Override
                                                         public void onPrepared(MediaPlayer mp) {
                                                             player.start();
                                                         }
                                                     }
                        );
                        // Start the service
                        startService(new Intent(getApplicationContext(), MediaPlaybackService.class));
                        // Set the session active  (and update metadata and state)
                        mSession.setActive(true);

                        // Register BECOME_NOISY BroadcastReceiver
                        registerReceiver(myNoisyAudioStreamReceiver, intentFilter);

                        mStateBuilder.setActions(PlaybackStateCompat.ACTION_STOP);
                        mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                                player.getCurrentPosition(), 1.0f);
                        mSession.setPlaybackState(mStateBuilder.build());
                    }
                }

                @Override
                public void onStop() {
                    super.onStop();
                    AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                    // Abandon audio focus
                    am.abandonAudioFocus(afChangeListener);
                    unregisterReceiver(myNoisyAudioStreamReceiver);
                    // Start the service
                    stopSelf();
                    // Set the session inactive  (and update metadata and state)
                    mSession.setActive(false);
                    // stop the player (custom call)
                    player.stop();

                    mStateBuilder.setActions(PlaybackStateCompat.ACTION_PLAY);
                    mStateBuilder.setState(PlaybackStateCompat.STATE_STOPPED,
                            player.getCurrentPosition(), 1.0f);
                    mSession.setPlaybackState(mStateBuilder.build());
                    // Take the service out of the foreground
                    //service.stopForeground(false);
                }

                @Override
                public void onPause() {
                    AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                    // Update metadata and state

                    // pause the player (custom call)
                    player.pause();
                    // unregister BECOME_NOISY BroadcastReceiver
                    unregisterReceiver(myNoisyAudioStreamReceiver);
                    // Take the service out of the foreground, retain the notification
                    service.stopForeground(false);
                }
            };

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        // Create a MediaSessionCompat
        mSession = new MediaSessionCompat(mContext, TAG);

        // Enable callbacks from MediaButtons and TransportControls
        mSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mSession.setPlaybackState(mStateBuilder.build());

        // MySessionCallback() has methods that handle callbacks from a media controller
        mSession.setCallback(mCallback);

        // Set the session's token so that client activities can communicate with it.
        setSessionToken(mSession.getSessionToken());

        //Creating and initializing player
        String uWave = "https://uwave.fm/listen/128.ogg";
        player = new MediaPlayer();
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(uWave);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Failed.");
        }
    }

    @Override
    public BrowserRoot onGetRoot(String clientPackageName, int clientUid,
                                 Bundle rootHints) {
        // Clients can connect, but this BrowserRoot is an empty hierachy
        // so onLoadChildren returns nothing. This disables the ability to browse for content.
        return new BrowserRoot(MY_EMPTY_MEDIA_ROOT_ID, null);
    }

    @Override
    public void onLoadChildren(final String parentMediaId,
                               final Result<List<MediaBrowserCompat.MediaItem>> result) {
        //  Browsing not allowed
        result.sendResult(null);
    }
}