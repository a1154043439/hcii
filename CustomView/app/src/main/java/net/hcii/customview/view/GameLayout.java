package net.hcii.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.hcii.customview.R;
import net.hcii.customview.entity.PicItem;
import net.hcii.customview.util.PicUtil;

/**
 * Created by moon.zhong on 2015/2/6.
 */
public class GameLayout extends RelativeLayout implements View.OnClickListener{
    private int mLayoutSize;
    private int mItemWidth;
    private int mMargin = 6;
    private int mPadding;
    private int mCount = 5;
    private Bitmap mBitmap;
    private List<PicItem> mPicItemList;
    private boolean mFirst = true;
    private ImageView mFirstView, mSecondView ;
    private ImageView mFirstAnim, mSecondAnim ;
    private RelativeLayout mAnimLayout ;

    private boolean isAnim = false ;

    public GameLayout(Context context) {
        this(context, null);
    }

    public GameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        initPic() ;
    }

    private void initPic() {
        mPicItemList = PicUtil.getPicItems(mCount, mBitmap);
        Collections.sort(mPicItemList,new Comparator<PicItem>() {
            @Override
            public int compare(PicItem lhs, PicItem rhs) {
                return Math.random() > 0.5 ? 1 : -1;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mFirst) {
            mLayoutSize = Math.min(getMeasuredHeight(), getMeasuredWidth());
            mPadding = min(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            mItemWidth = (mLayoutSize - mPadding * 2 - mMargin * (mCount - 1)) / mCount;
            setupView();
        }
        mFirst = false;
    }

    private void setupView() {
        ImageView[] imageViews = new ImageView[mPicItemList.size()];
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView(getContext());
            RelativeLayout.LayoutParams params = new LayoutParams(mItemWidth, mItemWidth);
            imageViews[i].setImageBitmap(mPicItemList.get(i).getBitmap());
            imageViews[i].setId(i + 10086);
            if (i % mCount == 0) {
                if (i > 0){
                    params.topMargin = mMargin ;
                    params.addRule(RelativeLayout.BELOW, imageViews[i - 1].getId());
                }
            } else {
                params.leftMargin = mMargin;
                params.addRule(RelativeLayout.ALIGN_TOP,imageViews[i - 1].getId());
                params.addRule(RelativeLayout.RIGHT_OF, imageViews[i - 1].getId());
            }
            imageViews[i].setTag(i);
            imageViews[i].setOnClickListener(this);
            imageViews[i].setLayoutParams(params);
            addView(imageViews[i]);
        }
    }

    private int min(int... param) {
        int min = param[0];
        for (int value : param) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    @Override
    public void onClick(View v) {
        if(isAnim){
            return;
        }
        if(mFirstView == null){
            mFirstView = (ImageView) v ;
            mFirstView.setColorFilter(Color.parseColor("#44000000"));
        }else {
            if(mFirstView == v){
                mFirstView.setColorFilter(null);
                mFirstView = null ;
                return;
            }else {
                mSecondView = (ImageView) v ;
                int tagFirst = (Integer)mFirstView.getTag() ;
                int tagSecond = (Integer)mSecondView.getTag() ;
                mSecondView.setColorFilter(Color.parseColor("#44000000"));
                setAnimLayout() ;
                setAnimation(tagFirst,tagSecond) ;
            }
        }
    }

    private void setAnimation(final int first, final int second){
        isAnim = true ;
        mFirstAnim = new ImageView(getContext()) ;
        LayoutParams firstParams = new LayoutParams(mItemWidth,mItemWidth) ;
        firstParams.leftMargin = mFirstView.getLeft() - mPadding;
        firstParams.topMargin = mFirstView.getTop() - mPadding;
        mFirstAnim.setImageBitmap(mPicItemList.get(first).getBitmap());
        mFirstAnim.setLayoutParams(firstParams);

        TranslateAnimation firstTransAnim = new TranslateAnimation(0,mSecondView.getLeft() - mFirstView.getLeft(),0,mSecondView.getTop() - mFirstView.getTop()) ;
        firstTransAnim.setDuration(200);
        firstTransAnim.setFillAfter(true);
        firstTransAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mFirstView.setVisibility(INVISIBLE);
                mSecondView.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mFirstView.setImageBitmap(mPicItemList.get((second)).getBitmap());
                mSecondView.setImageBitmap(mPicItemList.get(first).getBitmap());
                mFirstView.setTag(second);
                mSecondView.setTag(first);
                mFirstView.setColorFilter(null);
                mSecondView.setColorFilter(null);
                mFirstView.setVisibility(VISIBLE);
                mSecondView.setVisibility(VISIBLE);
                mFirstView = null;
                mSecondView = null;
                removeView(mAnimLayout);
                isAnim = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mAnimLayout.addView(mFirstAnim);
        mFirstAnim.startAnimation(firstTransAnim);

        mSecondAnim = new ImageView(getContext()) ;
        LayoutParams secondParams = new LayoutParams(mItemWidth,mItemWidth) ;
        secondParams.leftMargin = mSecondView.getLeft() - mPadding ;
        secondParams.topMargin = mSecondView.getTop() - mPadding;
        mSecondAnim.setImageBitmap(mPicItemList.get(second).getBitmap());
        mSecondAnim.setLayoutParams(secondParams);

        TranslateAnimation secondTransAnim = new TranslateAnimation(0,mFirstView.getLeft()-mSecondView.getLeft(),0,mFirstView.getTop()-mSecondView.getTop()) ;
        secondTransAnim.setDuration(200);
        secondTransAnim.setFillAfter(true);
        secondTransAnim.start();
        mAnimLayout.addView(mSecondAnim);
        mSecondAnim.startAnimation(secondTransAnim);
    }

    private void setAnimLayout(){
        mAnimLayout = new RelativeLayout(getContext()) ;
        int animWidth = getMeasuredWidth();
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(animWidth,animWidth) ;
        mAnimLayout.setLayoutParams(layoutParams);
        addView(mAnimLayout);
    }
}
