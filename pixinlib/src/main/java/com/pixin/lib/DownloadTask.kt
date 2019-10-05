package PackagePixinLib

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.graphics.BitmapFactory
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL
import android.util.Log
import com.pixin.lib.DownloadCallback


class DownloadTask(url: String, callback: DownloadCallback) : AsyncTask<String, Unit, Bitmap>() {

    private lateinit var callback: DownloadCallback
    var url: String = ""
    var inSampleSize: Int = 0
    private lateinit var image: Bitmap

    init {
        this.callback = callback
        this.url = url
    }

    override fun doInBackground(vararg params: String?): Bitmap {

        return DownloadImage(params[0].toString())
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        result?.let { callback.ImageDownloadSuccess(it) }

    }


    override fun onCancelled() {
        super.onCancelled()
    }

    fun DownloadImage(url: String): Bitmap {

        val options = BitmapFactory.Options()

        options.inSampleSize = inSampleSize

        options.inJustDecodeBounds = false

        val url = URL(url)

        var connection = url.openConnection() as HttpURLConnection

        var stream = connection.getInputStream()

        image = BitmapFactory.decodeStream(stream, null, options)!!

        return image

    }

}