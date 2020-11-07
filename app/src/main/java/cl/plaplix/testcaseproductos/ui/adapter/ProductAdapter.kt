package cl.plaplix.testcaseproductos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.plaplix.testcaseproductos.R
import cl.plaplix.testcaseproductos.model.db.ProductEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(private var myDataset: MutableList<ProductEntity>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    val phoneSelected = MutableLiveData<ProductEntity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = myDataset[position]
        Glide.with(holder.itemView.context)
            .load(product.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.productImg)
        holder.productName.text = product.name
        holder.productPrice.text = product.price.toString()
        holder.itemView.setOnClickListener {
            phoneSelected.value = myDataset[position]
        }
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName = itemView.product_name_item!!
        var productPrice = itemView.product_price_item!!
        var productImg = itemView.product_img_item!!
    }

    fun updateProductItems(it: List<ProductEntity>) {
        myDataset.clear()
        myDataset.addAll(it)
        notifyDataSetChanged()
    }

}