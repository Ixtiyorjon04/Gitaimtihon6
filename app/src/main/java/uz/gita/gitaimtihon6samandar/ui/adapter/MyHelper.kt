package uz.gita.gitaimtihon6samandar.ui.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class MyHelper(private val adapter: MainAdapter) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP,ItemTouchHelper.DOWN) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

        adapter.onMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        if (isLongPressDragEnabled) {
//            adapter.removeItem(viewHolder.adapterPosition)
//        }
    }

}