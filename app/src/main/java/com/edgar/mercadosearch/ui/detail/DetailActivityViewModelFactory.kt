package com.edgar.mercadosearch.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edgar.mercadosearch.repository.MercadoSearchRepository

/**
 * @author Edgar Glellel
 * View Model Factory for @see [DetailActivity]
 */
class DetailActivityViewModelFactory constructor(val repository: MercadoSearchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailActivityViewModel::class.java)) {
            return DetailActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}