package com.haha.selfserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/**
 * 不依赖于AIDL实现进程间通讯
 *
 */
public class CalcPlusService extends Service {
    
    private final static String TAG = "CalcPlusService";
    
    private final static String DESCRIPTOR = "CalcPlusService";
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return mBinder;
    }
    
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
    
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
    
    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind");
        super.onRebind(intent);
    }
    
    private MyBinder mBinder = new MyBinder();
    
    /**
     * 自己实现服务器端，所有我们自己定义了一个Binder子类，然后复现了其onTransact方法；
     * 我们指定服务的标识为CalcPlusServcie,然后0x110为乘；0x111为除；
     * 
     */
    private class MyBinder extends Binder{
        
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            switch (code) {
                case 0x110:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    int _result = _arg0*_arg1;
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 0x111:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg2;
                    _arg2 = data.readInt();
                    int _arg3;
                    _arg3 = data.readInt();
                    int _result1 = _arg2 / _arg3;
                    reply.writeNoException();
                    reply.writeInt(_result1);
                    return true;
                default:
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

}
