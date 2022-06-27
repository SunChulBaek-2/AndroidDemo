package kr.pe.ssun.androiddemo

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.disk.DiskCache
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Coil disk cache 설정
        Coil.setImageLoader(
            ImageLoader.Builder(applicationContext)
                .diskCache {
                    DiskCache.Builder()
                        .directory(applicationContext.cacheDir.resolve("image_cache"))
                        .build()
                }
                .build()
        )

        // Timber 설정
        Timber.plant(Timber.DebugTree())
    }
}