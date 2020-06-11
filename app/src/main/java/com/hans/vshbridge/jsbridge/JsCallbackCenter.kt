package com.hans.vshbridge.jsbridge

/**
 *
 * @date:     2020/6/11 7:00 PM
 * @author: hanxu
 * @email hxxx1992@163.com
 * @description null
 */
object JsCallbackCenter {
    private val jsFunctionHandlers: HashMap<String, JsFunctionHandler> = HashMap()
    var uniqueId = 0

    fun addFunction(
        callbackId: String,
        jsFunctionHandler: JsFunctionHandler
    ) {
        jsFunctionHandlers[callbackId] = jsFunctionHandler
    }

    fun findCallback(callbackId: String): JsFunctionHandler? {
        return jsFunctionHandlers[callbackId]
    }
}