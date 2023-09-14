package br.com.projetocrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.projetocrud.jdbc.ConnectionFactory;
import br.com.projetocrud.model.Cliente;
import br.com.projetocrud.model.Endereco;

public class ClienteDAO {

	private Connection con;
	
	public ClienteDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarCliente(Cliente obj, Endereco objE) {
	
		try {
			
			String sql = "insert into tb_clientes (id,nome,cpf,rg)"
					+ " values (?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, obj.getId());
			stmt.setString(2, obj.getNome());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getRg());
			
			stmt.execute();
			stmt.close();
			
			EnderecoDAO daoE = new EnderecoDAO();
			
			daoE.cadastrarEndereco(objE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
		}
		
	}
	
	public void alterarCliente(Cliente obj, Endereco objE) {
		
		try {
			
			String sql = "update tb_clientes set nome=?,cpf=?,rg=? where id=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCpf());
			stmt.setString(3, obj.getRg());
			stmt.setInt(4, obj.getId());
			
			stmt.execute();
			stmt.close();
			
			EnderecoDAO daoE = new EnderecoDAO();
			
			daoE.alterarEndereco(objE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
		}
		
	}
	
	public void excluirCliente(Cliente obj, Endereco objE) {
        try {
            String sql = "delete from tb_clientes where id = ?";

			EnderecoDAO daoE = new EnderecoDAO();
            
			daoE.excluirEndereco(objE);
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
		
	}
	
	public List<Endereco> listarCliente() {
		try {
			
            List<Endereco> lista = new ArrayList<>();
			
			String sql = "select * from tb_clientes "
					+ "inner join tb_enderecos on tb_clientes.id = tb_enderecos.cliente_id;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente obj = new Cliente();
				Endereco objE = new Endereco();
				
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCpf(rs.getString("cpf"));
				obj.setRg(rs.getString("rg"));
				objE.setId(rs.getInt("tb_enderecos.id"));
				objE.setCep(rs.getString("cep"));
				objE.setRua(rs.getString("rua"));
				objE.setNumero(rs.getString("numero"));
				objE.setComplemento(rs.getString("complemento"));
				objE.setBairro(rs.getString("Bairro"));
				objE.setCidade(rs.getString("cidade"));
				objE.setEstado(rs.getString("estado"));
				objE.setCliente(obj);
				
				lista.add(objE);
			}
			
			return lista;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
			return null;
		}
	}
	
	public List<Endereco> buscarClientePorNome(String nome) {
		try {
			
            List<Endereco> lista = new ArrayList<>();
			
			String sql = "select * from tb_clientes "
					+ "inner join tb_enderecos on tb_clientes.id = tb_enderecos.cliente_id where nome like ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente obj = new Cliente();
				Endereco objE = new Endereco();
				
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCpf(rs.getString("cpf"));
				obj.setRg(rs.getString("rg"));
				objE.setId(rs.getInt("tb_enderecos.id"));
				objE.setCep(rs.getString("cep"));
				objE.setRua(rs.getString("rua"));
				objE.setNumero(rs.getString("numero"));
				objE.setComplemento(rs.getString("complemento"));
				objE.setBairro(rs.getString("Bairro"));
				objE.setCidade(rs.getString("cidade"));
				objE.setEstado(rs.getString("estado"));
				objE.setCliente(obj);
				
				lista.add(objE);
			}
			
			return lista;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
			return null;
		}
	}
	
	public Endereco buscaPorNome(String nome) {
        try {
            String sql = "select * from tb_clientes "
            		+ "inner join tb_enderecos on tb_clientes.id = tb_enderecos.cliente_id where nome = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();
			Endereco objE = new Endereco();

            if (rs.next()) {
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCpf(rs.getString("cpf"));
				obj.setRg(rs.getString("rg"));
				objE.setId(rs.getInt("tb_enderecos.id"));
				objE.setCep(rs.getString("cep"));
				objE.setRua(rs.getString("rua"));
				objE.setNumero(rs.getString("numero"));
				objE.setComplemento(rs.getString("complemento"));
				objE.setBairro(rs.getString("Bairro"));
				objE.setCidade(rs.getString("cidade"));
				objE.setEstado(rs.getString("estado"));
				objE.setCliente(obj);
            }

            return objE;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }
}
