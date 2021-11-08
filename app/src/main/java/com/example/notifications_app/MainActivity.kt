package com.example.notifications_app


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var msg:EditText
   lateinit var btnshow:Button
      var channelId = "myapp.notifications"
    private var description = "Notification App Example"
    lateinit var builder: Notification.Builder
   private lateinit var  notificationManager: NotificationManager




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // we need to declare the channel her in (onCreate function)
        notificationManager =  getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        msg = findViewById(R.id.edNotification)
        btnshow = findViewById(R.id.btnNotification)


        btnshow.setOnClickListener {
            var message=msg.text.toString()

            if (message.isNotEmpty()) {

                createNotificationChannel(message)

            }else{
                Toast.makeText(this, "Please Enter value", Toast.LENGTH_SHORT).show()
            }
        }






    }

    fun createNotificationChannel(message:String) {
        // we need to set intent variable at first then pendingIntent so the user can go to anther page if he triggered the button
        intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_IMMUTABLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            var channel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

            builder = Notification.Builder(this, channelId)
                // set the icon to appear in box
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                // set title
                .setContentTitle("This is Notification Page")
                // set message
                .setContentText(message)
            msg.text.clearSpans()
            msg.clearFocus()




        }else{ builder = Notification.Builder(this)
            .setSmallIcon(R.drawable.ic_notifications)
            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_notifications))
            .setContentIntent(pendingIntent)
            .setContentTitle("My Notification")
            .setContentText(message)
            msg.text.clearSpans()
            msg.clearFocus()}
        //
        notificationManager.notify(1234, builder.build())



    }
        }





