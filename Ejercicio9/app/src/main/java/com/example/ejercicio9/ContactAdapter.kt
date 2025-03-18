package com.example.ejercicio9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val contacts: List<Contact>,
    private val listener: OnContactClickListener? = null
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    interface OnContactClickListener {
        fun onContactClick(contact: Contact)
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewPhone: TextView = itemView.findViewById(R.id.textViewPhone)

        init {
            itemView.setOnClickListener {
                listener?.onContactClick(contacts[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.textViewName.text = contact.name
        holder.textViewPhone.text = contact.phone
    }

    override fun getItemCount(): Int = contacts.size
}
