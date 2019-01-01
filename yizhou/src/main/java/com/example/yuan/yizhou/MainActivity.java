package com.example.yuan.yizhou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuan.yizhou.adapter.UserAdapter;
import com.example.yuan.yizhou.apis.Api;
import com.example.yuan.yizhou.bean.UserBean;
import com.example.yuan.yizhou.presenter.IPresenterImple;
import com.example.yuan.yizhou.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {
    @BindView(R.id.sousuo)
    ImageView sousuo;
    @BindView(R.id.qiehuan)
    ImageView qiehuan;
    @BindView(R.id.zonghe)
    TextView zonghe;
    @BindView(R.id.xiaoliang)
    TextView xiaoliang;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.shaixuan)
    TextView shaixuan;
    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @BindView(R.id.input)
    EditText input;
    private int mPage;
    private boolean isShow=true;
    private UserAdapter userAdapter;
    private IPresenterImple presenterImple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenterImple = new IPresenterImple(this);
        ititView();
        getShow();

    }

    private void ititView() {
        if (isShow) {
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            xrecyclerview.setLayoutManager(layoutManager);
        }
        else
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
            xrecyclerview.setLayoutManager(gridLayoutManager);
        }
        userAdapter = new UserAdapter(this,isShow);
        xrecyclerview.setAdapter(userAdapter);

        isShow=!isShow;
        mPage=1;
        xrecyclerview.setPullRefreshEnabled(true);
        xrecyclerview.setLoadingMoreEnabled(true);
        xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                getShow();
            }

            @Override
            public void onLoadMore() {
                getShow();
            }
        });
        getShow();
        userAdapter.setClick(new UserAdapter.Click() {
            @Override
            public void OnClick(int position) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("name",position+"");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.sousuo,R.id.qiehuan,R.id.zonghe,R.id.xiaoliang,R.id.price,R.id.shaixuan,R.id.xrecyclerview})
    public void OnClick(View view) {
    switch (view.getId())
    {
        case R.id.sousuo:
            mPage=1;
            String s = input.getText().toString();
            Map<String,String> params =new HashMap<>();
            params.put("keywords",s);
            params.put("page",mPage+"");
            presenterImple.getRequest(Api.TYPE_TEXT,UserBean.class,params);


            break;
        case R.id.qiehuan:
            ititView();
            getShow();
            break;
        case R.id.zonghe:
            mPage=1;
            Map<String,String> params1 =new HashMap<>();
            params1.put("keywords","手机");
            params1.put("page",mPage+"");
            params1.put("sort",0+"");
            presenterImple.getRequest(Api.TYPE_TEXT,UserBean.class,params1);
            break;
        case R.id.xiaoliang:
            mPage=1;
            Map<String,String> params2 =new HashMap<>();
            params2.put("keywords","手机");
            params2.put("page",mPage+"");
            params2.put("sort",1+"");
            presenterImple.getRequest(Api.TYPE_TEXT,UserBean.class,params2);
            break;
        case R.id.price:
            mPage=1;
            Map<String,String> params3 =new HashMap<>();
            params3.put("keywords","手机");
            params3.put("page",mPage+"");
            params3.put("sort",2+"");
            presenterImple.getRequest(Api.TYPE_TEXT,UserBean.class,params3);
            break;
        case R.id.shaixuan:

            break;
        case R.id.xrecyclerview:

            break;
    }
    }
    private void getShow() {
        Map<String,String> params =new HashMap<>();
        params.put("keywords","手机");
        params.put("page",mPage+"");
        presenterImple.getRequest(Api.TYPE_TEXT,UserBean.class,params);
    }

    @Override
    public void getRequest(Object data) {

        if (data instanceof UserBean)
        {
            UserBean userBean= (UserBean) data;
            if (mPage==1)
            {
                userAdapter.setmDatas(userBean.getData());
            }
            else
            {
                userAdapter.addmDatas(userBean.getData());
            }

            mPage++;
            xrecyclerview.loadMoreComplete();
            xrecyclerview.refreshComplete();

        }
    }
}
