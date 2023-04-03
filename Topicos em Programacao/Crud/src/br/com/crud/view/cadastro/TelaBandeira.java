package br.com.crud.view.cadastro;

import java.awt.Color;
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

import br.com.crud.dao.BandeiraCartaoDAO;
import br.com.crud.model.BandeiraCartao;
import br.com.crud.view.consulta.TelaConsultaBandeira;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

//classe extend de JDialog que � uma classe com padr�es de tela visual
public class TelaBandeira extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbCodigo = null;
	private JTextField txtCodigo = null;

	private JLabel lbDescricao;
	private JTextField txtDescricao = null;
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
	private BandeiraCartao cadastro = null;
	private JTextField txtContaDescricao;
	private JLabel lblNewLabel;
	private JLabel lbDataVencimento;
	private JTextField txtDataVencimento;
	private JLabel lbTipoConta;
	private JTextField txtTipo;
	private JLabel lbCreditoDebito;
	private JPanel painelCadastro;
	private JLabel lbpainelCadastro;

	/**
	 * @wbp.parser.constructor
	 */
	public TelaBandeira(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaBandeira(JDialog owner) {
		// Construtor da classe
		super(owner);
		// Construtor da classe que estamos extendendo... no caso JDialog
		initialize();
		// chama o metodo que inicializa os componentes
	}

	public TelaBandeira() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// metodo que inicializa os componentes
		this.setSize(633, 396);// tamanho da tela
		this.setTitle("Cadastro de Bandeira");
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

		getJContentPane().add(getPainelCadastro());
	}

	private JPanel getJContentPane() {
		// metodo que constroi os componentes no painel principal
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			lbStatusObjeto = new JLabel();
			lbStatusObjeto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbStatusObjeto.setBounds(new Rectangle(0, 98, 627, 25));
			lbStatusObjeto.setText("");
			lbStatusObjeto.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane.add(lbStatusObjeto, null);
			lbBusca = new JLabel();
			lbBusca.setBounds(new Rectangle(10, 83, 45, 25));
			lbBusca.setText("Buscar:");
			lbBusca.setDisplayedMnemonic('B');
			jContentPane.add(lbBusca, null);
			lbCodigo = new JLabel();
			lbCodigo.setBounds(new Rectangle(10, 134, 45, 25));
			lbCodigo.setText("C\u00F3digo:");
			jContentPane.add(lbCodigo, null);
			// adicionando a label no painel
			jContentPane.add(getTxtCodigo(), null);
			// adicionando o campo de texto no painel
			lbDescricao = new JLabel();
			lbDescricao.setBounds(new Rectangle(126, 129, 70, 25));
			lbDescricao.setText("Decri\u00E7\u00E3o: ");
			jContentPane.add(lbDescricao, null);
			// adicionando a label no painel
			jContentPane.add(getTxtDescricao(), null);
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
			jContentPane.add(getTxtDescricao());
			jContentPane.add(getTxtContaDescricao());
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getLbDataVencimento());
			jContentPane.add(getTxtDataVencimento());
			jContentPane.add(getLbTipoConta());
			jContentPane.add(getTxtTipo());
			jContentPane.add(getLbCreditoDebito());
		}
		return jContentPane;
	}

	// metodo que constroi o componente
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodigo.setBounds(new Rectangle(56, 129, 60, 25));
		}
		return txtCodigo;
	}

	// metodo que constroi o componente
	private JTextField getTxtDescricao() {
		if (txtDescricao == null) {
			txtDescricao = new JTextField();
			txtDescricao.setBounds(new Rectangle(186, 129, 431, 25));
			txtDescricao.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER || e.getKeyCode() == e.VK_DOWN) {
						txtDescricao.requestFocus();
					}
				}
			});
		}
		return txtDescricao;
	}

	private JTextField getTxtContaDescricao() {
		if (txtContaDescricao == null) {
			txtContaDescricao = new JTextField();
			txtContaDescricao.setBounds(new Rectangle(102, 165, 515, 25));
			txtContaDescricao.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER || e.getKeyCode() == e.VK_DOWN) {
						txtContaDescricao.requestFocus();
					}
				}
			});
		}
		return txtContaDescricao;
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
			txtBusca.setBounds(new Rectangle(58, 83, 559, 25));
			txtBusca.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TelaConsultaBandeira tela = new TelaConsultaBandeira(TelaBandeira.this);
					tela.setTexto(txtBusca.getText());
					tela.setVisible(true);
					if (tela.getRetorno() != null) {
						try {
							mostra(new BandeiraCartaoDAO().carregarPorID(Integer.parseInt(tela.getRetorno())));
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
		txtDescricao.setText("");
		txtContaDescricao.setText("");
		txtDataVencimento.setText("");
		txtTipo.setText("");
	}

	private void habilita(boolean b) {
		// Metodo que habilita ou desabilita os campos do formulario
		txtCodigo.setEnabled(false);
		txtDescricao.setEnabled(b);
		txtContaDescricao.setEnabled(b);
		txtDataVencimento.setEnabled(b);
		txtTipo.setEnabled(b);
		btnNovo.setVisible(!b);
		btnAlterar.setVisible(!b);
		btnExcluir.setVisible(!b);
		btnFechar.setVisible(!b);
		btnSalvar.setVisible(b);
		btnCancelar.setVisible(b);
		txtBusca.setEnabled(!b);
	}

	private void mostra(BandeiraCartao objeto) {
		// metodo recebe a instancia de um objeto e � exibido no formulario
		lbStatusObjeto.setText("Exibindo Registro");
		if (objeto.getCodigo() != null) {
			// � inteiro.. tem que por o toString no final
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));
		} else {
			txtCodigo.setText("");
		}
		txtDescricao.setText(objeto.getDescricao());
		txtContaDescricao.setText(objeto.getContaDescricao());
		txtTipo.setText(objeto.getTipoCartao().toString());
		if (objeto.getTipoCartao() == 1) {
			txtDataVencimento.setText(objeto.getDebitoDia().toString());
		}
		if (objeto.getTipoCartao() == 2) {
			txtDataVencimento.setText(objeto.getCredidoDia().toString());
		}
	}

	private void mostra() {
		// metodo que exibe os dados no formulario
		try {
			BandeiraCartao ob = new BandeiraCartaoDAO().selecionarUltimoCancel();
			mostra(ob);
		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}

	}

	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setIcon(new ImageIcon(TelaBandeira.class.getResource("/br/com/crud/img/icons8-mais1-30.png")));
			btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNovo.setBounds(new Rectangle(10, 319, 150, 35));
