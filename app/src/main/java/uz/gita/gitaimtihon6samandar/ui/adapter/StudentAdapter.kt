package uz.gita.gitaimtihon6samandar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.gitaimtihon6samandar.R

import uz.gita.gitaimtihon6samandar.data.room.entities.StudentEntity


class StudentAdapter:ListAdapter<StudentEntity, StudentAdapter.Holder> (itemCallback) {

    private var listenerRemove:((StudentEntity)->Unit)?=null

    fun removeToDo(l:(StudentEntity)->Unit){
        listenerRemove=l
    }
    private var itemDelteListener:((StudentEntity)->Unit)?=null

    fun itemDelete(l:(StudentEntity)->Unit){
       itemDelteListener=l
    }

    private var itemEditListener:((StudentEntity)->Unit)?=null

    fun itemEdit(l:(StudentEntity)->Unit){
       itemEditListener=l
    }

    private var itemPressListener:((StudentEntity)->Unit)?=null

    fun itemPress(l:(StudentEntity)->Unit){
       itemPressListener=l
    }


    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView =view.findViewById(R.id.name_student)
        val surname: TextView =view.findViewById(R.id.surname_student)
        val delete: TextView =view.findViewById(R.id.delete)
        val edit: TextView =view.findViewById(R.id.edit)
        fun bind() {
            val item=getItem(adapterPosition)
            title.text=item.name
            surname.text=item.surname

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
            LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
    fun removeItem(adapterPosition: Int) {
        listenerRemove?.invoke(getItem(adapterPosition))
    }

    object itemCallback : DiffUtil.ItemCallback<StudentEntity>() {
        override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name &&  oldItem.surname == newItem.surname && oldItem.tartibi == newItem.tartibi && oldItem.groupId == newItem.groupId

        }

    }


}





