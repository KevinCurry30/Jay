package com.diligroup.UserSet.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.base.Constant;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.UploadInfo;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.FileUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.PictureFileUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.utils.UpLoadPhotoUtils;
import com.diligroup.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.Request;

/**
 * Created by Kevin on 2016/6/14.
 * 用户信息详情
 */
public class UserInfoActivity extends BaseActivity {

    @Bind(R.id.tv_time_of_month)
    TextView tv_time_of_month;
    @Bind(R.id.user_icon)
    CircleImageView userIcon;
//    @Bind(R.id.change_headicon)
//    TextView change_headicon;//更换用户头像
//    @Bind(R.id.rl_time_of_month)
//    RelativeLayout rl_time_of_month;//生理期rl布局
    ArrayList<String> mSelectPath;
    private static final int REQUEST_IMAGE = 2;
    private static final int CROP_CODE = 3;
    private String fileName;
    @Bind(R.id.tv_user_phone_number)
    TextView tv_user_number;
    @Bind(R.id.tv_sex)
    TextView tv_sex;
    @Bind(R.id.tv_birth)
    TextView tv_birthday;
    @Bind(R.id.tv_work)
    TextView tv_job;
    @Bind(R.id.tv_noeat)
    TextView tv_noeat;
    @Bind(R.id.tv_height)
    TextView tv_height;
    @Bind(R.id.tv_weight)
    TextView tv_weight;
    @Bind(R.id.tv_where)
    TextView tv_where;
    @Bind(R.id.tv_now_address)
    TextView tv_address;
    @Bind(R.id.tv_special)
    TextView tv_special;
    @Bind(R.id.tv_allergy)
    TextView tv_alLeryFood;

