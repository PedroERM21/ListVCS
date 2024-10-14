package com.example.listviewconcustomadapter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: CustomAdapter
    private var contactos = listOf(
        ContactModel("Pedro Reynoso", "5574811280", "prpedro@mail.com", R.drawable.pedro_r),
        ContactModel("Atza", "5612389023", "azprincess@mail.com", R.drawable.atza),
        ContactModel("Edgar", "5584972401", "luised@mail.com", R.drawable.edgar),
        ContactModel("Mau", "5637498205", "dobleaa@mail.com", R.drawable.mau),
        ContactModel("Carlos", "5698478343", "armado@mail.com", R.drawable.carlos),
    )
    private var contactosFiltrados = ArrayList(contactos) // Para almacenar los contactos filtrados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.Contactos)
        searchView = findViewById(R.id.searchView)

        adapter = CustomAdapter(this, contactosFiltrados)
        listView.adapter = adapter

        configurarBusqueda()
    }

    private fun configurarBusqueda() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarContactos(newText)
                return true
            }
        })
    }

    private fun filtrarContactos(texto: String?) {
        contactosFiltrados.clear()
        if (!texto.isNullOrEmpty()) {
            val textoMinusculas = texto.lowercase()
            contactosFiltrados.addAll(contactos.filter {
                it.nombre.lowercase().contains(textoMinusculas) || it.telefono.contains(texto)
            })
        } else {
            contactosFiltrados.addAll(contactos)
        }
        adapter.notifyDataSetChanged()
    }
}
