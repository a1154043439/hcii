package net.hcii.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.hcii.customview.view.Divider;

/**
 * Created by moon.zhong on 2015/2/4.
 */
public class WeMineFragment extends BaseFragment<GridLayoutManager> {

    @Override
    public GridLayoutManager getLayoutManager(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3) ;
        return gridLayoutManager;
    }

    @Override
    public void initConfig(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new Divider());
    }
}
