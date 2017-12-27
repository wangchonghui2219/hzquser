package com.dlwx.hzquser.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.ShopCatAdapter;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.model.bean.AffOrderBean;
import com.dlwx.hzquser.model.bean.EditShopCatBean;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.model.bean.ShopCatListBean;
import com.dlwx.hzquser.model.bean.SubOrderBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.activity.MenuDetailsActivity;
import com.dlwx.hzquser.view.activity.SubOrderActivity;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dlwx.hzquser.base.MyApplication.User_Token;

/**
 *购物车
 */
public class ShopCatFragment extends BaseFragment {
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.tv_toatl)
    TextView tvToatl;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    Unbinder unbinder;
    @BindView(R.id.cb_allcheck)
    CheckBox cBAllcheck;
    @BindView(R.id.btn_delete)
    Button bTndelete;
    Unbinder unbinder1;
    private List<ShopCatListBean.BodyBean> body;
    private ShopCatAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_shop_cat;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getCatData();
    }

    @Override
    protected void initListener() {
        cBAllcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                double toatl = 0.0;
                if (isChecked) {

                    for (int i = 0; i < body.size(); i++) {
                        ShopCatListBean.BodyBean bodyBean = body.get(i);
                        bodyBean.setCheck(isChecked);
                        toatl += bodyBean.getPrice() * bodyBean.getNum();
                    }
                } else {
                    for (int i = 0; i < body.size(); i++) {
                        ShopCatListBean.BodyBean bodyBean = body.get(i);
                        bodyBean.setCheck(isChecked);
                        toatl = 0.0;
                    }
                }
                DecimalFormat df = new DecimalFormat("#.00");
                toatl = Double.parseDouble(df.format(toatl));
                tvToatl.setText("￥" + toatl);
                adapter.notifyDataSetChanged();
            }
        });
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopCatListBean.BodyBean bodyBean = body.get(position);
                String menu_id = bodyBean.getMenu_id();
                Intent intent = new Intent(ctx, MenuDetailsActivity.class);
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

    private boolean isEditext;//是否处于编辑状态

    @OnClick({R.id.tv_edit, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                if (isEditext) {
                    isEditext = !isEditext;
                    tvEdit.setText("编辑");
                    edimess();

                } else {
                    isEditext = !isEditext;
                    tvEdit.setText("完成");
                    adapter.setEdiState(isEditext);

                }

                break;
            case R.id.btn_submit:
                
                subOrder();
                
                break;
        }
    }



    private int HttpType = 0;
    /**
     * 获取购物车列表信息
     */
    private void getCatData() {
        HttpType = 0;
        Map<String, String> map = new HashMap<>();
        map.put("token", User_Token);
        mPreenter.fetch(map, true, HttpUtiles.ShopCat_List, "ShopCat_List");

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch("购物车" + s);
        if (HttpType == 0) {
                shopCatList(s);
        }else if (HttpType == 1) {//删除操作
            delete(s);
        }else if (HttpType == 2) {
            Gson gson = new Gson();
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                adapter.setEdiState(isEditext);

                double toatl = 0.0;
                int itemNum = 0;
                for (int i = 0; i < body.size(); i++) {
                    ShopCatListBean.BodyBean bodyBean = body.get(i);
                    if (bodyBean.getCheck()) {
                        toatl += bodyBean.getPrice() * bodyBean.getNum();
                        itemNum++;
                    }
                }
                if (itemNum == body.size()) {
                    cBAllcheck.setChecked(true);
                } else {
                    cBAllcheck.setChecked(false);
                }
                tvToatl.setText("￥" + toatl);


            }else{
                getCatData();
            }
        }


    }
    /**
     * 修改信息
     */
    private void edimess() {
        HttpType = 2;
        Map<String, String> map = adapter.map;
        EditShopCatBean shopCatBean = new EditShopCatBean();
        List<EditShopCatBean.CartBean> cartBeens = new ArrayList<>();
        int pos = 0;
        for (Map.Entry<String, String> entry:map.entrySet()) {

            EditShopCatBean.CartBean cartBean = new EditShopCatBean.CartBean();
            cartBean.setCart_id(entry.getKey());
            cartBean.setNum(entry.getValue());
            cartBeens.add(cartBean);
        }
        if (cartBeens.size() == 0) {
            adapter.setEdiState(isEditext);
        }else{
            shopCatBean.setCart(cartBeens);
            Gson gson = new Gson();
            String content = gson.toJson(shopCatBean);

            Map<String,String> maps = new HashMap<>();
            maps.put("token",User_Token);
            maps.put("content",content);
            mPreenter.fetch(maps,false,HttpUtiles.ShopCat_Edit,"");
        }



    }
    /**
     *删除
     */
    private void delete(String s) {
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
//            for (int i = 0; i < deletepostion.size(); i++) {
//                int integer = deletepostion.get(i);
//
//            }
                getCatData();
            cBAllcheck.setChecked(false);

            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 解析购物车列表
     * @param s
     */
    private void shopCatList(String s) {
        Gson gson = new Gson();
        ShopCatListBean shopCatListBean = gson.fromJson(s, ShopCatListBean.class);
        if (shopCatListBean.getCode() == 200) {
            body = shopCatListBean.getBody();
            adapter = new ShopCatAdapter(ctx, body);
            lvList.setAdapter(adapter);
            adapter.setOnCheckListener(new ShopCatAdapter.OnCheckListener() {

                @Override
                public void getResult() {
                    double toatl = 0.0;
                    int itemNum = 0;
                    for (int i = 0; i < body.size(); i++) {
                        ShopCatListBean.BodyBean bodyBean = body.get(i);
                        if (bodyBean.getCheck()) {
                            toatl += bodyBean.getPrice() * bodyBean.getNum();
                            itemNum++;
                        }
                    }
                    DecimalFormat df = new DecimalFormat("#.00");
                     toatl = Double.parseDouble(df.format(toatl));
                    if (itemNum == body.size()) {
                        cBAllcheck.setChecked(true);
                    } else {
                        cBAllcheck.setChecked(false);
                    }
                    tvToatl.setText("￥" + toatl);
                }
            });
        }
    }

    private String deleteId;
    private List<Integer> deletepostion = new ArrayList<>();
    /**
     * 删除
     */
    @OnClick(R.id.btn_delete)
    public void onViewClicked() {
        deleteId = "";
        for (int i = 0; i < body.size(); i++) {
            boolean check = body.get(i).getCheck();
            if (check) {
                if (TextUtils.isEmpty(deleteId)) {
                    deleteId = body.get(i).getCart_id();
                }else{
                    deleteId = deleteId+","+body.get(i).getCart_id();
                }
                deletepostion.add(i);

            }
        }
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        map.put("cart_id",deleteId);
        mPreenter.fetch(map,false,HttpUtiles.Delete_ShopCat,"");

    }

    private void subOrder() {

        AffOrderBean affOrderBean = new AffOrderBean();
        List<SubOrderBean> orderBeens = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {


            SubOrderBean orderBean = new SubOrderBean();
            ShopCatListBean.BodyBean bodyBean = body.get(i);
            boolean check = bodyBean.getCheck();
            if (check) {
//                orderBean.setBonus_point(bodyBean.get);
//            orderBean.setQita(bodyBean.get);
                orderBean.setSpec_id(bodyBean.getSpec_id());
                orderBean.setCart_id(bodyBean.getCart_id());
                orderBean.setImg(bodyBean.getPath());
                orderBean.setMenuname(bodyBean.getMenu_name());
                orderBean.setNum(bodyBean.getNum());
                orderBean.setPrice(bodyBean.getPrice());
                orderBean.setMenu_id(bodyBean.getMenu_id());
                orderBean.setBonus_point(bodyBean.getBonus_point());
                orderBean.setSpec_id(bodyBean.getSpec_id());
                List<SubOrderBean.MenuType> menuTypes = new ArrayList<>();
                List<ShopCatListBean.BodyBean.XuancanBean> xuancan = body.get(i).getXuancan();
                for (int j = 0; j < xuancan.size(); j++) {
                    SubOrderBean.MenuType menuType = new SubOrderBean.MenuType();
                    ShopCatListBean.BodyBean.XuancanBean xuancanBean = xuancan.get(j);
                    menuType.setTypename(xuancanBean.getName());
                    menuTypes.add(menuType);
                }
                orderBean.setMenuTypes(menuTypes);
                orderBeens.add(orderBean);
            }
//

        }
        if (orderBeens.size() == 0) {
            vibrator.vibrate(50);
            return;
        }

        affOrderBean.setOrderBeen(orderBeens);

        String s = new Gson().toJson(affOrderBean);
        wch(s);
        Intent intent = new Intent(ctx, SubOrderActivity.class);
        intent.putExtra("affOrder",s);
        startActivity(intent);

    }

}
