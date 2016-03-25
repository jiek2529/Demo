package jiek.demo.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {
    private int count = 100;

    private IMyAidlInterface.Stub impl = new AidlImpl();
    private IBinder mBinder = new LocalBinder();

    public MyService() {
    }

    public void a() {
        l("MyService a()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        Log.i("TAG", "onBind~~~~~~~~~~~~");
//		IBinder myIBinder = null;
//		if ( null == mBinder )
//            mBinder = new LocalBinder();
////		return myIBinder;
//        return mBinder;
        return impl;
    }

    private void l(String msg) {
        Log.e(getClassName(), msg);
    }

    private String getClassName() {
        return this.getClass().getSimpleName();
    }

    private void doSomething() {
        l("doSomething start!");
        try {
            Thread.sleep(1000);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l("doSomething complete!");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        l("onCreate");
        try {
            restartService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restartService() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //包装需要执行Service的Intent
        Intent intent = new Intent(this, this.getClass());
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //触发服务的起始时间
        long triggerAtTime = SystemClock.elapsedRealtime();
        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, 6 * 1000, pendingIntent);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        l("onDestroy 不死service");
        startService(new Intent("jiek.demo.action.myservice"));
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        l("onLowMemory");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        l("onStartCommand");
        return START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        l("onTaskRemoved");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        l("onTrimMemory");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        l("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        l("onRebind");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        l("onStart");
        doSomething();
    }

    public class LocalBinder extends Binder {
        public String stringToSend = "I'm the test String";

        public MyService getService() {
            Log.i("TAG", "getService ---> " + MyService.this);
            return MyService.this;
        }

        public void a() {
            try {
                getService().a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
