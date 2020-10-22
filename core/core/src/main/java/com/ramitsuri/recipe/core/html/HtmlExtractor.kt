package com.ramitsuri.recipe.core.html

import org.jsoup.Jsoup
import java.io.IOException

class HtmlExtractor {

    fun extractByAttributeValue(url: String,
                                attributeKey: String,
                                attributeValue: String): String? {
        try {
            val document = Jsoup.connect(url).get()
            val elements = document.getElementsByAttributeValue(attributeKey, attributeValue)
            for (element in elements) {
                for (node in element.dataNodes()) {
                    val text = node.wholeData
                    if (text != null) {
                        return Jsoup.parse(text).text()
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}
