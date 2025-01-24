package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                WoofTheme(darkTheme = true) {
                    DataIntitialization(modifier = Modifier.fillMaxSize())
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
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            WoofTheme(darkTheme = false) {
                DataIntitialization(modifier = Modifier.fillMaxSize())
            }
        }

}

@Composable
fun DataIntitialization(modifier: Modifier = Modifier){
    val dogs= dogs;
    BackgroundInit(dogs, modifier);
}

@Composable
fun BackgroundInit(dogs: List<Dog>,modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            WoofTopBar()
        }
    ) {
        paddingValues ->
            HomeLayout(dogs, modifier.padding(paddingValues))
    }
//    HomeLayout(dogs, modifier)

}

@Composable
fun HomeLayout(dogs: List<Dog>, modifier: Modifier = Modifier){

    LazyColumn(modifier=modifier) {
        items(dogs){
            item -> CardLayout(item, Modifier)

        }
    }

}

@Composable
fun CardLayout(dog: Dog, modifier: Modifier=Modifier){

    var expanded by remember { mutableStateOf(false) }

    val backColor: Color by  animateColorAsState(
        if(expanded) MaterialTheme.colorScheme.tertiaryContainer else
        MaterialTheme.colorScheme.secondaryContainer
    )

    Card(modifier=modifier.fillMaxWidth().padding(vertical = 3.dp).padding(horizontal = 10.dp),
        ) {
        Column(modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ).background(backColor)) {
            Row {
                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(dog.imageResourceId),
                    contentDescription = dog.name.toString(),
                    modifier = Modifier.size(88.dp)
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )

                Spacer(modifier = Modifier.width(8.dp))


                Column() {
                    Text(
                        text = stringResource(dog.name),
                        modifier = Modifier.padding(horizontal = 5.dp).padding(top = 18.dp),
                        fontWeight = FontWeight.ExtraBold

                    )
                    Text(
                        text = dog.age.toString() + " years",
                        modifier = Modifier.padding(horizontal = 5.dp)

                    )

                }
                Spacer(modifier = Modifier.weight(1f))

                DogItemButton(expanded=expanded, modifier = Modifier, onClick = {expanded = !expanded})




            }


            if(expanded){
                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier=Modifier.fillMaxWidth().padding(vertical = 3.dp).padding(horizontal = 10.dp)
                    ) {
                    Text(
                        text = "About:",
                        fontWeight = FontWeight.ExtraBold

                    )
                    Text(
                        text= stringResource(dog.hobbies),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopBar(modifier: Modifier= Modifier){

    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    modifier = Modifier,
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Spacer(modifier=modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.app_name),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        },
        modifier=modifier
    )

}


@Composable
fun DogItemButton(
    expanded: Boolean,
    modifier: Modifier= Modifier,
    onClick: ()->Unit
){
    
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
            Icon(
                imageVector =  if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = "Expand To Know about Dog"
            )

    }
    
}