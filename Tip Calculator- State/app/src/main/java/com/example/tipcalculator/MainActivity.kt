package com.example.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
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
    var tipPercentage by remember { mutableStateOf("") }
    var tipPercent=tipPercentage.toDoubleOrNull() ?: 0.0;
    var tipAmount = amt.toDoubleOrNull() ?: 0.0;
    var roundUp by remember { mutableStateOf(false) }
    tipAmount= calculateTip(tipAmount, tipPercent, roundUp);
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

            EnterTheValue("Enter Amount",value = amt,onValueChange= {amt=it},
                KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next), modifier)

            Spacer(modifier.height(20.dp))

            EnterTheValue("Tip Percentage",value = tipPercentage,onValueChange= {tipPercentage=it},
                KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done) ,modifier)

            Spacer(modifier.height(20.dp))

            RoundUpSwitch("Round Up Tip", roundUp, onSwitchChange={roundUp=it}, modifier )

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
    labelString: String,
    value : String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier=Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = labelString)
        },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        modifier = modifier
    )

}


@Composable
fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean) : Double{
    if(roundUp){
        return round(((amount*tipPercent)/100.0));
    }
    return (amount*tipPercent)/100.0;
}

@Composable
fun RoundUpSwitch(
    value: String,
    roundUp: Boolean,
    onSwitchChange: (Boolean) -> Unit ,
    modifier: Modifier = Modifier
){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), // Add padding for better alignment
        verticalAlignment = Alignment.CenterVertically // Align items vertically
    ) {
        Spacer(modifier.width(40.dp))

        Text(
            text = value,
            modifier = Modifier
        )

        Spacer(modifier.width(110.dp))

        Switch(
            checked = roundUp,
            onCheckedChange = onSwitchChange
        )
    }

}