package io.schinzel.jstranspiler.transpiler.method

import io.schinzel.jstranspiler.transpiler.IToJavaScript
import io.schinzel.jstranspiler.transpiler.getSimpleClassName
import io.schinzel.jstranspiler.transpiler.isList
import kotlin.reflect.KProperty1

/**
 * Purpose of this class is to construct the JavaScript code for a setter
 */
internal class JsSetter(private val property: KProperty1<out Any, Any?>, private val dataClassName: String) : IToJavaScript {


    override fun toJavaScript(): String {
        val jsCodeMethodName = JsMethodUtil.methodName("set", property.name)
        val jsDocArgumentDataType = JsDoc.getDataTypeName(property)
        val jsDocMethodDescription = jsDocMethodDescription(property.isList())
        val jsCodeArrayCopyString = JsMethodUtil.arrayCopyString(property.isList())
        val propertyName = property.name
        return """
            |    // noinspection JSUnusedGlobalSymbols
            |    /**
            |     * $jsDocMethodDescription
            |     * @param {$jsDocArgumentDataType} $propertyName
            |     * @return {$dataClassName}
            |     */
            |    $jsCodeMethodName($propertyName) {
            |        this.$propertyName = $propertyName$jsCodeArrayCopyString;
            |        return this;
            |    }
            |    """.trimMargin()
    }


    companion object {
        fun jsDocMethodDescription(isList: Boolean) = if (isList) "Argument array is copied and set" else ""
    }
}