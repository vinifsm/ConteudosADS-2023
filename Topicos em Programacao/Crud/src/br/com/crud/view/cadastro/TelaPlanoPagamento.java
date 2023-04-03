package br.com.crud.view.cadastro;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

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

import br.com.crud.dao.CrediarioDAO;
import br.com.crud.model.Crediario;
import br.com.crud.util.ManipulaValor;
import br.com.crud.view.consulta.TelaConsultaCrediario;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;

//classe extend de JDialog que � uma classe com padr�es de tela visual
public class TelaPlanoPagamento extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbCodigo = null;
	private JTextField txtCodigo = null;
	private JLabel lbRazaoSocial;
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
	private Crediario cadastro = null;
	private JLabel lbParcelas;
	private JTextField txtParcelas;
	private JLabel lblNewLabel;
	private JTextField txtQtdDias;
	private JPanel painel;
	private JLabel lbPainel;

	/**
	 * @wbp.parser.constructor
	 */
	public TelaPlanoPagamento(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaPlanoPagamento(JDialog owner) {
		// Construtor da classe
		super(owner);
		// Construtor da classe que estamos extendendo... no caso JDialog
		initialize();
		// chama o metodo que inicializa os componentes
	}

	public TelaPlanoPagamento() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// metodo que inicializa os componentes
		this.setSize(633, 354);// tamanho da tela
		this.setTitle("Cadastro de Crediario");
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
		getJContentPane().add(getLbParcelas());
		getJContentPane().add(getTxtParcelas());
		getJContentPane().add(getLblNewLabel());
		getJContentPane().add(getTxtQtdDias());
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
			lbStatusObjeto.setBounds(new Rectangle(0, 75, 627, 25));
			lbStatusObjeto.setText("");
			lbStatusObjeto.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane.add(lbStatusObjeto, null);
			lbBusca = new JLabel();
			lbBusca.setBounds(new Rectangle(10, 111, 45, 25));
			lbBusca.setText("Buscar:");
			lbBusca.setDisplayedMnemonic('B');
			jContentPane.add(lbBusca, null);
			lbCodigo = new JLabel();
			lbCodigo.setBounds(new Rectangle(10, 172, 45, 25));
			lbCodigo.setText("C\u00F3digo:");
			jContentPane.add(lbCodigo, null);
			// adicionando a label no painel
			jContentPane.add(getTxtCodigo(), null);
			// adicionando o campo de texto no painel
			lbRazaoSocial = new JLabel();
			lbRazaoSocial.setBounds(new Rectangle(126, 172, 60, 25));
			lbRazaoSocial.setText("Descri\u00E7\u00E3o:");
			jContentPane.add(lbRazaoSocial, null);
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
			jContentPane.add(getLbParcelas());
			jContentPane.add(getTxtParcelas());
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getTxtQtdDias());

		}
		return jContentPane;
	}

	// metodo que constroi o componente
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodigo.setBounds(new Rectangle(56, 172, 60, 25));
		}
		return txtCodigo;
	}

	// metodo que constroi o componente
	private JTextField getTxtDescricao() {
		if (txtDescricao == null) {
			txtDescricao = new JTextField();
			txtDescricao.setBounds(new Rectangle(201, 172, 414, 25));
		}
		return txtDescricao;
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
			txtBusca.setBounds(new Rectangle(56, 111, 559, 25));
			txtBusca.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// ENTRA NA TELA DE BUSCA DE CREDIARIOS
					TelaConsultaCrediario tela = new TelaConsultaCrediario(TelaPlanoPagamento.this);
					tela.setTexto(txtBusca.getText());
					tela.setVisible(true);
					if (tela.getRetorno() != null) {
						try {
							mostra(new CrediarioDAO().carregarPorID(Integer.parseInt(tela.getRetorno())));
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
		txtParcelas.setText("");
		txtQtdDias.setText("");
	}

	private void habilita(boolean b) {
		// Metodo que habilita ou desabilita os campos do formulario
		txtCodigo.setEnabled(false);
		txtDescricao.setEnabled(b);
		txtParcelas.setEnabled(b);
		txtQtdDias.setEnabled(b);
		btnNovo.setVisible(!b);
		btnAlterar.setVisible(!b);
		btnExcluir.setVisible(!b);
		btnFechar.setVisible(!b);
		btnSalvar.setVisible(b);
		btnCancelar.setVisible(b);
		txtBusca.setEnabled(!b);
	}

	private void mostra(Crediario objeto) {
		// metodo recebe a instancia de um objeto e � exibido no formulario
		ManipulaValor mani = new ManipulaValor();
		lbStatusObjeto.setText("Exibindo Registro");
		if (objeto.getCodigo() != null) {
			// � inteiro.. tem que por o toString no final
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));
		} else {
			txtCodigo.setText("");
		}
		txtDescricao.setText(objeto.getDescricao());
		txtQtdDias.setText(objeto.getQtdDias().toString());
		txtParcelas.setText(objeto.getParcelas().toString());
	}

	private void mostra() {
		// metodo que exibe os dados no formulario
		try {

		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}
	}

	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setIcon(new ImageIcon(TelaPlanoPagamento.class.getResource("/br/com/crud/img/icons8-mais1-30.png")));
			btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNovo.setBounds(new Rectangle(10, 279, 150, 35));
