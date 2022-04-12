package com.example.crud.service;

import com.example.crud.model.CreditCard;
import com.example.crud.model.Person;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("creditCardService")
@Transactional
public class CreditCardService {

    protected static Logger logger = Logger.getLogger("service");

    private SessionFactory sessionFactory;

    /**
     * Retrieves all credit cards
     */
    public List<CreditCard> getAll(Integer personId) {
        logger.debug("Retrieving all credit cards");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM Person as p LEFT JOIN FETCH  p.creditCards WHERE p.id="+personId);

        Person person = (Person) query.uniqueResult();

        // Retrieve all
        return  new ArrayList<CreditCard>(person.getCreditCards());
    }

    /**
     * Retrieves all credit cards
     */
    public List<CreditCard> getAll() {
        logger.debug("Retrieving all credit cards");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM  CreditCard");

        // Retrieve all
        return  query.list();
    }

    /**
     * Retrieves a single credit card
     */
    public CreditCard get( Integer id ) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing credit card
        CreditCard creditCard = (CreditCard) session.get(CreditCard.class, id);

        // Persists to db
        return creditCard;
    }

    /**
     * Adds a new credit card
     */
    public void add(Integer personId, CreditCard creditCard) {
        logger.debug("Adding new credit card");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Persists to db
        session.save(creditCard);

        // Add to person as well
        // Retrieve existing person via id
        Person existingPerson = (Person) session.get(Person.class, personId);

        // Assign updated values to this person
        existingPerson.getCreditCards().add(creditCard);

        // Save updates
        session.save(existingPerson);
    }

    /**
     * Deletes an existing credit card
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing credit card");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Delete reference to foreign key credit card first
        // We need a SQL query instead of HQL query here to access the third table
        Query query = session.createSQLQuery("DELETE FROM person_credit_cards " +
                "WHERE credit_cards_id="+id);

        query.executeUpdate();

        // Retrieve existing credit card
        CreditCard creditCard = (CreditCard) session.get(CreditCard.class, id);

        // Delete
        session.delete(creditCard);
    }

    /**
     * Edits an existing credit card
     */
    public void edit(CreditCard creditCard) {
        logger.debug("Editing existing creditCard");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing credit card via id
        CreditCard existingCreditCard = (CreditCard) session.get(CreditCard.class, creditCard.getId());

        // Assign updated values to this credit card
        existingCreditCard.setNumber(creditCard.getNumber());
        existingCreditCard.setType(creditCard.getType());

        // Save updates
        session.save(existingCreditCard);
    }
}
