package com.svapt.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.svapt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReproductorFragment extends Fragment {


    public ReproductorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_player_music_basic, container, false);
    }

}
