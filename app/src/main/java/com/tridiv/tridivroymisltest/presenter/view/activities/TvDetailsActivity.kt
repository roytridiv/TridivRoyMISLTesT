package com.tridiv.tridivroymisltest.presenter.view.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.tridiv.tridivroymisltest.R
import com.tridiv.tridivroymisltest.databinding.ActivityTvDetailsBinding
import com.tridiv.tridivroymisltest.databinding.ActivityTvListBinding
import com.tridiv.tridivroymisltest.presenter.viewModel.TvListDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailsActivity : BaseActivity() {
    private val viewModel by viewModels<TvListDetailsViewModel>()
    private val binding by lazy { ActivityTvDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = intent.extras?.get("tv_id") ?: -1
        if (!isNetworkAvailable()) {
            AlertDialog.Builder(this)
                .setTitle("No Internet!")
                .setMessage("Please check your internet connection and try again.")
                .setPositiveButton("OK") { _: DialogInterface?, i: Int ->
                    finishAffinity()
                }
                .create()
                .show()
        } else {
            viewModel.getTvDetails(b.toString().toInt())
            registerObservers()
        }
        setContentView(binding.root)
    }

    private fun registerObservers() {
        binding.progressBar.visibility = View.VISIBLE
        binding.whiteBg.visibility = View.VISIBLE
        viewModel.tvDetailsDataResponse.observe(this) {
            with(binding) {
                Picasso.get().load(it?.imageUrl)
                    .into(tvImage)

                titleTv.text = it?.title ?: "-"
                modelTv.text = modelTv.text.toString().plus(it?.model ?: "-")
                tvPrice.text = tvPrice.text.toString().plus(it?.price).plus(" Tk")
                tvDescriptionVal.text = it?.description ?: "-"
                tvScreen.text =
                    tvScreen.text.toString().plus(it?.specification?.display?.screenSize ?: "-")
                tvResolution.text =
                    tvResolution.text.toString().plus(it?.specification?.oS?.operatingSystem ?: "-")
                tvType.text =
                    tvType.text.toString().plus(it?.specification?.productType?.productType ?: "-")
                tvDimension.text = tvDimension.text.toString()
                    .plus(it?.specification?.dimensionsMm?.setSizeWithStandWxHxD ?: "-")
                tvPqi.text = tvPqi.text.toString()
                    .plus(it?.specification?.video?.pQIPictureQualityIndex ?: "-")
                tvHdr.text =
                    tvHdr.text.toString().plus(it?.specification?.video?.hDRHighDynamicRange ?: "-")
                tvContrast.text =
                    tvContrast.text.toString().plus(it?.specification?.video?.contrast ?: "-")
                tvContrastEnhancer.text = tvContrastEnhancer.text.toString()
                    .plus(it?.specification?.video?.contrastEnhancer ?: "-")
                tvDolby.text =
                    tvDolby.text.toString().plus(it?.specification?.audio?.dolbyDigitalPlus ?: "-")
                tvSoundOutput.text = tvSoundOutput.text.toString()
                    .plus(it?.specification?.audio?.soundOutputRMS ?: "-")
                tvSpeakerType.text =
                    tvSpeakerType.text.toString().plus(it?.specification?.audio?.speakerType ?: "-")
                tvHdmi.text =
                    tvHdmi.text.toString().plus(it?.specification?.connectivity?.hDMI ?: "-")
                tvUsb.text = tvUsb.text.toString().plus(it?.specification?.connectivity?.uSB ?: "-")
                tvMirroring.text = tvMirroring.text.toString().plus(it?.specification?.smartFeature?.mobileToTVMirroringDLNA?:"-")
                tvSoundToMobile.text = tvSoundToMobile.text.toString().plus(it?.specification?.smartFeature?.tVSoundToMobile?:"-")
                tvSoundMirroring.text = tvSoundMirroring.text.toString().plus(it?.specification?.smartFeature?.soundMirroring?:"-")

                tvSeries.text = tvSeries.text.toString().plus(it?.specification?.others?.series?:"-")
                tvMicroDiming.text = tvMicroDiming.text.toString().plus(it?.specification?.others?.microDimming?:"-")
                tvQSymphony.text = tvQSymphony.text.toString().plus(it?.specification?.others?.qSymphony?:"-")
                tvWebBrowser.text = tvWebBrowser.text.toString().plus(it?.specification?.others?.webBrowser?:"-")
                tvDesign.text = tvDesign.text.toString().plus(it?.specification?.others?.design?:"-")
                tvBezelType.text = tvBezelType.text.toString().plus(it?.specification?.others?.bezelType?:"-")
                tvPowerSupply.text =tvPowerSupply.text.toString().plus(it?.specification?.others?.powerSupply?:"-")
                tvPower.text = tvPower.text.toString()
                    .plus(it?.specification?.power?.powerConsumptionMax ?: "-")
                progressBar.visibility = View.GONE
                whiteBg.visibility = View.GONE
            }


        }

    }
}