package com.tridiv.tridivroymisltest.presenter.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.tridiv.tridivroymisltest.databinding.ActivityTvDetailsBinding
import com.tridiv.tridivroymisltest.databinding.ActivityTvListBinding
import com.tridiv.tridivroymisltest.presenter.viewModel.TvListDetailsViewModel


class TvDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModels<TvListDetailsViewModel>()
    private val binding by lazy { ActivityTvDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = intent.extras?.get("tv_id") ?: -1
        viewModel.getTvDetails(b.toString().toInt())
        registerObservers()
        setContentView(binding.root)
    }

    private fun registerObservers() {
        viewModel.tvDetailsDataResponse.observe(this) {
            with(binding){
                Picasso.get().load(it?.imageUrl)
                    .into(tvImage)

                titleTv.text = it?.title?:"-"
                modelTv.text = modelTv.text.toString().plus(it?.model?:"-")
                tvPrice.text = tvPrice.text.toString().plus(it?.price).plus(" Tk")
                tvDescriptionVal.text = it?.description?:"-"
                tvScreen.text = tvScreen.text.toString().plus(it?.specification?.display?.screenSize?:"-")
                tvResolution.text = tvResolution.text.toString().plus(it?.specification?.oS?.operatingSystem?:"-")
                tvType.text = tvType.text.toString().plus(it?.specification?.productType?.productType?:"-")
                tvDimension.text = tvDimension.text.toString().plus(it?.specification?.dimensionsMm?.setSizeWithStandWxHxD?:"-")
                tvPqi.text = tvPqi.text.toString().plus(it?.specification?.video?.pQIPictureQualityIndex?:"-")
                tvHdr.text = tvHdr.text.toString().plus(it?.specification?.video?.hDRHighDynamicRange?:"-")
                tvContrast.text = tvContrast.text.toString().plus(it?.specification?.video?.contrast?:"-")
                tvContrastEnhancer.text = tvContrastEnhancer.text.toString().plus(it?.specification?.video?.contrastEnhancer?:"-")
            }

        }
    }
}