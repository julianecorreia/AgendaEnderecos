package br.unipar.devbackend.agendaenderecos.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private EntityManagerUtil() {}

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence
                    .createEntityManagerFactory(
                            "agendaEnderecosPU");
            System.out.println("Conexão com o banco de " +
                    "dados estabelecida.");
        }
        return emf;
    }


}
