package cl.plaplix.testcaseproductos.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cl.plaplix.testcaseproductos.model.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var mainRepository: MainRepository = MainRepository(application)

    var productList = mainRepository.productList

    init {
        mainRepository = MainRepository(application)
        mainRepository.loadApiProductData()
    }
}