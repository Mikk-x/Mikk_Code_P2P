package cynthia.com.mikk_code_p2p.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import cynthia.com.mikk_code_p2p.R;
import cynthia.com.mikk_code_p2p.activity.LoginActivity;
import cynthia.com.mikk_code_p2p.bean.User;
import cynthia.com.mikk_code_p2p.common.BaseActivity;
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
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;


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
        //判断用户是否已经登录
        isLogin();
    }

    private void isLogin() {
        //查看本地是否有用户的登录信息
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        if(TextUtils.isEmpty(name)){
            //本地没有保存过用户信息，给出提示：登录
            doLogin();

        }else{
            //已经登录过，则直接加载用户的信息并显示
            doUser();
        }

    }
    //加载用户信息并显示
    private void doUser() {
        //1.读取本地保存的用户信息
        User user = ((BaseActivity) this.getActivity()).readUser();
        //2.获取对象信息，并设置给相应的视图显示。
        tvMeName.setText(user.getName());
        Picasso.with(this.getActivity()).load(user.getImageurl()).into(ivMeIcon);
    }

    //给出提示：登录
    private void doLogin() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("提示")
                .setMessage("您还没有登录哦！么么~")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            UIUtils.toast("进入登录页面",false);
                        ((BaseActivity)MeFragment.this.getActivity()).goToActivity(LoginActivity.class,null);
                    }
                })
                .setCancelable(false)
                .show();
    }

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
