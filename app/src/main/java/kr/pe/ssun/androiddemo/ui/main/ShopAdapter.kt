package kr.pe.ssun.androiddemo.ui.main

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kr.pe.ssun.androiddemo.data.model.ShopItem
import kr.pe.ssun.androiddemo.databinding.ItemShopBinding

class ShopAdapter(
    private val onClick: (String)->Unit
) : ListAdapter<ShopItem, ShopViewHolder>(ShopDiff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

class ShopViewHolder(
    private val binding: ItemShopBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ShopItem, onClick: (String)->Unit) {
        binding.iv.load(item.image)
        binding.tvTitle.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT)
        binding.root.setOnClickListener {
            onClick.invoke(item.link)
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