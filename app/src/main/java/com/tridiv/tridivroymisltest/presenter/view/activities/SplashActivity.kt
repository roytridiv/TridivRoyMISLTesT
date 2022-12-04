package com.tridiv.tridivroymisltest.presenter.view.activities

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.tridiv.tridivroymisltest.databinding.ActivityMainBinding
import com.tridiv.tridivroymisltest.presenter.viewModel.TvListDetailsViewModel
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.tridiv.tridivroymisltest.R
import com.tridiv.tridivroymisltest.data.db.AppDatabase
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.presenter.model.TvData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<TvListDetailsViewModel>()

    //    private val appDB by lazy { AppDatabase.getDB(this@SplashActivity).getTvDao() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(!isNetworkAvailable()){
            binding.logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse))
            AlertDialog.Builder(this)
                .setTitle("No Internet!")
                .setMessage("Please check your internet connection and try again.")
                .setPositiveButton("OK") { _: DialogInterface?, i: Int ->
                    finishAffinity()
                }
                .create()
                .show()
        }else{
            binding.logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse))
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getTvList(this@SplashActivity)
                delay(5000L)
                withContext(Dispatchers.Main) {
                    val test = viewModel.pageNoLiveData
                    println("------------------------"+test)
                    val intent = Intent(this@SplashActivity, TvListActivity::class.java)
                    intent.putExtra("page_list", test)
                    startActivity(intent)
                }
            }
        }
    }


}