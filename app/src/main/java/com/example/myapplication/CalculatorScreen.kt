package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Calculator() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = input, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = result, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("0", ".", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    Button(
                        onClick = {
                            when (label) {
                                "=" -> {
                                    try {
                                        result = eval(input)
                                    } catch (e: Exception) {
                                        result = "Error"
                                    }
                                }
                                else -> input += label
                            }
                        },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Text(label)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

fun eval(expression: String): String {
    return try {
        val result = simpleCalculate(expression)
        result.toString()
    } catch (e: Exception) {
        "Error"
    }
}
fun simpleCalculate(expr: String): Double {
    val tokens = Regex("(?<=[-+*/])|(?=[-+*/])").split(expr).map { it.trim() }

    var result = tokens[0].toDouble()
    var index = 1

    while (index < tokens.size) {
        val operator = tokens[index]
        val next = tokens[index + 1].toDouble()

        result = when (operator) {
            "+" -> result + next
            "-" -> result - next
            "*" -> result * next
            "/" -> result / next
            else -> throw IllegalArgumentException("Invalid operator: $operator")
        }

        index += 2
    }

    return result
}
