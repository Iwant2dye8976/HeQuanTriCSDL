package hqt_csdl;
import java.sql.Date;
public class PhieuNhap {
    private int MaPhieuNhap;
    private int MaNhaCungCap;
    private int MaNhanVien;
    private Date NgayLap;
    private double TongTien;

    public PhieuNhap(int MaPhieuNhap, int MaNhaCungCap, int MaNhanVien, Date NgayLap, double TongTien) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaNhaCungCap = MaNhaCungCap;
        this.MaNhanVien = MaNhanVien;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
    }

    public int getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public int getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setMaPhieuNhap(int MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public void setMaNhaCungCap(int MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
}
