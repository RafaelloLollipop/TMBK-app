package com.example.beacontour;

import com.example.beacontour.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Displays list of Beacons 
 * @author Adam
 *
 */
public class BeaconsFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_beacons, container, false);
         
        return rootView;
    }
    
    
    
}