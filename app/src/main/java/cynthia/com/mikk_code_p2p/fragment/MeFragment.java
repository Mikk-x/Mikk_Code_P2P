package cynthia.com.mikk_code_p2p.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import cynthia.com.mikk_code_p2p.R;
import cynthia.com.mikk_code_p2p.common.BaseFragment;

/**
 * Created by shkstart on 2016/11/30 0030.
 */
public class MeFragment extends BaseFragment {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {

    }

    /**
     * 初始化Title
     */
    public void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("我的资产");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

}
