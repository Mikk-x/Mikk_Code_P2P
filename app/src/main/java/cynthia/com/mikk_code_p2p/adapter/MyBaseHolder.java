package cynthia.com.mikk_code_p2p.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import cynthia.com.mikk_code_p2p.R;
import cynthia.com.mikk_code_p2p.bean.Product;
import cynthia.com.mikk_code_p2p.ui.RoundProgress;
import cynthia.com.mikk_code_p2p.util.UIUtils;

/**
 * Created by Mikk on 2017/3/19.
 */

public class MyBaseHolder extends BaseHolder<Product> {
    @Bind(R.id.p_name)
    TextView mPName;
    @Bind(R.id.p_money)
    TextView mPMoney;
    @Bind(R.id.p_yearlv)
    TextView mPYearlv;
    @Bind(R.id.p_suodingdays)
    TextView mPSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView mPMinzouzi;
    @Bind(R.id.p_minnum)
    TextView mPMinnum;
    @Bind(R.id.p_progresss)
    RoundProgress mPProgresss;

    @Override
    protected View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_product_list, null);
    }

    @Override
    protected void refreshData() {

        Product data = this.getData();

        mPName.setText(data.name);
        mPMoney.setText(data.money);
        mPYearlv.setText(data.yearRate);
        mPSuodingdays.setText(data.suodingDays);
        mPMinzouzi.setText(data.minTouMoney);
        mPMinnum.setText(data.memberNum);
        mPProgresss.setProgress(Integer.parseInt(data.progress));



    }
}
