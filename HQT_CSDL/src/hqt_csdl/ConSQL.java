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
                cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QlyNhaSach;user=sa;password=123456789;encrypt=true;trustServerCertificate=true");
                System.out.println("ket noi thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet GetBooks(){
        getCon();
        rs = null;
        String querry = "Select * from Books";
        try{
            PreparedStatement ps = cn.prepareStatement(querry);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void AddBook(String Title, String Author, String Category, String Publisher, double Price, int StockQuantity){
        String sql = "Insert into Books(Title, Author, Category, Publisher, Price, StockQuantity) Values(?,?,?,?,?,?)";
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
    
    public void UpdateBook(int BookID, String Title, String Author, String Category, String Publisher, double Price, int StockQuantity){
        String sql = "Update Books set Title=?, Author=?, Category=?, Publisher=?, Price=?, StockQuantity=? Where BookID=?";
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
        
    public void DeleteBook(int BookID) {
        String sql = "Delete Books Where BookID=?";
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
}
