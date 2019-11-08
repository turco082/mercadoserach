package com.edgar.mercadosearch.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edgar.mercadosearch.R
import com.edgar.mercadosearch.data.api.Result

/**
 * @author Edgar Glellel
 * Adapter class responsible for handling search items @see [HomeActivity]
 */

class HomeAdapter(
    private val results: ArrayList<Result>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<HomeAdapter.HomeResultsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeResultsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return HomeResultsViewHolder(view)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: HomeResultsViewHolder, position: Int) {
        val result = results[position]
        holder.updateView(result)
        //assign the click event
        holder.itemView.setOnClickListener {
            clickListener.onClick(result = result)
        }
    }

    /**
     * Class ViewHolder @see [HomeAdapter]
     */
    class HomeResultsViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {

        private var title: TextView         = itemVIew.findViewById(R.id.title)
        private var price: TextView         = itemVIew.findViewById(R.id.price)
        private var free_shipping: TextView = itemVIew.findViewById(R.id.free_shipping)
        private var thumbnail: ImageView    = itemVIew.findViewById(R.id.thumbnail)
        /**
         * Function responsible for managing the assignment of content to the cell
         */
        fun updateView(result: Result) {
            title.text   = result.title
            price.text   = "$"+result.price
            if(result.shipping!!.free_shipping==true)
                free_shipping.visibility =  View.VISIBLE
            else
                free_shipping.visibility =  View.GONE
            Glide.with(itemView.context)
                .asBitmap()
                .load(result.thumbnail)
                .into(thumbnail)
        }
    }

    companion object {
        private const val TAG = "HomeAdapter"
    }

    /**
     * Item Click Listener @see [HomeActivity]
     */
    interface ItemClickListener {
        fun onClick(result: Result)
    }
}

