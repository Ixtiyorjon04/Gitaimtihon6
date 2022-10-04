package uz.gita.gitaimtihon6samandar.ui.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class MyHelper2(private val adapter: StudentAdapter) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP,ItemTouchHelper.DOWN) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

//        adapter.onMove(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        if (isLongPressDragEnabled) {
//            adapter.removeItem(viewHolder.adapterPosition)
//        }
    }

}