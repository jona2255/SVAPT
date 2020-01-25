package com.svapt.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.svapt.Model.Playlist;
import com.svapt.Controller.PlaylistViewModel;
import com.svapt.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistsFragment extends Fragment {

    PlaylistViewModel playlistViewModel;
    NavController navController;
    PlaylistAdapter playlistAdapter;

    public PlaylistsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlists, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playlistViewModel = ViewModelProviders.of(requireActivity()).get(PlaylistViewModel.class);
        navController = Navigation.findNavController(view);

        RecyclerView playlistRecyclerView = view.findViewById(R.id.recyclerview_listaPlaylist);
        playlistRecyclerView.addItemDecoration(new DividerItemDecoration(playlistRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        playlistAdapter = new PlaylistAdapter();
        playlistRecyclerView.setAdapter(playlistAdapter);

        playlistViewModel.listaPlaylist.observe(getViewLifecycleOwner(), elementos -> playlistAdapter.establecerListaPlaylist(elementos));

    }

    public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>{

        List<Playlist> playlists;

        @NonNull
        @Override
        public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PlaylistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_viewholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PlaylistViewHolder holder, final int position) {

            final Playlist playlist = playlists.get(position);

            holder.titulo.setText(playlist.getTitle());
            holder.itemView.setOnClickListener(view -> {
                playlistViewModel.establecerElementoSeleccionado(playlist);
                navController.navigate(R.id.songsFragment);
            });
        }

        @Override
        public int getItemCount() {
            return playlists == null ? 0 : playlists.size();
        }

        public void establecerListaPlaylist(List<Playlist> playlists){
            this.playlists = playlists;
            notifyDataSetChanged();
        }

        class PlaylistViewHolder extends RecyclerView.ViewHolder {
            TextView titulo;
            ImageView portadaImageView;

            PlaylistViewHolder(@NonNull View itemView) {
                super(itemView);
                portadaImageView = itemView.findViewById(R.id.imgSong);

                titulo = itemView.findViewById(R.id.title_playlist);
            }
        }
    }

}
