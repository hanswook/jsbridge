package com.hans.vshbridge.jsbridge

import android.content.Context
import com.hans.vshbridge.utils.StringUtils
import java.util.*

/**
 *
 * @date:     2020/6/11 2:13 PM
 * @author: hanxu
 * @email hxxx1992@163.com
 * @description null
 */
class JsNativeCenter {
    companion object {
        @JvmStatic
        val instance: JsNativeCenter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            JsNativeCenter()
        }
    }

    private val mFunctions: HashMap<String, JsFunction> = HashMap()

    fun registFunctionWithPlugin(jsPlugin: JsPlugin) {
        jsPlugin.mFunctions.forEach { (protocolName, jsFunction) ->
            mFunctions[protocolName] = jsFunction
        }
    }

    fun registFunction(protocolName: String, jsFunction: JsFunction) {
        mFunctions[protocolName] = jsFunction
    }

    fun function(
        context: Context,
        protocolName: String,
        params: String,
        jsFunctionHandler: JsFunctionHandler
    ) {
        callNative(
            context,
            protocolName,
            params,
            jsFunctionHandler
        )
    }

    private fun callNative(
        context: Context, protocolName: String, params: String, jsFunctionHandler: JsFunctionHandler
    ) {
        try {
            val function: JsFunction = findFunction(protocolName)
            function.invoke(context, params, jsFunctionHandler)
        } catch (var6: Exception) {
            var6.printStackTrace()
        }
    }

    private fun findFunction(protocolName: String): JsFunction {
        return if (StringUtils.isInvalid(protocolName)) {
            throw IllegalArgumentException("protocolName can not be null!")
        } else {
            mFunctions[protocolName] ?: throw IllegalArgumentException("Not found the function!")
        }
    }
}