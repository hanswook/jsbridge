package com.hans.vshbridge

/**
 *
 * @date:     2020/6/11 10:55 AM
 * @author: hanxu
 * @email hxxx1992@163.com
 * @description null
 */
data class JsFunctionParams(
    var handlerName: String = "",
    var data: Any? = null,
    var callbackId: String = ""
) {
}