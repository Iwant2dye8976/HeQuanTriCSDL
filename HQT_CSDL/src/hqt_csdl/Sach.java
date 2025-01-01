/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hqt_csdl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sach {

    private int MaSach;
    private String TieuDe;
    private String TacGia;
    private String TheLoai;
    private String NhaXuatBan;
    private double Gia;
    private int SoLuongTon;

    public Sach(int MaSach, String TieuDe, String TacGia, String TheLoai, String NhaXuatBan, double Gia, int SoLuongTon) {
        this.MaSach = MaSach;
        this.TieuDe = TieuDe;
        this.TacGia = TacGia;
        this.TheLoai = TheLoai;
        this.NhaXuatBan = NhaXuatBan;
        this.Gia = Gia;
        this.SoLuongTon = SoLuongTon;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String TieuDe) {
        this.TieuDe = TieuDe;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String TacGia) {
        this.TacGia = TacGia;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String NhaXuatBan) {
        this.NhaXuatBan = NhaXuatBan;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }
}
