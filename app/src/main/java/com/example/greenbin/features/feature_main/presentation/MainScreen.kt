// app/src/main/kotlin/com/greenbin/features/feature_main/presentation/MainScreen.kt

package com.greenbin.features.feature_main.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.domain.banner.model.Banner
import com.example.domain.banner.model.StoryCard
import com.example.domain.categories.model.Category
import com.example.domain.info.model.InfoCard
import com.example.greenbin.features.feature_main.presentation.MainUiEffect
import com.example.greenbin.features.feature_main.presentation.MainUiEvent
import com.example.greenbin.features.feature_main.presentation.MainViewModel
import com.example.greenbin.navigation.AppScreen
import com.example.greenbin.presentation.ui.components.CustomTopAppBar
import com.greenbin.ui.components.AppBottomNavigationBar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    var selectedBanner by remember { mutableStateOf<Banner?>(null) }
    var selectedInfoCard by remember { mutableStateOf<InfoCard?>(null) }
    var currentStoryIndex by remember { mutableStateOf(0) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    // Модальное окно для инфокарточки
    if (selectedInfoCard != null) {
        AlertDialog(
            onDismissRequest = { selectedInfoCard = null },
            title = { Text(selectedInfoCard!!.title) },
            text = { Text(selectedInfoCard!!.description) },
            confirmButton = {
                TextButton(onClick = { selectedInfoCard = null }) { Text("OK") }
            }
        )
    }

    // BottomSheet для баннера (историй)
    selectedBanner?.let { banner ->
        ModalBottomSheet(
            onDismissRequest = {
                selectedBanner = null
                currentStoryIndex = 0   // сброс индекса
            },
            sheetState = sheetState
        ) {
            val pagerState = rememberPagerState(
                initialPage = currentStoryIndex,
                initialPageOffsetFraction = 0.1F,
                pageCount = { banner.cards.size }
            )
            HorizontalPager(
                state = pagerState
            ) { page ->
                StoryCardContent(
                    card = banner.cards[page],
                    onNext = {
                        if (page < banner.cards.size - 1) {
                            // Переход к следующей карточке
                            currentStoryIndex = page + 1
                        } else {
                            selectedBanner = null
                        }
                    }
                )
            }
        }
    }


    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is MainUiEffect.Navigate.ToCategoryMap -> navController.navigate(
                    AppScreen.Map(effect.categoryId)
                )
                // другие навигации...
                MainUiEffect.Navigate.ToGlobalMap -> TODO()
                MainUiEffect.Navigate.ToLessons -> TODO()
                MainUiEffect.Navigate.ToProfile -> TODO()
            }
        }
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = uiState.userName) },
        bottomBar = { AppBottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
//                .verticalScroll(rememberScrollState())
        ) {
            // === Баннеры (верхняя часть) ===
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.banners) { banner ->
                        BannerItem(
                            banner = banner,
                            onBannerClick = { selectedBanner = banner }
                        )
                    }
                }
            }

            // === Заголовок "Посмотреть пункты приема" ===
            item {
                Text(
                    text = "Посмотреть пункты приема",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // === Категории (горизонтальная прокрутка) ===
            item {
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.categories) { category ->
                        CategoryItem(
                            category = category,
                            onClick = { viewModel.onEvent(MainUiEvent.CategoryClicked(category.id)) }
                        )
                    }
                }
            }

            // === Полезная информация ===
            item {
                Text(
                    text = "Полезная информация",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }

            // === Карточки информации (по 2 в ряд) ===
            val chunkedCards = uiState.infoCards.chunked(2)

            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.infoCards) { index ->
                        InfoCardItem(
                            card = index,
                            onInfoCardClick = { card -> selectedInfoCard = card }
                        )
                    }
                }
            }

//            items(chunkedCards.size) { index ->
//                val pair = chunkedCards[index]
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    InfoCardItem(card = pair[0], modifier = Modifier.weight(1f))
//                    if (pair.size > 1) {
//                        InfoCardItem(card = pair[1], modifier = Modifier.weight(1f))
//                    } else {
//                        Spacer(modifier = Modifier.weight(1f))
//                    }
//                }
//            }

            if (uiState.error != null) {
                item {
                    Text(
                        text = uiState.error!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

// === Баннер ===
@Composable
private fun BannerItem(banner: Banner, onBannerClick: (Banner) -> Unit ) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(280.dp)
            .clickable {onBannerClick(banner)},
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)) // светлый фон
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = banner.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
//                Text(banner.title, style = MaterialTheme.typography.titleMedium, color = Color.Black) // На карточках уже все есть. Блииииин
//                Text(banner.subtitle, style = MaterialTheme.typography.bodyMedium, color = Color.Black.copy(alpha = 0.8f))
            }
        }
    }
}

// === Категория ===
@Composable
private fun CategoryItem(category: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(48.dp),
                tint = Color.Unspecified   // если иконка цветная
            )
            Spacer(Modifier.height(8.dp))
            Text(category.name, style = MaterialTheme.typography.labelSmall)
        }
    }
}

// === Инфокарточка (по 2 в ряд) ===
@Composable
private fun InfoCardItem(card: InfoCard, modifier: Modifier = Modifier, onInfoCardClick: (InfoCard) -> Unit ) {
    Card(
        modifier = modifier
            .height(180.dp)
            .clickable { onInfoCardClick(card) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(card.title, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start)
            Spacer(Modifier.height(12.dp))
            Image(
                painter = painterResource(id = card.imageRes),
                contentDescription = card.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}


@Composable
fun StoryCardContent(card: StoryCard, onNext: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Image(painterResource(card.imageRes), contentDescription = null, modifier = Modifier.fillMaxWidth())
        Text(card.title, style = MaterialTheme.typography.titleLarge)
        Text(card.text, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(32.dp))
        Button(onClick = onNext) {
            Text("Далее")
        }
    }
}