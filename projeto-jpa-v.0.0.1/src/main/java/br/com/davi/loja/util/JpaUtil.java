package br.com.davi.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("loja");

    public static EntityManager getEntityManager(){
        return EMF.createEntityManager();
    }
}
