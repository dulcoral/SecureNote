package com.coral.example.securenote.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.coral.example.securenote.R
import com.coral.example.securenote.ui.viewmodel.SecureNotesViewModel
import com.coral.example.securenote.utils.Utils.closeKeyboard

@Composable
fun AddNoteScreen(
    viewModel: SecureNotesViewModel,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography
    val shapes = MaterialTheme.shapes
    val colors = MaterialTheme.colors
    val view = LocalView.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .clickable { closeKeyboard(view) }
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.noteTitle,
            onValueChange = { viewModel.updateNoteTitle(it) },
            label = { Text(stringResource(R.string.add_note_title_hint)) },
            textStyle = typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colors.primary,
                unfocusedBorderColor = colors.onSurface.copy(alpha = 0.3f)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { closeKeyboard(view) }),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.size_medium))
        )

        OutlinedTextField(
            value = viewModel.noteMessage,
            onValueChange = { viewModel.updateNoteMessage(it) },
            label = { Text(stringResource(R.string.add_note_message_hint)) },
            textStyle = typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colors.primary,
                unfocusedBorderColor = colors.onSurface.copy(alpha = 0.3f)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { closeKeyboard(view) }),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = dimensionResource(R.dimen.size_medium))
        )

        Button(
            onClick = {
                viewModel.addNewNote()
                onSaveClick()
            },
            shape = shapes.medium,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.add_note_button))
        }
    }

}



