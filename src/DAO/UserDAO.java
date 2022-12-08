package DAO;

import java.sql.*;

public class UserDAO {    
    public void insertUser(String id_param, String passwd_param, String name_param, String phone_num_param){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO user(id, passwd, name, phone_num, user_status) values(?, ?, ?, ?, 0)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, id_param);
            pstmt.setString(2, passwd_param);
            pstmt.setString(3, name_param);
            pstmt.setString(4, phone_num_param);
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
    public String findPasswd(String id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url = null;
        String passwd = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "op1234");
            String query = "SELECT passwd FROM user WHERE id = '" + id +"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                passwd = rs.getString("passwd");
            }
        } catch (SQLException e) {
            System.out.println("error : " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null && !rs.isClosed()) {
                    rs.close();
                }
                if (conn != null && !stmt.isClosed()) {
                    rs.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passwd;
    }
}
