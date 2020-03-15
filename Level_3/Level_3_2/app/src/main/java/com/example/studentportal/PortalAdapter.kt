package com.example.studentportal


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_portal_adapter.view.*

class PortalAdapter(private val portals: List<Portal>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(portal: Portal){
            itemView.tvTitleAdapter.text = portal.title
            itemView.tvUrlAdapter.text = portal.url
            itemView.setOnClickListener{
                val url = Uri.parse(portal.url)
                val intent = Intent(Intent.ACTION_VIEW, url)
                val bundle = Bundle()
                bundle.putBoolean("new_window", true)
                intent.putExtras(bundle)
                var context: Context = itemView.context
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.content_portal_adapter, parent, false))
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }
}