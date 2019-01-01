package com.example.yuan.yizhou.model;

import com.example.yuan.yizhou.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String url, Class calzz, Map<String,String> params, MyCallBack callBack);
}
