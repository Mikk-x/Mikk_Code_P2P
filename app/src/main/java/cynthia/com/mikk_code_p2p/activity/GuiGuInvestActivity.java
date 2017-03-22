package cynthia.com.mikk_code_p2p.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cynthia.com.mikk_code_p2p.R;
import cynthia.com.mikk_code_p2p.common.BaseActivity;

public class GuiGuInvestActivity extends BaseActivity {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("关于硅谷理财");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view){
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gui_gu_invest;
    }

}
