package by.ermakov.lab8.service;

import by.ermakov.lab8.model.User;
import by.ermakov.lab8.model.Person;
import by.ermakov.lab8.model.Plane;
import by.ermakov.lab8.model.Ticket;

import java.util.List;

public interface Dao {
    public List<Person> getPersons();
    public void insertPerson(Person person);
    public int getLastUser();
    public Person getPerson(int id);
    public void updatePlanesTickets(int id, boolean plus);
    public List<Plane> getPlanes();
    public Plane getPlane(int id);
    public List<Ticket> getTickets();
    public boolean insertTicket(int idPlane, int idPerson);
    public void deleteBook(int id);
    public User isValidUser(final String login, final byte[] password);
    public boolean insertUser(User user);
    public void insertPlane(Plane plane);
    public int getPersonByUser(int id);
}
