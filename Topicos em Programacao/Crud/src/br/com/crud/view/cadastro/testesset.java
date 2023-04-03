package br.com.crud.view.cadastro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testesset extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel painel;
	private JPanel painel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testesset frame = new testesset();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testesset() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTabbedPane());
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(71, 100, 345, 206);
			tabbedPane.addTab("New tab", null, getPainel(), null);
			tabbedPane.addTab("New tab", null, getPainel_1(), null);
		}
		return tabbedPane;
	}
	private JPanel getPainel() {
		if (painel == null) {
			painel = new JPanel();
		}
		return painel;
	}
	private JPanel getPainel_1() {
		if (painel_1 == null) {
			painel_1 = new JPanel();
		}
		return painel_1;
	}
}
