package com.lopei.myapplication;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lopei.collageview.CollageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CollageView collageView = (CollageView) findViewById(R.id.collageView);

        collageView
                .photoMargin(1)
                .defaultPhotosForLine(3)
                .useFirstAsHeader(true)
                .headerForm(CollageView.ImageForm.IMAGE_FORM_SQUARE)
                .photosForm(CollageView.ImageForm.IMAGE_FORM_HALF_HEIGHT)
                .placeHolder(R.drawable.placeholder_photo)
                .loadPhotos(getDrawablesIds());
        collageView.setOnPhotoClickListener(new CollageView.OnPhotoClickListener() {
            @Override
            public void onPhotoClick(int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int[] getDrawablesIds() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.resids);
        int[] ids = new int[imgs.length()];
        for (int i = 0; i < imgs.length(); i++) {
            ids[i] = imgs.getResourceId(i, -1);
        }
        imgs.recycle();
        return ids;
    }

    private String[] getUrls() {
        return getResources().getStringArray(R.array.urls);
    }
}
