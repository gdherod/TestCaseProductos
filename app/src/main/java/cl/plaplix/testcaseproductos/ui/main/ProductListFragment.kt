package cl.plaplix.testcaseproductos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cl.plaplix.testcaseproductos.R
import cl.plaplix.testcaseproductos.model.db.ProductEntity
import cl.plaplix.testcaseproductos.ui.adapter.ProductAdapter
import cl.plaplix.testcaseproductos.ui.detail.DetailViewModel
import cl.plaplix.testcaseproductos.ui.detail.ProductDetailFragment
import kotlinx.android.synthetic.main.fragment_product_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductListFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val detailViewModel: DetailViewModel by activityViewModels()
    private var productList = ArrayList<ProductEntity>()
    private lateinit var productAdapter: ProductAdapter
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductAdapter(productList)
        product_list.layoutManager = LinearLayoutManager(context)
        product_list.adapter = productAdapter
        mainViewModel.productList.observe(viewLifecycleOwner, {
            productAdapter.updateProductItems(it)
        })

        productAdapter.phoneSelected.observe(viewLifecycleOwner, {
            detailViewModel.productSelected(it)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.mainContainer,
                    ProductDetailFragment.newInstance("", ""),
                    "Detail Fragment"
                )
                .addToBackStack("List Fragment")
                .commit()
        })
    }

}