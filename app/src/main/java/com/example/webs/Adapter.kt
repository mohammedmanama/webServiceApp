package com.example.webs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

open class Adapter (val context: Context, val list: ArrayList<case>, val click: onClick) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.number?.text = list[position].case_number
        holder.age?.text = list[position].case_age
        holder.gender?.text = list[position].case_gender
        holder.location?.text = list[position].case_location
        holder.date?.text = list[position].case_diagnose_date
        holder.infection?.text = list[position].case_source_of_infection
        holder.condition?.text = list[position].case_condition
        holder.quarantine?.text = list[position].case_quarantine
        holder.community?.text = list[position].case_community

        holder.card.setOnClickListener {
            click.onClickItem(holder.adapterPosition)
        }
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val number = item.number
        val age = item.age
        val gender = item.gender
        val location = item.location
        val date = item.date
        val infection = item.infection
        val condition = item.condition
        val quarantine = item.quarantine
        val community = item.community
        val card = item.cardview
    }

    interface onClick {
        fun onClickItem(position: Int)
    }
}