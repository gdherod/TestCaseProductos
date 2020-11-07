package cl.plaplix.testcaseproductos.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(productList: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(detail: DetailEntity)

    @Query("SELECT * FROM product_table")
    fun getAllProduct(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM detail_table WHERE id = :product_id")
    fun getProductDetail(product_id: Int): LiveData<DetailEntity>
}