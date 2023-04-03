package br.com.crud.view.pagamento;

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

public class TelaDinheiro extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnFechar = null;
	
	JToolBar tb;
	private JLabel lbStatus;

	/**
	 * 
	 */
	public TelaDinheiro(JFrame owner) {
		super(owner);
		initialize();
	}

	public TelaDinheiro(JDialog owner) {
		super(owner);
		initialize();
	}

	public TelaDinheiro() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(300,300);
		this.setTitle("Formas de pagamentos");
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.addWindowListener(new java.awt.event.WindowAdapter() {});
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnFechar(), null);
			lbStatus = new JLabel();
			lbStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbStatus.setBounds(new Rectangle(0, 11, 294, 25));
			lbStatus.setText("Valor no dinheiro");
			lbStatus.setHorizontalAlignment(SwingConstants.CENTER);
			
			
			jContentPane.add(lbStatus, null);
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
}
