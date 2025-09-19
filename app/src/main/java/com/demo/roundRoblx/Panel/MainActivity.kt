package com.demo.roundRoblx.Panel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.demo.roundRoblx.R
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.Unique

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            BeginApplication.roundRowResponse.observe(this) {
                startActivity(Intent(this@MainActivity, LetGoActivity::class.java))
                if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1" && it.first?.round_small_2 != null){
                    BeginApplication.showRoundRowTab(this@MainActivity,it.first?.round_small_2?.random()?.round_main_image?.toUri())
                    finish()
                }else{
                    finish()
                }
            }
        }, 2000)


    }
}