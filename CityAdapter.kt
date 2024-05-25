package com.example.travelbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelbook.databinding.RecyclerRowBinding

class CityAdapter(private val cityArrayList: ArrayList<City>) : RecyclerView.Adapter<CityAdapter.CityHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowBinding.inflate(inflater, parent, false)
        return CityHolder(binding)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.binding.recyclerTextView.text = cityArrayList[position].cityname
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("cityId", cityArrayList[position].id)
            intent.putExtra("info", "old")
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cityArrayList.size
    }

    class CityHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)
}
