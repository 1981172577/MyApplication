package com.example.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.LayoutInflaterCompat;

import com.example.myapplication.widget.GrayFrameLayout;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull final Activity activity, @Nullable Bundle savedInstanceState) {



        try {
            LayoutInflaterCompat.setFactory2(activity.getLayoutInflater(), new LayoutInflater.Factory2() {
                @Override
                public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                    if ("FrameLayout".equals(name)) {
                        int count = attrs.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = attrs.getAttributeName(i);
                            String attrValue = attrs.getAttributeValue(i);
                            if (attrName.equals("id")) {
                                int id = Integer.parseInt(attrValue.substring(1));
                                String idValue = context.getResources().getResourceName(id);
                                if ("android:id/content".equals(idValue)) {
                                    GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
                                    return grayFrameLayout;
                                }
                            }
                        }
                    }
                    return null;
                }

                @Override
                public View onCreateView(String name, Context context, AttributeSet attrs) {
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
