package com.tridiv.tridivroymisltest.presenter.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tridiv.tridivroymisltest.data.db.AppDatabase
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.databinding.ActivityMainBinding
import com.tridiv.tridivroymisltest.databinding.ActivityTvListBinding
import com.tridiv.tridivroymisltest.presenter.model.TvData
import com.tridiv.tridivroymisltest.presenter.view.adapters.TvListAdapter
import com.tridiv.tridivroymisltest.presenter.viewModel.TvListDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvListActivity : AppCompatActivity(), TvListAdapter.OnItemClickListener {
    private val binding by lazy { ActivityTvListBinding.inflate(layoutInflater) }

    private val appDB by lazy { AppDatabase.getDB(this@TvListActivity).getTvDao() }
    private val viewModel by viewModels<TvListDetailsViewModel>()
    var tvListResp: MutableList<TvDaoItem> = mutableListOf()
    private val tvListAdapter by lazy {
        TvListAdapter(
            tvListResp,
            this
        )
    }
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tvListResp = appDB.getAllTelevisionData().toMutableList()

        binding.tvListRv.layoutManager = linearLayoutManager
        binding.tvListRv.adapter = tvListAdapter
        registerObserver()
    }

    private fun registerObserver() {
//viewModel.tvListDataResponse.observe()
    }

    override fun onItemClick(Position: Int, tvData: TvDaoItem) {
        val intent = Intent(this@TvListActivity, TvDetailsActivity::class.java)
        intent.putExtra("tv_id", tvData.tvId)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}