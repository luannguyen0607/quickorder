package com.luannt.quickorder.data

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val category: String,
    val isPrescription: Boolean
)
