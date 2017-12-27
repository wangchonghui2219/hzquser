package com.dlwx.hzquser.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.MyCenterAdapter;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.model.bean.MessageRedBean;
import com.dlwx.hzquser.model.bean.VersionBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.dlwx.hzquser.utiles.UpVersionUtiles;
import com.dlwx.hzquser.utiles.UploadPicUtiles;
import com.dlwx.hzquser.view.CircleImageView;
import com.dlwx.hzquser.view.MyListView;
import com.dlwx.hzquser.view.activity.FeedbackActivity;
import com.dlwx.hzquser.view.activity.LoginActivity;
import com.dlwx.hzquser.view.activity.MessCenterActivity;
import com.dlwx.hzquser.view.activity.MyCollectActivity;
import com.dlwx.hzquser.view.activity.MyExtenActivity;
import com.dlwx.hzquser.view.activity.MyIntegralActivity;
import com.dlwx.hzquser.view.activity.MyOrderActivity;
import com.dlwx.hzquser.view.activity.SettActivity;
import com.dlwx.hzquser.view.activity.WebActivity;
import com.dlwx.hzquser.wxapi.QQUtiles;
import com.google.gson.Gson;
import com.lzy.okgo.db.CacheManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dlwx.hzquser.base.MyApplication.User_Token;

/**
 *我的
 */
