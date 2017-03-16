package cynthia.com.mikk_code_p2p.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import cynthia.com.mikk_code_p2p.R;
import cynthia.com.mikk_code_p2p.bean.Image;
import cynthia.com.mikk_code_p2p.bean.Index;
import cynthia.com.mikk_code_p2p.bean.Product;
import cynthia.com.mikk_code_p2p.common.AppNetConfig;
import cynthia.com.mikk_code_p2p.common.BaseFragment;
import cynthia.com.mikk_code_p2p.ui.RoundProgress;

/**
 * Created by shkstart on 2016/11/30 0030.
 */
public class HomeFragment extends BaseFragment {


    @Bind(R.id.iv_title_back)
    ImageView mIvTitleBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView mIvTitleSetting;
    @Bind(R.id.banner)
    Banner mBanner;
    @Bind(R.id.tv_home_product)
    TextView mTvHomeProduct;
    @Bind(R.id.roundPro_home)
    RoundProgress mRoundProHome;
    @Bind(R.id.tv_home_yearrate)
    TextView mTvHomeYearrate;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mRoundProHome.setMax(100);
            for (int i = 0; i < currentProress; i++) {
                mRoundProHome.setProgress(i + 1);

                SystemClock.sleep(20);
                //强制重绘
//                roundProHome.invalidate();//只有主线程才可以如此调用
                mRoundProHome.postInvalidate();//主线程、分线程都可以如此调用
            }
        }
    };

    private int currentProress;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    private Index index;

    @Override
    protected void initData(String content) {

        if (!TextUtils.isEmpty(content)){
            index = new Index();

            //解析json数据：GSON / FASTJSON
            JSONObject jsonObject = JSON.parseObject(content);
            //解析json对象数据
            String proInfo = jsonObject.getString("proInfo");
            Product product = JSON.parseObject(proInfo, Product.class);
            //解析json数组数据
            String imageArr = jsonObject.getString("imageArr");
            List<Image> images = jsonObject.parseArray(imageArr, Image.class);
            index.product = product;
            index.images = images;
            //更新页面数据
            mTvHomeProduct.setText(product.name);
            mTvHomeYearrate.setText(product.yearRate + "%");

            currentProress = Integer.parseInt(index.product.progress);


            //在分线程中，实现进度的动态变化
            new Thread(runnable).start();


            //设置banner样式
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器
            mBanner.setImageLoader(new GlideImageLoader());
            //设置图片地址构成的集合
            ArrayList<String> imagesUrl = new ArrayList<String>(index.images.size());
//                ArrayList<String> imagesUrl = new ArrayList<String>(index.images.size());
//                for(int i = 0; i < imagesUrl.size(); i++) {//imagesUrl.size():0
            for (int i = 0; i < index.images.size(); i++) {//index.images.size():4
                imagesUrl.add(index.images.get(i).IMAURL);
            }
//                Log.e("TAG","imagesUrl"+imagesUrl);
            mBanner.setImages(imagesUrl);
            //设置banner动画效果
            mBanner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            String[] titles = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
            mBanner.setBannerTitles(Arrays.asList(titles));
            //设置自动轮播，默认为true
            mBanner.isAutoPlay(true);
            //设置轮播时间
            mBanner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();
        }


    }

    @Override
    protected void initTitle() {

        mIvTitleBack.setVisibility(View.GONE);
        mTvTitle.setText("首页");
        mIvTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             常用的图片加载库：
             Universal Image Loader：一个强大的图片加载库，包含各种各样的配置，最老牌，使用也最广泛。
             Picasso: Square出品，必属精品。和OkHttp搭配起来更配呦！
             Volley ImageLoader：Google官方出品，可惜不能加载本地图片~
             Fresco：Facebook出的，天生骄傲！不是一般的强大。
             Glide：Google推荐的图片加载库，专注于流畅的滚动。
             */


            //Picasso 加载图片简单用法
//            Picasso.with(context).load((String) path).into(imageView);
            Picasso.with(context).load((String) path).into(imageView);

        }
    }
}
