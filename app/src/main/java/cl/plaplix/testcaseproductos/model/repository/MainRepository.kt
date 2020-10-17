package cl.plaplix.testcaseproductos.model.repository

import android.content.Context
import android.util.Log
import cl.plaplix.testcaseproductos.model.db.ProductDataBase
import cl.plaplix.testcaseproductos.model.db.ProductEntity
import cl.plaplix.testcaseproductos.model.remote.RetrofitClient
import cl.plaplix.testcaseproductos.model.remote.pojo.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainRepository(context: Context) {

    val tag = "MainRepository"

    private var productDataBase = ProductDataBase.getDataBase(context)
    var productList = productDataBase.productDB().getAllProduct()


    fun loadApiProductData() = CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitClient.retrofitInstance().productsList()

        when {
            response.isSuccessful -> insertProductDB(productApiToEntity(response.body()!!))
            else -> Log.d(tag, response.errorBody().toString())
        }
    }

    private fun productApiToEntity(productList: List<Product>): List<ProductEntity> {
        return productList.map { product ->
            ProductEntity(
                product.id,
                product.name,
                product.price,
                product.image
            )
        }
    }

    private fun insertProductDB(productList: List<ProductEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            productDataBase.productDB().insertAllProducts(productList)
        }
    }

}