/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * Features.kt is part of Kizzy
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.kizzy.feature_home.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.my.kizzy.resources.R
import com.my.kizzy.ui.components.KSwitch

/**
 * Home feature list rendered as a vertical stack of full-width cards.
 * Each card can be tapped to open its screen, and — when a switch is
 * available — toggled right from the home screen to start/stop its RPC.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Features(
    homeItems: List<HomeFeature> = emptyList(), onValueUpdate: (Int) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i in homeItems.indices) {
            val item = homeItems[i]
            if (item.tooltipText.isNotBlank()) {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                    state = rememberTooltipState(),
                    tooltip = {
                        RichTooltip(
                            title = {
                                Text(item.title, style = MaterialTheme.typography.titleMedium)
                            },
                            text = { Text(item.tooltipText) },
                            action = {
                                TextButton(onClick = { uriHandler.openUri(item.featureDocsLink) }) {
                                    Text(text = stringResource(R.string.learn_more))
                                }
                            },
                        )
                    },
                ) {
                    FeatureCard(
                        feature = item,
                        onToggle = {
                            item.onCheckedChange(!item.isChecked)
                            onValueUpdate(i)
                        }
                    )
                }
            } else {
                FeatureCard(
                    feature = item,
                    onToggle = {
                        item.onCheckedChange(!item.isChecked)
                        onValueUpdate(i)
                    }
                )
            }
        }
    }
}

@Composable
private fun FeatureCard(
    feature: HomeFeature,
    onToggle: () -> Unit,
) {
    val brush = if (feature.isChecked) {
        Brush.linearGradient(
            listOf(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.primaryContainer.copy(0.8f),
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
            )
        )
    } else {
        Brush.linearGradient(
            listOf(
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.colorScheme.secondaryContainer.copy(0.8f),
                MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)
            )
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(brush = brush)
            .clickable { feature.route?.let { feature.onClick(it) } }
            .padding(start = 22.dp, end = 18.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Icon(
            tint = if (feature.isChecked) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.secondary,
            painter = painterResource(id = feature.icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = feature.title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W500),
            color = if (feature.isChecked) MaterialTheme.colorScheme.onPrimaryContainer
            else MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.weight(1f)
        )
        if (feature.showSwitch) {
            Spacer(modifier = Modifier.width(12.dp))
            KSwitch(
                checked = feature.isChecked,
                onClick = onToggle
            )
        }
    }
}
