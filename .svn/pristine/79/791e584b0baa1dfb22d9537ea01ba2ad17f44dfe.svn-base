
/**************************************************************************************
* [Project]
*       MyProgressDialog
* [Package]
*       com.lxd.widgets
* [FileName]
*       CustomProgressDialog.java
* [Copyright]
*       Copyright 2012 LXD All Rights Reserved.
* [History]
*       Version          Date              Author                        Record
*--------------------------------------------------------------------------------------
*       1.0.0           2012-4-27         lxd (rohsuton@gmail.com)        Create
**************************************************************************************/
package com.diligroup.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.diligroup.R;

public class RotateShowProgressDialog extends Dialog {
	private static RotateShowProgressDialog progressDialog = null;
	 
	public RotateShowProgressDialog(Context context, int theme) {
        super(context, theme);
    }
	public static void ShowProgressOn(Context context){
		progressDialog=RotateShowProgressDialog.createDialog(context);
		progressDialog.show();
	}
	public static void ShowProgressOn(Context context, boolean cancelableOutside){
		progressDialog=RotateShowProgressDialog.createDialog(context);
		progressDialog.setCanceledOnTouchOutside(cancelableOutside);
		progressDialog.show();
	}
	public static void ShowProgressOn(Context context, boolean cancelable, boolean cancelableOutside){
		progressDialog=RotateShowProgressDialog.createDialog(context);
		progressDialog.setCanceledOnTouchOutside(cancelableOutside);
		progressDialog.show();
	}
    public static void ShowProgressOff() {
        if (progressDialog != null)
        	progressDialog.dismiss();
    }
	public static RotateShowProgressDialog createDialog(Context context){
		progressDialog = new RotateShowProgressDialog(context,R.style.CustomProgressDialog);
		progressDialog.setContentView(R.layout.cds_progress_dialog);
		progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
	
		return progressDialog;
	}
 
   
 
   
    
    
    /**
     * isClose 是否被关闭
     * @return boolean
     *
     */
    public static boolean isDialogShowing() {
    	if (progressDialog!=null){
    		return progressDialog.isShowing();
    	}else
    	{
    		return true;
    	}
	}
}
