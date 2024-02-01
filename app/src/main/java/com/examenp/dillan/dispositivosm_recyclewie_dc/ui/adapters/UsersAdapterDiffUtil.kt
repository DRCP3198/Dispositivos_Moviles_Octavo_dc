package com.examenp.dillan.dispositivosm_recyclewie_dc.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.examenp.dillan.dispositivosm_recyclewie_dc.R
import com.examenp.dillan.dispositivosm_recyclewie_dc.databinding.UsersItemsBinding
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullInfoAnimeLG

class UsersAdapterDiffUtil(
    private val onDeleteItem: (Int) -> Unit,
    private val onSelectItem: (FullInfoAnimeLG) -> Unit
) : ListAdapter<FullInfoAnimeLG, UsersAdapterDiffUtil.UsersVH>(DiffUtilUserCallBack) {


    class UsersVH(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: UsersItemsBinding = UsersItemsBinding.bind(view)

        fun render(
            item: FullInfoAnimeLG,
            OnDeleteItem: (Int) -> Unit,
            onSelectItem: (FullInfoAnimeLG) -> Unit
        ) {
            binding.UserName.text = item.name
            binding.UserDesc.text = item.sypnasis
            binding.imgUser.load(item.big_image)
            binding.btnBorrar.setOnClickListener {
                OnDeleteItem(adapterPosition)
            }
            binding.imgUser.setOnClickListener {
                onSelectItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        val inflater = LayoutInflater.from(parent.context)
        return UsersVH(inflater.inflate(R.layout.users_items, parent, false))
    }
    //Se modifico
    override fun onBindViewHolder(holder: UsersVH, position: Int) {
        holder.render(getItem(position), onDeleteItem, onSelectItem)
    }
}

//Me crea automaticamente una lista nueva de usuarios
private object DiffUtilUserCallBack : DiffUtil.ItemCallback<FullInfoAnimeLG>() {

    //comparo algo que sea unico, si no tengo el id, el nombre tambien puedo comparar
    override fun areItemsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {
        //comparo todo el contenido
        return (oldItem == newItem)
    }


}
