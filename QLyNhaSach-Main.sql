----BTL_HQT_CSDL_CUOI_KI
CREATE DATABASE QLyNhaSach;
GO

USE QLyNhaSach;
GO

CREATE TABLE Sach (
    MaSach INT IDENTITY PRIMARY KEY,
    TieuDe NVARCHAR(255) NOT NULL,
    TacGia NVARCHAR(255) NOT NULL,
    TheLoai NVARCHAR(100) NOT NULL,
    NhaXuatBan NVARCHAR(255) NOT NULL,
    Gia DECIMAL(10, 2) NOT NULL,
    SoLuongTon INT NOT NULL
);


CREATE TABLE KhachHang (
    MaKhachHang INT IDENTITY PRIMARY KEY,
    HoTen NVARCHAR(255) NOT NULL,
    SoDienThoai NVARCHAR(15) NOT NULL,
    Email NVARCHAR(255) NULL,
    DiaChi NVARCHAR(500) NOT NULL
);


CREATE TABLE NhanVien (
    MaNhanVien INT IDENTITY PRIMARY KEY,
    HoTen NVARCHAR(255) NOT NULL,
    ChucVu NVARCHAR(100) NOT NULL,
    SoDienThoai NVARCHAR(15) NOT NULL,
    Email NVARCHAR(255) NULL
);


CREATE TABLE HoaDon (
    MaHoaDon INT IDENTITY PRIMARY KEY,
    MaKhachHang INT NOT NULL,
    MaNhanVien INT NOT NULL,
    NgayLap DATETIME NOT NULL DEFAULT GETDATE(),
    TongTien DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);


CREATE TABLE ChiTietHoaDon (
    MaChiTietHoaDon INT IDENTITY PRIMARY KEY,
    MaHoaDon INT NOT NULL,
    MaSach INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(10, 2) NOT NULL,
    ThanhTien AS (SoLuong * DonGia),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaSach) REFERENCES Sach(MaSach)
);

CREATE TABLE NhaCungCap (
    MaNhaCungCap INT IDENTITY PRIMARY KEY,
    Ten NVARCHAR(255) NOT NULL,
    SoDienThoai NVARCHAR(15) NOT NULL,
    Email NVARCHAR(255) NULL,
    DiaChi NVARCHAR(500) NOT NULL
);

CREATE TABLE PhieuNhap (
    MaPhieuNhap INT IDENTITY PRIMARY KEY,
    MaNhaCungCap INT NOT NULL,
    MaNhanVien INT NOT NULL,
    NgayNhap DATETIME NOT NULL DEFAULT GETDATE(),
    TongTien DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (MaNhaCungCap) REFERENCES NhaCungCap(MaNhaCungCap),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);


CREATE TABLE ChiTietPhieuNhap (
    MaChiTietPhieuNhap INT IDENTITY PRIMARY KEY,
    MaPhieuNhap INT NOT NULL,
    MaSach INT NOT NULL,
    SoLuong INT NOT NULL,
    GiaNhap DECIMAL(10, 2) NOT NULL,
    ThanhTien AS (SoLuong * GiaNhap),
    FOREIGN KEY (MaPhieuNhap) REFERENCES PhieuNhap(MaPhieuNhap),
    FOREIGN KEY (MaSach) REFERENCES Sach(MaSach)
);

CREATE TABLE ThongBao (
    MaThongBao INT IDENTITY PRIMARY KEY,
    ThoiGian DATETIME NOT NULL DEFAULT GETDATE(),
    NoiDung NVARCHAR(1000) NOT NULL
);


