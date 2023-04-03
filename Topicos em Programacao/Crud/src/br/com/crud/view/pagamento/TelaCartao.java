package br.com.crud.view.pagamento;

import java.awt.Color;
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
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import br.com.crud.dao.BandeiraCartaoDAO;
import br.com.crud.dao.ContaReceberDAO;
import br.com.crud.model.BandeiraCartao;
import br.com.crud.model.Cliente;
import br.com.crud.model.ContaReceber;
import br.com.crud.model.PreVenda;
import br.com.crud.util.ManipulaValor;
import br.com.crud.view.consulta.TelaConsultaBandeira;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class TelaCartao extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnFechar = null;
	JToolBar tb;
	private JLabel lbStatus;
	private JLabel lbBandeira;
	private JTextField txtBandeira;
	private JLabel lbValor;
	private JLabel lbParcelas;
	private JTextField txtParcelas;
	private JButton btnFinalizar;
	private JTextField txtValor;
	private JLabel lbNumParcela;
	private JScrollPane scrollPane;
	private JButton btnAdicionar;
	private JLabel lbBandeiraNome;

	private ObjectTableModel<ContaReceber> model = null;

	private int total = 0;
	private JTable table;
	private JLabel lbValorParcelas;
	private Double valor;
	private JLabel lbDiferenca;
	private JLabel lbValorDif;
	private String texto;
	private String textoBand;
	private String tipoBand;
	private Integer dataVenc;

	// ContaReceberDAO contaFindAll = new ContaReceberDAO();
	private List<ContaReceber> listaConta = null;
	private JPanel painelPreencha;

	/**
	 * 
	 */
	public TelaCartao(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaCartao(JDialog owner) {
		super(owner);
		initialize();
	}

	public TelaCartao() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		this.setSize(400, 462);
		this.setTitle("Tela Pagamento Cartão");
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
		getJContentPane().add(getLbBandeiraNome());
		getJContentPane().add(getLbValorParcelas());
		getJContentPane().add(getLbDiferenca());
		getJContentPane().add(getLbValorDif());
		getJContentPane().add(getPainelPreencha());
	}

	@SuppressWarnings("unchecked")
	private void carregaLista() {
		try {
			List lista = new ArrayList<ContaReceber>();
			carregaTable(lista);
		} catch (Exception busca) {
			busca.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	private void carregaTable(List lista) {
		total = lista.size();
		AnnotationResolver resolver = new AnnotationResolver(ContaReceber.class);
		model = new ObjectTableModel<ContaReceber>(resolver, "nomeCartao,parcelas,valor");
		model.setData(lista);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
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
			getJContentPane().add(getLbBandeira());
			getJContentPane().add(getTxtBandeira());
			getJContentPane().add(getLbValor());
			getJContentPane().add(getLbParcelas());
			getJContentPane().add(getTxtParcelas());
			getJContentPane().add(getBtnFinalizar());
			getJContentPane().add(getTxtValor());
			getJContentPane().add(getLbNumParcela());
			getJContentPane().add(getScrollPane());
			getJContentPane().add(getBtnAdicionar());
		}
		return jContentPane;
	}

	private void limpa() {
		txtBandeira.setText("");
		lbBandeiraNome.setText("");
		lbValorParcelas.setText("");
		txtParcelas.setText("");
		
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

	private JLabel getLbBandeira() {
		if (lbBandeira == null) {
			lbBandeira = new JLabel("Bandeira: ");
			lbBandeira.setBounds(18, 96, 52, 14);
		}
		return lbBandeira;
	}

	private JTextField getTxtBandeira() {
		if (txtBandeira == null) {
			txtBandeira = new JTextField();
			txtBandeira.setHorizontalAlignment(SwingConstants.CENTER);
			txtBandeira.setBounds(72, 91, 52, 25);
			txtBandeira.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						// BUSCA PELO CART�O PARA POPULAR A TELA
						TelaConsultaBandeira tela = new TelaConsultaBandeira(TelaCartao.this);
						tela.setTexto(txtBandeira.getText());
						tela.setVisible(true);
						BandeiraCartaoDAO dao = new BandeiraCartaoDAO();
						if (tela.getRetorno() != null) {
							try {
								BandeiraCartao band = dao.carregarPorID(Integer.parseInt(tela.getRetorno()));
								mostra(band);
								txtBandeira.setText(band.getCodigo().toString());

							} catch (NumberFormatException txtBusca) {
								txtBusca.printStackTrace();
							} catch (Exception txtBusca) {
								txtBusca.printStackTrace();
							}
						}
					}
				}
			});
			txtBandeira.setColumns(10);
		}
		return txtBandeira;
	}

	private JLabel getLbValor() {
		if (lbValor == null) {
			lbValor = new JLabel("Valor: ");
			lbValor.setBounds(34, 136, 36, 14);
		}
		return lbValor;
	}

	private JLabel getLbParcelas() {
		if (lbParcelas == null) {
			lbParcelas = new JLabel("Parcelas: ");
			lbParcelas.setBounds(18, 182, 58, 14);
		}
		return lbParcelas;
	}

	private JTextField getTxtParcelas() {
		if (txtParcelas == null) {
			txtParcelas = new JTextField();
			txtParcelas.setHorizontalAlignment(SwingConstants.CENTER);
			txtParcelas.setBounds(72, 177, 52, 25);
			txtParcelas.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == e.VK_ENTER) {
						try {
							// CODIGO PARA FAZER O CALCULO DO VALOR / PARCELAS
							// -> PARA SABER QUANTO FICA CADA PARCELA
							ManipulaValor mani = new ManipulaValor();
							Double somaValor = mani.formataStringtoValor(txtValor.getText());
							Double somaParcelas = mani.formataStringtoValor(txtParcelas.getText());
							Double somaValorParcelas = somaValor / somaParcelas;
							if (somaParcelas == 0) {
								lbValorParcelas.setText(
										txtParcelas.getText() + " - " + mani.formataStringtoValor(txtValor.getText()));
							} else {
								lbValorParcelas.setText(
										txtParcelas.getText() + " - " + mani.formataValortoString(somaValorParcelas));
							}
						} catch (NumberFormatException lbValorParcelas) {
							lbValorParcelas.printStackTrace();
						} catch (Exception lbValorParcelas) {
							lbValorParcelas.printStackTrace();
						}
					}
				}
			});
			txtParcelas.setColumns(10);
		}
		return txtParcelas;
	}

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setToolTipText("Finalizar Venda");
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnFinalizar.setIcon(new ImageIcon(TelaCartao.class.getResource("/br/com/crud/img/icons8-adicionar-o-carrinho-de-compras-30.png")));
			btnFinalizar.setBounds(253, 375, 125, 30);
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
					// INSER��O DOS DADOS NO BANCO
					List<ContaReceber> contas = new ArrayList<>();
					if (verifica()) {
						try {
							for (int i = 0; i < table.getRowCount(); i++) {
								ContaReceber conta = model.getValue(i);

								// INSTANCIANDO OBJETOS NOVOS
								BandeiraCartao band = new BandeiraCartao();

								// PEGANDO O DIA E A HORA DA FINALIZA��O DA VENDA
								Calendar cal = Calendar.getInstance();

								// SETANDO A BANDEIRA DO CART�O
								band.setCodigo(Integer.parseInt(textoBand));
								band.setTipoCartao(Integer.parseInt(tipoBand));

								// CALCULO PARA SOMAR OS DIAS NA DATA DO INSTANTE DA VENDA
								// CONFORME O TIPO DE CART�O
								Integer tipo = band.getTipoCartao();
								if (tipo == 1) {
									cal.add(Calendar.DAY_OF_MONTH, dataVenc);
								} else {
									cal.add(Calendar.DAY_OF_MONTH, dataVenc);
								}
								Date data = cal.getTime();

								// SETANDO ALGUNS DADOS PARA O BANCO DE DADOS
								conta.setCancelado("N");
								//conta.setNSU(conta.getNSU());
								conta.setNomeCartao(conta.getNomeCartao());
								conta.setParcelas(conta.getParcelas());
								conta.setVencimento(data);
								conta.setBandeira(band);

								// CALCULO PARA VALOR DE CADA PARCELA
								Integer par = conta.getParcelas();
								Double valor = conta.getValor();
								Double soma = valor / par;

								// CODIGO PARA POPULAR O BANCO CASO AS PARCELAS SEJAM
								// MAIORES QUE 1
								if (conta.getParcelas() > 1) {
									for (int k = 0; k < conta.getParcelas(); k++) {
										ContaReceber conta2 = new ContaReceber();
										conta2.setCancelado("N");
										conta2.setParcelas(k + 1);
										conta2.setNomeCartao(conta.getNomeCartao());
										conta2.setValor(soma);
										
										conta2.setVencimento(data);
										conta2.setBandeira(band);

										// SOMA DAS DATAS SOBRE A DATA INFORMADA NA VENDA
										cal.add(Calendar.DAY_OF_MONTH, dataVenc);
										data = cal.getTime();

										contas.add(conta2);
									}

								} else {
									contas.add(conta);
								}
							}
							setListaConta(contas);
							dispose();
						} catch (Exception as) {
						}
					}
				}
			});
		}
		return btnFinalizar;
	}

	// METODO PARA VERIFICA��O SE VALOR DA TELA � DIFERENTE DE 0, SEN�O CONTINUA NA
	// TELA
	private boolean verifica() {
		ManipulaValor mani = new ManipulaValor();
		Double soma = mani.formataStringtoValor(txtValor.getText());
		if (soma != 0) {
			txtValor.requestFocus();
			JOptionPane.showMessageDialog(null, "O valor precisa estar zerado!");
			return false;
		}
		return true;
	}

	// METODO PARA RETORNAR A BANDEIRA DO CARTAO PARA POPULAR ALGUNS DADOS
	private void mostra(BandeiraCartao objeto) {
		if (objeto.getCodigo() != null) {
			txtBandeira.setText(String.valueOf(objeto.getCodigo()));

			// SETANDO O CODIGO DO CARTAO PARA USAR NO INSERT DO BANCO
			textoBand = txtBandeira.getText();

			// PEGA O VALOR DO TIPO DO CARTAO E ESCOLHE A DATA DO VENCIMENTO
			tipoBand = objeto.getTipoCartao().toString();
			if (objeto.getTipoCartao() == 1) {
				dataVenc = objeto.getDebitoDia();
			} else {
				dataVenc = objeto.getCredidoDia();
			}
			lbBandeiraNome.setText(objeto.getDescricao());
		} else {
			txtBandeira.setText("");
		}
	}

	private JTextField getTxtValor() {
		if (txtValor == null) {
			txtValor = new JTextField();
			txtValor.setHorizontalAlignment(SwingConstants.CENTER);
			txtValor.setColumns(10);
			txtValor.setBounds(72, 131, 52, 25);
		}
		return txtValor;
	}

	private JLabel getLbNumParcela() {
		if (lbNumParcela == null) {
			lbNumParcela = new JLabel("0 - D\u00E9bito / 1 - Cr\u00E9dito / Parcelado - Qtd Parcelas");
			lbNumParcela.setForeground(SystemColor.textHighlight);
			lbNumParcela.setBounds(134, 174, 244, 30);
		}
		return lbNumParcela;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(18, 249, 352, 115);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JButton getBtnAdicionar() {
		if (btnAdicionar == null) {
			btnAdicionar = new JButton("Adicionar");
			btnAdicionar.setToolTipText("Adicionar pagamento");
			btnAdicionar.setBounds(289, 215, 89, 23);
			btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// METODO PARA POPULAR A TABELA QUE TEM NA TELA DO CART�O
					ContaReceber conta = new ContaReceber();
					ManipulaValor mani = new ManipulaValor();

					conta.setNomeCartao(lbBandeiraNome.getText());
					conta.setParcelas(Integer.parseInt(txtParcelas.getText()));
					//conta.setNSU(txtAutorizacao.getText());
					conta.setValor(mani.formataStringtoValor(txtValor.getText()));

					// METODO PARA SUBTRAIR O VALOR DE CADA CART�O INSERIDO NA TELA
					if (valor >= conta.getValor()) {
						Double somaDif = valor - conta.getValor();

						txtBandeira.requestFocus();

						conta.setNomeCartao(lbBandeiraNome.getText());
						conta.setParcelas(Integer.parseInt(txtParcelas.getText()));
						
						conta.setValor(mani.formataStringtoValor(txtValor.getText()));

						lbValorDif.setText(mani.formataValortoString(somaDif));
						valor = somaDif;
						limpa();
					}
					txtValor.setText(mani.formataValortoString(valor));
					model.add(conta);
				}
			});
		}
		return btnAdicionar;
	}

	private JLabel getLbBandeiraNome() {
		if (lbBandeiraNome == null) {
			lbBandeiraNome = new JLabel("");
			lbBandeiraNome.setBounds(160, 224, 64, 14);
		}
		return lbBandeiraNome;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBounds(10, 130, 300, -4);
		}
		return table;
	}

	// METODO PARA POPULAR ALGUNS DADOS QUE VEM DA VENDAPRE
	private void mostra(PreVenda objeto) {
		ManipulaValor mani = new ManipulaValor();
		txtValor.setText(texto);
		lbValorDif.setText(texto);
		valor = mani.formataStringtoValor(texto);

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

	private JLabel getLbValorParcelas() {
		if (lbValorParcelas == null) {
			lbValorParcelas = new JLabel("");
			lbValorParcelas.setBounds(72, 224, 74, 14);
		}
		return lbValorParcelas;
	}

	private JLabel getLbDiferenca() {
		if (lbDiferenca == null) {
			lbDiferenca = new JLabel("Diferen\u00E7a ");
			lbDiferenca.setFont(new Font("Tahoma", Font.BOLD, 11));
			lbDiferenca.setForeground(SystemColor.textHighlight);
			lbDiferenca.setBounds(18, 383, 58, 14);
		}
		return lbDiferenca;
	}

	private JLabel getLbValorDif() {
		if (lbValorDif == null) {
			lbValorDif = new JLabel("");
			lbValorDif.setHorizontalAlignment(SwingConstants.CENTER);
			lbValorDif.setForeground(SystemColor.textHighlight);
			lbValorDif.setBounds(72, 383, 54, 14);
		}
		return lbValorDif;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTextoBand() {
		return textoBand;
	}

	public void setTextoBand(String textoBand) {
		this.textoBand = textoBand;
	}

	public String getTipoBand() {
		return tipoBand;
	}

	public void setTipoBand(String tipoBand) {
		this.tipoBand = tipoBand;
	}

	public Integer getDataVenc() {
		return dataVenc;
	}

	public void setData(Integer dataVenc) {
		this.dataVenc = dataVenc;
	}

	public List<ContaReceber> getListaConta() {
		return listaConta;
	}

	public void setListaConta(List<ContaReceber> listaConta) {
		this.listaConta = listaConta;
	}

	private JPanel getPainelPreencha() {
		if (painelPreencha == null) {
			painelPreencha = new JPanel();
			painelPreencha.setBackground(SystemColor.textHighlight);
			painelPreencha.setBounds(0, 0, 394, 66);
			painelPreencha.setLayout(null);
			lbStatus = new JLabel();
			lbStatus.setForeground(Color.WHITE);
			lbStatus.setBounds(46, 11, 294, 44);
			painelPreencha.add(lbStatus);
			lbStatus.setFont(new Font("Tahoma", Font.PLAIN, 31));
			lbStatus.setText("Preencha os campos");
			lbStatus.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return painelPreencha;
	}
}
