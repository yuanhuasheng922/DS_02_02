package com.example.yuan.yizhou.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuan.yizhou.R;
import com.example.yuan.yizhou.adapter.ImageBean;
import com.example.yuan.yizhou.apis.Api;
import com.example.yuan.yizhou.presenter.IPresenterImple;
import com.example.yuan.yizhou.view.IView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class One extends Fragment implements IView {
    private String name;
    private Banner banner;
    private IPresenterImple presenterImple;
    private TextView textView;
    private TextView textView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_item,container,false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                presenterImple = new IPresenterImple(this);

        textView = view.findViewById(R.id.text);
        textView2 =view. findViewById(R.id.text2);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double s = Double.valueOf(textView2.getText().toString());
                ImageBean.DataBean imageBean=new ImageBean.DataBean();
                imageBean.setPrice(s);
                EventBus.getDefault().postSticky(imageBean);

            }
        });
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String t = textView.getText().toString();
        ImageBean.DataBean dataBean=new ImageBean.DataBean();
        dataBean.setTitle(t);
        EventBus.getDefault().postSticky(dataBean);
    }
});

        Fresco.initialize(getActivity());
        Intent intent =getActivity().getIntent();
        name = intent.getStringExtra("name");
        banner = view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Uri uri = Uri.parse((String) path);
                imageView.setImageURI(uri);
            }

            @Override
            public ImageView createImageView(Context context) {
                SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
                return simpleDraweeView;
            }
        });
        getShow();

    }
        private void getShow() {
        Map<String,String> map =new HashMap<>();
        map.put("pid",name);
        presenterImple.getRequest(Api.TYPE_IMAGE,ImageBean.class,map);
    }
    @Override
    public void getRequest(Object data) {
        if (data instanceof ImageBean)
        {
            ImageBean imageBean= (ImageBean) data;
            List<String> list=new ArrayList<>();
            String[] split = imageBean.getData().getImages().split("\\|");
            for (int i=0;i<split.length;i++)
            {
                list.add(split[i]);
            }
            banner.setImages(list);
            banner.start();
            textView.setText(imageBean.getData().getTitle());
            textView2.setText(imageBean.getData().getPrice()+"");
            textView2.setTextColor(Color.RED);
        }
    }
}
