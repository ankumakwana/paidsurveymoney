package com.paidsurvey.paidsurveymoney

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.net.ConnectivityManager
import android.widget.LinearLayout
import android.widget.TextView


class PageFragment :Fragment() {
    private var mUrl: String = ""
    private var mName: String = ""

    companion object {
        val URL = "URL"
        val NAME = "Name"

        fun newInstance(url: String,name:String): PageFragment {
            val args = Bundle()
            args.putString(URL, url)
            val fragment = PageFragment()
            args.putString(NAME, name)
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
        val no_network=view.findViewById(R.id.no_network) as LinearLayout
        val retry=view.findViewById(R.id.retry) as TextView
        if(isOnline()){
            loadView(webView)
            no_network.visibility=View.GONE
            webView.visibility=View.VISIBLE
        }else{
            no_network.visibility=View.VISIBLE
            webView.visibility=View.GONE
        }
        retry.setOnClickListener {
            if(isOnline()){
                loadView(webView)
                no_network.visibility=View.GONE
                webView.visibility=View.VISIBLE
            }else{
                no_network.visibility=View.VISIBLE
                webView.visibility=View.GONE
            }
        }

        return view
    }
    fun loadView(webView: WebView) {
        val webSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webView.setHorizontalScrollBarEnabled(true)
        webView.setVerticalScrollBarEnabled(true)
        webView.setWebViewClient(AppWebViewClients(activity))
        webView.loadUrl(mUrl)
    }
    fun isOnline(): Boolean {
        val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
    inner class AppWebViewClients(activity1: FragmentActivity?) : WebViewClient() {
        lateinit var mDialog:Dialog;
       init {
           mDialog= Dialog(activity1)
       }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            // TODO Auto-generated method stub
           // view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            // TODO Auto-generated method stub
//            if(mDialog.isShowing){
//                mDialog.dismiss()
//            }
            super.onPageFinished(view, url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
//            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            if(!mDialog.isShowing) {
//
//                mDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                mDialog.setCancelable(false)
//                mDialog.setCanceledOnTouchOutside(false)
//                mDialog.setContentView(R.layout.custom_progress_dialog)
//                mDialog.show()
//            }

        }
    }
}
