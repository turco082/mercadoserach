package com.edgar.mercadosearch.ui.home

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edgar.mercadosearch.R
import com.edgar.mercadosearch.data.api.Result
import com.edgar.mercadosearch.databinding.ActivityHomeBinding
import com.edgar.mercadosearch.ui.detail.DetailActivity
import dagger.android.AndroidInjection
import javax.inject.Inject
import com.miguelcatalan.materialsearchview.MaterialSearchView

/**
 * @author Edgar Glellel
 */
class HomeActivity : AppCompatActivity() , HomeAdapter.ItemClickListener{

    @Inject
    lateinit var viewModelFactory : HomeActivityViewModelFactory
    lateinit var viewModel        : HomeActivityViewModel
    private lateinit var binding  : ActivityHomeBinding

    //region Override
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initDataBinding()
        initRecyclerView()
    }
    //crate menu item for search bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_home_items, menu)
        val item = menu.findItem(R.id.action_search)
        binding.searchView.setMenuItem(item)

        return true
    }
    /**
     * @see [HomeAdapter.ItemClickListener]
     */
    override fun onClick(result: Result) {
        val i = Intent(baseContext, DetailActivity::class.java)
        i.putExtra("id",result.id)
        startActivity(i)
    }
    //endregion
    /**
     * Initialization for Data Binding
     */
    private fun initDataBinding() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner  = this
        binding.viewModel       = viewModel

        initUI()
    }
    /**
     * Initialization UI components
     */
    private fun initUI() {
        //set custom toolbar
        setSupportActionBar(binding.toolbarPrincipal)

        //Check Internet Connection
        if(!isOnline()){
            //Gone title app
            binding.homeSearch.visibility = View.GONE
            //visibiliti no connection error
            binding.noInternet.visibility = View.VISIBLE
        }
        //user query handling when searching
        binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.homeSearch.visibility = View.GONE
                if(!isOnline()){
                    binding.listResults.visibility  = View.GONE
                    binding.noInternet.visibility   = View.VISIBLE
                }else{
                    //hidden the logo and text when do you not have no internet
                    binding.noInternet.visibility   = View.GONE
                    //search for query text
                    viewModel.searchResults(query).observe(this@HomeActivity, Observer {
                        if(it!=null) {
                            if(it.results!=null){
                                //set visibility to list items
                                binding.listResults.visibility  = View.VISIBLE
                                //set adapter to list items
                                binding.listResults.adapter = HomeAdapter(
                                    results       = it.results as ArrayList<Result>,
                                    clickListener = this@HomeActivity
                                )
                            }else{
                                //set text no result for search
                                binding.lbLogo.text = getString(R.string.search_not_result)
                                //set visibility to logo and to label, when not obtain result for search
                                binding.homeSearch.visibility  = View.VISIBLE
                            }
                        }
                    })
                }

                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
        //Add Voice Search
        binding.searchView.setVoiceSearch(true)
        //handling the title bar when showing or hidden the search bar
        binding.searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                //hidde title bar
                binding.titleBar.visibility = View.GONE
            }

            override fun onSearchViewClosed() {
                //hidde title bar
                binding.titleBar.visibility = View.VISIBLE
            }
        })
        //suscribe to error api or internet
        viewModel.errorApi.observe(this@HomeActivity, Observer {
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@HomeActivity)
            // Set the alert dialog title
            builder.setTitle(getString(R.string.error_title))
            // Display a message on alert dialog
            builder.setMessage(getString(R.string.error_api))
            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Ok"){_, _ ->

            }
            builder.setCancelable(false)
            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()
            // Display the alert dialog on app interface
            dialog.show()
        })
    }

    /**
     * function that returns us if we have internet connection
     * @return boolean
     */
    fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
    /**
     * Initialization for recycled view
     */
    private fun initRecyclerView() {
        //Use a layout manager to position your items to look like a standard ListView//
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this@HomeActivity)
        binding.listResults.layoutManager = layoutManager

    }
    companion object {
        private const val TAG = "HomeActivity"
    }
}
