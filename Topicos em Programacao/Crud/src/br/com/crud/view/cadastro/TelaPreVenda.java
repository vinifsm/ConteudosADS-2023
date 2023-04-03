package br.com.crud.view.cadastro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.towel.swing.table.TableFilter;

import br.com.crud.dao.ClienteDAO;
//import br.com.crud.dao.ContaReceberDAO;
import br.com.crud.dao.ItemVendaDAO;
import br.com.crud.dao.ProdutoDAO;
import br.com.crud.dao.VendedorDAO;
import br.com.crud.dao.VendaDAO;
import br.com.crud.dao.PreVendaDAO;
import br.com.crud.model.Cliente;
//import br.com.crud.model.ContaReceber;
import br.com.crud.model.ItemVenda;
import br.com.crud.model.Produto;
import br.com.crud.model.Vendedor;
import br.com.crud.model.Venda;
import br.com.crud.model.PreVenda;
import br.com.crud.util.ManipulaValor;
import br.com.crud.view.consulta.TelaConsultaCliente;
import br.com.crud.view.consulta.TelaConsultaProduto;
import br.com.crud.view.consulta.TelaConsultaVendedor;
import br.com.crud.view.pagamento.TelaPagamento;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;

//classe extend de JDialog que � uma classe com padr�es de tela visual
public class TelaPreVenda extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbPreVenda = null;
	private JLabel lbCliente = null;
	private JLabel lbVendedor = null;
	private JTextField txtVendedor = null;
	private JButton btnNovo = null;
	private JButton btnFechar = null;
	private JButton btnSalvar = null;
	private JButton btnCancelar = null;
	private boolean editar = false;
	private Boolean novo = false;
	private PreVenda cadastro = null;
	private JTextField txtCliente;
	private JLabel lbClienteNome;
	private JLabel lbItem;
	private JTextField txtItem;
	private JLabel lbQuantidade;
	private JTextField txtQtd;
	private JLabel lbValor;
	private JLabel lbValorTotal;
	private JLabel lbProduto;
	private JLabel lbValorItem;
	private JTextField txtVenda;
	private int total;
	private ObjectTableModel<ItemVenda> model = null;
	private TableFilter filtro = null;
	private String texto = null;
	private JButton btnAdiciona;
	PreVenda pre = new PreVenda();
	Produto prod = new Produto();
	private List<Venda> lista = null;
	private JLabel lbValorVenda;
	private Double somaVenda = 0.0;
	private Double somaItens = 0.0;
	private JLabel lbQtdItens;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lbNomeVendedor;
	private JLabel lbTotal;
	private JPanel painelCarinho;
	private JLabel lbCarrinho;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lbDesconto;
	private JTextField txtDesconto;
	
	DefaultTableModel carrinho;
	
	/**
	 * @wbp.parser.constructor
	 */
	public TelaPreVenda(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaPreVenda(JDialog owner) {
		// Construtor da classe
		super(owner);
		// Construtor da classe que estamos extendendo... no caso JDialog
		initialize();
		// chama o metodo que inicializa os componentes
	}

	public TelaPreVenda() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		// metodo que inicializa os componentes
		this.setSize(633, 553);// tamanho da tela
		this.setTitle("Cadastro de pre-venda");
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
				habilita(false);
				mostra();

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
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "F5");
		getRootPane().getActionMap().put("F5", new AbstractAction("F5") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (btnSalvar.isVisible()) {
					btnSalvar.doClick();
				}
			}
		});
		getJContentPane().add(getLbValorVenda());
		getJContentPane().add(getLbQtdItens());
		getJContentPane().add(getLblNewLabel());
		getJContentPane().add(getLblNewLabel_1());
		getJContentPane().add(getLbNomeVendedor());
		getJContentPane().add(getLbTotal());
		getJContentPane().add(getPainelCarinho());
		getJContentPane().add(getScrollPane());
		getJContentPane().add(getLbDesconto());
		getJContentPane().add(getTxtDesconto());
	}

	@SuppressWarnings("unchecked")
	private void carregaLista() {
		//String texto = "";
		VendaDAO dao = new VendaDAO();
		try {
			
			List lista = dao.buscaSimples(texto);
			carregaTable(lista);
		} catch (Exception busca) {
			busca.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	private void carregaTable(List lista) {
		total = lista.size();
		AnnotationResolver resolver = new AnnotationResolver(ItemVenda.class);
		model = new ObjectTableModel<ItemVenda>(resolver, "produto.codigo,produto.nomeProduto,quantidadeItens,produto.valor,");
		model.setData(lista);
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(69);
		table.getColumnModel().getColumn(1).setPreferredWidth(63);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(3).setPreferredWidth(146);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		
		if (table.getRowCount() > 0) {
			table.changeSelection(0, 0, false, false);
			table.requestFocus();
		}
		
		table.updateUI();
	}

	private JPanel getJContentPane() {
		// metodo que constroi os componentes no painel principal
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(224, 236, 255));
			jContentPane.setLayout(null);
			lbVendedor = new JLabel();
			lbVendedor.setBounds(323, 88, 275, 25);
			lbVendedor.setText("Vendedor: ");
			lbVendedor.setDisplayedMnemonic('B');
			jContentPane.add(lbVendedor);
			lbCliente = new JLabel();
			lbCliente.setBounds(10, 66, 55, 25);
			lbCliente.setText("Cliente: ");
			jContentPane.add(lbCliente);
			jContentPane.add(getTxtVendedor());
			jContentPane.add(getTxtCliente(), null);
			jContentPane.add(getBtnNovo());
			jContentPane.add(getBtnFechar());
			jContentPane.add(getBtnSalvar());
			jContentPane.add(getBtnCancelar());
			jContentPane.add(getTxtCliente());
			jContentPane.add(getLbVendedor());
			jContentPane.add(getLbClienteNome());
			jContentPane.add(getLbItem());
			jContentPane.add(getTxtItem());
			jContentPane.add(getLbQuantidade());
			jContentPane.add(getTxtQtd());
			jContentPane.add(getLbValor());
			jContentPane.add(getLbValorTotal());
			jContentPane.add(getLbProduto());
			jContentPane.add(getLbValorItem());
			jContentPane.add(getBtnAdiciona());
		}
		return jContentPane;
	}

	private JTextField getTxtVendedor() {
		// campo busca
		if (txtVendedor == null) {
			txtVendedor = new JTextField();
			txtVendedor.setToolTipText("Codigo Vendedor");
			txtVendedor.setHorizontalAlignment(SwingConstants.CENTER);
			txtVendedor.setBounds(279, 88, 34, 25);
			// EVENTO PARA CONSULTAR OS VendedorS
			txtVendedor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						TelaConsultaVendedor tela1 = new TelaConsultaVendedor(TelaPreVenda.this);
						tela1.setTexto(txtVendedor.getText());
						tela1.setVisible(true);
						VendedorDAO dao = new VendedorDAO();
						if (tela1.getRetorno() != null) {
							try {
								Vendedor u = dao.carregarPorID(Integer.parseInt(tela1.getRetorno()));
								mostra(u);
								txtVendedor.setText(u.getCodigo().toString());
							} catch (NumberFormatException txtBusca) {
								txtBusca.printStackTrace();
							} catch (Exception txtBusca) {
								txtBusca.printStackTrace();
							}
						}
					}
				}
			});
		}
		return txtVendedor;
	}

	private void limpa() {// metodo que limpa os campo do formulario
		txtVendedor.setText("");
		txtCliente.setText("");

		lbClienteNome.setText("");
		lbVendedor.setText("");
		txtItem.setText("");
		lbProduto.setText("");
		txtQtd.setText("0");
		lbValorItem.setText("");
		lbValorTotal.setText("");
		lbQtdItens.setText("0");
		lbValorVenda.setText("0,0");
	}

	private void habilita(boolean b) {
		// Metodo que habilita ou desabilita os campos do formulario
		txtVendedor.setVisible(b);
		txtCliente.setVisible(b);

		btnNovo.setVisible(!b);
		btnFechar.setVisible(!b);
		btnSalvar.setVisible(b);
		btnCancelar.setVisible(b);
	}

	private void mostra(PreVenda objeto) {

		if (objeto.getVendedor() != null) {
			txtVendedor.setText(objeto.getVendedor().getCodigo().toString());
			lbVendedor.setText(objeto.getVendedor().getRazaoSocial());
		} else {
			txtVendedor.setText("");
		}
		if (objeto.getCliente() != null) {
			txtCliente.setText(String.valueOf(objeto.getCliente().getCodigo().toString()));
			lbClienteNome.setText(objeto.getCliente().getRazaoSocial());
		} else {
			txtCliente.setText("");
		}
	}

	private void mostra() {
		try {
			limpa();
		} catch (Exception e) {
			e.printStackTrace();
			btnNovo.doClick();
		}

	}

	private void mostra(Vendedor objeto) {
		if (objeto.getCodigo() != null) {
			txtVendedor.setText(objeto.getCodigo().toString());
		} else {
			txtVendedor.setText("");
		}
		lbVendedor.setText(objeto.getRazaoSocial());
	}

	private void mostra(Cliente objeto) {
		if (objeto.getCodigo() != null) {
			txtCliente.setText(objeto.getCodigo().toString());
		} else {
			txtCliente.setText("");
		}
		lbClienteNome.setText(objeto.getRazaoSocial());
	}

	private void mostra(Produto objeto) {
		if (objeto.getCodigo() != null) {
			txtItem.setText(objeto.getCodigo().toString());
		} else {
			txtItem.setText("");
		}
		ManipulaValor mani = new ManipulaValor();
		lbProduto.setText(objeto.getNomeProduto());
		lbValorItem.setText(mani.formataValortoString(objeto.getValor()));
	}

	private JButton getBtnNovo() {
		if (btnNovo == null) {
			btnNovo = new JButton();
			btnNovo.setToolTipText("Realizar nova venda");
			btnNovo.setForeground(Color.BLACK);
			btnNovo.setIcon(new ImageIcon(TelaPreVenda.class.getResource("/br/com/crud/img/icons8-mais1-30.png")));
			btnNovo.setBounds(10, 466, 150, 35);
			btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			// btnNovo.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Plus24.gif")));
			btnNovo.setText(" Venda");
			btnNovo.setMnemonic('N');
			btnNovo.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					habilita(true);// habilita os campos
					limpa();// limpa o formulario
					editar = false;// fala que n�o ta editando
					txtCliente.requestFocus();
				}
			});
		}
		return btnNovo;
	}

	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton();
			btnFechar.setToolTipText("Fechar Tela");
			btnFechar.setIcon(new ImageIcon(TelaPreVenda.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setBounds(467, 466, 150, 35);
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			// btnFechar.setIcon(new
			// ImageIcon(TelaPreVenda.class.getResource("/icones/alertacancela.png")));
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
			btnSalvar.setIcon(new ImageIcon(TelaPreVenda.class.getResource("/br/com/crud/img/icons8-adicionar-o-carrinho-de-compras-30.png")));
			btnSalvar.setBounds(10, 465, 150, 35);
			btnSalvar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnCancelar.requestFocus();
					} else if (e.getKeyCode() == e.VK_UP) {
						txtVendedor.requestFocus();
					}
				}
			});
			
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			btnSalvar.setText("Finalizar");
			btnSalvar.setMnemonic('S');
			btnSalvar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ManipulaValor mani = new ManipulaValor();
					TelaPagamento tela = new TelaPagamento(TelaPreVenda.this);
					tela.setTexto(lbValorVenda.getText());
					tela.setCodigoCli(txtCliente.getText());
					tela.setCodigoVendedor(txtVendedor.getText());
					tela.setQtdItens(Double.parseDouble(lbQtdItens.getText()));
					tela.setValorVenda(mani.formataStringtoValor(lbValorTotal.getText()));
					tela.setVisible(true);
					if (verifica()) {
						// // verifica se os campos obrigatorios est�o todos
						// // preenchidos
						PreVendaDAO dao = new PreVendaDAO();
						ItemVendaDAO dao1 = new ItemVendaDAO();
						// PreVenda ob = new PreVenda();
						try {
							PreVenda pre = dao.selecionarUltimo();
							for (int i = 0; i < table.getRowCount(); i++) {
								ItemVenda itemV = model.getValue(i);
								itemV.setPreVenda(pre);
								itemV.setCancelado("N");
								dao1.cadastrar(itemV);
							}
							dispose();
						} catch (Exception as) {
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
			btnCancelar.setIcon(new ImageIcon(TelaPreVenda.class.getResource("/br/com/crud/img/icons8-cancelar-2-30.png")));
			btnCancelar.setBounds(467, 465, 150, 35);
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			// btnCancelar.setIcon(new
			// ImageIcon(getClass().getResource("/icones/Cancel24.gif"))); 28x28
			// pixel
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
						txtVendedor.requestFocus();
					}
				}
			});
		}
		return btnCancelar;
	}

	// AQUI � SETADO TODOS OS CAMPOS PARA POPULAR O BD
	private PreVenda monta(PreVenda pre) {
		VendedorDAO u = new VendedorDAO();
		ClienteDAO cli = new ClienteDAO();
		ManipulaValor mani = new ManipulaValor();
		Calendar cal = Calendar.getInstance();
		Date data = cal.getTime();
		try {
			Vendedor uNovo = u.carregarPorID(Integer.parseInt(txtVendedor.getText()));
			pre.setVendedor(uNovo);
			Cliente cliNovo = cli.carregarPorID(Integer.parseInt(txtCliente.getText()));
			pre.setCliente(cliNovo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pre.setQtdItens(Double.parseDouble(lbQtdItens.getText()));
		pre.setValorTotal(mani.formataStringtoValor(lbValorVenda.getText()));
		pre.setDataVenda(data);
		return pre;
	}

	
	private boolean verifica() {
		// metodo que verifica o preenchimento dos campos
		if (txtVendedor.getText().isEmpty()) {
			txtVendedor.requestFocus();
			JOptionPane.showMessageDialog(null, "O Campo Vendedor e obrigat�rio");
			return false;
		}
		return true;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setCadastro(PreVenda cadastro) {
		this.cadastro = cadastro;
	}

	public PreVenda getCadastro() {
		return cadastro;
	}

	private JTextField getTxtCliente() {
		if (txtCliente == null) {
			txtCliente = new JTextField();
			txtCliente.setToolTipText("Codigo Cliente\r\n");
			txtCliente.setHorizontalAlignment(SwingConstants.CENTER);
			txtCliente.setBounds(10, 88, 34, 25);
			// EVENTO PARA CONSULTAR CLIENTES DISPONIVEIS
			txtCliente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						TelaConsultaCliente tela = new TelaConsultaCliente(TelaPreVenda.this);
						tela.setTexto(txtCliente.getText());
						tela.setVisible(true);
						ClienteDAO dao = new ClienteDAO();
						if (tela.getRetorno() != null) {
							try {
								Cliente cli = dao.carregarPorID(Integer.parseInt(tela.getRetorno()));
								mostra(cli);
								txtCliente.setText(cli.getCodigo().toString());
							} catch (NumberFormatException txtBusca) {
								txtBusca.printStackTrace();
							} catch (Exception txtBusca) {
								txtBusca.printStackTrace();
							}
						}
					}
				}
			});
		}
		return txtCliente;
	}

	private JLabel getLbVendedor() {
		if (lbVendedor == null) {
			lbVendedor = new JLabel("");
			lbVendedor.setBounds(108, 11, 80, 25);
		}
		return lbVendedor;
	}

	private JLabel getLbClienteNome() {
		if (lbClienteNome == null) {
			lbClienteNome = new JLabel("");
			lbClienteNome.setBounds(54, 88, 239, 25);
		}
		return lbClienteNome;
	}

	private JLabel getLbItem() {
		if (lbItem == null) {
			lbItem = new JLabel("Item/Produto");
			lbItem.setToolTipText("");
			lbItem.setBounds(10, 124, 78, 14);
		}
		return lbItem;
	}

	private JTextField getTxtItem() {
		if (txtItem == null) {
			txtItem = new JTextField();
			txtItem.setToolTipText("Codigo Produto");
			txtItem.setHorizontalAlignment(SwingConstants.CENTER);
			txtItem.setBounds(10, 141, 34, 25);
			// EVENTO PARA CONSULTAR OS PRODUTOS DISPONIVEIS
			txtItem.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						TelaConsultaProduto tela1 = new TelaConsultaProduto(TelaPreVenda.this);
						tela1.setTexto(txtItem.getText());
						tela1.setVisible(true);
						ProdutoDAO dao = new ProdutoDAO();
						if (tela1.getRetorno() != null) {
							try {
								Produto prod = dao.carregarPorID(Integer.parseInt(tela1.getRetorno()));
								mostra(prod);
								txtItem.setText(prod.getCodigo().toString());
							} catch (NumberFormatException txtBusca) {
								txtBusca.printStackTrace();
							} catch (Exception txtBusca) {
								txtBusca.printStackTrace();
							}
						}
					}
				}
			});
			txtItem.setColumns(10);
		}
		return txtItem;
	}

	private JLabel getLbQuantidade() {
		if (lbQuantidade == null) {
			lbQuantidade = new JLabel("Quantidade");
			lbQuantidade.setBounds(269, 177, 78, 14);
		}
		return lbQuantidade;
	}

	private JTextField getTxtQtd() {
		if (txtQtd == null) {
			txtQtd = new JTextField();
			txtQtd.setToolTipText("Quantidade Itens");
			txtQtd.setHorizontalAlignment(SwingConstants.CENTER);
			txtQtd.setBounds(279, 202, 35, 25);
			txtQtd.setText("0,00");
			txtQtd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						try {
							ManipulaValor mani = new ManipulaValor();
							// PEGANDO OS VALORES DAS STRINGS E TRANSFORMANDO EM
							// DOUBLE PARA FAZER A SOMA
							// DOS VALORES
							Double somaQtdSoma = mani.formataStringtoValor(txtQtd.getText());
							Double somaValor = mani.formataStringtoValor(lbValorItem.getText());
							Double desconto = mani.formataStringtoValor(txtDesconto.getText());
							Double somaValorTotal = (somaQtdSoma * somaValor)  -  desconto;
							Double somaValorComDesconto = somaValorTotal;
							// FORMATANDO O VALOR PARA STRING PARA ELE IR NO
							// JLABEL
							lbValorTotal.setText(mani.formataValortoString(somaValorComDesconto.doubleValue()));
						} catch (NumberFormatException lbValorTotal) {
							lbValorTotal.printStackTrace();
						} catch (Exception lbValorTotal) {
							lbValorTotal.printStackTrace();
						}
					}
				}
			});
			txtQtd.setColumns(10);
		}
		return txtQtd;
	}

	private JLabel getLbValor() {
		if (lbValor == null) {
			lbValor = new JLabel("Valor Item");
			lbValor.setBounds(128, 177, 60, 14);
		}
		return lbValor;
	}

	private JLabel getLbValorTotal() {
		if (lbValorTotal == null) {
			lbValorTotal = new JLabel("");
			lbValorTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lbValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
			lbValorTotal.setBounds(409, 202, 60, 25);
			
		}
		return lbValorTotal;
	}

	private JLabel getLbProduto() {
		if (lbProduto == null) {
			lbProduto = new JLabel("");
			lbProduto.setBounds(54, 141, 419, 25);
		}
		return lbProduto;
	}

	private JLabel getLbValorItem() {
		if (lbValorItem == null) {
			lbValorItem = new JLabel("");
			lbValorItem.setHorizontalAlignment(SwingConstants.CENTER);
			lbValorItem.setBounds(122, 202, 66, 25);
		}
		return lbValorItem;
	}

	private JButton getBtnAdiciona() {
		if (btnAdiciona == null) {
			btnAdiciona = new JButton("Adiciona");
			btnAdiciona.setToolTipText("Adicionar item");
			btnAdiciona.setBounds(532, 172, 72, 25);
			btnAdiciona.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ItemVenda venda = new ItemVenda();
					ManipulaValor mani = new ManipulaValor();
					Produto prod = new Produto();
					
					prod.setNomeProduto(lbProduto.getText()); // MOSTRA O// PRODUTO/ITEM
					prod.setValor(mani.formataStringtoValor(lbValorItem.getText())); // MOSTRA O VALOR DO ITEM
					venda.setPreco(mani.formataStringtoValor(lbValorTotal.getText())); // MOSTRa O VALOR COMPLEto COm DESCONTO
					venda.setQuantidadeItens(mani.formataStringtoValor(txtQtd.getText())); // MOSTRA A QUANTIDADE
					prod.setCodigo(Integer.parseInt(txtItem.getText())); // INSTANCIANDO o  CODIgo DO PRODUTO
					venda.setProduto(prod); // VENDA RECEBENDO O CODIGo PRODUTO PARA SER SALVO NO BD
					// SOMA DO VALOR TOTAL DA VENDA
					
					
					
					
					somaVenda += ((prod.getValor() * venda.getQuantidadeItens())-mani.formataStringtoValor(txtDesconto.getText())) ;
					lbValorVenda.setText(mani.formataValortoString(somaVenda));
					
					somaItens += venda.getQuantidadeItens();
					lbQtdItens.setText(somaItens.toString());
					
					carrinho = (DefaultTableModel) table.getModel();
					
					carrinho.addRow(new Object[] {
							prod.getCodigo(),
							prod.getNomeProduto(),
							venda.getQuantidadeItens(),
							prod.getValor(),
							venda.getPreco()
					});
					
					
				}
				
			});
		}
		return btnAdiciona;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	private JLabel getLbValorVenda() {
		if (lbValorVenda == null) {
			lbValorVenda = new JLabel("0,0");
			lbValorVenda.setFont(new Font("Tahoma", Font.BOLD, 12));
			lbValorVenda.setBounds(532, 440, 72, 14);
			
		
		}
		return lbValorVenda;
	}

	private JLabel getLbQtdItens() {
		if (lbQtdItens == null) {
			lbQtdItens = new JLabel("0");
			lbQtdItens.setFont(new Font("Tahoma", Font.BOLD, 12));
			lbQtdItens.setBounds(73, 439, 25, 15);
		}
		return lbQtdItens;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Qtd Itens:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 440, 60, 14);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("R$");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(500, 440, 19, 14);
		}
		return lblNewLabel_1;
	}

	private JLabel getLbNomeVendedor() {
		if (lbNomeVendedor == null) {
			lbNomeVendedor = new JLabel("Vendedor");
			lbNomeVendedor.setBounds(279, 71, 46, 14);
		}
		return lbNomeVendedor;
	}

	private JLabel getLbTotal() {
		if (lbTotal == null) {
			lbTotal = new JLabel("Total");
			lbTotal.setBounds(426, 177, 46, 14);
		}
		return lbTotal;
	}
	private JPanel getPainelCarinho() {
		if (painelCarinho == null) {
			painelCarinho = new JPanel();
			painelCarinho.setBackground(SystemColor.textHighlight);
			painelCarinho.setBounds(0, 0, 627, 60);
			painelCarinho.setLayout(null);
			painelCarinho.add(getLbCarrinho());
		}
		return painelCarinho;
	}
	private JLabel getLbCarrinho() {
		if (lbCarrinho == null) {
			lbCarrinho = new JLabel("Carrinho de Compras");
			lbCarrinho.setForeground(Color.WHITE);
			lbCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbCarrinho.setBounds(171, 11, 299, 38);
		}
		return lbCarrinho;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 238, 607, 191);
			scrollPane.setViewportView(getTable_1());
		}
		return scrollPane;
	}
	private JTable getTable_1() {
		if (table == null) {
			table = new JTable();
			table.setBackground(SystemColor.textHighlight);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00F3digo", "Produto", "Qtd", "Pre\u00E7o", "Total"
				}
			));
			
		}
		return table;
	}
	private JLabel getLbDesconto() {
		if (lbDesconto == null) {
			lbDesconto = new JLabel("Desconto");
			lbDesconto.setBounds(10, 177, 46, 14);
		}
		return lbDesconto;
	}
	private JTextField getTxtDesconto() {
		if (txtDesconto == null) {
			txtDesconto = new JTextField();
			txtDesconto.setToolTipText("Desconto/Item");
			txtDesconto.setHorizontalAlignment(SwingConstants.CENTER);
			txtDesconto.setText("0,0");
			txtDesconto.setBounds(10, 202, 66, 25);
			
			txtDesconto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						try {
							ManipulaValor mani = new ManipulaValor();
						
							Double somaQtdSoma = mani.formataStringtoValor(txtQtd.getText());
							Double somaValor = mani.formataStringtoValor(lbValorItem.getText());
							Double desconto = mani.formataStringtoValor(txtDesconto.getText());
							Double venda = mani.formataStringtoValor(lbValorVenda.getText());
							Double somaValorTotal = (somaQtdSoma * somaValor);
							Double somaValorComDesconto = (somaValorTotal  -  desconto);
							lbValorVenda.setText(mani.formataValortoString(venda + somaValorComDesconto));
						} catch (NumberFormatException lbValorVenda) {
							lbValorVenda.printStackTrace();
						} catch (Exception lbValorVenda) {
							lbValorVenda.printStackTrace();
						}
					}
				}
			});
			txtDesconto.setColumns(10);
		}
		return txtDesconto;
	}

	
}