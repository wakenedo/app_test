package com.example.app_test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_test.ui.theme.App_testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_testTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Greeting message
        val greetingMessage = "Hello Android!"
        Greeting(name = greetingMessage)

        Spacer(modifier = Modifier.height(16.dp))

        // Button to navigate to Second Activity with the greeting message
        val context = LocalContext.current
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("greeting_message", greetingMessage)
            context.startActivity(intent)
        }) {
            Text(text = "Go to Second Activity")
        }
        Button(onClick = {
            val intent = Intent(context, ImageActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Go to Image Activity")
        }

        Spacer(modifier = Modifier.height(16.dp))
        CalculatorScreen()
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "$name!",
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CalculatorScreen() {
    // State variables
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("+") }

    // State for showing Popup
    var showPopup by remember { mutableStateOf(false) }
    val operators = listOf("+", "-", "*", "/")

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {
        // Input for the first number
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("First Number") },
            modifier = Modifier.fillMaxWidth()
        )

        // Input for the second number
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Second Number") },
            modifier = Modifier.fillMaxWidth()
        )

        // Display the selected operator
        Text(
            text = "Selected Operator: $operator",
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Clickable text to show the operator selection popup
        Text(
            text = "Select Operator: $operator",
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { showPopup = true }
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        // Popup for operator selection
        if (showPopup) {
            DropdownMenu(
                expanded = showPopup,
                onDismissRequest = { showPopup = false }
            ) {
                operators.forEach { op ->
                    DropdownMenuItem(
                        text = { Text(op) },
                        onClick = {
                            operator = op
                            showPopup = false // Close the dropdown after selection
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calculate button
        Button(onClick = {
            // Create an Intent to start ThirdActivity
            val intent = Intent(context, ThirdActivity::class.java)
            intent.putExtra("num1", num1.toDoubleOrNull() ?: 0.0)
            intent.putExtra("num2", num2.toDoubleOrNull() ?: 0.0)
            intent.putExtra("operator", operator)
            context.startActivity(intent)
        }) {
            Text(text = "Calculate")
        }
    }
}
