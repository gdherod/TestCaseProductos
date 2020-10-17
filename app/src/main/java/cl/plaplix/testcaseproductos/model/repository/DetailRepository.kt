package cl.plaplix.testcaseproductos.model.repository

import android.content.Context

class DetailRepository(context: Context) {

    //TODO: Crear Detalle

    /*val tag = "DetailRepository"

    private var productDataBase = ProductDataBase.getDataBase(context)
    var detailList = productDataBase.productDB().getAllDetail()

    fun loadApiDetailData() = CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitClient.retrofitInstance().detailProduct()

        when {
            response.isSuccessful -> response.body()?.map {
                Log.d(
                    tag,
                    "${it.id} - ${it.name} - ${it.price} - ${it.image} - ${it.description} - ${it.lastPrice} - ${it.credit}"
                )
            }
            else -> Log.d(tag, response.errorBody().toString())
        }
    }

    private fun detailApiToEntity(detailList: List<Detail>): List<DetailEntity> {
        return detailList.map { detail ->
            DetailEntity(
                detail.id,
                detail.name,
                detail.price,
                detail.image,
                detail.description,
                detail.lastPrice,
                detail.credit
            )
        }
    }

    private fun insertDetailDB(detailList: List<DetailEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            productDataBase.productDB().insertAllDetails(detailList)
        }
    }*/
}