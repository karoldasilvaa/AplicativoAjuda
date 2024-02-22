package com.example.novoapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.Manifest;
import android.widget.ImageButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class Mapa extends FragmentActivity implements OnMapReadyCallback {

   // GoogleMap gMap;
    Location currentLocation;
    FusedLocationProviderClient fusedClient;

    private static final int REQUEST_CODE = 101;

    private double lat, lng;
    GoogleMap map;

    ImageButton localizardelegacia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        localizardelegacia = (ImageButton) findViewById(R.id.localizardelegacia);

        //  gerar o mapa
        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
    }
    // Obter localização atual e pedir permissão, verificar as permissões da localização
    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // se não for concedida solicitamos a permissão
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {


            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currentLocation = location;
                    SupportMapFragment supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(Mapa.this);

                    localizardelegacia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                            stringBuilder.append("location=" + lat + "," + lng);
                            stringBuilder.append("&radius=3000");
                            stringBuilder.append("&sensor=true");
                            stringBuilder.append("&keyword=policestation");
                            stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                            String url = stringBuilder.toString();
                            Object dataFetch[] = new Object[2];
                            dataFetch[0] = map;
                            dataFetch[1] = url;

                            FetchData fetchData = new FetchData();
                            fetchData.execute(dataFetch);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Minha Localização");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        // marcador para mostrar localização no mapa
        googleMap.addMarker(markerOptions);
        lat = latLng.latitude;
        lng = latLng.longitude;
    }
  // Solicitar a permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == REQUEST_CODE){
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }
            }
    }
}
