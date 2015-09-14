package com.haha.selfserver;

import com.haha.selfserver.aidl.ICalcAIDL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
/**
 * AIDL服务器端；
 * 1.新建一个ICalcAIDL.aidl的文件；主要后缀名为aidl；里面定义跨进程调用的接口方法；
 * 2.然后系统会在gen目录下自动生成对应的java接口文件；
 * 3.定义用于进程间通信的CalcService；
 * 4.在此Service中，使用生成的ICalcAIDL创建一个mBinder对象，并在onBinder方法中返回；
 * 5.在AndroidManifest.xml中注册
 * 6.记得把ICalcAIDL.aidl带包一起拷贝到客户端；
 *
 */
public class CalcService extends Service {
    
    private final static String TAG = "CalcServide";
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return mBinder;
    }
    
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
    
    @Override
    public boolean onUnbind(Intent intent){
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
    
    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }
    
    /**
     * ICalcAIDL.Stub类是Binder的子类，也即是服务器端实际上是一个Binder类的实例
     */
    private final ICalcAIDL.Stub mBinder = new ICalcAIDL.Stub() {
        
        @Override
        public int min(int x, int y) throws RemoteException {            
            return x - y;
        }
        
        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }
    };

}
