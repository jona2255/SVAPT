package com.svapt.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.svapt.R;

import es.dmoral.toasty.Toasty;

public class Reproductor extends Fragment {


    public Reproductor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_player_music_basic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton repeat = view.findViewById(R.id.bt_repeat);
        ImageButton shuffle = view.findViewById(R.id.bt_shuffle);
        ImageButton prev = view.findViewById(R.id.bt_prev);
        ImageButton next = view.findViewById(R.id.bt_next);
        ImageButton play = view.findViewById(R.id.bt_play);

        Toasty.Config.getInstance()
                .apply();

        repeat.setOnClickListener(v -> Toasty.normal(v.getContext(),"Repeat Mode",  Toast.LENGTH_SHORT).show());
        shuffle.setOnClickListener(v -> Toasty.normal(v.getContext(),"Shuffle Mode",  Toast.LENGTH_SHORT).show());
        prev.setOnClickListener(v -> Toasty.normal(v.getContext(),"Previous Song",  Toast.LENGTH_SHORT).show());
        next.setOnClickListener(v -> Toasty.normal(v.getContext(),"Next Song",  Toast.LENGTH_SHORT).show());
        play.setOnClickListener(v -> Toasty.normal(v.getContext(),"Play Song",  Toast.LENGTH_SHORT).show());


    }


}
