package com.lopei.collageview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.lopei.collageview.CollageView.ImageForm;

/**
 * Created by alan on 08.12.16.
 */

public class RectangleImageView extends AppCompatImageView {
    private ImageForm imageForm = ImageForm.IMAGE_FORM_SQUARE;

    public RectangleImageView(Context context, ImageForm imageForm) {
        super(context);
        this.imageForm = imageForm;
    }

    public RectangleImageView(Context context, AttributeSet attrs, ImageForm imageForm) {
        super(context, attrs);
        this.imageForm = imageForm;
    }

    public RectangleImageView(Context context, AttributeSet attrs, ImageForm imageForm, int defStyle) {
        super(context, attrs, defStyle);
        this.imageForm = imageForm;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getParent() != null) {
            getLayoutParams().height = widthMeasureSpec / imageForm.getDivider();
            setMeasuredDimension(widthMeasureSpec, widthMeasureSpec / imageForm.getDivider());
        }
    }
}