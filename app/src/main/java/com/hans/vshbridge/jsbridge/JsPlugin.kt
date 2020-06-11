package com.hans.vshbridge.jsbridge

import java.util.*

/**
 *
 * @date:     2020/6/11 4:21 PM
 * @author: hanxu
 * @email hxxx1992@163.com
 * @description null
 */
class JsPlugin {
    val mFunctions: HashMap<String, JsFunction> = HashMap()

    fun registFunction(protocolName: String, jsFunction: JsFunction) {
        mFunctions[protocolName] = jsFunction
    }

}