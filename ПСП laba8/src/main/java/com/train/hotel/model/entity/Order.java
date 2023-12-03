package com.train.hotel.model.entity;
import java.util.Date;

public class Order {

    private long idOrder;
    private long idUser;
    private Date dateOfSettlement;
    private Date dateOfDeparture;
    private Date dateOfCreateOrder;
    private long idRoom;
    private String paymentStatus;
    private long amountOfGuests;
    private String typeOfRoom;

    public long getAmountOfGuests() {
        return amountOfGuests;
    }
    public String getTypeOfRoom() {
        return typeOfRoom;
    }
    public long getIdOrder() {
        return idOrder;
    }
    public long getIdUser() {
        return idUser;
    }
    public Date getDateOfSettlement() {
        return dateOfSettlement;
    }
    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }
    public Date getDateOfCreateOrder() {
        return dateOfCreateOrder;
    }
    public long getIdRoom() {
        return idRoom;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }


    public static class OrderBuilder{
        private Order order;

        public OrderBuilder() {
            order = new Order();
        }

        public OrderBuilder setAmountOfGuests(long amountOfGuests) {
            order.amountOfGuests = amountOfGuests;
            return this;
        }

        public OrderBuilder setTypeOfRoom(String typeOfRoom) {
            order.typeOfRoom = typeOfRoom;
            return this;
        }
        public OrderBuilder setIdOrder(long idOrder) {
            order.idOrder = idOrder;
            return this;
        }
        public OrderBuilder setIdUser(long idUser) {
            order.idUser = idUser;
            return this;
        }

        public OrderBuilder setDateOfSettlement(Date dateOfSettlement) {
            order.dateOfSettlement = dateOfSettlement;
            return this;
        }

        public OrderBuilder setDateOfDeparture(Date dateOfDeparture) {
            order.dateOfDeparture = dateOfDeparture;
            return this;
        }


        public OrderBuilder setDateOfCreateOrder(Date dateOfCreateOrder) {
            order.dateOfCreateOrder = dateOfCreateOrder;
            return this;
        }

        public OrderBuilder setIdRoom(long idRoom) {
            order.idRoom = idRoom;
            return this;
        }

        public OrderBuilder setPaymentStatus(String paymentStatus) {
            order.paymentStatus = paymentStatus;
            return this;
        }

        public Order build(){
            return order;
        }

    }


}
