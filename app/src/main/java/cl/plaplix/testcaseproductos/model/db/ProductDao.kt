package cl.plaplix.testcaseproductos.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    fun getAllProduct(): LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(productList: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(detailList: List<DetailEntity>)

    @Query("SELECT * FROM product_table WHERE id = :product_id")
    fun getProductDetail(product_id: Int): LiveData<DetailEntity>
}