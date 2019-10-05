package PackagePixinLib

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import android.widget.ImageView
import com.pixin.lib.DownloadCallback

class Pixin {

    companion object {
        val instance = Pixin();
    }

    lateinit var context: Context
    private lateinit var cacheStock: LruCache<String, Bitmap>


    //Default Max Cache Size
    var maxCacheSize: Int = MemoryUtils().getVMCacheSize() / 8

    //Set Context of Activity
    fun init(context: Context) {
        this.context = context

        cacheStock = object : LruCache<String, Bitmap>(maxCacheSize) {

            override fun sizeOf(key: String?, value: Bitmap): Int {
                //returns bytecount in a bitmap
                return value.getByteCount() / 1024;
            }
        }
    }

    //Set Max Cache Size
    fun setCacheSize(maxCacheSize: Int) {
        this.maxCacheSize = maxCacheSize
    }

    //Enque new request for loading bitmap asynchrounsly
    fun load(URL: String, PlaceHolder: Int, imageView: ImageView) {
        //Set Placeholder until we get image either from cache or network to show
        imageView.setImageResource(PlaceHolder)

        if (getBitmapCache(URL) != null) {
            imageView.setImageBitmap(getBitmapCache(URL))
        } else {
            //First check intetnet connectivity
            val result = NetworkUtils.verifyAvailableNetwork(context);
            if (result) {
                DownloadTask(URL, object : DownloadCallback {
                    override fun ImageDownloadSuccess(bitmap: Bitmap) {
                        imageView.setImageBitmap(bitmap)
                        setBitmapCache(URL, bitmap)
                    }

                    override fun ImageDownloadfail() {

                    }
                }).execute(URL)
            }

        }


    }

    //Retrive bitmap from cache if exit
    fun getBitmapCache(key: String): Bitmap? {
        return cacheStock.get(key)
    }

    //Set bitmap in cache
    fun setBitmapCache(key: String, bitmap: Bitmap) {
        if (getBitmapCache(key) == null)
            cacheStock.put(key, bitmap)
    }

    //Delete bitmap from cache if exist
    fun deleteBitmapCache(key: String) {
        cacheStock.remove(key)
    }

    //Clear all cache
    fun clearCacheStock() {
        cacheStock.evictAll()
    }

}