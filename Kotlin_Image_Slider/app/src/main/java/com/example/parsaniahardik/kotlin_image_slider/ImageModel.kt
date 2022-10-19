package com.example.parsaniahardik.kotlin_image_slider

/**
 * Created by Parsania Hardik on 03-Jan-17.
 */
class ImageModel {

    private var image_drawable: Int = 0

    fun getImage_drawables(): Int {
        return image_drawable
    }

    fun setImage_drawables(image_drawable: Int) {
        this.image_drawable = image_drawable
    }
}