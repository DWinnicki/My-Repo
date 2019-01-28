package com.davidwinnicki.myrepo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.davidwinnicki.myrepo.R
import com.davidwinnicki.myrepo.ext.snack
import com.davidwinnicki.myrepo.utils.NetworkHelper
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        loadWebPage()
    }

    private fun loadWebPage() {
        if (NetworkHelper().isNetworkAvailable(this)) {
            val htmlUrl = intent?.extras?.getString(REPO_HTML_URL_EXTRA)
            htmlUrl?.let {
                webView.loadUrl(htmlUrl)
            }
        } else {
            webView.snack(getString(R.string.no_internet_connection))
        }
    }

    companion object {
        private const val REPO_HTML_URL_EXTRA = "REPO_HTML_URL_EXTRA"

        fun getIntent(context: Context, htmlUrl: String): Intent =
            Intent(context, WebActivity::class.java)
                .putExtra("REPO_HTML_URL_EXTRA", htmlUrl)
    }
}
