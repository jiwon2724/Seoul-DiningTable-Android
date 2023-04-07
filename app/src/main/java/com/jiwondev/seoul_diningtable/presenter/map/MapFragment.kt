package com.jiwondev.seoul_diningtable.presenter.map

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.databinding.FragmentMapBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseFragment
import com.naver.maps.map.*
import com.naver.maps.map.MapFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.naverMap) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.naverMap, it).commit()
            }
        mapFragment.getMapAsync(this)
    }


    @SuppressLint("MissingPermission")
    private fun getLatitudeLongitude(): String {
        val locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        val currentLocation: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        return currentLocation?.let { "${it.latitude},${it.longitude}" }?: "주소없음"
    }


    override fun onMapReady(p0: NaverMap) {
        Log.d("result : ", getLatitudeLongitude())
        // TODO reverse geocoding
    }
}