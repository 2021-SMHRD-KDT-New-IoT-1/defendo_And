package com.jhj.myapplication3;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.List;


public class LocationFragment extends Fragment {
    private double lon;
    private double lat;
    private Geocoder geocoder;
    private RequestQueue requestQueue;
    LocationListener gpsLocationListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        geocoder = new Geocoder(getContext());
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getContext());
        }


        final LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }else{
            String locationProvider = LocationManager.NETWORK_PROVIDER;
            Location location = lm.getLastKnownLocation(locationProvider);
            lon = location.getLatitude();
            lat = location.getLatitude();

            try {
                List<Address> citylist = geocoder.getFromLocation(lat,lon,10);
                if(citylist!=null){
                    if(citylist.size()==0){
                        Log.e("reverseGeocoding","해당도시 없음");
                    }
                    else{
                        String subLocality = citylist.get(0).getSubLocality();
                        String thoroughfare = citylist.get(0).getThoroughfare();
//                        tv_gps.setText(subLocality + " " + thoroughfare);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,gpsLocationListener);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,1,gpsLocationListener);
        }

        return v;
    }
}