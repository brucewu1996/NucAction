package com.example.nucaction.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.example.nucaction.databinding.ActivityIntroBinding

//import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_intro)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.btnSignInIntro.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.btnSignUpIntro.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}