package br.com.crud.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.crud.model.Vendedor;
import br.com.crud.util.dao.Dao;
import br.com.crud.util.dao.HibernateUtil;


public class VendedorDAO extends Dao<Vendedor> {
	
	public VendedorDAO() {
		super(Vendedor.class);
	}

	
	public List<Vendedor> buscaSimples(String texto) throws Exception {	
		Session session = null;
		try {			
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Vendedor.class).setMaxResults(2000);			
			criteria.add(Restrictions.eq("cancelado", "N"));
			
			String te[] = texto.split(" ");
			for (int i = 0; i < te.length; i++) {
				criteria.add(Restrictions.like("razaoSocial", "%" + te[i] + "%"));
			}
			
			criteria.addOrder(Order.asc("razaoSocial"));
			
			return criteria.list();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

}
