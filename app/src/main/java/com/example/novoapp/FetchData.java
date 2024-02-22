package com.example.novoapp;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;


import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

// classe para obter os dados dos locais proximos
public class FetchData {
    String googleNearByPlacesData;
    GoogleMap googleMap;
    String url;

    public String execute(Object dataFetch[]) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            try {
                googleMap = (GoogleMap) dataFetch[0];
                url = (String) dataFetch[1];
                // com o metodo downloadUrl recuperamos os dados da Url e retornamos od dados dos locais proximos do google
                DownloadUrl downloadUrl = new DownloadUrl();
                googleNearByPlacesData = downloadUrl.retireveUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            handler.post(() -> {
                try {
                    JSONObject jsonObjct = new JSONObject(googleNearByPlacesData);
                    JSONArray jsonArray = jsonObjct.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjct1 = jsonArray.getJSONObject(i);
                        JSONObject getLocation = jsonObjct1.getJSONObject("geometry")
                                .getJSONObject("location");

                        String lat = getLocation.getString("lat");
                        String lng = getLocation.getString("lng");

                        JSONObject getName = jsonArray.getJSONObject(i);
                        String name = getName.getString("name");

                        LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.title(name);
                        markerOptions.position(latLng);
                        googleMap.addMarker(markerOptions);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        });

        return googleNearByPlacesData;
    }
}