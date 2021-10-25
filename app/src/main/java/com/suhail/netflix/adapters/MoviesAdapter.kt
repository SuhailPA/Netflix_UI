package com.suhail.netflix.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suhail.netflix.R
import com.suhail.netflix.dataClass.Result

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    inner class MoviesViewHolder(view: View):RecyclerView.ViewHolder(view){
        val movieThumb: ImageView =view.findViewById<ImageView>(R.id.itemThumb)

    }

    private val differCallBack=object :DiffUtil.ItemCallback<Result>(){

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id==newItem.id

        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
          return oldItem==newItem

        }

    }

    val differ=AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout_item,parent,false)
      return MoviesViewHolder(view)


    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val itemDet=differ.currentList[position]
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500${itemDet.posterPath}").into(holder.movieThumb)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let{it(itemDet)
        }


        }

    }

    private var onItemClickListener:((Result)->Unit)?=null

    fun setOnClickListner(listener:(Result)->Unit){
        onItemClickListener=listener
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}