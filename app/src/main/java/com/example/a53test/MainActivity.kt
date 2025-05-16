@file:Suppress("UNREACHABLE_CODE")
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.a53test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a53test.ui.theme._53TestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Change()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Change() {
    var drawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawer, gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet {
                Inside(drawer, scope)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = { scope.launch { drawer.open() } },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_menu_24),
                                tint = Color(0xFF005E86),
                                contentDescription = ""
                            )
                        }
                    },
                    title = {
                        Image(
                            modifier = Modifier.width(300.dp),
                            painter = painterResource(R.drawable.p),
                            contentDescription = ""
                        )
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .background(Color.Cyan)
            ) {
                val pagerState = rememberPagerState(pageCount = {2})
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.height(200.dp)
                ) { page ->
                    val image = when (page) {
                        0 -> R.drawable.d
                        1 -> R.drawable.s
                        else -> R.drawable.baseline_square_24
                    }
                    Image(
                        painter = painterResource(image),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Inside(drawer: DrawerState, scope: CoroutineScope) {
    val list = listOf("關於展覽", "樓層立體圖", "公共藝術", "聯絡我們", "全部票卡", "主畫面")
    val information = listOf("經營者", "展館介紹")

    LazyColumn {
        items(list) { item ->
            Row(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(10.dp)
                    .size(height = 20.dp, width = 100.dp)
                    .clickable { scope.launch { drawer.close() } },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_square_24),
                    tint = Color(0xFF025872),
                    contentDescription = "",
                )
                Text(item, fontSize = 15.sp)
            }
            if (item == "關於展覽") {
                information.forEach {
                    Row(
                        modifier = Modifier
                            .padding(start = 45.dp)
                            .width(100.dp)
                            .size(height = 20.dp, width = 100.dp)
                            .clickable { scope.launch { drawer.close() } },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(R.drawable.l),
                            tint = Color(0xFF025872),
                            contentDescription = "",
                        )
                        Text(it, fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}