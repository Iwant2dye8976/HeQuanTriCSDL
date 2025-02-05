/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hqt_csdl;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class QLNhaSach extends javax.swing.JFrame {

    private ConSQL conn = new ConSQL();
    private String[] columns_sach = {"MaSach", "TieuDe", "TacGia", "TheLoai", "NhaXuatBan", "Gia", "SoLuongTon"};
    private String[] columns_kh = {"MaKhachHang", "HoTen", "SoDienThoai", "Email", "DiaChi"};
    private String[] columns_nv = {"MaNhanVien", "HoTen", "ChucVu", "SoDienThoai", "Email"};
    private String[] columns_hd = {"MaHoaDon", "MaKH", "MaNV", "NgayLap", "TongTien"};
    private String[] columns_chitiet_hd = {"", "Sach", "SoLuong", "DonGia", "Tong"};
    private String[] columns_pn = {"MaPhieuNhap", "MaNhaCungCap", "MaNV", "NgayLap", "TongTien"};
    private String[] columns_chitiet_pn = {"", "Sach", "SoLuongNhap", "GiaNhap", "ThanhTien"};

    public QLNhaSach() {
        initComponents();
        addSelectionToDatatableSach();
        addSelectionToDatatableKH();
        addSelectionToDatatableNV();
        addSelectionToDatatableHD();
        addSelectionToDatatablePN();
    }

    //Xử lý sự kiện khi click vào dòng trong bảng
    public void addSelectionToDatatableSach() {
        data_table_sach.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = data_table_sach.getSelectedRow();
                    if (selectedRow != -1) {
                        System.out.println(selectedRow);
                        BookID_tf.setText(data_table_sach.getValueAt(selectedRow, 0).toString().trim());
                        Title_tf.setText(data_table_sach.getValueAt(selectedRow, 1).toString().trim());
                        Author_tf.setText(data_table_sach.getValueAt(selectedRow, 2).toString().trim());
                        Category_cbx.setSelectedItem(data_table_sach.getValueAt(selectedRow, 3).toString().trim());
                        Publisher_cbx.setSelectedItem(data_table_sach.getValueAt(selectedRow, 4).toString().trim());
                        Price_tf.setText(data_table_sach.getValueAt(selectedRow, 5).toString().trim());
                        StockQuantity_tf.setText(data_table_sach.getValueAt(selectedRow, 6).toString().trim());
                    }
                }
            }
        });
    }

    public void addSelectionToDatatableKH() {
        data_table_kh.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = data_table_kh.getSelectedRow();
                    if (selectedRow != -1) {
                        System.out.println(selectedRow);
                        MaKH_tf.setText(data_table_kh.getValueAt(selectedRow, 0).toString().trim());
                        HoTenKH_tf.setText(data_table_kh.getValueAt(selectedRow, 1).toString().trim());
                        DienThoaiKH_tf.setText(data_table_kh.getValueAt(selectedRow, 2).toString().trim());
                        Email_tf.setText(data_table_kh.getValueAt(selectedRow, 3).toString().trim());
                        DiaChiKH_TArea.setText(data_table_kh.getValueAt(selectedRow, 4).toString().trim());
                    }
                }
            }
        });
    }

    public void addSelectionToDatatableNV() {
        data_table_nv.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = data_table_nv.getSelectedRow();
                    if (selectedRow != -1) {
                        System.out.println(selectedRow);
                        MaNV_tf.setText(data_table_nv.getValueAt(selectedRow, 0).toString().trim());
                        HoTenNV_tf.setText(data_table_nv.getValueAt(selectedRow, 1).toString().trim());
                        DienThoaiNV_tf.setText(data_table_nv.getValueAt(selectedRow, 3).toString().trim());
                        EmailNV_tf.setText(data_table_nv.getValueAt(selectedRow, 4).toString().trim());
                        ChucVu_cbx.setSelectedItem(data_table_nv.getValueAt(selectedRow, 2).toString().trim());
                    }
                }
            }
        });
    }

    public void addSelectionToDatatableHD() {
        data_table_hd.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = data_table_hd.getSelectedRow();
                    if (selectedRow != -1) {
                        MaHD_tf.setText(data_table_hd.getValueAt(selectedRow, 0).toString().trim());
                        MKH_HD_tf.setText(data_table_hd.getValueAt(selectedRow, 1).toString().trim());
                        MaNVHD_tf.setText(data_table_hd.getValueAt(selectedRow, 2).toString().trim());
                        NgayLap_tf.setText(data_table_hd.getValueAt(selectedRow, 3).toString().trim());
                        TongTien_tf.setText(data_table_hd.getValueAt(selectedRow, 4).toString().trim());
                        ClearCTHD();
                        try (ResultSet rs = conn.getSachtuChiTietHD(Integer.parseInt(MaHD_tf.getText()))) {
                            DefaultTableModel model = (DefaultTableModel) cthd_table.getModel();

                            while (rs.next()) {
                                for (int i = 0; i < model.getRowCount(); i++) {
                                    if (model.getValueAt(i, 1).toString().equals(rs.getString(2))) {
                                        model.setValueAt(true, i, 0);
                                        model.setValueAt(rs.getInt(3), i, 2);
                                    }
                                }
                            }

                            cthd_table.setModel(model);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    
    public void addSelectionToDatatablePN() {
        data_table_pn.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = data_table_pn.getSelectedRow();
                    if (selectedRow != -1) {
                        MaPN_tf.setText(data_table_pn.getValueAt(selectedRow, 0).toString().trim());
                        MaNCC_tf.setText(data_table_pn.getValueAt(selectedRow, 1).toString().trim());
                        MaNVPN_tf.setText(data_table_pn.getValueAt(selectedRow, 2).toString().trim());
                        NgayLapPN_tf.setText(data_table_pn.getValueAt(selectedRow, 3).toString().trim());
                        TongTienPN_tf.setText(data_table_pn.getValueAt(selectedRow, 4).toString().trim());
                        ClearCTPN();
                        try (ResultSet rs = conn.getSachtuChiTietPN(Integer.parseInt(MaPN_tf.getText()))) {
                            DefaultTableModel model = (DefaultTableModel) ctpn_table.getModel();

                            while (rs.next()) {
                                for (int i = 0; i < model.getRowCount(); i++) {
                                    if (model.getValueAt(i, 1).toString().equals(rs.getString(2))) {
                                        model.setValueAt(true, i, 0);
                                        model.setValueAt(rs.getInt(3), i, 2);
                                        model.setValueAt(String.format("%.2f", rs.getDouble(4)), i, 3);
                                    }
                                }
                            }

                            ctpn_table.setModel(model);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    //Load dữ liệu vào bảng
    public void LoadDataIntoTableSach() {
        try {
            DefaultTableModel model = new DefaultTableModel(columns_sach, 0);
            ResultSet rs = conn.GetSach();
            while (rs.next()) {
                Sach b = new Sach(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
                Object[] row = {
                    b.getMaSach(),
                    b.getTieuDe(),
                    b.getTacGia(),
                    b.getTheLoai(),
                    b.getNhaXuatBan(),
                    b.getGia(),
                    b.getSoLuongTon()
                };
                model.addRow(row);
            }
            data_table_sach.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LoadDataIntoTableKH() {
        try {
            DefaultTableModel model = new DefaultTableModel(columns_kh, 0);
            ResultSet rs = conn.GetKhachHang();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                Object[] row = {
                    kh.getMaKhachHang(),
                    kh.getHoTen(),
                    kh.getSoDienThoai(),
                    kh.getEmail(),
                    kh.getDiaChi()
                };
                model.addRow(row);
            }
            data_table_kh.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LoadDataIntoTableNV() {
        try {
            DefaultTableModel model = new DefaultTableModel(columns_nv, 0);
            ResultSet rs = conn.GetNhanVien();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                Object[] row = {
                    nv.getMaNhanVien(),
                    nv.getHoTen(),
                    nv.getChucVu(),
                    nv.getSoDienThoai(),
                    nv.getEmail()
                };
                model.addRow(row);
            }
            data_table_nv.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LoadDataIntoTableHD() {
        try {
            DefaultTableModel model = new DefaultTableModel(columns_hd, 0);
            ResultSet rs = conn.GetHoaDon();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDouble(5));
                Object[] row = {
                    hd.getMaHoaDon(),
                    hd.getMaKhachHang(),
                    hd.getMaNhanVien(),
                    hd.getNgayLap(),
                    hd.getTongTien(),};
                model.addRow(row);
            }
            data_table_hd.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LoadDataIntoTableCTHoaDon() {
        try {
            // Tạo mô hình bảng
            DefaultTableModel model = new DefaultTableModel(columns_chitiet_hd, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Chỉ cho phép chỉnh sửa cột "Số lượng"
                    return column == 0 || column == 2;
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    // Cột 0 chứa checkbox
                    if (columnIndex == 0) {
                        return Boolean.class;
                    }
                    return String.class;
                }
            };

            // Lấy dữ liệu từ cơ sở dữ liệu
            ResultSet rs = conn.GetSach();
            while (rs.next()) {
                Sach s = new Sach(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
                Object[] row = {
                    false, // Checkbox
                    s.getTieuDe(), // Tiêu đề sách
                    0, // Số lượng (ban đầu là 0)
                    s.getGia(), // Đơn giá
                    0.0 // Thành tiền (ban đầu là 0)
                };
                model.addRow(row);
            }

            // Lắng nghe sự thay đổi trong bảng
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    // Xử lý sự kiện chỉ khi cột "Số lượng" (index = 2) thay đổi
                    if (e.getColumn() == 2) {
                        int row = e.getFirstRow(); // Hàng được thay đổi
                        if (row != -1) {
                            try {
                                // Lấy số lượng và đơn giá
                                int SL = Integer.parseInt(model.getValueAt(row, 2).toString());
                                double DG = Double.parseDouble(model.getValueAt(row, 3).toString());

                                // Tính thành tiền
                                double total = SL * DG;
                                model.setValueAt(total, row, 4); // Cập nhật cột "Thành tiền"
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                                model.setValueAt(0, row, 4); // Nếu lỗi, đặt "Thành tiền" về 0
                            }
                        }
                    }
                    int tong = 0;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if ((Boolean) model.getValueAt(i, 0)) {
                            tong += Double.parseDouble(model.getValueAt(i, 4).toString());
                        }
                    }
                    TongTien_tf.setText(tong + "");
                }
            });

            // Thiết lập mô hình cho bảng
            cthd_table.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LoadDataIntoTablePN() {
        try {
            DefaultTableModel model = new DefaultTableModel(columns_pn, 0);
            ResultSet rs = conn.GetPhieuNhap();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDouble(5));
                Object[] row = {
                    pn.getMaPhieuNhap(),
                    pn.getMaNhaCungCap(),
                    pn.getMaNhanVien(),
                    pn.getNgayLap(),
                    String.format("%.2f", pn.getTongTien())
                };
                model.addRow(row);
            }
            data_table_pn.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void LoadDataIntoTableCTPhieuNhap() {
        try {
            // Tạo mô hình bảng
            DefaultTableModel model = new DefaultTableModel(columns_chitiet_pn, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Chỉ cho phép chỉnh sửa cột "Số lượng"
                    return column == 0 || column == 2 || column == 3;
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    // Cột 0 chứa checkbox
                    if (columnIndex == 0) {
                        return Boolean.class;
                    }
                    return String.class;
                }
            };

            // Lấy dữ liệu từ cơ sở dữ liệu
            ResultSet rs = conn.GetSach();
            while (rs.next()) {
                Sach s = new Sach(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
                Object[] row = {
                    false, // Checkbox
                    s.getTieuDe(), // Tiêu đề sách
                    0, // Số lượng (ban đầu là 0)
                    0, // Gia nhap
                    0.0 // Thành tiền (ban đầu là 0)
                };
                model.addRow(row);
            }

            // Lắng nghe sự thay đổi trong bảng
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    // Xử lý sự kiện chỉ khi cột "Số lượng" (index = 2) thay đổi
                    if (e.getColumn() == 2 || e.getColumn() == 3) {
                        int row = e.getFirstRow(); // Hàng được thay đổi
                        if (row != -1) {
                            try {
                                // Lấy số lượng và đơn giá
                                int SL = Integer.parseInt(model.getValueAt(row, 2).toString());
                                double DG = Double.parseDouble(model.getValueAt(row, 3).toString());

                                // Tính thành tiền
                                double total = SL * DG;
                                model.setValueAt(String.format("%.2f", total), row, 4); // Cập nhật cột "Thành tiền"
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                                model.setValueAt(0, row, 4); // Nếu lỗi, đặt "Thành tiền" về 0
                            }
                        }
                    }
                    int tong = 0;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if ((Boolean) model.getValueAt(i, 0)) {
                            tong += Double.parseDouble(model.getValueAt(i, 4).toString());
                        }
                    }
                    TongTienPN_tf.setText(tong + "");
                }
            });

            // Thiết lập mô hình cho bảng
            ctpn_table.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Các hàm thêm dữ liệu
    public void AddBook() {
        try {
            String Title = Title_tf.getText().trim();
            String Author = Author_tf.getText().trim();
            String Category = Category_cbx.getSelectedItem().toString().trim();
            String Publisher = Publisher_cbx.getSelectedItem().toString().trim();
            double Price = Double.parseDouble(Price_tf.getText().trim());
            int StockQuantity = Integer.parseInt(StockQuantity_tf.getText().trim());
            if (Title.isEmpty() || Author.isEmpty() || Category.isEmpty() || Publisher.isEmpty() || StockQuantity <= 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.AddSach(Title, Author, Category, Publisher, Price, StockQuantity);
                ClearSach();
                LoadDataIntoTableSach();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddKH() {
        try {
            String HoTen = HoTenKH_tf.getText().trim();
            String SoDienThoai = DienThoaiKH_tf.getText().trim();
            String Email = Email_tf.getText().toString().trim();
            String DiaChi = DiaChiKH_TArea.getText().toString().trim();
            if (HoTen.isEmpty() || SoDienThoai.isEmpty() || Email.isEmpty() || DiaChi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.AddKhachHang(HoTen, SoDienThoai, Email, DiaChi);
                ClearKH();
                LoadDataIntoTableKH();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddNV() {
        try {
            String HoTen = HoTenNV_tf.getText().trim();
            String SoDienThoai = DienThoaiNV_tf.getText().trim();
            String Email = EmailNV_tf.getText().toString().trim();
            String ChucVu = ChucVu_cbx.getSelectedItem().toString().trim();
            if (HoTen.isEmpty() || SoDienThoai.isEmpty() || Email.isEmpty() || ChucVu.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.AddNhanVien(HoTen, ChucVu, SoDienThoai, Email);
                ClearNV();
                LoadDataIntoTableNV();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddHD() {
        try {
            int MaKhachHang = Integer.parseInt(MKH_HD_tf.getText().trim());
            int MaNhanVien = Integer.parseInt(MaNVHD_tf.getText().trim());
            java.sql.Date NgayLap = new java.sql.Date(System.currentTimeMillis());
            double TongTien = Double.parseDouble(TongTien_tf.getText().toString().trim());
            Object[] sach;
            DefaultTableModel model = (DefaultTableModel) cthd_table.getModel();
            if (MKH_HD_tf.getText().isEmpty() || MaNVHD_tf.getText().isEmpty() || TongTien_tf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.AddHoaDon(MaKhachHang, MaNhanVien, NgayLap, TongTien);
                ClearHD();
                ClearCTHD();
                LoadDataIntoTableHD();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Các hàm cập nhật dữ liệu
    public void UpdateBook() {
        try {
            int BookID = Integer.parseInt(BookID_tf.getText().trim());
            String Title = Title_tf.getText().trim();
            String Author = Author_tf.getText().trim();
            String Category = Category_cbx.getSelectedItem().toString().trim();
            String Publisher = Publisher_cbx.getSelectedItem().toString().trim();
            double Price = Double.parseDouble(Price_tf.getText().trim());
            int StockQuantity = Integer.parseInt(StockQuantity_tf.getText().trim());
            if (Title.isEmpty() || Author.isEmpty() || Category.isEmpty() || Publisher.isEmpty() || StockQuantity <= 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.UpdateSach(BookID, Title, Author, Category, Publisher, Price, StockQuantity);
                ClearSach();
                LoadDataIntoTableSach();
//                System.out.println(data_table.getSelectedRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateKH() {
        try {
            int MaKhachHang = Integer.parseInt(MaKH_tf.getText().trim());
            String HoTen = HoTenKH_tf.getText().trim();
            String SoDienThoai = DienThoaiKH_tf.getText().trim();
            String Email = Email_tf.getText().toString().trim();
            String DiaChi = DiaChiKH_TArea.getText().toString().trim();
            if (HoTen.isEmpty() || SoDienThoai.isEmpty() || Email.isEmpty() || DiaChi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.UpdateKhachHang(MaKhachHang, HoTen, SoDienThoai, Email, DiaChi);
                ClearKH();
                LoadDataIntoTableKH();
//                System.out.println(data_table.getSelectedRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateNV() {
        try {
            int MaNhanVien = Integer.parseInt(MaNV_tf.getText().trim());
            String HoTen = HoTenNV_tf.getText().trim();
            String SoDienThoai = DienThoaiNV_tf.getText().trim();
            String Email = EmailNV_tf.getText().toString().trim();
            String ChucVu = ChucVu_cbx.getSelectedItem().toString().trim();
            if (HoTen.isEmpty() || SoDienThoai.isEmpty() || Email.isEmpty() || ChucVu.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.UpdateNhanVien(MaNhanVien, HoTen, ChucVu, SoDienThoai, Email);
                ClearNV();
                LoadDataIntoTableNV();
//                System.out.println(data_table.getSelectedRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Các hàm xóa dữ liệu
    public void DeleteBook() {
        try {
            int BookID = Integer.parseInt(BookID_tf.getText().trim());
            conn.DeleteSach(BookID);
            ClearSach();
            LoadDataIntoTableSach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteKH() {
        try {
            int MaKhachHang = Integer.parseInt(MaKH_tf.getText().trim());
            conn.DeleteKhachHang(MaKhachHang);
            ClearKH();
            LoadDataIntoTableKH();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteNV() {
        try {
            int MaNhanVien = Integer.parseInt(MaNV_tf.getText().trim());
            conn.DeleteNhanVien(MaNhanVien);
            ClearKH();
            LoadDataIntoTableKH();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteHD() {
        try {
            int MaHoaDon = Integer.parseInt(MaHD_tf.getText().trim());
            conn.DeleteHoaDon(MaHoaDon);
            ClearHD();
            ClearCTHD();
            LoadDataIntoTableHD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Các hàm clear trường thông tin
    public void ClearSach() {
        BookID_tf.setText(null);
        Title_tf.setText(null);
        Author_tf.setText(null);
        Category_cbx.setSelectedIndex(0);
        Publisher_cbx.setSelectedIndex(0);
        Price_tf.setText(null);
        StockQuantity_tf.setText(null);
    }

    public void ClearKH() {
        MaKH_tf.setText(null);
        HoTenKH_tf.setText(null);
        DienThoaiKH_tf.setText(null);
        Email_tf.setText(null);
        DiaChiKH_TArea.setText(null);
    }

    public void ClearNV() {
        MaNV_tf.setText(null);
        HoTenNV_tf.setText(null);
        DienThoaiNV_tf.setText(null);
        EmailNV_tf.setText(null);
        ChucVu_cbx.setSelectedIndex(0);
    }

    public void ClearHD() {
        MaHD_tf.setText(null);
        MKH_HD_tf.setText(null);
        MaNVHD_tf.setText(null);
        NgayLap_tf.setText(null);
        TongTien_tf.setText("0");
    }

    public void ClearCTHD() {
        DefaultTableModel model = (DefaultTableModel) cthd_table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 0);
            model.setValueAt(0, i, 2);
        }
    }
    
    public void ClearCTPN() {
        DefaultTableModel model = (DefaultTableModel) ctpn_table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 0);
            model.setValueAt(0, i, 2);
            model.setValueAt(0, i, 3);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CTHoaDon_dialog = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        CTHoaDon_table = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BookID_tf = new javax.swing.JTextField();
        Title_tf = new javax.swing.JTextField();
        Author_tf = new javax.swing.JTextField();
        Category_cbx = new javax.swing.JComboBox<>();
        Publisher_cbx = new javax.swing.JComboBox<>();
        Price_tf = new javax.swing.JTextField();
        StockQuantity_tf = new javax.swing.JTextField();
        AddSach_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        data_table_sach = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        UpdateSach_btn = new javax.swing.JButton();
        DeleteSach_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        MaKH_tf = new javax.swing.JTextField();
        HoTenKH_tf = new javax.swing.JTextField();
        DienThoaiKH_tf = new javax.swing.JTextField();
        AddKH_btn = new javax.swing.JButton();
        UpdateKH_btn = new javax.swing.JButton();
        DeleteKH_tf = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        data_table_kh = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DiaChiKH_TArea = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        Email_tf = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        MaNV_tf = new javax.swing.JTextField();
        HoTenNV_tf = new javax.swing.JTextField();
        DienThoaiNV_tf = new javax.swing.JTextField();
        AddNV_btn = new javax.swing.JButton();
        UpdateNV_btn = new javax.swing.JButton();
        DeleteNV_tf = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        data_table_nv = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        EmailNV_tf = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ChucVu_cbx = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        MaHD_tf = new javax.swing.JTextField();
        NgayLap_tf = new javax.swing.JTextField();
        MaNVHD_tf = new javax.swing.JTextField();
        AddHD_btn = new javax.swing.JButton();
        SuaHD_btn = new javax.swing.JButton();
        DeleteHD_btn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        data_table_hd = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        TongTien_tf = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        cthd_table = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        MKH_HD_tf = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        MaPN_tf = new javax.swing.JTextField();
        TongTienPN_tf = new javax.swing.JTextField();
        MaNVPN_tf = new javax.swing.JTextField();
        AddPN_btn = new javax.swing.JButton();
        SuaPN_btn = new javax.swing.JButton();
        DeletePN_btn = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        data_table_pn = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        NgayLapPN_tf = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        ctpn_table = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        MaNCC_tf = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("CHI TIẾT HÓA ĐƠN");

        CTHoaDon_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        CTHoaDon_table.setColumnSelectionAllowed(true);
        jScrollPane6.setViewportView(CTHoaDon_table);
        CTHoaDon_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CTHoaDon_dialogLayout = new javax.swing.GroupLayout(CTHoaDon_dialog.getContentPane());
        CTHoaDon_dialog.getContentPane().setLayout(CTHoaDon_dialogLayout);
        CTHoaDon_dialogLayout.setHorizontalGroup(
            CTHoaDon_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CTHoaDon_dialogLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        CTHoaDon_dialogLayout.setVerticalGroup(
            CTHoaDon_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CTHoaDon_dialogLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Nhà Sách");
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel2.setText("Mã sách");

        jLabel1.setText("Tiêu đề");

        jLabel3.setText("Tác giả");

        jLabel4.setText("Thể loại");

        jLabel5.setText("Nhà xuất bản");

        jLabel6.setText("Giá");

        jLabel7.setText("Số lượng tồn");

        BookID_tf.setEnabled(false);

        Category_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dystopian", "Fantasy", "Lãng mạn", "Lịch sử", "Phiêu lưu", "Tiểu thuyết", "Tiểu thuyết triết học" }));

        Publisher_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà xuất bản Hội Nhà văn", "Nhà xuất bản Kim Đồng", "Nhà xuất bản Nhã Nam", "Nhà xuất bản Thế Giới", "Nhà xuất bản Trẻ", "Nhà xuất bản Văn hóa", "Nhà xuất bản Văn học" }));

        AddSach_btn.setText("Thêm");
        AddSach_btn.setActionCommand("Add_btn");
        AddSach_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSach_btnActionPerformed(evt);
            }
        });

        data_table_sach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(data_table_sach);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("QUẢN LÝ SÁCH");

        UpdateSach_btn.setText("Cập nhật");
        UpdateSach_btn.setActionCommand("Add_btn");
        UpdateSach_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateSach_btnActionPerformed(evt);
            }
        });

        DeleteSach_btn.setText("Xóa");
        DeleteSach_btn.setActionCommand("Add_btn");
        DeleteSach_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteSach_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Category_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Title_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Author_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BookID_tf)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Price_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Publisher_cbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StockQuantity_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(AddSach_btn)
                                .addGap(18, 18, 18)
                                .addComponent(UpdateSach_btn)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteSach_btn)
                                .addGap(32, 32, 32)))))
                .addGap(83, 83, 83))
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Author_tf, BookID_tf, Category_cbx, Price_tf, Publisher_cbx, StockQuantity_tf, Title_tf});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BookID_tf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(Publisher_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(Title_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Price_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(Author_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StockQuantity_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Category_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddSach_btn)
                    .addComponent(UpdateSach_btn)
                    .addComponent(DeleteSach_btn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        jTabbedPane1.addTab("QL Sách", jPanel1);

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel8.setText("Mã khách hàng");

        jLabel9.setText("Họ tên");

        jLabel10.setText("Điện thoại");

        jLabel13.setText("Địa chỉ");

        MaKH_tf.setEnabled(false);

        AddKH_btn.setText("Thêm");
        AddKH_btn.setActionCommand("Add_btn");
        AddKH_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddKH_btnActionPerformed(evt);
            }
        });

        UpdateKH_btn.setText("Cập nhật");
        UpdateKH_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateKH_btnActionPerformed(evt);
            }
        });

        DeleteKH_tf.setText("Xóa");
        DeleteKH_tf.setActionCommand("Delete_btn");
        DeleteKH_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteKH_tfActionPerformed(evt);
            }
        });

        data_table_kh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(data_table_kh);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("QUẢN LÝ KHÁCH HÀNG");

        DiaChiKH_TArea.setColumns(20);
        DiaChiKH_TArea.setRows(5);
        jScrollPane3.setViewportView(DiaChiKH_TArea);

        jLabel11.setText("Email");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HoTenKH_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MaKH_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DienThoaiKH_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(AddKH_btn)
                                .addGap(18, 18, 18)
                                .addComponent(UpdateKH_btn)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteKH_tf))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)))))
                .addGap(83, 83, 83))
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel8, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Email_tf, MaKH_tf});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MaKH_tf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(Email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(HoTenKH_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(DienThoaiKH_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddKH_btn)
                    .addComponent(UpdateKH_btn)
                    .addComponent(DeleteKH_tf))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel13, jLabel8, jLabel9});

        jTabbedPane1.addTab("QL Khách Hàng", jPanel2);

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel12.setText("Mã nhân viên");

        jLabel14.setText("Họ tên");

        jLabel16.setText("Điện thoại");

        MaNV_tf.setEnabled(false);

        AddNV_btn.setText("Thêm");
        AddNV_btn.setActionCommand("Add_btn");
        AddNV_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNV_btnActionPerformed(evt);
            }
        });

        UpdateNV_btn.setText("Cập nhật");
        UpdateNV_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateNV_btnActionPerformed(evt);
            }
        });

        DeleteNV_tf.setText("Xóa");
        DeleteNV_tf.setActionCommand("Delete_btn");
        DeleteNV_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteNV_tfActionPerformed(evt);
            }
        });

        data_table_nv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(data_table_nv);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel18.setText("Email");

        jLabel17.setText("Chức vụ");

        ChucVu_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên bán hàng", "Nhân viên kho", "Quản lý", "Thu ngân" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DienThoaiNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HoTenNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(EmailNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ChucVu_cbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(38, 38, 38))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(AddNV_btn)
                                .addGap(18, 18, 18)
                                .addComponent(UpdateNV_btn)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteNV_tf)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(83, 83, 83))
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {EmailNV_tf, MaNV_tf});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel17, jLabel18});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(EmailNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(HoTenNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(ChucVu_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNV_btn)
                    .addComponent(UpdateNV_btn)
                    .addComponent(DeleteNV_tf)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(DienThoaiNV_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel12, jLabel14, jLabel16, jLabel17, jLabel18});

        jTabbedPane1.addTab("QL Nhân Viên", jPanel3);

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel19.setText("Mã hóa đơn");

        jLabel20.setText("Ngày lập");

        jLabel21.setText("Mã nhân viên");

        MaHD_tf.setEnabled(false);

        NgayLap_tf.setEditable(false);
        NgayLap_tf.setEnabled(false);

        AddHD_btn.setText("Thêm");
        AddHD_btn.setActionCommand("Add_btn");
        AddHD_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddHD_btnActionPerformed(evt);
            }
        });

        SuaHD_btn.setText("Sửa");
        SuaHD_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaHD_btnActionPerformed(evt);
            }
        });

        DeleteHD_btn.setText("Xóa");
        DeleteHD_btn.setActionCommand("Delete_btn");
        DeleteHD_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteHD_btnActionPerformed(evt);
            }
        });

        data_table_hd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        data_table_hd.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        data_table_hd.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(data_table_hd);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("QUẢN LÝ HÓA ĐƠN");

        jLabel22.setText("Tổng tiền");

        TongTien_tf.setText("0");
        TongTien_tf.setEnabled(false);

        cthd_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        cthd_table.setColumnSelectionAllowed(true);
        cthd_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        cthd_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(cthd_table);
        cthd_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel23.setText("Mã khách hàng");

        jLabel28.setText("HÓA ĐƠN");

        jLabel29.setText("CHI TIẾT HÓA ĐƠN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel21))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(MaNVHD_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(MaHD_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(MKH_HD_tf)))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel20)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(NgayLap_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TongTien_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(AddHD_btn)
                                        .addGap(18, 18, 18)
                                        .addComponent(SuaHD_btn)
                                        .addGap(18, 18, 18)
                                        .addComponent(DeleteHD_btn))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel19, jLabel20, jLabel21, jLabel22, jLabel23});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {MaHD_tf, TongTien_tf});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(TongTien_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaHD_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(NgayLap_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(MKH_HD_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(MaNVHD_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddHD_btn)
                    .addComponent(SuaHD_btn)
                    .addComponent(DeleteHD_btn))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel19, jLabel20, jLabel21, jLabel22, jLabel23});

        jTabbedPane1.addTab("QL Hóa Đơn", jPanel4);

        jLabel30.setText("Mã phiếu nhập");

        jLabel31.setText("Tổng tiền");

        jLabel32.setText("Mã nhân viên");

        MaPN_tf.setEnabled(false);

        TongTienPN_tf.setEditable(false);
        TongTienPN_tf.setText("0");
        TongTienPN_tf.setEnabled(false);

        AddPN_btn.setText("Thêm");
        AddPN_btn.setActionCommand("Add_btn");
        AddPN_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPN_btnActionPerformed(evt);
            }
        });

        SuaPN_btn.setText("Sửa");
        SuaPN_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaPN_btnActionPerformed(evt);
            }
        });

        DeletePN_btn.setText("Xóa");
        DeletePN_btn.setActionCommand("Delete_btn");
        DeletePN_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletePN_btnActionPerformed(evt);
            }
        });

        data_table_pn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        data_table_pn.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        data_table_pn.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane8.setViewportView(data_table_pn);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("QUẢN LÝ PHIẾU NHẬP");

        jLabel34.setText("Ngày Lập");

        NgayLapPN_tf.setEnabled(false);

        ctpn_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ctpn_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ctpn_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane9.setViewportView(ctpn_table);
        ctpn_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel35.setText("Mã nhà cung cấp");

        jLabel36.setText("PHIẾU NHẬP");

        jLabel37.setText("CHI TIẾT PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel32))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(MaNVPN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(MaPN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel35)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(MaNCC_tf)))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NgayLapPN_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                            .addComponent(TongTienPN_tf)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(AddPN_btn)
                                        .addGap(18, 18, 18)
                                        .addComponent(SuaPN_btn)
                                        .addGap(18, 18, 18)
                                        .addComponent(DeletePN_btn))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(57, 57, 57))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel30, jLabel31, jLabel32, jLabel34, jLabel35});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(NgayLapPN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaPN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(TongTienPN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(MaNCC_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(MaNVPN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPN_btn)
                    .addComponent(SuaPN_btn)
                    .addComponent(DeletePN_btn))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel30, jLabel31, jLabel32, jLabel34, jLabel35});

        jTabbedPane1.addTab("Phiếu Nhập Sách", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setSize(new java.awt.Dimension(970, 809));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AddSach_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSach_btnActionPerformed
        AddBook();
    }//GEN-LAST:event_AddSach_btnActionPerformed

    private void UpdateSach_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateSach_btnActionPerformed
        UpdateBook();
    }//GEN-LAST:event_UpdateSach_btnActionPerformed

    private void DeleteSach_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteSach_btnActionPerformed
        DeleteBook();
    }//GEN-LAST:event_DeleteSach_btnActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        ClearSach();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int selectedTab = jTabbedPane1.getSelectedIndex();
        switch (selectedTab) {
            case 0:
                LoadDataIntoTableSach();
                break;
            case 1:
                LoadDataIntoTableKH();
                break;
            case 2:
                LoadDataIntoTableNV();
                break;
            case 3:
                LoadDataIntoTableHD();
                LoadDataIntoTableCTHoaDon();
                break;
            case 4:
                LoadDataIntoTablePN();
                LoadDataIntoTableCTPhieuNhap();
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        ClearKH();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void AddKH_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddKH_btnActionPerformed
        AddKH();
    }//GEN-LAST:event_AddKH_btnActionPerformed

    private void UpdateKH_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateKH_btnActionPerformed
        UpdateKH();
    }//GEN-LAST:event_UpdateKH_btnActionPerformed

    private void DeleteKH_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteKH_tfActionPerformed
        DeleteKH();
    }//GEN-LAST:event_DeleteKH_tfActionPerformed

    private void AddNV_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNV_btnActionPerformed
        AddNV();
    }//GEN-LAST:event_AddNV_btnActionPerformed

    private void UpdateNV_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateNV_btnActionPerformed
        UpdateNV();
    }//GEN-LAST:event_UpdateNV_btnActionPerformed

    private void DeleteNV_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteNV_tfActionPerformed
        DeleteNV();
    }//GEN-LAST:event_DeleteNV_tfActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        ClearNV();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void SuaHD_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaHD_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuaHD_btnActionPerformed

    private void DeleteHD_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteHD_btnActionPerformed
        DeleteHD();
    }//GEN-LAST:event_DeleteHD_btnActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        ClearHD();
        ClearCTHD();
    }//GEN-LAST:event_jPanel4MouseClicked

    private void AddHD_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddHD_btnActionPerformed
        AddHD();
    }//GEN-LAST:event_AddHD_btnActionPerformed

    private void AddPN_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPN_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPN_btnActionPerformed

    private void SuaPN_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaPN_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuaPN_btnActionPerformed

    private void DeletePN_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletePN_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeletePN_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLNhaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNhaSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddHD_btn;
    private javax.swing.JButton AddKH_btn;
    private javax.swing.JButton AddNV_btn;
    private javax.swing.JButton AddPN_btn;
    private javax.swing.JButton AddSach_btn;
    private javax.swing.JTextField Author_tf;
    private javax.swing.JTextField BookID_tf;
    private javax.swing.JDialog CTHoaDon_dialog;
    private javax.swing.JTable CTHoaDon_table;
    private javax.swing.JComboBox<String> Category_cbx;
    private javax.swing.JComboBox<String> ChucVu_cbx;
    private javax.swing.JButton DeleteHD_btn;
    private javax.swing.JButton DeleteKH_tf;
    private javax.swing.JButton DeleteNV_tf;
    private javax.swing.JButton DeletePN_btn;
    private javax.swing.JButton DeleteSach_btn;
    private javax.swing.JTextArea DiaChiKH_TArea;
    private javax.swing.JTextField DienThoaiKH_tf;
    private javax.swing.JTextField DienThoaiNV_tf;
    private javax.swing.JTextField EmailNV_tf;
    private javax.swing.JTextField Email_tf;
    private javax.swing.JTextField HoTenKH_tf;
    private javax.swing.JTextField HoTenNV_tf;
    private javax.swing.JTextField MKH_HD_tf;
    private javax.swing.JTextField MaHD_tf;
    private javax.swing.JTextField MaKH_tf;
    private javax.swing.JTextField MaNCC_tf;
    private javax.swing.JTextField MaNVHD_tf;
    private javax.swing.JTextField MaNVPN_tf;
    private javax.swing.JTextField MaNV_tf;
    private javax.swing.JTextField MaPN_tf;
    private javax.swing.JTextField NgayLapPN_tf;
    private javax.swing.JTextField NgayLap_tf;
    private javax.swing.JTextField Price_tf;
    private javax.swing.JComboBox<String> Publisher_cbx;
    private javax.swing.JTextField StockQuantity_tf;
    private javax.swing.JButton SuaHD_btn;
    private javax.swing.JButton SuaPN_btn;
    private javax.swing.JTextField Title_tf;
    private javax.swing.JTextField TongTienPN_tf;
    private javax.swing.JTextField TongTien_tf;
    private javax.swing.JButton UpdateKH_btn;
    private javax.swing.JButton UpdateNV_btn;
    private javax.swing.JButton UpdateSach_btn;
    private javax.swing.JTable cthd_table;
    private javax.swing.JTable ctpn_table;
    private javax.swing.JTable data_table_hd;
    private javax.swing.JTable data_table_kh;
    private javax.swing.JTable data_table_nv;
    private javax.swing.JTable data_table_pn;
    private javax.swing.JTable data_table_sach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
