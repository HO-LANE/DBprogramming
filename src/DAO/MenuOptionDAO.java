package DAO;

import DTO.MenuOptionDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MenuOptionDAO {
    public void insertOption(String option_name_param, int price_param, int option_num_param){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO menu_option(option_name, option_price, option_num) values(?, ?, ?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, option_name_param);
            pstmt.setInt(2, price_param);
            pstmt.setInt(3, option_num_param);
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

    public List<MenuOptionDTO> selectAllMenuOption() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT option_id, option_name, option_price, option_num FROM menu_option";
        List<MenuOptionDTO> MenuOptionDTOs = new ArrayList<>();
        MenuOptionDTO MenuOptionDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                MenuOptionDTO = new MenuOptionDTO();
                int option_id = rs.getInt("option_id");
                String option_name = rs.getString("option_name");
                int price = rs.getInt("option_price");
                int option_num = rs.getInt("option_num");
                
                MenuOptionDTO.setOptionId(option_id);
                MenuOptionDTO.setOptionName(option_name);
                MenuOptionDTO.setPrice(price);
                MenuOptionDTO.setOptionNum(option_num);
                MenuOptionDTOs.add(MenuOptionDTO);
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
        return MenuOptionDTOs;
    }

    public MenuOptionDTO selectMenuOption(int optionNum) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT option_id, option_name, option_price, option_num FROM menu_option " + optionNum;
        MenuOptionDTO MenuOptionDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) {
                MenuOptionDTO = new MenuOptionDTO();
                int option_id = rs.getInt("option_id");
                String option_name = rs.getString("option_name");
                int price = rs.getInt("option_price");
                int option_num = rs.getInt("option_num");
                
                MenuOptionDTO.setOptionId(option_id);
                MenuOptionDTO.setOptionName(option_name);
                MenuOptionDTO.setPrice(price);
                MenuOptionDTO.setOptionNum(option_num);
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
        return MenuOptionDTO;
    }

    public String selectMenuOptionName(int optionNum) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT option_name FROM menu_option WHERE option_num = " + optionNum;
        String menuName = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) {
                menuName = rs.getString("option_name");
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
        return menuName;
    }
}
