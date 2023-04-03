package br.com.crud.view.pagamento;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import br.com.crud.dao.CrediarioDAO;
import br.com.crud.model.ContaReceber;
import br.com.crud.model.Crediario;
import br.com.crud.util.ManipulaValor;
import br.com.crud.view.consulta.TelaConsultaCrediario;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaCrediario extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbStatus;
	private JButton btnFechar = null;
	private JButton btnFinalizar = null;
	private JLabel lbCondicaoPag;
	private JTextField txtCondicao;

	private Double valorVenda;

	private String texto;

	// ATRIBUTOS PARA RECEBER OS DADOS DO CREDIARIO NA TELA
	private ObjectTableModel<Crediario> model = null;
	private int total = 0;
	private Integer dias;
	private Integer parcelas;
	private Date vencimento;

	// LISTA PARA MANDAR DADOS DO PAGAMENTO PARA FINALIZA��O
	private List<ContaReceber> crediarios = null;

	private JLabel lbDescricaoPag;
	private JButton btnAdicionar;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel painel;

	public TelaCrediario(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaCrediario(JDialog owner) {
		super(owner);
		initialize();
	}

	public TelaCrediario() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		this.setSize(400, 332);
		this.setTitle("Tela Pagamento Crediario");
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowOpened(java.awt.event.WindowEvent e) {
				mostra();
				carregaLista();
			}
		});
		getJContentPane().add(getLbDescricaoPag());
		getJContentPane().add(getBtnAdicionar());
		getJContentPane().add(getScrollPane_1());
		getJContentPane().add(getPainel());
	}

	@SuppressWarnings("unchecked")
	private void carregaLista() {
		try {
			List lista = new ArrayList<Crediario>();
			carregaTable(lista);
		} catch (Exception busca) {
			busca.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void carregaTable(List lista) {
		total = lista.size();
		AnnotationResolver resolver = new AnnotationResolver(Crediario.class);
		// ESCOLHENDO QUAIS COLUNAS EU VOU APRESENTAR NA JTABLE DA TELA
		model = new ObjectTableModel<Crediario>(resolver, "descricao,parcelas,valor,vencimento");//
		model.setData(lista);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		// table.getColumnModel().getColumn(2).setPreferredWidth(100);
		if (table.getRowCount() > 0) {
			table.changeSelection(0, 0, false, false);
			table.requestFocus();
		}
		table.updateUI();
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnFechar(), null);
			jContentPane.add(getBtnFechar());
			jContentPane.add(getBtnFinalizar());
			jContentPane.add(getLbCondicaoPag());
			jContentPane.add(getTxtCondicao());
		}
		return jContentPane;
	}

	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton();
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnFechar.setBounds(new Rectangle(475, 174, 140, 30));
			btnFechar.setText("Fechar [ESC]");
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

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setToolTipText("Finalizar Pagamento");
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnFinalizar.setIcon(new ImageIcon(TelaCrediario.class.getResource("/br/com/crud/img/icons8-adicionar-o-carrinho-de-compras-30.png")));
			btnFinalizar.setBounds(259, 262, 125, 30);
			btnFinalizar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_RIGHT) {
						dispose();
					}
				}
			});
			btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					List<ContaReceber> crediario = new ArrayList<>();
					if (verifica()) {
						try {
							ContaReceber conta = new ContaReceber();
							Calendar cal = Calendar.getInstance();

							// SETANDO DADOS PARA INSER��O NO BANCO
							conta.setParcelas(parcelas);
							conta.setVencimento(vencimento);
							conta.setCancelado("N");
							conta.setNomeCartao("CREDIARIO");
							
							conta.setValor(valorVenda);

							// CALCULO PARA VALORES DAS PARCELAS
							Integer par = parcelas;
							Double valor = valorVenda;
							Double soma = valor / par;

							// SE PARCELAS DO CREDIARIO SER MAIOR QUE 1, SETANDO OS DADOS DOS OBJETOS E
							// SEGUINDO COM A INSER��O NO BANCO
							if (parcelas > 1) {
								for (int i = 0; i < parcelas; i++) {
									ContaReceber conta2 = new ContaReceber();

									cal.add(Calendar.DAY_OF_MONTH, dias);
									Date data = cal.getTime();

									conta2.setParcelas(i + 1);
									conta2.setVencimento(data);
									conta2.setCancelado("N");
									conta2.setNomeCartao("CREDIARIO");
									
									conta2.setValor(soma);

									crediario.add(conta2);
								}
							} else {
								crediario.add(conta);
							}
							setCrediarios(crediario);
							dispose();
						} catch (Exception ex) {
						}
					}
				}
			});

		}
		return btnFinalizar;
	}

	private boolean verifica() {
		if (txtCondicao.getText() == null) {
			txtCondicao.requestFocus();
			JOptionPane.showMessageDialog(null, "A condicaoo precisa ser informada");
			return false;
		}
		return true;
	}

	private void mostra() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel getLbCondicaoPag() {
		if (lbCondicaoPag == null) {
			lbCondicaoPag = new JLabel("Plano de Pagamento:");
			lbCondicaoPag.setBounds(10, 102, 125, 14);
		}
		return lbCondicaoPag;
	}

	private JTextField getTxtCondicao() {
		if (txtCondicao == null) {
			txtCondicao = new JTextField();
			txtCondicao.setHorizontalAlignment(SwingConstants.CENTER);
			txtCondicao.setBounds(135, 97, 45, 25);
			txtCondicao.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						// BUSCA DO TIPO DE CREDIARIO DISPONIVEL PELA LOJA PARA CLIENTE
						TelaConsultaCrediario tela = new TelaConsultaCrediario(TelaCrediario.this);
						tela.setTexto(txtCondicao.getText());
						tela.setVisible(true);
						CrediarioDAO dao = new CrediarioDAO();
						if (tela.getRetorno() != null) {
							try {
								Crediario cred = dao.carregarPorID(Integer.parseInt(tela.getRetorno()));
								mostra(cred);
								txtCondicao.setText(cred.getCodigo().toString());

							} catch (NumberFormatException ex) {
								ex.printStackTrace();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			});
			txtCondicao.setColumns(10);
		}
		return txtCondicao;
	}

	private void mostra(Crediario objeto) {
		if (objeto.getCodigo() != null) {
			txtCondicao.setText(String.valueOf(objeto.getCodigo()));
			lbDescricaoPag.setText(objeto.getDescricao());
			parcelas = objeto.getParcelas();
			dias = objeto.getQtdDias();
			vencimento = objeto.getVencimento();
		} else {
			lbDescricaoPag.setText("");
		}
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<ContaReceber> getCrediarios() {
		return crediarios;
	}

	public void setCrediarios(List<ContaReceber> crediarios) {
		this.crediarios = crediarios;
	}

	private JLabel getLbDescricaoPag() {
		if (lbDescricaoPag == null) {
			lbDescricaoPag = new JLabel("");
			lbDescricaoPag.setBounds(10, 127, 146, 14);
		}
		return lbDescricaoPag;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	private JButton getBtnAdicionar() {
		if (btnAdicionar == null) {
			btnAdicionar = new JButton("Adicionar");
			btnAdicionar.setToolTipText("Adicionar plano ");
			btnAdicionar.setBounds(295, 98, 89, 23);
			btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Crediario cred = new Crediario();

					cred.setDescricao(lbDescricaoPag.getText());
					cred.setParcelas(parcelas);
					cred.setVencimento(vencimento);
					cred.setValor(valorVenda);

					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DAY_OF_MONTH, dias);
					Date data = cal.getTime();

					Integer par = parcelas;
					Double valor = valorVenda;
					Double soma = valor / par;

					// SE PARCELAS MAIOR QUE 1, � CRIADO UM NOVO OBJETO PARA QUE N�O HAJA CONFLITO
					// ENTRE ELES NA INSERidoO NA JTABLE
					if (parcelas > 1) {
						for (int i = 0; i < parcelas; i++) {
							Crediario cred2 = new Crediario();
							cred2.setDescricao(i + 1 + "x Parcela");
							cred2.setParcelas(i + 1);
							cred2.setValor(soma);
							cred2.setVencimento(data);

							cal.add(Calendar.DAY_OF_MONTH, dias);
							data = cal.getTime();

							model.add(cred2);
						}
					} else {
						model.add(cred);
					}
				}
			});
		}
		return btnAdicionar;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 143, 374, 100);
			scrollPane.setViewportView(getTable_1());
		}
		return scrollPane;
	}

	private JTable getTable_1() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}
	private JPanel getPainel() {
		if (painel == null) {
			painel = new JPanel();
			painel.setBackground(SystemColor.textHighlight);
			painel.setBounds(0, 0, 394, 68);
			painel.setLayout(null);
			lbStatus = new JLabel();
			lbStatus.setForeground(Color.WHITE);
			lbStatus.setBounds(0, 21, 394, 25);
			painel.add(lbStatus);
			lbStatus.setFont(new Font("Tahoma", Font.PLAIN, 23));
			lbStatus.setText("Preencha os campos");
			lbStatus.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return painel;
	}
}
