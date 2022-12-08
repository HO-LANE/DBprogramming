package DAO;

import DTO.MenuDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public void insertMenu(String menu_name, String category, int price, int quantity, int store_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO menu(menu_name, category, price, quantity, store_id, registed) values(?, ?, ?, ?, ?, 0)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, menu_name);
            pstmt.setString(2, category);
            pstmt.setInt(3, price);
            pstmt.setInt(4, quantity);
            pstmt.setInt(5, store_id);
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch(ClassNotFoundException e){
            System.out.println("error : " + e);
        } finally{
            try{
                if(conn != null && rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !pstmt.isClosed()){
                    pstmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public List<MenuDTO> selectAllMenu() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT menu_id, menu_name, category, price, quantity, store_id, registed FROM menu";
        List<MenuDTO> menuDTOs = new ArrayList<>();
        MenuDTO menuDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                menuDTO = new MenuDTO();
                int menu_id = rs.getInt("menu_id");
                String menu_name = rs.getString("menu_name");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int store_id = rs.getInt("store_id");
                int registed = rs.getInt("registed");
                
                menuDTO.setMenuId(menu_id);
                menuDTO.setMenuName(menu_name);
                menuDTO.setCategory(category);
                menuDTO.setPrice(price);
                menuDTO.setQuantity(quantity);
                menuDTO.setStoreId(store_id);
                menuDTO.setRegisted(registed);
                menuDTOs.add(menuDTO);
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch(ClassNotFoundException e){
            System.out.println("error : " + e);
        }
        finally{
            try{
                if(conn != null && rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return menuDTOs;
    }

    public MenuDTO selectMenu(String menuName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT menu_id, menu_name, category, price, quantity, store_id, registed FROM menu WHERE menu_name = '" + menuName + "'";
        MenuDTO menuDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                menuDTO = new MenuDTO();
                int menu_id = rs.getInt("menu_id");
                String menu_name = rs.getString("menu_name");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int store_id = rs.getInt("store_id");
                int registed = rs.getInt("registed");
                
                menuDTO.setMenuId(menu_id);
                menuDTO.setMenuName(menu_name);
                menuDTO.setCategory(category);
                menuDTO.setPrice(price);
                menuDTO.setQuantity(quantity);
                menuDTO.setStoreId(store_id);
                menuDTO.setRegisted(registed);
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch(ClassNotFoundException e){
            System.out.println("error : " + e);
        }
        finally{
            try{
                if(conn != null && rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return menuDTO;
    }

    public int selectQuantity(String menuName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT quantity FROM menu WHERE menu_name = '" + menuName +"'";
        int quantity = -1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch(ClassNotFoundException e){
            System.out.println("error : " + e);
        }
        finally{
            try{
                if(conn != null && rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return quantity;
    }

    public int selectPrice(String menuName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT price FROM menu WHERE menu_name = '" + menuName +"'";
        int price = -1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) {
                price = rs.getInt("price");
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch(ClassNotFoundException e){
            System.out.println("error : " + e);
        }
        finally{
            try{
                if(conn != null && rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return price;
    }

    public List<String> selectAllCategory(int store_id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT category FROM menu WHERE store_id = " + store_id;
        List<String> categorys = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) {
                String category = rs.getString("category");
                categorys.add(category);
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch(ClassNotFoundException e){
            System.out.println("error : " + e);
        }
        finally{
            try{
                if(conn != null && rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return categorys;
    }

    public List<MenuDTO> selectAllMenuByCatetory(String category_param) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT menu_id, menu_name, category, price, quantity, store_id, registed FROM menu WHERE category = '" + category_param + "'";
        List<MenuDTO> menuDTOs = new ArrayList<>();
        MenuDTO menuDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                menuDTO = new MenuDTO();
                int menu_id = rs.getInt("menu_id");
                String menu_name = rs.getString("menu_name");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int store_id = rs.getInt("store_id");
                int registed = rs.getInt("registed");
                
                menuDTO.setMenuId(menu_id);
                menuDTO.setMenuName(menu_name);
                menuDTO.setCategory(category);
                menuDTO.setPrice(price);
                menuDTO.setQuantity(quantity);
                menuDTO.setStoreId(store_id);
                menuDTO.setRegisted(registed);
                menuDTOs.add(menuDTO);
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch(ClassNotFoundException e){
            System.out.println("error : " + e);
        }
        finally{
            try{
                if(conn != null && rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return menuDTOs;
    }
    public void updateMenuNameAndPrice(String curName, String name, int price) {
        Connection conn = null;
        String url = null;
        PreparedStatement pstmt = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            String query = "UPDATE menu SET price = ?, menu_name = ? WHERE menu_name = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setLong(1, price);
            pstmt.setString(2, name);
            pstmt.setString(3, curName);

            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            try{
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void updateMenuPrice(String name, int price) {
        Connection conn = null;
        String url = null;
        PreparedStatement pstmt = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            String query = "UPDATE menu SET price = ? WHERE menu_name = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setLong(1, price);
            pstmt.setString(2, name);

            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            try{
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void updateMenuName(String curName, String name) {
        Connection conn = null;
        String url = null;
        PreparedStatement pstmt = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            String query = "UPDATE menu SET menu_name = ? WHERE menu_name = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, curName);

            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("error : " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            try{
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
