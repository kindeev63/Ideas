package com.knomster.ideas.presentation.screens

import com.knomster.ideas.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.knomster.ideas.domain.elements.IdeaBacklink
import com.knomster.ideas.domain.elements.IdeaData
import com.knomster.ideas.domain.elements.IdeaElements
import com.knomster.ideas.presentation.viewModels.ideaScreen.IdeaScreenVM
import com.knomster.ideas.presentation.viewModels.ideaScreen.IdeaScreenVMFactory

@Composable
fun IdeaScreen(
    ideaId: Int? = null,
    withBackLink: IdeaBacklink? = null,
    back: () -> Unit
) {
    val viewModel: IdeaScreenVM = viewModel(
        factory = IdeaScreenVMFactory(ideaId, withBackLink)
    )
    val idea by viewModel.idea.observeAsState(IdeaData(0, "", 0L, emptyList(), emptyList()))
    // UI
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (!(idea.elements.isEmpty() == true && ideaId == null)) {
                        viewModel.save()
                    }
                    back()
                }
            ) {
                Icon(
                    painter = painterResource(if (idea.elements.isEmpty() == true && ideaId == null) R.drawable.ic_close else R.drawable.ic_save),
                    contentDescription = "Floating action button image"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                Text(text = idea.name)
            }
            items(
                items = idea.elements
            ) { item ->
                when(item) {
                    is IdeaElements.IdeaTextElement -> TODO()
                    is IdeaElements.IdeaImageElement -> TODO()
                    is IdeaElements.IdeaLinksElement -> TODO()
                    is IdeaElements.IdeaFileElement -> TODO()
                }
            }
        }
    }
}