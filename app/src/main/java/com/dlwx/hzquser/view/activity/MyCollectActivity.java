package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.MyCollectAdapter;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.CollectBean;
import com.dlwx.hzquser.model.bean.MyCOllectBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.google.gson.Gson;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的收藏
 */
public class MyCollectActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)



    TextView tvRight;
    @BindView(R.id.rev_view)
    SwipeMenuRecyclerView recyclerView;
    private MyCollectAdapter collectAdapter;
    private List<MyCOllectBean.BodyBean> body = new ArrayList<>();
    private int menuPosition;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
            initTabBar(toolbar);
            titleTxt.setText("我的收藏");
        getData();
    }



    @Override
    protected void initListener() {
        LinearLayoutManager manager = new LinearLayoutManager(ctx, LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyCOllectBean.BodyBean bodyBean = body.get(position);
                String menu_id = bodyBean.getMenu_id();
                Intent intent = new Intent(ctx,MenuDetailsActivity.class);
                intent.putExtra("menu_id",menu_id);
                startActivity(intent);
            }
        });

    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    private void getData() {
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.MyCollect,"MyCollect",true);

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();

        if (HttpType == 1) {


        MyCOllectBean myCOllectBean = gson.fromJson(s, MyCOllectBean.class);
        if (myCOllectBean.getCode() == 200) {

            body = myCOllectBean.getBody();
            collectAdapter = new MyCollectAdapter(ctx,body);
            recyclerView.setAdapter(collectAdapter);
        }else{
            Toast.makeText(ctx, myCOllectBean.getResult(), Toast.LENGTH_SHORT).show();
            
        } }else{
            CollectBean resultBean = gson.fromJson(s, CollectBean.class);
            if (resultBean.getCode() == 200) {
                body.remove(menuPosition);
                collectAdapter.notifyDataSetChanged();
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }

    }
    private int HttpType;

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.delete);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;


            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(ctx)
                        .setBackground(R.color.red)
                        .setImage(R.drawable.ic_delete_sweep_black_24dp)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

//                SwipeMenuItem addItem = new SwipeMenuItem(ctx)
//                        .setBackground(R.color.red)
//                        .setText("添加")
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };


    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            // 菜单在RecyclerView的Item中的Position。
            menuPosition = menuBridge.getPosition();

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                HttpType = 2;
                Map<String,String> map = new HashMap<>();
                map.put("token",MyApplication.User_Token);
                map.put("menu_id",body.get(menuPosition).getMenu_id());
                mPreenter.fetch(map,true,HttpUtiles.Collect_Product,"",false);
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(ctx, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

}
