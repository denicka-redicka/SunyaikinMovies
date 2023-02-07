package com.example.core.ui

import android.view.View

fun View.toGoneIf(condition: Boolean) {
    visibility = if (condition) View.GONE else View.VISIBLE
}