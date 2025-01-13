package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceID: Int,
    val courseId: Int,
    @DrawableRes val imageResourceId: Int
)
