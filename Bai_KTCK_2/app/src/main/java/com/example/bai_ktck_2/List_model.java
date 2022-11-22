package com.example.bai_ktck_2;

import java.io.Serializable;

public class List_model implements Serializable {
    String tenkh,tentg,mausac,dactinh,img;

    List_model(){

    }
    public List_model(String tenkh, String tentg, String mausac, String dactinh, String img) {
        this.tenkh = tenkh;
        this.tentg = tentg;
        this.mausac = mausac;
        this.dactinh = dactinh;
        this.img = img;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTentg() {
        return tentg;
    }

    public void setTentg(String tentg) {
        this.tentg = tentg;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getDactinh() {
        return dactinh;
    }

    public void setDactinh(String dactinh) {
        this.dactinh = dactinh;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
