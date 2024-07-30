package com.falkorichter.myapplication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falkorichter.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    LoginBox(
                        modifier = Modifier.padding(30.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Composable
fun LoginBox(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    Column {
        Column(
            modifier =
                modifier
                    .border(2.dp, color = Color(0xFF666666), shape = RoundedCornerShape(6.dp))
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier,
            ) {
                Text(
                    text = "Username",
                    modifier = Modifier.padding(end = 4.dp),
                )
                TextField(
                    value = username,
                    keyboardOptions =
                        KeyboardOptions(
                            autoCorrect = false,
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Email,
                        ),
                    placeholder = {
                        Text(text = "Who are you?")
                    },
                    singleLine = true,
                    onValueChange = {
                        username = it
                    },
                )
            }
            Row {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp),
            ) {
                Text(
                    text = "Password",
                    modifier = Modifier.padding(end = 4.dp),
                )
                TextField(
                    value = password,
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                        ),
                    placeholder = {
                        Text(text = "Secure Passphrase")
                    },
                    visualTransformation = PasswordVisualTransformation('\u2B50'),
                    singleLine = true,
                    onValueChange = {
                        password = it
                    },
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                Text(
                    text = "Login",
                    color = Color(0xFF0AC8FF),
                    modifier =
                        Modifier.clickable {
                            if (username.isEmpty()) {
                                errorMessage = "Please enter your username"
                            }
                            if (password.isEmpty()) {
                                if (errorMessage.isNotEmpty())
                                    {
                                        errorMessage += "\n"
                                    }
                                errorMessage += "Please enter your password"
                            }
                        },
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginPreview() {
    MyApplicationTheme {
        LoginBox(modifier = Modifier.padding(20.dp))
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
