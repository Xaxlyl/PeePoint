package com.test.myapplication
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private val markers = mutableListOf<Marker>()
    private lateinit var markerList: RecyclerView
    private lateinit var buttonMap: Button
    private lateinit var buttonList: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        markerList = findViewById(R.id.marker_list)
        markerList.layoutManager = LinearLayoutManager(this)
        markerList.adapter = MarkerAdapter(markers)

        buttonMap = findViewById(R.id.button_map)
        buttonList = findViewById(R.id.button_list)
        buttonMap.isSelected = true
        buttonMap.setTextColor(ContextCompat.getColor(this, R.color.white))
        buttonList.setTextColor(ContextCompat.getColor(this, R.color.white))

        buttonMap.setOnClickListener {
            buttonMap.isSelected = true
            buttonList.isSelected = false
            buttonMap.setBackgroundResource(R.drawable.switch_button_left)
            buttonList.setBackgroundResource(R.drawable.switch_button_right)
            buttonMap.setTextColor(ContextCompat.getColor(this, R.color.white))
            buttonList.setTextColor(ContextCompat.getColor(this, R.color.white))
            mapView.visibility = View.VISIBLE
            markerList.visibility = View.GONE
        }

        buttonList.setOnClickListener {
            buttonList.isSelected = true
            buttonMap.isSelected = false
            buttonList.setBackgroundResource(R.drawable.switch_button_right)
            buttonMap.setBackgroundResource(R.drawable.switch_button_left)
            buttonList.setTextColor(ContextCompat.getColor(this, R.color.white))
            buttonMap.setTextColor(ContextCompat.getColor(this, R.color.white))
            mapView.visibility = View.GONE
            markerList.visibility = View.VISIBLE
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setOnMapClickListener(this)
        googleMap.setOnMarkerClickListener(this)

        // Add a marker in Sydney with a custom icon and data
        val sydney = LatLng(-34.0, 151.0)
        val marker = googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
                .snippet("Population: 5 million")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.explore_nearby))
        )
        marker?.let { markers.add(it) }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Update the list adapter
        markerList.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onMapClick(latLng: LatLng) {
        val marker = googleMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title("New Marker")
                .snippet("Custom data")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.explore_nearby))
        )
        marker?.let { markers.add(it) }

        // Update the list adapter
        markerList.adapter?.notifyDataSetChanged()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        // Handle marker click
        return true
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}