package com.hans.vshbridge.jsbridge

import android.content.Context

/**
 *
 * @date:     2020/6/11 4:00 PM
 * @author: hanxu
 * @email hxxx1992@163.com
 * @description null
 */
interface JsFunction {
    fun invoke(
        context: Context,
        params: String,
        jsFunctionHandler: JsFunctionHandler
    )

}