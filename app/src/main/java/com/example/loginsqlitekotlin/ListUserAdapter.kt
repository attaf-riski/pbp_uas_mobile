package com.example.loginsqlitekotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsqlitekotlin.model.PenggunaModel

class ListUserAdapter(list: ArrayList<PenggunaModel>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var penggunaController: PenggunaController

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView? = null
        var tvId: TextView? = null
        var btEdit: ImageButton = itemView.findViewById(R.id.bt_edit)
        var btDelete: ImageButton = itemView.findViewById(R.id.bt_delete)
        var penggunaController: PenggunaController

        init {
            tvId = itemView.findViewById(R.id.tv_id)
            tvUsername = itemView.findViewById(R.id.tv_username)
            btEdit = itemView.findViewById(R.id.bt_edit)
            btDelete = itemView.findViewById(R.id.bt_delete)
            penggunaController = PenggunaController(itemView.context)
        }
    }

    private var UserList: ArrayList<PenggunaModel>? = null

    init {
        this.UserList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pengguna,
            parent, false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val penggunaModel: PenggunaModel = UserList?.get(position)!!
        holder.tvId?.text = penggunaModel.getIdPengguna().toString()
        holder.tvUsername?.text = penggunaModel.getUsername()

        holder.btEdit.setOnClickListener {
            val id = penggunaModel.getIdPengguna()
            val editIntent = Intent(holder.itemView.context, EditUser::class.java)
            editIntent.putExtra("USER_ID", id)
            holder.itemView.context.startActivity(editIntent)
        }

        holder.btDelete.setOnClickListener {
            val id = penggunaModel.getIdPengguna()
            val delete = holder.penggunaController.deleteUser(id)
            if (delete != 1L) {
                Toast.makeText(holder.itemView.context, "User deleted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(holder.itemView.context, "Failed to delete user", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btEdit: ImageButton = itemView.findViewById(R.id.bt_edit)
        val btDelete: ImageButton = itemView.findViewById(R.id.bt_delete)
    }

    override fun getItemCount(): Int {
        return UserList?.size ?: 0
    }
}
