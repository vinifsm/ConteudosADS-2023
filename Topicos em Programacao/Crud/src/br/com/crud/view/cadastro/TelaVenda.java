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

//import br.com.crud.dao.CategoriaDAO;
import br.com.crud.dao.ProdutoDAO;
import br.com.crud.dao.VendedorDAO;
import br.com.crud.dao.VendaDAO;
import br.com.crud.dao.PreVendaDAO;
//import br.com.crud.model.Categoria;
import br.com.crud.model.Produto;
import br.com.crud.model.Vendedor;
import br.com.crud.model.Venda;
import br.com.crud.model.PreVenda;
import br.com.crud.util.ManipulaValor;
//import br.com.crud.view.consulta.TelaConsultaCategoria;
import br.com.crud.view.consulta.TelaConsultaProduto;
import br.com.crud.view.consulta.TelaConsultaVenda;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaVenda extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbBusca = null;
	private JTextField txtBusca = null;
	private JLabel lbCodigo = null;
	private JTextField txtCodigo = null;
	private JLabel lbProduto = null;
	private JTextField txtValor = null;
	private String texto = null;
	// private JLabel lbClienteCodigo = null;
	// private JTextField txtClienteCodigo = null;
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
	private Venda cadastro = null;
	private JLabel lbCodCli = null;
	private JTextField txtCodCli = null;
	private JLabel lbNomeCli;
	private JLabel lbValorVenda;
	private JLabel lbTotalItens;
	private JLabel lbQtdItem;
	private JLabel lbDesconto;
	private JTextField txtDesconto;
	private JLabel lblNewLabel;
	private JLabel lbValorApagar;
	private String retorno = null;
	private JPanel painel;
	private JLabel lbVendas;
	
	public TelaVenda(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaVenda(JDialog owner) {
		super(owner);
		initialize();
	}

	public TelaVenda() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// metodo que inicializa os componentes
		this.setSize(633, 377);// tamanho da tela
		this.setTitle("Cadastro de Venda");
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
		getJContentPane().add(getLbNomeCli());
		getJContentPane().add(getLbValorVenda());
		getJContentPane().add(getLbTotalItens());
		getJContentPane().add(getLbQtdItem());
		getJContentPane().add(getLbDesconto());
		getJContentPane().add(getTxtDesconto());
		getJContentPane().add(getLblNewLabel());
		getJContentPane().add(getLbValorApagar());
		getJContentPane().add(getPainel());
	}

	private JPanel getJContentPane() {
		// metodo que constroi os componentes no painel principal
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			lbStatusObjeto = new JLabel();
			lbStatusObjeto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbStatusObjeto.setBounds(new Rectangle(0, 60, 627, 36));
			lbStatusObjeto.setText("");
			lbStatusObjeto.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane.add(lbStatusObjeto, null);
			lbBusca = new JLabel();
			lbBusca.setBounds(new Rectangle(10, 97, 45, 25));
			lbBusca.setText("Buscar:");
			lbBusca.setDisplayedMnemonic('B');
			jContentPane.add(lbBusca, null);
			lbCodigo = new JLabel();
			lbCodigo.setBounds(new Rectangle(10, 133, 101, 25));
			lbCodigo.setText("C\u00F3digo pr\u00E9-venda:");
			jContentPane.add(lbCodigo, null);
			// adicionando a label no painel
			jContentPane.add(getTxtCodigo(), null);
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
			jContentPane.add(getLbProduto());
			jContentPane.add(getLbCodCli());
			jContentPane.add(getTxtCodCli(), null);

		}
		return jContentPane;
	}

	// metodo que constroi o componente
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodigo.setBounds(new Rectangle(108, 132, 37, 26));
		}
		return txtCodigo;
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
			txtBusca.setBounds(new Rectangle(58, 97, 559, 25));
			txtBusca.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TelaConsultaVenda tela = new TelaConsultaVenda(TelaVenda.this);
					tela.setTexto(txtBusca.getText());
					tela.setVisible(true);
					VendaDAO dao = new VendaDAO();
					if (tela.getRetorno() != null) {
						try {
							Venda venda = dao.carregarPorID(Integer.parseInt(tela.getRetorno()));
							mostra(venda);
							
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
		// txtValor.setText("");
		txtCodCli.setText("");
		lbNomeCli.setText("");
		lbValorVenda.setText("");
	}

	private void mostra(Venda objeto) {
		// metodo recebe a instancia de um objeto e � exibido no formulario
		lbStatusObjeto.setText("Exibindo Registro");

		if (objeto.getCodigo() != null) {
			// � inteiro.. tem que por o toString no final
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));

		} else {
			txtCodigo.setText("");

		}
		if (objeto.getVendedor() != null) {
			txtCodCli.setText(objeto.getVendedor().getCodigo().toString());
			lbNomeCli.setText(objeto.getVendedor().getRazaoSocial());
		} else {
			txtCodCli.setText("");
		}


	}
	

	private void mostra() {
		// metodo que exibe os dados no formulario
		try {
			Venda ob = new VendaDAO().selecionarUltimoCancel();
			mostra(ob);
		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}

	}
	
	private void mostra(PreVenda objeto) {
		lbStatusObjeto.setText("Exibindo Registro");
		if (objeto.getCodigo() != null) {
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));
			lbNomeCli.setText(String.valueOf(objeto.getCliente().getRazaoSocial()));
			lbQtdItem.setText(String.valueOf(objeto.getQtdItens()));
			lbValorVenda.setText(String.valueOf(objeto.getValorTotal()));
		} else {
			txtCodigo.setText("");
		}
		txtCodigo.setText(objeto.getCodigo().toString());
	}


	private void habilita(boolean b) {
		// Metodo que habilita ou desabilita os campos do formulario
		txtCodigo.setEnabled(false);
		// txtValor.setEnabled(b);
		txtCodCli.setEnabled(b);
		btnNovo.setVisible(!b);
		btnAlterar.setVisible(!b);
		btnExcluir.setVisible(!b);
		btnFechar.setVisible(!b);
		btnSalvar.setVisible(b);
		btnCancelar.setVisible(b);
		txtBusca.setEnabled(!b);
	}

	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/crud/img/icons8-mais1-30.png")));
			btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNovo.setBounds(new Rectangle(10, 300, 150, 35));
