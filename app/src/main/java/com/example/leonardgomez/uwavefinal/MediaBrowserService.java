/*Copyright 2018 UWave Radio

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/

package com.example.leonardgomez.uwavefinal;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MediaBrowserService extends MediaBrowserServiceCompat {

    private static final String MEDIA_ID_EMPTY_ROOT = "__EMPTY_ROOT__";
    private static final String TAG = MediaBrowserService.class.getSimpleName();

    private static MediaPlayer player;
    private MediaSessionCompat mSession;

    private MediaSessionCompat.Callback mCallback = new MediaSessionCompat.Callback() {

        private AudioManager am;
        private IntentFilter filter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        private BroadcastReceiver mNoisyReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onStop();
            }
        };

        @Override
        public void onPlay() {
            super.onPlay();
            registerReceiver(mNoisyReceiver, filter);

            am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                player.prepareAsync();
                mSession.setActive(true);
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                 @Override
                                                 public void onPrepared(MediaPlayer mp) {
                                                     player.start();
                                                 }
                                             }
                );
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            mSession.setActive(false);
            am.abandonAudioFocus(afChangeListener);

            //release resources
            if(player != null) {
                player.stop();
                player.reset();
                player.release();
                player = null;
            }
        }
    };
    private PlaybackStateCompat.Builder mStateBuilder;
    private MediaMetadataCompat.Builder mMetaDataBuilder;
    private AudioManager.OnAudioFocusChangeListener afChangeListener;


    @Override
    public void onCreate() {
        super.onCreate();

        // Create a new MediaSession.
        mSession = new MediaSessionCompat(this, "com.example.leonardgomez.uwavefinal.MediaBrowserService");

        //Enables callbacks from MediaButtons and TransportControls
        mSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mSession.setPlaybackState(mStateBuilder.build());

        // Sets Callbacks for media session
        mSession.setCallback(mCallback);

        //Sets session token so clients can communicate with service
        setSessionToken(mSession.getSessionToken());

        //Creating and initializing player
        String uWave = "https://uwave.fm/listen/128.ogg";
        player = new MediaPlayer();
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(uWave);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
            throw new Error("Failed.");
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        mSession.release();
        Log.d(TAG, "onDestroy: MediaPlayerAdapter stopped, and MediaSession released");
    }

    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName,
                                 int clientUid,
                                 Bundle rootHints) {
        return new BrowserRoot(MEDIA_ID_EMPTY_ROOT, null);
    }

    @Override
    public void onLoadChildren(
            @NonNull final String parentMediaId,
            @NonNull final Result<List<MediaBrowserCompat.MediaItem>> result) {
    }
}