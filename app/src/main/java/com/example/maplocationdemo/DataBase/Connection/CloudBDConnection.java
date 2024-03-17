package com.example.maplocationdemo.DataBase.Connection;

import com.example.maplocationdemo.DataBase.Address;
import com.example.maplocationdemo.DataBase.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CloudBDConnection {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://rm-cn-nwy3mysqr000ftpo.rwlb.rds.aliyuncs.com:3306/test";
    private static final String userName = "wxf";
    private static final String password = "362426WxF!!07";
    public static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url, userName, password);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }


    public static void link(Address address) {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("获取数据库连接失败！");
            return;
        }

        try {
            String sql = "INSERT INTO address (Phone, time, locType, locTypeDescription, latitude, longitude, radius, Province, citycode, city, District, Town, Street, addr, StreetNumber, UserIndoorState, Direction, locationdescribe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, address.getPhone());
                ps.setString(2, address.getTime());
                ps.setInt(3, address.getLocType());
                ps.setString(4, address.getLocTypeDescription());
                ps.setDouble(5, address.getLatitude());
                ps.setDouble(6, address.getLongitude());
                ps.setDouble(7, address.getRadius());
                ps.setString(8, address.getProvince());
                ps.setString(9, address.getCityCode());
                ps.setString(10, address.getCity());
                ps.setString(11, address.getDistrict());
                ps.setString(12, address.getTown());
                ps.setString(13, address.getStreet());
                ps.setString(14, address.getAddr());
                ps.setString(15, address.getStreetNumber());
                ps.setInt(16, address.getUserIndoorState());
                ps.setString(17, address.getDirection());
                ps.setString(18, address.getLocationDescribe());

                ps.executeUpdate();
                System.out.println("数据插入成功！！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 用户数据插入
    public static void insert_user_info(String userName,String password) throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("获取数据库连接失败！");
            return;
        }

        try{
            String sql = "INSERT INTO user (username,password) VALUES (?, ?)";
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, userName);
                ps.setString(2, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 用户数据查询
    public static boolean select_user_info(String userName, String password) throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("获取数据库连接失败！");
            return false;
        }

        try{
            String sql = "SELECT * FROM android WHERE username=? AND password=?";
            boolean result = false;
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, userName);
                ps.setString(2,password);
                ResultSet rs = ps.executeQuery();
                result = rs.next();
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


}

