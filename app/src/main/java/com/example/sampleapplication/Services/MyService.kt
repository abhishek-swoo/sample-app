package com.example.sampleapplication.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import android.media.MediaPlayer
import android.provider.Settings


class MyService : Service() {

    private val player: MediaPlayer? by lazy { MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI) }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player?.isLooping = true;
        // It will start the player
        player?.start();
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    override fun onDestroy() {
        super.onDestroy();
        // Stopping the player when service is destroyed
        player?.stop();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }
}