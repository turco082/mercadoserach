package com.edgar.mercadosearch

import android.app.Activity
import android.app.Application
import com.edgar.mercadosearch.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author Edgar Glellel
 */
class MercadoSearchApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.
            builder().create(this).inject(this)
    }
    companion object {

        private val TAG = "MercadoSearchApp"
    }
}