package org.oop.model.dao;


import org.oop.db.SQLParameters;

import java.util.ArrayList;

public interface DAOInterface<T> {

    public T find(int id);
    public ArrayList<T> findAll();
    public ArrayList<T> findBy(SQLParameters params);
    public void persist(T entity);
    public void update(T entity);
    public void remove(T entity);
}