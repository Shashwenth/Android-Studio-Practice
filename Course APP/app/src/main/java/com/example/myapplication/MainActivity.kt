package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.DataSource.DataSource
import com.example.myapplication.model.Topic
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BackgroundInit(modifier = Modifier.fillMaxSize().padding(innerPadding))
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
    BackgroundInit(modifier = Modifier)
}


@Composable
fun BackgroundInit(modifier: Modifier= Modifier){

    val topics= DataSource.topics;
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 20.dp).background(Color(0xFFE6E6FA))  // Ensure that the Column takes up the entire available space
    ) {
        Text(
            text = "Shashwenth",
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .background(Color(0xFF4b0082))
        )
        Spacer(modifier = Modifier.height(30.dp))  // Should now work to provide vertical space
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 3.dp)

        ) {
            items(topics.chunked(2)) { pair ->
                if (pair.size <= 1) {
                    RowInit(pair[0], null, false, Modifier)
                } else {
                    RowInit(pair[0], pair[1], true, Modifier)
                }
            }
        }
    }

}

@Composable
fun RowInit(
    chunk1: Topic,
    chunk2: Topic?,
    pairExist: Boolean,
    modifier: Modifier=Modifier
){
    if(pairExist){
        Row(modifier = modifier.fillMaxWidth()){
            CardLayout(chunk1, modifier.weight(1f))
            if (chunk2 != null) {
                CardLayout(chunk2, modifier.weight(1f))
            }
        }
    }else{
        Row(modifier = modifier.fillMaxWidth()){
            CardLayout(chunk1, modifier.weight(1f))
        }
    }

}


@Composable
fun CardLayout(
    Data: Topic,
    modifier: Modifier=Modifier
){

    Card(modifier=modifier.padding(8.dp)) {
        Row(Modifier.height(IntrinsicSize.Max)){
            Image(painter = painterResource(Data.imageResourceId),
                contentDescription = stringResource(Data.stringResourceID),
                modifier = Modifier.height(124.dp).width(110.dp),
                contentScale = ContentScale.FillBounds
            )

            Column(modifier = Modifier.background(Color.LightGray)
                .fillMaxHeight().fillMaxWidth(),
                verticalArrangement = Arrangement.Center) {
                Text(
                    text= stringResource(Data.stringResourceID),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text= Data.courseId.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )


            }

        }
    }

}