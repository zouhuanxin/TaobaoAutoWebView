package com.zhx.taobaoautowebview.activity.taobao;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zhx.taobaoautowebview.R;
import com.zhx.taobaoautowebview.util.TimeUtil;
import com.zhx.taobaoautowebview.util.accessutil.AccessibilityOperator;

public class TaobaoHjkxActivity extends AppCompatActivity {

    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao_hjkx);
        initView();
        initData();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        updateText("三秒后执行任务");
                        Thread.sleep(3000);
                        updateText("任务开始");
                        task();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void task() throws InterruptedException {
        //卷1
        AccessibilityOperator.getInstance().dispatchGestureClick(916,953);
        updateText("卷1执行完毕");
        Thread.sleep(200);
        //卷2
        AccessibilityOperator.getInstance().dispatchGestureClick(347,1261);
        updateText("卷2执行完毕");
        Thread.sleep(200);
        //卷3
        AccessibilityOperator.getInstance().dispatchGestureClick(902,1261);
        updateText("卷3执行完毕");
        Thread.sleep(200);
        //刷新
        AccessibilityOperator.getInstance().dispatchGestureClick(753,177);
        updateText("刷新完毕\n");
        Thread.sleep(2500);
    }

    private void updateText(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                content.setText(TimeUtil.getCurrentTime() + "\n" + msg + "\n" + content.getText());
            }
        });
    }
}