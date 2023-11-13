package com.example.demo.util;

import com.example.demo.model.po.AppPO;

public class AppStore {
    private AppStore(){
    }

    private static final AppStore instance = new AppStore();

    private final ThreadLocal<AppPO> threadLocal = new ThreadLocal<>();

    public static AppStore getInstance() {
        return instance;
    }

    public void set(AppPO appPO) {
        threadLocal.set(appPO);
    }

    public AppPO get() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}
