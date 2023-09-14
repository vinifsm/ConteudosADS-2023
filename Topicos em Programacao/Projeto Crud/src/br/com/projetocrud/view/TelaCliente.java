package br.com.projetocrud.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;

import br.com.projetocrud.model.*;
import br.com.projetocrud.dao.*;
import javax.swing.DropMode;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtCep;
	private JTextField txtNumero;
	private JTextField txtRua;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtPesquisa;
	private JTable tabelaCliente;
	private JTextField txtCpf;
	private JTextField txtRg;
	private JComboBox comboBoxEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void listar() {
		
		ClienteDAO dao = new ClienteDAO();
		List<Endereco> lista = dao.listarCliente();
		DefaultTableModel dados = (DefaultTableModel) tabelaCliente.getModel();
		dados.setNumRows(0);
		
		for(Endereco c: lista) {
		dados.addRow(new Object[] {
			c.getCliente().getId(),
			c.getCliente().getNome(),
			c.getCliente().getCpf(),
			c.getCliente().getRg(),
			c.getId(),
			c.getCep(),
			c.getRua(),
			c.getNumero(),
			c.getComplemento(),
			c.getBairro(),
			c.getCidade(),
			c.getEstado()
		});
		}
	}
	
	public TelaCliente() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				listar();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 672, 69);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cadastro de Clientes");
		lblTitulo.setBounds(242, 21, 184, 37);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setForeground(Color.WHITE);
		panel.add(lblTitulo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 80, 672, 294);
		contentPane.add(tabbedPane);
		
		JPanel painelDados = new JPanel();
		painelDados.setBackground(Color.WHITE);
		tabbedPane.addTab("Dados Pessoais", null, painelDados, null);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		painelDados.setLayout(null);
		
		JPanel painelCliente = new JPanel();
		painelCliente.setBounds(0, 0, 667, 109);
		painelDados.add(painelCliente);
		painelCliente.setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(10, 17, 46, 14);
		painelCliente.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(66, 14, 86, 20);
		painelCliente.add(txtCodigo);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 45, 46, 14);
		painelCliente.add(lblCpf);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(186, 14, 46, 14);
		painelCliente.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(242, 11, 276, 20);
		painelCliente.add(txtNome);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(186, 45, 46, 14);
		painelCliente.add(lblRg);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(66, 45, 86, 20);
		painelCliente.add(txtCpf);
		txtCpf.setColumns(10);
		
		txtRg = new JTextField();
		txtRg.setBounds(242, 42, 86, 20);
		painelCliente.add(txtRg);
		txtRg.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
		        Endereco objE = new Endereco();
		        ClienteDAO dao = new ClienteDAO();

		        objE = dao.buscaPorNome(nome);

		        if (objE.getCliente().getNome() != null) {
			        txtCodigo.setText(String.valueOf(objE.getCliente().getId()));
			        txtNome.setText(objE.getCliente().getNome());
			        txtCpf.setText(objE.getCliente().getCpf());
			        txtRg.setText(objE.getCliente().getRg());
			        txtCep.setText(objE.getCep());
			        txtRua.setText(objE.getRua());
			        txtNumero.setText(objE.getNumero());
			        txtComplemento.setText(objE.getComplemento());
			        txtBairro.setText(objE.getBairro());
			        txtCidade.setText(objE.getCidade());
			        comboBoxEstado.setSelectedItem(objE.getEstado());
		        } else {
		            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
		        }
				
			}
		});
		btnBuscar.setBounds(545, 10, 89, 23);
		painelCliente.add(btnBuscar);
		
		JPanel painelEndereco = new JPanel();
		painelEndereco.setBounds(0, 120, 667, 135);
		painelDados.add(painelEndereco);
		painelEndereco.setLayout(null);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(10, 14, 46, 14);
		painelEndereco.add(lblCep);
		
		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            Endereco obj = new Endereco();
		            EnderecoDAO dao = new EnderecoDAO();
		            obj = dao.buscaCep(txtCep.getText());

		            txtRua.setText(obj.getRua());
		            txtBairro.setText(obj.getBairro());
		            txtCidade.setText(obj.getCidade());
		            comboBoxEstado.setSelectedItem(obj.getEstado());       

		        }
			}
		});
		txtCep.setColumns(10);
		txtCep.setBounds(66, 11, 86, 20);
		painelEndereco.add(txtCep);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setBounds(10, 42, 46, 14);
		painelEndereco.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(66, 39, 86, 20);
		painelEndereco.add(txtNumero);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(181, 14, 46, 14);
		painelEndereco.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(260, 11, 273, 20);
		painelEndereco.add(txtRua);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(181, 42, 69, 14);
		painelEndereco.add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(260, 39, 273, 20);
		painelEndereco.add(txtComplemento);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 72, 46, 14);
		painelEndereco.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(66, 69, 228, 20);
		painelEndereco.add(txtBairro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(315, 72, 46, 14);
		painelEndereco.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(371, 69, 286, 20);
		painelEndereco.add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 104, 46, 14);
		painelEndereco.add(lblEstado);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"SP", "RJ", "MG"}));
		comboBoxEstado.setSelectedIndex(0);
		comboBoxEstado.setBounds(66, 100, 46, 22);
		painelEndereco.add(comboBoxEstado);
		
		JButton btnAdicionar = new JButton("Adicionar Endere\u00E7o");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente obj = new Cliente();
				Endereco objE = new Endereco();
				
				obj.setId(Integer.parseInt(txtCodigo.getText()));
				obj.setNome(txtNome.getText());
				obj.setCpf(txtCpf.getText());
				obj.setRg(txtRg.getText());
				objE.setCep(txtCep.getText());
				objE.setRua(txtRua.getText());
				objE.setNumero(txtNumero.getText());
				objE.setComplemento(txtComplemento.getText());
				objE.setBairro(txtBairro.getText());
				objE.setCidade(txtCidade.getText());
				objE.setEstado(comboBoxEstado.getSelectedItem().toString());
				objE.setCliente(obj);

				EnderecoDAO dao = new EnderecoDAO();
				
				dao.cadastrarEndereco(objE);
				new Utilitarios().LimpaTela(painelEndereco);
			}
		});
		btnAdicionar.setBounds(484, 104, 159, 23);
		painelEndereco.add(btnAdicionar);
		
		JPanel painelConsulta = new JPanel();
		painelConsulta.setBackground(Color.WHITE);
		tabbedPane.addTab("Consulta de Clientes", null, painelConsulta, null);
		painelConsulta.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 11, 667, 195);
		painelConsulta.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPesquisa = new JLabel("Nome:");
		lblPesquisa.setBounds(10, 14, 37, 14);
		panel_1.add(lblPesquisa);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(52, 11, 86, 20);
		txtPesquisa.setColumns(10);
		panel_1.add(txtPesquisa);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String nome = "%" + txtPesquisa.getText() + "%";
				
				ClienteDAO dao = new ClienteDAO();
				List<Endereco> lista = dao.buscarClientePorNome(nome);
				DefaultTableModel dados = (DefaultTableModel) tabelaCliente.getModel();
				dados.setNumRows(0);
				
				for(Endereco c: lista) {
				dados.addRow(new Object[] {
					c.getCliente().getId(),
					c.getCliente().getNome(),
					c.getCliente().getCpf(),
					c.getCliente().getRg(),
					c.getId(),
					c.getCep(),
					c.getRua(),
					c.getNumero(),
					c.getComplemento(),
					c.getBairro(),
					c.getCidade(),
					c.getEstado()
				});
				}
			}
		});
		btnPesquisar.setBounds(168, 10, 106, 23);
		panel_1.add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 647, 140);
		panel_1.add(scrollPane);
		
		tabelaCliente = new JTable();
		tabelaCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        tabbedPane.setSelectedIndex(0);

		        txtCodigo.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0).toString());
		        txtNome.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 1).toString());
		        txtCpf.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 2).toString());
		        txtRg.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 3).toString());
		        txtCep.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 5).toString());
		        txtRua.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 6).toString());
		        txtNumero.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 7).toString());
		        txtComplemento.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 8).toString());
		        txtBairro.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 9).toString());
		        txtCidade.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 10).toString());
		        comboBoxEstado.setSelectedItem(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 11).toString());
			}
		});
		tabelaCliente.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id Cliente", "Nome", "CPF", "RG", "Id Endere\u00E7o", "CEP", "Rua", "N\u00FAmero", "Complemento", "Bairro", "Cidade", "Estado"
			}
		));
		tabelaCliente.getColumnModel().getColumn(0).setPreferredWidth(56);
		tabelaCliente.getColumnModel().getColumn(4).setPreferredWidth(68);
		tabelaCliente.getColumnModel().getColumn(5).setPreferredWidth(62);
		tabelaCliente.getColumnModel().getColumn(6).setPreferredWidth(100);
		tabelaCliente.getColumnModel().getColumn(7).setPreferredWidth(53);
		tabelaCliente.getColumnModel().getColumn(9).setPreferredWidth(60);
		tabelaCliente.getColumnModel().getColumn(10).setPreferredWidth(53);
		tabelaCliente.getColumnModel().getColumn(11).setPreferredWidth(57);
		scrollPane.setViewportView(tabelaCliente);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Utilitarios().LimpaTela(painelCliente);
				new Utilitarios().LimpaTela(painelEndereco);
			}
		});
		btnNovo.setBounds(55, 388, 89, 23);
		contentPane.add(btnNovo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente obj = new Cliente();
					Endereco objE = new Endereco();
					
					obj.setId(Integer.parseInt(txtCodigo.getText()));
					obj.setNome(txtNome.getText());
					obj.setCpf(txtCpf.getText());
					obj.setRg(txtRg.getText());
					objE.setCep(txtCep.getText());
					objE.setRua(txtRua.getText());
					objE.setNumero(txtNumero.getText());
					objE.setComplemento(txtComplemento.getText());
					objE.setBairro(txtBairro.getText());
					objE.setCidade(txtCidade.getText());
					objE.setEstado(comboBoxEstado.getSelectedItem().toString());
					objE.setCliente(obj);

					ClienteDAO dao = new ClienteDAO();
					
					dao.cadastrarCliente(obj, objE);
					new Utilitarios().LimpaTela(painelCliente);
					new Utilitarios().LimpaTela(painelEndereco);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro: "+e2);
				}
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvar.setBounds(172, 388, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					Cliente obj = new Cliente();
					Endereco objE = new Endereco();
					
					obj.setId(Integer.parseInt(txtCodigo.getText()));
					obj.setNome(txtNome.getText());
					obj.setCpf(txtCpf.getText());
					obj.setRg(txtRg.getText());
					objE.setCep(txtCep.getText());
					objE.setRua(txtRua.getText());
					objE.setNumero(txtNumero.getText());
					objE.setComplemento(txtComplemento.getText());
					objE.setBairro(txtBairro.getText());
					objE.setCidade(txtCidade.getText());
					objE.setEstado(comboBoxEstado.getSelectedItem().toString());
					objE.setCliente(obj);

					ClienteDAO dao = new ClienteDAO();
					
					dao.alterarCliente(obj, objE);
					new Utilitarios().LimpaTela(painelCliente);
					new Utilitarios().LimpaTela(painelEndereco);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro: "+e2);
				}
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setBounds(290, 388, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente obj = new Cliente();
					Endereco objE = new Endereco();
					
					obj.setId(Integer.parseInt(txtCodigo.getText()));
					objE.setCliente(obj);

					ClienteDAO dao = new ClienteDAO();
					
					dao.excluirCliente(obj, objE);
					new Utilitarios().LimpaTela(painelCliente);
					new Utilitarios().LimpaTela(painelEndereco);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro: "+e2);
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBounds(407, 388, 89, 23);
		contentPane.add(btnExcluir);
	}
}
