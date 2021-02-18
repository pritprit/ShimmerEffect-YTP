package com.eunidev.shimmereffectytp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout

class RVAdapter(private val context: Context, private val list: ArrayList<ContentModel>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private var isShimmer = true

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.textViewTitle_Cardview)
        val tvDescription = itemView.findViewById<TextView>(R.id.textViewDescription_Cardview)
        val shimmer = itemView.findViewById<ShimmerFrameLayout>(R.id.shimmerFrameLayout_CardView)

        fun bind(currentItem: ContentModel) {
            tvTitle.text = currentItem.title
            tvDescription.text = currentItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        if (isShimmer) {
            holder.shimmer.startShimmer()
        } else {
            holder.shimmer.stopShimmer()
            holder.shimmer.setShimmer(null)
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setShimmer(shimmer: Boolean) {
        this.isShimmer = shimmer
    }
}