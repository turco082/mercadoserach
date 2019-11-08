package com.edgar.mercadosearch.di

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import dagger.Module
/**
 * @author Edgar Glellel
 */
@Module(includes = [NetworkModule::class, RepositoryModule::class])
class AppModule {
    fun hiddenKeyboard(activity: Activity){
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view = activity.getCurrentFocus()
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}