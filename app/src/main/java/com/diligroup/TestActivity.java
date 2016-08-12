package com.diligroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.diligroup.UserSet.activity.ReportSex;
import com.diligroup.UserSet.activity.ReportWork;

public class TestActivity extends AppCompatActivity {
GridView gv_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        gv_test= (GridView) findViewById(R.id.gv_words);
        gv_test.setAdapter(new MyGridAdapter());
        gv_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent =new Intent(TestActivity.this, ReportWork.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 =new Intent(TestActivity.this, ReportSex.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                }

            }
        });
    }
    public class MyGridAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
ViewHolder  holder;
            if (convertView==null){
                holder=new ViewHolder();
                convertView= LayoutInflater.from(TestActivity.this).inflate(R.layout.test_grid_item,null);
              holder.tv_name= (TextView) convertView.findViewById(R.id.gv_bushou_TextView1);
                convertView.setTag(holder);
            }
            holder= (ViewHolder) convertView.getTag();
            holder.tv_name.setText("王");
            return convertView;
        }
    }
    class ViewHolder{
        TextView tv_name;
    }

}
