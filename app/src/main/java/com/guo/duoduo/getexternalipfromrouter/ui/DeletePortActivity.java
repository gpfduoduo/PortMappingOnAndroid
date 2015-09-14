package com.guo.duoduo.getexternalipfromrouter.ui;


import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.guo.duoduo.getexternalipfromrouter.MyApplication;
import com.guo.duoduo.getexternalipfromrouter.MyController;
import com.guo.duoduo.getexternalipfromrouter.R;
import com.guo.duoduo.getexternalipfromrouter.entity.MappingEntity;
import com.guo.duoduo.getexternalipfromrouter.sdk.UpnpCommand;


/**
 * Created by 郭攀峰 on 2015/9/13.
 */
public class DeletePortActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String tag = DeletePortActivity.class.getSimpleName();

    private Spinner spinner;
    private MyController myController;
    private DelHandler handler;
    protected ArrayAdapter<CharSequence> mAdapter;
    private static String[] itemsArray;
    private MappingEntity curEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_deleteport);

        handler = new DelHandler(DeletePortActivity.this);
        myController = new MyController(DeletePortActivity.class.getName(), handler);
        initWidget();
    }

    private void initWidget()
    {
        spinner = (Spinner) findViewById(R.id.spinner);
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        Button del = (Button) findViewById(R.id.del);
        del.setOnClickListener(this);

        itemsArray = new String[MyApplication.itemList.size()];

        if (MyApplication.itemList.size() > 0)
        {
            for (int i = 0; i < MyApplication.itemList.size(); i++)
            {
                itemsArray[i] = MyApplication.itemList.get(i).NewExternalPort;
            }
        }

        mAdapter = new ArrayAdapter<CharSequence>(DeletePortActivity.this,
            android.R.layout.simple_spinner_item, itemsArray);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i,
                    long l)
            {
                curEntity = MyApplication.itemList.get(i);
                Log.d(tag, "current selected entity=" + curEntity.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        myController.destroy();
        MyApplication.itemList.clear();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.cancel :
                finish();
                break;
            case R.id.del :
                if (curEntity == null || MyApplication.curDevice == null)
                    return;

                new Thread()
                {
                    public void run()
                    {

                        if (UpnpCommand.DeletePortMapping(MyApplication.curDevice,
                            curEntity.NewExternalPort, curEntity.NewRemoteHost,
                            curEntity.NewProtocol))
                        {
                            Message msg = Message.obtain();
                            msg.what = RESULT_OK;
                            handler.sendMessage(msg);
                            handler.sendMessage(msg);
                        }
                        else
                        {
                            Message msg = Message.obtain();
                            msg.what = RESULT_CANCELED;
                            handler.sendMessage(msg);
                        }
                    }
                }.start();

                break;
        }
    }

    private static class DelHandler extends Handler
    {
        private WeakReference<DeletePortActivity> weakReference;

        public DelHandler(DeletePortActivity activity)
        {
            this.weakReference = new WeakReference<DeletePortActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            DeletePortActivity activity = weakReference.get();
            if (activity == null)
                return;
            if (activity.isFinishing())
                return;

            switch (msg.what)
            {
                case RESULT_OK :
                    Toast.makeText(activity, activity.getString(R.string.del_success),
                        Toast.LENGTH_SHORT).show();
                case RESULT_CANCELED :
                    Toast.makeText(activity, activity.getString(R.string.del_success),
                        Toast.LENGTH_SHORT).show();
                default :
                    activity.finish();
            }
        }
    }
}
