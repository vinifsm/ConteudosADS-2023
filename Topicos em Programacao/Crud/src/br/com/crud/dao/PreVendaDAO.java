package br.com.crud.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.crud.model.PreVenda;
import br.com.crud.util.dao.Dao;
import br.com.crud.util.dao.HibernateUtil;

public class PreVendaDAO extends Dao<PreVenda>{
	
	public PreVendaDAO() {
		super(PreVenda.class);
	}
	
	public List<PreVenda> buscaSimples(String texto) throws Exception {
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(PreVenda.class).setMaxResults(2000);
			criteria.add(Restrictions.eq("Cancelado", "N"));
			String te[] = texto.split("  ");
			for(int i = 0; i<te.length; i++) {
				criteria.add(Restrictions.like("codigo", "%" + te[i] + "&"));
			}
			criteria.addOrder(Order.asc("codigo"));
			return criteria.list();
		}catch (Exception e) {
			System.out.println(e);
			throw e;
			
		}
	}
	
	
	
	
	
	
	
}
