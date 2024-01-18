package com.voinov.giflist

import android.app.Application
import com.voinov.giflist.core.gifListModule
import com.voinov.giflist.core.gifSearchModule
import com.voinov.giflist.core.networkModule
import com.voinov.giflist.core.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class GifApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@GifApp)
            // Load modules
            modules(networkModule, viewModelModule, gifListModule, gifSearchModule)
        }
    }
}