package cmru.kallayajairak.cmrurun;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double cmruLatADouble=18.807096, cmruLngADouble = 98.986530;
    private double userLaADouble, userLngADouble;
    private LocationManager locationManager;
    private Criteria criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        //Setup
        userLaADouble = cmruLatADouble;
        userLngADouble = cmruLngADouble;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    } // Main Method

    @Override
    protected void onResume() {
        super.onResume();

        locationManager.removeUpdates(locationListener);

        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {
            userLaADouble = networkLocation.getLatitude();
            userLngADouble = networkLocation.getLongitude();

        }// if
        Location gpsLocation = myFindLocation(locationManager.GPS_PROVIDER);
        if (gpsLocation != null) {

            userLaADouble = gpsLocation.getLatitude();
            userLngADouble = gpsLocation.getLongitude();
        }

        Log.d("29JuneV1", "userLat ==> " + userLaADouble);
        Log.d("29JuneV1", "userlng ==> " + userLngADouble);

        {

        }// if

    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    public Location myFindLocation(String strProvider) {
        Location location = null;
        if (locationManager.isProviderEnabled(strProvider)) {
            locationManager.requestLocationUpdates(strProvider, 1000, 10,locationListener);
            location = locationManager.getLastKnownLocation(strProvider);

        } else {
            Log.d("29JuneV1", "Cannot Find Location");
        }



        return Location;
    }
    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            userLaADouble = location.getLatitude();
            userLngADouble = location.getLongitude();
        }//onLocationChange

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Setup to CMRU
        LatLng latLng = new LatLng(cmruLatADouble, cmruLngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        createStationMarker();


    }//onMapReady

    private void createStationMarker() {
        Mydata mydata = new Mydata();
        double[] latDoubles = mydata.getLatStationDoubles();
        double[] lngDoubles = mydata.getLngStationDoubles();
        int []iconInts = mydata.getIconStationInts();

        for (int i=0;i<latDoubles.length; i++) {
            LatLng latLng = new LatLng(latDoubles[i], lngDoubles[i]);
            mMap.addMarker(new MarkerOptions().position(latLng)
            .icon(BitmapDescriptorFactory.fromResource(iconInts[i]))
            .title("ด่านที่ " + Integer.toString(i+1)));
        }

    }//createStationMarker
} //Main Class
