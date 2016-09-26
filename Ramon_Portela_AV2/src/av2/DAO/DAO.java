package av2.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import av2.modelo.Fornecedor;
import av2.modelo.Processo;

public class DAO <T>{

	private final Class<T> classe;	
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adicionar(T t) {

		EntityManager em = new HibernateJPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.persist(t);
		
		em.getTransaction().commit();
		em.close();
	}

	public void adicionar(T t, String cnpj) {

		EntityManager em = new HibernateJPAUtil().getEntityManager();
		
		Fornecedor f = new Fornecedor();
		
		try{
            for(Object forn : em.createQuery("SELECT f FROM Fornecedor f").getResultList()){
                f = (Fornecedor)forn;                
                if(f.getCnpj().equals(cnpj)){
                	((Processo)t).setFornecedor(f);
                }                
            }
		}
		finally{
			
		}  	
		
		em.getTransaction().begin();

		em.persist(t);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void remover(T t) {
		EntityManager em = new HibernateJPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public void atualizar(T t) {
		EntityManager em = new HibernateJPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}
	
	public void atualizar(T t, String cnpj) {
		EntityManager em = new HibernateJPAUtil().getEntityManager();
		
		Fornecedor f = new Fornecedor();
		
		try{
            for(Object forn : em.createQuery("SELECT f FROM Fornecedor f").getResultList()){
                f = (Fornecedor)forn;                
                if(f.getCnpj().equals(cnpj)){
                	((Processo)t).setFornecedor(f);
                }                
            }
		}
		finally{
			
		}  	
		
		
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}

	public List<T> listar() {
		EntityManager em = new HibernateJPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

	public T buscarId(Integer id) {
		EntityManager em = new HibernateJPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}
	
	public T buscarCnpj(String cnpj) {
		EntityManager em = new HibernateJPAUtil().getEntityManager();	
		Fornecedor f = new Fornecedor();
		
		try{
            for(Object forn : em.createQuery("SELECT f FROM Fornecedor f").getResultList()){
                f = (Fornecedor)forn;                
                if(f.getCnpj().equals(cnpj)){
                	return (T) f;
                }                
            }
		}
		finally{
			
			em.close();
		}  	
		return null;
	}

}
