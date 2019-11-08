package com.edgar.mercadosearch.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.edgar.mercadosearch.R
import com.edgar.mercadosearch.data.api.Picture
import com.edgar.mercadosearch.databinding.ActivityDetailBinding
import dagger.android.AndroidInjection
import javax.inject.Inject
import androidx.appcompat.app.AlertDialog

/**
 * @author Edgar Glellel
 * Operation to Display of item details
 */
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : DetailActivityViewModelFactory
    lateinit var viewModel        : DetailActivityViewModel
    private lateinit var binding  : ActivityDetailBinding
    private lateinit var mAdapter : ImageGalleryHeaderAdapter

    private var dotsCount : Int = 0

    private var dots: ArrayList<ImageView> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@DetailActivity)
        super.onCreate(savedInstanceState)
        initDataBinding()
    }

    /**
     * Function of Initialization to Data Binding
     */
    private fun initDataBinding() {
        viewModel = ViewModelProvider(
            this@DetailActivity,
            viewModelFactory
        ).get(DetailActivityViewModel::class.java)

        binding = DataBindingUtil.setContentView(
            this@DetailActivity,
            R.layout.activity_detail)

        binding.lifecycleOwner  = this@DetailActivity
        binding.viewModel       = viewModel

        initUI()
    }

    /**
     * Function of Initialization to user interface
     * for more information to image gallery @see [ImageGalleryHeaderAdapter]
     */
    private fun initUI() {

        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true)

        //Load data from API
        viewModel.loadItemDetail(ids = intent.extras.getString("id")).observe(this@DetailActivity, Observer {
            if(it!=null) {
                binding.title.text = it.title
                binding.price.text = "$"+it.price
                //configure the display of free shipping Label
                if(it.shipping!!.free_shipping==true)
                    binding.shipping.visibility = View.VISIBLE
                else
                    binding.shipping.visibility = View.GONE
                //Load and display image gallery
                if (it.pictures!!.isNotEmpty()) {

                    mAdapter = ImageGalleryHeaderAdapter(loadHeaderImages(it.pictures as List<Picture>))
                    setUiPageViewController()
                    binding.gallery.adapter = mAdapter
                    binding.gallery.currentItem = 0

                    binding.gallery.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                        override fun onPageScrollStateChanged(state: Int) {}
                        override fun onPageScrolled(
                            position: Int,
                            positionOffset: Float,
                            positionOffsetPixels: Int
                        ) {}
                        override fun onPageSelected(position: Int) {
                            for (i in 0 until dotsCount) {
                                dots[i].setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this@DetailActivity,
                                        R.drawable.non_selected_item_dot))
                            }
                            dots[position].setImageDrawable(
                                ContextCompat.getDrawable(
                                    this@DetailActivity,
                                    R.drawable.selected_item_dot))
                        }
                    })
                }
            }
        })
        viewModel.errorApi.observe(this@DetailActivity, Observer {
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@DetailActivity)
            // Set the alert dialog title
            builder.setTitle(getString(R.string.error_title))
            // Display a message on alert dialog
            builder.setMessage(getString(R.string.error_api))
            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Ok"){_, _ ->
                //close screen
                finish()
            }
            builder.setCancelable(false)
            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()
            // Display the alert dialog on app interface
            dialog.show()
        })
    }

    /**
     * Generate the list of images for the gallery
     * @return ArrayList<String> with of URL images
     */
    private fun loadHeaderImages(istPhos: List<Picture>): ArrayList<String> {
        // Initialize a new instance of
        val imageUrlList = ArrayList<String>()
        for (image in istPhos) {
            if(image.url!=null) {
                // Add URL Image
                imageUrlList.add(image.url)
            }
        }
        return imageUrlList
    }

    /**
     * Setup dot to image gallery.
     */
    private fun setUiPageViewController() {

        dotsCount = mAdapter.count

        for (i in 0 until dotsCount) {
            dots.add(ImageView(this@DetailActivity))
            dots[i].setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    R.drawable.non_selected_item_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(6, 0, 6, 0)
            binding.viewPagerCountDots.addView(dots[i], params)
        }
        if (dots.size > 0) {
            dots[0].setImageDrawable(ContextCompat.getDrawable(
                this@DetailActivity,
                R.drawable.selected_item_dot))
        }
    }
    companion object {
        //Tag for debug
        private const val TAG = "DetailActivity"
    }
}
