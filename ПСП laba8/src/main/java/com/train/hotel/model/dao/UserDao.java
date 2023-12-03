package com.train.hotel.model.dao;

import com.train.hotel.model.entity.User;
import com.train.hotel.controller.DBHandler;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private int noOfRecords;
    private static final Logger log = Logger.getLogger(UserDao.class);

    public static long nextPk() {
        long pk = 0;
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select Max(id) from user");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                pk = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return pk+1;

    }


    public static long addUser(User user) {
        int i = 0;
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
            stmt.setLong(1, nextPk());
            stmt.setString(2 , user.getFirstName() );
            stmt.setString(3 , user.getLastName() );
            stmt.setString(4 , user.getLogin() );
            stmt.setString(5 , user.getPassword() );
            stmt.setDate(6 , new java.sql.Date(user.getDob().getTime()) );
            stmt.setString(7 , user.getMobileNo() );
            stmt.setString(8 , "user");
            i =     stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }


        return i;
    }


    //Login User......
    public static User UserLogin(String login, String password) {
        Connection conn = null;
        User user = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("Select * from user where login=? and password = ?");
            stmt.setString(1,login);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                log.debug("User login ID: "+rs.getLong("id"));
                user = new User.UserBuilder()
                    .setId(rs.getLong("id"))
                    .setFirstName(rs.getString("fname"))
                    .setLastName(rs.getString("lname"))
                    .setLogin(rs.getString("login"))
                    .setPassword(rs.getString("password"))
                    .setDob(rs.getDate("dob"))
                    .setMobileNo(rs.getString("mobile"))
                    .setRole(rs.getString("role"))
                    .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }


        return user;
    }




    public static User FindByPk(long id) {
        Connection conn = null;

        User user = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("Select * from user where id=?");
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                log.debug("Find by primary key ID: "+rs.getLong("id"));
                user = new User.UserBuilder()
                        .setId(rs.getLong("id"))
                        .setFirstName(rs.getString("fname"))
                        .setLastName(rs.getString("lname"))
                        .setLogin(rs.getString("login"))
                        .setPassword(rs.getString("password"))
                        .setDob(rs.getDate("dob"))
                        .setMobileNo(rs.getString("mobile"))
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }

        return user;
    }

    public static long UpdateUser(User user) {
        int i = 0;
        Connection conn = null;

        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("update user set fname=?, lname=?, login=?,password=?,dob=?,mobile=? where id=?");

            stmt.setString(1 , user.getFirstName() );
            stmt.setString(2 , user.getLastName() );
            stmt.setString(3 , user.getLogin() );
            stmt.setString(4 , user.getPassword() );
            stmt.setDate(5 , new java.sql.Date(user.getDob().getTime()) );
            stmt.setString(6 , user.getMobileNo() );
            stmt.setLong(7, user.getId());
            i =   stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }


        return i;
    }

    public static User delete(long id) {
        User user = null;
        Connection conn = null;

        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE from user where id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }
        return user;
    }

    public static List<User> list() {
        ArrayList<User> list = new ArrayList<>();
        Connection conn = null;
        User user = null;
        try {
            conn= DBHandler.getConnection();
            PreparedStatement pstmt=conn.prepareStatement("Select * from user");
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                user = new User.UserBuilder()
                        .setId(rs.getLong("id"))
                        .setFirstName(rs.getString("fname"))
                        .setLastName(rs.getString("lname"))
                        .setLogin(rs.getString("login"))
                        .setPassword(rs.getString("password"))
                        .setDob(rs.getDate("dob"))
                        .setMobileNo(rs.getString("mobile"))
                        .build();
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return list;
    }

    public List<User> viewAllUsers(int offset, int noOfRecords) {
        String query = "select SQL_CALC_FOUND_ROWS * from user limit " + offset + ", " + noOfRecords;
        List<User> list = new ArrayList<>();
        User user = null;
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = new User.UserBuilder()
                        .setId(rs.getLong("id"))
                        .setFirstName(rs.getString("fname"))
                        .setLastName(rs.getString("lname"))
                        .setLogin(rs.getString("login"))
                        .setPassword(rs.getString("password"))
                        .setDob(rs.getDate("dob"))
                        .setMobileNo(rs.getString("mobile"))
                        .build();
                list.add(user);
            }
            rs.close();

            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHandler.closeConnection(conn);
        }
        return list;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

}
