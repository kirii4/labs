package com.train.hotel.model.dao;

import com.train.hotel.controller.DBHandler;
import com.train.hotel.model.entity.Room;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private static final Logger log = Logger.getLogger(RoomDao.class);


    public static List<Room> list(String sortBy, String locale) {
        ArrayList<Room> list = new ArrayList<>();
        Connection conn=null;
        Room room = null;
        try {
            conn= DBHandler.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(
                    "SELECT id_room, image, rooms.id_class_of_room ,class_room_"+locale+", guests, price, rooms.status_" + locale + " FROM rooms\n" +
                    "RIGHT JOIN class_of_room ON rooms.id_class_of_room = class_of_room.id_class_of_room WHERE id_room > 0 " +
                    "order by " + sortBy);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                room = new Room.RoomBuilder()
                        .setIdRoom(rs.getLong("id_room"))
                        .setImage(rs.getString("image"))
                        .setIdClassOfRoom(rs.getLong("id_class_of_room"))
                        .setClassRoom(rs.getString("class_room_" + locale))
                        .setGuests(rs.getInt("guests"))
                        .setPrice(rs.getInt("price"))
                        .setStatus(rs.getString("status_" + locale))
                        .build();
                list.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return list;
    }

    public static List<Room> listSelect(String locale) {
        ArrayList<Room> list = new ArrayList<>();
        Connection conn=null;
        Room room = null;
        try {
            conn= DBHandler.getConnection();
            PreparedStatement pstmt=conn.prepareStatement("SELECT id_room, class_room_"+locale+", guests FROM rooms RIGHT JOIN class_of_room " +
                    "ON rooms.id_class_of_room = class_of_room.id_class_of_room WHERE status_en = 'available' and id_room > 0 ORDER BY class_of_room.id_class_of_room");
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                room = new Room.RoomBuilder()
                        .setIdRoom(rs.getLong("id_room"))
                        .setClassRoom(rs.getString("class_room_" + locale))
                        .setGuests(rs.getInt("guests"))
                        .build();
                list.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
        return list;
    }

    public static void updateRoomStatus(long idRoom, String roomStatusEn, String roomStatusUk) {
        Connection conn = null;
        try {
            conn = DBHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE rooms SET status_en = ?, status_uk = ? WHERE id_room = ?");
            stmt.setString(1, roomStatusEn);
            stmt.setString(2, roomStatusUk);
            stmt.setLong(3, idRoom);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBHandler.closeConnection(conn);
        }
    }

}
