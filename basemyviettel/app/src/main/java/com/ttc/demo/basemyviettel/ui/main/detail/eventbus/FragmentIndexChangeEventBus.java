package com.ttc.demo.basemyviettel.ui.main.detail.eventbus;

public
class FragmentIndexChangeEventBus {
    private int position;

    public FragmentIndexChangeEventBus(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
