@file:OptIn(ExperimentalMaterial3Api::class)

package nz.co.dragontech.fitnessquest.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import nz.co.dragontech.fitnessquest.R

@Composable
fun Header(title: String, onMenuClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 20.sp)
        },
        actions = {
            IconButton(onClick = onMenuClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_burger_menu),
                    contentDescription = "Menu"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}