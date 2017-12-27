package com.dlwx.hzquser.view.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.ClassifyTabAdapter;
import com.dlwx.hzquser.adapter.MeachProductAdapter;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.base.BaseRecrviewAdapter;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.HomeNewProduct;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.activity.MenuDetailsActivity;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分类商品
 */
public class ClassifyProductFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.tablayout)
    RecyclerView tablayout;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.lv_list)
    ListView lvList;
    Unbinder unbinder1;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<String> titles = new ArrayList<>();
    List<Fragment> fragments;
    private ClassifyTabAdapter tabAdapter;
    private String typeId;
    private MeachProductAdapter adapter;
    private List<HomeNewProduct.BodyBean.ListBean> list = new ArrayList<>();

    @Override
    public int getLayoutID() {
        return R.layout.fragment_classify_product;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadmore(true);//是否启用列表惯性滑动到底部时自动加载更多

    }
    private List<String> typeIds = new ArrayList<>();
    @Override
    protected void initDate() {

       typeIds = MyApplication.typeId;
        titles = MyApplication.typeName;
        LinearLayoutManager manager = new LinearLayoutManager(ctx, LinearLayout.VERTICAL,false);
        tablayout.setLayoutManager(manager);
        tabAdapter = new ClassifyTabAdapter(ctx,titles);
        tablayout.setAdapter(tabAdapter);
        adapter = new MeachProductAdapter(ctx, list);
        lvList.setAdapter(adapter);
        getClassList();
        typeId = "A";
        Httptype = 2;
        getKindData();
    }
    private int page = 1;
    @Override
    protected void initListener() {
        tabAdapter.setOnItemClickListener(new BaseRecrviewAdapter.OnItemClickListener() {
            @Override
            public void setOnClick(int position) {
                list.clear();
                tabAdapter.setTag(position);
                tvName.setText(titles.get(position));
                Httptype = 2;
                if (position <3) {
                    getKindData();
                }


                if (position == 3) {
                    typeId = "d";
                    getNew();
                }else{

                    page = 1;
                    typeId = typeIds.get(position);
                    getOtherData();
                }

            }
        });
        refreshLayout.setRefreshHeader(new FalsifyHeader(ctx));
        refreshLayout.setRefreshFooter(new FalsifyFooter(ctx));

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                wch("wwwwww");
                refreshlayout.finishLoadmore();
                if (typeId.equals("d")) {

                } else {
                    page++;
                    getOtherData();

                }
            }
        });
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ctx,MenuDetailsActivity.class);
                intent.putExtra("menu_id",list.get(position).getMenu_id());
                startActivity(intent);
            }
        });
    }



    private void getNew() {
        mPreenter.fetch(new HashMap<String, String>(), true, HttpUtiles.Home_NewProduct, "meach_NewProduct");
    }
    private void getKindData() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("kind",typeId);
        map.put("page",page+"");
        map.put("num",20+"");
        mPreenter.fetch(map,true, HttpUtiles.Seach_Center,"meach"+typeId);
    }
    private void getOtherData(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("type_id",typeId);
        map.put("page",page+"");
        map.put("num",20+"");
        mPreenter.fetch(map,true, HttpUtiles.Seach_Center,"meach"+typeId);
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
    private int Httptype;
    private void getClassList() {
        Httptype = 1;
//        mPreenter.fetch(new HashMap<String, String>(), true, HttpUtiles.Home_Banner, "meach_banner");
    }

    @Override
    public void showData(String s) {
        wch(s);
        loading.dismiss();
        Gson gson = new Gson();
       if (Httptype == 2) {
            HomeNewProduct homeNewProduct = gson.fromJson(s, HomeNewProduct.class);
            if (homeNewProduct.getCode() == 200) {
                HomeNewProduct.BodyBean body = homeNewProduct.getBody();
                list.addAll(body.getList());
               adapter.notifyDataSetChanged();
            }else{
                Toast.makeText(ctx, homeNewProduct.getResult(), Toast.LENGTH_SHORT).show();
            }
        }else{

       }
    }
}
