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
import javax.swing.table.DefaultTableModel;

public class BookStoreM extends javax.swing.JFrame {

    private ConSQL conn = new ConSQL();
    private String[] columns = {"MaSach", "TieuDe", "TacGia", "TheLoai", "NhaXuatBan", "Gia", "SoLuongTon"};

    public BookStoreM() {
        initComponents();
        LoadDataIntoTable();
    }

    public void LoadDataIntoTable() {
        try {
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            ResultSet rs = conn.GetBooks();
            while (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
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
            data_table.setModel(model);

            data_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = data_table.getSelectedRow();
                        if (selectedRow != -1) {
                            BookID_tf.setText(data_table.getValueAt(selectedRow, 0).toString().trim());
                            Title_tf.setText(data_table.getValueAt(selectedRow, 1).toString().trim());
                            Author_tf.setText(data_table.getValueAt(selectedRow, 2).toString().trim());
                            Category_cbx.setSelectedItem(data_table.getValueAt(selectedRow, 3).toString().trim());
                            Publisher_cbx.setSelectedItem(data_table.getValueAt(selectedRow, 4).toString().trim());
                            Price_tf.setText(data_table.getValueAt(selectedRow, 5).toString().trim());
                            StockQuantity_tf.setText(data_table.getValueAt(selectedRow, 6).toString().trim());
                        }
                    }
                }
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kết nối CSDL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
                conn.AddBook(Title, Author, Category, Publisher, Price, StockQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                conn.UpdateBook(BookID, Title, Author, Category, Publisher, Price, StockQuantity);
//                System.out.println(data_table.getSelectedRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteBook() {
        try {
            int BookID = Integer.parseInt(BookID_tf.getText().trim());
            conn.DeleteBook(BookID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Clear() {
        BookID_tf.setText(null);
        Title_tf.setText(null);
        Author_tf.setText(null);
        Category_cbx.setSelectedIndex(0);
        Publisher_cbx.setSelectedIndex(0);
        Price_tf.setText(null);
        StockQuantity_tf.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BookID_tf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Title_tf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Author_tf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        StockQuantity_tf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Price_tf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Category_cbx = new javax.swing.JComboBox<>();
        Add_btn = new javax.swing.JButton();
        Update_btn = new javax.swing.JButton();
        Delete_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        data_table = new javax.swing.JTable();
        Publisher_cbx = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Nhà Sách");

        jLabel2.setText("BookID");

        BookID_tf.setEnabled(false);

        jLabel3.setText("Title");

        jLabel4.setText("Author");

        jLabel5.setText("Category");

        jLabel6.setText("StockQuantity");

        jLabel7.setText("Price");

        jLabel8.setText("Publisher");

        Category_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dystopian", "Fantasy", "Lãng mạn", "Lịch sử", "Phiêu lưu", "Tiểu thuyết", "Tiểu thuyết triết học" }));

        Add_btn.setText("Add");
        Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_btnActionPerformed(evt);
            }
        });

        Update_btn.setText("Update");
        Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_btnActionPerformed(evt);
            }
        });

        Delete_btn.setText("Delete");
        Delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_btnActionPerformed(evt);
            }
        });

        data_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        data_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        data_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(data_table);

        Publisher_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà xuất bản Hội Nhà văn", "Nhà xuất bản Kim Đồng", "Nhà xuất bản Nhã Nam", "Nhà xuất bản Thế Giới", "Nhà xuất bản Trẻ", "Nhà xuất bản Văn hóa", "Nhà xuất bản Văn học" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(221, 221, 221))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Title_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Author_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BookID_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Category_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StockQuantity_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Add_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Update_btn)
                                .addGap(45, 45, 45)
                                .addComponent(Delete_btn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Publisher_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Price_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Author_tf, BookID_tf, Category_cbx, Price_tf, Publisher_cbx, StockQuantity_tf, Title_tf});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Add_btn, Delete_btn, Update_btn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookID_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Publisher_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Price_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Title_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StockQuantity_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Author_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Category_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Add_btn)
                                .addComponent(Update_btn)
                                .addComponent(Delete_btn)))
                        .addGap(41, 41, 41)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        setSize(new java.awt.Dimension(846, 740));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_btnActionPerformed
        AddBook();
        LoadDataIntoTable();
        Clear();
    }//GEN-LAST:event_Add_btnActionPerformed

    private void Update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_btnActionPerformed
        UpdateBook();
        LoadDataIntoTable();
        Clear();
    }//GEN-LAST:event_Update_btnActionPerformed

    private void Delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_btnActionPerformed
        DeleteBook();
        LoadDataIntoTable();
        Clear();
    }//GEN-LAST:event_Delete_btnActionPerformed

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
            java.util.logging.Logger.getLogger(BookStoreM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookStoreM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookStoreM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookStoreM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookStoreM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_btn;
    private javax.swing.JTextField Author_tf;
    private javax.swing.JTextField BookID_tf;
    private javax.swing.JComboBox<String> Category_cbx;
    private javax.swing.JButton Delete_btn;
    private javax.swing.JTextField Price_tf;
    private javax.swing.JComboBox<String> Publisher_cbx;
    private javax.swing.JTextField StockQuantity_tf;
    private javax.swing.JTextField Title_tf;
    private javax.swing.JButton Update_btn;
    private javax.swing.JTable data_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
