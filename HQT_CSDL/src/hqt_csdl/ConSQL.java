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


public class ConSQL implements INhaSach{
    static Connection cn;
    
    public void getCon() {
        try {
            if (cn == null || cn.isClosed()) {
                cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QlyNhaSach;user=sa;password=123456;encrypt=true;trustServerCertificate=true");
                System.out.println("ket noi thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Book> LoadData(){
        getCon();
        String querry = "Select * from Books";
        ArrayList<Book> ListEmployees = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(querry)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getInt("BookID"), rs.getString("Title"), rs.getString("Author"), 
                        rs.getString("Category"), rs.getString("Publisher"), rs.getDouble("Price"), 
                        rs.getInt("StockQuantity"));
                ListEmployees.add(book);
            }
            return ListEmployees;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
