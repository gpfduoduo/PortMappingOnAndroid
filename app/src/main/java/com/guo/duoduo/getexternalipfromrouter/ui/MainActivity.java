package com.guo.duoduo.getexternalipfromrouter.ui;


import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.guo.duoduo.getexternalipfromrouter.MyApplication;
import com.guo.duoduo.getexternalipfromrouter.MyController;
import com.guo.duoduo.getexternalipfromrouter.R;
import com.guo.duoduo.getexternalipfromrouter.constant.UpnpConstant;
import com.guo.duoduo.getexternalipfromrouter.service.UpnpService;
import com.guo.duoduo.getexternalipfromrouter.utils.NetworkUtils;
import com.guo.duoduo.scrolltextview.ScrollTextView;
import com.guo.duoduo.togicloadingview.TogicLoadingView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private ScrollTextView content;
    private TogicLoadingView loadingView;
    private MyController myController;
    private Button start_find;
    private LinearLayout port_layout;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    private StringBuffer strContent = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Handler handler = new MainHandler(MainActivity.this);
        myController = new MyController(MainActivity.class.getName(), handler);

        initWidget();
    }

    private void initWidget()
    {
        start_find = (Button) findViewById(R.id.start_find_router);
        start_find.setOnClickListener(this);

        port_layout = (LinearLayout) findViewById(R.id.port_layout);
        port_layout.setVisibility(View.GONE);

        View rootView = findViewById(R.id.include_content);
        content = (ScrollTextView) rootView.findViewById(R.id.content);
        loadingView = (TogicLoadingView) rootView.findViewById(R.id.loading_view);
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.port_add :
                break;
            case R.id.port_delete :
                break;
            case R.id.start_find_router :
                content.setText("");
                strContent.delete(0, strContent.length());

                String ip = NetworkUtils.getLocalIp(MyApplication.getInstance());
                String time = format.format(new Date());
                strContent
                        .append(time)
                        .append(": ")
                        .append(
                            MyApplication.getInstance().getResources()
                                    .getString(R.string.find_local_ip)).append("[")
                        .append(ip).append("]").append("\r\n");
                content.setText(strContent);

                startService(new Intent(MainActivity.this, UpnpService.class));
                loadingView.setVisibility(View.VISIBLE);
                start_find.setClickable(false);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        myController.destroy();
        stopService(new Intent(MainActivity.this, UpnpService.class));
    }

    private static class MainHandler extends Handler
    {

        private WeakReference<MainActivity> weakReference;

        public MainHandler(MainActivity activity)
        {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            MainActivity activity = weakReference.get();
            if (activity == null)
                return;
            if (activity.isFinishing())
                return;

            switch (msg.what)
            {
                case UpnpConstant.MSG.find_end :
                {
                    activity.start_find.setClickable(true);
                    activity.loadingView.setVisibility(View.GONE);
                    activity.stopService(new Intent(activity, UpnpService.class));

                    if (activity.port_layout.getVisibility() != View.VISIBLE)
                        activity.port_layout.setVisibility(View.VISIBLE);
                    break;
                }

                case UpnpConstant.MSG.find_ok :
                {
                    String content = (String) msg.obj;
                    String time = activity.format.format(new Date());
                    activity.strContent.append(time).append(": ").append(content)
                            .append("\r\n");
                    activity.content.setText(activity.strContent);
                }
                    break;
                case UpnpConstant.MSG.find_start :
                {
                    String content = (String) msg.obj;
                    String time = activity.format.format(new Date());
                    activity.strContent.append(time).append(": ").append(content)
                            .append("\r\n");
                    activity.content.setText(activity.strContent);
                    break;
                }

            }
        }
    }
}
