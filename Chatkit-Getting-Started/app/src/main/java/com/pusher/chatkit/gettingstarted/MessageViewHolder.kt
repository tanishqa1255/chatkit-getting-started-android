package com.pusher.chatkit.gettingstarted

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pusher.chatkit.messages.multipart.Message
import com.pusher.chatkit.messages.multipart.Payload
import java.text.SimpleDateFormat
import java.util.*

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(context: Context,
             message: Message,
             currentUserId: String){

        val lblSender = itemView.findViewById<TextView>(R.id.lblSender)
        val lblTimestamp = itemView.findViewById<TextView>(R.id.lblTimestamp)
        val lblMessage = itemView.findViewById<TextView>(R.id.lblMessage)

        lblSender.text = message.sender.name

        val fmt = SimpleDateFormat("HH:mm:ss EEE dd MMM", Locale.ENGLISH)
        lblTimestamp.text = fmt.format(message.createdAt)


        val inlineMessage: Payload.Inline = message.parts[0].payload as Payload.Inline
        lblMessage.text = inlineMessage.content

        if (currentUserId == message.sender.id) {
            lblSender.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            lblMessage.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            lblTimestamp.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        } else {
            lblSender.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }
    }
}