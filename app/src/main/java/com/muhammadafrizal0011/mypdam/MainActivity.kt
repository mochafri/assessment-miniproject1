package com.muhammadafrizal0011.mypdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.muhammadafrizal0011.mypdam.navigation.SetupNavGraph
import com.muhammadafrizal0011.mypdam.ui.theme.MyPDAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPDAMTheme {
                SetupNavGraph()
            }
        }
    }
}