//			btnNovo.setIcon(new ImageIcon(getClass().getResource("/icones/Plus24.gif")));
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
			btnAlterar.setIcon(new ImageIcon(TelaPlanoPagamento.class.getResource("/br/com/crud/img/icons8-alterar-30.png")));
			btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAlterar.setBounds(new Rectangle(165, 279, 150, 35));
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
			btnExcluir.setIcon(new ImageIcon(TelaPlanoPagamento.class.getResource("/br/com/crud/img/icons8-excluir-30.png")));
			btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnExcluir.setBounds(new Rectangle(321, 279, 150, 35));
//			btnExcluir.setIcon(new ImageIcon(getClass().getResource("/icones/Delete24.gif")));
			btnExcluir.setText("Excluir ");
			btnExcluir.setMnemonic('E');
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {

						String motivo = JOptionPane.showInputDialog(TelaPlanoPagamento.this, "Motivo: ");
						if (motivo != null && motivo.trim().length() > 0) {
							CrediarioDAO dao = new CrediarioDAO();
							try {
								Crediario ob = getCrediario();
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
			btnFechar.setIcon(new ImageIcon(TelaPlanoPagamento.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFechar.setBounds(new Rectangle(477, 279, 140, 35));
//			btnFechar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/alertacancela.png")));
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
			btnSalvar.setIcon(new ImageIcon(TelaPlanoPagamento.class.getResource("/br/com/crud/img/icons8-salvar-30.png")));
			btnSalvar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnCancelar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {

					}
				}
			});
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnSalvar.setBounds(new Rectangle(10, 279, 150, 35));

			btnSalvar.setText("Salvar ");
			btnSalvar.setMnemonic('S');
			btnSalvar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (verifica()) {
						// verifica se os campos obrigatorios est�o todos
						// preenchidos
						CrediarioDAO dao = new CrediarioDAO();
						if (editar) {
							try {
								Crediario ob = getCrediario();
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
							Crediario ob = new Crediario();
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
			btnCancelar.setIcon(new ImageIcon(TelaPlanoPagamento.class.getResource("/br/com/crud/img/icons8-cancelar-2-30.png")));
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnCancelar.setBounds(new Rectangle(165, 279, 150, 35));
//			btnCancelar.setIcon(new ImageIcon(getClass().getResource("/icones/Cancel24.gif"))); 28x28 pixel
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

					}
				}
			});
		}
		return btnCancelar;
	}

	private Crediario monta(Crediario c) {
		// SETANDO OS DADOS PARA SEREM INSERIDOS NO BANCO
		c.setDescricao(txtDescricao.getText());
		c.setParcelas(Integer.parseInt(txtParcelas.getText()));
		c.setQtdDias(Integer.parseInt(txtQtdDias.getText()));
		c.setCancelado("N");

		// SETANDO DATA DE VENCIMENTO PRO BANCO
		Calendar cal = Calendar.getInstance();
		Integer days = c.getQtdDias();
		cal.add(Calendar.DAY_OF_MONTH, days);
		Date data = cal.getTime();
		c.setVencimento(data);

		return c;
	}

	private Crediario getCrediario() throws NumberFormatException, Exception {
		return new CrediarioDAO().carregarPorID(Integer.parseInt(txtCodigo.getText()));
	}

	private boolean verifica() {
		// metodo que verifica o preenchimento dos campos
		if (txtDescricao.getText().isEmpty()) {
			txtDescricao.requestFocus();
			JOptionPane.showMessageDialog(null, "O Campo Descricao e obrigatorio");
			return false;
		}
		return true;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setCadastro(Crediario cadastro) {
		this.cadastro = cadastro;
	}

	public Crediario getCadastro() {
		return cadastro;
	}

	private JLabel getLbParcelas() {
		if (lbParcelas == null) {
			lbParcelas = new JLabel("Parcelas: ");
			lbParcelas.setBounds(10, 242, 60, 14);
		}
		return lbParcelas;
	}

	private JTextField getTxtParcelas() {
		if (txtParcelas == null) {
			txtParcelas = new JTextField();
			txtParcelas.setHorizontalAlignment(SwingConstants.CENTER);
			txtParcelas.setBounds(new Rectangle(70, 79, 60, 25));
			txtParcelas.setBounds(56, 237, 60, 25);
		}
		return txtParcelas;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Quant. Dias:");
			lblNewLabel.setBounds(126, 242, 70, 14);
		}
		return lblNewLabel;
	}

	private JTextField getTxtQtdDias() {
		if (txtQtdDias == null) {
			txtQtdDias = new JTextField();
			txtQtdDias.setHorizontalAlignment(SwingConstants.CENTER);
			txtQtdDias.setBounds(new Rectangle(70, 79, 60, 25));
			txtQtdDias.setBounds(201, 237, 60, 25);
		}
		return txtQtdDias;
	}
	private JPanel getPainel() {
		if (painel == null) {
			painel = new JPanel();
			painel.setBackground(SystemColor.textHighlight);
			painel.setBounds(0, 0, 627, 73);
			painel.setLayout(null);
			painel.add(getLbPainel());
		}
		return painel;
	}
	private JLabel getLbPainel() {
		if (lbPainel == null) {
			lbPainel = new JLabel("Cadastro de Crediario");
			lbPainel.setForeground(Color.WHITE);
			lbPainel.setFont(new Font("Tahoma", Font.PLAIN, 29));
			lbPainel.setBounds(170, 17, 281, 45);
		}
		return lbPainel;
	}
}
