package com.train.hotel.model.entity;

public class Room {
    private long idRoom;
    private long idClassOfRoom;
    private String classRoom;
    private int guests;
    private int price;
    private String status;
    private String image;


    public long getIdRoom() {
        return idRoom;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public long getIdClassOfRoom() {
        return idClassOfRoom;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public int getGuests() {
        return guests;
    }

    public long getPrice() {
        return price;
    }


    public static class RoomBuilder {
        private Room room;

        public RoomBuilder() {
            room = new Room();
        }

        public RoomBuilder setIdRoom(long idRoom) {
            room.idRoom = idRoom;
            return this;
        }
        public RoomBuilder setStatus(String status) {
            room.status = status;
            return this;
        }
        public RoomBuilder setImage(String image) {
            room.image = image;
            return this;
        }
        public RoomBuilder setIdClassOfRoom(long idClassOfRoom) {
            room.idClassOfRoom = idClassOfRoom;
            return this;
        }
        public RoomBuilder setClassRoom(String classRoom) {
            room.classRoom = classRoom;
            return this;
        }
        public RoomBuilder setGuests(int guests) {
            room.guests = guests;
            return this;
        }
        public RoomBuilder setPrice(int price) {
            room.price = price;
            return this;
        }
        public Room build(){
            return room;
        }


    }


}


