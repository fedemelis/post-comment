package com.example.recycleview.extension


import android.os.Handler
import java.text.NumberFormat

/**
 * Extensions.kt
 * Menuvio
 *
 * Created by Osvaldo Pirrello on 16/05/2020.
 * Copyright © 2020 OverApp. All rights reserved.
 */

fun withDelay(delay: Long, block: () -> Unit) {
    Handler().postDelayed(Runnable(block), delay)
}

val Double.asCurrency: String
    get() {
        // Format currency
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        return format.format(this)
    }

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }
