package com.example.projectnews.model;

public class chuyenmuc {
    private  String tenchuyenmuc;
    private int hinhanhchuyenmucl;

    public chuyenmuc(String tenchuyenmuc, int hinhanhchuyenmucl) {
        this.tenchuyenmuc = tenchuyenmuc;
        this.hinhanhchuyenmucl = hinhanhchuyenmucl;
    }

    public String getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public void setTenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
    }

    public int getHinhanhchuyenmucl() {
        return hinhanhchuyenmucl;
    }

    public void setHinhanhchuyenmucl(int hinhanhchuyenmucl) {
        this.hinhanhchuyenmucl = hinhanhchuyenmucl;
    }
}
