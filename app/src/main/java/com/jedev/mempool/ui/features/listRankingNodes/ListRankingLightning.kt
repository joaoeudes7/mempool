package com.jedev.mempool.ui.features.listRankingNodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.LineAxis
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jedev.mempool.R
import com.jedev.mempool.domain.model.NodeConnectivityModel
import com.jedev.mempool.ui.main.MainViewModel
import com.jedev.mempool.ui.theme.TitleColor
import com.jedev.mempool.utils.PreviewMD
import com.jedev.mempool.utils.roundedCorners
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListRankingLightning(
    modifier: Modifier,
    vm: ListRankingLightningViewModel = koinViewModel(),
    vmMain: MainViewModel = koinViewModel()
) {
    val localClipboardManager = LocalClipboardManager.current
    val msgPkCopied = stringResource(R.string.pk_copied)

    LaunchedEffect(Unit) {
        vm.fetchData(true)
    }

    ListRankingLightningContent(
        modifier = modifier,
        uiState = vm.uiState,
        actions = ListRankingLightningUiActions(
            onRefresh = { vm.fetchData(true) },
            onCopyPk = {
                localClipboardManager.setText(AnnotatedString(it))
                vmMain.setSnackBarMessage(msgPkCopied)
            }
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListRankingLightningContent(
    modifier: Modifier,
    uiState: ListRankingLightningState,
    actions: ListRankingLightningUiActions
) = PullToRefreshBox(
    modifier = modifier,
    isRefreshing = uiState.isRefreshing,
    onRefresh = actions.onRefresh
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        items(uiState.nodes) {
            ItemRankingNode(it) { actions.onCopyPk(it.publicKey) }
        }
    }
}

@Composable
fun ItemRankingNode(item: NodeConnectivityModel, onCopy: () -> Unit) = Column(
    Modifier
        .padding(vertical = 4.dp)
        .fillMaxWidth()
        .roundedCorners(color = MaterialTheme.colorScheme.secondary)
        .background(MaterialTheme.colorScheme.primary)
        .padding(16.dp)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            text = item.alias,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = TitleColor
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Filled.AccessTime,
                tint = Color.Gray,
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = item.updatedAtFormatted,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = item.publicKey,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
            color = Color.Gray
        )

        IconButton(modifier = Modifier.size(24.dp), onClick = { onCopy() }) {
            Icon(
                imageVector = Icons.Default.CopyAll,
                contentDescription = stringResource(R.string.copy),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }

    RowIconText(
        imageVector = Icons.Default.LocationOn,
        text = item.location,
    )

    RowIconText(
        imageVector = Icons.Default.RemoveRedEye,
        text = "Vista pela primeira vez: ${item.firstSeenFormatted}"
    )

    RowIconText(
        imageVector = Icons.Filled.LineAxis,
        text = "Canais ativos: ${item.channelsFormatted}"
    )

    RowIconText(
        imageVector = Icons.Default.AttachMoney,
        text = "Capacidade: ${item.capacityFormatted}"
    )
}

@Composable
fun RowIconText(
    imageVector: ImageVector,
    text: String
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier.size(16.dp),
        imageVector = imageVector,
        contentDescription = text,
        tint = MaterialTheme.colorScheme.onSurface
    )

    Text(
        modifier = Modifier
            .padding(start = 8.dp)
            .weight(1f),
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Preview
@Composable
private fun PreviewItem() = PreviewMD {
    ItemRankingNode(
        NodeConnectivityModel(
            alias = "ACINQ",
            channels = 2908,
            capacity = 62496865516,
            firstSeen = 1522941222,
            country = "United States",
            city = "San Francisco",
            publicKey = "03864ef025fde8fb587d989186ce6a4a186895ee44a926bfc370e2c366597a3f8f",
            updatedAt = 1661274935,
        )
    ) {}
}