INSERT INTO Sach (TieuDe, TacGia, TheLoai, NhaXuatBan, Gia, SoLuongTon)
VALUES
(N'Kẻ Bắt Lúa Mạch', N'J.D. Salinger', N'Tiểu thuyết', N'Nhà xuất bản Trẻ', 250000, 50),
(N'Giết Con Chim Nhại', N'Harper Lee', N'Tiểu thuyết', N'Nhà xuất bản Văn học', 300000, 30),
(N'1984', N'George Orwell', N'Dystopian', N'Nhà xuất bản Thế giới', 220000, 40),
(N'Kiêu Hãnh và Định Kiến', N'Jane Austen', N'Lãng mạn', N'Nhà xuất bản Hội Nhà văn', 180000, 60),
(N'Đại Gia Gatsby', N'F. Scott Fitzgerald', N'Tiểu thuyết', N'Nhà xuất bản Nhã Nam', 250000, 25),
(N'Moby-Dick', N'Herman Melville', N'Phiêu lưu', N'Nhà xuất bản Thế Giới', 350000, 15),
(N'Chiến Tranh và Hòa Bình', N'Leo Tolstoy', N'Lịch sử', N'Nhà xuất bản Văn hóa', 500000, 10),
(N'Tội Ác và Hình Phạt', N'Fyodor Dostoevsky', N'Tiểu thuyết triết học', N'Nhà xuất bản Trẻ', 300000, 50),
(N'Chúa Tể Những Chiếc Nhẫn', N'J.R.R. Tolkien', N'Fantasy', N'Nhà xuất bản Kim Đồng', 250000, 35),
(N'Hobbit', N'J.R.R. Tolkien', N'Fantasy', N'Nhà xuất bản Kim Đồng', 220000, 20);


INSERT INTO KhachHang (HoTen, SoDienThoai, Email, DiaChi)
VALUES
(N'Nguyễn Thị Lan', N'0912345678', N'lan.nguyen@example.com', N'123 Đường 1, Hà Nội'),
(N'Trần Văn Nam', N'0987654321', N'nam.tran@example.com', N'456 Đường 2, Hồ Chí Minh'),
(N'Lê Hoàng Duy', N'0901122334', N'duy.le@example.com', N'789 Đường 3, Đà Nẵng'),
(N'Hoàng Minh Tuấn', N'0911223344', N'tuan.hoang@example.com', N'101 Đường 4, Hà Nội'),
(N'Phạm Minh Khuê', N'0988776655', N'khue.pham@example.com', N'202 Đường 5, Hồ Chí Minh'),
(N'Mai Thi Lan', N'0909887766', N'lan.mai@example.com', N'303 Đường 6, Hải Phòng'),
(N'Vũ Minh Quân', N'0912233445', N'quan.vu@example.com', N'404 Đường 7, Cần Thơ'),
(N'Đoàn Thị Lan Anh', N'0988773344', N'anh.doan@example.com', N'505 Đường 8, Huế'),
(N'Nguyễn Thị Ngọc', N'0901124556', N'ngoc.nguyen@example.com', N'606 Đường 9, Hà Nội'),
(N'Lương Minh Duy', N'0988772233', N'duy.luong@example.com', N'707 Đường 10, Hồ Chí Minh');


INSERT INTO NhanVien (HoTen, ChucVu, SoDienThoai, Email)
VALUES
(N'Lê Văn An', N'Quản lý', N'0901234567', N'an.le@company.com'),
(N'Trần Thị Lan', N'Nhân viên bán hàng', N'0902345678', N'lan.tran@company.com'),
(N'Nguyễn Minh Tuấn', N'Nhân viên bán hàng', N'0903456789', N'tuan.nguyen@company.com'),
(N'Phan Thị Dung', N'Thu ngân', N'0904567890', N'dung.phan@company.com'),
(N'Đặng Minh Khoa', N'Nhân viên kho', N'0905678901', N'khoa.dang@company.com'),
(N'Hồ Quốc Duy', N'Quản lý', N'0906789012', N'duy.ho@company.com'),
(N'Trần Kim Lan', N'Nhân viên bán hàng', N'0907890123', N'lan.tran@company.com'),
(N'Bùi Minh Tuấn', N'Thu ngân', N'0908901234', N'tuan.bui@company.com'),
(N'Nguyễn Thị Như', N'Nhân viên kho', N'0909012345', N'nhu.nguyen@company.com'),
(N'Phan Minh Hoàng', N'Quản lý', N'0901123456', N'hoang.phan@company.com');


INSERT INTO HoaDon (MaKhachHang, MaNhanVien, NgayLap, TongTien)
VALUES
(1, 2, N'2024-12-01', 1200000),
(2, 3, N'2024-12-02', 1350000),
(3, 4, N'2024-12-03', 800000),
(4, 5, N'2024-12-04', 950000),
(5, 6, N'2024-12-05', 700000),
(6, 7, N'2024-12-06', 1500000),
(7, 8, N'2024-12-07', 1700000),
(8, 9, N'2024-12-08', 650000),
(9, 10, N'2024-12-09', 1450000),
(10, 1, N'2024-12-10', 1200000);


