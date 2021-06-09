package com.codeforce.shop.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void getUsers(){
        String queryStr="select * from users";
        List<Object> list = new ArrayList<>();
        try{
            Query query =entityManager.createNativeQuery(queryStr);
             list = query.getResultList();
            System.out.println("result");
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        System.out.println("Users");
    }

}
