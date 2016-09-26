package av2.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("av2");
	
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	public void close(EntityManager em){
		em.close();
	}
	
}
