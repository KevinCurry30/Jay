package com.diligroup.utils;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.diligroup.dialog.SharePopwindow;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;


public class ShareUtils implements SharePopwindow.PlantFormClickLinstener {
    private Activity activity;
    private UMImage image;
    private UMusic music;
    private UMVideo video;
    private String shareUrl;

    View rootView;
    //分享类型-普通产品分享
    public final static int SHARE_TYPE_GENERAL_PRODUCT = 100;
    public final static int SHARE_TYPE_PING_GOU = 200;

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(activity, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(activity, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(activity, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    public ShareUtils(Activity activity, String fromUrl, String toUrl,View rootView) {
        this.activity = activity;
        this.rootView=rootView;
        image = new UMImage(activity, "http://www.umeng.com/images/pic/social/integrated_3.png");
//        music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
//        music.setThumb(new UMImage(activity, "http://www.umeng.com/images/pic/social/chart_1.png"));
//        video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");

    }

    /**
     * 打开分享面板
     */
    public void openSharebord(String msg) {
        /**普通分享面板，分享相同一致内容**/
        SharePopwindow popwindow=new SharePopwindow(activity,rootView,this);
        this.shareUrl = "http://baidu.com";
    }

    @Override
    public void onClickWX() {
        new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                .withText("werestetet")
                .withMedia(image)
                .withTitle("qqshare")
                .withTargetUrl(shareUrl)
                .share();
//       new ShareAction(activity).setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE)
//                .setShareboardclickCallback(new ShareBoardlistener() {
//                    @Override
//                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                        new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
//                .withText("werestetet")
////                                .withMedia(image)
////                                .withTitle("qqshare")
////                                .withTargetUrl("http://dev.umeng.com")
//                                .share();
//                    }
//                })
//                .open();
    }

    @Override
    public void onClickWXCircle() {
        new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                .withText("werestetet")
                .withMedia(image)
                .withTitle("qqshare")
                .withTargetUrl(shareUrl)
                .share();
    }

    @Override
    public void onClickQQ() {

        new ShareAction(activity).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
                .withText("werestetet")
                .withMedia(image)
                .withTitle("qqshare")
                .withTargetUrl(shareUrl)
                .share();
    }

    @Override
    public void onClickQQZone() {
        new ShareAction(activity).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener)
                .withText("werestetet")
                .withMedia(image)
                .withTitle("qqshare")
                .withTargetUrl(shareUrl)
                .share();
    }

    class WZShareBoardlistener implements ShareBoardlistener {

        Activity context;
        String msg;

        public WZShareBoardlistener(Activity context, String msg) {
            this.context = context;
            this.msg = msg;
        }

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            new ShareAction(context).setPlatform(share_media).setCallback(umShareListener)
                    .withText(msg)
                    .withMedia(image)
                    .withTitle("qqshare")
                    .withTargetUrl(shareUrl)
                    .share();
        }
    }

}
