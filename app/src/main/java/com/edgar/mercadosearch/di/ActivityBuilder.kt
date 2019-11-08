package com.edgar.mercadosearch.di

import com.edgar.mercadosearch.ui.detail.DetailActivity
import com.edgar.mercadosearch.ui.detail.DetailActivityModule
import com.edgar.mercadosearch.ui.home.HomeActivity
import com.edgar.mercadosearch.ui.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * @author Edgar Glellel
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun contributeDetailActivity(): DetailActivity

}