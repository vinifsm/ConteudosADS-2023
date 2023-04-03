package br.com.crud.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.crud.model.ContaReceber;
import br.com.crud.util.dao.Dao;
import br.com.crud.util.dao.HibernateUtil;

public class ContaReceberDAO extends Dao<ContaReceber> {

	public ContaReceberDAO() {
		super(ContaReceber.class);
	}

	public List<ContaReceber> buscaSimples(String texto) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(ContaReceber.class).setMaxResults(100);
			criteria.add(Restrictions.eq("cancelado", "N"));

			String te[] = texto.split(" ");
			for (int i = 0; i < te.length; i++) {
				criteria.add(Restrictions.like("nomeCartao", "%" + te[i] + "%"));
			}

			criteria.addOrder(Order.asc("nomeCartao"));

			return criteria.list();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}
}
