package com.jiwondev.seoul_diningtable.presenter.map

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.databinding.FragmentMapBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseFragment
import com.naver.maps.map.*
import com.naver.maps.map.MapFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate), OnMapReadyCallback {
    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        binding.searchAroundConstraint.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), SearchProductActivity::class.java))
        }
    }

    private fun init() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.naverMap) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.naverMap, it).commit()
            }
        mapFragment.getMapAsync(this)
    }


    // TODO : 프래그먼트에서 권한 확인
    @SuppressLint("MissingPermission")
    private fun getLatitudeLongitude(): String {
        val locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        val currentLocation: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        return currentLocation?.let { "${it.latitude},${it.longitude}" }?: "주소없음"
    }

    private fun startFlow() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


            }
        }
    }


    override fun onMapReady(p0: NaverMap) {
        startFlow()
    }
}