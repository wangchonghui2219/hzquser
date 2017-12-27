package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.GuiGe;
import com.dlwx.hzquser.adapter.SeleteClassHunAdapter;
import com.dlwx.hzquser.adapter.SeleteClassOrderAdapter;
import com.dlwx.hzquser.adapter.SeleteClassSuAdapter;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.model.bean.AffOrderBean;
import com.dlwx.hzquser.model.bean.MenuDetails;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.model.bean.SeleteMealBean;
import com.dlwx.hzquser.model.bean.SeleteMealOrtherBean;
import com.dlwx.hzquser.model.bean.SeleteMealSuBean;
import com.dlwx.hzquser.model.bean.SubOrderBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.MyGridView;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.hzquser.base.MyApplication.User_Token;

/**
 * 选餐分类
 */
public class SeleteClassActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.gv_list1)
    MyGridView gvList1;
    @BindView(R.id.gv_list2)
    MyGridView gvList2;
    @BindView(R.id.tv_orther)
    TextView tvOrder;
    @BindView(R.id.gv_order)
    MyGridView gvOrder;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.iv_minus)
    ImageView ivMinus;
    @BindView(R.id.tv_menuTypename1)
    TextView tvMenuTypename1;
    @BindView(R.id.tv_menuTypename2)
    TextView tvMenuTypename2;
    private MenuDetails menuDetails;
    @BindView(R.id.btn_submit)
    Button bTnsubmit;
    @BindView(R.id.tv_seletetypenum)
    TextView tvSeletetypenum;
    @BindView(R.id.sc_view)
    ScrollView sc_view;
    @BindView(R.id.ll_standard)
    LinearLayout llStandard;
    @BindView(R.id.gv_list4)
    GridView gvList4;
    @BindView(R.id.tv_guiprice)
    TextView tv_guiprice;
    @BindView(R.id.ll_price)
    LinearLayout ll_price;
    private List<SeleteMealSuBean.IsCheck> checknumsu= new ArrayList<>();
    private List<SeleteMealBean.IsCheck> checknum = new ArrayList<>();
    private List<SeleteMealOrtherBean.IsCheck> checknumorther = new ArrayList<>();
    private List<MenuDetails.BodyBean.XuancanBean.HunBean> hun= new ArrayList<>();
    private List<MenuDetails.BodyBean.XuancanBean.SuBean> su= new ArrayList<>();
    private MenuDetails.BodyBean.XuancanBean xuancan;
    private String type;
    private int hunNum;
    private int suNum;
    private int ortherNum;
    private MenuDetails.BodyBean body;
    private List<MenuDetails.BodyBean.XuancanBean.QitaBean> qitas= new ArrayList<>();
    private String guispec_id;
    private int guibonus_point;
    private double guiGeBeanPrice;

    @Override
    protected void initView() {
        menuDetails = (MenuDetails) getIntent().getSerializableExtra("xuancan");
        //1为购买，0为购物车
        type = getIntent().getStringExtra("type");
        setContentView(R.layout.activity_selete_class);
        ButterKnife.bind(this);
    }

    private List<String> hunstrs = new ArrayList<>();
    private List<String> suStrs = new ArrayList<>();
    private List<String> orderStr = new ArrayList<>();
    private List<Integer> hunSeletePos = new ArrayList<>();
    private List<Integer> suSeletePos = new ArrayList<>();
    private List<Integer> ortherSeletepos = new ArrayList<>();
    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("选餐");
        body = menuDetails.getBody();
        xuancan = body.getXuancan();
        if (xuancan == null) {

            ll_price.setVisibility(View.VISIBLE);
            sc_view.setVisibility(View.GONE);
                llStandard.setVisibility(View.VISIBLE);
            final List<MenuDetails.BodyBean.GuiGeBean> guige = body.getGuige();
            final GuiGe guiGeAdapter = new GuiGe(ctx,guige);
            gvList4.setAdapter(guiGeAdapter);

            if ( guige.get(0).getPre_price() == 0) {
                guiGeBeanPrice = guige.get(0).getPrice();
                guispec_id = guige.get(0).getSpec_id();
                tv_guiprice.setText("￥"+ guige.get(0).getPrice());
            }else{
                tv_guiprice.setText("￥"+guige.get(0).getPre_price());
                guiGeBeanPrice = guige.get(0).getPre_price();
            }
           typeName = guige.get(0).getSpec_name();
            gvList4.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    MenuDetails.BodyBean.GuiGeBean guiGeBean = guige.get(position);
                    num=1;
                    tvNum.setText(num+"");
                    if (guiGeBean.getPre_price() == 0) {
                        guiGeBeanPrice = guiGeBean.getPrice();
                    }else{
                        guiGeBeanPrice = guiGeBean.getPre_price();
                    }
                    tv_guiprice.setText("￥"+guiGeBeanPrice);
                    guispec_id = guiGeBean.getSpec_id();
                    guibonus_point = guiGeBean.getBonus_point();
                    typeName = guiGeBean.getSpec_name();
                    guiGeAdapter.setTag(position);
                }
            });




        } else {
            sc_view.setVisibility(View.VISIBLE);
            llStandard.setVisibility(View.GONE);

            tv_guiprice.setText("￥"+body.getPre_price());
            /**
             * 荤菜
             */
            SeleteMealBean seleteMealBean = new SeleteMealBean();
            //存储选中的菜品
            checknum = new ArrayList<>();
            hun = xuancan.getHun();



            for (int i = 0; i < hun.size(); i++) {
                SeleteMealBean.IsCheck isCheck = new SeleteMealBean.IsCheck();
                isCheck.setCheck(false);
                checknum.add(isCheck);
                hunstrs.add(hun.get(i).getName());
            }
            final SeleteClassHunAdapter hunAdapter = new SeleteClassHunAdapter(ctx, hunstrs, checknum, xuancan.getHun_num());
            gvList1.setAdapter(hunAdapter);
            hunAdapter.setOnCheckNumListener(new SeleteClassHunAdapter.SeleteNumListener() {
                @Override
                public void setCheckResult(boolean checked, int position) {
                    hunNum = 0;
                    hunSeletePos.add(position);
                    for (int i = 0; i < checknum.size(); i++) {
                        SeleteMealBean.IsCheck isCheck1 = checknum.get(i);
                        Boolean check = isCheck1.getCheck();
                        if (check) {
                            hunNum++;
                        }
                    }
//
                    if (hunNum < xuancan.getHun_num()) {
                        SeleteMealBean.IsCheck isCheck = checknum.get(position);
                        isCheck.setCheck(checked);
                    }else{

                        checknum.get(hunSeletePos.get(0)).setCheck(false);
                        checknum.get(position).setCheck(true);
                        hunSeletePos.remove(0);
                        hunAdapter.setCheckPos(position);
                    }
                    hunAdapter.notifyDataSetChanged();
                }
            });
            /**
             * 素餐
             */

            SeleteMealSuBean seleteMealSuBean = new SeleteMealSuBean();
            checknumsu = new ArrayList<>();
            su = xuancan.getSu();
            for (int i = 0; i < su.size(); i++) {
                suStrs.add(su.get(i).getName());
                SeleteMealSuBean.IsCheck isChecksu = new SeleteMealSuBean.IsCheck();
                isChecksu.setCheck(false);
                checknumsu.add(isChecksu);
            }
            final SeleteClassSuAdapter suAdapter = new SeleteClassSuAdapter(ctx, suStrs, checknumsu);
            gvList2.setAdapter(suAdapter);
            suAdapter.setOnCheckNumListener(new SeleteClassSuAdapter.SeleteNumListener() {
                @Override
                public void setCheckResult(boolean checked, int position) {
                    suSeletePos.add(position);
                    suNum = 0;
                    for (int i = 0; i < checknumsu.size(); i++) {
                        SeleteMealSuBean.IsCheck isChecksu = checknumsu.get(i);
                        Boolean check = isChecksu.getCheck();
                        if (check) {
                            suNum++;
                        }
                    }
                    if (suNum < xuancan.getSu_num()) {
                        SeleteMealSuBean.IsCheck isCheck = checknumsu.get(position);
                        isCheck.setCheck(checked);
                    }else{
                        checknumsu.get(suSeletePos.get(0)).setCheck(false);
                        checknumsu.get(position).setCheck(true);
                        suSeletePos.remove(0);
                    }
                    suAdapter.notifyDataSetChanged();
                }
            });

            /**
             * 其他
             */

            qitas = xuancan.getQita();

            if (qitas.size() != 0) {
                tvSeletetypenum.setText("荤菜可选" + (xuancan.getHun_num()) + "种" + ","
                        + "素菜可选" + (xuancan.getSu_num())+"种其它可选1种");
                checknumorther = new ArrayList<>();
                for (int i = 0; i < qitas.size(); i++) {
                    orderStr.add(qitas.get(i).getName());
                    SeleteMealOrtherBean.IsCheck isCheckorther = new SeleteMealOrtherBean.IsCheck();
                    isCheckorther.setCheck(false);
                    checknumorther.add(isCheckorther);
                }
                final SeleteClassOrderAdapter orderAdapter = new SeleteClassOrderAdapter(ctx,orderStr,checknumorther);
                gvOrder.setAdapter(orderAdapter);
                orderAdapter.setOnCheckNumListener(new SeleteClassOrderAdapter.SeleteNumListener() {
                    @Override
                    public void setCheckResult(boolean checked, int position) {
                        ortherNum = 0;
                        ortherSeletepos.add(position);
                        for (int i = 0; i < checknumorther.size(); i++) {
                            SeleteMealOrtherBean.IsCheck isCheck = checknumorther.get(i);
                            Boolean check = isCheck.getCheck();
                            if (check) {
                                ortherNum++;
                            }
                        }
//
                        if (ortherNum < xuancan.getQita_num()) {
                            SeleteMealOrtherBean.IsCheck isCheck = checknumorther.get(position);
                            isCheck.setCheck(checked);
                        }else{

                            checknumorther.get(ortherSeletepos.get(0)).setCheck(false);
                            checknumorther.get(position).setCheck(true);
                            ortherSeletepos.remove(0);
                        }
                        orderAdapter.notifyDataSetChanged();
                    }
                });

            }else{
                tvSeletetypenum.setText("荤菜可选" + (xuancan.getHun_num()) + "种" + ","
                        + "素菜可选" + (xuancan.getSu_num())+"种");
            }
            }


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private String hunid;
    private String suid;
    private String qitaid;
    String typeName = "";
    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        hunid = "";
        suid = "";
        if (xuancan != null) {
            typeName = "";
        }

        for (int i = 0; i < checknum.size(); i++) {

            SeleteMealBean.IsCheck isCheck = checknum.get(i);
            Boolean check = isCheck.getCheck();


            if (TextUtils.isEmpty(hunid)) {
                hunid = hun.get(i).getMenu_id();
                typeName = hun.get(i).getName();
            } else {
                hunid = hunid + "," + hun.get(i).getMenu_id();
                typeName = typeName + "," + hun.get(i).getName();
            }


//            if (check) {
//                if (TextUtils.isEmpty(hunid)) {
//                    hunid = hun.get(i).getMenu_id();
//                    typeName = hun.get(i).getName();
//                } else {
//                     hunid = hunid + "," + hun.get(i).getMenu_id();
//                    typeName = typeName + "," + hun.get(i).getName();
//                }
//
//            }
        }
        for (int i = 0; i < checknumsu.size(); i++) {
            SeleteMealSuBean.IsCheck isCheck = checknumsu.get(i);
            Boolean check = isCheck.getCheck();

            if (TextUtils.isEmpty(suid)) {
                suid = su.get(i).getMenu_id();
                typeName = typeName + "," + su.get(i).getName();
            } else {
                suid = hunid + "," + su.get(i).getMenu_id();
                typeName = typeName + "," + su.get(i).getName();
            }

//            if (check) {
//                if (TextUtils.isEmpty(suid)) {
//                    suid = su.get(i).getMenu_id();
//                    typeName = typeName + "," + su.get(i).getName();
//                } else {
//                    suid = hunid + "," + su.get(i).getMenu_id();
//                    typeName = typeName + "," + su.get(i).getName();
//                }
//
//            }
        }



        /**
         * 其他
         */
         for (int i = 0; i < checknumorther.size(); i++) {
             SeleteMealOrtherBean.IsCheck isCheck = checknumorther.get(i);
             Boolean check = isCheck.getCheck();

             if (TextUtils.isEmpty(qitaid)) {
                 qitaid = qitas.get(i).getMenu_id();
                 typeName = typeName + "," + qitas.get(i).getName();
             } else {
                 qitaid = qitaid + "," + qitas.get(i).getMenu_id();
                 typeName = typeName + "," + qitas.get(i).getName();
             }

//            if (check) {
//                if (TextUtils.isEmpty(qitaid)) {
//                    qitaid = qitas.get(i).getMenu_id();
//                    typeName = typeName + "," + qitas.get(i).getName();
//                } else {
//                    qitaid = qitaid + "," + qitas.get(i).getMenu_id();
//                    typeName = typeName + "," + qitas.get(i).getName();
//                }
//
//            }
        }


        if (xuancan != null) {
            if (TextUtils.isEmpty(hunid)) {
                Toast.makeText(ctx, "请先选择荤菜菜系", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(50);
                return;
            }
            String[] split = hunid.split(",");
            if (split.length < xuancan.getHun_num()) {
                vibrator.vibrate(50);
                Toast.makeText(ctx, "还需要选择" + (xuancan.getHun_num() - split.length) + "种荤菜", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(suid)) {
                Toast.makeText(ctx, "请先选择素菜系", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(50);
                return;
            }
            String[] splitsu = suid.split(",");
            if (splitsu.length < xuancan.getSu_num()) {
                vibrator.vibrate(50);
                Toast.makeText(ctx, "还需要选择" + (xuancan.getSu_num() - splitsu.length) + "种素菜", Toast.LENGTH_SHORT).show();
                return;
            }
            wch(hunid + ";" + suid);
        }



        if (type.equals("0")) {//加入购物车
            Map<String, String> map = new HashMap<>();
            map.put("token", User_Token);
            map.put("menu_id", menuDetails.getBody().getMenu_id());
            map.put("num", num + "");
            if (xuancan != null) {

                map.put("spec_id", menuDetails.getBody().getSpec_id());
                map.put("hun", hunid);
                map.put("su", suid);
            }else{

                map.put("spec_id",guispec_id);
            }
            mPreenter.fetch(map, false, HttpUtiles.Add_ShopCat, "");
        } else {//直接购买


            AffOrderBean affOrderBean = new AffOrderBean();
            List<SubOrderBean> orderBeens = new ArrayList<>();
            SubOrderBean orderBean = new SubOrderBean();
            orderBean.setImg(menuDetails.getBody().getPath());
            orderBean.setNum(num);
            orderBean.setMenuname(menuDetails.getBody().getMenu_name());
            if (menuDetails.getBody().getPre_price() == 0) {
                orderBean.setPrice(menuDetails.getBody().getPrice());
            }else{

                orderBean.setPrice(menuDetails.getBody().getPre_price());
            }

            orderBean.setHunid(hunid);
            orderBean.setSuid(suid);
            orderBean.setSpec_id(body.getSpec_id());
            orderBean.setQita(qitaid);
            orderBean.setMenu_id(body.getMenu_id());
            orderBean.setBonus_point(body.getBonus_point());
            List<SubOrderBean.MenuType> menuTypes = new ArrayList<>();
            if (xuancan != null) {
                String[] types = typeName.split(",");
                for (int i = 0; i < types.length; i++) {
                    SubOrderBean.MenuType menuType = new SubOrderBean.MenuType();
                    menuType.setTypename(types[i]);
                    menuTypes.add(menuType);
                }
            }else{
                SubOrderBean.MenuType menuType = new SubOrderBean.MenuType();
                menuType.setTypename(typeName);
                menuTypes.add(menuType);
            }

            orderBean.setMenuTypes(menuTypes);
            orderBeens.add(orderBean);
            affOrderBean.setOrderBeen(orderBeens);
            String affOrder = new Gson().toJson(affOrderBean);

            Intent intent = new Intent(ctx, SubOrderActivity.class);
            intent.putExtra("affOrder", affOrder);
            startActivity(intent);
            finish();

        }


    }

    private int num = 1;

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            finish();
        }
        Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();

    }


    @OnClick(R.id.iv_add)
    public void onIvAddClicked() {
        num++;
        tvNum.setText(num + "");
        if (xuancan == null) {
            DecimalFormat df = new DecimalFormat("#.00");
            double totalPrice = Double.parseDouble(df.format(guiGeBeanPrice*num));

            tv_guiprice.setText("￥"+totalPrice);
        }
    }

    @OnClick(R.id.iv_minus)
    public void onIvMinusClicked() {
        if (num == 1) {
            num = 1;
        } else {
            num--;
        }
        if (xuancan == null) {
            DecimalFormat df = new DecimalFormat("#.00");
            double totalPrice = Double.parseDouble(df.format(guiGeBeanPrice*num));
            tv_guiprice.setText("￥"+totalPrice);
        }
        tvNum.setText(num + "");
    }

}
