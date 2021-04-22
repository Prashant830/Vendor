package com.example.vender;

public class Member {
    private  Integer mage;
    private  String  name;
    private  Integer discount;
    private  Integer dc;
    private  String  mcat;
    private  Integer gst;

    public Integer getDc() {
        return dc;
    }

    public void setDc(Integer dc) {
        this.dc = dc;
    }

    public Integer getGst() {
        return gst;
    }

    public void setGst(Integer gst) {
        this.gst = gst;
    }

    public String getMcat() {
        return mcat;
    }

    public void setMcat(String mcat) {
        this.mcat = mcat;
    }

    public Member() {
    }

    public Integer getMage() {
        return mage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMage(Integer mage) {
        this.mage = mage;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
