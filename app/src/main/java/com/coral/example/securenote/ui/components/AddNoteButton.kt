package com.coral.example.securenote.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.coral.example.securenote.R

@Composable
fun AddNoteButton(onButtonClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onButtonClick() },
        backgroundColor = Color.DarkGray,
        elevation = FloatingActionButtonDefaults.elevation(dimensionResource(R.dimen.size_small)),
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_note_description),
            tint = Color.White
        )
    }
}
