package cl.plaplix.testcaseproductos.model.remote.pojo

data class Detail(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)