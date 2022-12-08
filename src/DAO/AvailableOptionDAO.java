package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AvailableOptionDAO {
    public void insertOption(int option_num, int menu_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO available_option(option_num, menu_id) values(?, ?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, option_num);
            pstmt.setInt(2, menu_id);
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

    public List<Integer> selectOptionNum(int menu_id_param) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT option_num FROM available WHERE menu_id = " + menu_id_param;
        List<Integer> optionNumbers = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            for(; rs.next();){
                int optionNumber = rs.getInt("option_num");
                optionNumbers.add(optionNumber);
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
        return optionNumbers;
    }
}
