package com.pusher.chatkit.gettingstarted

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pusher.chatkit.CurrentUser
import com.pusher.chatkit.rooms.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val INSTANCE_LOCATOR = "YOUR_INSTANCE_LOCATOR_HERE"
    private val TOKEN_PROVIDER_URL = "YOUR_TOKEN_PROVIDER_HERE"

    private val userId = "alice"

    private lateinit var currentUser: CurrentUser
    private lateinit var currentRoom: Room

    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //configure the recyclerview
        adapter = MessageAdapter(this, userId)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        recyclerViewMessages.layoutManager =  layoutManager
        recyclerViewMessages.adapter = adapter

        //TODO: your Chatkit code here :-)


        txtMessage.setOnEditorActionListener { v, actionId, event ->

            //if the action button was send (replaces the enter button)
            if(actionId == EditorInfo.IME_ACTION_SEND){

                //TODO: send message using chatkit

                //clear the edit text
                txtMessage.setText("")

            }

            false
        }

    }

}
