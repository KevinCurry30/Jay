package com.diligroup.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

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
import com.diligroup.login.LoginActivity;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.shop.GetShopActivity;
import com.diligroup.utils.FileUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.PictureFileUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.utils.UpLoadPhotoUtils;
import com.diligroup.utils.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.Request;

public class HomeActivity extends BaseActivity implements RequestManager.ResultCallback {
    private static final int REQUEST_IMAGE = 2;
    private static final int CROP_CODE = 3;
    private ArrayList<String> mSelectPath;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.tab_bar)
    RadioGroup tabBar;
    private List<String> titles;
    private String path;
    private String fileName;
    private File file;
    private String imagPath;
    private String currentStoreId;//当前门店id

    private String lastTag;
    private Map<String, Class<? extends Fragment>> fragments;
    private String tag;//当前需要展示的tab
    private FragmentManager fm;
    private String storeAdress;//门店地址

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
        fm = getSupportFragmentManager();
        if(!TextUtils.isEmpty(UserManager.getInstance().getStoreId()) && !TextUtils.isEmpty(UserManager.getInstance().getStoreAddress())){
            currentStoreId =UserManager.getInstance().getStoreId();
            storeAdress = UserManager.getInstance().getStoreAddress();
            initFragment();
        }else{
            startActivityForResult(new Intent(this,GetShopActivity.class),20);
        }

        tabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_home:
                        changeFragment("tab1");
                        break;
                    case R.id.tab_before:
                        if (UserManager.getInstance().isLogin()) {
                            changeFragment("tab2");
                        } else {
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            tabBar.check(R.id.tab_home);
                        }
                        break;
                    case R.id.tab_after:
                        if (UserManager.getInstance().isLogin()) {
                            changeFragment("tab3");
                        } else {
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            tabBar.check(R.id.tab_home);
                        }
                        break;
                    case R.id.tab_my:
                        if (UserManager.getInstance().isLogin()) {
                            changeFragment("tab4");
                        } else {
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            tabBar.check(R.id.tab_home);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
    /*
* 初始化Fragment，添加第一个tab
* */
    private void initFragment() {
        fragments = new Hashtable<String, Class<? extends Fragment>>();
        fragments.put("tab1", HomeFragment.class);
        fragments.put("tab2", BeforeFragment.class);
        fragments.put("tab3", AfterFragment.class);
        fragments.put("tab4", UserSetFragment.class);
        tabBar.check(R.id.tab_home);
        changeFragment("tab1");
    }
    /**
     * 显示和隐藏Fragment
     *
     * @param tag 切换的tab
     */
    public void changeFragment(String tag) {
        if(lastTag!=null&&lastTag.equals(tag)){
            return;
        }
        Fragment last = fm.findFragmentByTag(lastTag);
        Fragment current = fm.findFragmentByTag(tag);
        if(last != null && last.isAdded()){
            fm.beginTransaction().hide(last).commit();
        }
        if(current == null){
            try {
                if(tag.equals("tab1")){
                    current=new HomeFragment().newInstance(currentStoreId,storeAdress);
                }else{
                current = fragments.get(tag).newInstance();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(current.isAdded()){
            fm.beginTransaction().show(current).commit();
        }else{
            fm.beginTransaction().add(R.id.container, current, tag).commit();
        }
        lastTag = tag;
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
                Api.perfectInfoAfterUpLoad(UserManager.getInstance().getUserId(), bean.getFilePath(), this);
            }
        } else if (action == Action.SET_INFOS && object != null) {
            CommonBean bean1 = (CommonBean) object;
            if (bean1.getCode().equals(Constant.RESULT_SUCESS)) {
                LogUtils.i("完善信息成功==");
                final UserSetFragment userSetFragment;
                try {
                    userSetFragment = (UserSetFragment) (fragments.get("tab4").newInstance());
                    if (null != imagPath) {
                        userSetFragment.chageHeadIcon(imagPath);
                        UserInfoBean.getInstance().setHeadPhotoAdd(imagPath);
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
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
        if(resultCode==0x111){//门店返回id。进入首页
            currentStoreId = data.getStringExtra("storeId");
            storeAdress = data.getStringExtra("address");
            initFragment();
        }
    }
}
