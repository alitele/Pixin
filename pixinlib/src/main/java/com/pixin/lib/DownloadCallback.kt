package com.pixin.lib

import android.graphics.Bitmap

interface DownloadCallback {

    fun ImageDownloadfail()
    fun ImageDownloadSuccess(bitmap: Bitmap)
}