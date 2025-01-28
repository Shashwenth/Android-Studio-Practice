package com.example.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroapp.model.Hero
import com.example.superheroapp.model.HerosRepository
import com.example.superheroapp.ui.theme.Shapes
import com.example.superheroapp.ui.theme.SuperHeroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperHeroAppTheme (
                darkTheme = false
            ){
                Scaffold(
                    topBar = {
                        TopAppBar()
                    }
                    ) {
                    innerPadding ->
                    DataInitialization(
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
        DataInitialization()
    }
}


@Composable
fun DataInitialization(modifier: Modifier= Modifier){
    val superHeroData= HerosRepository.heros;
    LayOutInit(modifier=modifier, superHeroData)
}

@Composable
fun LayOutInit(
    modifier: Modifier= Modifier,
    superHeroData: List<Hero>
){
        LazyColumn(modifier= modifier.padding(16.dp)) {
            items(superHeroData){
                item ->
                    CardLayout(item, modifier= Modifier)
            }
        }

}

@Composable
fun CardLayout(item: Hero, modifier: Modifier = Modifier) {

    Card(modifier=modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )){
        Row (modifier= Modifier.padding(8.dp)){
            Column (modifier= Modifier.padding(16.dp).weight(1f)){
                Text(
                    text = stringResource(item.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = stringResource(item.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Normal
                )
            }

            Image(
                painter = painterResource(item.imageRes),
                contentDescription = stringResource(item.descriptionRes),
                modifier = Modifier.height(88.dp).width(88.dp).padding(8.dp)
                    .clip(shape = Shapes.small),
            )


        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier=Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "SuperHeros",
                style = MaterialTheme.typography.displayLarge)
        },
        modifier
    )
}
