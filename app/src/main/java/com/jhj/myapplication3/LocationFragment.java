package com.jhj.myapplication3;


import android.os.Bundle;


import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.api.MapPOIItem;



public class LocationFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);

        MapView mapView = new MapView(getActivity());


        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.mapView);
        mapViewContainer.addView(mapView);

        mapView.setMapCenterPoint(MapPoint.mapPointWithCONGCoord(35.160622891050146, 126.87957687879235),true);

        mapView.setZoomLevel(2,true);

        MapPoint MARKER_POINTER = MapPoint.mapPointWithGeoCoord(35.160622891050146, 126.87957687879235);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Defalut Marker");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINTER);
        marker.setMarkerType(MapPOIItem.MarkerType.RedPin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.YellowPin);
        mapView.addPOIItem(marker);

        return v;
    }
}