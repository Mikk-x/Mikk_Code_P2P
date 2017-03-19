package cynthia.com.mikk_code_p2p.adapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Mikk on 2017/3/19.
 */

public abstract class BaseHolder<T> {

    private View rootView;

    private T data;

    public BaseHolder(){
        rootView = initView();
        rootView.setTag(this);
        ButterKnife.bind(this,rootView);
    }

    //提供item的布局
    protected abstract View initView();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        refreshData();
    }
    //装配过程
    protected abstract void refreshData();

    public View getRootView() {
        return rootView;
    }
}
