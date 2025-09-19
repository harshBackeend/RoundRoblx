package com.demo.roundRoblx.Panel

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.demo.roundRoblx.R
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.ActivityLetGoBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.Unique

class LetGoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLetGoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLetGoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        BeginApplication.roundRowResponse.observe(this) {
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1") {
                roundSetInfo(it.first!!)
            } else {
                binding.biloImage.visibility = View.GONE
            }
            setPushEvent(it?.first)
        }
    }

    private fun roundSetInfo(first: RoundStructureData) {
        if (first.round_small_2 != null) {
            binding.biloImage.setOnClickListener {
                BeginApplication.showRoundRowTab(
                    this@LetGoActivity,
                    first.round_small_2.random().round_main_image?.toUri()
                )
            }
        }


    }

    private fun setPushEvent(first: RoundStructureData?) {

    }
}