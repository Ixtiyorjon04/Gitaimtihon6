package uz.gita.gitaimtihon6samandar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.gitaimtihon6samandar.R

import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity


class MainAdapter:ListAdapter<GroupEntity, MainAdapter.Holder> (itemCallback) {

    private var listenerMove:((GroupEntity,GroupEntity)->Unit)?=null

    fun move(l:(GroupEntity,GroupEntity)->Unit){
        listenerMove=l
    }
    private var itemDelteListener:((GroupEntity)->Unit)?=null

    fun itemDelete(l:(GroupEntity)->Unit){
       itemDelteListener=l
    }

    private var itemEditListener:((GroupEntity)->Unit)?=null

    fun itemEdit(l:(GroupEntity)->Unit){
       itemEditListener=l
    }

    private var itemPressListener:((GroupEntity)->Unit)?=null

    fun itemPress(l:(GroupEntity)->Unit){
       itemPressListener=l
    }


    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView =view.findViewById(R.id.title_group)
        val delete: TextView =view.findViewById(R.id.delete)
        val edit: TextView =view.findViewById(R.id.edit)

        fun bind() {
            val item=getItem(adapterPosition)
            title.text=item.name


        }
        init {
            delete.setOnClickListener{
                itemDelteListener?.invoke(getItem(adapterPosition))
            }
           edit.setOnClickListener{
                itemEditListener?.invoke(getItem(adapterPosition))
            }
//
           itemView.setOnClickListener {
                itemPressListener?.invoke(getItem(adapterPosition))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    fun onMove(from: Int, to: Int) {
       listenerMove?.invoke(GroupEntity(getItem(to).id,getItem(to).name,getItem(from).tartibi,getItem(to).userId),GroupEntity(getItem(from).id,getItem(from).name,getItem(to).tartibi,getItem(from).userId),)
    }

    object itemCallback : DiffUtil.ItemCallback<GroupEntity>() {
        override fun areItemsTheSame(oldItem: GroupEntity, newItem: GroupEntity): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: GroupEntity, newItem: GroupEntity): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.tartibi == newItem.tartibi && oldItem.userId == newItem.userId

        }

    }


}





