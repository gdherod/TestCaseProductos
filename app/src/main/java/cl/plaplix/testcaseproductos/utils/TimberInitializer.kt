package cl.plaplix.testcaseproductos.utils

import cl.plaplix.testcaseproductos.BuildConfig
import timber.log.Timber

class TimberInitializer {

    fun initTimber() {
        when {
            BuildConfig.DEBUG -> Timber.plant(Timber.DebugTree())
        }
    }
}