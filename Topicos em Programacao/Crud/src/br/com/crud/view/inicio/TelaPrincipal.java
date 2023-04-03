package br.com.crud.view.inicio;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

//import br.com.crud.view.cadastro.TelaBandeira;
//import br.com.crud.view.cadastro.TelaCategoria;
import br.com.crud.view.cadastro.TelaCliente;
//import br.com.crud.view.cadastro.TelaPlanoPagamento;
import br.com.crud.view.cadastro.TelaPreVenda;
import br.com.crud.view.cadastro.TelaProduto;
import br.com.crud.view.cadastro.TelaVendedor;
import br.com.crud.view.cadastro.TelaPreVenda;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;

//classe extend de JDialog que � uma classe com padr�es de tela visual
public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnFechar = null;

	JToolBar tb;
	JButton cliente;
	private JToolBar toolBar;
	private JButton btnCliente;
	private JLabel lbStatus;
	private JLabel lbBemVindo;
	private JButton btnProduto;
	private JButton btnCategoria;
	private JButton btnPrevenda;
	private JButton btnBandeira;
	private JButton btnCarteira;
	private JPanel painel;

	/**
	 * @wbp.parser.constructor
	 */
	public TelaPrincipal(JFrame owner) {
//		super(owner);
		initialize();
	}

	public TelaPrincipal(JDialog owner) {
//		super(owner);
		initialize();
	}

	public TelaPrincipal() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		this.setSize(633, 221);
		this.setTitle("Menu Inicial");
		this.setContentPane(getJContentPane());
//		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		getJContentPane().add(getToolBar());
		getJContentPane().add(getLbStatus());
		getJContentPane().add(getPainel());
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnFechar(), null);
		}
		return jContentPane;
	}

	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton();
			btnFechar.setToolTipText("Fechar tela");
			btnFechar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/crud/img/icons8-fechar-janela-30.png")));
			btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFechar.setBounds(new Rectangle(456, 146, 150, 35));
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

	private JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.setBounds(0, 0, 627, 30);
			toolBar.add(getBtnCliente());
			toolBar.add(getBtnProduto());
			
			toolBar.add(getBtnPrevenda());
			
		}
		return toolBar;
	}

	private JButton getBtnCliente() {
		if (btnCliente == null) {
			btnCliente = new JButton("Cadastro");
			btnCliente.setToolTipText("Cadastrar Cliente/Funcionario");
			btnCliente.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					new TelaCliente().setVisible(true);
				}
			});
			btnCliente.setMargin(new Insets(5, 17, 5, 17));
		}
		return btnCliente;
	}

	private JLabel getLbStatus() {
		if (lbStatus == null) {
			lbStatus = new JLabel("");
			lbStatus.setBounds(0, 95, 627, 30);
		}
		return lbStatus;
	}

	private JButton getBtnProduto() {
		if (btnProduto == null) {
			btnProduto = new JButton("Produto");
			btnProduto.setToolTipText("Cadastrar Produto");
			btnProduto.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new TelaProduto().setVisible(true);
				}
			});
			btnProduto.setMargin(new Insets(5, 17, 5, 17));
		}
		return btnProduto;
	}

	
	private JButton getBtnPrevenda() {
		if (btnPrevenda == null) {
			btnPrevenda = new JButton("Pr\u00E9-venda");
			btnPrevenda.setToolTipText("Carrinho de compras");
			btnPrevenda.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new TelaPreVenda().setVisible(true);
				}
			});
			btnPrevenda.setMargin(new Insets(5, 17, 5, 17));
		}
		return btnPrevenda;
	}

	
	private JPanel getPainel() {
		if (painel == null) {
			painel = new JPanel();
			painel.setLayout(null);
			painel.setBackground(SystemColor.textHighlight);
			painel.setBounds(0, 65, 627, 60);
			lbBemVindo = new JLabel();
			lbBemVindo.setForeground(Color.WHITE);
			lbBemVindo.setBounds(0, 11, 627, 38);
			painel.add(lbBemVindo);
			lbBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 32));
			lbBemVindo.setText("Bem-vindo ao menu inicial");
			lbBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return painel;
	}
}
