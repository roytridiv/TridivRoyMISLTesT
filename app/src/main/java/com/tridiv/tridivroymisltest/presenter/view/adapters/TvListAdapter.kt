package com.tridiv.tridivroymisltest.presenter.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.databinding.TvListItemLayoutBinding
import com.tridiv.tridivroymisltest.presenter.model.TvData

class TvListAdapter(
    private var tvList: MutableList<TvDaoItem>,
    private val listener: OnItemClickListener,
) :
    RecyclerView.Adapter<TvListAdapter.TvListViewHolder>() {
    inner class TvListViewHolder(val binding: TvListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListViewHolder {
        val binding = TvListItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        val lp = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(10, 10, 10, 10)
        binding.root.layoutParams = lp

        return TvListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvListViewHolder, position: Int) {
        with(holder) {
            val listItem = tvList[position]
            with(binding) {

                if (listItem.image.isNotEmpty())
                    Picasso.get().load(listItem.image)
                        .into(televisionIv)

                televisionTitleTv.text = "Name: ".plus(listItem.name)
                priceTv.text = "Prize: ".plus(listItem.price.plus(" Tk"))
                modelTv.text = "Model: ".plus(listItem.model)

            }
            itemView.setOnClickListener {
                listener.onItemClick(position, listItem)
            }
        }
    }

    override fun getItemCount() = tvList.size

    interface OnItemClickListener {
        fun onItemClick(Position: Int, tvData: TvDaoItem)
    }
}