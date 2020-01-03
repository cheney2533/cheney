/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huaqin.asfactory;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import com.huaqin.asfactory.R;
import com.huaqin.asfactory.activity.BattertHealth;
import com.huaqin.asfactory.activity.BattaryReset;
import com.huaqin.asfactory.test.ColorThemeTest;
import com.huaqin.asfactory.util.Util;

import android.os.SystemProperties;

public class MyMain extends Activity {
    private static String TAG ="MyMain";
    private static final int REQUEST_CODE_ENABLE_ADMIN = 1;
    private Context mContext;
    private boolean  result=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = MyMain.this;
        initActionBar();
        initView();
    }

    private void initView() {
        TextView tv1 = (TextView) findViewById(R.id.Aged_batt);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=Util.copyFilesFromAssets(MyMain.this, "/data/media/0/dualCaliParams.bin");
                Log.d(TAG,"copy result ="+result);
                Intent intent = new Intent(mContext, BattaryReset.class);
                startActivity(intent);
            }
        });

        TextView tv2 = (TextView) findViewById(R.id.battary);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BattertHealth.class);
                startActivity(intent);
            }
        });
        TextView tv3 = (TextView) findViewById(R.id.wallpaperpicker);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ColorThemeTest.class);
                startActivity(intent);
            }
        });
    }

    private void initActionBar() {
        Util.setAcionBarTitle(getActionBar(), getString(R.string.main_title));
    }

    @Override
    protected void onResume() {
        super.onResume();
        String product = SystemProperties.get("ro.build.product");
        if (!product.equals("sm34")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("APK does not apply to this mobile phone");
            builder.setCancelable(false);
            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
