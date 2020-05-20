package com.jimbolix.april.common.utils;

import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/20 10:48
 * @Description: 用户自定义上下问对象
 */
public class UserContextHolder {

    private ThreadLocal<Map<String, String>> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    public static UserContextHolder getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserContextHolder instance = new UserContextHolder();
    }

    public Map<String, String> getContext() {
        return threadLocal.get();
    }

    public void setContext(Map<String, String> map) {
        this.threadLocal.set(map);
    }

    public void clearContext(){
        this.threadLocal.remove();
    }
}