public class MyCenterFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @BindView(R.id.iv_sett)
    ImageView ivSett;
    @BindView(R.id.iv_mess)
    ImageView ivMess;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.ll_myorder)
    LinearLayout llMyorder;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ll_myntegral)
    LinearLayout llMyntegral;
    @BindView(R.id.ll_mycollect)
    LinearLayout llMycollect;
    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.tv_exten_code)
    TextView tvExtencode;
    @BindView(R.id.iv_red)
    ImageView iv_red;
    Unbinder unbinder;
    String[] strs = {"我的推荐","关于我们","意见反馈","清理缓存","版本更新","退出应用"};
    int [] pics = {R.mipmap.icon_wdtuijian,R.mipmap.icon_wdguanyu,R.mipmap.icon_wdyijian,R.mipmap.icon_wdqingli
                    ,R.mipmap.icon_wdbanben,R.mipmap.icon_wdtuichu
    };
    private String head_pic;
    private boolean clear;
    private int versionCode;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_my_center;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
        lvList.setAdapter(new MyCenterAdapter(ctx,strs,pics));
    }

    @Override
    protected void initListener() {
            lvList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
             switch (position){
                         case 0:
                             if (TextUtils.isEmpty(User_Token)) {
                                 startActivity(new Intent(ctx, LoginActivity.class));
                                 return;
                             }
                             startActivity(new Intent(ctx,MyExtenActivity.class));
                             break;

                        case 1://关于我们

                            intent = new Intent(ctx, WebActivity.class);
                            intent.putExtra("url", HttpUtiles.About_We);
                            intent.putExtra("title","关于我们");
                            startActivity(intent);
                            break;
                        case 2://一箭反馈
                                startActivity(new Intent(ctx,FeedbackActivity.class));
                            break;
                        case 3://清理缓存
                            clearCache();

                            break;
                        case 4://版本更新
                            Version();
                            break;
                        case 5://退出应用
                                showPopu();
                            break;
                    }
    }
    private int HttpType;
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (HttpType == 1) {
            MessageRedBean messageRedBean = gson.fromJson(s, MessageRedBean.class);
            if (messageRedBean.getCode() == 200) {
                int is_read = messageRedBean.getBody().getIs_read();
                if (is_read == 0) {
                    iv_red.setVisibility(View.GONE);
                }else{
                    iv_red.setVisibility(View.VISIBLE);
                }
            }
        }else{
            VersionBean version = gson.fromJson(s, VersionBean.class);
            if (version.getCode() == 200) {
                VersionBean.BodyBean body = version.getBody();
                int new_num = body.getNew_num();
                if (new_num > versionCode) {
                    UpVersionUtiles versionUtiles = new UpVersionUtiles(ctx);
                    versionUtiles.showVersionDia(body.getDownurl());
                }else{
                    Toast.makeText(ctx, version.getResult(), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(ctx, version.getResult(), Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void Version() {
        HttpType = 2;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(ctx.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
            Map<String,String> map = new HashMap<>();
            map.put("version_no", versionCode +"");
            mPreenter.fetch(map,true,HttpUtiles.upVersion,"",false);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void clearCache() {
        new AlertDialog.Builder(ctx)
                .setMessage("将删除所有的图片和数据，删除后不可恢复")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        boolean clear = CacheManager.getInstance().clear();
                        if (clear) {
                            Toast.makeText(ctx, "清除缓存成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ctx, "当前没有缓存信息", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消",null)
                .show();
     
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        sp = ctx.getSharedPreferences(SpUtiles.SP_Mode, Context.MODE_PRIVATE);
        String User_Token = sp.getString(SpUtiles.User_Token, "");
        if (!TextUtils.isEmpty(User_Token)) {
            head_pic = sp.getString(SpUtiles.header_pic, "");

            if (!TextUtils.isEmpty(head_pic)) {
                Glide.with(ctx).load(head_pic).into(ivHead);
                tvUserName.setText(sp.getString(SpUtiles.NickName,""));
                tvExtencode.setText("平台号:"+sp.getString(SpUtiles.terrace,""));
            }else{
                tvUserName.setText("登录注册");

            }
            getMess();
        }


    }

    /**
     * 获取消息
     */
    private void getMess() {
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        mPreenter.fetch(map,true,HttpUtiles.IsRed,"");

    }

    @OnClick({R.id.iv_sett, R.id.iv_mess, R.id.iv_head, R.id.ll_myorder, R.id.ll_myntegral, R.id.ll_mycollect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sett://设置中心
                startActivity(new Intent(ctx,SettActivity.class));
                break;
            case R.id.iv_mess://消息中心
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(ctx,MessCenterActivity.class));
                break;
            case R.id.iv_head://登录
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }

                String loginType = sp.getString(SpUtiles.LoginType, "");
                if (loginType.equals("1")) {

                    UploadPicUtiles.showDia(ctx);
                }
                break;
            case R.id.ll_myorder://我的订单
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(ctx, MyOrderActivity.class));
                break;
            case R.id.ll_myntegral://我的积分
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(ctx,MyIntegralActivity.class));
                break;
            case R.id.ll_mycollect://我的收藏
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(ctx,MyCollectActivity.class));
                break;
        }
    }

    private void showPopu(){
        final View view = LayoutInflater.from(ctx).inflate(R.layout.popu_exit,null);
        ImageView iv_exit = (ImageView) view.findViewById(R.id.iv_pic);
        final PopupWindow popu = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popu.setFocusable(true);
        ColorDrawable drawaable = new ColorDrawable(0x77000000);
        popu.setBackgroundDrawable(drawaable);
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.iv_pic).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height) {
                        popu.dismiss();
                    }
                }
                return true;
            }
        });
        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popu.dismiss();
                //TODO
                sp.edit().putString(SpUtiles.User_Token,"").commit();
                sp.edit().putString(SpUtiles.NickName,"").commit();
                sp.edit().putString(SpUtiles.header_pic,"").commit();
                sp.edit().putString(SpUtiles.seller_id,"").commit();
                sp.edit().putString(SpUtiles.Grade,"").commit();
                sp.edit().putString(SpUtiles.Phone,"").commit();
                tvExtencode.setVisibility(View.GONE);
                User_Token = "";
                new QQUtiles().logout(ctx);
                Glide.with(ctx).load(R.mipmap.icon_wdtouxiiang).into(ivHead);
                tvUserName.setText("注册/登录");
                startActivity(new Intent(ctx,LoginActivity.class));

            }
        });
        popu.showAtLocation(ivHead, Gravity.CENTER,0,0);
    }

    public void setHead(String cropPhoto) {
        Glide.with(ctx).load(cropPhoto).into(ivHead);
    }


}
