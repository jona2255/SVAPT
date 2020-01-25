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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svapt.Controller.FavsViewModel;
import com.svapt.Model.Favs;
import com.svapt.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    FavsViewModel favsViewModel;
    NavController navController;
    FavsAdapter favsAdapter;



    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favsViewModel = ViewModelProviders.of(requireActivity()).get(FavsViewModel.class);
        navController = Navigation.findNavController(view);

        RecyclerView favsRecyclerView = view.findViewById(R.id.recyclerview_listaFavs);
        favsRecyclerView.setLayoutManager(new LinearLayoutManager(favsRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        favsAdapter = new FavsAdapter();
        favsRecyclerView.setAdapter(favsAdapter);

        favsViewModel.listaFavs.observe(getViewLifecycleOwner(), elementos -> favsAdapter.establecerListaFavs(elementos));

    }

    public class FavsAdapter extends RecyclerView.Adapter<FavsAdapter.FavsViewHolder>{

        List<Favs> favs;

        @NonNull
        @Override
        public FavsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FavsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.last_reps_viewholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull FavsViewHolder holder, final int position) {

            final Favs favs = this.favs.get(position);

            holder.titulo.setText(favs.getTitle());
            holder.itemView.setOnClickListener(view -> {
                favsViewModel.establecerElementoSeleccionado(favs);
                navController.navigate(R.id.reproductor);
            });
        }

        @Override
        public int getItemCount() {
            return favs == null ? 0 : favs.size();
        }

        public void establecerListaFavs(List<Favs> favs){
            this.favs = favs;
            notifyDataSetChanged();
        }

        class FavsViewHolder extends RecyclerView.ViewHolder {
            TextView titulo;
            ImageView portadaImageView;

            FavsViewHolder(@NonNull View itemView) {
                super(itemView);
                portadaImageView = itemView.findViewById(R.id.imgSong);

                titulo = itemView.findViewById(R.id.title_favs);
            }
        }
    }
}
