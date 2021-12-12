package com.example.dictionary.ui.main

import android.widget.ImageView

interface IImageLoader {
    fun loadImage(imageView: ImageView, imageLink: String)
}