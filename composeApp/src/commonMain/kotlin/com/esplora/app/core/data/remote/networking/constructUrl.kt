package com.esplora.app.core.data.remote.networking

fun constructUrl(base: String, url: String): String {
    return when {
        url.contains(base) -> url
        url.startsWith("/") -> base + url.drop(1)
        else -> base + url
    }
}