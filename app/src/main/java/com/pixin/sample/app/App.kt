package com.pixin.sample.app

import PackagePixinLib.Pixin
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        //Initializing Library once
        Pixin.instance.init(this@App);
    }
}