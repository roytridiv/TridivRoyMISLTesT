package com.tridiv.tridivroymisltest.presenter.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tridiv.tridivroymisltest.data.db.AppDatabase
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.data.repository.Repository
import com.tridiv.tridivroymisltest.databinding.ActivityTvListBinding
import com.tridiv.tridivroymisltest.presenter.view.adapters.TvListAdapter
import com.tridiv.tridivroymisltest.presenter.viewModel.TvListDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvListActivity : AppCompatActivity(), TvListAdapter.OnItemClickListener {
    private val binding by lazy { ActivityTvListBinding.inflate(layoutInflater) }

    @Inject
    lateinit var repository: Repository

    private val viewModel by viewModels<TvListDetailsViewModel>()
    private var tvListResp: MutableList<TvDaoItem> = mutableListOf()
    private val tvListAdapter by lazy {
        TvListAdapter(
            tvListResp,
            this
        )
    }
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private var currentPage: Int = 0
    var pageList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val myList = intent.extras?.getSerializable("page_list") as ArrayList<*>?
        pageList = myList as ArrayList<Int>
        initUi()
        checkPageTransition(pageList)
        registerObserver()
    }

    private fun registerObserver() {
//viewModel.tvListDataResponse.observe()
    }

    private fun initUi() {
        binding.tvListRv.layoutManager = linearLayoutManager
        binding.tvListRv.adapter = tvListAdapter
        binding.buttonBar.left.setOnClickListener {
            if (currentPage > 0) currentPage--
            checkPageTransition(pageList)
        }
        binding.buttonBar.right.setOnClickListener {
            if (currentPage < pageList.size) currentPage++
            checkPageTransition(pageList)
        }
    }


    private fun checkPageTransition(pageList: ArrayList<Int>) {
        if (pageList.isNotEmpty()) {
            repository.getAllTvDataFromCurrentPage(pageList.get(currentPage).toString())
                .observe(this) {
                    if (tvListResp.isNotEmpty()) tvListResp.clear()
                    tvListResp.addAll(it.toMutableList())
                    tvListAdapter.notifyDataSetChanged()
                    binding.buttonBar.pageTv.text = "Page ".plus(pageList.get(currentPage))
                    if (currentPage >= pageList.size - 1) {
                        binding.buttonBar.right.isEnabled = false
                        binding.buttonBar.right.isClickable = false
                        binding.buttonBar.left.isEnabled = true
                        binding.buttonBar.left.isClickable = true
                    } else if (currentPage <= 0) {
                        binding.buttonBar.left.isEnabled = false
                        binding.buttonBar.left.isClickable = false
                        binding.buttonBar.right.isEnabled = true
                        binding.buttonBar.right.isClickable = true
                    }
                }
        }
    }

    override fun onItemClick(Position: Int, tvData: TvDaoItem) {
        val intent = Intent(this@TvListActivity, TvDetailsActivity::class.java)
        intent.putExtra("tv_id", tvData.tvId)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        repository.clearDb()
        finishAffinity()
    }
}