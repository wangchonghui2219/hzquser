package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseRecrviewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 标题栏
 */

public class ClassifyTabAdapter extends BaseRecrviewAdapter {
    private List<String> titles;
    private int tag;
    public ClassifyTabAdapter(Context ctx,List<String> titles) {
        super(ctx);
        this.titles = titles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_class_tab, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String s = titles.get(position);
        ((ViewHolder)holder).tvName.setText(s);
        if (position == tag) {
            ((ViewHolder)holder).inder.setVisibility(View.VISIBLE);
            ((ViewHolder)holder).ll_lyout.setBackgroundResource(R.color.white);
        }else{
            ((ViewHolder)holder).inder.setVisibility(View.INVISIBLE);
            ((ViewHolder)holder).ll_lyout.setBackgroundResource(R.color.classify_tab);
        }
        ((ViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {

                    onItemClickListener.setOnClick(position);


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void setTag(int position) {
        this.tag = position;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.inder)
        View inder;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_lyout)
        LinearLayout ll_lyout;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
