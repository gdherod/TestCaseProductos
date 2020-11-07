package cl.plaplix.testcaseproductos.model.remote

import cl.plaplix.testcaseproductos.model.remote.pojo.Detail
import cl.plaplix.testcaseproductos.model.remote.pojo.Product
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeAPI {
    @GET("products/")
    suspend fun productsList(): Response<List<Product>>

    @GET("details/{id}")
    suspend fun detailProduct(@Path("id") id: Int): Response<Detail>
}

class RetrofitClient {
    companion object {

        private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun retrofitInstance(): FakeAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(FakeAPI::class.java)
        }
    }
}

