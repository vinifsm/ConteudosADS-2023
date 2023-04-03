package br.com.crud.view.pagamento;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.towel.swing.table.ObjectTableModel;

import br.com.crud.dao.ContaReceberDAO;
import br.com.crud.dao.PreVendaDAO;
import br.com.crud.model.Cliente;
import br.com.crud.model.ContaReceber;
import br.com.crud.model.ItemVenda;
import br.com.crud.model.Vendedor;
import br.com.crud.model.PreVenda;
import br.com.crud.util.ManipulaValor;
import br.com.crud.view.pagamento.TelaCrediario;
import br.com.crud.view.cadastro.TelaPreVenda;

import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TelaPagamento extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnFechar = null;
	JToolBar tb;
	// private JLabel lbStatus;
	private JLabel lbValorVenda;
	private JLabel lbDinheiro;
	private JLabel lbCartao;
	private JTextField txtDinheiro;
	private JTextField txtCartao;
	private JLabel lbValorVendaTotal;
	private JButton btnFinalizar;
	private JLabel lbCrediario;
	private JTextField txtCrediario;
	private String valor;
	private JLabel lblTroco;
	private JLabel lbTroco;
	private String texto = null;

	private String codigoCli;
	private Integer codVenda;
	private String codigoVendedor;
	private Double qtdItens;
	private Double valorVenda;

	private JTable table = null;
	private ObjectTableModel<ItemVenda> model;
	private JLabel lbPagemento;
	private JPanel painelLbPagamento;

	/**
	 * 
	 */
	public TelaPagamento(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaPagamento(JDialog owner) {
		super(owner);
		initialize();
	}

	public TelaPagamento() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		this.setSize(300, 343);
		this.setTitle("Fechar Venda");
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowOpened(java.awt.event.WindowEvent e) {
				mostra();
			}
		});
		getJContentPane().add(getLbValorVenda());
		getJContentPane().add(getLbDinheiro());
		getJContentPane().add(getLbCartao());
		getJContentPane().add(getTxtDinheiro());
		getJContentPane().add(getTxtCartao());
		getJContentPane().add(getLbValorVendaTotal());
		getJContentPane().add(getBtnFinalizar());
		getJContentPane().add(getLbCrediario());
		getJContentPane().add(getTxtCrediario());
		getJContentPane().add(getLblTroco());
		getJContentPane().add(getLbTroco());
		getJContentPane().add(getPainelLbPagamento());
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnFechar(), null);
			// jContentPane.add(lbStatus, null);

		}
		return jContentPane;
	}

	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton();
			btnFechar.setIcon(new ImageIcon(TelaPagamento.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setBackground(Color.WHITE);
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnFechar.setBounds(new Rectangle(167, 257, 117, 30));
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

	private JLabel getLbValorVenda() {
		if (lbValorVenda == null) {
			lbValorVenda = new JLabel("Valor Venda:");
			lbValorVenda.setBounds(26, 60, 67, 25);
		}
		return lbValorVenda;
	}

	private JLabel getLbDinheiro() {
		if (lbDinheiro == null) {
			lbDinheiro = new JLabel("Dinheiro: ");
			lbDinheiro.setBounds(37, 96, 46, 25);
		}
		return lbDinheiro;
	}

	private JLabel getLbCartao() {
		if (lbCartao == null) {
			lbCartao = new JLabel("Cart\u00E3o:");
			lbCartao.setBounds(47, 132, 46, 25);
		}
		return lbCartao;
	}

	private JTextField getTxtDinheiro() {
		if (txtDinheiro == null) {
			txtDinheiro = new JTextField();
			txtDinheiro.setToolTipText("Valor em dinheiro");
			txtDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
			txtDinheiro.setText("0,00");
			txtDinheiro.setBounds(93, 96, 100, 25);
			txtDinheiro.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						// CODIGO PARA VERIFICA��O DOS TEXTFIELDS
						// PARA SOMA DOS VALORES
						ManipulaValor mani = new ManipulaValor();
						Double dindin = mani.formataStringtoValor(txtDinheiro.getText());
						Double cartao = mani.formataStringtoValor(txtCartao.getText());
						Double crediario = mani.formataStringtoValor(txtCrediario.getText());
						if (dindin >= 0) {
							Double valor = mani.formataStringtoValor(lbValorVendaTotal.getText());
							Double conta = valor - dindin;
							lbTroco.setText(mani.formataValortoString(conta));
						}
						if (cartao >= 0) {
							Double conta = mani.formataStringtoValor(lbTroco.getText());
							Double v = conta - cartao;
							lbTroco.setText(mani.formataValortoString(v));
						}
						if (crediario >= 0) {
							Double conta = mani.formataStringtoValor(lbTroco.getText());
							Double d = conta - crediario;
							lbTroco.setText(mani.formataValortoString(d));
						}
					}
				}
			});
			txtDinheiro.setColumns(10);
		}
		return txtDinheiro;
	}

	private JTextField getTxtCartao() {
		if (txtCartao == null) {
			txtCartao = new JTextField();
			txtCartao.setToolTipText("Valor em cartão");
			txtCartao.setHorizontalAlignment(SwingConstants.CENTER);
			txtCartao.setText("0,00");
			txtCartao.setColumns(10);
			txtCartao.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						// CODIGO PARA VERIFICA��O DOS TEXTFIELDS
						// PARA SOMA DOS VALORES
						ManipulaValor mani = new ManipulaValor();
						Double cartao = mani.formataStringtoValor(txtCartao.getText());
						Double dindin = mani.formataStringtoValor(txtDinheiro.getText());
						Double crediario = mani.formataStringtoValor(txtCrediario.getText());
						if (cartao >= 0) {
							Double valor = mani.formataStringtoValor(lbValorVendaTotal.getText());
							Double conta = valor - cartao;
							lbTroco.setText(mani.formataValortoString(conta));
						}
						if (dindin >= 0) {
							Double conta = mani.formataStringtoValor(lbTroco.getText());
							Double v = conta - dindin;
							lbTroco.setText(mani.formataValortoString(v));
						}
						if (crediario >= 0) {
							Double conta = mani.formataStringtoValor(lbTroco.getText());
							Double d = conta - crediario;
							lbTroco.setText(mani.formataValortoString(d));
						}
					}
				}
			});
			txtCartao.setBounds(93, 128, 100, 25);
		}
		return txtCartao;
	}

	private JLabel getLbValorVendaTotal() {
		if (lbValorVendaTotal == null) {
			lbValorVendaTotal = new JLabel("Aqui vem o $ venda");
			lbValorVendaTotal.setBounds(103, 60, 67, 25);
			lbValorVendaTotal.setText(getValor());
		}
		return lbValorVendaTotal;
	}

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setToolTipText("s");
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnFinalizar.setIcon(new ImageIcon(TelaPagamento.class.getResource("/br/com/crud/img/icons8-adicionar-o-carrinho-de-compras-30.png")));
			btnFinalizar.setForeground(Color.BLACK);
			btnFinalizar.setBackground(Color.WHITE);
			btnFinalizar.setBounds(10, 257, 117, 30);
			btnFinalizar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						btnFinalizar.requestFocus();
						
					}
					
				}
			});
			btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
				Double dinheiro, totalPago;
				@Override
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PreVendaDAO dao = new PreVendaDAO();
					PreVenda ob = new PreVenda();
					ob = monta(ob);
					
					try {
						dao.cadastrar(ob);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					ob.setCancelado("N");
					
					if (verifica()) {
						ManipulaValor mani = new ManipulaValor();
						Double cartao = mani.formataStringtoValor(txtCartao.getText());
						Double crediario = mani.formataStringtoValor(txtCrediario.getText());
						Double dinherio = mani.formataStringtoValor(txtDinheiro.getText());
						
						
						// SE VALOR DO CARTAO DIFERENTE DE 0,
						// ENTRA NA TELACARTAO PARA FINALIZAR A VENDA
						
						if (cartao != 0) {
							dispose();
							TelaCartao tela = new TelaCartao(TelaPagamento.this);
							tela.setTexto(txtCartao.getText());
							tela.setVisible(true);
							// METODO PARA CADASTRAR A PREVENDA NA TELA PAGAMENTO
							if (tela.getListaConta().size() != 0) {
								ContaReceberDAO dao2 = new ContaReceberDAO();
								Cliente cli = new Cliente();
								cli.setCodigo(Integer.parseInt(codigoCli));
								try {
									for (ContaReceber conta : tela.getListaConta()) {
										conta.setVenda(ob);
										conta.setCliente(cli);
										dao2.cadastrar(conta);
									}
								} catch (Exception as) {
								}
							}
						}
						if (crediario != 0) {
							TelaCrediario tela2 = new TelaCrediario(TelaPagamento.this);
							tela2.setValorVenda(mani.formataStringtoValor(txtCrediario.getText()));
							tela2.setTexto(txtCrediario.getText());
							tela2.setVisible(true);
							if (tela2.getCrediarios().size() != 0) {
								ContaReceberDAO dao2 = new ContaReceberDAO();
								Cliente cli = new Cliente();
								cli.setCodigo(Integer.parseInt(codigoCli));
								try {
									for (ContaReceber conta : tela2.getCrediarios()) {
										conta.setVenda(ob);
										conta.setCliente(cli);
										dao2.cadastrar(conta);
									}
								} catch (Exception ex) {
								}
							}
						}
						
						
						
						JOptionPane.showMessageDialog(null, "Venda finalizada");
						dispose();
						}
				}
			});
		}
		return btnFinalizar;
	}

	private PreVenda monta(PreVenda pre) {
		// SETANDO CLIENTE DA PRIMEIRA TELA
		Cliente cli = new Cliente();
		cli.setCodigo(Integer.parseInt(codigoCli));
		// SETANDO O VENDEDOR
		Vendedor u = new Vendedor();
		u.setCodigo(Integer.parseInt(codigoVendedor));
		// SETANDO A DATA DA VENDA
		Calendar cal = Calendar.getInstance();
		Date data = cal.getTime();
		// SETANDO QUANTIDADE
		pre.setQtdItens(qtdItens);
		pre.setCliente(cli);
		pre.setVendedor(u);
		pre.setValorTotal(valorVenda);
		pre.setDataVenda(data);
		
		
		
		
		
		return pre;
	}

	private JLabel getLbCrediario() {
		if (lbCrediario == null) {
			lbCrediario = new JLabel("Credi\u00E1rio:");
			lbCrediario.setBounds(36, 164, 57, 25);
		}
		return lbCrediario;
	}

	private JTextField getTxtCrediario() {
		if (txtCrediario == null) {
			txtCrediario = new JTextField();
			txtCrediario.setToolTipText("Valor no crediario");
			txtCrediario.setHorizontalAlignment(SwingConstants.CENTER);
			txtCrediario.setText("0,00");
			txtCrediario.setColumns(10);
			txtCrediario.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						// CODIGO PARA VERIFICA��O DOS TEXTFIELDS
						// PARA SOMA DOS VALORES
						ManipulaValor mani = new ManipulaValor();
						Double cartao = mani.formataStringtoValor(txtCartao.getText());
						Double dindin = mani.formataStringtoValor(txtDinheiro.getText());
						Double crediario = mani.formataStringtoValor(txtCrediario.getText());
						if (crediario >= 0) {
							Double valor = mani.formataStringtoValor(lbValorVendaTotal.getText());
							Double conta = valor - crediario;
							lbTroco.setText(mani.formataValortoString(conta));
						}
						if (cartao >= 0) {
							Double conta = mani.formataStringtoValor(lbTroco.getText());
							Double v = conta - cartao;
							lbTroco.setText(mani.formataValortoString(v));
						}
						if (dindin >= 0) {
							Double conta = mani.formataStringtoValor(lbTroco.getText());
							Double v = conta - dindin;
							lbTroco.setText(mani.formataValortoString(v));
						}
					}
				}
			});
			txtCrediario.setBounds(93, 164, 100, 25);
		}
		return txtCrediario;
	}

	private void mostra(PreVenda objeto) {
		lbValorVendaTotal.setText(texto);
		lbTroco.setText(texto);
		objeto.setCodigo(codVenda);
	}

	private void mostra(Cliente objeto) {
		codigoCli = objeto.getRazaoSocial();
	}

	private void mostra() {
		try {
			ManipulaValor mani = new ManipulaValor();
			PreVenda ob = new PreVenda();
			ob.setValorTotal(mani.formataStringtoValor(texto));
			mostra(ob);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	private JLabel getLblTroco() {
		if (lblTroco == null) {
			lblTroco = new JLabel("Troco:");
			lblTroco.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblTroco.setForeground(SystemColor.textHighlight);
			lblTroco.setBounds(43, 214, 67, 14);
		}
		return lblTroco;
	}

	private JLabel getLbTroco() {
		if (lbTroco == null) {
			lbTroco = new JLabel("");
			lbTroco.setForeground(SystemColor.textHighlight);
			lbTroco.setBounds(93, 214, 100, 14);
		}
		return lbTroco;
	}

	private boolean verifica() {
		ManipulaValor mani = new ManipulaValor();
		Double diferenca = mani.formataStringtoValor(lbTroco.getText());
		if (diferenca > 0) {
			lbTroco.requestFocus();
			JOptionPane.showMessageDialog(null, "O valor esta pendente!");
			return false;
		}
		return true;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setCodigoCli(String codigoCli) {
		this.codigoCli = codigoCli;
	}

	public String getCodigoCli() {
		return codigoCli;
	}

	public void setCodVenda(Integer codVenda) {
		this.codVenda = codVenda;
	}

	public Integer getCodVenda() {
		return codVenda;
	}

	public String getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(String codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public Double getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(Double qtdItens) {
		this.qtdItens = qtdItens;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	private JLabel getLbPagemento() {
		if (lbPagemento == null) {
			lbPagemento = new JLabel("PAGAMENTO");
			lbPagemento.setForeground(Color.WHITE);
			lbPagemento.setBounds(84, 11, 126, 19);
			lbPagemento.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lbPagemento;
	}
	private JPanel getPainelLbPagamento() {
		if (painelLbPagamento == null) {
			painelLbPagamento = new JPanel();
			painelLbPagamento.setBackground(SystemColor.textHighlight);
			painelLbPagamento.setBounds(0, 0, 294, 44);
			painelLbPagamento.setLayout(null);
			painelLbPagamento.add(getLbPagemento());
		}
		return painelLbPagamento;
	}
}
