package com.example.sampleapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fragment =
            supportFragmentManager.findFragmentByTag(MainFragment::class.java.simpleName)
                ?: MainFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.main_container, fragment, MainFragment::class.java.simpleName).commitAllowingStateLoss()
    }
}
