package jiek.demo;

import android.app.Activity;
import android.content.ServiceConnection;
import android.os.Bundle;

import jiek.demo.service.MyService;

public class StartActivity extends BaseActivity {

    private MyService myService;
    private ServiceConnection sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(false)return;



//        startService(new Intent(this, MyIntentService.class));
//        startService(new Intent(this, MyIntentService.class));
//        startService(new Intent(this, MyService.class));
//        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void jumpActivity(Class<? extends Activity> _class) {
        super.jumpActivity(_class);
        finish();
//        startService(new Intent("jiek.demo.action.FOO"));
//        startService(new Intent("jiek.demo.action.myservice"));
//        sc = new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                myService = ((MyService.LocalBinder) service).getService();
//                String recStr = ((MyService.LocalBinder) service).stringToSend;
//                l("bind getString :: "+recStr);
//                myService.a();
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        };
//        bindService(new Intent("jiek.demo.action.myservice"), sc, 0);
    }
}
