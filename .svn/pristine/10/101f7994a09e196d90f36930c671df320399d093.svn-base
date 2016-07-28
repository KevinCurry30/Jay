package com.diligroup.shop;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.base.Constant;
import com.diligroup.base.DiliApplication;
import com.diligroup.bean.ShopInfosBean;
import com.diligroup.bean.UserLocationBean;
import com.diligroup.other.LocationService;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 根据位置获取 附近门店 list
 * Created by Kevin on 2016/7/11.
 */
public class GetShopActivity extends BaseAcitvity implements CloudListener, BDLocationListener {
    private static final String LTAG = GetShopActivity.class.getSimpleName();
    private final int SDK_PERMISSION_REQUEST = 127;
    //    private List<String> list_shop;
    LocationService locationService;
    //    List<CloudPoiInfo> cloudPoiInfoList;
    List<ShopInfosBean> shopInfoList;
    @Bind(R.id.lv_list_shop)
    ListView shopListView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            cloudPoiInfoList = (List<CloudPoiInfo>) msg.obj;
            if (msg.what == SDK_PERMISSION_REQUEST) {
//                list_shop = new ArrayList<>();
                shopInfoList = new ArrayList<>();
                for (CloudPoiInfo info : (List<CloudPoiInfo>) msg.obj) {
                    ShopInfosBean shopinfo = new ShopInfosBean();
                    shopinfo.setTitle(info.title);
                    shopinfo.setAddress(info.address);
                    shopinfo.setCity(info.city);
                    shopinfo.setProvince(info.province);
                    shopinfo.setDistrict(info.district);
//                    list_shop.add(shopinfo.getTitle());
                    shopInfoList.add(shopinfo);
                }
                initListShop();
            }

        }


    };

    private void initListShop() {
        if (shopInfoList != null) {
            shopListView.setAdapter(new ShopListAdapter());
//            shop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                }
//            });
        }
    }

    @OnClick(R.id.bt_more)
    public void goMoreShop() {
        {
            readyGo(SelectShopByUser.class);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.getshop_activity;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {
        ToastUtil.showShort(GetShopActivity.this, "网络异常请检查连接.....");

    }

    @Override
    protected void onStart() {
        getPersimmions();
        super.onStart();

    }

    /**
     * 根据经纬度 查询周边商铺
     *
     * @param lon
     * @param lat
     */
    public void getShopByLocation(double lon, double lat) {
        CloudManager.getInstance().init(GetShopActivity.this);
        NearbySearchInfo searchInfo = new NearbySearchInfo();
        searchInfo.ak = Constant.APP_KEY;
        searchInfo.geoTableId = Constant.GENTABID;
        searchInfo.radius = 30000;
        searchInfo.location = String.valueOf(lon) + "," + String.valueOf(lat);
        LogUtils.e("=====当前经纬度=====" + searchInfo.location);
        CloudManager.getInstance().nearbySearch(searchInfo);
    }

    private void getLocation() {
        locationService = ((DiliApplication) getApplication()).locationService;
        locationService.registerListener(this);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
    }

    @Override
    protected void initViewAndData() {
        getLocation();
    }

    @Override
    public void setTitle() {
        tv_title.setText("请选择门店");
        title_infos.setText("附近门店");
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (null != bdLocation && bdLocation.getLocType() != BDLocation.TypeServerError) {
            UserLocationBean userBean = new UserLocationBean();
            userBean.setLatitud(bdLocation.getLatitude());
            userBean.setLongitude(bdLocation.getLongitude());
            userBean.setCity(bdLocation.getCity());
            userBean.setProvice(bdLocation.getProvince());
            userBean.setAddress(bdLocation.getAddress());
            userBean.setTime(bdLocation.getTime());
            getShopByLocation(bdLocation.getLongitude(), bdLocation.getLatitude());
            locationService.unregisterListener(this);
            locationService.stop();
        } else {
            ToastUtil.showLong(this, "获取位置失败");
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        if (locationService != null) {
            locationService.unregisterListener(this); //注销掉监听
            locationService.stop(); //停止定位服务
        }
        super.onStop();
    }

    @Override
    public void onGetSearchResult(CloudSearchResult result, int i) {
        if (result != null && result.poiList != null && result.poiList.size() > 0) {
            Log.d(LTAG, "onGetSearchResult, result length: " + result.poiList.size());
            Message msg = new Message();
            msg.obj = result.poiList;
            msg.what = SDK_PERMISSION_REQUEST;
            handler.sendMessage(msg);
            for (CloudPoiInfo info : result.poiList) {
                String address = info.address;
                String city = info.city;
                String province = info.province;
                int distance = info.distance;
                String title = info.title;
                String district = info.district;
                Log.e("address==", address);
                Log.e("city==", city);
                Log.e("province==", province);
                Log.e("title==", title);
                Log.e("district==", district);
                Log.e("dinstance==", String.valueOf(distance));
            }
        }
    }

    @Override
    public void onGetDetailSearchResult(DetailSearchResult detailSearchResult, int i) {

    }

    public class ShopListAdapter extends BaseAdapter {

        LayoutInflater mInflater;

        ShopListAdapter() {
            mInflater = LayoutInflater.from(GetShopActivity.this);
        }

        @Override
        public int getCount() {
            return shopInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_shop, null);
                holder.shop_name = (TextView) convertView.findViewById(R.id.tv_shopname);
                holder.shop_details = (TextView) convertView.findViewById(R.id.tv_shop_details);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.shop_name.setText(shopInfoList.get(position).getTitle());
            holder.shop_details.setText(shopInfoList.get(position).getAddress());
            return convertView;
        }
    }

    class ViewHolder {
        TextView shop_name;
        TextView shop_details;
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }
}
