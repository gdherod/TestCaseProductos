package cl.plaplix.testcaseproductos.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.plaplix.testcaseproductos.model.db.DetailEntity
import cl.plaplix.testcaseproductos.model.db.ProductEntity
import cl.plaplix.testcaseproductos.model.repository.DetailRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private var detailRepository: DetailRepository = DetailRepository(application)

    lateinit var result: LiveData<DetailEntity>

    init {
        detailRepository = DetailRepository(application)
    }

    private val unselected = MutableLiveData<ProductEntity>()

    fun productSelected(productEntity: ProductEntity) {
        unselected.value = productEntity
        detailRepository.loadApiDetailData(productEntity.id)
        result = detailRepository.getProductDetail(productEntity.id)
    }
}