INSERT INTO ChiTietHoaDon (MaHoaDon, MaSach, SoLuong, DonGia)
VALUES
(1, 1, 2, 250000),
(1, 2, 3, 300000),
(2, 3, 2, 220000),
(2, 4, 1, 180000),
(3, 5, 1, 250000),
(4, 6, 1, 350000),
(5, 7, 2, 500000),
(6, 8, 3, 300000),
(7, 9, 2, 250000),
(8, 10, 2, 250000);


INSERT INTO NhaCungCap (Ten, SoDienThoai, Email, DiaChi)
VALUES
(N'Nhà cung cấp Sách A', N'0901234000', N'ncc1@example.com', N'10 Đường Sách, Hà Nội'),
(N'Nhà cung cấp Sách B', N'0902345000', N'ncc2@example.com', N'20 Đường Sách, Hồ Chí Minh'),
(N'Nhà cung cấp Sách C', N'0903456000', N'ncc3@example.com', N'30 Đường Sách, Đà Nẵng'),
(N'Nhà cung cấp Sách D', N'0904567000', N'ncc4@example.com', N'40 Đường Sách, Hải Phòng'),
(N'Nhà cung cấp Sách E', N'0905678000', N'ncc5@example.com', N'50 Đường Sách, Cần Thơ'),
(N'Nhà cung cấp Sách F', N'0906789000', N'ncc6@example.com', N'60 Đường Sách, Huế'),
(N'Nhà cung cấp Sách G', N'0907891000', N'ncc7@example.com', N'70 Đường Sách, Hà Nội'),
(N'Nhà cung cấp Sách H', N'0908902000', N'ncc8@example.com', N'80 Đường Sách, Hồ Chí Minh'),
(N'Nhà cung cấp Sách I', N'0909013000', N'ncc9@example.com', N'90 Đường Sách, Đà Nẵng'),
(N'Nhà cung cấp Sách J', N'0901124000', N'ncc10@example.com', N'100 Đường Sách, Hải Phòng');


INSERT INTO PhieuNhap (MaNhaCungCap, MaNhanVien, NgayNhap, TongTien)
VALUES
(1, 1, N'2024-12-01', 1000000),
(2, 2, N'2024-12-02', 1500000),
(3, 3, N'2024-12-03', 900000),
(4, 4, N'2024-12-04', 1200000),
(5, 5, N'2024-12-05', 700000),
(6, 6, N'2024-12-06', 2000000),
(7, 7, N'2024-12-07', 2100000),
(8, 8, N'2024-12-08', 1500000),
(9, 9, N'2024-12-09', 2200000),
(10, 10, N'2024-12-10', 2500000);

-- Thêm vào bảng StockReceiptDetails
INSERT INTO ChiTietPhieuNhap (MaPhieuNhap, MaSach, SoLuong, GiaNhap)
VALUES
(1, 1, 100, 100000),
(2, 2, 200, 150000),
(3, 3, 150, 120000),
(4, 4, 120, 130000),
(5, 5, 80, 140000),
(6, 6, 170, 160000),
(7, 7, 180, 180000),
(8, 8, 150, 200000),
(9, 9, 220, 190000),
(10, 10, 250, 250000);


--Câu 7, 8 view
7. Tạo view hiển thị chi tiết các hóa đơn với thông tin từng sản phẩm và thành tiền.
CREATE VIEW ChiTietHoaDonView AS
SELECT 
    hd.MaHoaDon,
    kh.HoTen AS TenKhachHang,
    nv.HoTen AS TenNhanVien,
    hd.NgayLap,
    s.TieuDe AS TenSach,
    cthd.SoLuong,
    cthd.DonGia,
    cthd.ThanhTien
FROM 
    HoaDon hd, 
    KhachHang kh, 
    NhanVien nv, 
    ChiTietHoaDon cthd, 
    Sach s
WHERE 
    hd.MaKhachHang = kh.MaKhachHang
    AND hd.MaNhanVien = nv.MaNhanVien
    AND hd.MaHoaDon = cthd.MaHoaDon
    AND cthd.MaSach = s.MaSach;
SELECT * FROM ChiTietHoaDonView

8. Viết view để hiển thị danh sách sách cùng với số lượng tồn và tổng số lượng đã nhập.
CREATE VIEW View_DSSach
AS
SELECT
	s.MaSach,
	s.TieuDe,
	s.TacGia,
	s.TheLoai,
	s.NhaXuatBan,
	s.Gia,
	s.SoLuongTon,
	SUM(SoLuong) AS SoLuongNhap
