package com.youunfruit.communitygardenfinder

import android.app.Application
import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer

class CGFApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize MapLibre with your API key or WellKnownTileServer
        MapLibre.getInstance(this, "HqnxDZmNRf6yheBaVLt9", WellKnownTileServer.MapTiler)
    }
}