//				btnNovo.setIcon(new ImageIcon(getClass().getResource("/icones/Plus24.gif")));
			btnNovo.setText("Novo ");
			btnNovo.setMnemonic('N');
			btnNovo.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					habilita(true);// habilita os campos
					limpa();// limpa o formulario
					editar = false;// fala que n�o ta editando
					// manda o foco para o campo de codigo
					lbStatusObjeto.setText("Cadastrando Registro");
				}
			});
		}
		return btnNovo;
	}

	private JButton getBtnAlterar() {
		if (btnAlterar == null) {
			btnAlterar = new JButton();
			btnAlterar.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/crud/img/icons8-alterar-30.png")));
			btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAlterar.setBounds(new Rectangle(165, 300, 150, 35));
//				btnAlterar.setIcon(new ImageIcon(getClass().getResource("/icones/Refresh24.gif")));
			btnAlterar.setText("Alterar ");
			btnAlterar.setMnemonic('A');
			btnAlterar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {
						// pergunta se o campo codigo est� preenchido
						habilita(true);// habilita os campos do formulario
						editar = true;// fala que est� editando
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
			btnExcluir.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/crud/img/icons8-excluir-30.png")));
			btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnExcluir.setBounds(new Rectangle(320, 300, 150, 35));
//				btnExcluir.setIcon(new ImageIcon(getClass().getResource("/icones/Delete24.gif")));
			btnExcluir.setText("Excluir ");
			btnExcluir.setMnemonic('E');
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {

						String motivo = JOptionPane.showInputDialog(TelaVenda.this, "Motivo: ");
						if (motivo != null && motivo.trim().length() > 0) {
							VendaDAO dao = new VendaDAO();
							try {
								Venda ob = getVenda();
								ob.setCancel("S");
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
			btnFechar.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFechar.setBounds(new Rectangle(477, 300, 140, 35));
//				btnFechar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/alertacancela.png")));
			btnFechar.setText("Fechar ");
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
			btnSalvar.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/crud/img/icons8-salvar-30.png")));
			btnSalvar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnCancelar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {
						txtCodCli.requestFocus();
					}
				}
			});
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnSalvar.setBounds(new Rectangle(10, 300, 150, 35));
//				btnSalvar.setIcon(new ImageIcon(getClass().getResource("/icones/Save24.gif")));
			btnSalvar.setText("Salvar ");
			btnSalvar.setMnemonic('S');
			btnSalvar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (verifica()) {
						// verifica se os campos obrigatorios est�o todos
						// preenchidos
						VendaDAO dao = new VendaDAO();
						if (editar) {
							try {
								Venda ob = getVenda();
								// Usuario u = getUsuario();
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
							Venda ob = new Venda();
							// cria um objeto novo
							ob = monta(ob);// chama o metodo que monta o objeto
							try {
								ob.setCancel("N");
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
			btnCancelar.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/crud/img/icons8-cancelar-2-30.png")));
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnCancelar.setBounds(new Rectangle(165, 300, 150, 35));
//				btnCancelar.setIcon(new ImageIcon(getClass().getResource("/icones/Cancel24.gif"))); 28x28 pixel
			btnCancelar.setText("Cancel ");
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
						txtCodCli.requestFocus();
					}
				}
			});
		}
		return btnCancelar;
	}

	private Venda monta(Venda v) {
		// c.setCliente(txtClienteCodigo.getText());
		ManipulaValor mani = new ManipulaValor();
		VendedorDAO u = new VendedorDAO();
		
		try {
			Vendedor novoU = u.carregarPorID(Integer.parseInt(txtCodCli.getText()));
			v.setVendedor(novoU);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return v;
	}

	private Venda getVenda() throws NumberFormatException, Exception {
		return new VendaDAO().carregarPorID(Integer.parseInt(txtCodigo.getText()));
	}

	private boolean verifica() {
		if (txtCodCli.getText().isEmpty()) {
			txtCodCli.requestFocus();
			JOptionPane.showMessageDialog(null, "O campo cliente e obrigatorio");
			return false;
		}
		return true;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setCadastro(Venda cadastro) {
		this.cadastro = cadastro;
	}

	public Venda getCadastro() {
		return cadastro;
	}

	private JLabel getLbProduto() {
		if (lbProduto == null) {
			lbProduto = new JLabel("Valor da Venda: ");
			lbProduto.setBounds(10, 169, 95, 14);
		}
		return lbProduto;
	}

	private JLabel getLbCodCli() {
		if (lbCodCli == null) {
			lbCodCli = new JLabel("Cliente: ");
			lbCodCli.setBounds(168, 138, 50, 14);
		}
		return lbCodCli;
	}

	private JTextField getTxtCodCli() {
		if (txtCodCli == null) {
			txtCodCli = new JTextField();
			txtCodCli.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodCli.setBounds(210, 133, 95, 25);
			txtCodCli.setColumns(10);
		}
		return txtCodCli;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	private JLabel getLbNomeCli() {
		if (lbNomeCli == null) {
			lbNomeCli = new JLabel("-");
			lbNomeCli.setBounds(210, 138, 70, 14);
		}
		return lbNomeCli;
	}

	private JLabel getLbValorVenda() {
		if (lbValorVenda == null) {
			lbValorVenda = new JLabel("-");
			lbValorVenda.setBounds(99, 170, 95, 13);
		}
		return lbValorVenda;
	}
	private JLabel getLbTotalItens() {
		if (lbTotalItens == null) {
			lbTotalItens = new JLabel("Total itens: ");
			lbTotalItens.setBounds(10, 205, 59, 14);
		}
		return lbTotalItens;
	}
	private JLabel getLbQtdItem() {
		if (lbQtdItem == null) {
			lbQtdItem = new JLabel("-");
			lbQtdItem.setBounds(76, 205, 84, 14);
		}
		return lbQtdItem;
	}
	private JLabel getLbDesconto() {
		if (lbDesconto == null) {
			lbDesconto = new JLabel("Desconto % : ");
			lbDesconto.setBounds(10, 255, 70, 14);
		}
		return lbDesconto;
	}
	private JTextField getTxtDesconto() {
		if (txtDesconto == null) {
			txtDesconto = new JTextField();
			txtDesconto.setHorizontalAlignment(SwingConstants.CENTER);
			txtDesconto.setBounds(87, 243, 37, 26);
			txtDesconto.setColumns(10);
		}
		return txtDesconto;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Valor a ser pago: ");
			lblNewLabel.setBounds(427, 249, 101, 14);
		}
		return lblNewLabel;
	}
	private JLabel getLbValorApagar() {
		if (lbValorApagar == null) {
			lbValorApagar = new JLabel("R$ 0,0");
			lbValorApagar.setBounds(525, 249, 90, 14);
		}
		return lbValorApagar;
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
			painel.setLayout(null);
			painel.setBackground(SystemColor.textHighlight);
			painel.setBounds(0, 0, 627, 60);
			painel.add(getLbVendas());
		}
		return painel;
	}
	private JLabel getLbVendas() {
		if (lbVendas == null) {
			lbVendas = new JLabel("Vendas");
			lbVendas.setForeground(Color.WHITE);
			lbVendas.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbVendas.setBounds(250, 0, 180, 60);
		}
		return lbVendas;
	}
}
