package br.com.crud.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.crud.model.BandeiraCartao;
import br.com.crud.util.dao.Dao;
import br.com.crud.util.dao.HibernateUtil;

public class BandeiraCartaoDAO extends Dao<BandeiraCartao> {

	public BandeiraCartaoDAO() {
		super(BandeiraCartao.class);
	}

	public List<BandeiraCartao> buscaSimples(String texto) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(BandeiraCartao.class).setMaxResults(100);
			criteria.add(Restrictions.eq("cancelado", "N"));

			String te[] = texto.split(" ");
			for (int i = 0; i < te.length; i++) {
				criteria.add(Restrictions.like("descricao", "%" + te[i] + "%"));
			}

			criteria.addOrder(Order.asc("descricao"));

			return criteria.list();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}
}
