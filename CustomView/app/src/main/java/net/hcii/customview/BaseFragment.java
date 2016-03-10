package net.hcii.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import net.hcii.customview.adapter.RecycleAdapter;
import net.hcii.customview.entity.RecycleBean;

/**
 * Created by moon.zhong on 2015/2/6.
 */
public abstract class BaseFragment<K extends RecyclerView.LayoutManager> extends Fragment {

    private View mView ;
    private RecyclerView mRecycleView ;
    private RecycleAdapter mRecycleAdapter ;
    private List<RecycleBean> mRecycleBeans ;
    private K mLayoutManager ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView != null){
            return mView ;
        }
        mRecycleBeans = new ArrayList<>() ;
        for(int i = 0; i < 20 ;i ++){
            RecycleBean recycleBean = new RecycleBean() ;
            mRecycleBeans.add(recycleBean) ;
        }
        mRecycleAdapter = new RecycleAdapter(mRecycleBeans) ;
        mView = inflater.inflate(R.layout.view_base_fragment, null) ;
        mRecycleView = (RecyclerView) mView.findViewById(R.id.id_recycle_view);
        mRecycleView.setLayoutManager(getLayoutManager(getActivity()));
        mRecycleView.setAdapter(mRecycleAdapter);
        initConfig(mRecycleView) ;
        return mView;
    }

    public abstract K getLayoutManager(Context context) ;

    public abstract void initConfig(RecyclerView recyclerView) ;
}
