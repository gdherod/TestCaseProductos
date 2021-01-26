package cl.plaplix.testcaseproductos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cl.plaplix.testcaseproductos.R
import cl.plaplix.testcaseproductos.databinding.FragmentProductListBinding
import cl.plaplix.testcaseproductos.model.db.ProductEntity
import cl.plaplix.testcaseproductos.ui.adapter.ProductAdapter
import cl.plaplix.testcaseproductos.ui.detail.DetailViewModel
import cl.plaplix.testcaseproductos.ui.detail.ProductDetailFragment

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val detailViewModel: DetailViewModel by activityViewModels()
    private var productList = ArrayList<ProductEntity>()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductAdapter(productList)
        binding.productList.layoutManager = LinearLayoutManager(context)
        binding.productList.adapter = productAdapter
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