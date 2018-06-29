package com.paidsurvey.paidsurveymoney

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient





class PageFragment :Fragment() {
    private var mUrl: String = ""
    private var mName: String = ""

    companion object {
        val URL = "URL"
        val NAME = "Name"

        fun newInstance(url: String,name:String): PageFragment {

            val args = Bundle()
            args.putString(URL, url)
            args.putString(NAME, name)
            val fragment = PageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUrl = arguments!!.getString(URL)
        mName = arguments!!.getString(NAME)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_webview, container, false)
        val webView = view.findViewById(R.id.webview) as WebView

        val activity = activity as Activity_Home?
        activity!!.setActionBarHome(mName)
        val webSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webView.setHorizontalScrollBarEnabled(true)
        webView.setVerticalScrollBarEnabled(true)
        webView.setWebViewClient(AppWebViewClients())
        webView.loadUrl(mUrl)
        return view
    }

    inner class AppWebViewClients() : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            // TODO Auto-generated method stub
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url)
        }
    }
}
