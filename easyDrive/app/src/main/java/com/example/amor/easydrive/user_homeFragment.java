package com.example.amor.easydrive;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.swedspot.automotiveapi.AutomotiveSignal;
import android.swedspot.automotiveapi.AutomotiveSignalId;
import android.swedspot.scs.data.SCSFloat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;
import com.swedspot.automotiveapi.AutomotiveFactory;
import com.swedspot.automotiveapi.AutomotiveListener;
import com.swedspot.vil.distraction.DriverDistractionLevel;
import com.swedspot.vil.distraction.DriverDistractionListener;
import com.swedspot.vil.policy.AutomotiveCertificate;

/**
 * Created by Amor on 2015-03-10.
 */
public class user_homeFragment extends Fragment{

    View rootview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lay_user_home, container, false);

        final TextView fuelRate = (TextView)rootview.findViewById(R.id.displayFuelrate);
        final TextView fuelLeft = (TextView)rootview.findViewById(R.id.displayFuelleft);
        final TextView pedalPosition = (TextView)rootview.findViewById(R.id.pedalPosition);



        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... objects) {
                // Access to Automotive API
                AutomotiveFactory.createAutomotiveManagerInstance(
                        new AutomotiveCertificate(new byte[0]),
                        new AutomotiveListener() { // Listener that observes the Signals
                            @Override
                            public void receive(final AutomotiveSignal automotiveSignal) {
                                fuelLeft.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                        fuelLeft.setText(String.format("Fuel left: %.1f percent", ((SCSFloat) automotiveSignal.getData()).getFloatValue()));
                                    }
                                });
                            }

                            @Override
                            public void timeout(int i) {
                            }

                            @Override
                            public void notAllowed(int i) {
                            }
                        },
                        new DriverDistractionListener() {       // Observe driver distraction level
                            @Override
                            public void levelChanged(final DriverDistractionLevel driverDistractionLevel) {
                                fuelLeft.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                    }
                                });
                            }
                        }
                ).register(AutomotiveSignalId.FMS_FUEL_LEVEL_1);
                // Register for the speed signal
                return null;
            }
        }.execute(); // And go!


        // Fire off an async task. Networking and similar should not (cannot) happen on the UI thread
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... objects) {
                // Access to Automotive API
                AutomotiveFactory.createAutomotiveManagerInstance(
                        new AutomotiveCertificate(new byte[0]),
                        new AutomotiveListener() { // Listener that observes the Signals
                            @Override
                            public void receive(final AutomotiveSignal automotiveSignal) {
                                fuelRate.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                        fuelRate.setText(String.format("Fuel rate: %.1f litres/hour", ((SCSFloat) automotiveSignal.getData()).getFloatValue()));
                                    }
                                });
                            }

                            @Override
                            public void timeout(int i) {
                            }

                            @Override
                            public void notAllowed(int i) {
                            }
                        },
                        new DriverDistractionListener() {       // Observe driver distraction level
                            @Override
                            public void levelChanged(final DriverDistractionLevel driverDistractionLevel) {
                                fuelRate.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                    }
                                });
                            }
                        }
                ).register(AutomotiveSignalId.FMS_FUEL_RATE);
                // Register for the speed signal
                return null;
            }
        }.execute(); // And go!

// Fire off an async task. Networking and similar should not (cannot) happen on the UI thread
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... objects) {
                // Access to Automotive API
                AutomotiveFactory.createAutomotiveManagerInstance(
                        new AutomotiveCertificate(new byte[0]),
                        new AutomotiveListener() { // Listener that observes the Signals
                            @Override
                            public void receive(final AutomotiveSignal automotiveSignal) {
                                pedalPosition.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                        pedalPosition.setText(String.format("Pedal Position: %.1f percent down", ((SCSFloat) automotiveSignal.getData()).getFloatValue()));
                                    }
                                });
                            }

                            @Override
                            public void timeout(int i) {
                            }

                            @Override
                            public void notAllowed(int i) {
                            }
                        },
                        new DriverDistractionListener() {       // Observe driver distraction level
                            @Override
                            public void levelChanged(final DriverDistractionLevel driverDistractionLevel) {
                                pedalPosition.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                    }
                                });
                            }
                        }
                ).register(AutomotiveSignalId.FMS_ACCELERATOR_PEDAL_POSITION_1);
                // Register for the speed signal
                return null;
            }
        }.execute(); // And go!



        return rootview;



    }
}
