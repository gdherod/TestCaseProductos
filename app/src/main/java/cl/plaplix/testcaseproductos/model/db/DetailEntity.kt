package cl.plaplix.testcaseproductos.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_table")
data class DetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)
