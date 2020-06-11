package com.hans.vshbridge

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hans.vshbridge.jsbridge.*
import com.hans.vshbridge.utils.JsonUtils

class MainActivity : AppCompatActivity() {

    private var webview: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview = findViewById<WebView>(R.id.webview) ?: return

        val settings = webview!!.settings
        settings.javaScriptEnabled = true;
        settings.useWideViewPort = true;
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        settings.allowFileAccess = false
        settings.setAppCacheEnabled(false)
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.loadWithOverviewMode = true
        webview?.loadUrl("http://10.237.209.160:9080/web/vshbridge.html")
        webview?.addJavascriptInterface(
            VshJsInterface(
                this,
                webview!!
            ), "vsh"
        )

        JsNativeCenter.instance.registFunction("hans://demo/log", object : JsFunction {
            override fun invoke(
                context: Context,
                params: String,
                jsFunctionHandler: JsFunctionHandler
            ) {
                Log.e("hans://demo/log", "params:$params")
                jsFunctionHandler.handler("this is return data from demo log")
            }

        })

        findViewById<TextView>(R.id.tvOne).setOnClickListener {
            callHandler("doSth", NormalData("showshowshow"))
        }
    }


    private fun callHandler(handlerName: String, params: Any) {
        val func = JsFunctionParams()
        func.handlerName = handlerName
        func.data = params
        var json = JsonUtils.toJsonString(func)
        webview?.evaluateJavascript(
            "javascript:callJS($json)"
        ) { }
    }
}
