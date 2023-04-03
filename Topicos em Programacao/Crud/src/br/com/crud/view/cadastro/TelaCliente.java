package br.com.crud.view.cadastro;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import br.com.crud.dao.ClienteDAO;
import br.com.crud.dao.VendedorDAO;
import br.com.crud.model.Cliente;
import br.com.crud.model.Vendedor;
import br.com.crud.view.consulta.TelaConsultaCliente;
import br.com.crud.view.consulta.TelaConsultaVendedor;


import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

//classe extend de JDialog que � uma classe com padr�es de tela visual
public class TelaCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbCodigo = null;
	private JTextField txtCodigo = null;
	private JLabel lbDescricao = null;
	private JLabel lbRazaoSocial;
	private JTextField txtRazaoSocial = null;
	private JLabel lbBusca = null;
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
	private Cliente cadastro = null;
	private Vendedor cadastroV = null;
	private JDateChooser txtDataNascimento = null;
	private JLabel lbDataNascimento;
	private JLabel lbEndereco;
	private JTextField txtEndereco;
	private JLabel lbComplemento;
	private JTextField txtComplemento;
	private JLabel lbBairro;
	private JTextField txtBairro;
	private JLabel lbNumero;
	private JTextField txtNumero;
	private JLabel lbCpf;
	private JTextField txtCpf;
	private JPanel painelClientes;
	private JLabel lbCliente;
	private JLabel lbNomeFantasia;
	private JTextField txtNomeFantasia;
	private JLabel lbRG;
	private JTextField txtRG;
	private JLabel lbUFrg;
	private JTextField txtUfrg;
	private JLabel lbTelefone;
	private JTextField txtTelefone;
	private JLabel lbTelefone2;
	private JTextField txtTelefone2;
	private JLabel lbCelular;
	private JTextField txtCelular;
	private JLabel lbCep;
	private JTextField txtCep;
	private JLabel lbCidade;
	private JTextField txtCidade;
	private JLabel lbUF;
	private JTextField txtUf;
	private JLabel lbEmail;
	private JTextField txtEmail;
	private JLabel lbSite;
	private JTextField txtSite;
	private JCheckBox checkCliente;
	private JCheckBox checkFuncionaria;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnCliente;
	private JButton btnFuncionario;
	private JPanel painelDadosPessoais;
	private JPanel painelEndereco;
	private JPanel painelContatoEletronico;
	private JPanel painelTipoCadastro;
	private JPanel painelPrincipal;
	private JButton btnBusca;
	private JTabbedPane tabbedPane;
	private JPanel painelCadastro;
	private JPanel painelFuncionario;
	private JPanel painelDadosFuncionarios;
	private JDateChooser dataAdmissao;
	private JLabel lbAdmissao;
	private JDateChooser dataDemissao;
	private JLabel lbDemissao;
	private JTextField txtNome;
	private JLabel lbNome;
	private JTextField txtCargo;
	private JLabel lbCargo;
	private JTextField txtSalario;
	private JLabel lbSalario;
	private JTextField txtFilho;
	private JLabel lbFilhos;
	private JPanel painelConfigFuncionario;
	private JLabel lbObs;
	private JTextPane textObs;

	public TelaCliente(JFrame owner) {
//		super(owner);
		initialize();
	}

	public TelaCliente(JDialog owner) {
		// Construtor da classe
//		super(owner);
		// Construtor da classe que estamos extendendo... no caso JDialog
		initialize();
		// chama o metodo que inicializa os componentes
	}

	public TelaCliente() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// metodo que inicializa os componentes
		this.setSize(966, 743);// tamanho da tela
		this.setTitle("Cadastro");
		this.setContentPane(getJContentPane());
		// setando o painel do nosso formulario - o GetJContentPane() � um
		// metodo logo abaixo
//		this.setModal(true);
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
		getJContentPane().add(getPainelClientes());
		getJContentPane().add(getTabbedPane());
		tabbedPane.setEnabledAt(1, false);
		getJContentPane().add(getBtnCliente());
		getJContentPane().add(getBtnFuncionario());
		lbStatusObjeto = new JLabel();
		lbStatusObjeto.setBounds(184, 80, 627, 37);
		getJContentPane().add(lbStatusObjeto);
		lbStatusObjeto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbStatusObjeto.setText("");
		lbStatusObjeto.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private JPanel getJContentPane() {
		// metodo que constroi os componentes no painel principal
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(224, 236, 255));
			jContentPane.setLayout(null);
			// adicionando botoes no painel
			jContentPane.add(getBtnNovo());
			// adiciona o botao novo no painel
			jContentPane.add(getBtnAlterar());// botao alterar
			jContentPane.add(getBtnExcluir());
			jContentPane.add(getBtnFechar());
			jContentPane.add(getBtnSalvar());
			jContentPane.add(getBtnCancelar());
			jContentPane.add(getBtnBuscar());

		}
		return jContentPane;
	}

	// metodo que constroi o componente
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setBounds(10, 34, 70, 25);
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return txtCodigo;
	}

	// metodo que constroi o componente
	private JTextField getTxtRazaoSocial() {
		if (txtRazaoSocial == null) {
			txtRazaoSocial = new JTextField();
			txtRazaoSocial.setBounds(93, 34, 405, 25);
			txtRazaoSocial.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER || e.getKeyCode() == e.VK_DOWN) {
						txtDataNascimento.requestFocus();
					}
				}
			});
		}
		return txtRazaoSocial;
	}

	// metodo que constroi o botao buscar
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setBounds(567, 8, 0, 0);
			btnBuscar.setMnemonic('B');
			btnBuscar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					txtBusca.requestFocus();
