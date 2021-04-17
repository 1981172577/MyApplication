package com.example.myapplication.setvices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.IMyAidlInterface;

public class MyService extends Service {

    private String string;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public String getName() throws RemoteException {
            return "text";
        }

        @Override
        public void setName(String name) throws RemoteException {
            string = name;
            Log.d("MyService","name:"+name);
        }
    }
}
