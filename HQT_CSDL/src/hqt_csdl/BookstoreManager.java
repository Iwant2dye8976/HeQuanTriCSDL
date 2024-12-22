/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hqt_csdl;

/**
 *
 * @author DONG
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookstoreManager extends JFrame implements ActionListener {
    
    public JTextField BookID, Title, Author, Publisher, Price, StockQuantity, jt_Search;
    public JComboBox<String> jcb_Select;
    public ButtonGroup btGr;
    public JRadioButton jrb_Nam, jrb_Nu;
    public JButton jb_Add, jb_Delete, jb_Update, jb_Search;
    public JPanel jp_head, jp, jp1, jp2, jpGrSex;
    public JTable tb_ns;
    public DefaultTableModel tableModel;
    public ConSQL xld;

    public BookstoreManager() {
        this.setTitle("Quản lý nhà sách");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5, 10));

        // Khởi tạo các JPanel
        jp_head = new JPanel();
        jp = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();

        // ComboBox danh sách lớp
        String ListCategory[] = {"Novel", "Thrillers & Horror", "Romance", "Short Stories", "History", "Mystery"};
        jcb_Select = new JComboBox<>(ListCategory);
        btGr = new ButtonGroup();

        jp_head.setLayout(new BorderLayout(10, 0));
        jp.setLayout(new GridLayout(5, 2));
        jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jp.add(new JLabel("BookID: "));
        BookID = new JTextField();
        jp.add(BookID);

        jp.add(new JLabel("Title: "));
        Title = new JTextField();
        jp.add(Title);
        
        jp.add(new JLabel("Author: "));
        Author = new JTextField();
        jp.add(Author);

        jp.add(new JLabel("Category: "));
        jp.add(jcb_Select);

        jp.add(new JLabel("Publisher: "));
        Publisher = new JTextField();
        jp.add(Publisher);

        jp.add(new JLabel("Price: "));
        Price = new JTextField();
        jp.add(Price);
        
        jp.add(new JLabel("StockQuantity: "));
        StockQuantity = new JTextField();
        jp.add(StockQuantity);

        // Nút thêm, cập nhật
        jb_Add = new JButton("Add");
        jb_Add.setPreferredSize(new Dimension(75, 30));
        jb_Add.addActionListener(this);
        jp.add(jb_Add);

        jb_Update = new JButton("Update");
        jb_Update.setPreferredSize(new Dimension(75, 30));
        jp.add(jb_Update);

        // Nút tìm kiếm và thanh tìm kiếm
        jt_Search = new JTextField(30);
        jb_Search = new JButton("Search");
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(jt_Search);
        searchPanel.add(jb_Search);

        // Panel cho các nút thao tác
        JPanel btPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btPanel.add(jb_Add);
        btPanel.add(jb_Update);

        jp_head.add(jp, BorderLayout.CENTER);
        jp_head.add(btPanel, BorderLayout.SOUTH);
        jp_head.add(searchPanel, BorderLayout.NORTH);

        // Tạo bảng quản lý
        tb_ns = new JTable();
        tb_ns.setRowHeight(30);
        updateTable();
        JScrollPane scrollPane = new JScrollPane(tb_ns);
        jp1.add(scrollPane);
        
        
        // Nút xóa
        jb_Delete = new JButton("Delete");
        jp2.add(jb_Delete);

        // Thêm các panel vào JFrame
        this.add(jp_head, BorderLayout.NORTH);
        this.add(jp1, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    private void updateTable() {
        ArrayList<Book> lns = xld.LoadData();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "BookID", "Title", "Author", "Category", "Publisher", "Price", "StockQuantity"
        });

        for (Book ns : lns) {
            model.addRow(new Object[]{
                ns.getBookID(), ns.getTitle(), ns.getAuthor(), ns.getCategory(), 
                ns.getPublisher(), ns.getPrice(), ns.getStockQuantity()
            });
        }
        tb_ns.setModel(model);
    }
    
    public static void main(String[] args) {
        BookstoreManager app = new BookstoreManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
