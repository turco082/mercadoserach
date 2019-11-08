package com.edgar.mercadosearch.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edgar.mercadosearch.repository.MercadoSearchRepository

/**
 * @author Edgar Glellel
 * View Model Factory for @see [HomeActivity]
 */
class HomeActivityViewModelFactory constructor(val repository: MercadoSearchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeActivityViewModel::class.java)) {
            return HomeActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}