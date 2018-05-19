package com.example.leonardgomez.uwavefinal.uwavechat;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by chris oung on 3/23/18.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "UWaveFirebaseIIDService";
    private static final String UWAVE_PROGRAM_TOPIC = "uwave_program";


    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Refreshed Token: " + refreshedToken);

        System.out.println("Registration.onTokenRefresh TOKEN: " + refreshedToken);

        FirebaseMessaging.getInstance().subscribeToTopic(UWAVE_PROGRAM_TOPIC);

    }

}
