package cl.plaplix.testcaseproductos.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.plaplix.testcaseproductos.databinding.FragmentProductDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val detailViewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(layoutInflater)
        return binding.root
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
                binding.productDetailName.text = it.name
                binding.productDetailPrice.text = it.price.toString()
                Glide.with(binding.productDetailImg.context)
                    .load(it.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.productDetailImg)
                binding.productDetailDescription.text = it.description
                binding.productDetailLastPrice.text = it.lastPrice.toString()
                binding.productDetailCredit.text = it.credit.toString()

                fun email() {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@plaplix.cl"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta ${it.name}, id ${it.id}")
                    intent.putExtra(
                        Intent.EXTRA_TEXT, "Hola" +
                                "Vi el producto ${it.name} y me gustaría que me contactaran a este correo o al " +
                                "siguiente número _________ " +
                                "Quedo atento."
                    )
                    intent.type = "message/rfc822"
                    startActivity(Intent.createChooser(intent, "Seleccione un cliente de correo"))
                }

                binding.floatingActionButton.setOnClickListener { view ->
                    Snackbar.make(view, "Buy!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                    email()
                }
            }
        })
    }
}