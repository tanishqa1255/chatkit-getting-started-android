package com.pusher.chatkit.gettingstarted

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pusher.chatkit.*
import com.pusher.chatkit.rooms.Room
import com.pusher.chatkit.rooms.RoomListeners
import com.pusher.util.Result
import elements.Error
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

        //create the chat manager
        val chatManager = ChatManager(
            instanceLocator = INSTANCE_LOCATOR,
            userId = userId,
            dependencies = AndroidChatkitDependencies(
                tokenProvider = ChatkitTokenProvider(
                    endpoint = TOKEN_PROVIDER_URL,
                    userId = userId
                ),
                context = this
            )
        )

        //attempt to connect
        chatManager.connect(
            listeners = ChatListeners(),
            callback = { result ->
                when (result) {
                    is Result.Success -> {
                        result.value.let { user ->

                            //success! save the user and the room to be able to use later
                            currentUser = user
                            currentRoom = user.rooms.first()

                            runOnUiThread {
                                //display what room we're in on the top of the screen
                                lblTopBarTitle.text = currentRoom.name
                            }

                            //subscribe to room
                            user.subscribeToRoomMultipart(
                                currentRoom.id,
                                RoomListeners(onMultipartMessage = { message ->
                                    runOnUiThread {
                                        //add the message to our adapter and scroll to the new message
                                        adapter.addMessage(message)
                                        recyclerViewMessages.layoutManager?.scrollToPosition(adapter.messages.size -1)
                                    }
                                }),
                                messageLimit = 20,
                                callback = {})

                        }
                    }

                    is Error -> {
                        Toast.makeText(this, result.reason, Toast.LENGTH_SHORT).show() }
                }
            }
        )

        txtMessage.setOnEditorActionListener { v, actionId, event ->

            //if the action button was send (replaces the enter button)
            if(actionId == EditorInfo.IME_ACTION_SEND){

                //send the message using chatkit
                currentUser.sendSimpleMessage(
                    roomId = currentRoom.id,
                    messageText = txtMessage.text.toString(),
                    callback = {  }
                )

                //clear the edit text
                txtMessage.setText("")

            }

            false
        }

    }

}
