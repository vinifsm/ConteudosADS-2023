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

import br.com.crud.dao.VendedorDAO;
import br.com.crud.model.Vendedor;
import br.com.crud.view.consulta.TelaConsultaVendedor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaVendedor extends JDialog {
	private static final long serialVersionUID = 1L;
	// Aqui s�o declarados todos os bot�es e textos
	private JPanel jContentPane = null;
	private JLabel lbBusca = null;
	private JTextField txtBusca = null;
	private JLabel lbCodigo = null;
	private JTextField txtCodigo = null;
	// Bot�es pr�-cadastrados do uso da tela
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
	private Vendedor cadastro = null;
	private JTextField txtNome = null;
	private JTextField txtCpf = null;
	private JTextField txtEmail = null;
	private JLabel lbCpf = null;
	private JLabel lbDataNascimento;
	private JDateChooser txtDataNascimento = null;
	private JPanel painelVendedores;
	private JLabel lbVendedores;

	public TelaVendedor(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaVendedor(JDialog owner) {
		super(owner);
		initialize();
	}

	public TelaVendedor() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// metodo que inicializa os componentes
		this.setSize(648, 387);// tamanho da tela
		this.setTitle("Cadastro de Vendedor");
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

		getJContentPane().add(getLbDataNascimento());
		getJContentPane().add(getTxtDataNascimento());
		getJContentPane().add(getPainelVendedores());
	}

	private JPanel getJContentPane() {
		// metodo que constroi os componentes no painel principal
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(224, 236, 255));
			jContentPane.setLayout(null);
			lbBusca = new JLabel();
			lbBusca.setBounds(new Rectangle(10, 107, 45, 25));
			lbBusca.setText("Buscar:");
			lbBusca.setDisplayedMnemonic('B');
			jContentPane.add(lbBusca, null);
			lbCodigo = new JLabel();
			lbCodigo.setBounds(new Rectangle(10, 154, 45, 25));
			lbCodigo.setText("C\u00F3digo:");
			jContentPane.add(lbCodigo, null);
			// adicionando a label no painel
			jContentPane.add(getTxtCodigo(), null);
			lbStatusObjeto = new JLabel();
			lbStatusObjeto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbStatusObjeto.setBounds(new Rectangle(0, 71, 639, 25));
			lbStatusObjeto.setText("");
			lbStatusObjeto.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane.add(lbStatusObjeto, null);

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
			jContentPane.add(getTxtDataNascimento(), null);

			JLabel lbNome = new JLabel("<html><font color=blue>*</font>Nome:</html>");
			lbNome.setBounds(9, 212, 46, 14);
			jContentPane.add(lbNome);

			txtNome = new JTextField();
			txtNome.setBounds(56, 201, 459, 25);
			jContentPane.add(txtNome);
			txtNome.setColumns(10);

			JLabel lbCpf = new JLabel("<html><font color=blue>*</font>CPF:</html>");
			lbCpf.setBounds(272, 252, 33, 14);
			jContentPane.add(lbCpf);

			txtCpf = new JTextField();
			txtCpf.setBounds(309, 247, 206, 25);
			jContentPane.add(txtCpf);
			txtCpf.setColumns(10);

			JLabel lbEmail = new JLabel("Email: ");
			lbEmail.setBounds(10, 252, 46, 14);
			jContentPane.add(lbEmail);

			txtEmail = new JTextField();
			txtEmail.setBounds(56, 247, 206, 25);
			jContentPane.add(txtEmail);
			txtEmail.setColumns(10);

		}
		return jContentPane;
	}

	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodigo.setBounds(new Rectangle(56, 154, 70, 25));
		}
		return txtCodigo;
	}

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
			txtBusca.setBounds(new Rectangle(56, 107, 559, 25));
			txtBusca.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TelaConsultaVendedor tela = new TelaConsultaVendedor(TelaVendedor.this);
					tela.setTexto(txtBusca.getText());
					tela.setVisible(true);
					if (tela.getRetorno() != null) {
						try {
							mostra(new VendedorDAO().carregarPorID(Integer.parseInt(tela.getRetorno())));
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
		txtNome.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtDataNascimento.setDate(null);
	}

	private void mostra(Vendedor objeto) {
		// metodo recebe a instancia de um objeto e � exibido no formulario
		lbStatusObjeto.setText("Exibindo Registro");
		if (objeto.getCodigo() != null) {
			// � inteiro.. tem que por o toString no final
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));
		} else {
			txtCodigo.setText("");
		}
		txtNome.setText(objeto.getRazaoSocial());
		txtEmail.setText(objeto.getEmail());
		txtCpf.setText(objeto.getCpf());
		txtDataNascimento.setDate(objeto.getDataNascimento());
	}

	private void mostra() {
		// metodo que exibe os dados no formulario
		try {
			Vendedor ob = new VendedorDAO().selecionarUltimoCancel();
			mostra(ob);
		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}

	}

	private void habilita(boolean b) {
		// Metodo que habilita ou desabilita os campos do formulario
		txtCodigo.setEnabled(false);
		txtNome.setEnabled(b);
		txtCpf.setEnabled(b);
		txtEmail.setEnabled(b);
		btnNovo.setVisible(!b);
		btnAlterar.setVisible(!b);
		btnExcluir.setVisible(!b);
		btnFechar.setVisible(!b);
		btnSalvar.setVisible(b);
		btnCancelar.setVisible(b);
		txtBusca.setEnabled(!b);
		txtDataNascimento.setEnabled(b);
	}

	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setIcon(new ImageIcon(TelaVendedor.class.getResource("/br/com/crud/img/icons8-mais1-30.png")));
			btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNovo.setBounds(new Rectangle(10, 311, 150, 35));
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
					txtNome.requestFocus();
					txtCpf.requestFocus();
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
			btnAlterar.setIcon(new ImageIcon(TelaVendedor.class.getResource("/br/com/crud/img/icons8-alterar-30.png")));
			btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAlterar.setBounds(new Rectangle(170, 311, 150, 35));
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
						txtCpf.requestFocus();
						txtNome.requestFocus();
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
			btnExcluir.setIcon(new ImageIcon(TelaVendedor.class.getResource("/br/com/crud/img/icons8-excluir-30.png")));
			btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnExcluir.setBounds(new Rectangle(330, 311, 150, 35));
			// btnExcluir.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Delete24.gif")));
			btnExcluir.setText("Excluir ");
			btnExcluir.setMnemonic('E');
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {

						String motivo = JOptionPane.showInputDialog(TelaVendedor.this, "Motivo: ");
						if (motivo != null && motivo.trim().length() > 0) {
							VendedorDAO dao = new VendedorDAO();
							try {
								Vendedor ob = getVendedor();
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
			btnFechar.setIcon(new ImageIcon(TelaVendedor.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFechar.setBounds(new Rectangle(490, 311, 140, 35));
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
			btnSalvar.setIcon(new ImageIcon(TelaVendedor.class.getResource("/br/com/crud/img/icons8-salvar-30.png")));
			btnSalvar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnCancelar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {
						txtNome.requestFocus();
						txtCpf.requestFocus();
					}
				}
			});
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnSalvar.setBounds(new Rectangle(10, 311, 150, 35));
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
						VendedorDAO dao = new VendedorDAO();
						if (editar) {
							try {
								Vendedor ob = getVendedor();
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
							Vendedor ob = new Vendedor();
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
			btnCancelar.setIcon(new ImageIcon(TelaVendedor.class.getResource("/br/com/crud/img/icons8-cancelar-2-30.png")));
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnCancelar.setBounds(new Rectangle(170, 311, 150, 35));
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
						txtNome.requestFocus();
					}
				}
			});
		}
		return btnCancelar;
	}

	private Vendedor monta(Vendedor u) {
		// c.setCliente(txtClienteCodigo.getText());
		u.setRazaoSocial(txtNome.getText());
		u.setCpf(txtCpf.getText());
		u.setEmail(txtEmail.getText());
		u.setDataNascimento(txtDataNascimento.getDate());
		return u;
	}

	private Vendedor getVendedor1() throws NumberFormatException, Exception {
		return new VendedorDAO().carregarPorID(Integer.parseInt(txtCodigo.getText()));
	}

	private boolean verifica() {
		if (txtCpf.getText().isEmpty()) {
			txtCpf.requestFocus();
			JOptionPane.showMessageDialog(null, "O campo CPF e obrigatorio");
			return false;
		}
		if (txtNome.getText().isEmpty()) {
			txtNome.requestFocus();
			JOptionPane.showMessageDialog(null, "O campo NOME é obrigatorio");
			return false;
		}
		return true;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setCadastro(Vendedor cadastro) {
		this.cadastro = cadastro;
	}

	public Vendedor getCadastro() {
		return cadastro;
	}

	//
	// private JTextField getCpf() {
	// if(txtCpf == null) {
	// txtCpf = new JTextField();
	// txtCpf.setColumns(10);
	// }
	// return txtCpf;
	// }
	//
	// private JTextField getEmail() {
	// if(txtEmail == null) {
	// txtEmail = new JTextField();
	// txtEmail.setColumns(10);
	// }
	// return txtEmail;
	// }

	public JLabel getLbCpf() {
		return lbCpf;
	}

	public void setLbCpf(JLabel lbCpf) {
		this.lbCpf = lbCpf;
	}

	private JLabel getLbDataNascimento() {
		if (lbDataNascimento == null) {
			lbDataNascimento = new JLabel("Data de Nascimento");
			lbDataNascimento.setBounds(249, 155, 116, 22);

		}
		return lbDataNascimento;
	}

	private JDateChooser getTxtDataNascimento() {
		if (txtDataNascimento == null) {
			txtDataNascimento = new JDateChooser();
			txtDataNascimento.setBounds(355, 152, 115, 25);
			txtDataNascimento.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER || e.getKeyCode() == e.VK_DOWN) {
						btnSalvar.requestFocus();
					}
				}
			});
		}
		return txtDataNascimento;
	}
	private JPanel getPainelVendedores() {
		if (painelVendedores == null) {
			painelVendedores = new JPanel();
			painelVendedores.setBackground(SystemColor.textHighlight);
			painelVendedores.setBounds(0, 0, 642, 60);
			painelVendedores.setLayout(null);
			painelVendedores.add(getLbVendedores());
		}
		return painelVendedores;
	}
	private JLabel getLbVendedores() {
		if (lbVendedores == null) {
			lbVendedores = new JLabel("Funcionarios");
			lbVendedores.setForeground(Color.WHITE);
			lbVendedores.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbVendedores.setBounds(230, 0, 191, 60);
		}
		return lbVendedores;
	}
}
