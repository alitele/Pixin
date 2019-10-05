package com.pixin.sample.app

import android.graphics.ColorSpace
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val urls: ArrayList<String> = ArrayList()
    var API_URL: String = "https://pastebin.com/raw/wgkJgazE"
    lateinit var imagesAdapter: ImagesAdapter
    lateinit var images_rv: RecyclerView
    lateinit var progress: ProgressBar
    val client = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images_rv = findViewById(R.id.rv_images)
        progress=findViewById(R.id.progressBar)

        fetchURLs(API_URL)

    }


    fun initRecyclerView(images: ArrayList<String>) {

        images_rv.layoutManager = GridLayoutManager(this, 2)
        imagesAdapter = ImagesAdapter(images, applicationContext)
        images_rv.adapter = imagesAdapter

    }


    fun fetchURLs(url: String) {
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                progress.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact: JSONArray = JSONArray(str_response)

                for (i in 0..json_contact.length()-1) {

                    var profile_image: JSONObject =
                        json_contact.getJSONObject(i).getJSONObject("user").getJSONObject("profile_image") as JSONObject;

                    urls.add(profile_image.get("large").toString())
                }

                runOnUiThread {
                    //stuff that updates ui
                    initRecyclerView(urls)
                    progress.visibility = View.GONE
                }

            }
        })
    }
}