FROM Sach s, ChiTietPhieuNhap ctpn 
WHERE s.MaSach = ctpn.MaSach
GROUP BY s.MaSach, s.TieuDe, s.TheLoai, s.TacGia,
		 s.NhaXuatBan, s.Gia, s.SoLuongTon

SELECT * FROM View_DSSach
DROP VIEW View_DSSach

--Proc câu 7,8
7. Viết thủ tục để trả về danh sách các khách hàng có hóa đơn trong một khoảng thời gian cụ thể.
ALTER PROC Proc_KhachHangTheoNgay
@NgayBatDau Date, @NgayKetThuc Date, @DSKH cursor varying out
AS BEGIN
	SET @DSKH = CURSOR FOR
	SELECT kh.MaKhachHang, kh.HoTen, kh.SoDienThoai, kh.Email, kh.DiaChi, NgayLap AS NgayMuaHang
	FROM KhachHang kh, HoaDon hd 
	WHERE kh.MaKhachHang = hd.MaKhachHang
	AND kh.MaKhachHang 
	IN (SELECT MaKhachHang FROM HoaDon WHERE NgayLap BETWEEN @NgayBatDau AND @NgayKetThuc)
	OPEN @DSKH
END

DECLARE @DS CURSOR
EXEC Proc_KhachHangTheoNgay '2024-12-03', '2024-12-09', @DSKH = @DS OUT
FETCH NEXT FROM @DS
WHILE(@@FETCH_STATUS=0)
	FETCH NEXT FROM @DS
DROP PROC Proc_HoaDonTheoNgay

8. Tạo thủ tục để tính tổng số lượng sách nhập từ một nhà cung cấp cụ thể.
CREATE PROC Proc_TongSoSachNhap
@MaNhaCungCap int, @Tong int OUT
AS BEGIN
	SELECT @Tong = SUM(SoLuong) FROM ChiTietPhieuNhap
	WHERE MaPhieuNhap IN (SELECT MaPhieuNhap FROM PhieuNhap WHERE MaNhaCungCap=@MaNhaCungCap)
END

DECLARE @T INT
EXEC Proc_TongSoSachNhap 1, @Tong = @T out
PRINT N'Tổng số lượng sách đã nhập là: ' + CONVERT(CHAR(3), @T)



--Câu 7,8 hàm
7. Viết hàm tính tổng tiền nhập kho từ một phiếu nhập cụ thể.
CREATE FUNCTION Func_TongTienPhieuNhap(@MaPhieuNhap INT)
RETURNS DECIMAL(10,2)
AS BEGIN
	RETURN (SELECT TongTien FROM PhieuNhap WHERE MaPhieuNhap = @MaPhieuNhap)
END

DECLARE @MaPN INT
SET @MaPN = 1
PRINT N'Tổng tiền nhập của phiếu mã số ' + TRIM(CAST(@MaPN AS NCHAR)) + N' là : ' 
	  + TRIM(CAST(DBO.Func_TongTienPhieuNhap(1) AS NCHAR))

8. Tạo hàm trả về danh sách các khách hàng có tổng giá trị hóa đơn lớn hơn một ngưỡng.
ALTER FUNCTION Func_DSKHHoaDon(@TongTien DECIMAL(10,2))
RETURNS TABLE
AS RETURN
	(SELECT kh.MaKhachHang, kh.HoTen, kh.SoDienThoai, kh.Email, kh.DiaChi
	, SUM(TongTien) AS TongTien FROM KhachHang kh, HoaDon hd
	WHERE kh.MaKhachHang = hd.MaKhachHang 
	AND kh.MaKhachHang IN (SELECT MaKhachHang FROM HoaDon GROUP BY MaKhachHang HAVING SUM(TongTien) > @TongTien)
	GROUP BY kh.MaKhachHang, kh.HoTen, kh.SoDienThoai, kh.Email, kh.DiaChi)

SELECT * FROM DBO.Func_DSKHHoaDon(1000000.00)
DROP FUNCTION Func_DSKHHoaDon

