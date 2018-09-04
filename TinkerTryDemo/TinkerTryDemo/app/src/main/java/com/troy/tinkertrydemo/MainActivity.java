package com.troy.tinkertrydemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends Activity {

    private static final String TAG = "TEST";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "成功了", Toast.LENGTH_SHORT).show();

        showToast();

        tv = (TextView) findViewById(R.id.tv);

        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加载补丁的方法
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                Log.d(TAG, "onClick: load");
            }
        });

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tinker tinker = Tinker.with(getApplicationContext());
                boolean isLoadSuccess = tinker.isTinkerLoaded();
                Log.d(TAG, "isLoadSuccess: " + isLoadSuccess);
            }
        });

        findViewById(R.id.btn_bug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btn_bug");
                tv.setText("出现BUG");
            }
        });
        //        findViewById(R.id.btn_fix).setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Log.d(TAG, "onClick: btn_fix");
        //                tv.setText("修复BUG");
        //            }
        //        });
    }

    private void showToast() {
        Toast.makeText(this, "修改成功 2", Toast.LENGTH_SHORT).show();

        Student student = new Student("小明");
        Toast.makeText(this, student.getName(), Toast.LENGTH_SHORT).show();
    }
}
