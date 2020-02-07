package com.joanthandarwin.selectablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joanthandarwin.selectablerecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : ItemAdapter
    private val listItem = ArrayList<Item>()
    private val listSelected = ArrayList<Item>()
    private lateinit var binding : ActivityMainBinding
    private lateinit var snackBar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        snackBar = Snackbar.make(binding.main, "${listSelected.size} item(s) selected", Snackbar.LENGTH_INDEFINITE)
        getList()
        setAdapter()
    }

    private fun getList(){
        for (i in 1 until 10){
            listItem.add(Item(i, "Item $i"))
        }
    }

    private fun setAdapter(){
        adapter = ItemAdapter{
            item, checked ->
                when(checked){
                    true -> listSelected.add(item)
                    false -> listSelected.remove(item)
                }
                updateSelected()
        }
        adapter.update(listItem)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun updateSelected(){
        if(listSelected.size == 0){
            snackBar.dismiss()
        }
        else{
            snackBar.setText("${listSelected.size} item(s) selected")
            snackBar.show()
        }
    }
}
