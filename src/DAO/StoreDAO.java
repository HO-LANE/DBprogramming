package DAO;

import DTO.StoreDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StoreDAO {

    public void insertStore(String store_name_param, String introduce_param, String location_param, String store_phone_number_param
    , String open, String close, String owner){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO store(store_name, introduce, location, store_call_number, open, close, registed, owner) values(?, ?, ?, ?, ?, ?, 0, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, store_name_param);
            pstmt.setString(2, introduce_param);
            pstmt.setString(3, location_param);
            pstmt.setString(4, store_phone_number_param);
            pstmt.setString(5, open);
            pstmt.setString(6, close);
            pstmt.setString(7, owner);
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
    public void acceptStore(String storeName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String url = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            String query = "UPDATE store SET registed = 2 WHERE store_name = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, storeName);
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

    public void rejectStore(String storeName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String url = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            String query = "UPDATE store SET registed = 1 WHERE store_name = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, storeName);
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

    public List<StoreDTO> selectAllStore() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT store_id, store_name, introduce, location, store_call_number, open, close, registed, owner FROM store";
        List<StoreDTO> storeDTOs = new ArrayList<>();
        StoreDTO storeDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                storeDTO = new StoreDTO();
                
                int store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String introduce = rs.getString("introduce");
                String location = rs.getString("location");
                String store_call_number = rs.getString("store_call_number");
                String open = rs.getString("open");
                String close = rs.getString("close");
                int registed = rs.getInt("registed");
                String owner = rs.getString("owner");
                
                storeDTO.setStoreId(store_id);
                storeDTO.setStoreName(store_name);
                storeDTO.setIntroduce(introduce);
                storeDTO.setLocation(location);
                storeDTO.setStoreCallNumber(store_call_number);
                storeDTO.setOpen(open);
                storeDTO.setClose(close);
                storeDTO.setRegisted(registed);
                storeDTO.setOwner(owner);
                storeDTOs.add(storeDTO);
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
        return storeDTOs;
    }
    public List<StoreDTO> selectAcceptedStore() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT store_id, store_name, introduce, location, store_call_number, open, close, registed, owner FROM store WHERE registed = 2";
        List<StoreDTO> storeDTOs = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                StoreDTO storeDTO = new StoreDTO();

                int store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String introduce = rs.getString("introduce");
                String location = rs.getString("location");
                String store_call_number = rs.getString("store_call_number");
                String open = rs.getString("open");
                String close = rs.getString("close");
                int registed = rs.getInt("registed");
                String owner = rs.getString("owner");

                storeDTO.setStoreId(store_id);
                storeDTO.setStoreName(store_name);
                storeDTO.setIntroduce(introduce);
                storeDTO.setLocation(location);
                storeDTO.setStoreCallNumber(store_call_number);
                storeDTO.setOpen(open);
                storeDTO.setClose(close);
                storeDTO.setRegisted(registed);
                storeDTO.setOwner(owner);
                storeDTOs.add(storeDTO);
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
        return storeDTOs;
    }

    public int selectStoreId(String storeName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT store_id FROM store WHERE store_name = '" + storeName +"'";
        int storeId = -1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            if(rs.next()) 
                storeId = rs.getInt("store_id");
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
        return storeId;
    }
}
