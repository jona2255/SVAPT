package com.svapt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DetailSongFragment extends Fragment {

    MainViewModel mainViewModel;

    TextView tituloTextView, artistaTextView, duracionTextView;

    public DetailSongFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_song, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        tituloTextView = view.findViewById(R.id.title);
        artistaTextView = view.findViewById(R.id.artist);
        duracionTextView = view.findViewById(R.id.duration);

        mainViewModel.cancionSeleccionada.observe(getViewLifecycleOwner(), new Observer<Cancion>() {
            @Override
            public void onChanged(final Cancion cancion) {
                if(cancion == null) return;

                tituloTextView.setText(cancion.titulo);
                artistaTextView.setText(cancion.artista);
                duracionTextView.setText(cancion.duracionTrans);
            }
        });
    }

}
