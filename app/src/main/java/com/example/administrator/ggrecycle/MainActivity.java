package com.example.administrator.ggrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements XRecyclerView.LoadingListener,MyAdapter.OnItemClickListener{
    int page=1;
    private String path="http://app.u17.com/v3/appV3_3/android/phone/list/commonComicList?argValue=23&argName=sort&argCon=0&android_id=4058040115108878&v=3330110&model=GT-P5210&come_from=Tg002&page=";
    @ViewInject(R.id.x_recyc)
    XRecyclerView recyclerView;
    List<First_User.DataBean.ReturnDataBean.ComicsBean> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        list=new ArrayList<>();

//        指定RecycleView的布局方式
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(this);
        recyclerView.setLoadingMoreEnabled(true);
        //设置刷新风格
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        //        设置条目之间的下划线
        recyclerView.addItemDecoration(new MyLine(this,R.drawable.item_line));
        adapter.setListener(this);
        getDate();
    }

    private void getDate() {
        RequestParams params = new RequestParams(path+page);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                First_User user = gson.fromJson(result, First_User.class);
                list.addAll(user.getData().getReturnData().getComics());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                recyclerView.loadMoreComplete();
                recyclerView.refreshComplete();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }
//刷新
    @Override
    public void onRefresh() {
        list.clear();
        page=1;
        getDate();
        adapter.notifyDataSetChanged();
    }
//加载更多
    @Override
    public void onLoadMore() {
        page++;
        getDate();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View var2, int var3) {
        Toast.makeText(MainActivity.this,"点击了整个条目",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClick(View view, int pos) {
        Toast.makeText(MainActivity.this,"长按了图片",Toast.LENGTH_SHORT).show();
    }
}
