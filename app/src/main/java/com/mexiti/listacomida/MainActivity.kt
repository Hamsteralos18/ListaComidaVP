package com.mexiti.listacomida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.listacomida.data.DataSource
import com.mexiti.listacomida.model.Platillo
import com.mexiti.listacomida.ui.theme.ListaComidaTheme
import com.mexiti.listacomida.ui.theme.md_theme_dark_onSecondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaComidaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MenuApp()

                }
            }
        }
    }
}
@Composable
fun MenuApp(){
    MenuCardList(
        platilloList = DataSource().LoadPlatillos(),
    )
}

@Composable
fun MenuCardList( platilloList:List<Platillo>, modifier: Modifier = Modifier ){
    LazyColumn( modifier = modifier ){
        items(platilloList){
            platillo -> MenuCard(
            platillo = platillo,
                modifier= Modifier.padding(10.dp)
        )
        }

    }
}



@Composable
fun MenuCard(platillo: Platillo, modifier: Modifier = Modifier){
    Card(modifier = modifier
        .padding(10.dp)) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        )  {
            Image(
                painter = painterResource(id = platillo.drawableResourceId),
                contentDescription = stringResource(id = platillo.stringResourceId),
                modifier = Modifier
                    .size(180.dp)
                    .padding(start = 10.dp)
                    .clip(CircleShape)
                ,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier =
                    Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = "Pozole",
                    style = MaterialTheme.typography.displayLarge

                )
                Text(
                    text = "MX $100.0 ",
                    style = MaterialTheme.typography.labelSmall

                )
                Text(
                    text = "Ahorra hasta el 30%",
                    color = md_theme_dark_onSecondary,
                    style = MaterialTheme.typography.displayMedium

                    
                )
            }


        }

    }


}

@Preview(showBackground = true)
@Composable
fun MenuPlatilloPreview() {
    ListaComidaTheme(darkTheme = false) {
        MenuCard(platillo = Platillo(R.string.pozole,R.drawable.pozole) )
    }
}