package cynthia.com.mikk_code_p2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Mikk on 2017/3/19.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

   // 数据
    public List<T> mList;

    public MyBaseAdapter(List<T> TList) {
        mList = TList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHolder<T> hodler;

        if (convertView == null) {
            hodler = getHodler();
        }else {
            hodler = (BaseHolder<T>) convertView.getTag();
        }

        // 装数据
        T t = mList.get(position);
        hodler.setData(t);

        return hodler.getRootView();
    }

    protected abstract BaseHolder<T> getHodler();
}
