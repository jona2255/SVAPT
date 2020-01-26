package com.svapt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1234;

    private AppBarConfiguration mAppBarConfiguration;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         Comprueba que tenga permisos
        aceptarPermisos();
//         Buscador, barra de abajo y menu despegable

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarApp();
            } else {
                Toasty.Config.getInstance()
                        .apply();
                Toasty.info(this, "Acepta los Permisos para poder continuar", 200000000, true).show();

                finish();
                // botonREquest.setVisibiliy(VISIBLE)
            }
        }
    }

    private void iniciarApp() {
            final Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.inicioFragment, R.id.musicFragment
            )
                    .build();

            navController = Navigation.findNavController(this, R.id.nav_host_fragment);

            final BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

            NavigationUI.setupWithNavController(bottomNavView, navController);
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                switch (destination.getId()) {
                    case R.id.reproductor:
                        toolbar.setVisibility(View.GONE);
                        bottomNavView.setVisibility(View.GONE);
                        break;
                    case R.id.helpFragment:
                        toolbar.setVisibility(View.VISIBLE);
                        bottomNavView.setVisibility(View.GONE);
                        break;
                    default:
                        toolbar.setVisibility(View.VISIBLE);
                        bottomNavView.setVisibility(View.VISIBLE);
                }
            });
        }

        void aceptarPermisos(){

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {


                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            } else {
                iniciarApp();
            }
        }


}
