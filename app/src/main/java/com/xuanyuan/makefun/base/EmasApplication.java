package com.xuanyuan.makefun.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.alibaba.sdk.android.feedback.util.ErrorCode;
import com.alibaba.sdk.android.feedback.util.FeedbackErrorCallback;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * 作者：罗发新
 * 时间：2019/8/1 0001    星期四
 * 邮件：424533553@qq.com
 * 说明：application
 */
public class EmasApplication extends Application {
    private static final String TAG = "AppApplication";

    //hotfix init need attr
    public interface MsgDisplayListener {
        void handle(String msg);
    }
    public static MsgDisplayListener msgDisplayListener = null;

    public static StringBuilder cacheMsg = new StringBuilder();

    @Override
    public void onCreate() {
        super.onCreate();

//        initManService();
//        initFeedbackService();
//        initHttpDnsService();
//        // 热更新
//        initHotfix();
//        //推送服务，一定限制内是免费的
//        initPushService(getApplicationContext());
//
//        //查询并下载 补丁
//        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
//        SophixManager.getInstance().queryAndLoadNewPatch();


    }

    /**
     * 初始化Mobile Analytics服务
     */
    private void initManService() {
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();
        // 打开调试日志
        manService.getMANAnalytics().turnOnDebug();
        manService.getMANAnalytics().setAppVersion("3.0");
        // MAN初始化方法之一，通过插件接入后直接在下发json中获取appKey和appSecret初始化
        manService.getMANAnalytics().init(this, getApplicationContext());
        // MAN另一初始化方法，手动指定appKey和appSecret
        // String appKey = "******";
        // String appSecret = "******";
        // manService.getMANAnalytics().init(this, getApplicationContext(), appKey, appSecret);
        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作,详见文档5.4
        //manService.getMANAnalytics().turnOffCrashReporter();
        // 通过此接口关闭页面自动打点功能，详见文档4.2
        manService.getMANAnalytics().turnOffAutoPageTrack();
        // 设置渠道（用以标记该app的分发渠道名称），如果不关心可以不设置即不调用该接口，渠道设置将影响控制台【渠道分析】栏目的报表展现。如果文档3.3章节更能满足您渠道配置的需求，就不要调用此方法，按照3.3进行配置即可
        manService.getMANAnalytics().setChannel("某渠道");
        // 若AndroidManifest.xml 中的 android:versionName 不能满足需求，可在此指定；
        // 若既没有设置AndroidManifest.xml 中的 android:versionName，也没有调用setAppVersion，appVersion则为null
        //manService.getMANAnalytics().setAppVersion("2.0");
    }

    /**
     * 添加自定义的error handler
     */
    private void initFeedbackService() {

        FeedbackAPI.addErrorCallback(new FeedbackErrorCallback() {
            @Override
            public void onError(Context context, String errorMessage, ErrorCode code) {
                Toast.makeText(context, "ErrMsg is: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        FeedbackAPI.addLeaveCallback(new Callable() {
            @Override
            public Object call() throws Exception {
                Log.d("DemoApplication", "custom leave callback");
                return null;
            }
        });
        /**
         * 建议放在此处做初始化
         */
        //默认初始化
        FeedbackAPI.init(this);
        //FeedbackAPI.init(this, "DEFAULT_APPKEY", "DEFAULT_APPSECRET");
        /**
         * 在Activity的onCreate中执行的代码
         * 可以设置状态栏背景颜色和图标颜色，这里使用com.githang:status-bar-compat来实现
         */
        //FeedbackAPI.setActivityCallback(new IActivityCallback() {
        //    @Override
        //    public void onCreate(Activity activity) {
        //        StatusBarCompat.setStatusBarColor(activity,getResources().getColor(R.color.aliwx_setting_bg_nor),true);
        //    }
        //});
        /**
         * 自定义参数演示
         */
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("loginTime", "登录时间");
            jsonObject.put("visitPath", "登陆，关于，反馈");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FeedbackAPI.setAppExtInfo(jsonObject);
        /**
         * 以下是设置UI
         */
        //设置默认联系方式
        FeedbackAPI.setDefaultUserContactInfo("13800000000");
        //沉浸式任务栏，控制台设置为true之后此方法才能生效
        FeedbackAPI.setTranslucent(true);
        //设置返回按钮图标
        //FeedbackAPI.setBackIcon(R.drawable.ali_feedback_common_back_btn_bg);
        //设置标题栏"历史反馈"的字号，需要将控制台中此字号设置为0
        FeedbackAPI.setHistoryTextSize(20);
        //设置标题栏高度，单位为像素
        FeedbackAPI.setTitleBarHeight(100);
    }

    private void initHttpDnsService() {
        // 初始化httpdns
        //HttpDnsService httpdns = HttpDns.getService(getApplicationContext(), accountID);
        HttpDnsService httpdns = HttpDns.getService(getApplicationContext());
        //this.setPreResoveHosts();
        // 允许过期IP以实现懒加载策略
        //httpdns.setExpiredIPEnabled(true);
    }

    /**
     * 热更新的使用
     */
    private void initHotfix() {
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                //.setAesKey("0123456789123456")
                .setEnableDebug(false)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                        if (msgDisplayListener != null) {
                            msgDisplayListener.handle(msg);
                        } else {
                            cacheMsg.append("\n").append(msg);
                        }

                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            // 使用头像和设施
                        }

                    }
                }).initialize();
    }

    /**
     *
     * 初始化云推送通道
     */
    private void initPushService(final Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(TAG, "init cloudchannel success");
                //setConsoleText("init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
                //setConsoleText("init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

    /**
     *
     */
    private void testConnect(){

    }

    /**
     *  重启软件
     * @param context
     */
    public void restartApplication(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());


//        2. 通过ActivityManager来重新启动应用程序：
        ActivityManager manager =(ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
        manager.restartPackage("com.example.test");

    }

}


