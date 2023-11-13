package com.dicoding.cocktailapps

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@Composable
fun App(
    modifier: Modifier = Modifier,
) {

}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    CocktailAppsTheme {
        App()
    }
}
