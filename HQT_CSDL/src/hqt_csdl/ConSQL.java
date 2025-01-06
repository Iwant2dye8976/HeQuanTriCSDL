/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hqt_csdl;

import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.sql.*;
import javax.crypto.spec.PSource;
import javax.swing.JOptionPane;

public class ConSQL implements INhaSach {

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

    public ResultSet GetSach() {
        getCon();
        rs = null;
        String querry = "Select * from Sach";
        try {
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void AddSach(String Title, String Author, String Category, String Publisher, double Price, int StockQuantity) {
        String sql = "Insert into Sach(TieuDe, TacGia, TheLoai, NhaXuatBan, Gia, SoLuongTon) Values(?,?,?,?,?,?)";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, Title);
            ps.setString(2, Author);
            ps.setString(3, Category);
            ps.setString(4, Publisher);
            ps.setDouble(5, Price);
            ps.setInt(6, StockQuantity);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Thêm thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateSach(int BookID, String Title, String Author, String Category, String Publisher, double Price, int StockQuantity) {
        String sql = "Update Sach set TieuDe=?, TacGia=?, TheLoai=?, NhaXuatBan=?, Gia=?, SoLuongTon=? Where MaSach=?";
        getCon();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteSach(int BookID) {
        String sql = "Delete Sach Where MaSach=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, BookID);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Xóa thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet GetKhachHang() {
        getCon();
        rs = null;
        String querry = "Select * from KhachHang";
        try {
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void AddKhachHang(String HoTen, String SoDienThoai, String Email, String DiaChi) {
        String sql = "Insert into KhachHang(HoTen, SoDienThoai, Email, DiaChi) Values(?,?,?,?)";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, SoDienThoai);
            ps.setString(3, Email);
            ps.setString(4, DiaChi);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Thêm thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateKhachHang(int MaKhachHang, String HoTen, String SoDienThoai, String Email, String DiaChi) {
        String sql = "Update KhachHang set HoTen=?, SoDienThoai=?, Email=?, DiaChi=? Where MaKhachHang=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, SoDienThoai);
            ps.setString(3, Email);
            ps.setString(4, DiaChi);
            ps.setInt(5, MaKhachHang);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteKhachHang(int MaKhachHang) {
        String sql = "Delete KhachHang Where MaKhachHang=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaKhachHang);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Xóa thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet GetNhanVien() {
        getCon();
        rs = null;
        String querry = "Select * from NhanVien";
        try {
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void AddNhanVien(String HoTen, String ChucVu, String SoDienThoai, String Email) {
        String sql = "Insert into NhanVien(HoTen, ChucVu,SoDienThoai, Email) Values(?,?,?,?)";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, ChucVu);
            ps.setString(3, SoDienThoai);
            ps.setString(4, Email);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Thêm thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateNhanVien(int MaNhanVien, String HoTen, String ChucVu, String SoDienThoai, String Email) {
        String sql = "Update NhanVien set HoTen=?, ChucVu=?, SoDienThoai=?, Email=? Where MaNhanVien=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, HoTen);
            ps.setString(2, ChucVu);
            ps.setString(3, SoDienThoai);
            ps.setString(4, Email);
            ps.setInt(5, MaNhanVien);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteNhanVien(int MaNhanVien) {
        String sql = "Delete KhachHang Where MaKhachHang=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaNhanVien);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Xóa thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet GetHoaDon() {
        getCon();
        rs = null;
        String querry = "Select * from HoaDon";
        try {
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void AddHoaDon(int MaKhachHang, int MaNhanVien, Date NgayLap, Double TongTien) {
        String sql = "Insert into HoaDon(MaKhachHang, MaNhanVien, NgayLap, TongTien) Values(?,?,?,?)";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaKhachHang);
            ps.setInt(2, MaNhanVien);
            ps.setDate(3, NgayLap);
            ps.setDouble(4, TongTien);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Thêm thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateHoaDon(int MaHoaDon, int MaKhachHang, int MaNhanVien, Double TongTien) {
        String sql = "Update HoaDon set MaKhachHang=?, MaNhanVien=?, TongTien=? Where MaHoaDon=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaKhachHang);
            ps.setInt(2, MaNhanVien);
            ps.setDouble(3, TongTien);
            ps.setInt(4, MaHoaDon);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteHoaDon(int MaHoaDon) {
        String sql = "Delete HoaDon Where MaHoaDon=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaHoaDon);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Xóa thành công!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getSachtuChiTietHD(int MaHoaDon) {
        String sql = "SELECT SACH.MaSach, TieuDe, SUM(SoLuong), DonGia FROM SACH, ChiTietHoaDon "
                + "WHERE SACH.MaSach = ChiTietHoaDon.MaSach AND MaHoaDon=? GROUP BY SACH.MaSach, TieuDe, DonGia";
        rs = null;
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaHoaDon);
            rs = ps.executeQuery();
            System.out.println("aa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet GetCTHoaDon() {
        getCon();
        rs = null;
        String querry = "Select * from ChiTietHoaDon";
        try {
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void AddCTHoaDon(int MaHoaDon, int MaSach, int SoLuong, double DonGia) {
        String sql = "Insert into ChiTietHoaDon(MaHoaDon, MaSach, SoLuong, DonGia) Values(?,?,?,?)";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaHoaDon);
            ps.setInt(2, MaSach);
            ps.setInt(3, SoLuong);
            ps.setDouble(4, DonGia);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateCTHoaDon(int MaChiTietHoaDon, int MaHoaDon, int MaSach, int SoLuong, double DonGia) {
        String sql = "Update ChiTietHoaDon set MaHoaDon=?, MaSach=?, SoLuong=?, DonGia=? Where MaChiTietHoaDon=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaHoaDon);
            ps.setInt(2, MaSach);
            ps.setInt(3, SoLuong);
            ps.setDouble(4, DonGia);
            ps.setInt(5, MaChiTietHoaDon);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteCTHoaDon(int MaHoaDon) {
        String sql = "Delete HoaDon Where MaChiTietHoaDon=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaHoaDon);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet GetPhieuNhap() {
        getCon();
        rs = null;
        String querry = "Select * from PhieuNhap";
        try {
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void AddPhieuNhap(int MaPhieuNhap, int MaNhaCungCap, int MaNhanVien, Date NgayLap, double TongTien) {
        String sql = "Insert into PhieuNhap(MaNhaCungCap, MaNhanVien, NgayLap, TongTien) Values(?,?,?,?)";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaNhaCungCap);
            ps.setInt(2, MaNhanVien);
            ps.setDate(3, NgayLap);
            ps.setDouble(4, TongTien);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdatePhieuNhap(int MaPhieuNhap, int MaNhaCungCap, int MaNhanVien, Date NgayLap, double TongTien) {
        String sql = "Update PhieuNhap set MaNhaCungCap=?, MaNhanVien=?, NgayLap=?, TongTien=? Where MaPhieuNhap=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaNhaCungCap);
            ps.setInt(2, MaNhanVien);
            ps.setDate(3, NgayLap);
            ps.setDouble(4, TongTien);
            ps.setInt(5, MaPhieuNhap);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeletePhieuNhap(int MaPhieuNhap) {
        String sql = "Delete PhiueNhap Where MaPhieuNhap=?";
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaPhieuNhap);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getSachtuChiTietPN(int MaPhieuNhap) {
        String sql = "SELECT SACH.MaSach, TieuDe, SUM(SoLuong), GiaNhap FROM SACH, ChiTietPhieuNhap " +
                    "WHERE SACH.MaSach = ChiTietPhieuNhap.MaSach AND MaPhieuNhap=? GROUP BY SACH.MaSach, TieuDe, GiaNhap";
        rs = null;
        getCon();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, MaPhieuNhap);
            rs = ps.executeQuery();
            System.out.println("aa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
