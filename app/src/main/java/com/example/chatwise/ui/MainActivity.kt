package com.example.chatwise.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.chatwise.R
import androidx.core.util.Pair
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the MaterialButton by its ID
        val transitionButton: MaterialButton = findViewById(R.id.transitionButton)

        // Set click listener on the button
        transitionButton.setOnClickListener {
            // Create intent to navigate to the next activity
            val intent = Intent(this, ProductListActivity::class.java)

            // Define the shared elements for the transition animation
            val buttonPair = Pair<View, String>(transitionButton, transitionButton.transitionName)

            // Set up the options for the activity transition (shared element animation)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, buttonPair)

            // Start the new activity with the specified transition animation
            startActivity(intent, options.toBundle())
        }

    }
}