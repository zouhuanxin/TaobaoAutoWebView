package com.zhx.taobaoautowebview.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zhx.taobaoautowebview.R;
import com.zhx.taobaoautowebview.activity.access.AccessibilityNormalSample;
import com.zhx.taobaoautowebview.activity.taobao.TaobaoHjkxActivity;
import com.zhx.taobaoautowebview.util.ScreenUtil;
import com.zhx.taobaoautowebview.util.accessutil.OpenAccessibilitySettingHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mainGroup;
    private Button openAccessibilitySetting;
    private Button openWindowSetting;
    private Button openWindowUtil;
    private Button accessibilityFindAndClick;
    private Button taobaoKjgxClick;

    private View floatView;
    private TextView info;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

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
        switch (v.getId()) {
            case R.id.open_accessibility_setting:
                OpenAccessibilitySettingHelper.jumpToSettingPage(this);
                break;
            case R.id.open_window_setting:
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 33445);
                break;
            case R.id.open_window_util:
                showFloatingWindow();
                break;
            case R.id.accessibility_find_and_click:
                startActivity(new Intent(this, AccessibilityNormalSample.class));
                break;
            case R.id.taobao_kjgx_click:
                startActivity(new Intent(this, TaobaoHjkxActivity.class));
                break;
        }
    }

    private void showFloatingWindow() {
        if (Settings.canDrawOverlays(this)) {
            // 获取WindowManager服务
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

            // 新建悬浮窗控件
            floatView = LayoutInflater.from(this).inflate(R.layout.dialog_coordinates, null);
            FrameLayout group = floatView.findViewById(R.id.group);
            info = floatView.findViewById(R.id.text2);

            group.setOnTouchListener(new FloatingOnTouchListener());
            // 设置LayoutParam
            layoutParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            }
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            layoutParams.format = PixelFormat.RGBA_8888;
            layoutParams.width = ScreenUtil.dip2px(this, 50);
            layoutParams.height = ScreenUtil.dip2px(this, 90);

            // 将悬浮窗控件添加到WindowManager
            windowManager.addView(floatView, layoutParams);
        }
    }

    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getRawX();
                    int nowY = (int) event.getRawY();
                    int movedX = nowX - x;
                    int movedY = nowY - y;
                    x = nowX;
                    y = nowY;
                    layoutParams.x = layoutParams.x + movedX;
                    layoutParams.y = layoutParams.y + movedY;

                    info.setText("x:" + x + "\ny:" + y);
                    // 更新悬浮窗控件布局
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        windowManager.removeView(floatView);
    }
}