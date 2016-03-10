package net.hcii.customview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import net.hcii.customview.R;
import net.hcii.customview.entity.RecycleBean;

/**
 * Created by moon.zhong on 2015/2/5.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private List<RecycleBean> mRecycleBeans ;

    public RecycleAdapter(List<RecycleBean> mRecycleBeans) {
        super();
        this.mRecycleBeans = mRecycleBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null) ;
        ViewHolder viewHolder = new ViewHolder(itemView) ;
        viewHolder.mContent.setHeight(50+new Random().nextInt(100));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mIcon.setImageBitmap();


    }

    @Override
    public int getItemCount() {
        return mRecycleBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mIcon ;
        public TextView mName ;
        public TextView mContent ;

        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.id_item_icon);
            mName = (TextView) itemView.findViewById(R.id.id_item_title);
            mContent = (TextView) itemView.findViewById(R.id.id_item_content) ;
        }
    }
}
