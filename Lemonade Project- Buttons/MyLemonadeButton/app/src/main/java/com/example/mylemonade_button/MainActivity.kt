package com.example.mylemonade_button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylemonade_button.ui.theme.MyLemonadeButtonTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLemonadeButtonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    LemonadeJuicer(
                        modifier = Modifier
                    );
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
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyLemonadeButtonTheme {
//        Greeting("Android")
//    }
//}

@Preview(showBackground = true)
@Composable
fun LemonadeJuicerPreview() {
    MyLemonadeButtonTheme {
        LemonadeJuicer();
    }
}

@Composable
fun LemonadeJuicer(modifier: Modifier=Modifier){


    var result by remember{ mutableStateOf(1) };

    var imageResouce = when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    var temp = (2..4).random();
    Column(modifier = modifier.fillMaxSize().background(
        Color(0xFFFFF9C4)
    ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Text(
            text="Lemonade",
            modifier=modifier.background(Color.Yellow).
                     fillMaxHeight(fraction = 0.14F).
                     fillMaxWidth(fraction = 1.0F).
                        padding(top = 60.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = modifier.height(180.dp))
        Column(modifier = modifier.
        fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Button(
                onClick = {
                    if(result==2) {
                        temp--;
                        if(temp==0){
                            result++;
                        }
                        println(temp)
                    }
                    else{
                        if(result==4){
                            result=1;
                        }
                        else{
                            result++;
                        }
                    }

                }
            ) {

                Image(painter = painterResource(imageResouce), contentDescription = result.toString());

            }

            Spacer(Modifier.height(30.dp))

            Text(
                text = stringResource(id=when(result) {
                    1 -> R.string.select_fruit
                    2 -> R.string.keep_tapping
                    3 -> R.string.tap_lemonade
                    else -> R.string.tap_empty_glass
                }
                ),
                modifier = modifier
                    .padding(horizontal = 65.dp) // Set margins for the X-axis
                    .padding(vertical = 20.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

        }


    }

}