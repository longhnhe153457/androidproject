package com.example.projectnews.model;

public class New {
    private int ID;
    private String TenBao;
    private String NoiDung;
    private String Anh;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenBao() {
        return TenBao;
    }

    public void setTenBao(String tenBao) {
        TenBao = tenBao;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public New(int ID, String tenBao, String noiDung, String anh) {
        this.ID = ID;
        TenBao = tenBao;
        NoiDung = noiDung;
        Anh = anh;
    }

    public New(String tenBao, String noiDung, String anh) {
        TenBao = tenBao;
        NoiDung = noiDung;
        Anh = anh;
    }

    public New(String tenBao, String noiDung) {
        TenBao = tenBao;
        NoiDung = noiDung;
    }
}
