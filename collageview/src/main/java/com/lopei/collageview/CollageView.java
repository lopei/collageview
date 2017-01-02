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
    private boolean useFirstAsHeader = true;

    private int defaultPhotosForLine = 3;

    private ImageForm photosForm = ImageForm.IMAGE_FORM_SQUARE;
    private ImageForm headerForm = ImageForm.IMAGE_FORM_SQUARE;


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

    public CollageView useFirstAsHeader(boolean useFirstAsHeader) {
        this.useFirstAsHeader = useFirstAsHeader;
        return this;
    }

    public CollageView defaultPhotosForLine(int defaultPhotosForLine) {
        this.defaultPhotosForLine = defaultPhotosForLine;
        return this;
    }

    public CollageView photosForm(ImageForm photosForm) {
        this.photosForm = photosForm;
        return this;
    }

    public CollageView headerForm(ImageForm headerForm) {
        this.headerForm = headerForm;
        return this;
    }

    private void init() {
        boolean fromResources = resIds != null && urls == null;

        setOrientation(VERTICAL);
        setBackgroundColor(color);
        removeAllViews();
        ArrayList<String> addUrls = new ArrayList<>();
        ArrayList<Integer> addRes = new ArrayList<>();

        ArrayList<Integer> photosCount = buildPhotosCounts();

        if (urls != null || resIds != null) {
            int size = getPhotosSize();
            if (size > 0) {
                int number = 0;
                int i = 0;
                while (i < size) {
                    int photosInLine = photosCount.get(getChildCount());
                    if (fromResources) {
                        addRes.add(resIds[i]);
                    } else {
                        addUrls.add(urls.get(i));
                    }
                    number++;
                    if (number == photosInLine || size == i + 1) {
                        final LinearLayout photosLine = new LinearLayout(getContext());
                        photosLine.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        photosLine.setOrientation(LinearLayout.HORIZONTAL);
                        photosLine.setWeightSum(photosInLine * 1f);
                        for (int j = 0; j < photosInLine; j++) {
                            ViewGroup photoFrame;
                            if (useCards) {
                                photoFrame = new CardView(getContext());
                            } else {
                                photoFrame = new FrameLayout(getContext());
                            }
                            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
                            layoutParams.setMargins(photoMargin, photoMargin, photoMargin, photoMargin);
                            photoFrame.setLayoutParams(layoutParams);
                            ImageForm imageForm = useFirstAsHeader && i == 0 ? headerForm : photosForm;
                            ImageView imageView = new RectangleImageView(getContext(), imageForm);
                            imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setAdjustViewBounds(true);
                            imageView.setBackgroundColor(photoFrameColor);
                            imageView.setPadding(photoPadding, photoPadding, photoPadding, photoPadding);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            photoFrame.addView(imageView);


                            String url = null;
                            int resId = 0;
                            if (fromResources) {
                                resId = addRes.get(j);
                            } else {
                                url = addUrls.get(j);
                            }
                            try {
                                Picasso picasso =
                                        Picasso
                                                .with(getContext());
                                RequestCreator requestCreator = null;
                                if (fromResources) {
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

                            photoFrame.setOnClickListener(null);
                            final int finalI = i - (photosInLine - j) + 1;
                            photoFrame.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (onPhotoClickListener != null) {
                                        onPhotoClickListener.onPhotoClick(finalI);
                                    }
                                }
                            });
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

    private int getPhotosSize() {
        int size = 0;
        if (urls != null) {
            size = urls.size();
        } else if (resIds != null) {
            size = resIds.length;
        }
        return size;
    }

    private ArrayList<Integer> buildPhotosCounts() {
        int headerDecreaser = useFirstAsHeader ? 1 : 0;
        int photosSize = getPhotosSize() - headerDecreaser;
        int remainder = photosSize % defaultPhotosForLine;
        int lineCount = photosSize / defaultPhotosForLine;
        ArrayList<Integer> photosCounts = new ArrayList<>();
        if (useFirstAsHeader) {
            photosCounts.add(1);
            lineCount++;
        }
        for (int i = 0; i < lineCount; i++) {
            photosCounts.add(defaultPhotosForLine);
        }
        if (remainder >= lineCount) {
            photosCounts.add(headerDecreaser, remainder);
        } else {
            for (int i = lineCount - 1; i > lineCount - remainder - 1; i--) {
                photosCounts.set(i, photosCounts.get(i) + 1);
            }
        }
        return photosCounts;
    }

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }

    public interface OnPhotoClickListener {
        void onPhotoClick(int position);
    }


    public enum ImageForm {
        IMAGE_FORM_SQUARE(1), IMAGE_FORM_HALF_HEIGHT(2);

        private int divider = 1;

        ImageForm(int divider) {
            this.divider = divider;
        }

        public int getDivider() {
            return divider;
        }
    }
}
