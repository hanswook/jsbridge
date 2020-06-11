package com.hans.vshbridge

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hans.vshbridge.jsbridge.JsCallbackCenter
import com.hans.vshbridge.jsbridge.JsFunction
import com.hans.vshbridge.jsbridge.JsFunctionHandler
import com.hans.vshbridge.jsbridge.JsNativeCenter

class MainActivity : AppCompatActivity() {

    private val webview = findViewById<WebView>(R.id.webview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val settings = webview.settings
        settings.javaScriptEnabled = true;
        settings.useWideViewPort = true;
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        settings.allowFileAccess = false
        settings.setAppCacheEnabled(false)
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.loadWithOverviewMode = true
        webview.loadUrl("http://10.237.209.160:9080/web/vshbridge.html")
        webview.addJavascriptInterface(VshJsInterface(this, webview), "vsh")

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
            val func = JsFunctionParams()
            func.handlerName = "doSth"
            var normalData = NormalData("showshowshow")
            func.data = normalData
            var json = JsonUtils.toJsonString(func)
            //escape special characters for json string
            webview.evaluateJavascript(
                "javascript:callJS($json)"
            ) { }
        }
    }


    fun callHandler(handlerName: String, params: String, jsFunctionHandler: JsFunctionHandler) {
        val func = JsFunctionParams()
        func.handlerName = handlerName
        func.data = params
        func.callbackId= JsCallbackCenter.uniqueId++.toString();
        var json = JsonUtils.toJsonString(func)
        JsCallbackCenter.addFunction(func.callbackId,jsFunctionHandler)
        webview.evaluateJavascript(
            "javascript:callJS($json)"
        ) { }
    }
}
