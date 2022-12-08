package DAO;

import DTO.ReviewDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    public void insertReview(int order_id, int star, String message){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String insertQuery = "INSERT INTO review(order_id, star, message) values(?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, order_id);
            pstmt.setInt(2, star);
            pstmt.setString(3, message);
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

    public List<ReviewDTO> selectAllReview(int from, int lineCount) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url;
        String selectQuery = "SELECT review_id, order_id, star, message FROM review limit " + from +", " + lineCount ;
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        ReviewDTO reviewDTO = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                reviewDTO = new ReviewDTO();
                int review_id = rs.getInt("review_id");
                int order_id = rs.getInt("order_id");
                int star = rs.getInt("star");
                String message = rs.getString("message");
                
                reviewDTO.setReview_id(review_id);
                reviewDTO.setOrder_id(order_id);
                reviewDTO.setStar(star);
                reviewDTO.setMessage(message);
                reviewDTOs.add(reviewDTO);
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
        return reviewDTOs;
    }
}
