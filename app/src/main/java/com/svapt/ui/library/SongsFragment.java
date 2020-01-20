package com.svapt.ui.library;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.svapt.Cancion;
import com.svapt.MainViewModel;
import com.svapt.R;

import java.util.List;



public class SongsFragment extends Fragment {

    MainViewModel mainViewModel;
    NavController navController;

    SongsAdapter songsAdapter;

    public SongsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_songs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        RecyclerView cancionesRecyclerView = view.findViewById(R.id.recyclerview_listaElementos);
        cancionesRecyclerView.addItemDecoration(new DividerItemDecoration(cancionesRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        songsAdapter = new SongsAdapter();
        cancionesRecyclerView.setAdapter(songsAdapter);

        mainViewModel.obtenerTodasLasCanciones().observe(getViewLifecycleOwner(), new Observer<List<Cancion>>() {
            @Override
            public void onChanged(List<Cancion> canciones) {
                for(Cancion cancion: canciones) {
                    songsAdapter.establecerListaCanciones(canciones);                }
            }
        });
    }


    class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongViewHolder>{

        List<Cancion> canciones;

        @NonNull
        @Override
        public SongsAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cancion_viewholder, parent, false));
        }


        @Override
        public void onBindViewHolder(@NonNull SongViewHolder holder, final int position) {

            final Cancion cancion = canciones.get(position);

            holder.tituloTextView.setText(cancion.titulo);
            holder.artistaTextView.setText(cancion.artista);
            holder.duracionTextView.setText(String.valueOf(cancion.duracion));
            Glide.with(requireActivity()).load(R.drawable.vinylo).into(holder.portadaImageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainViewModel.establecerCancionSeleccionada(cancion);
                    navController.navigate(R.id.inicioFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return canciones == null ? 0 : canciones.size();
        }

        public void establecerListaCanciones(List<Cancion> elementos){
            this.canciones = elementos;
            notifyDataSetChanged();
        }

        class SongViewHolder extends RecyclerView.ViewHolder {
            ImageView portadaImageView;
            TextView tituloTextView, artistaTextView, duracionTextView;

            public SongViewHolder(@NonNull View itemView) {
                super(itemView);
                portadaImageView = itemView.findViewById(R.id.imgSong);
                tituloTextView = itemView.findViewById(R.id.title);
                artistaTextView = itemView.findViewById(R.id.artist);
                duracionTextView = itemView.findViewById(R.id.duration);
            }
        }
    }



}
