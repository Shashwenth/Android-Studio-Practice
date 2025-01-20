package com.example.woof

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.DataSource.Dog
import com.example.woof.DataSource.dogs
import com.example.woof.ui.theme.Shapes
import com.example.woof.ui.theme.WoofTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoofTheme {
                    DataIntitialization(modifier = Modifier.fillMaxSize())
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
    WoofTheme {
        DataIntitialization(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun DataIntitialization(modifier: Modifier = Modifier){
    val dogs= dogs;
    BackgroundInit(dogs, modifier);
}

@Composable
fun BackgroundInit(dogs: List<Dog>,modifier: Modifier = Modifier){
        HomeLayout(dogs, modifier)
}

@Composable
fun HomeLayout(dogs: List<Dog>, modifier: Modifier = Modifier){

    LazyColumn(modifier=modifier.fillMaxSize().padding(8.dp)) {
        items(dogs){
            item -> CardLayout(item, modifier)

        }
    }

}

@Composable
fun CardLayout(dog: Dog, modifier: Modifier=Modifier){

    Card(modifier=modifier.fillMaxWidth().padding(vertical = 3.dp), shape = MaterialTheme.shapes.medium) {
        Row {
            Spacer(modifier=Modifier.width(10.dp))

            Image(
                painter = painterResource(dog.imageResourceId),
                contentDescription = dog.name.toString(),
                modifier = Modifier.size(68.dp)
                    .padding(10.dp)
                    .clip(CircleShape),

//                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.FillBounds
            )
            
            Spacer(modifier=Modifier.width(8.dp))

            Column {
                Text(
                    text = stringResource(dog.name),
                    modifier=Modifier.padding(horizontal = 5.dp).padding(top = 8.dp),
                    fontWeight = FontWeight.ExtraBold

                )
                Text(
                    text= dog.age.toString()+" years",
                    modifier=Modifier.padding(horizontal = 5.dp)
                )
            }
        }
    }

}