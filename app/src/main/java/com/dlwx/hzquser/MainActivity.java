package com.dlwx.hzquser;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.UserHeadBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.service.ExampleUtil;
import com.dlwx.hzquser.utiles.BottomNavigationViewHelper;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.dlwx.hzquser.utiles.UploadPicUtiles;
import com.dlwx.hzquser.view.NoScrollViewPager;
import com.dlwx.hzquser.view.activity.LoginActivity;
import com.dlwx.hzquser.view.activity.MessagebindingActivity;
import com.dlwx.hzquser.view.fragment.HomeFragment;
import com.dlwx.hzquser.view.fragment.MealFragment;
import com.dlwx.hzquser.view.fragment.MyCenterFragment;
import com.dlwx.hzquser.view.fragment.ShopCatFragment;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.dlwx.hzquser.R.id.bottom_navigation_container;
import static com.dlwx.hzquser.base.MyApplication.User_Token;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {
    private NoScrollViewPager vpViewpage;
    private BottomNavigationView bottomNavigationContainer;
    private List<Fragment> fragments;
    private MyCenterFragment myCenterFragment;
    public static boolean isForeground = false;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        bottomNavigationContainer = (BottomNavigationView) findViewById(bottom_navigation_container);
        vpViewpage = (NoScrollViewPager) findViewById(R.id.vp_viewpage);
            sp.edit().putString(SpUtiles.First,"2").commit();
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MealFragment());
        fragments.add(new ShopCatFragment());
        myCenterFragment = new MyCenterFragment();
        fragments.add(myCenterFragment);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationContainer);
        bottomNavigationContainer.setOnNavigationItemSelectedListener(this);
        bottomNavigationContainer.getMenu().getItem(0).setCheckable(true);
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager(),fragments);
        vpViewpage.setAdapter(adapter);
      registerMessageReceiver();
        if (ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) ctx, new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    1000);
        }
    }
    @Override
    protected void initListener() {
        vpViewpage.setOnPageChangeListener(this);
        vpViewpage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        vpViewpage.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        vpViewpage.requestDisallowInterceptTouchEvent(false);

                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indext = position;

//        if (TextUtils.isEmpty(User_Token)&&position>0) {
//            startActivity(new Intent(ctx, LoginActivity.class));
//        }else {
//            if (position > 0) {
//
//                if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
//                    startActivity(new Intent(ctx, MessagebindingActivity.class));
//                }else{
//
//                    bottomNavigationContainer.getMenu().getItem(position).setChecked(true);
//                }
//            }
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_home://首页
                indext = 0;
                vpViewpage.setCurrentItem(0);
                break;
            case R.id.item_release://领餐
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                }else {


                        if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                            startActivity(new Intent(ctx, MessagebindingActivity.class));
                        }else{

                            bottomNavigationContainer.getMenu().getItem(1).setChecked(true);
                            vpViewpage.setCurrentItem(1);
                        }

                }
//                indext = 1;
//                vpViewpage.setCurrentItem(1);

                break;
            case R.id.item_shopcat://购物车
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                }else {


                    if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                        startActivity(new Intent(ctx, MessagebindingActivity.class));
                    }else{

                        bottomNavigationContainer.getMenu().getItem(2).setChecked(true);
                        vpViewpage.setCurrentItem(2);
                    }

                }
//                indext = 2;
//                vpViewpage.setCurrentItem(2);
                break;
            case R.id.item_persion://个人中心
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                }else {


                    if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                        startActivity(new Intent(ctx, MessagebindingActivity.class));
                    }else{

                        bottomNavigationContainer.getMenu().getItem(3).setChecked(true);
                        vpViewpage.setCurrentItem(3);
                    }

                }
//                indext = 3;
//                vpViewpage.setCurrentItem(3);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:

                UploadPicUtiles.startZoomPic((Activity) ctx, data, 150, 150,1,1);
                break;
            case 2:
                if (data == null) {
                    return;
                }

                UploadPicUtiles.startZoomPic((Activity) ctx, data, 150, 150,1,1);
                break;
            case 5:
                if (data == null) {
                    return;
                }

//                final Bitmap cropPhoto = UploadPicUtiles.getCropPhoto(data);
                File filePath = UploadPicUtiles.getFilePath(data,ctx);
                wch(filePath+"hhhhhhhhhh");
                if (filePath != null) {
                    loading.show();
                    OkGo.<String>post(HttpUtiles.UpHeadPic)
                            .params("token",User_Token)
                            .params("type","1")
                            .params("header_pic",filePath)
                            .params("sas","sadas")
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    wch(response.body()+"wwwwwwwwwwwwww");
                                    loading.dismiss();
                                    Gson gson = new Gson();
                                    UserHeadBean userHeadBean = gson.fromJson(response.body(), UserHeadBean.class);
                                    UserHeadBean.BodyBean body = userHeadBean.getBody();
                                    sp.edit().putString(SpUtiles.header_pic,body.getHeader_pic()).commit();
                                    myCenterFragment.setHead(body.getHeader_pic());
                                    MyApplication.Head_Pic = body.getHeader_pic();
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    loading.dismiss();
                                    Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;

        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
    private int indext = 0;
    @Override
    protected void onResume() {
        isForeground = true;


        super.onResume();
        if (TextUtils.isEmpty(User_Token)) {
            vpViewpage.setCurrentItem(0);
            bottomNavigationContainer.getMenu().getItem(0).setChecked(true);
        } else {
                if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                    vpViewpage.setCurrentItem(0);
                    bottomNavigationContainer.getMenu().getItem(0).setChecked(true);
                }else{
                    vpViewpage.setCurrentItem(indext);
                    bottomNavigationContainer.getMenu().getItem(indext).setChecked(true);
                }
        }
    }
    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }
    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("wch","接收到了广播");
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }

                }
            } catch (Exception e){
            }
        }
    }
}