//			btnNovo.setIcon(new ImageIcon(getClass().getResource("/icones/Plus24.gif")));
			btnNovo.setText("Novo ");
			btnNovo.setMnemonic('N');
			btnNovo.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					habilita(true);// habilita os campos
					limpa();// limpa o formulario
					editar = false;// fala que n�o ta editando
					txtDescricao.requestFocus();
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
			btnAlterar.setIcon(new ImageIcon(TelaBandeira.class.getResource("/br/com/crud/img/icons8-alterar-30.png")));
			btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAlterar.setBounds(new Rectangle(165, 319, 150, 35));
//			btnAlterar.setIcon(new ImageIcon(getClass().getResource("/icones/Refresh24.gif")));
			btnAlterar.setText("Alterar ");
			btnAlterar.setMnemonic('A');
			btnAlterar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {
						// pergunta se o campo codigo est� preenchido
						habilita(true);// habilita os campos do formulario
						editar = true;// fala que est� editando
						txtDescricao.requestFocus();
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
			btnExcluir.setIcon(new ImageIcon(TelaBandeira.class.getResource("/br/com/crud/img/icons8-excluir-30.png")));
			btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnExcluir.setBounds(new Rectangle(321, 319, 150, 35));
//			btnExcluir.setIcon(new ImageIcon(getClass().getResource("/icones/Delete24.gif")));
			btnExcluir.setText("Excluir ");
			btnExcluir.setMnemonic('E');
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {

						String motivo = JOptionPane.showInputDialog(TelaBandeira.this, "Motivo: ");
						if (motivo != null && motivo.trim().length() > 0) {
							BandeiraCartaoDAO dao = new BandeiraCartaoDAO();
							try {
								BandeiraCartao ob = getBandeiraCartao();
								ob.setCancelado("S");
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
			btnFechar.setIcon(new ImageIcon(TelaBandeira.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFechar.setBounds(new Rectangle(477, 319, 140, 35));
//			btnFechar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/alertacancela.png")));
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
			btnSalvar.setIcon(new ImageIcon(TelaBandeira.class.getResource("/br/com/crud/img/icons8-salvar-30.png")));
			btnSalvar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnCancelar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {
						txtDescricao.requestFocus();
					}
				}
			});
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnSalvar.setBounds(new Rectangle(10, 319, 150, 35));
//			btnSalvar.setIcon(new ImageIcon(getClass().getResource("/icones/Save24.gif")));
			btnSalvar.setText("Salvar ");
			btnSalvar.setMnemonic('S');
			btnSalvar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (verifica()) {
						// verifica se os campos obrigatorios est�o todos
						// preenchidos
						BandeiraCartaoDAO dao = new BandeiraCartaoDAO();
						if (editar) {
							try {
								BandeiraCartao ob = getBandeiraCartao();
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
							BandeiraCartao ob = new BandeiraCartao();
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
			btnCancelar.setIcon(new ImageIcon(TelaBandeira.class.getResource("/br/com/crud/img/icons8-cancelar-2-30.png")));
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnCancelar.setBounds(new Rectangle(165, 319, 150, 35));
//			btnCancelar.setIcon(new ImageIcon(getClass().getResource("/icones/Cancel24.gif"))); 28x28 pixel
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
						txtDescricao.requestFocus();
					}
				}
			});
		}
		return btnCancelar;
	}

	private BandeiraCartao monta(BandeiraCartao band) {
		// metodo que monta o objeto para salvar ou alterar
		// passamos os dados do formulario para o objeto
		band.setDescricao(txtDescricao.getText());
		band.setContaDescricao(txtContaDescricao.getText());
		band.setTipoCartao(Integer.parseInt(txtTipo.getText()));
		Integer tipo = Integer.parseInt(txtTipo.getText());
		if (tipo == 1) {
			band.setDebitoDia(Integer.parseInt(txtDataVencimento.getText()));
		}
		if (tipo == 2) {
			band.setCreditoDia(Integer.parseInt(txtDataVencimento.getText()));
		}
		return band;
	}

	private BandeiraCartao getBandeiraCartao() throws NumberFormatException, Exception {
		return new BandeiraCartaoDAO().carregarPorID(Integer.parseInt(txtCodigo.getText()));
	}

	private boolean verifica() {
		// metodo que verifica o preenchimento dos campos
		if (txtDescricao.getText().isEmpty()) {
			txtDescricao.requestFocus();
			JOptionPane.showMessageDialog(null, "O Campo Descri��o � obrigat�rio");
			return false;
		}
		return true;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setCadastro(BandeiraCartao band) {
		this.cadastro = band;
	}

	public BandeiraCartao getCadastro() {
		return cadastro;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Conta Descri\u00E7\u00E3o:");
			lblNewLabel.setBounds(10, 170, 82, 14);
		}
		return lblNewLabel;
	}

	private JLabel getLbDataVencimento() {
		if (lbDataVencimento == null) {
			lbDataVencimento = new JLabel("Dias para pagamento: ");
			lbDataVencimento.setBounds(10, 210, 109, 14);
		}
		return lbDataVencimento;
	}

	private JTextField getTxtDataVencimento() {
		if (txtDataVencimento == null) {
			txtDataVencimento = new JTextField();
			txtDataVencimento.setHorizontalAlignment(SwingConstants.CENTER);
			txtDataVencimento.setBounds(126, 205, 70, 25);
			
		}
		return txtDataVencimento;
	}

	private JLabel getLbTipoConta() {
		if (lbTipoConta == null) {
			lbTipoConta = new JLabel("Tipo Conta:");
			lbTipoConta.setBounds(10, 247, 60, 14);
		}
		return lbTipoConta;
	}

	private JTextField getTxtTipo() {
		if (txtTipo == null) {
			txtTipo = new JTextField();
			txtTipo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTipo.setBounds(80, 242, 116, 25);
			txtTipo.setColumns(10);
		}
		return txtTipo;
	}

	private JLabel getLbCreditoDebito() {
		if (lbCreditoDebito == null) {
			lbCreditoDebito = new JLabel("1 - D\u00E9bito | 2 - Cr\u00E9dito");
			lbCreditoDebito.setForeground(SystemColor.textHighlight);
			lbCreditoDebito.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbCreditoDebito.setBounds(222, 246, 140, 14);
		}
		return lbCreditoDebito;
	}
	private JPanel getPainelCadastro() {
		if (painelCadastro == null) {
			painelCadastro = new JPanel();
			painelCadastro.setBackground(SystemColor.textHighlight);
			painelCadastro.setBounds(0, 0, 627, 66);
			painelCadastro.setLayout(null);
			painelCadastro.add(getLbpainelCadastro());
		}
		return painelCadastro;
	}
	private JLabel getLbpainelCadastro() {
		if (lbpainelCadastro == null) {
			lbpainelCadastro = new JLabel("Cadastro de Bandeira");
			lbpainelCadastro.setForeground(Color.WHITE);
			lbpainelCadastro.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbpainelCadastro.setBounds(148, 11, 320, 39);
		}
		return lbpainelCadastro;
	}
}
