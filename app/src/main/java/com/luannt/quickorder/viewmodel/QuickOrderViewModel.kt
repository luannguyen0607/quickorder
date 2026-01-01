package com.luannt.quickorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luannt.quickorder.data.Product
import com.luannt.quickorder.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class QuickOrderViewModel : ViewModel() {

    private val repo = ProductRepository()
    private val products = repo.getProducts()

    private val _search = MutableStateFlow("")
    private val _category = MutableStateFlow("All")
    private val _cart = MutableStateFlow<Map<Int, Int>>(emptyMap())

    val uiState = combine(_search, _category, _cart) { search, category, cart ->

        val filtered = products.filter {
            it.name.contains(search, true) &&
                    (category == "All" || it.category == category)
        }

        val totalSku = cart.size
        val totalQty = cart.values.sum()
        val totalAmount = cart.entries.sumOf { entry ->
            products.first { it.id == entry.key }.price * entry.value
        }

        UiState(
            products = filtered,
            cart = cart,
            totalSku = totalSku,
            totalQty = totalQty,
            totalAmount = totalAmount,
            searchText = search,
            selectedCategory = category
        )
    }

    fun search(text: String) {
        _search.value = text
    }

    fun filter(category: String) {
        _category.value = category
    }

    fun increase(product: Product) {
        val current = _cart.value[product.id] ?: 0
        if (current >= 99) return

        _cart.value += (product.id to current + 1)
    }

    fun decrease(product: Product) {
        val current = _cart.value[product.id] ?: return
        if (current <= 1) {
            _cart.value -= product.id
        } else {
            _cart.value += (product.id to current - 1)
        }
    }
}

data class UiState(
    val products: List<Product> = emptyList(),
    val cart: Map<Int, Int> = emptyMap(),
    val totalSku: Int = 0,
    val totalQty: Int = 0,
    val totalAmount: Int = 0,
    val searchText: String = "",
    val selectedCategory: String = "All"
)

