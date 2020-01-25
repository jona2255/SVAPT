package com.svapt.Controller;

import android.app.Application;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.svapt.Model.Cancion;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Cancion>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Cancion> cancionSeleccionada = new MutableLiveData<>();

    private Application application;


    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;


    }

    //
    public LiveData<List<Cancion>> obtenerTodasLasCanciones(){


        try (Cursor cursor = application.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { // select
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                },
                "",
                null,
                ""
        )) {
            List<Cancion> list = new ArrayList<>();

            int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
            int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
            int artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);

//            String durStr = dur/60 + ":" + dur%60;

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                String artist = cursor.getString(artistColumn);


                Log.e("ABCD", "name = " + name + "artist = " + artist);


                int minutes = ((duration % (1000 * 60 * 60)) / (1000 * 60));

                int seconds = ((duration % (1000 * 60 * 60)) % (1000 * 60) / 1000);
                String secs = String.valueOf(seconds);
                if (seconds<10) secs = 0 + secs;

                String durationTrans = minutes+":"+secs;

                Cancion cancion = new Cancion(name, artist, durationTrans,duration);

                list.add(cancion);
            }

            listMutableLiveData.postValue(list);
        }

        return listMutableLiveData;
    }

    public void establecerCancionSeleccionada(Cancion cancion) {
        cancionSeleccionada.setValue(cancion);
    }
}
