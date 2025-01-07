package com.example.lista_compra_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GroceryAdapter
    private val groceryList = mutableListOf<GroceryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GroceryAdapter(groceryList)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            startActivityForResult(Intent(this, AddItemActivity::class.java), ADD_ITEM_REQUEST)
        }

        findViewById<Button>(R.id.btnDeleteAll).setOnClickListener {
            groceryList.clear()
            adapter.notifyDataSetChanged()
        }

        findViewById<Button>(R.id.btnDeleteSelected).setOnClickListener {
            groceryList.removeAll { it.isSelected }
            adapter.notifyDataSetChanged()
        }

        findViewById<Button>(R.id.btnPrint).setOnClickListener {
            val intent = Intent(this, PrintActivity::class.java)
            intent.putExtra("groceryList", ArrayList(groceryList))
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
            data?.getParcelableExtra<GroceryItem>("item")?.let {
                groceryList.add(it)
                adapter.notifyItemInserted(groceryList.size - 1)
            }
        }
    }

    companion object {
        const val ADD_ITEM_REQUEST = 1
    }
}
