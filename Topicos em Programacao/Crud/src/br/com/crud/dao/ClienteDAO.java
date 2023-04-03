package br.com.crud.dao;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.crud.model.Cliente;
import br.com.crud.model.WebServiceCep;
import br.com.crud.util.dao.Dao;
import br.com.crud.util.dao.HibernateUtil;


public class ClienteDAO extends Dao<Cliente> {
	
	public ClienteDAO() {
		super(Cliente.class);
	}

	
	public List<Cliente> buscaSimples(String texto) throws Exception {	
		Session session = null;
		try {			
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Cliente.class).setMaxResults(2000);			
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
	
	
	  public Cliente buscaCep(String cep) {
	       
	        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
	       

	        Cliente obj = new Cliente();

	        if (webServiceCep.wasSuccessful()) {
	            obj.setEndereço(webServiceCep.getLogradouroFull());
	            obj.setCidade(webServiceCep.getCidade());
	            obj.setBairro(webServiceCep.getBairro());
	            obj.setUf(webServiceCep.getUf());
	            return obj;
	        } else {
	            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
	            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
	            return null;
	        }

	    }

}
