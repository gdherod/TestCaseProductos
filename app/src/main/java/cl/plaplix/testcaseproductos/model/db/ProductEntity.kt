package cl.plaplix.testcaseproductos.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String
)