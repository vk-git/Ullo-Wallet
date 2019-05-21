package com.ullo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.tamir7.contacts.Contact
import com.ullo.R
import com.ullo.databinding.PatientListItemBinding

class ContactAdapter(var context: Context) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var contactList: ArrayList<Contact> = ArrayList()

    fun setContactData(contactList: ArrayList<Contact>) {
        this.contactList = contactList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contactListItemBinding = DataBindingUtil.inflate<PatientListItemBinding>(LayoutInflater.from(parent!!.context), R.layout.patient_list_item, parent, false)
        return ViewHolder(contactListItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = contactList[position]
        holder.bind(patient)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ViewHolder internal constructor(private val contactListItemBinding: PatientListItemBinding) : RecyclerView.ViewHolder(contactListItemBinding.root) {

        fun bind(contact: Contact) {
            contactListItemBinding.contact = contact
            contactListItemBinding.executePendingBindings()
        }
    }
}