package com.svapt;

import android.app.Application;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM
                },
                "",
                null,
                ""
        )) {
            List<Cancion> list = new ArrayList<>();

            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
            int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
            int artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
            int albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);

//            String durStr = dur/60 + ":" + dur%60;

            while (cursor.moveToNext()) {
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                String artist = cursor.getString(artistColumn);
                int album = cursor.getInt(albumColumn);


                Log.e("ABCD", "name = " + name);

                Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

                Cancion cancion = new Cancion(name, artist,duration);

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
