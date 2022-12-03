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
            Picasso.get().load(it?.imageUrl)
                .into(binding.tvImage)
        }
    }
}