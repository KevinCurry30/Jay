package com.diligroup.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diligroup.After.fragment.AfterFragment;
import com.diligroup.Before.fragment.BeforeFragment;
import com.diligroup.Home.fragment.HomeFragment;
import com.diligroup.R;
import com.diligroup.UserSet.fragment.UserSetFragment;
import com.diligroup.base.BaseActivity;
import com.diligroup.base.Constant;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.UploadInfo;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.utils.FileUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.PictureFileUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.utils.UpLoadPhotoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.Request;

public class HomeActivity extends BaseActivity implements RequestManager.ResultCallback {


    private static final int REQUEST_IMAGE = 2;
    private static final int CROP_CODE = 3;
    private ArrayList<String> mSelectPath;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tab_layout)
    TabLayout mTablayout;
    @Bind(R.id.title_root)
    RelativeLayout title_root;
    private int[] tabIcons = {
            R.drawable.selector_tab1,
            R.drawable.selector_tab2,
            R.drawable.selector_tab3,
            R.drawable.selector_tab4
    };
    private List<String> titles;
    private String path;
    private String fileName;
    private File file;
    private MyPagerAdapter adapter;
    private String imagPath;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        isShowBack(false);
        setupViewPager(mViewPager);

        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("餐前指导");
        titles.add("餐后评价");
        titles.add("我的");
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.setupWithViewPager(mViewPager);

        setupTabIcons();
        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(changeListener);

    }

    @Override
    public void setTitle() {
        super.setTitle();
        title_root.setVisibility(View.GONE);
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons() {

        mTablayout.getTabAt(0).setCustomView(getTabView(0));
        mTablayout.getTabAt(1).setCustomView(getTabView(1));
        mTablayout.getTabAt(2).setCustomView(getTabView(2));
        mTablayout.getTabAt(3).setCustomView(getTabView(3));
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(titles.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons[position]);
        return view;
    }

    private void setupViewPager(ViewPager mViewPager) {
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), getString(R.string.home));
        adapter.addFragment(new BeforeFragment(), getString(R.string.before));
        adapter.addFragment(new AfterFragment(), getString(R.string.after));
        adapter.addFragment(new UserSetFragment(), getString(R.string.user));
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (action == Action.UPLOAD_PHOTO && null != object) {
            UploadInfo bean = (UploadInfo) object;
            if (bean.getCode().equals(Constant.RESULT_SUCESS)) {
                ToastUtil.showLong(this, "上传成功");
                imagPath = bean.getFilePath();
                Api.perfectInfoAfterUpLoad(Constant.userId + "", bean.getFilePath(), this);
            }
        } else if (action == Action.SET_INFOS && object != null) {
            CommonBean bean1 = (CommonBean) object;
            if (bean1.getCode().equals(Constant.RESULT_SUCESS)) {
                LogUtils.i("完善信息成功==");
                final UserSetFragment userSetFragment = (UserSetFragment) (adapter.getItem(3));
                if (null != imagPath) {
                    userSetFragment.chageHeadIcon(imagPath);
                    UserInfoBean.getInstance().setHeadPhotoAdd(imagPath);
                }
            }
        }
    }

    class MyOnClickListener implements OnClickListener {
        int index;

        public MyOnClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            if (index == 1) {
                ((BeforeFragment) (adapter.getItem(1))).clickShare(ivShare);
            } else if (index == 2) {
                ((AfterFragment) (adapter.getItem(2))).clickShare(ivShare);
            }
        }
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE:
                if (resultCode == RESULT_OK) {
                    mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                    StringBuilder sb = new StringBuilder();
                    for (String p : mSelectPath) {
                        sb.append(p);
                    }
                    LogUtils.i("onActivityResult方法=", sb.toString());
                    String tempStr = sb.toString();
                    fileName = tempStr.substring(tempStr.lastIndexOf("/") + 1);
                    file = new File(sb.toString());
                    new UpLoadPhotoUtils(this).startPhotoZoom(Uri.fromFile(file));
                }
                break;
            case CROP_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        photo = PictureFileUtils.zoomImage(photo, 150, 150);
                        Api.upLoadPicture(new String(Base64.encode(FileUtils.Bitmap2Bytes(photo), Base64.DEFAULT)), fileName, this);
                    }
                } else {
                    finish();
                }
                break;
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    title_root.setVisibility(View.GONE);
                    break;
                case 1:
                    title_root.setVisibility(View.VISIBLE);
                    tv_title.setText(titles.get(1));
                    ivShare.setVisibility(View.VISIBLE);
                    ivShare.setOnClickListener(new MyOnClickListener(1));
                    break;
                case 2:
                    title_root.setVisibility(View.VISIBLE);
                    tv_title.setText(titles.get(2));
                    ivShare.setVisibility(View.VISIBLE);
                    ivShare.setOnClickListener(new MyOnClickListener(2));
                    break;
                case 3:
                    title_root.setVisibility(View.VISIBLE);
                    ivShare.setVisibility(View.GONE);
                    tv_title.setText(titles.get(3));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
