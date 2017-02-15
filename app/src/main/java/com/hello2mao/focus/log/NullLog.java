package com.hello2mao.focus.log;

public class NullLog implements BasicLog {

    public NullLog() {
    }

    public void debug(String s) {
    }

    public void info(String s) {
    }

    public void verbose(String s) {
    }

    public void error(String s) {
    }

    public void error(String s, Throwable throwable) {
    }

    public void warning(String s) {
    }

    public int getLevel() {
        return DEBUG;
    }

    public void setLevel(int i) {
    }
}