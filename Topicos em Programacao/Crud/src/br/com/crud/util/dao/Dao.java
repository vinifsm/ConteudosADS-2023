package br.com.crud.util.dao;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public abstract class Dao<E> {

	private Class clazz;

	public Dao(Class clazz){
		this.clazz = clazz;
	}
	
	public void cadastrar(E objeto) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(objeto);
			transaction.commit();
			session.flush();
		} catch (Exception exe) {
			System.out.println(exe);
			if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
				transaction.rollback();
			}
			throw exe;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	public void alterar(E objeto) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.update(objeto);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
				transaction.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	public void merge(E objeto) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.merge(objeto);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
				transaction.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void excluir(E objeto, boolean tru) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(objeto);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
				transaction.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

//	public void excluir(Integer i) throws Exception {
//		excluir(carregarPorID(i));
//	}

	public E carregarPorID(Integer id) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			E result = (E) session.get(clazz, id);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public E carregarPorID2(String id) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			E result = (E) session.get(clazz, id);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public E selecionarUltimoCancel() throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(clazz).setMaxResults(2);
			criteria.add(Restrictions.eq("cancelado", "N"));
			return (E)criteria.list().get(0);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public E selecionarUltimo() throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(clazz).setMaxResults(2);
			return (E)criteria.list().get(0);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public List<E> selecionarTodos() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(clazz);
			return criteria.list();
		}catch(Exception e){
			throw e;
		}finally{
			if(session != null && session.isOpen()){
				session.close(); 
			}
		      
		}
	}
	

	public List<E> selecionarTodosSemCancel() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.flush();
			Criteria criteria = session.createCriteria(clazz);
			criteria.add(org.hibernate.criterion.Restrictions.eq("cancelado", "N"));
			return criteria.list();
		}catch(Exception e){
			throw e;
		}finally{
			if(session != null && session.isOpen()){
				session.close(); 
			}
		}
	}
		
	public static int executeSql(String sqll){
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			Query sql = session.createSQLQuery(sqll);
			int i = sql.executeUpdate();
			transaction.commit();

			return i;
		} catch (Exception e) {
			System.out.println(e);
			if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
				transaction.rollback();
			}
			JOptionPane.showMessageDialog(null, "Erro ao Executar o comando");
			return 0;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
}
