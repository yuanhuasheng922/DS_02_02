package com.example.yuan.yizhou.presenter;

import com.example.yuan.yizhou.callback.MyCallBack;
import com.example.yuan.yizhou.model.IModelImple;
import com.example.yuan.yizhou.view.IView;

import java.util.Map;

public class IPresenterImple implements IPresenter {
    private IView mIview;
    private IModelImple mIModelImple;

    public IPresenterImple(IView mIview) {
        this.mIview = mIview;
        mIModelImple=new IModelImple();
    }

    @Override
    public void getRequest(String url, Class calzz, Map<String, String> params) {
        mIModelImple.getRequest(url, calzz, params, new MyCallBack() {
            @Override
            public void getRequest(Object data) {
                mIview.getRequest(data);
            }
        });
    }
}
