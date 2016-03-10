package net.hcii.customview.entity;

import android.graphics.Bitmap;

/**
 * Created by moon.zhong on 2015/2/6.
 */
public class PicItem {

    private Bitmap bitmap ;

    private int position ;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
