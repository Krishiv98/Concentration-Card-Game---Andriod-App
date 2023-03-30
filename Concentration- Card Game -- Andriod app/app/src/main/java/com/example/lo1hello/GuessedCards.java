package com.example.lo1hello;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

public class GuessedCards {
    private ImageView imgId;

    public View getOriginalView() {
        return originalView;
    }

    public void setOriginalView(View originalView) {
        this.originalView = originalView;
    }

    private View originalView;
    private int drawableId;
    private boolean blFlipped = false;

    public boolean isBlFlipped() {
        return blFlipped;
    }

    public void setBlFlipped(boolean blFlipped) {
        this.blFlipped = blFlipped;
    }



    public GuessedCards(ImageView imgId, int drawableId)
    {
        this.imgId = imgId;
        this.drawableId = drawableId;
    }

    public void flip()
    {
        imgId.setImageResource(R.drawable.b);
    }

    public ImageView getImgId() {
        return imgId;
    }

    public void setImgId(ImageView imgId) {
        this.imgId = imgId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
