package com.example.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPageLayout()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        MainPageLayout()
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun MainPageLayout(modifier: Modifier = Modifier){

    var amt by remember { mutableStateOf("") }
    var tipAmount = amt.toDoubleOrNull() ?: 0.0;
    tipAmount= CalculateTip(tipAmount);
    tipAmount=String.format("%.2f", tipAmount).toDouble()

    Column(
        modifier = modifier.
            fillMaxSize().padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

            Text(
                text = "Calculate Tip",
                modifier = modifier.
                    align(Alignment.Start)
                    .padding(
                        horizontal = 20.dp
                    ).padding(bottom = 10.dp)
            )

            EnterTheValue(value = amt,onValueChange= {amt=it},modifier)

            Spacer(modifier.height(20.dp))

            Text(
                text = "The Tip is $tipAmount",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier.height(150.dp))

    }
}

@Composable
fun EnterTheValue(
    value : String,
    onValueChange: (String) -> Unit,
    modifier: Modifier=Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = "Bill Amount")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = modifier
    )

}


@Composable
fun CalculateTip(amount: Double) : Double{
    var percentage: Int = 18;
    return (amount*percentage)/100.0;
}