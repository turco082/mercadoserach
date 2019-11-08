package com.edgar.mercadosearch.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.edgar.mercadosearch.R
import java.util.ArrayList

/**
 * Adapter for the handler image gallery
 * usage in @see [DetailActivity]
 */
class ImageGalleryHeaderAdapter(
    private val items: ArrayList<String>
) : PagerAdapter() {


    /**
     * @return Count items (Int Value)
     */
    override fun getCount(): Int {
        return items.size
    }

    /**
     * @return View
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(container.context).inflate(R.layout.item_image, container, false)
        val item = items[position]

        val imageItem: ImageView = itemView.findViewById(R.id.imageItem)
        Glide.with(container.context).load(item).into(imageItem)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}