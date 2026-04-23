package com.example.rssapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rssapp.model.FeedItem

@Composable
fun FeedScreen(modifier: Modifier = Modifier, viewModel: RssViewModel) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Feed Screen")
        val channels by viewModel.channels.collectAsStateWithLifecycle()
        channels.forEach { channel ->
            LazyColumn {
                items(items = channel.channelChildren) { item ->
                    ItemBubble(itemToShow = item)
                }
            }
        }
    }
}

@Composable
fun ItemBubble(
    modifier: Modifier = Modifier,
    itemToShow: FeedItem
) {

    val uriHandler = LocalUriHandler.current
    Button(
        onClick = { uriHandler.openUri(itemToShow.link) },
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth(0.9f)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column() {
            Text(
                text = itemToShow.headline,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Written by ${itemToShow.author} at ${itemToShow.publisher}",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = itemToShow.publicationDate,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = itemToShow.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

//@Composable
//@Preview(showBackground = true)
//fun FeedScreenPreview(){
//    FeedScreen()
//}

@Composable
@Preview(showBackground = true)
fun ItemBubblePreview() {
    ItemBubble(
        itemToShow = FeedItem(
            author = "Author",
            publisher = "Publisher",
            headline = "HEADLINE HERE",
            link = "Link",
            description = "Lorem ipsum something something",
            publicationDate = "19.4.2026"
        )
    )
}