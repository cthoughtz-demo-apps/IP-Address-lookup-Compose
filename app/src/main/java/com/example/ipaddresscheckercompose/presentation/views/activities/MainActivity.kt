package com.example.ipaddresscheckercompose.presentation.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ipaddresscheckercompose.presentation.viewmodel.IpViewModel
import com.example.ipaddresscheckercompose.presentation.views.pages.IpDetailsPage
import com.example.ipaddresscheckercompose.ui.theme.IpAddressCheckerComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            IpAddressCheckerComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val ipViewModel = hiltViewModel<IpViewModel>()
                    IpDetailsPage(ipViewModel)
                }
            }
        }
    }
}

