package com.example.wallpaper

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.os.Handler
import android.graphics.BitmapFactory
import android.app.WallpaperManager

class MainActivity : AppCompatActivity() {
    var myWallpaperList = arrayOf(R.drawable.wallpaper1,R.drawable.wallpaper2,R.drawable.wallpaper3,R.drawable.wallpaper4)
    private lateinit var changeWallpaper:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeWallpaper = findViewById(R.id.button)
        changeWallpaper.setOnClickListener { setWallpaper() }
    }

    fun setWallpaper() {
        Toast.makeText(this, "Setting Wallpaper please wait.", Toast.LENGTH_SHORT).show()
        @Suppress("DEPRECATION")
        Handler().postDelayed({
            for(i in myWallpaperList) {
                val bitmap: Bitmap = BitmapFactory.decodeResource(resources, i)
                val wallpaperManager = WallpaperManager.getInstance(baseContext)
                wallpaperManager.setBitmap(bitmap)
            }
        }, 2000)
    }
}