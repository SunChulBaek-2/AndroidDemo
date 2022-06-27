package kr.pe.ssun.androiddemo.ui.main

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kr.pe.ssun.androiddemo.data.model.ShopItem
import kr.pe.ssun.androiddemo.databinding.ItemShopBinding

class ShopAdapter : ListAdapter<ShopItem, ShopViewHolder>(ShopDiff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ShopViewHolder(
    private val binding: ItemShopBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ShopItem) {
        binding.iv.load(item.image)
        binding.tvTitle.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT)
        binding.root.setOnClickListener {
            Toast.makeText(binding.root.context, item.link, Toast.LENGTH_SHORT).show()
        }
    }
}

object ShopDiff: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.image == newItem.image
                && oldItem.title == newItem.title
    }
}