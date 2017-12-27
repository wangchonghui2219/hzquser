package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.SeachCenterAdapter;
import com.dlwx.hzquser.adapter.SeachPopuAdapter;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.model.bean.SeachCenterBean;
import com.dlwx.hzquser.model.bean.SeachMenuBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.MyPopuWindow;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.hzquser.R.id.tv_tao;

/**
 * 搜索中心 分类
 */
public class SeachCenterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(tv_tao)
    TextView tvTao;
    @BindView(R.id.iv_tao)
    ImageView ivTao;
    @BindView(R.id.view_tao)
    View viewTao;
    @BindView(R.id.ll_tao)
    LinearLayout llTao;
    @BindView(R.id.tv_sale)
    TextView tvSale;
    @BindView(R.id.view_sale)
    View viewSale;
    @BindView(R.id.ll_sale)
    LinearLayout llSale;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_price)
    ImageView ivPrice;
    @BindView(R.id.view_price)
    View viewPrice;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.lv_list)
    ListView listView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_popu)
    LinearLayout llpopu;
    private String type_id;//分类id
    private int page = 1;
    private String sort = "";//排序
    private List<SeachCenterBean.BodyBean.ListBean> list = new ArrayList<>();
    private SeachCenterAdapter adapter;
    private int type = 0;
    private List<SeachMenuBean.BodyBean.ClassifyBean> classify;
    private String kind;
    private String search;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        type_id = intent.getStringExtra("type_id");
        String tit_name = intent.getStringExtra("tit_name");
        search = intent.getStringExtra("seach");
        setContentView(R.layout.activity_seach_center);
        ButterKnife.bind(this);
        initTabBar(toolbar);
        if (type_id != null) {

            if (type_id.equals("A")||type_id.equals("B")||type_id.equals("C")) {
                llTao.setVisibility(View.GONE);
                kind = type_id;
                type_id = "";
            }
        }

        titleTxt.setText("全部");

        if (tit_name != null) {
            titleTxt.setText(tit_name);

            tvTao.setText(tit_name);
        }

        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadmore(true);//是否启用列表惯性滑动到底部时自动加载更多
    }
    @Override
    protected void initData() {
        adapter = new SeachCenterAdapter(ctx,list);
        listView.setAdapter(adapter);
        if (kind == null) {

            getData();
        }else{
            getKindData();
        }
    }
    @Override
    protected void initListener() {
        refreshLayout.setRefreshHeader(new WaterDropHeader(ctx));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new FalsifyFooter(ctx));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                page = 1;
                search = "";
                list.clear();
                getData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                wch("wwwwww");
                refreshlayout.finishLoadmore();
                page++;
                getData();
            }
        });

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(ctx,MenuDetailsActivity.class);
               intent.putExtra("menu_id",list.get(position).getMenu_id());
               startActivity(intent);
           }
       });

    }
    private List<String> menuClass = new ArrayList<>();
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (type == 1) {//菜单分类
            type = 0;
            SeachMenuBean seachMenuBean = gson.fromJson(s, SeachMenuBean.class);
            if (seachMenuBean.getCode() == 200) {
                SeachMenuBean.BodyBean body = seachMenuBean.getBody();
                classify = body.getClassify();
                menuClass.clear();
                menuClass.add("全部");
                for (int i = 0; i < classify.size(); i++) {
                   menuClass.add(classify.get(i).getType_name());
                }
                showPopu();
            }
        }else {
        SeachCenterBean seachCenterBean = gson.fromJson(s, SeachCenterBean.class);
        if (seachCenterBean.getCode() == 200) {
            SeachCenterBean.BodyBean body = seachCenterBean.getBody();
            if (body.getList().size()==0) {
                page--;
            }
            list.addAll(body.getList());
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(ctx, seachCenterBean.getResult(), Toast.LENGTH_SHORT).show();
        }
        }
    }
    private void getKindData() {
        Map<String,String> map = new HashMap<>();
        map.put("kind",kind);
        map.put("page",page+"");
        map.put("num",20+"");
        map.put("sort",sort);
        map.put("search",search);
        mPreenter.fetch(map,true, HttpUtiles.Seach_Center,"seach_center"+type_id);
    }
    private void getData() {
        wch(page);
        Map<String,String> map = new HashMap<>();
        map.put("type_id",type_id);
        map.put("page",page+"");
        map.put("num",20+"");
        map.put("sort",sort);
        map.put("search",search);
        mPreenter.fetch(map,true, HttpUtiles.Seach_Center,"seach_center"+type_id);
    }
    private int sortType = 0;
    @OnClick({R.id.ll_tao, R.id.ll_sale, R.id.ll_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tao:
                tvPrice.setTextColor(getResources().getColor(R.color.black_color));
                Glide.with(ctx).load(R.mipmap.icon_fenleia).into(ivPrice);
                viewPrice.setBackgroundResource(R.color.gary_line);

                tvSale.setTextColor(getResources().getColor(R.color.black_color));
                viewSale.setBackgroundResource(R.color.gary_line);

                tvTao.setTextColor(getResources().getColor(R.color.orange));
                Glide.with(ctx).load(R.mipmap.icon_fenlei).into(ivTao);
                viewTao.setBackgroundResource(R.color.orange);
                sortType = 1;
                getMenuClass();
                break;
            case R.id.ll_sale:
                tvTao.setTextColor(getResources().getColor(R.color.black_color));
                Glide.with(ctx).load(R.mipmap.icon_fenleia).into(ivTao);
                viewTao.setBackgroundResource(R.color.gary_line);

                tvPrice.setTextColor(getResources().getColor(R.color.black_color));
                Glide.with(ctx).load(R.mipmap.icon_fenleia).into(ivPrice);
                viewPrice.setBackgroundResource(R.color.gary_line);

                tvSale.setTextColor(getResources().getColor(R.color.orange));
                viewSale.setBackgroundResource(R.color.orange);
                list.clear();
                page = 1;
                sort = "1";
                getData();
                break;
            case R.id.ll_price:
                tvTao.setTextColor(getResources().getColor(R.color.black_color));
                Glide.with(ctx).load(R.mipmap.icon_fenleia).into(ivTao);
                viewTao.setBackgroundResource(R.color.gary_line);

                tvSale.setTextColor(getResources().getColor(R.color.black_color));
                viewSale.setBackgroundResource(R.color.gary_line);

                tvPrice.setTextColor(getResources().getColor(R.color.orange));
                Glide.with(ctx).load(R.mipmap.icon_fenlei).into(ivPrice);
                viewPrice.setBackgroundResource(R.color.orange);
                sortType = 2;
                menuClass.clear();
                menuClass.add("全部");
                menuClass.add("价格从低到高");
                menuClass.add("价格从高到低");
                showPopu();
                break;
        }
    }
    /**
     * 获取菜单分类
     */
    private void getMenuClass() {
            type = 1;
            mPreenter.fetch(new HashMap<String, String>(),true,HttpUtiles.MenuClass,"menuclass");

    }

    private void showPopu(){
        final View view = LayoutInflater.from(ctx).inflate(R.layout.popu_seach,null,false);
        final MyPopuWindow popu = new MyPopuWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        GridView gv_list = (GridView) view.findViewById(R.id.gv_list);
        ColorDrawable drawaable = new ColorDrawable(0x77000000);
        popu.setBackgroundDrawable(drawaable);
        popu.setFocusable(true);
        popu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isshow = false;
            }
        });
        //popuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height) {
                        popu.dismiss();
                    }
                }
                return true;
            }
        });
        if (sortType == 2) {
            gv_list.setNumColumns(3);
        }
        final SeachPopuAdapter seachPopuAdapter = new SeachPopuAdapter(ctx, menuClass);
        gv_list.setAdapter(seachPopuAdapter);
        popu.showAsDropDown(llpopu);
        gv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     switch (sortType){
                                case 1://类别
                                    list.clear();
                                    if (position == 0) {
                                        type_id = "0";
                                        titleTxt.setText("全部");
                                    }else{
                                        type_id = classify.get(position-1).getType_id();
                                        titleTxt.setText(classify.get(position-1).getType_name());
                                        page =1;
                                        sort = "";
                                        list.clear();

                                    }
                                    tvTao.setText(menuClass.get(position));
                                    seachPopuAdapter.setShowbg(position);
                                    popu.dismiss();
                                    getData();
                                    break;
                                case 2://价格排序
                                    page =1;
                                    list.clear();
                                    if (position == 0) {
                                        sort = "";
                                    }else if (position == 1) {
                                        sort = "2";
                                    }else{
                                        sort = "3";
                                    }
                                    popu.dismiss();
                                    getData();
                                    break;

                            }
            }
        });

    }
    boolean isshow = false;
}
