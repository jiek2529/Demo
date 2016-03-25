package jiek.demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.simcpux.uikit.JkAlert;

import java.util.List;

import jiek.demo.service.MyIntentService;
import jiek.demo.service.MyService;

/**
 * A login screen that offers login via email/password.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /*public BaseActivity() {
        super();
        l("BaseActivity init");
    }*/
    private static int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        l("onCreate 1");
        setTitle(getClass().getSimpleName());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        l("setContentView");
        a();
    }

    private void a() {
        try {
            ((TextView) findViewById(R.id.tv)).setText(count++ +" :: "+ getClassName()+"\n\n" +getTaskId());//+ getStackString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void le(String msg) {
        Log.w(getClassName(), msg);
    }

    private String getStackString() {
        StringBuilder sb = new StringBuilder();
        try {
            ActivityManager mActivityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

//获得系统运行的进程
            /*List<ActivityManager.RunningAppProcessInfo> appList1 = mActivityManager
                    .getRunningAppProcesses();
            sb.append(appList1.size()+" = size      sys app List\n");
            for (ActivityManager.RunningAppProcessInfo running : appList1) {
                le(running.processName);
                sb.append(running.processName +" : pid="+running.pid+"\n");
            }
            le("================");

//获得当前正在运行的service
            List<ActivityManager.RunningServiceInfo> appList2 = mActivityManager
                    .getRunningServices(100);
            for (ActivityManager.RunningServiceInfo running : appList2) {
                le(running.service.getClassName());
                sb.append(running.service.getClassName() + " :: size = " + appList2.size() + "  正在运行的service \n");
            }
            le("================");

//获得最近运行的应用
            List<ActivityManager.RecentTaskInfo> appList4 = mActivityManager
                    .getRecentTasks(100, 1);
            for (ActivityManager.RecentTaskInfo running : appList4) {
                if(running.origActivity == null) {le("RecentTaskInfo size: "+appList4.size());continue;}
                le(running
                        .origActivity
                        .getClassName());
                sb.append(running.origActivity.getClassName()+" :: size = "+ appList4.size()+"正在运行的应用\n");
            }*/
            le("================" + getTaskId());

//获得当前正在运行的activity
            List<ActivityManager.RunningTaskInfo> appList3 = mActivityManager
                    .getRunningTasks(1000);
            for (ActivityManager.RunningTaskInfo running : appList3) {
                if (running.baseActivity == null) {
                    le("RunningTaskInfo size: " + appList3.size());
                    continue;
                }
                le(running
                        .baseActivity
                        .getClassName());
                sb.append(running.baseActivity.getClassName() + " :: size = " + appList3.size() + "正在运行的Activity\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getClassName() {
        return this.getClass().getSimpleName();
    }

    protected void l(@NonNull String msg) {
        Log.e(getClassName() + " ::  ==> taskId:" + getTaskId(), msg);
    }

    protected void t(@NonNull String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        l("onCreate 2");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        l("onRestoreInstanceState 1");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        l("onRestoreInstanceState 3");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        l("onSaveInstanceState 3");
    }
/*
    @Override
    public Uri onProvideReferrer() {
        l("onProvideReferrer");
        return super.onProvideReferrer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        l("onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
//        l("onCreatePanelView");
        return super.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        l("onCreateThumbnail");
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        l("onDetachedFromWindow");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        l("onAttachedToWindow");
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        l("onActionModeFinished");
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        l("onActionModeStarted");
    }

    @Override
    public boolean navigateUpTo(Intent upIntent) {
        l("navigateUpTo");
        return super.navigateUpTo(upIntent);
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        l("moveTaskToBack");
        return super.moveTaskToBack(nonRoot);
    }

    @Override
    public boolean isDestroyed() {
        l("isDestroyed");
        return super.isDestroyed();
    }

    @Override
    public boolean isFinishing() {
        l("isFinishing");
        return super.isFinishing();
    }

    @Override
    public Window getWindow() {
//        l("getWindow");
        return super.getWindow();
    }

    @Override
    public VoiceInteractor getVoiceInteractor() {
        l("getVoiceInteractor");
        return super.getVoiceInteractor();
    }

    @Override
    public int getTaskId() {
//        Log.e(getClassName(), "getTaskId");
        return super.getTaskId();
    }

    @Override
    public Intent getIntent() {
        l("getIntent");
        return super.getIntent();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
//        l("onCreateView 3");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        l("onCreateView 4");
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        l("onPostResume");
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        l("onTitleChanged");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        l("onContentChanged");
    }


    @Override
    public AppCompatDelegate getDelegate() {
//        l("AppCompatDelegate");
        return super.getDelegate();
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        l("addContentView");
    }*/

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        l("onConfigurationChanged");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        l("onDestroy");
    }
    protected void jumpActivity(Class<? extends Activity> _class) {
        try {
            startActivity(new Intent(this, _class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click_singleInstance(View view) {
        jumpActivity(SingleInstanceActivity.class);
    }

    public void click_MainActivity(View view) {
        jumpActivity(MainActivity.class);
    }

    public void click_singleTask(View view) {
        jumpActivity(SingleTaskActivity.class);
    }

    public void click_singleTop(View view) {
        jumpActivity(SingleTopActivity.class);
    }

    public void click_LoginActivity(View view) {
        jumpActivity(LoginActivity.class);
    }

    public void click_standardA(View view) {
        jumpActivity(StandardActivity.class);
    }

    public void click_standardB(View view) {
        jumpActivity(StandardBActivity.class);
    }

    public void click_standardC(View view) {
        jumpActivity(StandardCActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        l("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        l("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        l("onPause");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        l("onLowMemory");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        l("onNewIntent");
//        a();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        l("onBackPressed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        l("onStop");
    }

    @Override
    public void finish() {
        super.finish();
        l("finish");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        l("onRestart");
    }

    public void click_Dialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("alertDialog")
        .setPositiveButton("confirm", null)
        .show();
//        JkAlert.showAlert(this, R.string.get_from_wx_title, R.string.send, null, null);
    }

    public void click_DialogActivity(View view) {
//        String[] options = {};
//        JkAlert.showAlert(this, "JkAlert", options, null, null);
        startActivity(new Intent(this, DialogActivity.class));
    }

    public void click_startService(View view) {
        startService(new Intent(this, MyService.class));
        startService(new Intent(this, MyService.class));
        t("startService complete!");
    }

    public void click_startIntentService(View view) {
        startService(new Intent(this, MyIntentService.class));
        t("startIntentService complete!");
    }
}

