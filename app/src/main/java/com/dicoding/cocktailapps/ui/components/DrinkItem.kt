package com.dicoding.cocktailapps.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.cocktailapps.data.dummy.getDummyCocktailResponse
import com.dicoding.cocktailapps.data.model.CocktailEntity
import com.dicoding.cocktailapps.data.model.DrinksItem

@Composable
fun DrinkItem(
    modifier: Modifier = Modifier,
    data: DrinksItem,
    onNavigateToDetailScreen: (String) -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .clickable {
                onNavigateToDetailScreen(data.idDrink)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = data.strDrinkThumb,
                contentDescription = data.strDrink + " Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = data.strDrink,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier
                )
                Text(
                    text = data.strCategory,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier
                )
                Text(
                    text = data.strAlcoholic,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = modifier,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun DrinkItem(
    modifier: Modifier = Modifier,
    data: CocktailEntity,
    onNavigateToDetailScreen: (String) -> Unit = {},
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .clickable {
                onNavigateToDetailScreen(data.idDrink)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = data.strDrinkThumb,
                contentDescription = data.strDrink + " Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = data.strDrink,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier
                )
                Text(
                    text = data.strCategory,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier
                )
                Text(
                    text = data.strAlcoholic,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = modifier,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrinkItemPreview() {
    DrinkItem(
        data = getDummyCocktailResponse().drinks!![0],
        onNavigateToDetailScreen = {},
    )
}
