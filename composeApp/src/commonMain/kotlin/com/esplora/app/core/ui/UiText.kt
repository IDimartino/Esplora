package com.esplora.app.core.ui

sealed class UiText {
    data class DynamicString(val text: String): UiText()
    data class StringResource(val resId: Int): UiText()
}