--Câu 7, 8 Trigger
7. Tạo trigger để kiểm tra nếu tổng tiền của một phiếu nhập vượt quá một ngưỡng nhất định thì ghi vào một bảng thông báo.
CREATE TRIGGER Trigger_ThongBaoPhieuNhap
ON PhieuNhap FOR INSERT, UPDATE
AS
	DECLARE @Nguong DECIMAL(10,2) = 10000000.00
	INSERT INTO ThongBao (NoiDung)
	SELECT N'Tổng tiền của phiếu nhập có mã ' + CAST(MaPhieuNhap AS NVARCHAR) + 
           N' vượt ngưỡng giới hạn là ' + CAST(@Nguong AS NVARCHAR) + 
           N'. Tổng tiền: ' + CAST(TongTien AS NVARCHAR)
    FROM INSERTED WHERE TongTien > @Nguong

--Kiểm tra với Phiếu nhập có tổng tiền cượt ngưỡng
INSERT INTO PhieuNhap (MaNhaCungCap, MaNhanVien, NgayNhap, TongTien)
VALUES
(1, 1, '2024-12-31', 10000001)

8. Viết trigger để tự động xóa các hóa đơn và chi tiết hóa đơn liên quan nếu khách hàng bị xóa khỏi bảng KhachHang.
CREATE TRIGGER Trigger_XoaHoaDonKhiKhachHangBiXoa
ON KhachHang
INSTEAD OF DELETE
AS
BEGIN
    DELETE FROM ChiTietHoaDon
    WHERE MaHoaDon 
	IN (SELECT MaHoaDon FROM HoaDon WHERE MaKhachHang IN (SELECT MaKhachHang FROM DELETED))

    DELETE FROM HoaDon
    WHERE MaKhachHang IN (SELECT MaKhachHang FROM DELETED)

	DELETE FROM KhachHang WHERE MaKhachHang IN (SELECT MaKhachHang FROM DELETED)
END;

DELETE FROM KhachHang WHERE MaKhachHang = 9
DROP TRIGGER Trigger_XoaHoaDonKhiKhachHangBiXoa

--Câu 7, 8 con trỏ
7. Viết con trỏ duyệt qua các nhà cung cấp và tính tổng số tiền đã chi cho từng nhà cung cấp.
DECLARE Cursor_TienDaChiChoNCC CURSOR
FOR
	SELECT 
		Ten,
		SoDienThoai,
		Email,
		DiaChi,
		SUM(TongTien) AS TongTienDaChi
	FROM NhaCungCap ncc, PhieuNhap pn 
	WHERE ncc.MaNhaCungcap = pn.MaNhaCungCap
	GROUP BY ncc.Ten, ncc.SoDienThoai, ncc.Email, ncc.DiaChi

	OPEN Cursor_TienDaChiChoNCC
	FETCH NEXT FROM Cursor_TienDaChiChoNCC
	WHILE(@@FETCH_STATUS=0)
		FETCH NEXT FROM Cursor_TienDaChiChoNCC
	CLOSE Cursor_TienDaChiChoNCC
	DEALLOCATE Cursor_TienDaChiChoNCC

8. Tạo con trỏ để hiển thị thông tin các hóa đơn có tổng tiền lớn hơn 1 triệu và chi tiết các sách trong hóa đơn đó.
DECLARE Cursor_HoaDonTren1Trieu CURSOR
FOR
    SELECT MaHoaDon 
    FROM HoaDon 
    WHERE TongTien > 1000000.00;
OPEN Cursor_HoaDonTren1Trieu;

DECLARE @MaHoaDon INT;
FETCH NEXT FROM Cursor_HoaDonTren1Trieu INTO @MaHoaDon;

WHILE (@@FETCH_STATUS = 0)
BEGIN
    SELECT * FROM HoaDon 
    WHERE MaHoaDon = @MaHoaDon;

    SELECT 
        ct.MaSach, 
        s.TieuDe, 
        s.TheLoai, 
        s.NhaXuatBan, 
        s.Gia, 
        ct.SoLuong, 
        ct.DonGia, 
        ct.ThanhTien
    FROM 
        Sach s, ChiTietHoaDon ct 
    WHERE 
        s.MaSach = ct.MaSach
        AND ct.MaHoaDon = @MaHoaDon;
    FETCH NEXT FROM Cursor_HoaDonTren1Trieu INTO @MaHoaDon;
END;

CLOSE Cursor_HoaDonTren1Trieu;
DEALLOCATE Cursor_HoaDonTren1Trieu;

