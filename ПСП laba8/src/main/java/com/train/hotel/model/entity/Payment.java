package com.train.hotel.model.entity;

import java.util.Date;

public class Payment {
    private String image;
    private long idOrder;
    private long idRoom;
    private String classRoom;
    private long guests;
    private Date dateOfSettlement;
    private Date dateOfDeparture;
    private long price;


    public String getImage() {
        return image;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public String getClassRoom() { return classRoom; }

    public long getGuests() {
        return guests;
    }

    public Date getDateOfSettlement() {
        return dateOfSettlement;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public long getPrice() {
        return price;
    }


    public static class PaymentBuilder{
        private Payment payment;

        public PaymentBuilder() {
            payment = new Payment();
        }
        public PaymentBuilder setImage(String image) {
            payment.image = image;
            return this;
        }

        public PaymentBuilder setIdOrder(long idOrder) {
            payment.idOrder = idOrder;
            return this;
        }

        public PaymentBuilder setIdRoom(long idRoom) {
            payment.idRoom = idRoom;
            return this;
        }

        public PaymentBuilder setClassRoom(String classRoom) {
            payment.classRoom = classRoom;
            return this;
        }


        public PaymentBuilder setGuests(long guests) {
            payment.guests = guests;
            return this;
        }

        public PaymentBuilder setDateOfSettlement(Date dateOfSettlement) {
            payment.dateOfSettlement = dateOfSettlement;
            return this;
        }
        public PaymentBuilder setDateOfDeparture(Date dateOfDeparture) {
            payment.dateOfDeparture = dateOfDeparture;
            return this;
        }

        public PaymentBuilder setPrice(long price) {
            payment.price = price;
            return this;
        }

        public Payment build(){
            return payment;
        }

    }

}
