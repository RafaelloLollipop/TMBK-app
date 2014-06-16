package com.example.beacontour;

import com.example.beacontour.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Fragment which displays location detais 
 * @author Adam, Antek, Rafal
 *
 */
public class DetailsFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
         
        return rootView;
    }
 
}