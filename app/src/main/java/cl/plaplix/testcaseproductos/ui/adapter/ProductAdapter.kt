package cl.plaplix.testcaseproductos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.plaplix.testcaseproductos.databinding.ProductItemBinding
import cl.plaplix.testcaseproductos.model.db.ProductEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ProductAdapter(private var myDataset: MutableList<ProductEntity>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    val phoneSelected = MutableLiveData<ProductEntity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))

        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = myDataset[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            phoneSelected.value = myDataset[position]
        }
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    class ProductHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity) {
            binding.productNameItem.text = product.name
            binding.productPriceItem.text = product.price.toString()
            Glide.with(binding.productImgItem.context)
                .load(product.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.productImgItem)
        }
    }

    fun updateProductItems(it: List<ProductEntity>) {
        myDataset.clear()
        myDataset.addAll(it)
        notifyDataSetChanged()
    }

}