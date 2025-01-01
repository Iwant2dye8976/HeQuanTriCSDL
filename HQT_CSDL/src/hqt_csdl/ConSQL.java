/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hqt_csdl;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.crypto.spec.PSource;
import javax.swing.JOptionPane;


public class ConSQL implements INhaSach{
    static Connection cn;
    private ResultSet rs;
    
    public void getCon() {
        try {
            if (cn == null || cn.isClosed()) {
                cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QlyNhaSach;user=sa;password=123;encrypt=true;trustServerCertificate=true");
                System.out.println("ket noi thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet GetSach(){
        getCon();
        rs = null;
        String querry = "Select * from Sach";
        try{
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void AddSach(String Title, String Author, String Category, String Publisher, double Price, int StockQuantity){
        String sql = "Insert into Sach(TieuDe, TacGia, TheLoai, NhaXuatBan, Gia, SoLuongTon) Values(?,?,?,?,?,?)";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, Title);
            ps.setString(2, Author);
            ps.setString(3, Category);
            ps.setString(4, Publisher);
            ps.setDouble(5, Price);
            ps.setInt(6, StockQuantity);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Thêm thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void UpdateSach(int BookID, String Title, String Author, String Category, String Publisher, double Price, int StockQuantity){
        String sql = "Update Sach set TieuDe=?, TacGia=?, TheLoai=?, NhaXuatBan=?, Gia=?, SoLuongTon=? Where MaSach=?";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, Title);
            ps.setString(2, Author);
            ps.setString(3, Category);
            ps.setString(4, Publisher);
            ps.setDouble(5, Price);
            ps.setInt(6, StockQuantity);
            ps.setInt(7, BookID);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
        
    public void DeleteSach(int BookID) {
        String sql = "Delete Sach Where MaSach=?";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, BookID);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Xóa thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public ResultSet GetKhachHang(){
        getCon();
        rs = null;
        String querry = "Select * from KhachHang";
        try{
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void AddKhachHang(String HoTen, String SoDienThoai, String Email, String DiaChi){
        String sql = "Insert into KhachHang(HoTen, SoDienThoai, Email, DiaChi) Values(?,?,?,?)";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, SoDienThoai);
            ps.setString(3, Email);
            ps.setString(4, DiaChi);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Thêm thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void UpdateKhachHang(int MaKhachHang, String HoTen, String SoDienThoai, String Email, String DiaChi){
        String sql = "Update KhachHang set HoTen=?, SoDienThoai=?, Email=?, DiaChi=? Where MaKhachHang=?";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, SoDienThoai);
            ps.setString(3, Email);
            ps.setString(4, DiaChi);
            ps.setInt(5, MaKhachHang);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
        
    public void DeleteKhachHang(int MaKhachHang) {
        String sql = "Delete KhachHang Where MaKhachHang=?";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaKhachHang);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Xóa thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public ResultSet GetNhanVien(){
        getCon();
        rs = null;
        String querry = "Select * from NhanVien";
        try{
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void AddNhanVien(String HoTen, String ChucVu, String SoDienThoai, String Email){
        String sql = "Insert into NhanVien(HoTen, ChucVu,SoDienThoai, Email) Values(?,?,?,?)";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, ChucVu);
            ps.setString(3, SoDienThoai);
            ps.setString(4, Email);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Thêm thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void UpdateNhanVien(int MaNhanVien, String HoTen, String ChucVu, String SoDienThoai, String Email){
        String sql = "Update NhanVien set HoTen=?, ChucVu=?, SoDienThoai=?, Email=? Where MaNhanVien=?";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, ChucVu);
            ps.setString(3, SoDienThoai);
            ps.setString(4, Email);
            ps.setInt(5, MaNhanVien);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
        
    public void DeleteNhanVien(int MaNhanVien) {
        String sql = "Delete KhachHang Where MaKhachHang=?";
        getCon();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaNhanVien);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Xóa thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
