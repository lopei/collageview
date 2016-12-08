package com.lopei.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.lopei.collageview.CollageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CollageView collageView = (CollageView) findViewById(R.id.collageView);

        collageView
                .photoMargin(1)
                .useSquarePhotos(true)
                .placeHolder(R.drawable.placeholder_photo)
                .loadPhotos(getResources().getStringArray(R.array.urls));
    }
}
