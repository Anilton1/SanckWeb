package br.com.rexpress.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory factory = null;
	private static EntityManager entityManager = null;

	public static EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("REXPRESS");
		}
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

}
