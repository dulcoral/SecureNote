package com.coral.example.securenote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.coral.example.securenote.ui.models.NoteItem
import com.coral.example.securenote.R

@Composable
fun NoteCardItem(note: NoteItem, onEdit: () -> Unit, onDelete: () -> Unit) {
    val gradientStartColor = colorResource(R.color.light_yellow)
    val gradientEndColor = colorResource(R.color.strong_yellow)

    Card(
        modifier = Modifier.padding(vertical = dimensionResource(R.dimen.size_xsmall)),
        elevation = dimensionResource(R.dimen.size_xsmall),
        backgroundColor = gradientStartColor,
        shape = RoundedCornerShape(dimensionResource(R.dimen.size_small))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.size_small))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(gradientStartColor, gradientEndColor)
                    )
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            val message = if (note.message.length > 40) {
                note.message.substring(0, 40) + R.string.ellipsis
            } else {
                note.message
            }

            Text(
                text = note.title,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.size_xsmall))
            )
            Text(
                text = message,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.size_xsmall))
            )
            ActionsRow(onEdit, onDelete)
        }
    }
}

@Composable
fun ActionsRow(onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.size_xsmall))
    ) {
        IconButton(onClick = { onEdit }) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.edit_description)
            )
        }
        IconButton(onClick = { onDelete() }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_description)
            )
        }
    }
}
