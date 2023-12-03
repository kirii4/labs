package by.ermakov.lab8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String phone;
    private String email;
    private int userID;


    public Person(Person person) {
        name = person.name;
        phone = person.phone;
        email = person.email;
    }
}



