package com.davidwinnicki.myrepo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.davidwinnicki.myrepo.R
import com.davidwinnicki.myrepo.adapters.RepoAdapter
import com.davidwinnicki.myrepo.ext.snack
import com.davidwinnicki.myrepo.model.Repo
import com.davidwinnicki.myrepo.network.GitHubService
import com.davidwinnicki.myrepo.utils.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RepoAdapter.OnItemClickListener {

    private val getHubService = GitHubService.getInstance()
    private var disposable: Disposable? = null
    private val repoAdapter = RepoAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        getRepos()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun initRecyclerView() {
        recyclerViewRepos.apply {
            adapter = repoAdapter
            val layoutManager = LinearLayoutManager(context)
            setLayoutManager(layoutManager)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun getRepos() {
        if (NetworkHelper().isNetworkAvailable(this)) {
            disposable =
                getHubService.getReposForUser(GITHUB_USER)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onResult(it) }, { onFailure(it) })
        } else {
            recyclerViewRepos.snack(getString(R.string.no_internet_connection))
        }
    }

    private fun onResult(repos: List<Repo>) {
        repoAdapter.setData(repos)
    }

    private fun onFailure(t: Throwable) {
        Log.d(TAG, "Something went wrong: ${t.message}")
    }

    override fun onItemClick(item: Repo) {
        startActivity(WebActivity.getIntent(this, item.htmlUrl))
    }

    companion object {
        const val TAG = "MainActivity"
        const val GITHUB_USER = "dwinnicki"
    }
}
