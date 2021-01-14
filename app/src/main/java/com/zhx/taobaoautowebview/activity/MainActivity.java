package com.zhx.taobaoautowebview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.zhx.taobaoautowebview.R;
import com.zhx.taobaoautowebview.activity.access.AccessibilityNormalSample;
import com.zhx.taobaoautowebview.activity.taobao.TaobaoHjkxActivity;
import com.zhx.taobaoautowebview.util.accessutil.OpenAccessibilitySettingHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mainGroup;
    private Button openAccessibilitySetting;
    private Button openWindowSetting;
    private Button openWindowUtil;
    private Button accessibilityFindAndClick;
    private Button taobaoKjgxClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mainGroup = (LinearLayout) findViewById(R.id.main_group);
        openAccessibilitySetting = (Button) findViewById(R.id.open_accessibility_setting);
        openWindowSetting = (Button) findViewById(R.id.open_window_setting);
        openWindowUtil = (Button) findViewById(R.id.open_window_util);
        accessibilityFindAndClick = (Button) findViewById(R.id.accessibility_find_and_click);
        taobaoKjgxClick = (Button) findViewById(R.id.taobao_kjgx_click);

        openAccessibilitySetting.setOnClickListener(this);
        openWindowSetting.setOnClickListener(this);
        openWindowUtil.setOnClickListener(this);
        accessibilityFindAndClick.setOnClickListener(this);
        taobaoKjgxClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_accessibility_setting:
                OpenAccessibilitySettingHelper.jumpToSettingPage(this);
                break;
            case R.id.open_window_setting:

                break;
            case R.id.open_window_util:

                break;
            case R.id.accessibility_find_and_click:
                startActivity(new Intent(this, AccessibilityNormalSample.class));
                break;
            case R.id.taobao_kjgx_click:
                startActivity(new Intent(this, TaobaoHjkxActivity.class));
                break;
        }
    }
}