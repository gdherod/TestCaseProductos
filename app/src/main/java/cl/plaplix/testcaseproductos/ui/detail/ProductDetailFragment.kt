package cl.plaplix.testcaseproductos.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.plaplix.testcaseproductos.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_product_detail.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductDetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by activityViewModels()
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

        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.result.observe(viewLifecycleOwner, {
            if (it != null) {
                product_detail_name.text = it.name
                product_detail_price.text = it.price.toString()
                Glide.with(view.context)
                    .load(it.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(product_detail_img)
                product_detail_description.text = it.description
                product_detail_last_price.text = it.lastPrice.toString()
                product_detail_credit.text = it.credit.toString()
            }
        })
    }
}