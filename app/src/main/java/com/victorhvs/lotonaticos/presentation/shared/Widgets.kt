package com.victorhvs.lotonaticos.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victorhvs.lotonaticos.presentation.theme.LotonaticosTheme

@Composable
fun RoundText(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .size(55.dp)
            .background(MaterialTheme.colorScheme.background, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun RoundTextPreview() {
    LotonaticosTheme {
        RoundText(text = "12")
    }
}
