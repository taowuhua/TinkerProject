package com.example.wb_twh369668.tinker;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPatch();
    }

    public void loadPatch() {
        TinkerInstaller.onReceiveUpgradePatch(this.getApplication(),
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");

    }
}
