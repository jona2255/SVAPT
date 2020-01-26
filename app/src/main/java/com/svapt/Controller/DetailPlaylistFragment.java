package com.svapt.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.svapt.R;

public class DetailPlaylistFragment extends Fragment {

    PlaylistViewModel playlistViewModel;

    TextView tituloTextView;

    public DetailPlaylistFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.playlist_viewholder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playlistViewModel = ViewModelProviders.of(requireActivity()).get(PlaylistViewModel.class);

        tituloTextView = view.findViewById(R.id.title_playlist);

        playlistViewModel.elementoSeleccionado.observe(getViewLifecycleOwner(), playlist -> {
            if(playlist == null) return;

            tituloTextView.setText(playlist.getTitle());

        });
    }
}
