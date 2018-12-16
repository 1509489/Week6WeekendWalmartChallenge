package com.pixelart.week6weekendwalmartchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.pixelart.week6weekendwalmartchallenge.R
import com.pixelart.week6weekendwalmartchallenge.common.GlideApp
import com.pixelart.week6weekendwalmartchallenge.databinding.RvItemLayoutBinding
import com.pixelart.week6weekendwalmartchallenge.model.Item
import kotlinx.android.synthetic.main.rv_item_layout.view.*

class ProductsAdapter(private val listener: OnItemClickedListener): ListAdapter<Item,ProductsAdapter.ViewHolder>(DIFF_CALLBACK){

    private lateinit var context: Context

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Item> = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.itemId == newItem.itemId
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.shortDescription == newItem.shortDescription &&
                        oldItem.salePrice == newItem.salePrice &&
                        oldItem.sellerInfo == newItem.sellerInfo &&
                        oldItem.thumbnailImage == newItem.thumbnailImage
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: RvItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.rv_item_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.apply {
            tvItemName.text = item.name
            tvDescription.text = item.shortDescription
            tvItemPrice.text = "$"+item.salePrice.toString()
            tvSellerInfo.text = item.sellerInfo

            GlideApp.with(context)
                .load(item.thumbnailImage)
                .override(100, 100)
                //.apply(RequestOptions.circleCropTransform())
                .into(ivImage)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClicked(position)
        }
    }

    class ViewHolder(binder: RvItemLayoutBinding) : RecyclerView.ViewHolder(binder.root)

    interface OnItemClickedListener{
        fun onItemClicked(position: Int)
    }
}