package com.train.tests;


import com.train.hotel.model.dao.UserDao;
import com.train.hotel.model.entity.User;
import com.train.hotel.utility.DataUtility;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

    @Test
    public void AddClientTest(){
        User sUser = new User.UserBuilder()
                .setFirstName("Pok")
                .setLastName("Jok")
                .setLogin("yoy@gmail.com")
                .setPassword("PokJok123")
                .setDob(DataUtility.getDate("12/08/2003"))
                .setMobileNo("0864123152")
                .build();

        try{
            UserDao userDao = new UserDao();
            long i = userDao.addUser(sUser);
            User user = userDao.UserLogin(sUser.getLogin(), sUser.getPassword());


            Assert.assertNotNull(sUser);
            Assert.assertTrue(20 < user.getId()); // test correct id
            Assert.assertEquals(22,userDao.list().size()); // test all row in the table

            //test the new inserted row
            Assert.assertEquals(23, user.getId());
            Assert.assertEquals(sUser.getFirstName(), user.getFirstName());
            Assert.assertEquals(sUser.getLastName(), user.getLastName());
            Assert.assertEquals(sUser.getDob(), user.getDob());
            Assert.assertEquals(sUser.getMobileNo(), user.getMobileNo());
            Assert.assertEquals(sUser.getLogin(), user.getLogin());
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void bEditClientTest() {
        String newFirstName = "bob";
        String newLastName = "Jenkins";
        String newLogin = "bob@gg.io";
        String newPassword = "Cock";
        String newDob = "20/12/2002";
        String newNom = "0955596551";

        User sUser = new User.UserBuilder()
                .setId(23)
                .setFirstName(newFirstName)
                .setLastName(newLastName)
                .setLogin(newLogin)
                .setPassword(newPassword)
                .setDob(DataUtility.getDate(newDob))
                .setMobileNo(newNom)
                .build();


        try {
            UserDao userDao = new UserDao();
            userDao.UpdateUser(sUser);
            User user = userDao.FindByPk(23);

            Assert.assertEquals(newFirstName, user.getFirstName());
            Assert.assertEquals(newLastName, user.getLastName());
            Assert.assertEquals(newLogin, user.getLogin());
            Assert.assertEquals(newPassword, user.getPassword());
            Assert.assertEquals(newNom, user.getMobileNo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void cDeleteClientTest() {


        try {
            UserDao userDao = new UserDao();
            User sUser = userDao.delete(23);
            User user = userDao.FindByPk(23);

            String notNyl = "" + sUser;
            System.out.println(notNyl);
            Assert.assertNull(sUser);
            Assert.assertNull(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
