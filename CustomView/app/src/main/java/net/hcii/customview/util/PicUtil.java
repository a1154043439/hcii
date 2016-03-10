package net.hcii.customview.util;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import net.hcii.customview.entity.PicItem;

/**
 * Created by moon.zhong on 2015/2/6.
 */
public class PicUtil {
    public static List<PicItem> getPicItems(int count, Bitmap src){
        List<PicItem> list = new ArrayList<>() ;
        int width = src.getWidth() ;
        int height = src.getHeight() ;
        int picSize = Math.min(width,height) ;
        int itemSize = picSize/count ;
        for(int i = 0 ; i < count; i++){
            for(int j = 0 ;j < count; j ++){
                PicItem picItem = new PicItem() ;
                picItem.setPosition(j + i * count);
                Bitmap bitmap = Bitmap.createBitmap(src,j*itemSize,i*itemSize,itemSize,itemSize) ;
                picItem.setBitmap(bitmap);
                list.add(picItem) ;
            }
        }
        return list ;
    }
}
