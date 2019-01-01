package com.example.yuan.yizhou.model;

import com.example.yuan.yizhou.callback.ICallBack;
import com.example.yuan.yizhou.callback.MyCallBack;
import com.example.yuan.yizhou.utils.OkHttp;

import java.util.Map;

public class IModelImple implements IModel {
    @Override
    public void getRequest(String url, Class calzz, Map<String, String> params, final MyCallBack callBack) {
        OkHttp.getInstance().postEnqueue(url, calzz, params, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                callBack.getRequest(obj);
            }

            @Override
            public void onfail(Exception e) {
                callBack.getRequest(e);
            }
        });
    }
}
