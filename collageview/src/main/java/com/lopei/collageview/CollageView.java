package com.lopei.collageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 08.12.16.
 */

public class CollageView extends LinearLayout {
    private List<String> urls;

    private int color = Color.TRANSPARENT;
    private int photoPadding = 0;
    private int photoMargin = 0;
    private int placeHolderResId = 0;
    private int photoFrameColor = Color.TRANSPARENT;
    private boolean useCards;


    private OnPhotoClickListener onPhotoClickListener;

    public CollageView(Context context) {
        super(context);
    }

    public CollageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CollageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CollageView backgroundColor(int color) {
        this.color = color;
        return this;
    }

    public CollageView photoPadding(int photoPadding) {
        this.photoPadding = photoPadding;
        return this;
    }

    public CollageView photoMargin(int photoMargin) {
        this.photoMargin = photoMargin;
        return this;
    }

    public CollageView photoFrameColor(int photoFrameColor) {
        this.photoFrameColor = photoFrameColor;
        return this;
    }

    public CollageView placeHolder(int resId) {
        this.placeHolderResId = resId;
        return this;
    }

    public CollageView useCards(boolean useCards) {
        this.useCards = useCards;
        return this;
    }

    public void loadPhotos(List<String> urls) {
        this.urls = urls;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setBackgroundColor(color);
        removeAllViews();
        ArrayList<String> addUrls = new ArrayList<>();
        if (urls.size() > 0) {
            int number = 0;
            int i = 0;
            while (i < urls.size()) {
                addUrls.add(urls.get(i));
                number++;
                if (number == 3 || urls.size() == i + 1) {
                    final LinearLayout photosLine = new LinearLayout(getContext());
                    photosLine.setLayoutParams(new LayoutParams(-1, -2));
                    photosLine.setOrientation(LinearLayout.HORIZONTAL);
                    photosLine.setWeightSum(6.0f);
                    for (int j = 0; j < addUrls.size(); j++) {
                        ViewGroup photoFrame;
                        if (useCards) {
                            photoFrame = new CardView(getContext());
                        } else {
                            photoFrame = new FrameLayout(getContext());
                        }
                        LayoutParams layoutParams = new LayoutParams(0, -1, (float) (6 / addUrls.size()));
                        layoutParams.setMargins(photoMargin, photoMargin, photoMargin, photoMargin);
                        photoFrame.setLayoutParams(layoutParams);
                        ImageView imageView = new ImageView(getContext());
                        imageView.setLayoutParams(new LayoutParams(-1, -1));
                        imageView.setAdjustViewBounds(true);
                        imageView.setBackgroundColor(photoFrameColor);
                        imageView.setPadding(photoPadding, photoPadding, photoPadding, photoPadding);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        final String url = addUrls.get(j);
                        photoFrame.addView(imageView);


                        try {
                            RequestCreator requestCreator =
                                    Picasso
                                            .with(getContext())
                                            .load(url);
                            if (placeHolderResId != 0) {
                                requestCreator.placeholder(placeHolderResId);
                            }
                            requestCreator.into(imageView);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        if (onPhotoClickListener != null) {
                            final int finalI = i;
                            photoFrame.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    onPhotoClickListener.onPhotoClick(finalI, url);
                                }
                            });
                        } else {
                            photoFrame.setOnClickListener(null);
                        }
                        photosLine.addView(photoFrame);
                    }
                    addView(photosLine);
                    addUrls.clear();
                    number = 0;
                }
                i++;
            }
        }
    }

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }

    public interface OnPhotoClickListener {
        void onPhotoClick(int position, String url);
    }
}
