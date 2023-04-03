package br.com.crud.view.consulta;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.towel.swing.table.TableFilter;

import br.com.crud.dao.ClienteDAO;
import br.com.crud.dao.CrediarioDAO;
import br.com.crud.model.Crediario;
import br.com.crud.view.cadastro.TelaPlanoPagamento;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaConsultaCrediario extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbBusca = null;
	private JTextField txtBusca = null;
	private JScrollPane jScrollPane = null;
	private JTable table = null;
	private JButton btnFechar = null;
	private JLabel lbInformacao = null;
	private String texto = null;
	private String retorno = null;
	// objectablemodel � um modelo de jtable
	private ObjectTableModel<Crediario> model = null;// modelo da JTable
	// tablefilter � a classe que faz filtragens na jtable
	private TableFilter filtro = null;// auto filtro da Jtable
	private JButton btnCad = null;
	private int total = 0;
	private JLabel lbTotal = null;
	private JCheckBox checkAvancada = null;
	private JPanel painel;
	private JLabel lbPainel;
	private JButton btnCad_1;
	private JButton btnCad1;

	/**
	 * @wbp.parser.constructor
	 */
	public TelaConsultaCrediario(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaConsultaCrediario(JDialog owner) {// construtor do formulario
		super(owner);
		initialize();// metodo que inicializa os componentes
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// inicializa��o dos componentes
		this.setTitle("Consulta de Crediario");
		this.setSize(695, 425);
		// tamanho da tela
		this.setResizable(false);
		// n�o redimensionar
		this.setModal(true);
		// n�o mecher na de tras enquanto esta tiver aberta
		this.setContentPane(getJContentPane());
		// passando para o formulario o painel principal
		this.setLocationRelativeTo(getOwner());
		// iniciar no meio da tela o formulario
		// o metodo de baixo � para receber os dados da tela que chamou a
		// consulta
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowOpened(java.awt.event.WindowEvent e) {
				setTexto(getTexto());
				if (getTexto() != null) {
					// casso a tela que chamou a consulta tenha passado um
					// texto, faz a consulta com o texto
					txtBusca.setText(getTexto());
					carregaLista();// chama o metodo
				}
			}
		});
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), "F11");
		getRootPane().getActionMap().put("F11", new AbstractAction("F11") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				checkAvancada.setSelected(!checkAvancada.isSelected());
				carregaLista();
			}
		});
		getJContentPane().add(getPainel());
		getJContentPane().add(getBtnCad1());
	}

	@SuppressWarnings("unchecked")
	private void carregaLista() {
		String texto = "";
		CrediarioDAO dao = new CrediarioDAO();
		try {
			// faz a consulta
			List lista = dao.buscaSimples(texto);
			carregaTable(lista);// chama o metodo que carrega a tabela
		} catch (Exception busca) {
			busca.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	private void carregaTable(List lista) {
		total = lista.size();
		// necessario para criar o modelo da JTable
		AnnotationResolver resolver = new AnnotationResolver(Crediario.class);
		// instancia o modelo da tabela, com os campos que ir� aparecer separado
		// por virgula
		model = new ObjectTableModel<Crediario>(resolver, "codigo,descricao");
		model.setData(lista);
		// passa a lista para o modelo
		filtro = new TableFilter(table.getTableHeader(), model);
		// passa o modelo para o filtro
		table.setModel(filtro);
		// redimensionar a tabela
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(570);
		if (table.getRowCount() > 0) {
			table.changeSelection(0, 0, false, false);
			table.requestFocus();
			lbTotal.setText("Exibindo registro " + (table.getSelectedRow() + 1) + " de " + total + ".");
		}
	}

	private JPanel getJContentPane() {
		// metodo que constroi o painel
		if (jContentPane == null) {
			lbInformacao = new JLabel();
			
			lbInformacao.setBounds(new Rectangle(10, 356, 497, 16));
			lbInformacao.setText("Enter/Dois cliques para selecionar");
			lbBusca = new JLabel();
			lbBusca.setBounds(new Rectangle(10, 87, 40, 20));
			lbBusca.setText("Buscar:");
			lbTotal = new JLabel();
			lbTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lbTotal.setBounds(new Rectangle(422, 322, 260, 16));
			lbTotal.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lbBusca, null);
			jContentPane.add(lbTotal, null);
			jContentPane.add(getTxtBusca(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getBtnFechar(), null);
			jContentPane.add(lbInformacao, null);
			
		}
		return jContentPane;
	}

	private JTextField getTxtBusca() {
		if (txtBusca == null) {
			txtBusca = new JTextField();
			txtBusca.setToolTipText("Buscar Crediario");
			txtBusca.setBounds(new Rectangle(54, 87, 369, 20));
			txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				@SuppressWarnings("static-access")
				// se digitar enter chama o metodo que faz busca
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						carregaLista();
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						table.requestFocus();
					}
				}
			});
		}
		return txtBusca;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPane.setBounds(new Rectangle(10, 118, 672, 193));
			jScrollPane.setViewportView(getTable());
		}
		return jScrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyReleased(java.awt.event.KeyEvent e) {

					lbTotal.setText("Exibindo registro " + (table.getSelectedRow() + 1) + " de " + total + ".");

				}

				@Override
				@SuppressWarnings("static-access")
				// caso de enter chama o metodo que faz o retorno para tela de
				// tras
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						selecionar();
					} else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_B || e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_D 
							|| e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_F || e.getKeyCode() == KeyEvent.VK_G || e.getKeyCode() == KeyEvent.VK_H 
							|| e.getKeyCode() == KeyEvent.VK_I || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_K || e.getKeyCode() == KeyEvent.VK_L 
							|| e.getKeyCode() == KeyEvent.VK_M || e.getKeyCode() == KeyEvent.VK_N || e.getKeyCode() == KeyEvent.VK_O || e.getKeyCode() == KeyEvent.VK_P 
							|| e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_T 
							|| e.getKeyCode() == KeyEvent.VK_U || e.getKeyCode() == KeyEvent.VK_V || e.getKeyCode() == KeyEvent.VK_X || e.getKeyCode() == KeyEvent.VK_Y 
							|| e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_Z) {
						txtBusca.setText(e.getKeyChar() + "");
						txtBusca.requestFocus();
					}
				}
			});
			// caso de um clique duplo na linha da tabela, chama o metodo que
			// retorna
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					lbTotal.setText("Exibindo registro " + (table.getSelectedRow() + 1) + " de " + total + ".");
					if (e.getClickCount() == 2) {
						selecionar();
					}
				}
			});
		}
		return table;
	}

	private void selecionar() {
		// metodo que faz o retorno.. pega o codigo
		if (table.getRowCount() > 0) {
			if (filtro.isFiltering() || filtro.isSorting()) {
				String codigo = model.getValue(filtro.getModelRow(table.getSelectedRow())).getCodigo().toString();
				setRetorno(codigo);
				dispose();
			} else {
				String codigo = model.getValue(table.getSelectedRow()).getCodigo().toString();
				setRetorno(codigo);
				dispose();
			}
		}
	}

	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton();
			btnFechar.setToolTipText("Fechar tela");
			btnFechar.setIcon(new ImageIcon(TelaConsultaCrediario.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFechar.setBounds(new Rectangle(529, 349, 150, 35));
			// btnFechar.setIcon(new
			// ImageIcon(TelaConsultaCliente.class.getResource("/icones/alertacancela.png")));
			btnFechar.setText("Fechar");
			btnFechar.setMnemonic('F');
			btnFechar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return btnFechar;
	}

	

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getRetorno() {
		return retorno;
	}

	private JPanel getPainel() {
		if (painel == null) {
			painel = new JPanel();
			painel.setBackground(SystemColor.textHighlight);
			painel.setBounds(0, 0, 689, 66);
			painel.setLayout(null);
			painel.add(getLbPainel());
		}
		return painel;
	}
	private JLabel getLbPainel() {
		if (lbPainel == null) {
			lbPainel = new JLabel("Consulta de Crediario");
			lbPainel.setForeground(Color.WHITE);
			lbPainel.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbPainel.setBounds(188, 11, 339, 44);
		}
		return lbPainel;
	}
	private JButton getBtnCad1() {
		if (btnCad1 == null) {
			btnCad1 = new JButton();
			btnCad1.setToolTipText("Cadastrar Crediario");
			btnCad1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnCad1.setBounds(new Rectangle(558, 77, 120, 32));
			btnCad1.setText("Cadastro");
			btnCad1.setMnemonic('C');
			btnCad1.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					txtBusca.requestFocus();
					TelaPlanoPagamento tela = new TelaPlanoPagamento(TelaConsultaCrediario.this);
					tela.setNovo(true);
					tela.setVisible(true);
					if (tela.getCadastro() != null) {
						setRetorno(tela.getCadastro().getCodigo().toString());
						dispose();
					}
					carregaLista();
				}
			});
		}
		return btnCad1;
	}
	
}
