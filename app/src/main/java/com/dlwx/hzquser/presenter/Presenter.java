package com.dlwx.hzquser.presenter;

import com.dlwx.hzquser.model.ModeImpl;
import com.dlwx.hzquser.model.ModeInterface;
import com.dlwx.hzquser.view.ViewInterface;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/12/012.
 */

public class Presenter <V>extends BasePresenter{

        ViewInterface viewInterface;
    ModeImpl mode = new ModeImpl();

    public Presenter(ViewInterface viewInterface) {
        super();
        this.viewInterface = viewInterface;
    }
    //绑定view和mode
    public void fetch(Map<String,String> map,Boolean isget,String url,String cachKey){
        viewInterface.showLoading();

        if (mode != null) {
            mode.loadData(new ModeInterface.LoadListener() {
                @Override
                public void complete(String s) {
                    viewInterface.showData(s);
                }

                @Override
                public void onError() {
                    viewInterface.onError();
                }
            },map,isget,url,cachKey,true);
        }

    }
    //绑定view和mode
    public void fetch(Map<String,String> map,Boolean isget,String url,String cachKey,boolean isCach){
        viewInterface.showLoading();

        if (mode != null) {
            mode.loadData(new ModeInterface.LoadListener() {
                @Override
                public void complete(String s) {
                    viewInterface.showData(s);
                }
                @Override
                public void onError() {
                    viewInterface.onError();
                }
            },map,isget,url,cachKey,isCach);
        }

    }
}
