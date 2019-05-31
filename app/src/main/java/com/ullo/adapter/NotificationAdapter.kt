package com.ullo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ullo.R
import com.ullo.api.response.contact.Contact
import com.ullo.databinding.ContactListItemBinding
import com.ullo.databinding.NotificationListItemBinding

class NotificationAdapter(var context: Context) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    var contactList: ArrayList<Contact> = ArrayList()

    fun setContactListData(contactList: ArrayList<Contact>) {
        this.contactList = contactList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val notificationListItemBinding = DataBindingUtil.inflate<NotificationListItemBinding>(LayoutInflater.from(context), R.layout.notification_list_item, parent, false)
        return ViewHolder(notificationListItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = contactList[position]
        holder.bind(patient)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ViewHolder internal constructor(private val notificationListItemBinding: NotificationListItemBinding) : RecyclerView.ViewHolder(notificationListItemBinding.root) {

        fun bind(contact: Contact) {
            notificationListItemBinding.executePendingBindings()
        }
    }
}