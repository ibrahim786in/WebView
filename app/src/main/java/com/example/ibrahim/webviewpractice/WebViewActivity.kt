package com.example.ibrahim.webviewpractice

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_web_view)
        val listener = View.OnClickListener { v ->
            when (v.id) {
                R.id.searchUrlBtnToGo -> webView.loadUrl("http://" + urlEditT.text.toString())
                R.id.fbBtn -> {
                    webView.loadUrl("http://www.facebook.com")
                    urlEditT.setText("www.facebook.com")
                }
                R.id.googleBtn -> {
                    webView.loadUrl("http://www.google.com")
                    urlEditT.setText("www.google.com")

                }
                R.id.youtubeBtn -> {
                    webView.loadUrl("http://www.youtube.com")
                    urlEditT.setText("www.youtube.com")

                }
                R.id.htmlpage -> webView.loadUrl("file:///android_asset/htmlfile.html")
            }

        }
        searchUrlBtnToGo.setOnClickListener(listener)
        fbBtn.setOnClickListener(listener)
        googleBtn.setOnClickListener(listener)
        youtubeBtn.setOnClickListener(listener)
        htmlpage.setOnClickListener(listener)
        val progressDialog = ProgressDialog(this@WebViewActivity)
        progressDialog.setMessage("Please Wait till Page is Loading")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressDialog.hide()
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.addJavascriptInterface(this@WebViewActivity, "displayMsg")
    }//onCreate Close

    @JavascriptInterface
    fun funcLogin(email: String, pass: String) {
        Toast.makeText(this@WebViewActivity, "Email: $email\n Password: $pass", Toast.LENGTH_LONG).show()
    }


}
