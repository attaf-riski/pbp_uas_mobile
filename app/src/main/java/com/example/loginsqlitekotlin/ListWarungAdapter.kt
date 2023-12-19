package com.example.loginsqlitekotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsqlitekotlin.model.WarungModel

class ListWarungAdapter(list: ArrayList<WarungModel>?): RecyclerView.Adapter<ListWarungAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var tvNamaWarung: TextView? = null;
        public var tvIdWarung: TextView? = null;
        init {
            tvNamaWarung = itemView.findViewById(R.id.warung_name)
            tvIdWarung = itemView.findViewById(R.id.id_warung)
        }
    }

    private var listWarung: ArrayList<WarungModel>? = null

    init {
        this.listWarung = list
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_warung,
            parent, false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val warungModel: WarungModel = listWarung?.get(position)!!
        holder.tvNamaWarung?.text = warungModel.getNamawarung()
        holder.tvIdWarung?.text = warungModel.getIdwarung().toString()
    }

    override fun getItemCount(): Int {
        return listWarung?.size!!
    }
}