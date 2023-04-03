package br.com.crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.crud.model.ItemVenda;
import br.com.crud.model.Venda;
import br.com.crud.util.dao.Dao;
import br.com.crud.util.dao.HibernateUtil;

public class VendaDAO extends Dao<Venda> {
	public VendaDAO() {
		super(Venda.class);
	}

	public List<Venda> buscaSimples(String texto) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Venda.class).setMaxResults(2000);
			criteria.add(Restrictions.eq("cancelado", "N"));

			String te[] = texto.split("  ");
			for (int i = 0; i < te.length; i++) {
				criteria.add(Restrictions.like("produto", "%" + te[i] + "%"));
				
			}
			return criteria.list();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

}
