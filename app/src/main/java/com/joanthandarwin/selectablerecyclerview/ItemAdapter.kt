package com.joanthandarwin.selectablerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joanthandarwin.selectablerecyclerview.databinding.ListItemBinding

class ItemAdapter(private val listener : (Item, Boolean) -> Unit) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    private var listItem = ArrayList<Item>()

    inner class ItemViewHolder(private val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Item){
            binding.item = item
            binding.cbxItem.setOnClickListener {
                this@ItemAdapter.listener(item, binding.cbxItem.isChecked)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    fun update(listItem : ArrayList<Item>){
        this.listItem.clear()
        this.listItem.addAll(listItem)
        notifyDataSetChanged()
    }
}