package com.meleha.animateddetails.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meleha.animateddetails.R

@Composable
fun AudioPlayerButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.fast_rewind),
            contentDescription = null
        )
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.play_circle),
            contentDescription = null
        )
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.fast_forward),
            contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AudioPlayerButtonsPreview() {
    AudioPlayerButtons()
}