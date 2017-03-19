package cynthia.com.mikk_code_p2p.adapter;

import java.util.List;

import cynthia.com.mikk_code_p2p.bean.Product;

/**
 * Created by Mikk on 2017/3/19.
 */

public class ProductAdapter extends MyBaseAdapter<Product> {
    public ProductAdapter(List<Product> TList) {
        super(TList);
    }

    @Override
    protected BaseHolder<Product> getHodler() {
        return new MyBaseHolder();
    }
}
