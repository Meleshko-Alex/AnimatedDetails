package com.meleha.animateddetails.ui.components.DetailScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meleha.animateddetails.R

/**
 * A composable that displays two clickable text elements: "Home" and "Audio".
 *
 * @param modifier A [Modifier] to customize the layout and appearance of the `Row`.
 * @param onHomeClick Callback invoked when the "Home" text is clicked.
 * @param onAudioClick Callback invoked when the "Audio" text is clicked.
 */
@Composable
fun TwoClickableText(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onAudioClick: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
                .clickable { onHomeClick() },
            text = stringResource(R.string.home)
        )
        Text(
            modifier = Modifier
                .padding(8.dp)
                .clickable { onAudioClick() },
            text = stringResource(R.string.audio)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TwoClickableTextPreview() {
    TwoClickableText(onAudioClick = {}, onHomeClick = {})
}