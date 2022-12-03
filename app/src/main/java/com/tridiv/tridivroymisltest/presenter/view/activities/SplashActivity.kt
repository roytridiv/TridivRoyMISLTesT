package com.tridiv.tridivroymisltest.presenter.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.tridiv.tridivroymisltest.databinding.ActivityMainBinding
import com.tridiv.tridivroymisltest.presenter.viewModel.TvListDetailsViewModel
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.tridiv.tridivroymisltest.R
import com.tridiv.tridivroymisltest.data.db.AppDatabase
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.presenter.model.TvData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<TvListDetailsViewModel>()

    //    private val appDB by lazy { AppDatabase.getDB(this@SplashActivity).getTvDao() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse))
        lifecycleScope.launch(Dispatchers.IO) {

            viewModel.getTvList(this@SplashActivity)
            delay(500L)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashActivity, TvListActivity::class.java)
                startActivity(intent)
            }
        }

    }
}