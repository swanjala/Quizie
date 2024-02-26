package com.example.quizie.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizie.R
import com.example.quizie.composables.components.HeaderItemsDimens.heightDimens

@Composable
fun QuestionHeaderItemsContainer(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(HeaderItemsDimens.roundedCornerDimens))
            .background(Color.White)
            .width(IntrinsicSize.Min)
            .padding(horizontal = 6.dp)
            .height(heightDimens),
        contentAlignment = Alignment.Center

    ) {
        content()
    }
}

@Composable
fun HeaderText(
    fontSize: TextUnit,
    text: String,
) {
    QuestionHeaderItemsContainer {
        Text(
            modifier = Modifier.width(50.dp),
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HeaderIconText(
    content: @Composable () -> Unit
) {

    QuestionHeaderItemsContainer {
        Row(
            modifier = Modifier
                .width(80.dp)
                .padding(start = 5.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .scale(1.3f),
                painter = painterResource(id = R.drawable.icon_type),
                contentDescription = ""
            )
            content()
        }
    }
}

@Composable
fun HeaderBanner(
    headerResources: Pair<Color, Int>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(headerResources.first)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge.copy(
                shadow = Shadow(
                    color = Color.DarkGray.copy(alpha = 0.3f),
                    offset = Offset(0.0f, 7.2f)
                )
            ),
            color = Color(0xFFFFFFFF),
            text = stringResource(id = headerResources.second),
        )
    }
}

object HeaderItemsDimens {
    val roundedCornerDimens = 60.dp
    val heightDimens = 42.dp
    val fontSize = 17.sp
    val fontSize2 = 15.sp
    val width = 35.dp
    val height = 20.dp
}

@Preview(showBackground = true)
@Composable
fun HeaderTextPreview() {
    HeaderText(
        fontSize = HeaderItemsDimens.fontSize,
        text = "2/14"
    )
}

@Preview(showBackground = true)
@Composable
fun HeaderIconTextPreview() {
    HeaderIconText() {
        HeaderText(
            fontSize = HeaderItemsDimens.fontSize2,
            text = "Quiz"
        )
    }
}
