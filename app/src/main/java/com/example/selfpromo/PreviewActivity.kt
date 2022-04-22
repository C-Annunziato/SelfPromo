package com.example.selfpromo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.selfpromo.databinding.ActivityPreviewBinding


private lateinit var binding: ActivityPreviewBinding

lateinit var message: Message
lateinit var messagePreviewText: String

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayMessage()
        setupButton()

    }

    private fun displayMessage() {

        message = intent.getSerializableExtra("Message") as Message
        messagePreviewText = """
            Hi ${message.contactName}, 
            
            My name is ${message.myDisplayName} and I am ${message.getFullJobDescription()}.
             
            I have a portfolio of apps to demonstrate my technical skills that I can show on request.
            
            I am able to start a new position ${message.getAvailibility()}.
            
            Please get in touch.
            
            Thanks.
            
        """.trimIndent()

        binding.textViewMessage.text = messagePreviewText
    }

    private fun setupButton() {
        binding.buttonSendMessage.setOnClickListener {

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                setData(Uri.parse("sms: ${message.contactNumber}"))
                putExtra("sms_body", messagePreviewText)
                type = "text/plain"

            }

            val intent = Intent.createChooser(sendIntent, null)
                    startActivity (intent)
        }
    }
}