package com.app.newapicalling.model

data class Support(
    val text: String,
    val url: String,
    var support: Support
)