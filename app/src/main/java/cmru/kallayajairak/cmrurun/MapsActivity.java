package cmru.kallayajairak.cmrurun;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double cmruLatADouble=18.807096, cmruLngADouble = 98.986530;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    } // Main Method



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Setup to CMRU
        LatLng latLng = new LatLng(cmruLatADouble, cmruLngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));



    }//onMapReady
} //Main Class
