package com.example.myvoicecallapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {
    private lateinit var targetUserId: EditText
    private lateinit var myUserIdText: TextView
    private lateinit var voiceCallBtn: ZegoSendCallInvitationButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        targetUserId = findViewById(R.id.targetUserId)
        myUserIdText = findViewById(R.id.myUserIdText)
        voiceCallBtn = findViewById(R.id.voiceCallBtn)

        val myUserId = intent.getStringExtra("userID")
        myUserIdText.text = "Hi $myUserId, \nWhom do you want to call ?"

        targetUserId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val targetUserID = targetUserId.text.toString()
                if (targetUserID.isNotEmpty()) {
                    startVoiceCall(targetUserID)
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun startVoiceCall(targetUserID: String) {
        val targetUserName: String = targetUserID

        voiceCallBtn.setIsVideoCall(false)
        voiceCallBtn.resourceID = "zego_uikit_call"
        voiceCallBtn.setInvitees(listOf(ZegoUIKitUser(targetUserID, targetUserName)))
    }
}