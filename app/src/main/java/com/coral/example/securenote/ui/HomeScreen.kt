package com.coral.example.securenote.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.coral.example.securenote.R
import com.coral.example.securenote.ui.components.EmptyNotesView
import com.coral.example.securenote.ui.components.NoteCardItem
import com.coral.example.securenote.ui.models.NoteItem
import com.coral.example.securenote.ui.viewmodel.SecureNotesViewModel

@Composable
fun HomeScreen(
    notes: List<NoteItem>,
    viewModel: SecureNotesViewModel,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.size_medium))
    ) {
        Text(
            text = stringResource(R.string.my_notes),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_xbig)))
        LazyColumn(content = {
            if (notes.isEmpty()) {
                item {
                    EmptyNotesView()
                }
            } else {
                items(notes) { note ->
                    NoteCardItem(
                        note = note,
                        onEdit = {
                            viewModel.updateNote(note)
                            onEdit()
                        },
                        onDelete = { viewModel.deleteNote(note.id) }
                    )
                }
            }
        })
    }
}
