package br.com.crud.util.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import br.com.crud.model.Vendedor;
import br.com.crud.model.BandeiraCartao;
import br.com.crud.model.Cartao;
import br.com.crud.model.Cliente;
import br.com.crud.model.ContaReceber;
import br.com.crud.model.Crediario;
import br.com.crud.model.ItemVenda;
import br.com.crud.model.PreVenda;
import br.com.crud.model.Produto;
import br.com.crud.model.Venda;

public class HibernateUtil {

	public static SessionFactory sessionFactory = null;

	public synchronized static Session getSession() throws Exception {
		try {
			Session session =  getSessionFactory().openSession();
			return session;
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory = null;
			Session s = getSessionFactory().openSession();
			return s;
		}
	}

	public synchronized static SessionFactory getSessionFactory()
			throws Exception {
		if (sessionFactory == null) {
			sessionFactory = criarSessionFactory();
		}
		return sessionFactory;
	}

	public static SessionFactory criarSessionFactory() throws Exception {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		
		try {
			cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3307/crud");
			cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			cfg.setProperty("hibernate.connection.username", "root");
			cfg.setProperty("hibernate.connection.password", "vacavaca");
			cfg.setProperty("hibernate.hbm2ddl.auto", "update");
		} catch (Exception e) {
			e.printStackTrace();
		}
		cfg.setProperty("hibernate.show_sql", "true");
		cfg.setProperty("hibernate.connection.autoReconnect", "true");

		cfg.addAnnotatedClass(Cliente.class);
		cfg.addAnnotatedClass(Produto.class);
		cfg.addAnnotatedClass(Vendedor.class);
		cfg.addAnnotatedClass(Venda.class);
		cfg.addAnnotatedClass(ItemVenda.class);
		cfg.addAnnotatedClass(PreVenda.class);
		cfg.addAnnotatedClass(BandeiraCartao.class);
		cfg.addAnnotatedClass(ContaReceber.class);
		cfg.addAnnotatedClass(Crediario.class);
		cfg.addAnnotatedClass(Cartao.class);
		
		return cfg.buildSessionFactory();
	}
	
	
	
	
	
}