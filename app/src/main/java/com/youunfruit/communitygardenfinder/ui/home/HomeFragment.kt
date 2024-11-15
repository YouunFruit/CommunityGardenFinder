package com.youunfruit.communitygardenfinder.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.youunfruit.communitygardenfinder.GardenInfo
import com.youunfruit.communitygardenfinder.R
import com.youunfruit.communitygardenfinder.databinding.FragmentHomeBinding
import org.maplibre.android.annotations.Marker
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.camera.CameraUpdateFactory

class HomeFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var mapLibreMap: MapLibreMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1000

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            getUserLocation()
        }
    }
    private fun setupCustomInfoWindow() {
        mapLibreMap.setInfoWindowAdapter(object : MapLibreMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? {
                // Inflate custom layout
                val view = LayoutInflater.from(context).inflate(R.layout.garden_info_window, null)
                val title = view.findViewById<TextView>(R.id.info_window_title)
                val snippet = view.findViewById<TextView>(R.id.info_window_snippet)
                val button = view.findViewById<Button>(R.id.info_window_button)

                // Set marker title and snippet
                title.text = marker.title
                snippet.text = marker.snippet

                // Set button click listener
                button.setOnClickListener {
                    // Handle button click event
                    // For example, show a toast or open a new activity
                    val intent = Intent(requireContext(), GardenInfo::class.java)
                    intent.putExtra("PLACE_ID", marker.title as String)
                    startActivity(intent)
                }

                return view
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getUserLocation()
        } else {
            // Handle case where permission is denied
        }
    }

    private fun getUserLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val userLocation = LatLng(location.latitude, location.longitude)
                mapLibreMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10.0))

                val marker = MarkerOptions()
                    .position(userLocation)
                    .title("You are here")
                    .snippet("This is your current location")

                mapLibreMap.addMarker(marker)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize MapView
        mapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        // Set up the map asynchronously
        mapView.getMapAsync { map ->
            mapLibreMap = map

            // Set the map style
            mapLibreMap.setStyle("https://api.maptiler.com/maps/basic-v2/style.json?key=HqnxDZmNRf6yheBaVLt9") {
                // Add any map customizations here if needed
                setupCustomInfoWindow()
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                checkLocationPermission()
            }

            // Move the camera to an initial location
            val initialLocation = LatLng(40.7128, -74.0060) // Example: New York City
            mapLibreMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 10.0))
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}