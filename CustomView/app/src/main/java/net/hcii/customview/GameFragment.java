package net.hcii.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import net.hcii.customview.R;
import net.hcii.customview.entity.PicItem;
import net.hcii.customview.util.PicUtil;

/**
 * Created by moon.zhong on 2015/2/6.
 */
public class GameFragment extends Fragment {
    private View mView ;
    private LinearLayout mContainer ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView != null){
            return mView ;
        }
        mView = inflater.inflate(R.layout.view_game,null) ;
//        mContainer = (LinearLayout) mView.findViewById(R.id.id_container);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sunli1) ;
//        List<PicItem> list = PicUtil.getPicItems(3,bitmap) ;
//        for(int i = 0 ; i < list.size() ; i ++){
//            ImageView imageView = new ImageView(getActivity()) ;
//            imageView.setImageBitmap(list.get(i).getBitmap());
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) ;
//            imageView.setLayoutParams(layoutParams);
//            mContainer.addView(imageView);
//        }
        return mView;
    }
}
