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
import java.util.Arrays;
import java.util.List;

/**
 * Created by alan on 08.12.16.
 */

public class CollageView extends LinearLayout {
    private List<String> urls;
    private int[] resIds;

    private int color = Color.TRANSPARENT;
    private int photoPadding = 0;
    private int photoMargin = 0;
    private int placeHolderResId = 0;
    private int photoFrameColor = Color.TRANSPARENT;
    private boolean useCards = false;
    private boolean useSquarePhotos = false;


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

    public CollageView useSquarePhotos(boolean useSquarePhotos) {
        this.useSquarePhotos = useSquarePhotos;
        return this;
    }

    public void loadPhotos(String[] urls) {
        this.urls = new ArrayList<>(Arrays.asList(urls));
        init();
    }

    public void loadPhotos(int[] resIds) {
        this.resIds = resIds;
        init();
    }

    public void loadPhotos(List<String> urls) {
        this.urls = urls;
        init();
    }

    private void init() {
        boolean fromResourses = resIds != null && urls == null;

        setOrientation(VERTICAL);
        setBackgroundColor(color);
        removeAllViews();
        ArrayList<String> addUrls = new ArrayList<>();
        ArrayList<Integer> addRes = new ArrayList<>();
        if (urls != null || resIds != null) {
            int size = 0;
            if (urls != null) {
                size = urls.size();
            } else if (resIds != null) {
                size = resIds.length;
            }
            if (size > 0) {
                int number = 0;
                int i = 0;
                while (i < size) {
                    if (fromResourses) {
                        addRes.add(resIds[i]);
                    } else {
                        addUrls.add(urls.get(i));
                    }
                    number++;
                    if (number == 3 || size == i + 1) {
                        int addSize = 0;
                        if (urls != null) {
                            addSize = addUrls.size();
                        } else if (resIds != null) {
                            addSize = addRes.size();
                        }
                        final LinearLayout photosLine = new LinearLayout(getContext());
                        photosLine.setLayoutParams(new LayoutParams(-1, -2));
                        photosLine.setOrientation(LinearLayout.HORIZONTAL);
                        photosLine.setWeightSum(6.0f);
                        for (int j = 0; j < addSize; j++) {
                            ViewGroup photoFrame;
                            if (useCards) {
                                photoFrame = new CardView(getContext());
                            } else {
                                photoFrame = new FrameLayout(getContext());
                            }
                            LayoutParams layoutParams = new LayoutParams(0, -1, (float) (6 / addSize));
                            layoutParams.setMargins(photoMargin, photoMargin, photoMargin, photoMargin);
                            photoFrame.setLayoutParams(layoutParams);
                            ImageView imageView;
                            if (useSquarePhotos) {
                                imageView = new SquareImageView(getContext());
                            } else {
                                imageView = new ImageView(getContext());
                            }
                            imageView.setLayoutParams(new LayoutParams(-1, -1));
                            imageView.setAdjustViewBounds(true);
                            imageView.setBackgroundColor(photoFrameColor);
                            imageView.setPadding(photoPadding, photoPadding, photoPadding, photoPadding);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            photoFrame.addView(imageView);


                            String url = null;
                            int resId = 0;
                            if (fromResourses) {
                                resId = addRes.get(j);
                            } else {
                                url = addUrls.get(j);
                            }
                            try {
                                Picasso picasso =
                                        Picasso
                                                .with(getContext());
                                RequestCreator requestCreator = null;
                                if (fromResourses) {
                                    if (resId != 0) {
                                        requestCreator = picasso.load(resId);
                                    }
                                } else {
                                    if (url != null) {
                                        requestCreator = picasso.load(url);
                                    }
                                }
                                if (requestCreator != null) {
                                    if (placeHolderResId != 0) {
                                        requestCreator.placeholder(placeHolderResId);
                                    }
                                    requestCreator.into(imageView);
                                }
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                            if (onPhotoClickListener != null) {
                                final int finalI = i;
                                photoFrame.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        onPhotoClickListener.onPhotoClick(finalI);
                                    }
                                });
                            } else {
                                photoFrame.setOnClickListener(null);
                            }
                            photosLine.addView(photoFrame);
                        }
                        addView(photosLine);
                        addUrls.clear();
                        addRes.clear();
                        number = 0;
                    }
                    i++;
                }
            }
        }
    }

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }

    public interface OnPhotoClickListener {
        void onPhotoClick(int position);
    }
}
