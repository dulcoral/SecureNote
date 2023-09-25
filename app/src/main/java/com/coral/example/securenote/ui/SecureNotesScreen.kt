package com.coral.example.securenote.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.coral.example.securenote.R
import com.coral.example.securenote.ui.components.AddNoteButton
import com.coral.example.securenote.ui.components.NoteCardItem
import com.coral.example.securenote.ui.models.NoteItem

@Preview
@Composable
fun SecureNotesScreen() {
    Scaffold(
        floatingActionButton = { AddNoteButton(onButtonClick = { }) }
    ) {
        NotesListView(listOf(NoteItem("My first note", "Hi, this is a note!", {}, {})))
    }

}

@Composable
fun NotesListView(notes: List<NoteItem>) {
    Column(
        modifier = Modifier
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
                    Text(text = "There are no saved notes")
                }
            } else {
                items(notes) { NoteCardItem(it) }
            }
        })
    }
}