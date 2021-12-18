package com.example.dictionary.ui.main.imageloader

import android.widget.ImageView

interface IImageLoader {
    fun loadImage(imageView: ImageView, imageLink: String)
}