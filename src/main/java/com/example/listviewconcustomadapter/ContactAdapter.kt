package com.example.listviewconcustomadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter (private val context: Context, private val contactos: List<ContactModel>) : BaseAdapter(){

    override fun getCount(): Int {
        return contactos.size
    }
    override fun getItem(position: Int): Any{
        return contactos[position]
    }
    override fun getItemId(position: Int): Long{
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_contact, parent, false)

        val contacto = contactos[position]
        val imagen = view.findViewById<ImageView>(R.id.contactoImagen)
        val nombre = view.findViewById<TextView>(R.id.Nombre)
        val telefono = view.findViewById<TextView>(R.id.Telefono)
        val email = view.findViewById<TextView>(R.id.Email)

        imagen.setImageResource(contacto.imageId)
        nombre.text = contacto.nombre
        telefono.text = contacto.telefono
        email.text = contacto.email

        return view
    }
}