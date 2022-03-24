package com.example.androidHelpers.extensions

import android.content.Context
import android.widget.Toast


fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(applicationContext, text, duration)
    toast.show()
}