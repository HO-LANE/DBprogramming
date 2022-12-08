package DAO;

import DTO.OrdersDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OrdersDAO {
    public void acceptOrder(int orderId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String url = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");
            String query = "UPDATE orders SET status = 1 WHERE order_id = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, orderId);
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
    
    public void rejectOrder(int orderId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String url = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");
            String query = "UPDATE orders SET status = 4 WHERE order_id = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, orderId);
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

    public void completeOrder(int orderId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String url = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");
            String query = "UPDATE orders SET status = 3 WHERE order_id = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, orderId);
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

    public List<OrdersDTO> selectAllOrders() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT order_id, user_id, menu_name, status FROM orders";
        List<OrdersDTO> ordersDTOs = new ArrayList<>();
        OrdersDTO ordersDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                ordersDTO = new OrdersDTO();
                int order_id = rs.getInt("order_id");
                String user_id = rs.getString("user_id");
                String menu_name = rs.getString("menu_name");
                int status = rs.getInt("status");
                
                ordersDTO.setOrderId(order_id);
                ordersDTO.setUserId(user_id);
                ordersDTO.setMenuName(menu_name);
                ordersDTO.setStatus(status);
                ordersDTOs.add(ordersDTO);
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
        return ordersDTOs;
    }

    public List<OrdersDTO> selectAllOrdersForClient(String userId) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT order_id, user_id, menu_name, status FROM orders WHERE user_id = " + userId;
        List<OrdersDTO> ordersDTOs = new ArrayList<>();
        OrdersDTO ordersDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                ordersDTO = new OrdersDTO();
                int order_id = rs.getInt("order_id");
                String user_id = rs.getString("user_id");
                String menu_name = rs.getString("menu_name");
                int status = rs.getInt("status");
                
                ordersDTO.setOrderId(order_id);
                ordersDTO.setUserId(user_id);
                ordersDTO.setMenuName(menu_name);
                ordersDTO.setStatus(status);
                ordersDTOs.add(ordersDTO);
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
        return ordersDTOs;
    }

    public OrdersDTO selectOrder(int orderId) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT order_id, user_id, menu_name, status FROM orders WHERE order_id = " + orderId;
        OrdersDTO ordersDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) {
                ordersDTO = new OrdersDTO();
                int order_id = rs.getInt("order_id");
                String user_id = rs.getString("user_id");
                String menu_name = rs.getString("menu_name");
                int status = rs.getInt("status");
                
                ordersDTO.setOrderId(order_id);
                ordersDTO.setUserId(user_id);
                ordersDTO.setMenuName(menu_name);
                ordersDTO.setStatus(status);
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
        return ordersDTO;
    }
    public void insertOrder(int order_id, String user_id, String menu_name){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO orders(order_id, user_id, menu_name, status) values(?, ?, ?, 0)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, order_id);
            pstmt.setString(2, user_id);
            pstmt.setString(3, menu_name);
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

    public int getOrderId() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT COUNT(*) FROM orders";
        int orderId = -1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) {
                orderId = rs.getInt("store_id");
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
        return orderId;
    }
}
