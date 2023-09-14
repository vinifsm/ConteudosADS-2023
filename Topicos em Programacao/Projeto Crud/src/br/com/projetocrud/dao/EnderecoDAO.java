	package br.com.projetocrud.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.List;
	import java.util.ArrayList;

	import javax.swing.JOptionPane;

	import br.com.projetocrud.jdbc.ConnectionFactory;
	import br.com.projetocrud.model.WebServiceCep;
	import br.com.projetocrud.model.Cliente;
	import br.com.projetocrud.model.Endereco;


	public class EnderecoDAO {

		private Connection con;
		
		public EnderecoDAO() {
			this.con = new ConnectionFactory().getConnection();
		}
		
		public void cadastrarEndereco(Endereco obj) {
		
			try {
				
				String sql = "insert into tb_enderecos (cep,rua,numero,complemento,bairro,cidade,estado,cliente_id)"
						+ " values (?,?,?,?,?,?,?,?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, obj.getCep());
				stmt.setString(2, obj.getRua());
				stmt.setString(3, obj.getNumero());
				stmt.setString(4, obj.getComplemento());
				stmt.setString(5, obj.getBairro());
				stmt.setString(6, obj.getCidade());
				stmt.setString(7, obj.getEstado());
				stmt.setInt(8, obj.getCliente().getId());
				
				stmt.execute();
				stmt.close();
				JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: "+e);
			}
			
		}
		
		public void alterarEndereco(Endereco obj) {
			
			try {
				
				String sql = "update tb_enderecos set cep=?,rua=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where cliente_id = ?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, obj.getCep());
				stmt.setString(2, obj.getRua());
				stmt.setString(3, obj.getNumero());
				stmt.setString(4, obj.getComplemento());
				stmt.setString(5, obj.getBairro());
				stmt.setString(6, obj.getCidade());
				stmt.setString(7, obj.getEstado());
				stmt.setInt(8, obj.getCliente().getId());
				
				stmt.execute();
				stmt.close();

				JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: "+e);
			}
			
			
		}
		
		public void excluirEndereco(Endereco obj) {
	        try {
	            String sql = "delete from tb_enderecos where cliente_id = ?";

	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, obj.getCliente().getId());

	            stmt.execute();
	            stmt.close();

	            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

	        } catch (Exception erro) {
	            JOptionPane.showMessageDialog(null, "Erro: " + erro);

	        }
			
		}
		
		  public Endereco buscaCep(String cep) {
		       
		        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
		       

		        Endereco obj = new Endereco();

		        if (webServiceCep.wasSuccessful()) {
		        	obj.setRua(webServiceCep.getLogradouro());
		            obj.setCidade(webServiceCep.getCidade());
		            obj.setBairro(webServiceCep.getBairro());
		            obj.setEstado(webServiceCep.getUf());
		            return obj;
		        } else {
		            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
		            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
		            return null;
		        }

		    }
	
		
	}

