package com.diligroup.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.diligroup.R;

import java.io.File;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by hjf on 2016/7/18.
 */
public class UpLoadPhotoUtils {
    Activity mContext;
    private static final int REQUEST_IMAGE = 2;
    private static final int CROP_CODE = 3;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;//读权限
    private ArrayList<String> mSelectPath;
    private String path;

    public UpLoadPhotoUtils(Activity mContext){
        this.mContext=mContext;
    }
    public void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, permission)) {
            new AlertDialog.Builder(mContext)
                    .setTitle(R.string.permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);
        }
    }
    public void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
           requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,mContext.getResources().
                            getString(R.string.permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            MultiImageSelector selector = MultiImageSelector.create(mContext);
            selector.showCamera(true);
            selector.count(1);
            selector.single();
            selector.origin(mSelectPath);
            selector.start(mContext, REQUEST_IMAGE);
        }
    }
    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        File file = FileUtils.buildFile(
                AppUtils.getPath(mContext, AppUtils.StorageFile.cache) + String.valueOf(System.currentTimeMillis() / 1000) + ".jpg", false);
        path = file.getPath();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
//        intent.putExtra("output", Uri.fromFile(new File(path)));
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// ȡ������ʶ��
        intent.putExtra("return-data", true);// true:������uri��false������uri
        mContext.startActivityForResult(intent, CROP_CODE);
    }

}
