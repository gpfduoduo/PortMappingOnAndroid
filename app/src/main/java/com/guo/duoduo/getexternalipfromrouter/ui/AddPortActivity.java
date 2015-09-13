package com.guo.duoduo.getexternalipfromrouter.ui;


import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guo.duoduo.getexternalipfromrouter.MyApplication;
import com.guo.duoduo.getexternalipfromrouter.MyController;
import com.guo.duoduo.getexternalipfromrouter.R;
import com.guo.duoduo.getexternalipfromrouter.constant.UpnpConstant;
import com.guo.duoduo.getexternalipfromrouter.entity.MappingEntity;
import com.guo.duoduo.getexternalipfromrouter.sdk.UpnpCommand;
import com.guo.duoduo.getexternalipfromrouter.utils.NetworkUtils;


/**
 * Created by 郭攀峰 on 2015/9/13.
 */
public class AddPortActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String tag = AddPortActivity.class.getSimpleName();

    private EditText protocol;
    private EditText internal_ip;
    private EditText internal_port;
    private EditText external_port;
    private EditText description;

    private AddPortHandler handler;
    private MyController myController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addport);

        handler = new AddPortHandler(this);
        myController = new MyController(AddPortActivity.class.getName(), handler);
        initWidget();
    }

    private void initWidget()
    {
        protocol = (EditText) findViewById(R.id.add_port_protocol);
        internal_ip = (EditText) findViewById(R.id.add_port_internal_ip);
        internal_port = (EditText) findViewById(R.id.add_port_internal_port);
        external_port = (EditText) findViewById(R.id.add_port_external_port);
        description = (EditText) findViewById(R.id.add_port_description);

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);

        String ip = NetworkUtils.getLocalIp(MyApplication.getInstance());
        internal_ip.setText(ip);
        protocol.setText("TCP");

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        myController.destroy();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.cancel :
                finish();
                break;
            case R.id.add :
                final String pro = protocol.getText().toString();
                final String ip = internal_ip.getText().toString();
                final String in_port = internal_port.getText().toString();
                final String ex_port = external_port.getText().toString();
                if (TextUtils.isEmpty(pro) || TextUtils.isEmpty(ip)
                    || TextUtils.isEmpty(in_port) || TextUtils.isEmpty(ex_port))
                {
                    Toast.makeText(AddPortActivity.this, "填写完整信息", Toast.LENGTH_SHORT)
                            .show();
                }
                final String des = TextUtils.isEmpty(description.getText().toString())
                    ? "Your&#32;new&#32;port&#32;mapping"
                    : description.getText().toString();
                new Thread()
                {
                    public void run()
                    {
                        MappingEntity entity = new MappingEntity();
                        entity.NewPortMappingDescription = des;
                        entity.NewInternalPort = in_port;
                        entity.NewExternalPort = ex_port;
                        entity.NewProtocol = pro;
                        entity.NewInternalClient = ip;
                        entity.NewPortMappingDescription = des;
                        UpnpCommand.addPortMapping(MyApplication.curDevice, entity);
                    }
                }.start();
                break;
        }
    }

    private static class AddPortHandler extends Handler
    {
        private WeakReference<AddPortActivity> weakReference;

        public AddPortHandler(AddPortActivity addPortActivity)
        {
            this.weakReference = new WeakReference<>(addPortActivity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            AddPortActivity activity = weakReference.get();
            if (activity == null)
                return;
            if (activity.isFinishing())
                return;

            switch (msg.what)
            {
                case UpnpConstant.MSG.add_port_fail :
                    Toast.makeText(MyApplication.getInstance(),
                        activity.getResources().getString(R.string.add_port_fail),
                        Toast.LENGTH_SHORT).show();
                    activity.finish();
                    break;
                case UpnpConstant.MSG.add_port_ok :
                    Toast.makeText(MyApplication.getInstance(),
                        activity.getResources().getString(R.string.add_port_success),
                        Toast.LENGTH_SHORT).show();
                    activity.finish();
                    break;
            }
        }
    }
}
