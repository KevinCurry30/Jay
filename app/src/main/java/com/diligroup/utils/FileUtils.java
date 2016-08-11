package com.diligroup.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件操作�?
 */
public class FileUtils {
    /**
     * 从文件中读取json数据
     */
    public static String getJsonFromAssets(Context context, String fileName) {
        StringBuilder builder = new StringBuilder();
        BufferedInputStream bis = null;
        String result = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, len));
            }
            JSONObject jsonObject = new JSONObject(builder.toString());
            if (jsonObject.has("result")) {
                result = jsonObject.getString("result");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Drawable file2Drawable(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            Drawable drawable = new BitmapDrawable(bitmap);
            return drawable;
        } catch (Exception e) {
            LogUtils.e(e.toString());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /***
     * 动�?�创建�?�择�?
     *
     * @param context
     * @param idNormal
     * @param idPressed
     * @param idFocused
     * @param idUnable
     * @return
     */
    public static StateListDrawable newSelector(Context context,
                                                Drawable drawableNormal, Drawable drawablePressed,
                                                Drawable drawableFocused, Drawable drawableUnable) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = drawableNormal == null ? null : drawableNormal;
        // Drawable pressed = drawablePressed == null ? null : drawablePressed;
        // Drawable focused = drawableFocused == null ? null : drawableFocused;
        // Drawable unable = drawableUnable == null ? null : drawableUnable;
        Drawable checked = drawablePressed == null ? null : drawablePressed;
        Drawable selected = drawablePressed == null ? null : drawablePressed;
        // View.PRESSED_ENABLED_STATE_SET
        // bg.addState(new int[] { android.R.attr.state_pressed,
        // android.R.attr.state_enabled }, pressed);
        // View.ENABLED_FOCUSED_STATE_SET
        // bg.addState(new int[] { android.R.attr.state_enabled,
        // android.R.attr.state_focused }, focused);
        // View.ENABLED_STATE_SET
        // bg.addState(new int[] { android.R.attr.state_enabled }, normal);
        // View.FOCUSED_STATE_SET
        // bg.addState(new int[] { android.R.attr.state_focused }, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        // bg.addState(new int[] { android.R.attr.state_window_focused },
        // unable);

        bg.addState(new int[]{android.R.attr.state_checked}, checked);
        bg.addState(new int[]{android.R.attr.state_selected}, selected);
        // View.EMPTY_STATE_SET
        bg.addState(new int[]{}, normal);
        return bg;
    }

    /**
     * 创建文件及相应的目录
     */
    public static File buildFile(String fileName, boolean isDirectory) {

        File target = new File(fileName);

        if (isDirectory) {
            target.mkdirs();
        } else {
            if (!target.getParentFile().exists()) {
                target.getParentFile().mkdirs();
                target = new File(target.getAbsolutePath());
            }
            // target = new File(target.getAbsolutePath());
        }
        return target;
    }

    	public static byte[] fileToByte(File file){
            ByteArrayOutputStream outputStream;
		try {
			FileInputStream inputStream=new FileInputStream(file);
			byte buffer[] = new byte[1024];
            outputStream=new ByteArrayOutputStream(1024);

			try {
				while(inputStream.read(buffer,0,buffer.length)!=-1){
                    outputStream.write(buffer,0,buffer.length);
                }

			} catch (IOException e) {
				e.printStackTrace();
                return null;
			}
		} catch (FileNotFoundException e) {
            return null;
		}
            return outputStream.toByteArray();
	}
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

}
