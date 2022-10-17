package uz.example.less67_task1_posterlistwithprogrbar_kotlin.adapter

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemTouchHelper(var dragDirs:Int, var swipeDirs:Int, var listener: RecyclerItemTouchHelperListener ):ItemTouchHelper.SimpleCallback(dragDirs,swipeDirs) {


    override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
       return false
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            //val foregroundView: View = (viewHolder as PosterAdapter.PosterViewHolder).view_foreground
            //getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDrawOver(c: Canvas,recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder?,dX: Float,dY: Float,actionState: Int,isCurrentlyActive: Boolean) {
        // foregroundView: View = (viewHolder as PosterAdapter.PosterViewHolder).view_foreground
        //getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundView,dX / 2,dY,actionState,isCurrentlyActive)
    }

    override fun onChildDraw(c: Canvas,recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,dX: Float,dY: Float,actionState: Int,isCurrentlyActive: Boolean) {
        //val foregroundView: View = (viewHolder as PosterAdapter.PosterViewHolder).view_foreground
        //getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX / 2,dY,actionState,isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onSwiped(viewHolder,direction,viewHolder.adapterPosition)
    }
    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }
    interface RecyclerItemTouchHelperListener {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int)
    }
}