package com.example.sampleapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val intent = intent?.getBundleExtra(Constants.DATA)
        val list = intent?.getStringArrayList(Constants.MY_LIST)

        val fragment =
            supportFragmentManager.findFragmentByTag(MainFragment::class.java.simpleName)
                ?: MainFragment.newInstance(list)
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.main_container, fragment, MainFragment::class.java.simpleName).commit()
    }
}
