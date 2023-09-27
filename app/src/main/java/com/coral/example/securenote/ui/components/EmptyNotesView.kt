package com.coral.example.securenote.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.coral.example.securenote.R

@Composable
fun EmptyNotesView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = stringResource(R.string.empty_icon_description),
                tint = Color.Gray,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_medium)))
            Text(
                text = stringResource(R.string.empty_view_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray
            )
            Text(
                text = stringResource(R.string.empty_view_subtitle),
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}