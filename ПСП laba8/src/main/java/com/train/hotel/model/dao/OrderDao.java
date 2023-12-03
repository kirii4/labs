package com.train.hotel.model.dao;

import com.train.hotel.controller.DBHandler;
import com.train.hotel.model.entity.Order;
import com.train.hotel.model.entity.Payment;
import com.train.hotel.model.entity.UserOrderList;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class OrderDao {

    private static final Logger log = Logger.getLogger(OrderDao.class);


    public static long addOrder(Order order) {
        int i = 0;
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("insert into orders " +
                    "(id_order, id_user, date_of_settlement, date_of_departure, id_room, payment_status_en, payment_status_uk, date_of_create_order, amount_of_guests, room_type) " +
                    "values(?,?,?,?,?,?,?,?,?,?)");
            stmt.setLong(1, nextPk());
            stmt.setLong(2 , order.getIdUser());
            stmt.setDate(3 , new java.sql.Date(order.getDateOfSettlement().getTime()));
            stmt.setDate(4 , new java.sql.Date(order.getDateOfDeparture().getTime()));
            stmt.setLong(5 , order.getIdRoom());
            stmt.setString(6 , order.getPaymentStatus());
            stmt.setString(7 , "розглядається");
            stmt.setDate(8 , new java.sql.Date(order.getDateOfCreateOrder().getTime()));
            stmt.setLong(9, order.getAmountOfGuests());
            stmt.setString(10, order.getTypeOfRoom());
            i =     stmt.executeUpdate();
            DBHandler.closeConnection(conn);
            if(order.getPaymentStatus().equals("need to pay")){
                RoomDao.updateRoomStatus(order.getIdRoom(), "booked", "заброньований");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }

        return i;
    }
    public static long nextPk() {
        long pk = 0;
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select Max(id_order) from orders");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                pk = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return pk+1;
    }


    public static List<Order> AdminOrderList() {
        ArrayList<Order> list = new ArrayList<>();
        Connection conn=null;
        Order order = null;
        try {
            conn= DBHandler.getConnection();

            PreparedStatement pstmt=conn.prepareStatement("SELECT id_order, id_user, amount_of_guests, room_type, + " +
                    "date_of_settlement, date_of_departure, date_of_create_order, id_room FROM orders WHERE id_room = 0");
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                order = new Order.OrderBuilder()
                        .setIdOrder(rs.getLong("id_order"))
                        .setIdUser(rs.getLong("id_user"))
                        .setAmountOfGuests(rs.getLong("amount_of_guests"))
                        .setTypeOfRoom(rs.getString("room_type"))
                        .setDateOfSettlement(rs.getDate("date_of_settlement"))
                        .setDateOfDeparture(rs.getDate("date_of_departure"))
                        .setDateOfCreateOrder(rs.getDate("date_of_create_order"))
                        .setIdRoom(rs.getLong("id_room"))
                        .build();

                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return list;
    }
    public static long adminOrderChange(long idRoom, long idOrder) {
        int i = 0;
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET id_room = ?, payment_status_en = 'waiting to confirmation', payment_status_uk = 'потрібне підтвердження' WHERE id_order = ?");
            stmt.setLong(1, idRoom);
            stmt.setLong(2, idOrder);
            i =     stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return i;
    }

    public static void DeleteOrder(long idOrder) {
        int i = 0;
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders WHERE id_order = ?");
            stmt.setLong(1, idOrder);
            i =     stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
    }

    public static void updateStatus(long idOrder, String status_en, String status_uk) {
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET payment_status_en = ?, payment_status_uk = ? WHERE id_order = ?");
            stmt.setString(1, status_en);
            stmt.setString(2, status_uk);
            stmt.setLong(3, idOrder);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
    }

    public static void updateStatusWithRoom(long idOrder, long idRoom, String paymentStatus_en, String paymentStatus_uk, String status_en, String status_uk) {

        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE orders LEFT join rooms on rooms.id_room = orders.id_room " +
                    "SET payment_status_en = ?, payment_status_uk = ? ,status_en = ? ,status_uk = ? " +
                    "WHERE id_order = ? and rooms.id_room = ?");
            stmt.setString(1, paymentStatus_en);
            stmt.setString(2, paymentStatus_uk);
            stmt.setString(3, status_en);
            stmt.setString(4, status_uk);
            stmt.setLong(5, idOrder);
            stmt.setLong(6, idRoom);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
    }

    public static List<UserOrderList> UserOrderlist(long idUser, String locale) {
        ArrayList<UserOrderList> list = new ArrayList<>();
        Connection conn=null;
        UserOrderList userOrderList = null;
        try {
            conn= DBHandler.getConnection();
            PreparedStatement pstmt=conn.prepareStatement("SELECT id_order,rooms.id_room, class_room_"+ locale +",  guests, date_of_settlement, date_of_departure, payment_status_"+ locale + " " +
                    "FROM orders \n" +
                    "LEFT JOIN rooms ON orders.id_room = rooms.id_room \n" +
                    "RIGHT JOIN class_of_room ON rooms.id_class_of_room = class_of_room.id_class_of_room\n" +
                    "WHERE id_user = ?\n" +
                    "ORDER BY id_order DESC");
            pstmt.setLong(1, idUser);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                userOrderList = new UserOrderList.UserOrderListBuilder()
                        .setIdOrder(rs.getLong("id_order"))
                        .setIdRoom(rs.getLong("rooms.id_room"))
                        .setClasRoom(rs.getString("class_room_" + locale))
                        .setGuests(rs.getLong("guests"))
                        .setDateOfSettlement(rs.getDate("date_of_settlement"))
                        .setDateOfDeparture(rs.getDate("date_of_departure"))
                        .setPaymentStatus(rs.getString("payment_status_" + locale))
                        .build();
                list.add(userOrderList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return list;
    }

    public static Payment PaymentAttributes(long id_orders, String locale) {
        Connection conn = null;
        Payment payment = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT image, id_order, rooms.id_room, class_of_room.class_room_"+locale+", class_of_room.guests, date_of_settlement, date_of_departure,  price  FROM orders \n" +
                    "LEFT JOIN rooms ON orders.id_room = rooms.id_room\n" +
                    "RIGHT JOIN class_of_room ON rooms.id_class_of_room = class_of_room.id_class_of_room\n" +
                    "WHERE id_order = ?");
            stmt.setLong(1,id_orders);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                payment = new Payment.PaymentBuilder()
                        .setImage(rs.getString("image"))
                        .setIdOrder(rs.getLong("id_order"))
                        .setIdRoom(rs.getLong("rooms.id_room"))
                        .setClassRoom(rs.getString("class_of_room.class_room_" + locale))
                        .setGuests(rs.getLong("class_of_room.guests"))
                        .setDateOfSettlement(rs.getDate("date_of_settlement"))
                        .setDateOfDeparture(rs.getDate("date_of_departure"))
                        .setPrice(rs.getLong("price"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return payment;
    }



}
