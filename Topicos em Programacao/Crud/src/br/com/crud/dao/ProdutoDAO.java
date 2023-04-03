package br.com.crud.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.crud.model.Cliente;
import br.com.crud.model.Produto;
import br.com.crud.util.dao.Dao;
import br.com.crud.util.dao.HibernateUtil;

public class ProdutoDAO extends Dao<Produto> {
	public ProdutoDAO() {
		super(Produto.class);
	}

	public List<Produto> buscaSimples(String texto) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Produto.class).setMaxResults(2000);
			criteria.add(Restrictions.eq("cancelado", "N"));

			String te[] = texto.split(" ");
			for (int i = 0; i < te.length; i++) {
				criteria.add(Restrictions.like("nomeProduto", "%" + te[i] + "%"));
			}

			criteria.addOrder(Order.asc("nomeProduto"));

			return criteria.list();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

}
