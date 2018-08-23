package com.upays.Activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.maps.model.JointType.ROUND;


import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.upays.R;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private static final long UPDATE_INTERVAL = 5 * 6000;
    private static final long FASTEST_UPDATE_INTERVAL = UPDATE_INTERVAL / 2;
    private static final long MAX_WAIT_TIME = UPDATE_INTERVAL * 3;
    LocationManager mLocationManager;
    SupportMapFragment mapFragment;

    private GoogleMap mMap;
    TextView address_txt;
    private List<LatLng> polyLineList;
    EditText destinationEditText;
    ImageView search_btn;
    String destination;

    private PolylineOptions polylineOptions, blackPolylineOptions;
    private Polyline blackPolyline, greyPolyLine;

    private Handler handler;
    private Marker marker;
    private float v;
    private int index, next;
    private LatLng startPosition, endPosition;
    private double lat, lng;
    private LatLng sydney;

    Double lovelat,lovlong;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        search_btn = (ImageView) findViewById(R.id.search_btn);
        address_txt = (TextView) findViewById(R.id.address_txt);
        polyLineList = new ArrayList<>();

        destinationEditText = (EditText) findViewById(R.id.destination_id);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
          mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        createLocationRequest();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination = destinationEditText.getText().toString();
                destination = destination.replace(" ", "+");
                Log.d("destinationEditText", destination);
                mapFragment.getMapAsync(MapsActivity.this);
                new MapsActivity.DataLongOperationAsynchTask().execute();
                //drawpath(lovelat,lovlong);

            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

   /* protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }*/

   public void createnewploy(){

       //Adjusting bounds
       LatLngBounds.Builder builder = new LatLngBounds.Builder();
      /* for (LatLng latLng : polyLineList) {
           builder.include(latLng);
       }*/

       LatLng latLng = new LatLng(lovelat,lovlong);
       builder.include(latLng);
       startPosition = latLng;



       polyLineList.add(latLng);

       LatLngBounds bounds = builder.build();
       CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 2);
       mMap.animateCamera(mCameraUpdate);

       polylineOptions = new PolylineOptions();
       polylineOptions.color(Color.GRAY);
       polylineOptions.width(5);
       polylineOptions.startCap(new SquareCap());
       polylineOptions.endCap(new SquareCap());
       polylineOptions.jointType(ROUND);
       polylineOptions.addAll(polyLineList);
       greyPolyLine = mMap.addPolyline(polylineOptions);

       blackPolylineOptions = new PolylineOptions();
       blackPolylineOptions.width(5);
       blackPolylineOptions.color(Color.BLACK);
       blackPolylineOptions.startCap(new SquareCap());
       blackPolylineOptions.endCap(new SquareCap());
       blackPolylineOptions.jointType(ROUND);
       blackPolyline = mMap.addPolyline(blackPolylineOptions);

       mMap.addMarker(new MarkerOptions()
               .position(polyLineList.get(polyLineList.size()-1 )));

       ValueAnimator polylineAnimator = ValueAnimator.ofInt(0, 100);
       polylineAnimator.setDuration(2000);
       polylineAnimator.setInterpolator(new LinearInterpolator());
       polylineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
           @Override
           public void onAnimationUpdate(ValueAnimator valueAnimator) {
               List<LatLng> points = greyPolyLine.getPoints();
               int percentValue = (int) valueAnimator.getAnimatedValue();
               int size = points.size();
               int newPoints = (int) (size * (percentValue / 100.0f));
               List<LatLng> p = points.subList(0, newPoints);
               blackPolyline.setPoints(p);
           }
       });
       polylineAnimator.start();
       LatLng sydney = new LatLng(lovelat,lovlong);
       marker = mMap.addMarker(new MarkerOptions().position(sydney)
               .flat(true)
               .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
       handler = new Handler();
       index = -1;
       next = 1;
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               if (index < polyLineList.size() - 1) {
                   index++;
                   next = index + 1;
               }
               if (index < polyLineList.size() - 1) {
                   startPosition = polyLineList.get(index);
                   endPosition = polyLineList.get(next);
               }
               ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
               valueAnimator.setDuration(3000);
               valueAnimator.setInterpolator(new LinearInterpolator());
               valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                   @Override
                   public void onAnimationUpdate(ValueAnimator valueAnimator) {
                       v = valueAnimator.getAnimatedFraction();
                       lng = v * endPosition.longitude + (1 - v)
                               * startPosition.longitude;
                       lat = v * endPosition.latitude + (1 - v)
                               * startPosition.latitude;
                       LatLng newPos = new LatLng(lat, lng);
                       marker.setPosition(newPos);
                       marker.setAnchor(0.5f, 0.5f);
                       marker.setRotation(getBearing(startPosition, newPos));
                       mMap.moveCamera(CameraUpdateFactory
                               .newCameraPosition
                                       (new CameraPosition.Builder()
                                               .target(newPos)
                                               .zoom(15.5f)
                                               .build()));
                   }
               });
               valueAnimator.start();
               handler.postDelayed(this, 3000);
           }
       }, 3000);

   }

    private void createLocationRequest() {
        Log.d("dadi", "createLocationRequest");
        mLocationRequest = new LocationRequest();

      //  mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setInterval(10000);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
      /*  mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
*/
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Sets the maximum time when batched location updates are delivered. Updates may be
        // delivered sooner than this interval.
        mLocationRequest.setMaxWaitTime(MAX_WAIT_TIME);


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);


        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());


        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @SuppressLint("MissingPermission")
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...

                Toast.makeText(getBaseContext(), "All Location Settings are satisfied herer The client can initialize", Toast.LENGTH_SHORT).show();
                Location myLocation = getLastKnownLocation();
               //  System.out.println("latitude "+myLocation.getLatitude()+"  LOngitude "+myLocation.getLongitude());
                LatLng sydney = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());


                Drawable circleDrawable = getResources().getDrawable(R.drawable.greendot);
                BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);


                mMap.addMarker(new MarkerOptions().position(sydney).title("Current Location")
                        .icon(markerIcon));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.setMaxZoomPreference(17.0f);
               // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,14));


                LocationAddress locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(myLocation.getLatitude(), myLocation.getLongitude(),
                        getApplicationContext(), new GeocoderHandler());
                lovelat = myLocation.getLatitude();
                lovlong = myLocation.getLongitude();

                //drawpath(lovelat,lovlong);
            }
        });



        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MapsActivity.this,0);
                        String xx = String.valueOf(resolvable.getStatusCode());
                        Log.d("d", xx);
                        //Toast.makeText(getBaseContext(),,Toast.LENGTH_SHORT).show();
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                        ///  Toast.makeText(getBaseContext(), "cancel the dialog", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = "no value";
            }
            address_txt.setText(locationAddress);
            System.out.println("location address "+locationAddress);
            //tvAddress.setText(locationAddress);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String xx = String.valueOf(requestCode);
        String yy = String.valueOf(resultCode);
        //  Toast.makeText(getBaseContext(), "REquest code :" + xx + "  request res : " + yy, Toast.LENGTH_SHORT).show();
       // if (resultCode == 0) {
            createLocationRequest();
        //}
    }

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission")
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

   /* @SuppressLint("MissingPermission")
    private void getLastLocationNewMethod(){
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            getAddress(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }*/

    private class DataLongOperationAsynchTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(MapsActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            String response;
            try {
                response = getLatLongByURL("http://maps.google.com/maps/api/geocode/json?address="+destination.toString()+"&sensor=false");
                Log.d("response",""+response);
                return new String[]{response};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                JSONObject jsonObject = new JSONObject(result[0]);

                double lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                        .getJSONObject("geometry").getJSONObject("location")
                        .getDouble("lng");

                double lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                        .getJSONObject("geometry").getJSONObject("location")
                        .getDouble("lat");

                Log.d("latitude", "" + lat);
                Log.d("longitude", "" + lng);
                LatLng latLng = new LatLng(lat,lng);
                endPosition = latLng;
                polyLineList.add(latLng);
                createnewploy();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }


    public String getLatLongByURL(String requestURL) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public void drawpath(Double latitude,Double longitude){

       String requestUrl = null;
       try {
           requestUrl = "https://maps.googleapis.com/maps/api/directions/json?" +
                   "mode=driving&"
                   + "transit_routing_preference=less_driving&"
                   + "origin=" + latitude + "," + longitude + "&"
                   + "destination=" + destination + "&"
                   + "key=" + getResources().getString(R.string.apikey);
           Log.d("Requesturl", requestUrl);
           JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                   requestUrl, null,
                   new Response.Listener<JSONObject>() {
                       @Override
                       public void onResponse(JSONObject response) {
                           Log.d("srinivasu response ", response + "");
                           try {
                               JSONArray jsonArray = response.getJSONArray("routes");
                               for (int i = 0; i < jsonArray.length(); i++) {
                                   JSONObject route = jsonArray.getJSONObject(i);
                                   JSONObject poly = route.getJSONObject("overview_polyline");
                                   String polyline = poly.getString("points");
                                   polyLineList = decodePoly(polyline);
                                   Log.d("polylineslist dadi ", polyLineList + "");
                               }
                               //Adjusting bounds
                               LatLngBounds.Builder builder = new LatLngBounds.Builder();
                               for (LatLng latLng : polyLineList) {
                                   builder.include(latLng);
                               }
                               LatLngBounds bounds = builder.build();
                               CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 2);
                               mMap.animateCamera(mCameraUpdate);

                               polylineOptions = new PolylineOptions();
                               polylineOptions.color(Color.GRAY);
                               polylineOptions.width(5);
                               polylineOptions.startCap(new SquareCap());
                               polylineOptions.endCap(new SquareCap());
                               polylineOptions.jointType(ROUND);
                               polylineOptions.addAll(polyLineList);
                               greyPolyLine = mMap.addPolyline(polylineOptions);

                               blackPolylineOptions = new PolylineOptions();
                               blackPolylineOptions.width(5);
                               blackPolylineOptions.color(Color.BLACK);
                               blackPolylineOptions.startCap(new SquareCap());
                               blackPolylineOptions.endCap(new SquareCap());
                               blackPolylineOptions.jointType(ROUND);
                               blackPolyline = mMap.addPolyline(blackPolylineOptions);

                               mMap.addMarker(new MarkerOptions()
                                       .position(polyLineList.get(polyLineList.size() - 1)));

                               ValueAnimator polylineAnimator = ValueAnimator.ofInt(0, 100);
                               polylineAnimator.setDuration(2000);
                               polylineAnimator.setInterpolator(new LinearInterpolator());
                               polylineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                   @Override
                                   public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                       List<LatLng> points = greyPolyLine.getPoints();
                                       int percentValue = (int) valueAnimator.getAnimatedValue();
                                       int size = points.size();
                                       int newPoints = (int) (size * (percentValue / 100.0f));
                                       List<LatLng> p = points.subList(0, newPoints);
                                       blackPolyline.setPoints(p);
                                   }
                               });
                               polylineAnimator.start();
                               marker = mMap.addMarker(new MarkerOptions().position(sydney)
                                       .flat(true)
                                       .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
                               handler = new Handler();
                               index = -1;
                               next = 1;
                               handler.postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       if (index < polyLineList.size() - 1) {
                                           index++;
                                           next = index + 1;
                                       }
                                       if (index < polyLineList.size() - 1) {
                                           startPosition = polyLineList.get(index);
                                           endPosition = polyLineList.get(next);
                                       }
                                       ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                                       valueAnimator.setDuration(3000);
                                       valueAnimator.setInterpolator(new LinearInterpolator());
                                       valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                           @Override
                                           public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                               v = valueAnimator.getAnimatedFraction();
                                               lng = v * endPosition.longitude + (1 - v)
                                                       * startPosition.longitude;
                                               lat = v * endPosition.latitude + (1 - v)
                                                       * startPosition.latitude;
                                               LatLng newPos = new LatLng(lat, lng);
                                               marker.setPosition(newPos);
                                               marker.setAnchor(0.5f, 0.5f);
                                               marker.setRotation(getBearing(startPosition, newPos));
                                               mMap.moveCamera(CameraUpdateFactory
                                                       .newCameraPosition
                                                               (new CameraPosition.Builder()
                                                                       .target(newPos)
                                                                       .zoom(15.5f)
                                                                       .build()));
                                           }
                                       });
                                       valueAnimator.start();
                                       handler.postDelayed(this, 3000);
                                   }
                               }, 3000);


                           } catch (Exception e) {
                               e.printStackTrace();
                           }

                       }
                   }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Log.d("srinivasu error", error + "");
               }
           });
           RequestQueue requestQueue = Volley.newRequestQueue(this);
           requestQueue.add(jsonObjectRequest);
       } catch (Exception e) {
           e.printStackTrace();
       }

   }

    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    private float getBearing(LatLng begin, LatLng end) {
        double lat = Math.abs(begin.latitude - end.latitude);
        double lng = Math.abs(begin.longitude - end.longitude);

        if (begin.latitude < end.latitude && begin.longitude < end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)));
        else if (begin.latitude >= end.latitude && begin.longitude < end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 90);
        else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)) + 180);
        else if (begin.latitude < end.latitude && begin.longitude >= end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 270);
        return -1;
    }

 }
