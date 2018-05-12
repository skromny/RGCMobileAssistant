package com.recrutify.rgc.mobileassistant.common

import android.util.Log
import java.util.regex.Pattern

data class ApiSuccessResponse<T>(val body: T, val links: Map<String, String>) : ApiResponse<T>() {

    //główny konstruktor nie zawira kodu
    //kod dla konstruktora głównego może być w bloku init (init to nie funkcja bo nie jest definiowane z fun)

    init {
        //kod dla konstruktora głównego
    }

    //konstruktor dodatkowy, wywołujący główny
    constructor(body: T, linkHeader: String?) : this(body,linkHeader?.extractLinks() ?: emptyMap())

    val nextPage: Int? by lazy(LazyThreadSafetyMode.NONE) {
        links[NEXT_LINK]?.let { next ->
            val matcher = PAGE_PATTERN.matcher(next)
            if (!matcher.find() || matcher.groupCount() != 1) {
                null
            } else {
                try {
                    Integer.parseInt(matcher.group(1))
                } catch (ex: NumberFormatException) {
                    Log.w("API", "cannot parse next page from {next}")
                    null
                }
            }
        }
    }

    companion object {
        private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private const val NEXT_LINK = "next"

        private fun String.extractLinks(): Map<String, String> {
            val links = mutableMapOf<String, String>()
            val matcher = LINK_PATTERN.matcher(this)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links[matcher.group(2)] = matcher.group(1)
                }
            }
            return links
        }

    }
}
