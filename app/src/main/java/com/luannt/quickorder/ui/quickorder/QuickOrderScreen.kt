package com.luannt.quickorder.ui.quickorder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luannt.quickorder.viewmodel.QuickOrderViewModel
import com.luannt.quickorder.viewmodel.UiState

@Composable
fun QuickOrderScreen(
    viewModel: QuickOrderViewModel = viewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycle = lifecycleOwner.lifecycle,
        initialValue = UiState()
    )

    val categories = listOf(
        "All", "Pain Relief", "Antibiotic", "Supplement", "Allergy"
    )

    Column(
        Modifier
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {

        OutlinedTextField(
            value = state.searchText,
            onValueChange = viewModel::search,
            label = { Text("Search product") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        CategoryTabs(
            categories = categories,
            selectedCategory = state.selectedCategory,
            onCategorySelected = viewModel::filter
        )

        Spacer(Modifier.height(8.dp))

        if (state.products.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No products found")
            }
        } else {
            LazyColumn(Modifier.weight(1f)) {
                items(state.products) { product ->
                    ProductItem(
                        product = product,
                        quantity = state.cart[product.id] ?: 0,
                        onPlus = { viewModel.increase(product) },
                        onMinus = { viewModel.decrease(product) }
                    )
                }
            }
        }

        HorizontalDivider()

        Text(
            text = "SKUs: ${state.totalSku} | Qty: ${state.totalQty} | Total: ${state.totalAmount} VND",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CategoryTabs(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val selectedIndex = categories.indexOf(selectedCategory)

    TabRow(selectedTabIndex = selectedIndex) {
        categories.forEachIndexed { index, title ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onCategorySelected(title) },
                text = { Text(title) }
            )
        }
    }
}