//					txtBusca.selectAll();
				}
			});
		}
		return btnBuscar;
	}

	private void limpa() {// metodo que limpa os campo do formulario
		txtCodigo.setText("");
		txtRazaoSocial.setText("");
		txtDataNascimento.setDate(null);
		txtCpf.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtComplemento.setText("");
		txtBairro.setText("");
		txtCelular.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		txtEmail.setText("");
		txtNomeFantasia.setText("");
		txtRG.setText("");
		txtSite.setText("");
		txtTelefone.setText("");
		txtTelefone2.setText("");
		txtUfrg.setText("");
		txtUf.setText("");

	}

	private void habilita(boolean b) {
		// Metodo que habilita ou desabilita os campos do formulario
		txtCodigo.setEnabled(false);
		txtRazaoSocial.setEnabled(b);
		txtDataNascimento.setEnabled(b);
		txtBairro.setEnabled(b);
		txtCpf.setEnabled(b);
		txtNumero.setEnabled(b);
		txtComplemento.setEnabled(b);
		txtBairro.setEnabled(b);
		txtEndereco.setEnabled(b);
		txtCelular.setEnabled(b);
		txtCep.setEnabled(b);
		txtCidade.setEnabled(b);
		txtEmail.setEnabled(b);
		txtNomeFantasia.setEnabled(b);
		txtRG.setEnabled(b);
		txtSite.setEnabled(b);
		txtTelefone.setEnabled(b);
		txtTelefone2.setEnabled(b);
		txtUfrg.setEnabled(b);
		txtUf.setEnabled(b);
		btnNovo.setVisible(!b);
		btnAlterar.setVisible(!b);
		btnExcluir.setVisible(!b);
		btnFechar.setVisible(!b);
		btnSalvar.setVisible(b);
		btnCancelar.setVisible(b);
		
		checkCliente.setEnabled(b);
		checkFuncionaria.setEnabled(b);
		//txtNome.setEnabled(b);
		txtFilho.setEnabled(b);
		txtCargo.setEnabled(b);
		dataAdmissao.setEnabled(b);
		dataDemissao.setEnabled(b);
		txtSalario.setEnabled(b);
		textObs.setEditable(b);
		
	}

	private void mostra(Cliente objeto) {
		// metodo recebe a instancia de um objeto e � exibido no formulario
		lbStatusObjeto.setText("Exibindo Registro");
		if (objeto.getCodigo() != null) {
			// � inteiro.. tem que por o toString no final
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));
		} else {
			txtCodigo.setText("");
		}
		txtRazaoSocial.setText(objeto.getRazaoSocial());
		txtDataNascimento.setDate(objeto.getDataNascimento());
		txtCpf.setText(objeto.getCpf());
		txtBairro.setText(objeto.getBairro());
		txtEndereco.setText(objeto.getEndereço());
		txtComplemento.setText(objeto.getComplemento());
		txtNumero.setText(objeto.getNumero());
		txtCelular.setText(objeto.getCelular());
		txtCep.setText(objeto.getCep());
		txtCidade.setText(objeto.getCidade());
		txtEmail.setText(objeto.getEmail());
		txtNomeFantasia.setText(objeto.getNomeFatasia());
		txtRG.setText(objeto.getRg());
		txtSite.setText(objeto.getSite());
		txtTelefone.setText(objeto.getTelefone1());
		txtTelefone2.setText(objeto.getTelefone2());
		txtUfrg.setText(objeto.getUfrg());
		txtUf.setText(objeto.getUf());
		
		
	}

	private void mostra() {
		// metodo que exibe os dados no formulario
		try {
			Cliente ob = new ClienteDAO().selecionarUltimoCancel();
			mostra(ob);
		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}

	}

	private void mostra2(Vendedor objeto) {
		// metodo recebe a instancia de um objeto e � exibido no formulario
		lbStatusObjeto.setText("Exibindo Registro");
		if (objeto.getCodigo() != null) {
			// � inteiro.. tem que por o toString no final
			txtCodigo.setText(String.valueOf(objeto.getCodigo().toString()));
		} else {
			txtCodigo.setText("");
		}
		txtRazaoSocial.setText(objeto.getRazaoSocial());
		txtEmail.setText(objeto.getEmail());
		txtCpf.setText(objeto.getCpf());
		txtDataNascimento.setDate(objeto.getDataNascimento());
		txtNome.setText(objeto.getRazaoSocial());
		txtFilho.setText(objeto.getFilhos());
		txtSalario.setText(String.valueOf(objeto.getSalario().toString()));
		txtCargo.setText(objeto.getCargo());
		dataAdmissao.setDate(objeto.getDataAdmissao());
		dataDemissao.setDate(objeto.getDataDemissao());
		textObs.setText(objeto.getObs());
	}

	private void mostra2() {
		// metodo que exibe os dados no formulario
		try {
			Vendedor ob = new VendedorDAO().selecionarUltimoCancel();
			mostra2(ob);
		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}

	}

	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setBounds(10, 667, 150, 35);
			btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNovo.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-mais1-30.png")));

			btnNovo.setText("Novo");
			btnNovo.setMnemonic('N');
			btnNovo.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					btnCliente.setEnabled(false);
					btnFuncionario.setEnabled(false);
					habilita(true);// habilita os campos
					limpa();// limpa o formulario
					editar = false;// fala que n�o ta editando
					txtRazaoSocial.requestFocus();
					// manda o foco para o campo de codigo-
					lbStatusObjeto.setText("Cadastrando Registro");
					btnCliente.setEnabled(true);
					btnFuncionario.setEnabled(true);
				}

			});

		}
		return btnNovo;
	}

	private JButton getBtnAlterar() {
		if (btnAlterar == null) {
			btnAlterar = new JButton();
			btnAlterar.setBounds(170, 667, 150, 35);
			btnAlterar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-alterar-30.png")));
			btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));

			btnAlterar.setText("Alterar ");
			btnAlterar.setMnemonic('A');
			btnAlterar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {
						// pergunta se o campo codigo est� preenchido
						habilita(true);// habilita os campos do formulario
						editar = true;// fala que est� editando
						txtRazaoSocial.requestFocus();
						// manda o foco para o segundo campo
						lbStatusObjeto.setText("Alterando Registro");
					}
					btnCliente.setEnabled(true);
					btnFuncionario.setEnabled(true);
				}
			});
		}
	
		return btnAlterar;
	}

	private JButton getBtnExcluir() {
		if (btnExcluir == null) {
			btnExcluir = new JButton();
			btnExcluir.setBounds(655, 667, 150, 35);
			btnExcluir.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-excluir-30.png")));
			btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
			// btnExcluir.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Delete24.gif")));
			btnExcluir.setText("Excluir ");
			btnExcluir.setMnemonic('E');
			
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (txtCodigo.getText().length() > 0) {

						String motivo = JOptionPane.showInputDialog(TelaCliente.this, "Motivo: ");
						if (motivo != null && motivo.trim().length() > 0) {
							ClienteDAO dao = new ClienteDAO();
							try {
								Cliente ob = getCliente();
								ob.setCancelado("S");
								ob.setCausaCancel(motivo.trim());
								dao.alterar(ob);
								// efetua a exclus�o
								mostra();
								// manda carregar a lista novamente
							} catch (Exception eas) {
							}
						}
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
			btnFechar.setBounds(813, 667, 140, 35);
			btnFechar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));

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
			btnSalvar.setBounds(10, 667, 150, 35);
			btnSalvar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-salvar-30.png")));
			btnSalvar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnCancelar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {
						txtDataNascimento.requestFocus();
					}
				}
			});
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnSalvar.setText("Salvar ");
			btnSalvar.setMnemonic('S');
			btnSalvar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (checkCliente.isSelected()) {

						if (verifica()) {

							ClienteDAO dao = new ClienteDAO();

							if (editar) {
								try {
									Cliente ob = getCliente();
									// pega o objeto para ser alterado
									ob = monta(ob);
									// chama o metodo que poe os dados do
									// formulario
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
								Cliente ob = new Cliente();
								// cria um objeto novo
								ob = monta(ob);// chama o metodo que monta o
												// objeto
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

					if (checkFuncionaria.isSelected()) {

						if (verifica()) {
							// verifica se os campos obrigatorios est�o todos
							// preenchidos
							VendedorDAO dao = new VendedorDAO();
							if (editar) {
								try {
									Vendedor ob = getVendedor();
									// pega o objeto para ser alterado
									ob = monta(ob);
									// chama o metodo que poe os dados do
									// formulario
									// no objeto
									dao.alterar(ob);
									// efetua a altera��o
									editar = false;
									habilita(false);
									// manda desabilitar os campos do formulario
									mostra2(ob);
									// carrega a lista novamente
								} catch (Exception as) {
									JOptionPane.showMessageDialog(null, "Erro");
								}

							} else {
								Vendedor ob = new Vendedor();
								// cria um objeto novo
								ob = monta(ob);// chama o metodo que monta o
												// objeto
								try {
									ob.setCancelado("N");
									dao.cadastrar(ob);// efetua o cadastro
									habilita(false);// desabilita os campos
									mostra2(ob);// carrega a lista
									if (novo) {
										setCadastro(ob);
										dispose();
									}
								} catch (Exception as) {
								}
							}
						}
					}

				}
			});
		}
		return btnSalvar;
	}

	protected Vendedor getVendedor1() {
		// TODO Auto-generated method stub
		return null;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(170, 667, 150, 35);
			btnCancelar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-cancelar-2-30.png")));
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
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
						txtDataNascimento.requestFocus();
					}
				}
			});
		}
		return btnCancelar;
	}

	private Cliente monta(Cliente c) {
		// metodo que monta o objeto para salvar ou alterar
		// passamos os dados do formulario para o objeto
		c.setRazaoSocial(txtRazaoSocial.getText());
		c.setDataNascimento(txtDataNascimento.getDate());
		c.setCpf(txtCpf.getText());
		c.setEndereço(txtEndereco.getText());
		c.setNumero(txtNumero.getText());
		c.setBairro(txtBairro.getText());
		c.setComplemento(txtComplemento.getText());
		c.setCep(txtCep.getText());
		c.setCelular(txtCelular.getText());
		c.setNomeFatasia(txtNomeFantasia.getText());
		c.setRg(txtRG.getText());
		c.setSite(txtSite.getText());
		c.setTelefone1(txtTelefone.getText());
		c.setTelefone2(txtTelefone2.getText());
		c.setUf(txtUf.getText());
		c.setUfrg(txtUfrg.getText());
		c.setCidade(txtCidade.getText());
		c.setEmail(txtEmail.getText());

		return c;
	}

	private Vendedor monta(Vendedor v) {
		// metodo que monta o objeto para salvar ou alterar
		// passamos os dados do formulario para o objeto
		v.setRazaoSocial(txtRazaoSocial.getText());
		v.setDataNascimento(txtDataNascimento.getDate());
		v.setCpf(txtCpf.getText());
		v.setEndereço(txtEndereco.getText());
		v.setNumero(txtNumero.getText());
		v.setBairro(txtBairro.getText());
		v.setComplemento(txtComplemento.getText());
		v.setCep(txtCep.getText());
		v.setCelular(txtCelular.getText());
		v.setNomeFatasia(txtNomeFantasia.getText());
		v.setRg(txtRG.getText());
		v.setSite(txtSite.getText());
		v.setTelefone1(txtTelefone.getText());
		v.setTelefone2(txtTelefone2.getText());
		v.setUf(txtUf.getText());
		v.setUfrg(txtUfrg.getText());
		v.setCidade(txtCidade.getText());
		v.setEmail(txtEmail.getText());
		v.setNome(txtNome.getText());
		v.setFilhos(txtFilho.getText());
		v.setCargo(txtCargo.getText());
		v.setDataAdmissao(dataAdmissao.getDate());
		v.setDataDemissao(dataDemissao.getDate());
		v.setSalario(Double.parseDouble((txtSalario.getText().toString())));
		v.setObs(textObs.getText());
		return v;
	}

	private Cliente getCliente() throws NumberFormatException, Exception {
		return new ClienteDAO().carregarPorID(Integer.parseInt(txtCodigo.getText()));
	}

	private Vendedor getVendedor() throws NumberFormatException, Exception {
		return new VendedorDAO().carregarPorID(Integer.parseInt(txtCodigo.getText()));
	}

	private boolean verifica() {
		// metodo que verifica o preenchimento dos campos
		if (txtRazaoSocial.getText().isEmpty()) {
			txtRazaoSocial.requestFocus();
			JOptionPane.showMessageDialog(null, "O Campo Raz�o Social � obrigat�rio");
			return false;
		}
		if (txtCpf.getText().isEmpty()) {
			txtCpf.requestFocus();
			JOptionPane.showMessageDialog(null, "O campo CPF é obrigatorio");
		}
		if (txtCep.getText().isEmpty()) {
			txtCep.requestFocus();
			JOptionPane.showMessageDialog(null, "O campo CEP é obrigatorio");
		}
		return true;

	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setCadastro(Cliente cadastro) {
		this.cadastro = cadastro;
	}

	public Cliente getCadastro() {
		return cadastro;
	}

	public void setCadastro(Vendedor cadastro) {
		this.cadastro = cadastroV; //o programa funciona perfeitamente com isso, é graça do eclipse
	}

	public Vendedor getCadastroV() {

		return cadastroV;
	}

	private JDateChooser getTxtDataNascimento() {
		if (txtDataNascimento == null) {
			txtDataNascimento = new JDateChooser();
			txtDataNascimento.setBounds(370, 81, 135, 25);
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

	private JLabel getLbDataNascimento() {
		if (lbDataNascimento == null) {
			lbDataNascimento = new JLabel();
			lbDataNascimento.setBounds(369, 62, 92, 25);
			lbDataNascimento.setText("Data Nascimento:");
		}
		return lbDataNascimento;
	}

	private JLabel getLbEndereco() {
		if (lbEndereco == null) {
			lbEndereco = new JLabel("Endereço:");
			lbEndereco.setBounds(127, 29, 51, 14);
		}
		return lbEndereco;
	}

	private JTextField getTxtEndereco() {
		if (txtEndereco == null) {
			txtEndereco = new JTextField();
			txtEndereco.setBounds(128, 45, 356, 25);
			txtEndereco.setColumns(10);
		}
		return txtEndereco;
	}

	private JLabel getLbComplemento() {
		if (lbComplemento == null) {
			lbComplemento = new JLabel("Complemento:");
			lbComplemento.setBounds(15, 83, 70, 14);
		}
		return lbComplemento;
	}

	private JTextField getTxtComplemento() {
		if (txtComplemento == null) {
			txtComplemento = new JTextField();
			txtComplemento.setBounds(15, 98, 293, 25);
			txtComplemento.setHorizontalAlignment(SwingConstants.CENTER);
			txtComplemento.setColumns(10);
		}
		return txtComplemento;
	}

	private JLabel getLbBairro() {
		if (lbBairro == null) {
			lbBairro = new JLabel("Bairro:");
			lbBairro.setBounds(590, 29, 34, 14);
		}
		return lbBairro;
	}

	private JTextField getTxtBairro() {
		if (txtBairro == null) {
			txtBairro = new JTextField();
			txtBairro.setBounds(590, 45, 290, 25);
			txtBairro.setColumns(10);
		}
		return txtBairro;
	}

	private JLabel getLbNumero() {
		if (lbNumero == null) {
			lbNumero = new JLabel("Número:");
			lbNumero.setBounds(494, 29, 46, 14);
		}
		return lbNumero;
	}

	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(494, 45, 86, 25);
			txtNumero.setHorizontalAlignment(SwingConstants.CENTER);
			txtNumero.setColumns(10);
		}
		return txtNumero;
	}

	private JLabel getLbCpf() {
		if (lbCpf == null) {
			lbCpf = new JLabel("<html><font color=blue>*</font>CPF:</html>");
			lbCpf.setBounds(10, 67, 46, 14);
		}
		return lbCpf;
	}

	private JTextField getTxtCpf() {
		if (txtCpf == null) {
			txtCpf = new JTextField();
			try {

				javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");

				txtCpf = new javax.swing.JFormattedTextField(format_textField4);

			} catch (Exception e) {
			}
			txtCpf.setBounds(10, 81, 140, 25);
			txtCpf.setColumns(10);

		}

		return txtCpf;
	}

	private JPanel getPainelClientes() {
		if (painelClientes == null) {
			painelClientes = new JPanel();
			painelClientes.setBounds(0, 0, 972, 69);
			painelClientes.setBackground(SystemColor.textHighlight);
			painelClientes.setLayout(null);
			painelClientes.add(getLbCliente());
		}
		return painelClientes;
	}

	private JLabel getLbCliente() {
		if (lbCliente == null) {
			lbCliente = new JLabel("Cadastro");
			lbCliente.setForeground(Color.WHITE);
			lbCliente.setBackground(Color.WHITE);
			lbCliente.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbCliente.setBounds(433, 11, 133, 46);
		}
		return lbCliente;
	}

	private JLabel getLbNomeFantasia() {
		if (lbNomeFantasia == null) {
			lbNomeFantasia = new JLabel("Nome Fantasia / Apelido:");
			lbNomeFantasia.setBounds(511, 16, 135, 14);
		}
		return lbNomeFantasia;
	}

	private JTextField getTxtNomeFantasia() {
		if (txtNomeFantasia == null) {
			txtNomeFantasia = new JTextField();
			txtNomeFantasia.setBounds(511, 34, 370, 25);
			txtNomeFantasia.setColumns(10);
		}
		return txtNomeFantasia;
	}

	private JLabel getLbRG() {
		if (lbRG == null) {
			lbRG = new JLabel("RG:");
			lbRG.setBounds(160, 67, 46, 14);
		}
		return lbRG;
	}

	private JTextField getTxtRG() {
		if (txtRG == null) {
			txtRG = new JTextField();
			//mascara rg
			try {

				javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("##.###.###-#");

				txtRG = new javax.swing.JFormattedTextField(format_textField3);

			} catch (Exception e) {
			}
			txtRG.setBounds(160, 81, 135, 25);
			txtRG.setColumns(10);
		}
		return txtRG;
	}

	private JLabel getLbUFrg() {
		if (lbUFrg == null) {
			lbUFrg = new JLabel("UF RG:");
			lbUFrg.setBounds(305, 67, 46, 14);
		}
		return lbUFrg;
	}

	private JTextField getTxtUfrg() {
		if (txtUfrg == null) {
			txtUfrg = new JTextField();
			txtUfrg.setHorizontalAlignment(SwingConstants.CENTER);
			txtUfrg.setBounds(305, 81, 55, 25);
			txtUfrg.setColumns(10);
		}
		return txtUfrg;
	}

	private JLabel getLbTelefone() {
		if (lbTelefone == null) {
			lbTelefone = new JLabel("Telefone:");
			lbTelefone.setBounds(515, 67, 51, 14);
		}
		return lbTelefone;
	}

	private JTextField getTxtTelefone() {
		if (txtTelefone == null) {
			txtTelefone = new JTextField();
			//mascara do telefone
			try {

				javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("(##)####-####");

				txtTelefone = new javax.swing.JFormattedTextField(format_textField3);

			} catch (Exception e) {
			}
			txtTelefone.setBounds(515, 81, 113, 25);
			txtTelefone.setColumns(10);
		}
		return txtTelefone;
	}

	private JLabel getLbTelefone2() {
		if (lbTelefone2 == null) {
			lbTelefone2 = new JLabel("Telefone 02:");
			lbTelefone2.setBounds(638, 67, 70, 14);
		}
		return lbTelefone2;
	}

	private JTextField getTxtTelefone2() {
		if (txtTelefone2 == null) {
			txtTelefone2 = new JTextField();
			//mascara do telefone2
			try {

				javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("(##)####-####");

				txtTelefone2 = new javax.swing.JFormattedTextField(format_textField3);

			} catch (Exception e) {
			}
			
			txtTelefone2.setBounds(638, 81, 113, 25);
			txtTelefone2.setColumns(10);
		}
		return txtTelefone2;
	}

	private JLabel getLbCelular() {
		if (lbCelular == null) {
			lbCelular = new JLabel("Celular:");
			lbCelular.setBounds(760, 67, 46, 14);
		}
		return lbCelular;
	}

	private JTextField getTxtCelular() {
		if (txtCelular == null) {
			txtCelular = new JTextField();
			// mascara do celular
			try {
				javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("(##)#####-####");

				txtCelular = new javax.swing.JFormattedTextField(format_textField3);

			} catch (Exception e) {
			}

			txtCelular.setBounds(761, 81, 117, 25);
			txtCelular.setColumns(10);
		}
		return txtCelular;
	}

	private JLabel getLbCep() {
		if (lbCep == null) {
			lbCep = new JLabel("<html><font color=blue>*</font>CEP:</html>");
			lbCep.setBounds(15, 29, 29, 14);
		}
		return lbCep;
	}

	private JTextField getTxtCep() {
		if (txtCep == null) {
			txtCep = new JTextField();
			// mascara do cep
			try {
				txtCep = new JFormattedTextField(new MaskFormatter("#####-###"));
			} catch (Exception excp) {
				// algum problema pode ocorrer aqui
			}

			// A partir do enter busca o endereco completo
			txtCep.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						Cliente obj = new Cliente();
						ClienteDAO dao = new ClienteDAO();
						obj = dao.buscaCep(txtCep.getText());
						txtEndereco.setText(obj.getEndereço());
						txtBairro.setText(obj.getBairro());
						txtCidade.setText(obj.getCidade());
						txtUf.setText(obj.getUf());
						System.out.println(obj.getUf());

					}
				}
			});

			txtCep.setHorizontalAlignment(SwingConstants.CENTER);
			txtCep.setBounds(15, 45, 103, 25);
			txtCep.setColumns(10);
		}
		return txtCep;
	}

	private JLabel getLbCidade() {
		if (lbCidade == null) {
			lbCidade = new JLabel("Cidade:");
			lbCidade.setBounds(317, 83, 46, 14);
		}
		return lbCidade;
	}

	private JTextField getTxtCidade() {
		if (txtCidade == null) {
			txtCidade = new JTextField();
			txtCidade.setBounds(318, 98, 447, 25);
			txtCidade.setHorizontalAlignment(SwingConstants.CENTER);
			txtCidade.setColumns(10);
		}
		return txtCidade;
	}

	private JLabel getLbUF() {
		if (lbUF == null) {
			lbUF = new JLabel("UF:");
			lbUF.setBounds(773, 83, 46, 14);
		}
		return lbUF;
	}

	private JTextField getTxtUf() {
		if (txtUf == null) {
			txtUf = new JTextField();
			txtUf.setBounds(775, 98, 105, 25);
			txtUf.setHorizontalAlignment(SwingConstants.CENTER);
			txtUf.setColumns(10);
		}
		return txtUf;
	}

	private JLabel getLbEmail() {
		if (lbEmail == null) {
			lbEmail = new JLabel("E-mail:");
			lbEmail.setBounds(15, 23, 46, 14);
		}
		return lbEmail;
	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setBounds(15, 36, 403, 25);
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}

	private JLabel getLbSite() {
		if (lbSite == null) {
			lbSite = new JLabel("Site:");
			lbSite.setBounds(427, 23, 46, 14);
		}
		return lbSite;
	}

	private JTextField getTxtSite() {
		if (txtSite == null) {
			txtSite = new JTextField();
			txtSite.setBounds(428, 36, 454, 25);
			txtSite.setColumns(10);
		}
		return txtSite;
	}

	private JCheckBox getCheckCliente() {
		if (checkCliente == null) {
			checkCliente = new JCheckBox("Cliente");
			checkCliente.setBounds(24, 18, 97, 23);
			checkCliente.setBackground(new Color(224, 236, 255));
			checkCliente.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						checkCliente.setSelected(false);
						buttonGroup.clearSelection();
						btnCliente.setEnabled(false);
					} 
					else {
						btnCliente.setEnabled(true);
					}
				}
			});
			buttonGroup.add(checkCliente);
		}
		return checkCliente;
	}

	private JCheckBox getCheckFuncionaria() {
		if (checkFuncionaria == null) {
			checkFuncionaria = new JCheckBox("Funcionario");
			checkFuncionaria.setBounds(170, 7, 97, 44);
			checkFuncionaria.setBackground(new Color(224, 236, 255));
			checkFuncionaria.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						checkFuncionaria.setSelected(false);
						tabbedPane.setEnabledAt(1, false);
						buttonGroup.clearSelection();
						btnFuncionario.setEnabled(false);
						painelFuncionario.setEnabled(false);
					} else {
						painelFuncionario.setEnabled(true);
						tabbedPane.setEnabledAt(1, true);
						btnFuncionario.setEnabled(true);
					}
				}
				
			});
			buttonGroup.add(checkFuncionaria);
		}
		return checkFuncionaria;
	}

	private JButton getBtnCliente() {
		if (btnCliente == null) {
			btnCliente = new JButton("Clientes");
			btnCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-cliente-de-pesquisa-25.png")));
			btnCliente.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnCliente.setToolTipText("Consulta de Clientes");
			btnCliente.setBounds(330, 667, 150, 35);
			btnCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnCliente.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					// new TelaConsultaCliente().setVisible(true);
					TelaConsultaCliente tela = new TelaConsultaCliente(TelaCliente.this);
//					tela.setTexto(txtBusca.getText());
					tela.setVisible(true);
					if (tela.getRetorno() != null) {
						try {
							mostra(new ClienteDAO().carregarPorID(Integer.parseInt(tela.getRetorno())));
//							txtBusca.setText("");
						} catch (NumberFormatException txtBusca) {
							txtBusca.printStackTrace();
						} catch (Exception txtBusca) {
							txtBusca.printStackTrace();
						}
					}
					checkCliente.setEnabled(true);
					checkCliente.setSelected(true);
					checkCliente.setEnabled(false);
					tabbedPane.setEnabledAt(0, true);

				}
			});
		}
		return btnCliente;
	}

	private JButton getBtnFuncionario() {
		if (btnFuncionario == null) {
			btnFuncionario = new JButton("Funcionarios");
			btnFuncionario.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/crud/img/icons8-trabalhador-25.png")));
			btnFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnFuncionario.setToolTipText("Consulta de Funcionarios");
			btnFuncionario.setBounds(490, 668, 150, 35);
			btnFuncionario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnFuncionario.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					// new TelaConsultaVendedor().setVisible(true);

					TelaConsultaVendedor tela = new TelaConsultaVendedor(TelaCliente.this);
//					tela.setTexto(txtBusca.getText());
					tela.setVisible(true);
					
					
					if (tela.getRetorno() != null) {
						try {
							mostra2(new VendedorDAO().carregarPorID(Integer.parseInt(tela.getRetorno())));
//							txtBusca.setText("");
						} catch (NumberFormatException txtBusca) {
							txtBusca.printStackTrace();
						} catch (Exception txtBusca) {
							txtBusca.printStackTrace();
						}
						
					}
					checkFuncionaria.setEnabled(true);
					checkFuncionaria.setSelected(true);
					checkFuncionaria.setEnabled(false);
					tabbedPane.setEnabledAt(1, true);
					
				}
			});
			btnFuncionario.setEnabled(true);
		}
		return btnFuncionario;
	}

	// funcao para arrumar os checkbox do cartao
	private void funcButton() {
		if (checkCliente.isSelected()) {
			btnCliente.setEnabled(true);
		}
		if (checkFuncionaria.isSelected()) {
			btnFuncionario.setEnabled(true);
		}
	}

	private JPanel getPainelDadosPessoais() {
		if (painelDadosPessoais == null) {
			painelDadosPessoais = new JPanel();
			painelDadosPessoais.setBounds(10, 22, 893, 117);
			painelDadosPessoais.setBackground(new Color(224, 236, 255));
			painelDadosPessoais.setBorder(new TitledBorder(null, "Dados Pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
			painelDadosPessoais.setLayout(null);
			painelDadosPessoais.add(getTxtCelular());
			painelDadosPessoais.add(getLbCelular());
			painelDadosPessoais.add(getTxtTelefone2());
			painelDadosPessoais.add(getLbTelefone2());
			painelDadosPessoais.add(getTxtTelefone());
			painelDadosPessoais.add(getLbTelefone());
			painelDadosPessoais.add(getTxtDataNascimento());
			painelDadosPessoais.add(getLbDataNascimento());
			painelDadosPessoais.add(getTxtUfrg());
			painelDadosPessoais.add(getLbUFrg());
			painelDadosPessoais.add(getTxtRG());
			painelDadosPessoais.add(getLbRG());
			painelDadosPessoais.add(getTxtCpf());
			painelDadosPessoais.add(getLbCpf());
			painelDadosPessoais.add(getTxtCodigo());
			lbCodigo = new JLabel();
			lbCodigo.setBounds(10, 11, 45, 25);
			painelDadosPessoais.add(lbCodigo);
			lbCodigo.setText("C\u00F3digo:");
			painelDadosPessoais.add(getLbNomeFantasia());
			painelDadosPessoais.add(getTxtRazaoSocial());
			// adicionando o campo de texto no painel
			lbRazaoSocial = new JLabel();
			lbRazaoSocial.setBounds(93, 11, 70, 25);
			painelDadosPessoais.add(lbRazaoSocial);
			lbRazaoSocial.setText("<html><font color=blue>*</font>Razão Social:</html>");
			painelDadosPessoais.add(getTxtNomeFantasia());
		}
		return painelDadosPessoais;
	}

	private JPanel getPainelEndereco() {
		if (painelEndereco == null) {
			painelEndereco = new JPanel();
			painelEndereco.setBounds(10, 160, 893, 134);
			painelEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
			painelEndereco.setBackground(new Color(224, 236, 255));
			painelEndereco.setLayout(null);
			painelEndereco.add(getLbCep());
			painelEndereco.add(getLbComplemento());
			painelEndereco.add(getTxtBairro());
			painelEndereco.add(getTxtUf());
			painelEndereco.add(getLbUF());
			painelEndereco.add(getTxtCidade());
			painelEndereco.add(getLbCidade());
			painelEndereco.add(getTxtComplemento());
			painelEndereco.add(getLbBairro());
			painelEndereco.add(getTxtNumero());
			painelEndereco.add(getLbNumero());
			painelEndereco.add(getTxtEndereco());
			painelEndereco.add(getLbEndereco());
			painelEndereco.add(getTxtCep());
		}
		return painelEndereco;
	}

	private JPanel getPainelContatoEletronico() {
		if (painelContatoEletronico == null) {
			painelContatoEletronico = new JPanel();
			painelContatoEletronico.setBounds(10, 305, 893, 82);
			painelContatoEletronico.setBackground(new Color(224, 236, 255));
			painelContatoEletronico.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Endere\u00E7o Eletr\u00F4nico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
			painelContatoEletronico.setLayout(null);
			painelContatoEletronico.add(getTxtEmail());
			painelContatoEletronico.add(getLbEmail());
			painelContatoEletronico.add(getTxtSite());
			painelContatoEletronico.add(getLbSite());
		}
		return painelContatoEletronico;
	}

	private JPanel getPainelTipoCadastro() {
		if (painelTipoCadastro == null) {
			painelTipoCadastro = new JPanel();
			painelTipoCadastro.setBounds(10, 398, 893, 58);
			painelTipoCadastro.setBackground(new Color(224, 236, 255));
			painelTipoCadastro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
			painelTipoCadastro.setLayout(null);
			painelTipoCadastro.add(getCheckFuncionaria());
			painelTipoCadastro.add(getCheckCliente());
		}
		return painelTipoCadastro;
	}

	private JPanel getPainelPrincipal() {
		if (painelPrincipal == null) {
			painelPrincipal = new JPanel();
			painelPrincipal.setBounds(10, 11, 917, 544);
			painelPrincipal.setBackground(new Color(224, 236, 255));
			painelPrincipal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
			painelPrincipal.setLayout(null);
			painelPrincipal.add(getPainelDadosPessoais());
			painelPrincipal.add(getPainelEndereco());
			painelPrincipal.add(getPainelContatoEletronico());
			painelPrincipal.add(getPainelTipoCadastro());
		}
		return painelPrincipal;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(10, 100, 943, 528);
			jContentPane.setBackground(new Color(224, 236, 255));
			tabbedPane.addTab("Cadastro", null, getPainelCadastro(), null);
			tabbedPane.addTab("Funcionario", null, getPainelFuncionario(), "Dados do Funcionario");
		}
		return tabbedPane;
	}
	private JPanel getPainelCadastro() {
		if (painelCadastro == null) {
			painelCadastro = new JPanel();
			
			painelCadastro.setBackground(new Color(224, 236, 255));
			painelCadastro.setLayout(null);
			painelCadastro.add(getPainelPrincipal());
		}
		return painelCadastro;
	}
	private JPanel getPainelFuncionario() {
		if (painelFuncionario == null) {
			painelFuncionario = new JPanel();
			
			painelFuncionario.setBackground(new Color(224, 236, 255));
			painelFuncionario.setLayout(null);
			painelFuncionario.add(getPainelDadosFuncionarios());
			painelFuncionario.add(getPainelConfigFuncionario());
		}
		return painelFuncionario;
	}
	private JPanel getPainelDadosFuncionarios() {
		if (painelDadosFuncionarios == null) {
			painelDadosFuncionarios = new JPanel();
			painelDadosFuncionarios.setLayout(null);
			painelDadosFuncionarios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Funcion\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
			painelDadosFuncionarios.setBounds(10, 21, 926, 130);
			painelDadosFuncionarios.setBackground(new Color(224, 236, 255));
			painelDadosFuncionarios.add(getDataAdmissao());
			painelDadosFuncionarios.add(getLbAdmissao());
			painelDadosFuncionarios.add(getDataDemissao());
			painelDadosFuncionarios.add(getLbDemissao());
			painelDadosFuncionarios.add(getTxtNome());
			painelDadosFuncionarios.add(getLbNome());
			painelDadosFuncionarios.add(getTxtCargo());
			painelDadosFuncionarios.add(getLbCargo());
			painelDadosFuncionarios.add(getTxtSalario());
			painelDadosFuncionarios.add(getLbSalario());
			painelDadosFuncionarios.add(getTxtFilho());
			painelDadosFuncionarios.add(getLbFilhos());
		}
		return painelDadosFuncionarios;
	}
	private JDateChooser getDataAdmissao() {
		if (dataAdmissao == null) {
			dataAdmissao = new JDateChooser();
			dataAdmissao.setToolTipText("Data de começo de trabalho");
			dataAdmissao.setBounds(21, 88, 125, 25);
		}
		return dataAdmissao;
	}
	private JLabel getLbAdmissao() {
		if (lbAdmissao == null) {
			lbAdmissao = new JLabel("<html><font color=blue>*</font>Admissão:</html>");
			lbAdmissao.setBounds(21, 69, 66, 14);
		}
		return lbAdmissao;
	}
	private JDateChooser getDataDemissao() {
		if (dataDemissao == null) {
			dataDemissao = new JDateChooser();
			dataDemissao.setToolTipText("Data de demissão");
			dataDemissao.setBounds(156, 88, 120, 25);
		}
		return dataDemissao;
	}
	private JLabel getLbDemissao() {
		if (lbDemissao == null) {
			lbDemissao = new JLabel("Demissão:");
			lbDemissao.setBounds(156, 69, 54, 14);
		}
		return lbDemissao;
	}
	private JTextField getTxtNome() {
		if (txtNome == null) {
			txtNome = new JTextField();
			txtNome.setToolTipText("Nome do funcionario");
			txtNome.setColumns(10);
			txtNome.setBounds(21, 35, 421, 25);
		}
		return txtNome;
	}
	private JLabel getLbNome() {
		if (lbNome == null) {
			lbNome = new JLabel("<html><font color=blue>*</font>Nome:</html>");
			lbNome.setBounds(21, 21, 46, 14);
		}
		return lbNome;
	}
	private JTextField getTxtCargo() {
		if (txtCargo == null) {
			txtCargo = new JTextField();
			txtCargo.setColumns(10);
			txtCargo.setBounds(452, 35, 188, 25);
		}
		return txtCargo;
	}
	private JLabel getLbCargo() {
		if (lbCargo == null) {
			lbCargo = new JLabel("Cargo:");
			lbCargo.setBounds(456, 21, 46, 14);
		}
		return lbCargo;
	}
	private JTextField getTxtSalario() {
		if (txtSalario == null) {
			txtSalario = new JTextField();
			txtSalario.setColumns(10);
			txtSalario.setBounds(650, 35, 144, 25);
		}
		return txtSalario;
	}
	private JLabel getLbSalario() {
		if (lbSalario == null) {
			lbSalario = new JLabel("Salário:");
			lbSalario.setBounds(650, 21, 46, 14);
		}
		return lbSalario;
	}
	private JTextField getTxtFilho() {
		if (txtFilho == null) {
			txtFilho = new JTextField();
			txtFilho.setToolTipText("Quantidade de filhos do funcionario");
			txtFilho.setColumns(10);
			txtFilho.setBounds(804, 35, 86, 25);
		}
		return txtFilho;
	}
	private JLabel getLbFilhos() {
		if (lbFilhos == null) {
			lbFilhos = new JLabel("<html><font color=blue>*</font>Filhos:</html>");
			lbFilhos.setBounds(804, 21, 46, 14);
		}
		return lbFilhos;
	}
	private JPanel getPainelConfigFuncionario() {
		if (painelConfigFuncionario == null) {
			painelConfigFuncionario = new JPanel();
			painelConfigFuncionario.setBackground(new Color(224, 236, 255));
			
			painelConfigFuncionario.setLayout(null);
			painelConfigFuncionario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Configura\u00E7\u00E3o do Funcion\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
			painelConfigFuncionario.setBounds(10, 178, 926, 120);
			
			painelConfigFuncionario.add(getLbObs());
			painelConfigFuncionario.add(getTextObs());
		}
		return painelConfigFuncionario;
	}
	private JLabel getLbObs() {
		if (lbObs == null) {
			lbObs = new JLabel("Observação:");
			lbObs.setBounds(10, 23, 66, 14);
		}
		return lbObs;
	}
	private JTextPane getTextObs() {
		if (textObs == null) {
			textObs = new JTextPane();
			textObs.setForeground(Color.BLACK);
			textObs.setCaretColor(Color.BLACK);
			textObs.setBackground(SystemColor.control);
			textObs.setBounds(10, 37, 906, 72);
		}
		return textObs;
	}
}
