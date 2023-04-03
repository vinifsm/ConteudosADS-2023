package br.com.crud.view.cadastro;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import br.com.crud.dao.ClienteDAO;
import br.com.crud.dao.ProdutoDAO;
import br.com.crud.model.Cliente;
import br.com.crud.model.Produto;
import br.com.crud.view.consulta.TelaConsultaCliente;
import br.com.crud.view.consulta.TelaConsultaProduto;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;

//classe extend de JDialog que � uma classe com padr�es de tela visual
public class TelaProduto extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbCodigo = null;
	private JTextField txtCodigo = null;
	private JLabel lbDescricao = null;
	private JLabel lbBusca = null;
	private JTextField txtBusca = null;
	private JButton btnNovo = null;
	private JButton btnAlterar = null;
	private JButton btnExcluir = null;
	private JButton btnFechar = null;
	private JButton btnSalvar = null;
	private JButton btnCancelar = null;
	private boolean editar = false;
	private JButton btnBuscar = null;
	private JLabel lbStatusObjeto = null;
	private Boolean novo = false;
	private Produto cadastro = null;
	private JLabel lbNomeProduto;
	private JTextField txtNomeProduto;
	private JLabel lbQtdEstoqueAtual;
	private JTextField txtQtdEstoqueAtual;
	private JLabel lbValor;
	private JTextField txtValor;
	private JTextField txtQtdEstoqueAtual1;
	private JTextField txtValor1;
	private JPanel painel;
	private JLabel lbProdutos;

	/**
	 * @wbp.parser.constructor
	 */
	public TelaProduto(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaProduto(JDialog owner) {
		// Construtor da classe
		super(owner);
		// Construtor da classe que estamos extendendo... no caso JDialog
		initialize();
		// chama o metodo que inicializa os componentes
	}

	public TelaProduto() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// metodo que inicializa os componentes
		this.setSize(647, 358);// tamanho da tela
		this.setTitle("Produto");
		this.setContentPane(getJContentPane());
		// setando o painel do nosso formulario - o GetJContentPane() � um
		// metodo logo abaixo
		this.setModal(true);
		// dizendo que n�o poder� mecher na tela de tras
		this.setResizable(false);
		// n�o ir� redimensionar a tela
		this.setLocationRelativeTo(getOwner());
		// iniciar� no centro da tela
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowOpened(java.awt.event.WindowEvent e) {
				habilita(false);// chama o metodo habilita
				// chama o metodo botao
				mostra();
				// chama o metodo carregaLista
				if (novo) {
					btnNovo.doClick();
				}
			}
		});
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (editar) {
					btnCancelar.doClick();
				}
				System.out.println("Fechando a tela");
			}
		});
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "F2");
		getRootPane().getActionMap().put("F2", new AbstractAction("F2") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (btnNovo.isVisible()) {
					btnNovo.doClick();
				}
			}
		});
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "F3");
		getRootPane().getActionMap().put("F3", new AbstractAction("F3") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (btnAlterar.isVisible()) {
					btnAlterar.doClick();
				}
			}
		});
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), "F4");
		getRootPane().getActionMap().put("F4", new AbstractAction("F4") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (btnExcluir.isVisible()) {
					btnExcluir.doClick();
				}
			}
		});
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "F5");
		getRootPane().getActionMap().put("F5", new AbstractAction("F5") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (btnSalvar.isVisible()) {
					btnSalvar.doClick();
				}
			}
		});

		// getJContentPane().add(getLbNomeProduto());
		getContentPane().add(getLbNomeProduto());
		getContentPane().add(getTxtNomeProduto());
		getContentPane().add(getLbQtdEstoqueAtual());
		getContentPane().add(getTxtQtdEstoqueAtual());
		getContentPane().add(getLbValor());
		getContentPane().add(getTxtValor());
		getContentPane().add(getLbCodigo());
		getContentPane().add(getTxtCodigo());

		getJContentPane().add(getPainel());
	}

	private JPanel getJContentPane() {
		// metodo que constroi os componentes no painel principal
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(224, 236, 255));
			jContentPane.setLayout(null);
			lbStatusObjeto = new JLabel();
			lbStatusObjeto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbStatusObjeto.setBounds(new Rectangle(0, 71, 642, 25));
			lbStatusObjeto.setText("");
			lbStatusObjeto.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane.add(lbStatusObjeto, null);
			lbBusca = new JLabel();
			lbBusca.setBounds(new Rectangle(10, 107, 45, 25));
			lbBusca.setText("Buscar:");
			lbBusca.setDisplayedMnemonic('B');
			jContentPane.add(lbBusca, null);
			// adicionando a label no painel
			jContentPane.add(getTxtCodigo(), null);
			// adicionando o campo de texto no painel
			lbNomeProduto = new JLabel();
			lbNomeProduto.setBounds(new Rectangle(138, 155, 98, 25));
			lbNomeProduto.setText("<html><font color=blue>*</font>Nome Produto:</html>");
			jContentPane.add(lbNomeProduto, null);
			// adicionando a label no painel
			jContentPane.add(getTxtNomeProduto(), null);
			// adicionando o campo de texto no painel
			jContentPane.add(getTxtBusca(), null);
			// adicionando botoes no painel
			jContentPane.add(getBtnNovo(), null);
			// adiciona o botao novo no painel
			jContentPane.add(getBtnAlterar(), null);// botao alterar
			jContentPane.add(getBtnExcluir(), null);
			jContentPane.add(getBtnFechar(), null);
			jContentPane.add(getBtnSalvar(), null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnBuscar(), null);

		}
		return jContentPane;
	}

	// metodo que constroi o componente
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodigo.setBounds(new Rectangle(68, 155, 60, 25));
		}
		return txtCodigo;
	}

	// metodo que constroi o componente
	private JTextField getTxtNomeProduto() {
		if (txtNomeProduto == null) {
			txtNomeProduto = new JTextField();
			txtNomeProduto.setBounds(new Rectangle(227, 155, 400, 25));
			txtNomeProduto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER || e.getKeyCode() == e.VK_DOWN) {
						txtNomeProduto.requestFocus();
					}
				}
			});
		}
		return txtNomeProduto;
	}

	// metodo que constroi o botao buscar
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setBounds(new Rectangle(567, 8, 0, 0));
			btnBuscar.setMnemonic('B');
			btnBuscar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					txtBusca.requestFocus();
					txtBusca.selectAll();
				}
			});
		}
		return btnBuscar;
	}

	private JTextField getTxtBusca() {
		// campo busca
		if (txtBusca == null) {
			txtBusca = new JTextField();
			txtBusca.setBounds(new Rectangle(68, 107, 559, 25));
			txtBusca.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TelaConsultaProduto tela = new TelaConsultaProduto(TelaProduto.this);
					tela.setTexto(txtBusca.getText());
					tela.setVisible(true);
					if (tela.getRetorno() != null) {
						try {
							mostra(new ProdutoDAO().carregarPorID(Integer.parseInt(tela.getRetorno())));
							txtBusca.setText("");
						} catch (NumberFormatException txtBusca) {
							txtBusca.printStackTrace();
						} catch (Exception txtBusca) {
							txtBusca.printStackTrace();
						}
					}

				}
			});
		}
		return txtBusca;
	}

	private void limpa() {// metodo que limpa os campo do formulario
		txtCodigo.setText("");
		txtNomeProduto.setText("");
		txtQtdEstoqueAtual.setText("");
		txtValor.setText("");

	}

	private void habilita(boolean b) {
		// Metodo que habilita ou desabilita os campos do formulario
		txtCodigo.setEnabled(false);
		txtNomeProduto.setEnabled(b);
		txtQtdEstoqueAtual.setEnabled(b);
		txtValor.setEnabled(b);
		btnNovo.setVisible(!b);
		btnAlterar.setVisible(!b);
		btnExcluir.setVisible(!b);
		btnFechar.setVisible(!b);
		btnSalvar.setVisible(b);
		btnCancelar.setVisible(b);
		txtBusca.setEnabled(!b);
	}

	private void mostra(Produto objeto) {
		// metodo recebe a instancia de um objeto e � exibido no formulario
		lbStatusObjeto.setText("Exibindo Registro");
		if (objeto.getCodigo() != null) {
			// � inteiro.. tem que por o toString no final
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));
		} else {
			txtCodigo.setText("");
		}

		txtNomeProduto.setText(objeto.getNomeProduto());
		txtValor.setText(String.valueOf(objeto.getValor()));
		txtQtdEstoqueAtual.setText(String.valueOf((objeto.getQtdEstoqueAtual())));

	}

	private void mostra() {
		// metodo que exibe os dados no formulario
		try {
			Produto ob = new ProdutoDAO().selecionarUltimoCancel();
			mostra(ob);
		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}

	}

	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/crud/img/icons8-mais1-30.png")));
			btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNovo.setBounds(new Rectangle(10, 280, 150, 35));
			// btnNovo.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Plus24.gif")));
			btnNovo.setText("Novo ");
			btnNovo.setMnemonic('N');
			btnNovo.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					habilita(true);// habilita os campos
					limpa();// limpa o formulario
					editar = false;// fala que n�o ta editando
					txtNomeProduto.requestFocus();
					// manda o foco para o campo de codigo-
					lbStatusObjeto.setText("Cadastrando Registro");
				}
			});
		}
		return btnNovo;
	}

	private JButton getBtnAlterar() {
		if (btnAlterar == null) {
			btnAlterar = new JButton();
			btnAlterar.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/crud/img/icons8-alterar-30.png")));
			btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAlterar.setBounds(new Rectangle(170, 280, 150, 35));
			// btnAlterar.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Refresh24.gif")));
			btnAlterar.setText("Alterar ");
			btnAlterar.setMnemonic('A');
			btnAlterar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {
						// pergunta se o campo codigo est� preenchido
						habilita(true);// habilita os campos do formulario
						editar = true;// fala que est� editando
						txtNomeProduto.requestFocus();
						// manda o foco para o segundo campo
						lbStatusObjeto.setText("Alterando Registro");
					}
				}
			});
		}
		return btnAlterar;
	}

	private JButton getBtnExcluir() {
		if (btnExcluir == null) {
			btnExcluir = new JButton();
			btnExcluir.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/crud/img/icons8-excluir-30.png")));
			btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnExcluir.setBounds(new Rectangle(330, 280, 150, 35));
			// btnExcluir.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Delete24.gif")));
			btnExcluir.setText("Excluir ");
			btnExcluir.setMnemonic('E');
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {

						String motivo = JOptionPane.showInputDialog(TelaProduto.this, "Motivo: ");
						if (motivo != null && motivo.trim().length() > 0) {
							ProdutoDAO dao = new ProdutoDAO();
							try {
								Produto ob = getProduto();
								ob.setCancelado("S");
								ob.setCausaCancel(motivo.trim());
								dao.alterar(ob);
								// efetua a exclus�o
								mostra();
								// manda carregar a lista novamente
							} catch (Exception eas) {
							}
						}
					}
				}
			});
		}
		return btnExcluir;
	}

	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton();
			btnFechar.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFechar.setBounds(new Rectangle(487, 280, 140, 35));
			// btnFechar.setIcon(new
			// ImageIcon(TelaCliente.class.getResource("/icones/alertacancela.png")));
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

	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton();
			btnSalvar.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/crud/img/icons8-salvar-30.png")));
			btnSalvar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnCancelar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {
						txtNomeProduto.requestFocus();
					}
				}
			});
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnSalvar.setBounds(new Rectangle(10, 280, 150, 35));
			// btnSalvar.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Save24.gif")));
			btnSalvar.setText("Salvar ");
			btnSalvar.setMnemonic('S');
			btnSalvar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (verifica()) {
						// verifica se os campos obrigatorios est�o todos
						// preenchidos
						ProdutoDAO dao = new ProdutoDAO();
						if (editar) {
							try {
								Produto ob = getProduto();
								// pega o objeto para ser alterado
								ob = monta(ob);
								// chama o metodo que poe os dados do formulario
								// no objeto
								dao.alterar(ob);
								// efetua a altera��o
								editar = false;
								habilita(false);
								// manda desabilitar os campos do formulario
								mostra(ob);
								// carrega a lista novamente
							} catch (Exception as) {
								JOptionPane.showMessageDialog(null, "Erro");
							}

						} else {
							Produto ob = new Produto();
							// cria um objeto novo
							ob = monta(ob);// chama o metodo que monta o objeto
							try {
								ob.setCancelado("N");
								dao.cadastrar(ob);// efetua o cadastro
								habilita(false);// desabilita os campos
								mostra(ob);// carrega a lista
								if (novo) {
									setCadastro(ob);
									dispose();
								}
							} catch (Exception as) {
							}
						}
					}
				}
			});
		}
		return btnSalvar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/crud/img/icons8-cancelar-2-30.png")));
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnCancelar.setBounds(new Rectangle(170, 280, 150, 35));
			// btnCancelar.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Cancel24.gif"))); 28x28
			// pixel
			btnCancelar.setText("Cancel");
			btnCancelar.setMnemonic('C');
			btnCancelar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					habilita(false);
					// desabilita os campos do formulario
					editar = false;
					// se estiver editando ou n�o.. o editando passa a ser falso
					mostra();
				}
			});
			btnCancelar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_LEFT) {
						btnSalvar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {
						txtNomeProduto.requestFocus();
					}
				}
			});
		}
		return btnCancelar;
	}

	private Produto monta(Produto p) {
		// metodo que monta o objeto para salvar ou alterar
		// passamos os dados do formulario para o objeto
		p.setNomeProduto(txtNomeProduto.getText());
		p.setValor(Double.parseDouble(txtValor.getText()));
		p.setQtdEstoqueAtual(Integer.parseInt(txtQtdEstoqueAtual.getText()));

		return p;
	}

	private Produto getProduto() throws NumberFormatException, Exception {
		return new ProdutoDAO().carregarPorID(Integer.parseInt(txtCodigo.getText()));
	}

	private boolean verifica() {
		// metodo que verifica o preenchimento dos campos
		if (txtNomeProduto.getText().isEmpty()) {
			txtNomeProduto.requestFocus();
			JOptionPane.showMessageDialog(null, "O Campo nome do produto é obrigatorio");
			return false;
		}
		if (txtValor.getText().isEmpty()) {
			txtValor.requestFocus();
			JOptionPane.showMessageDialog(null, "O campo valor é obrigatorio");
		}
		return true;

	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setCadastro(Produto cadastro) {
		this.cadastro = cadastro;
	}

	public Produto getCadastro() {
		return cadastro;
	}

	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("Código:");
			lbCodigo.setBounds(9, 160, 46, 14);
		}
		return lbCodigo;
	}

	private JLabel getLbNomeProduto() {
		if (lbNomeProduto == null) {
			lbNomeProduto = new JLabel("");
			lbNomeProduto.setBounds(10, 58, 95, 14);
		}
		return lbNomeProduto;
	}

	private JLabel getLbQtdEstoqueAtual() {
		if (lbQtdEstoqueAtual == null) {
			lbQtdEstoqueAtual = new JLabel("Qtd Estoque Atual:");
			lbQtdEstoqueAtual.setBounds(7, 217, 98, 14);
		}
		return lbQtdEstoqueAtual;
	}

	private JTextField getTxtQtdEstoqueAtual() {
		if (txtQtdEstoqueAtual == null) {
			txtQtdEstoqueAtual = new JTextField();
			txtQtdEstoqueAtual.setHorizontalAlignment(SwingConstants.CENTER);
			txtQtdEstoqueAtual.setBounds(109, 212, 86, 25);
			txtQtdEstoqueAtual.setColumns(10);
		}
		return txtQtdEstoqueAtual;

	}

	private JLabel getLbValor() {
		if (lbValor == null) {
			lbValor = new JLabel("<html><font color=blue>*</font>Valor:</html>");
			lbValor.setBounds(227, 217, 70, 14);
		}
		return lbValor;
	}

	private JTextField getTxtValor() {
		if (txtValor == null) {
			txtValor = new JTextField();
			txtValor.setHorizontalAlignment(SwingConstants.CENTER);
			txtValor.setBounds(275, 212, 86, 25);
			txtValor.setColumns(10);
		}
		return txtValor;
	}

	private JPanel getPainel() {
		if (painel == null) {
			painel = new JPanel();
			painel.setLayout(null);
			painel.setBackground(SystemColor.textHighlight);
			painel.setBounds(0, 0, 642, 60);
			painel.add(getLbProdutos());
		}
		return painel;
	}
	private JLabel getLbProdutos() {
		if (lbProdutos == null) {
			lbProdutos = new JLabel("Produtos");
			lbProdutos.setForeground(Color.WHITE);
			lbProdutos.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbProdutos.setBounds(250, 0, 180, 60);
		}
		return lbProdutos;
	}
}