    Boolean isFrist;
    Bundle bundle;
        @Bind(R.id.iv_back)
    ImageView iv_back;
    @Override
    protected void onStart() {
        super.onStart();
        isShowBack(true);

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.user_info;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }
@OnClick(R.id.iv_back)
public void getBack(){
    this.finish();
}
    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("我的信息");


    }

    @Override
    protected void initViewAndData() {

        bundle = new Bundle();
        bundle.putBoolean("isFrist", false);
        tv_user_number.setText(Constant.userNumber);
        if (UserInfoBean.getInstance()!=null){
            if (UserInfoBean.getInstance().getSex()==0){
                tv_sex.setText("男");
            }else{
                tv_sex.setText("女");
            }
            tv_job.setText(UserInfoBean.getInstance().getJob());
            tv_birthday.setText(UserInfoBean.getInstance().getBirthday());
            tv_height.setText(UserInfoBean.getInstance().getHeight());
            tv_weight.setText(UserInfoBean.getInstance().getWeight());
            tv_weight.setText(UserInfoBean.getInstance().getWeight());
            tv_noeat.setText(UserInfoBean.getInstance().getNoEatFood());
            tv_alLeryFood.setText(UserInfoBean.getInstance().getAllergyFood());
            tv_where.setText(UserInfoBean.getInstance().getHomeAddress());
            tv_address.setText(UserInfoBean.getInstance().getCurrentAddress());
        }



    }

    @OnClick(R.id.rl_time_of_month)
    public void ClickPhysiolog() {
        Intent mIntent = new Intent(UserInfoActivity.this, PhysiologicalPeriodActivity.class);
        mIntent.putExtra("isFromMy", true);
        startActivityForResult(mIntent, 10);
    }

    @OnClick(R.id.rl_sex)
    public void ClickSex() {

        Intent intent=new Intent(this,ReportSex.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x00);

    }

    @OnClick(R.id.rl_birthday)
    public void ClickBirthday() {
//        readyGo(ReportBirthday.class, bundle);
        Intent intent=new Intent(this,ReportBirthday.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x10);
    }

    @OnClick(R.id.rl_height)
    public void ClickHeight() {
        readyGo(ReportHeight.class, bundle);

        Intent intent=new Intent(this,ReportHeight.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x30);
    }

    @OnClick(R.id.rl_other)
    public void ClickOther() {
//        readyGo(ReportOther.class, bundle);
        Intent intent=new Intent(this,ReportOther.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x1);
    }

    @OnClick(R.id.rl_special)
    public void ClickTsrq() {
//        readyGo(ReportSpecial.class, bundle);
        Intent intent=new Intent(this,ReportSpecial.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x1);
    }

    @OnClick(R.id.rl_taste)
    public void ClickTaste() {
//        readyGo(ReportTaste.class, bundle);
        Intent intent=new Intent(this,ReportTaste.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x70);
    }

    @OnClick(R.id.rl_where)
    public void ClickWhere() {
        Intent intent=new Intent(this,ReportWhere.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x50);
//        readyGo(ReportWhere.class, bundle);
    }

    @OnClick(R.id.rl_weight)
    public void ClickWeight() {
        Intent intent=new Intent(this,ReportWeight.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x40);
//        readyGo(ReportWeight.class, bundle);
    }

    @OnClick(R.id.rl_noeat)
    public void ClickYsjj() {
        Intent intent=new Intent(this,ReportNoeat.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x1);
//        readyGo(ReportNoeat.class, bundle);
    }

    @OnClick(R.id.rl_work)
    public void ClickWork() {
        Intent intent=new Intent(this,ReportWork.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x20);
//        readyGo(ReportWork.class, bundle);
    }

    @OnClick(R.id.rl_history)
    public void ClickHistory() {
//        readyGo(ReportHistory.class, bundle);
        Intent intent=new Intent(this,ReportHistory.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x1);
    }

    @OnClick(R.id.rl_allergy)
    public void ClickAllergy() {
        Intent intent=new Intent(this,ReportAllergy.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x1);
//        readyGo(ReportAllergy.class, bundle);
    }

    @OnClick(R.id.change_headicon)
    public void ChangeHeadPhoto() {
        new UpLoadPhotoUtils(this).pickImage();
    }

    @OnClick(R.id.rl_now)
    public void ClickAddress() {
//        readyGo(ReportAddress.class, bundle);
        Intent intent=new Intent(this,ReportAddress.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0x60);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0x00:
                if (null!=data){
                    String sexTag=data.getStringExtra("sex");
                    if (sexTag.equals("1")){
                        tv_sex.setText("男");
                    }else{
                        tv_sex.setText("女");
                    }
                }
                break;
            case 0x10:
                if (null!=data){
                    String sexTag=data.getStringExtra("brithday");
                    tv_birthday.setText(sexTag);
                }
                break;
            case 0x20:
                if (null!=data){
                    String job=data.getStringExtra("job");
                    tv_job.setText(job);
                }
                break;
            case 0x30:
                if (null!=data){
                    String height=data.getStringExtra("height");
                    tv_height.setText(height);
                }
                break;
            case 0x40:
                if (null!=data){
                    String weight=data.getStringExtra("weight");
                    tv_weight.setText(weight);
                }
                break;
            case 0x50:
                if (null!=data){
                    String weight=data.getStringExtra("where");
                    tv_where.setText(weight);
                }
                break;
            case 0x60:
                if (null!=data){
                    String weight=data.getStringExtra("address");
                    tv_address.setText(weight);
                }
                break;
            case 0x70:
                if (null!=data){
                    String seleCount=data.getStringExtra("taste");

                }
                break;
            case 0x80:
                if (null!=data){
                    String seleCount=data.getStringExtra("taste");

                }
                break;
            case 10:
                if (null != data) {
                    ArrayList<String> selectDate = (ArrayList<String>) data.getSerializableExtra("cycle");
                    if (selectDate.size() > 1) {
                        tv_time_of_month.setText(selectDate.get(0) + "至" + selectDate.get(1));
                    }
                }
                break;

            case REQUEST_IMAGE:
                if (resultCode == RESULT_OK) {
                    mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                    StringBuilder sb = new StringBuilder();
                    for (String p : mSelectPath) {
                        sb.append(p);
                    }
                    String tempStr = sb.toString();
                    fileName = tempStr.substring(tempStr.lastIndexOf("/") + 1);
                    new UpLoadPhotoUtils(this).startPhotoZoom(Uri.fromFile(new File(sb.toString())));
                }
                break;
            case CROP_CODE:
                if (resultCode == RESULT_OK) {
//                    detialPathAndShowImage();  上传图片
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

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (action == Action.UPLOAD_PHOTO && null != object) {
            UploadInfo bean = (UploadInfo) object;
            if (bean.getCode().equals(Constant.RESULT_SUCESS)) {
                ToastUtil.showLong(this, "上传成功");
                Picasso.with(this).load(bean.getFilePath()).into(userIcon);
                UserInfoBean.getInstance().setHeadPhotoAdd(bean.getFilePath());
                Api.perfectInfoAfterUpLoad(Constant.userId + "", bean.getFilePath(), this);
            }
        } else if (action == Action.SET_INFOS && object != null) {
            CommonBean bean1 = (CommonBean) object;
            if (bean1.getCode().equals(Constant.RESULT_SUCESS)) {
                LogUtils.i("完善信息成功==");
            }
        }
    }
}
