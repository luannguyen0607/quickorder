package com.luannt.quickorder.data

class ProductRepository {
    fun getProducts() = listOf(
        Product(1, "Paracetamol 500mg", 15000, "Pain Relief", false),
        Product(2, "Amoxicillin 500mg", 45000, "Antibiotic", true),
        Product(3, "Vitamin C 1000mg", 30000, "Supplement", false),
        Product(4, "Cetirizine 10mg", 20000, "Allergy", false)
    )
}