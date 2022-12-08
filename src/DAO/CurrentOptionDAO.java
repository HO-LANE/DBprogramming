package DAO;

import java.sql.*;
import java.util.List;
public class CurrentOptionDAO {
    public List<Integer> selectOptionNum(int orderId){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT otpion_num FROM current_option WHERE order_id = " + orderId;
        List<Integer> optionNums = null;
        int optionNum;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                optionNum = rs.getInt("quantity");
                optionNums.add(optionNum);
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
        return optionNums;
    }
    public void insertOption(int order_id, int option_num){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO current_option(order_id, option_num) values(?, ?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, order_id);
            pstmt.setInt(2, option_num);
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
}
