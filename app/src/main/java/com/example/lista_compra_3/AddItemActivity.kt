package com.example.lista_compra_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val etQuantity = findViewById<EditText>(R.id.etQuantity)
        val etName = findViewById<EditText>(R.id.etName)
        val spinnerSection = findViewById<Spinner>(R.id.spinnerSection)
        val cbUrgent = findViewById<CheckBox>(R.id.cbUrgent)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        ArrayAdapter.createFromResource(
            this,
            R.array.sections_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSection.adapter = adapter
        }

        btnAdd.setOnClickListener {
            val quantity = etQuantity.text.toString().toIntOrNull()
            val name = etName.text.toString()
            val section = spinnerSection.selectedItem.toString()
            val isUrgent = cbUrgent.isChecked

            if (quantity != null && quantity > 0 && name.isNotBlank()) {
                val item = GroceryItem(quantity, name, section, isUrgent)
                val intent = Intent()
                intent.putExtra("item", item)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
