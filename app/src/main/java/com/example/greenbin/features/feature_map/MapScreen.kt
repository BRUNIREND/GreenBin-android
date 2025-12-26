package com.example.greenbin.features.feature_map

import android.Manifest
import android.graphics.PointF
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.data.R
import com.example.greenbin.features.feature_map.viewmodel.MapViewModel
import com.example.greenbin.presentation.ui.components.PointDetailContent
import com.greenbin.ui.components.AppBottomNavigationBar
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    categoryId: String?,
    navController: NavHostController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val points by viewModel.points.collectAsStateWithLifecycle()
    val selectedPoint by viewModel.selectedPoint.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()

    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    )
    val sheetStateDetail = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var selectedTab by remember { mutableStateOf(0) } // 0 = Категории, 1 = История



    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                // Разрешение дано — можно включить мою позицию
                MapKitFactory.getInstance().onStart() // если нужно
            }
            else -> {
                // Разрешение отклонено — покажи объяснение
            }
        }
    }

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
    if (selectedPoint != null) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.clearSelectedPoint() },
            sheetState = sheetStateDetail
        ) {
            PointDetailContent(
                point = selectedPoint!!,
                onClose = { viewModel.clearSelectedPoint() }
            )
        }
    }
    Scaffold(
        bottomBar = { AppBottomNavigationBar(navController = navController) }
    ) {paddingValues ->
        BottomSheetScaffold(
            scaffoldState = sheetState,
            sheetPeekHeight = 140.dp, // высота в полураскрытом состоянии (поиск + табы)
            sheetSwipeEnabled = true,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetTonalElevation = 8.dp,
            sheetContent = {
                Column(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
                    // Поиск
                    OutlinedTextField(
                        value = viewModel.searchQuery,
                        onValueChange = { viewModel.updateSearchQuery(it) },
                        placeholder = { Text("Поиск") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(24.dp),
                        singleLine = true
                    )

                    Spacer(Modifier.height(16.dp))

                    // Табы
                    TabRow(selectedTabIndex = selectedTab) {
                        Tab(
                            selected = selectedTab == 0,
                            onClick = { selectedTab = 0 },
                            text = { Text("Категории") }
                        )
                        Tab(
                            selected = selectedTab == 1,
                            onClick = { selectedTab = 1 },
                            text = { Text("История") }
                        )
                    }

                    // Содержимое таба
                    if (selectedTab == 0) {
                        LazyRow(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(viewModel.categories) { category ->
                                FilterChip(
                                    selected = viewModel.selectedCategory.collectAsState().value == category.id,
                                    onClick = { viewModel.onCategorySelected(category.id) },
                                    label = { Text(category.name) },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = category.iconRes),
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                )
                            }
                        }
                    } else {
                        // История поиска (захардкожено для примера) TODO("Сделать историю поиска")
                        Column(modifier = Modifier.padding(16.dp)) {
                            val history = listOf(
                                "Сдать пластик",
                                "Сдать стекло",
                                "Сдать батарейки",
                                "Сдать бумагу",
                                "Сдать металл"
                            )
                            history.forEach { item ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .clickable { /* выполнить поиск */ }
                                ) {
                                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                                    Spacer(Modifier.width(8.dp))
                                    Text(item)
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(100.dp).fillMaxWidth()) // место для BottomBar
//                    AppBottomNavigationBar(
//                        navController = navController,
//                        modifier = Modifier.navigationBarsPadding()
//                    )
                }
            },
            content = { innerPadding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    // Карта Yandex
                    AndroidView(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        factory = { context ->
                            MapView(context).apply {
                                mapWindow.map.move(
                                    CameraPosition(Point(55.7558, 37.6173), 11f, 0f, 0f) // Москва центр
                                )
                            }
                        },
                        update = { mapView ->
                            mapView.map.mapObjects.clear()

                            points.forEach { point ->
                                val placemark = mapView.map.mapObjects.addPlacemark(
                                    Point(point.latitude, point.longitude)
                                )
                                placemark.setIcon(
                                    ImageProvider.fromResource(mapView.context, R.drawable.ic_pointer), // Обычные метки
//                            ImageProvider.fromResource(mapView.context, point.iconRes), // Метки ввиде иконок категорий сортировки
                                    IconStyle().apply {
                                        scale = 0.8f
                                        anchor = PointF(0.5f, 1f) // "ножка" метки на точку
                                    }
                                )
                                placemark.userData = point.id
                                placemark.addTapListener { _, _ ->
                                    viewModel.selectPoint(point)
//                            val pointId = placemark.userData as String
//                            Log.d("MapScreen", "Метка кликнута! pointId = $pointId")
//                            navController.navigate("point_detail/$pointId")   // ← просто строка
                                    true
                                }
                            }
                        },
                        onRelease = { mapView ->
                            mapView.onStop()
                            MapKitFactory.getInstance().onStop()
                        }
                    )

                }

            }
        )
    }

    DisposableEffect(Unit) {
        MapKitFactory.getInstance().onStart()
        onDispose {
            MapKitFactory.getInstance().onStop()
        }
    }
}

//@Composable
//fun SeatchWithTubs():
