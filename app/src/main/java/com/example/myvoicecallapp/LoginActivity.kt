package com.example.myvoicecallapp

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.core.invite.advanced.ZegoCallInvitationInCallingConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {
    private lateinit var myUserId: EditText
    private lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myUserId = findViewById(R.id.myUserId)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val myUserId = myUserId.text.toString()
            if (myUserId.isNotEmpty()) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userID", myUserId)
                startActivity(intent)
                setupZegoUIKit(myUserId)
            }
        }
    }

    private fun setupZegoUIKit(userID: String) {
        val application: Application = application
        val appId: Long = 1320560826
        val appSign: String = "a0275d97f3480f1f47f98fa3989a2660033df2fd10e5e3072090c2157b5590a6"
        val userName: String = userID

        val callInvitationConfiq = ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(
            application,
            appId,
            appSign,
            userID,
            userName,
            callInvitationConfiq
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}