package net.hcii.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.hcii.customview.adapter.RecycleAdapter;
import net.hcii.customview.entity.RecycleBean;
import net.hcii.customview.view.Divider;

/**
 * Created by moon.zhong on 2015/2/4.
 */
public class WeContactFragment extends Fragment {
    private View mView ;
    private RecyclerView mRecycleView ;
    private RecycleAdapter mRecycleAdapter ;
    private List<RecycleBean> mRecycleBeans ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView != null){
            return mView ;
        }
        Log.v("zgy", "========WeContentFragment====") ;
        mView = inflater.inflate(R.layout.view_we_contact, null) ;
        mRecycleBeans = new ArrayList<>() ;
        for(int i = 0; i < 20 ;i ++){
            RecycleBean recycleBean = new RecycleBean() ;
            mRecycleBeans.add(recycleBean) ;
        }
        mRecycleAdapter = new RecycleAdapter(mRecycleBeans) ;
        mRecycleView = (RecyclerView) mView.findViewById(R.id.id_contact_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2) ;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position % 3 == 0)
                    return 2 ;
                return 1;
            }
        });
        mRecycleView.addItemDecoration(new Divider());
        mRecycleView.setLayoutManager(gridLayoutManager);
        mRecycleView.setAdapter(mRecycleAdapter);
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
