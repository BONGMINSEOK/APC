package com.kdn.apc.repository;

public class Node {
    private int sName;
    private int iParent;
    private int iId;

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public int getsName() {
        return sName;
    }

    public void setsName(int sName) {
        this.sName = sName;
    }

    public int getiParent() {
        return iParent;
    }

    public void setiParent(int iParent) {
        this.iParent = iParent;
    }

}
