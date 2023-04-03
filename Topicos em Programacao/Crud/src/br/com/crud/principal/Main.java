package br.com.crud.principal;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.crud.view.cadastro.TelaCliente;
import br.com.crud.view.inicio.TelaPrincipal;
import br.com.crud.view.cadastro.TelaProduto;

public class Main {
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					String lf = UIManager.getSystemLookAndFeelClassName();
					UIManager.setLookAndFeel(lf);
					System.out.println("LF: " + UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}

				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);

			}

		});

	}
}
