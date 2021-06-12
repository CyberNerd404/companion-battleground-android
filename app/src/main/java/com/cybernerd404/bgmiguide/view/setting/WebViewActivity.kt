package com.cybernerd404.bgmiguide.view.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cybernerd404.bgmiguide.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val intent = intent.extras
        val url = intent?.getString("url") as String

        /*webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(url)
                return true
            }
        }*/
        webView.settings.javaScriptEnabled = true

        webView.loadUrl(url)


    }
}