package com.meleha.animateddetails.ui.components.DetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meleha.animateddetails.R

@Composable
fun FullPlayerHeader(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    title: String,
    description: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .padding(top = 4.dp)
                    .width(60.dp)
                    .height(3.dp)
                    .background(Color.DarkGray)
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Close",
                modifier = modifier
                    .size(24.dp)
                    .clickable { onCloseClick() }
            )
        }

        Spacer(modifier = modifier.height(30.dp))

        Text(
            modifier = modifier.fillMaxWidth(),
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = modifier.height(20.dp))

        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            text = description,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = modifier.height(20.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun FullPlayerHeaderPreview() {
    FullPlayerHeader(
        onCloseClick = {},
        title = "Some Title",
        description = "Subtitle Text ".repeat(10)
    )
}