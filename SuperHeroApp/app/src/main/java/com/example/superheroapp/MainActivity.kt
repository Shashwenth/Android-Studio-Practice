package com.example.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroapp.model.Hero
import com.example.superheroapp.model.HerosRepository
import com.example.superheroapp.ui.theme.SuperHeroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperHeroAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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
    SuperHeroAppTheme {
        Greeting("Android")
    }
}


@Composable
fun DataInitialization(modifier: Modifier= Modifier){
    val superHeroData= HerosRepository.heros;
}

@Composable
fun LayOutInit(
    modifier: Modifier= Modifier,
    superHeroData: List<Hero>?
){
    if(superHeroData!=null){
        LazyColumn(modifier= modifier) {
            items(superHeroData){
                item ->
                    CardLayout(item, modifier= Modifier)
            }
        }
    }
}

@Composable
fun CardLayout(item: Hero, modifier: Modifier.Companion) {

    Card(modifier=Modifier){
        Row {

            Image(
                painter = painterResource(item.imageRes),
                contentDescription = stringResource(item.descriptionRes)
            )

            Column {
                Text(
                    text = stringResource(item.nameRes)
                )
                Text(text = stringResource(item.descriptionRes))
            }
        }
    }

}
