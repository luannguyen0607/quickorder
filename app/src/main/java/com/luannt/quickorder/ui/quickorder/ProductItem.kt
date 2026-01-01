package com.luannt.quickorder.ui.quickorder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luannt.quickorder.data.Product

@Composable
fun ProductItem(
    product: Product, quantity: Int, onPlus: () -> Unit, onMinus: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(Modifier.padding(12.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(product.name, fontWeight = FontWeight.Bold)
                if (product.isPrescription) {
                    Spacer(Modifier.width(8.dp))
                    Text("Rx", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            }

            Text(product.category)
            Text("${product.price} VND")

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onMinus) {
                    Icon(Icons.Default.Remove, contentDescription = null)
                }
                Text(quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                IconButton(onClick = onPlus) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        }
    }
}
