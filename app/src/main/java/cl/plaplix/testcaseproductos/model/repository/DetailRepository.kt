package cl.plaplix.testcaseproductos.model.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.plaplix.testcaseproductos.model.db.DetailEntity
import cl.plaplix.testcaseproductos.model.db.ProductDataBase
import cl.plaplix.testcaseproductos.model.remote.RetrofitClient
import cl.plaplix.testcaseproductos.model.remote.pojo.Detail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailRepository(context: Context) {

    private val tag = "DetailRepository"

    private var productDataBase = ProductDataBase.getDataBase(context)

    fun loadApiDetailData(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitClient.retrofitInstance().detailProduct(id)

        when {
            response.isSuccessful -> insertDetailDB(detailApiToEntity(response.body()!!))
            else -> Log.d(tag, response.errorBody().toString())
        }
    }

    private fun detailApiToEntity(detailList: Detail): DetailEntity {
        return DetailEntity(
            detailList.id,
            detailList.name,
            detailList.price,
            detailList.image,
            detailList.description,
            detailList.lastPrice,
            detailList.credit
        )
    }

    private fun insertDetailDB(detailList: DetailEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            productDataBase.productDB().insertDetails(detailList)
        }
    }

    fun getProductDetail(product_id: Int): LiveData<DetailEntity> {
        return productDataBase.productDB().getProductDetail(product_id)
    }
}