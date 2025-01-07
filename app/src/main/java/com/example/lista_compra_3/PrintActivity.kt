package com.example.lista_compra_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PrintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print)

        val tvPrintList = findViewById<TextView>(R.id.tvPrintList)
        val groceryList = intent.getParcelableArrayListExtra<GroceryItem>("groceryList")

        val printText = groceryList?.joinToString("\n") { item ->
            "${item.quantity} x ${item.name} (${item.section})${if (item.isUrgent) " - URGENTE" else ""}"
        }

        tvPrintList.text = printText
    }
}
