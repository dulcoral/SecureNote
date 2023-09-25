package com.coral.example.securenote.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.coral.example.securenote.R

@Composable
fun AddNoteButton(onButtonClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        FloatingActionButton(
            onClick = { onButtonClick() },
            backgroundColor = Color.DarkGray,
            elevation = FloatingActionButtonDefaults.elevation(dimensionResource(R.dimen.size_small)),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.size_huge))
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.add_note_description),
                tint = Color.White
            )
        }

    }
}