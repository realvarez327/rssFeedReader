package com.example.rssapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rssapp.model.RssChannel
import androidx.compose.runtime.collectAsState

@Composable
fun PreferencesScreen(modifier: Modifier = Modifier, viewModel: RssViewModel) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(0.5f)
        ) {

        }
    }

}

@Composable
fun EditChannelsScreen(
    modifier: Modifier = Modifier,
    viewModel: RssViewModel,
    navigateToPref: () -> Unit
) {
    val channels: MutableList<RssChannel> =
        viewModel.channels.collectAsState().value as MutableList<RssChannel>

    LazyColumn {
        items(items = channels) { channel ->
            ChannelRow(
                channelId = channel.id,
                channelName = channel.title
            )
        }
    }

}

@Composable
fun ChannelRow(modifier: Modifier = Modifier, channelId: Int, channelName: String) {
    var userKeeping by remember { mutableStateOf(true) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = channelName
        )

        Button(
            onClick = {
                //pop up, asking for confirmation, delete channel
            }
        ) { }

    }


}


@Composable
@Preview(showBackground = true)
fun PreferencesScreenPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {}
        ) {
            Text(
                text = "Edit Channels"
            )
        }
    }
}