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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(

                        painter = painterResource(id = R.drawable.comidalogo),
                        contentDescription = null,
                        modifier = modifier
                            .padding(8.dp)
                            .size(dimensionResource(id = R.dimen.image_size))

                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.displayLarge
                    )

                }
        }
        ,
        modifier = modifier
    )

}

@Composable
fun MenuCardList( platilloList:List<Platillo>, modifier: Modifier = Modifier ){
    Scaffold(
        topBar = {
            MenuTopAppBar()

        }
    ) {
            it ->

    LazyColumn( contentPadding = it ){
        items(platilloList){
            platillo -> MenuCard(
            platillo = platillo,
                modifier= modifier.padding(10.dp)
            )
            }

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
                    text = LocalContext.current.getString(platillo.stringResourceId),
                    modifier = modifier.padding(22.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Rebaja",
                    style = MaterialTheme.typography.displayLarge

                )
                Text(
                    text = "MX $100.0 ",
                    style = MaterialTheme.typography.labelSmall

                )
                Text(
                    text = "Ahorra hasta 30%",
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
        MenuCardList( platilloList = DataSource().LoadPlatillos()  )
    }
}