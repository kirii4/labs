package com.train.hotel.model.entity;

import java.util.Date;

public class UserOrderList {
    private long idOrder;
    private long idRoom;
    private String clasRoom;
    private long guests;
    private Date dateOfSettlement;
    private Date dateOfDeparture;
    private String paymentStatus;

    public long getIdRoom() {return idRoom;}

    public long getIdOrder() {
        return idOrder;
    }


    public String getClasRoom() {
        return clasRoom;
    }


    public long getGuests() {
        return guests;
    }


    public Date getDateOfSettlement() {
        return dateOfSettlement;
    }


    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }


    public String getPaymentStatus() {
        return paymentStatus;
    }


    public static class UserOrderListBuilder{
        private UserOrderList userOrderList;

        public UserOrderListBuilder() {
            userOrderList = new UserOrderList();
        }
        public UserOrderListBuilder setIdRoom(long idRoom) {
            userOrderList.idRoom = idRoom;
            return this;
        }
        public UserOrderListBuilder setIdOrder(long idOrder) {
            userOrderList.idOrder = idOrder;
            return this;
        }
        public UserOrderListBuilder setClasRoom(String clasRoom) {
            userOrderList.clasRoom = clasRoom;
            return this;
        }
        public UserOrderListBuilder setGuests(long guests) {
            userOrderList.guests = guests;
            return this;
        }
        public UserOrderListBuilder setDateOfSettlement(Date dateOfSettlement) {
            userOrderList.dateOfSettlement = dateOfSettlement;
            return this;
        }
        public UserOrderListBuilder setDateOfDeparture(Date dateOfDeparture) {
            userOrderList.dateOfDeparture = dateOfDeparture;
            return this;
        }
        public UserOrderListBuilder setPaymentStatus(String paymentStatus) {
            userOrderList.paymentStatus = paymentStatus;
            return this;
        }

        public UserOrderList build(){
            return userOrderList;
        }

    }


}
