package com.victorhvs.lotonaticos.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victorhvs.lotonaticos.presentation.theme.LotonaticosTheme

@Composable
fun RoundText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    text: String
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(
                color,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun RoundTextPreview() {
    LotonaticosTheme(darkTheme = false) {
        RoundText(text = "12")
    }
}
