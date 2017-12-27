package com.dlwx.hzquser.view.fragment;


import android.content.Intent;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.HomeClassAdapter;
import com.dlwx.hzquser.adapter.HomeNewProductAdapter;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.base.BaseRecrviewAdapter;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.HomeBannerClass;
import com.dlwx.hzquser.model.bean.HomeNewProduct;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.SetBanner;
import com.dlwx.hzquser.view.MyListView;
import com.dlwx.hzquser.view.activity.MenuDetailsActivity;
import com.dlwx.hzquser.view.activity.SeachActivity;
import com.dlwx.hzquser.view.activity.SeachCenterActivity;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rl_seach)
    RelativeLayout rlSeach;
    @BindView(R.id.gv_list)
    RecyclerView gvList;
    @BindView(R.id.iv_speoff)
    ImageView ivSpeoff;
    @BindView(R.id.btn_golook)
    Button btnGolook;
    @BindView(R.id.ll_hot)
    LinearLayout llHot;
    @BindView(R.id.ll_dis)
    LinearLayout llDis;
    @BindView(R.id.lv_list)
    MyListView lvList;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<HomeBannerClass.BodyBean.BannerBean> bannerBean;
    private Palette.Swatch swatch;
    private int pixel;
    private List<HomeNewProduct.BodyBean.ListBean> new_menus;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadmore(true);//是否启用列表惯性滑动到底部时自动加载更多
        getBanner();
    }
    @Override
    protected void initListener() {
        //设置 Header 为 Material风格
//        refreshLayout.setRefreshHeader(new FalsifyHeader(ctx));
        refreshLayout.setRefreshFooter(new FalsifyFooter(ctx));
        refreshLayout.setRefreshHeader(new WaterDropHeader(ctx));
        //设置 Footer 为 球脉冲
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                getBanner();
            }
        });

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeNewProduct.BodyBean.ListBean newMenusBean = new_menus.get(position);
                String menu_id = newMenusBean.getMenu_id();
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_seach, R.id.btn_golook, R.id.ll_hot, R.id.ll_dis})
    public void onViewClicked(View view) {
        Intent intent ;
        switch (view.getId()) {
            case R.id.rl_seach:
                startActivity(new Intent(ctx,SeachActivity.class));
                break;
            case R.id.btn_golook:// 今日特价
                intent = new Intent(ctx,SeachCenterActivity.class);
                intent.putExtra("type_id","A");
                intent.putExtra("tit_name","今日特价");
                startActivity(intent);
                break;
            case R.id.ll_hot:
                intent = new Intent(ctx,SeachCenterActivity.class);
                intent.putExtra("type_id","C");
                intent.putExtra("tit_name","火爆美食");
                startActivity(intent);
                break;
            case R.id.ll_dis:
                intent = new Intent(ctx,SeachCenterActivity.class);
                intent.putExtra("type_id","B");
                intent.putExtra("tit_name","优惠多多");
                startActivity(intent);
                break;
        }
    }
    private List<String> typeIds = new ArrayList<>();
    private List<String> typeNames = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    @Override
    public void showData(String s) {
        wch(s);
        Gson gson = new Gson();
        if (getDatatype == 1) {
            wch("轮播"+s);
            HomeBannerClass homeBannerClass = gson.fromJson(s, HomeBannerClass.class);
            if (homeBannerClass.getCode() == 200) {
                HomeBannerClass.BodyBean body = homeBannerClass.getBody();
                bannerBean = body.getBanner();
                //轮播图
                list.clear();
                for (int i = 0; i < bannerBean.size(); i++) {
                    list.add(bannerBean.get(i).getPath());
                }
                SetBanner.startBanner(ctx, banner, list);
                final List<HomeBannerClass.BodyBean.ClassifyBean> classify = body.getClassify();
                GridLayoutManager manager = new GridLayoutManager(ctx, 4);
                gvList.setLayoutManager(manager);
                typeIds.add("A");
                typeIds.add("B");
                typeIds.add("C");
                typeIds.add("D");
                typeNames.add("火爆美食");
                typeNames.add("今日特价");
                typeNames.add("优惠多多");
                typeNames.add("最新上架");
                for (int i = 0; i < classify.size(); i++) {


                        typeIds.add(classify.get(i).getType_id());
                        typeNames.add(classify.get(i).getType_name());

                }
                MyApplication.setClass(typeIds);
                MyApplication.setClassName(typeNames);
                HomeClassAdapter homeClassAdapter = new HomeClassAdapter(ctx, classify);
                gvList.setAdapter(homeClassAdapter);
                homeClassAdapter.setOnItemClickListener(new BaseRecrviewAdapter.OnItemClickListener() {
                    @Override
                    public void setOnClick(int position) {
                        Intent intent = new Intent(ctx, SeachCenterActivity.class);
                        intent.putExtra("type_id",classify.get(position).getType_id());
                        intent.putExtra("tit_name",classify.get(position).getType_name());
                        startActivity(intent);
                    }
                });

            } else {
                Toast.makeText(ctx, homeBannerClass.getResult(), Toast.LENGTH_SHORT).show();
            }
            getDatatype = 0;
            mPreenter.fetch(new HashMap<String, String>(), true, HttpUtiles.Home_NewProduct, "Home_NewProduct");
        } else {//获取到了新品上架的数据
            wch("新品"+s);
            loading.dismiss();
            HomeNewProduct homeNewProduct = gson.fromJson(s, HomeNewProduct.class);
            if (homeNewProduct.getCode() == 200) {
                HomeNewProduct.BodyBean body = homeNewProduct.getBody();
                new_menus = body.getList();
                HomeNewProductAdapter homeNewProductAdapter = new HomeNewProductAdapter(ctx, new_menus);
                lvList.setAdapter(homeNewProductAdapter);
            }
        }
    }

    private int getDatatype = 0;//0是全部数据已经获取 1是获取了轮播和分类数据

    /**
     * 获取banner图和分类
     */
    private void getBanner() {
        mPreenter.fetch(new HashMap<String, String>(), true, HttpUtiles.Home_Banner, "Home_banner");
        getDatatype = 1;
    }

    }
