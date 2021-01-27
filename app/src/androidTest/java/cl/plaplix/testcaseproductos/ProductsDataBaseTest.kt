package cl.plaplix.testcaseproductos

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import cl.plaplix.testcaseproductos.model.db.ProductDao
import cl.plaplix.testcaseproductos.model.db.ProductDataBase
import cl.plaplix.testcaseproductos.model.db.ProductEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductsDataBaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productDao: ProductDao
    private lateinit var dataBase: ProductDataBase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(context, ProductDataBase::class.java).build()
        productDao = dataBase.productDB()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun insertProducts_empty() = runBlocking {
        // Given
        val productList = listOf<ProductEntity>()

        // When
        productDao.insertAllProducts(productList)

        // Then
        productDao.getAllProduct().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertProducts_1product() = runBlocking {
        // Given
        val productList = listOf(
            ProductEntity(
                0,
                "Soy un producto",
                99999999,
                "Soy una imagen"
            )
        )

        // When
        productDao.insertAllProducts(productList)

        //Then
        productDao.getAllProduct().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat("cl.plaplix.testcaseproductos").isEqualTo(appContext.packageName)
    }
}