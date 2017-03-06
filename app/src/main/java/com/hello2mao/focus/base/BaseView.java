package com.hello2mao.focus.base;

/**
 * View基类
 * 让activity和fragment都实现这个接口
 * presenter中attachView，从而能从presenter访问activity和fragment中的方法，即访问View
 */
public interface BaseView {

    void showError(String msg);

}
