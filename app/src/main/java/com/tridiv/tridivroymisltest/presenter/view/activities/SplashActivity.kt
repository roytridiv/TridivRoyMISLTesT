package com.tridiv.tridivroymisltest.presenter.view.activities

import android.content.Context
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
        if(!isNetworkAvailable()){
            binding.logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse))
            Toast.makeText(this,"No Internet", Toast.LENGTH_LONG).show()
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

    private fun isNetworkAvailable(): Boolean {
        try {
            val connectivityManager: ConnectivityManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) as NetworkCapabilities
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            } else {
                try {
                    val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                        return true
                    }
                } catch (exception: Exception) {
                }
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }
}