package com.ttc.demo.basemyviettel.ui.main.detail.eventbus;

public
class StepIndexEventBus {
    private  boolean isShowed;
    private int position;
    private int requestFocus;

    public StepIndexEventBus(int number, boolean isShowed) {
        this.position = number;
        this.isShowed = isShowed;
    }

    public StepIndexEventBus(int number, boolean isShowed, int requestFocus) {
        this.isShowed = isShowed;
        this.position = number;
        this.requestFocus = requestFocus;
    }

    public int getRequestFocus() {
        //  3 -> focus vào textbox nhập số liên hệ, 2 -> focus vào textbox nhập số giấy tờ
        return requestFocus;
    }

    public int getPosition() {
        return position;
    }

    public boolean isShowed() {
        return isShowed;
    }
}
