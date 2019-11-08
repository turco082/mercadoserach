package com.edgar.mercadosearch.ui.home

import com.edgar.mercadosearch.repository.MercadoSearchRepository
import dagger.Module
import dagger.Provides

/**
 * @author Edgar Glellel
 * Provide ViewModelFactory
 */
@Module
class HomeActivityModule {

    @Provides
    internal fun provideViewModelFactory(repository: MercadoSearchRepository) : HomeActivityViewModelFactory
            = HomeActivityViewModelFactory(repository)
}