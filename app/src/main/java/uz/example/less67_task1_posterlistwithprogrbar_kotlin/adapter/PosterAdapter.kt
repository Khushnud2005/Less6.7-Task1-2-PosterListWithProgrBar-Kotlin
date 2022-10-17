package uz.example.less67_task1_posterlistwithprogrbar_kotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.MainActivity
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.R
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.activity.EditActivity
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.model.Poster
import java.lang.String
import kotlin.Int

class PosterAdapter(var activity: MainActivity, var items: ArrayList<Poster>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val poster: Poster = items[position]
        if (holder is PosterViewHolder) {
            val swpipe: SwipeLayout = holder.sl_swipe
            val left:LinearLayout = holder.linear_left
            val right:LinearLayout = holder.linear_right
            swpipe.showMode = SwipeLayout.ShowMode.PullOut
            swpipe.addDrag(SwipeLayout.DragEdge.Left,left)
            swpipe.addDrag(SwipeLayout.DragEdge.Right,right)
            val delete: TextView = holder.tv_delete
            val edit: TextView = holder.tv_edit

            val tv_title = holder.tv_title
            val tv_body = holder.tv_body
            tv_title.setText(poster.title.toUpperCase())
            tv_body.setText(poster.body)

            delete.setOnClickListener {
                activity.dialogPoster(poster)
            }
            edit.setOnClickListener {
                val intent = Intent(activity.baseContext, EditActivity::class.java)
                intent.putExtra("id", String.valueOf(poster.id))
                intent.putExtra("user_id", String.valueOf(poster.userId))
                intent.putExtra("title", poster.title)
                intent.putExtra("body", poster.body)
                activity.startActivity(intent)
            }
        }
    }

    inner class PosterViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var sl_swipe: SwipeLayout
        var tv_title: TextView
        var tv_body: TextView
        var tv_delete: TextView
        var tv_edit: TextView
        var linear_left: LinearLayout
        var linear_right: LinearLayout

        init {

            sl_swipe = view.findViewById(R.id.sl_swipe)
            linear_left = view.findViewById(R.id.ll_linear_left)
            linear_right = view.findViewById(R.id.ll_linear_right)
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
            tv_delete = view.findViewById(R.id.tv_delete)
            tv_edit = view.findViewById(R.id.tv_edit)
        }
    }
}