package com.example.loginsqlitekotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsqlitekotlin.model.RoleModel

class ListRoleAdapter(private val roleList: ArrayList<RoleModel>) : RecyclerView.Adapter<ListRoleAdapter.ListViewHolder>() {

    private lateinit var roleController: RoleController

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView? = null
        var tvRole: TextView? = null
        var tvStatus: TextView? = null
        var btEdit: ImageButton = itemView.findViewById(R.id.bt_edit)
        var btDelete: ImageButton = itemView.findViewById(R.id.bt_delete)
        var roleController: RoleController

        init {
            tvId = itemView.findViewById(R.id.tv_id)
            tvRole = itemView.findViewById(R.id.tv_role)
            tvStatus = itemView.findViewById(R.id.tv_status)
            btEdit = itemView.findViewById(R.id.bt_edit)
            btDelete = itemView.findViewById(R.id.bt_delete)
            roleController = RoleController(itemView.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_role,
            parent, false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val roleModel: RoleModel = roleList[position]
        holder.tvId?.text = roleModel.getIdRole().toString()
        holder.tvRole?.text = roleModel.getRole()
        holder.tvStatus?.text = roleModel.getStatus()

        holder.btEdit.setOnClickListener {
            // Handle the click for the edit button
            // You can use the position parameter to get the item associated with this view
            // For example, roleList[position] would give you the data for the clicked item
            // Perform the edit action here
        }

        holder.btDelete.setOnClickListener {
            val id = roleModel.getIdRole()
            val delete = holder.roleController.deleteRole(id)
            if (delete != 1L) {
                Toast.makeText(holder.itemView.context, "Role deleted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(holder.itemView.context, "Failed to delete role", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return roleList.size
    }
}
