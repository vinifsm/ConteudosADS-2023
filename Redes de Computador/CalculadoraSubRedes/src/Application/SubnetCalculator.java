package Application;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SubnetCalculator extends JFrame {
	private JTextField ipField1, ipField2, ipField3, ipField4;
	private JTextField subnetMaskField1, subnetMaskField2, subnetMaskField3, subnetMaskField4;
	private JTextField cidrField, numSubnetsField, numHostsField;
	private JTextArea resultArea;
	private String ipClass;
	private int bits;
	private int mask;

	public SubnetCalculator() {
		setTitle("Calculadora de Subredes");
		setSize(414, 335);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JLabel ipLabel = new JLabel("Endere\u00E7o IP", SwingConstants.CENTER);
		ipLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ipLabel.setBounds(148, 9, 99, 30);
		JLabel subnetMaskLabel = new JLabel("M\u00E1scara de Sub-rede");
		subnetMaskLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		subnetMaskLabel.setBounds(136, 95, 129, 30);
		JLabel cidrLabel = new JLabel("CIDR");
		cidrLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cidrLabel.setBounds(72, 193, 39, 30);
		JLabel numSubnetsLabel = new JLabel("S.R");
		numSubnetsLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numSubnetsLabel.setBounds(189, 193, 30, 30);
		JLabel numHostsLabel = new JLabel("Hosts");
		numHostsLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numHostsLabel.setBounds(300, 193, 50, 30);

		ipField1 = new JTextField(3);
		ipField1.setLocation(20, 5);
		createIPTextField(ipField1);
		ipField2 = new JTextField(3);
		ipField2.setBounds(110, 5, 60, 30);
		createIPTextField(ipField2);
		ipField3 = new JTextField(3);
		ipField3.setBounds(200, 5, 60, 30);
		createIPTextField(ipField3);
		ipField4 = new JTextField(3);
		ipField4.setBounds(290, 5, 60, 30);
		createIPTextField(ipField4);
		ipField4.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		

		subnetMaskField1 = new JTextField(3);
		subnetMaskField1.setLocation(20, 5);
		createSubnetMaskTextField(subnetMaskField1);
		subnetMaskField2 = new JTextField(3);
		subnetMaskField2.setBounds(110, 5, 60, 30);
		createSubnetMaskTextField(subnetMaskField2);
		subnetMaskField3 = new JTextField(3);
		subnetMaskField3.setBounds(200, 5, 60, 30);
		createSubnetMaskTextField(subnetMaskField3);
		subnetMaskField4 = new JTextField(3);
		subnetMaskField4.setBounds(290, 5, 60, 30);
		createSubnetMaskTextField(subnetMaskField4);
		subnetMaskField4.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		cidrField = new JTextField(5);
		cidrField.setBounds(53, 223, 70, 40);
		cidrField.setEditable(false);
        Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculateSubnetByCIDR();
				
			}
        };
        cidrField.addActionListener(action);
		
		numSubnetsField = new JTextField(5);
		numSubnetsField.setBounds(164, 223, 70, 40);
		numSubnetsField.setEditable(false);
		numHostsField = new JTextField(5);
		numHostsField.setBounds(280, 223, 70, 40);
		numHostsField.setEditable(false);

		resultArea = new JTextArea(10, 30);
		resultArea.setEditable(false);

		JPanel ipPanel = new JPanel();
		ipPanel.setBounds(10, 50, 365, 46);
		ipPanel.setLayout(null);
		ipPanel.add(ipField1);
		JLabel label = new JLabel(".");
		label.setHorizontalAlignment(label.CENTER);
		label.setBounds(90, 7, 10, 27);
		ipPanel.add(label);
		ipPanel.add(ipField2);
		JLabel label_1 = new JLabel(".");
		label_1.setBounds(180, 7, 10, 27);
		ipPanel.add(label_1);
		label_1.setHorizontalAlignment(label_1.CENTER);
		ipPanel.add(ipField3);
		JLabel label_2 = new JLabel(".");
		label_2.setBounds(270, 5, 10, 30);
		ipPanel.add(label_2);
		label_2.setHorizontalAlignment(label_2.CENTER);
		ipPanel.add(ipField4);

		JPanel subnetMaskPanel = new JPanel();
		subnetMaskPanel.setBounds(10, 136, 365, 46);
		subnetMaskPanel.setLayout(null);
		subnetMaskPanel.add(subnetMaskField1);
		JLabel label_3 = new JLabel(".");
		label_3.setHorizontalAlignment(label.CENTER);
		label_3.setBounds(90, 7, 10, 27);
		subnetMaskPanel.add(label_3);
		subnetMaskPanel.add(subnetMaskField2);
		JLabel label_4 = new JLabel(".");
		label_4.setBounds(180, 7, 10, 27);
		subnetMaskPanel.add(label_4);
		label_1.setHorizontalAlignment(label_4.CENTER);
		subnetMaskPanel.add(subnetMaskField3);
		JLabel label_5 = new JLabel(".");
		label_5.setBounds(270, 5, 10, 30);
		subnetMaskPanel.add(label_5);
		label_2.setHorizontalAlignment(label_5.CENTER);
		subnetMaskPanel.add(subnetMaskField4);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 398, 302);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(null);
		panel.add(ipLabel);
		panel.add(ipPanel);
		panel.add(subnetMaskLabel);
		panel.add(subnetMaskPanel);
		panel.add(cidrLabel);
		panel.add(cidrField);
		panel.add(numSubnetsLabel);
		panel.add(numSubnetsField);
		panel.add(numHostsLabel);
		panel.add(numHostsField);

		getContentPane().setLayout(null);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("[ENTER] Confirma");
		lblNewLabel.setBounds(10, 11, 100, 21);
		panel.add(lblNewLabel);
	}

	private JTextField createIPTextField(JTextField textField) {
		textField.setSize(60, 30);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (textField.getText().length() == 3) {
					textField.transferFocus();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				clearFields();
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		
        Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculateSubnetClass();
				
			}
        };
        textField.addActionListener(action);

		return textField;
	}

	private JTextField createSubnetMaskTextField(JTextField textField) {
		textField.setSize(60, 30);
		textField.setEditable(false);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (textField.getText().length() == 3) {
					textField.transferFocus();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		
        Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculateSubnetByMask();
				
			}
        };
        textField.addActionListener(action);

		return textField;
	}

	public void calculateSubnetClass() {
		if (intervaloIsValid(ipField1.getText(), true) && intervaloIsValid(ipField2.getText(), false)
				&& intervaloIsValid(ipField3.getText(), false) && intervaloIsValid(ipField4.getText(), false)) {
			if (defineClass(Integer.parseInt(ipField1.getText()))) {
				setEditableSubnetMask();
			}
			;
		}
	}

	public void calculateSubnetByMask() {
		try {
		int mask2 = Integer.parseInt(subnetMaskField2.getText());
		int mask3 = Integer.parseInt(subnetMaskField3.getText());
		int mask4 = Integer.parseInt(subnetMaskField4.getText());
		if (ipClass == "C") {
			if (isValidMask(mask4)) {
				cidrField.setText(Integer.toString(24 + bits));
				if (mask4 == 255) {
					numHostsField.setText("0");
				} else {
					numHostsField.setText(Integer.toString((int) ((Math.pow(2, (8 - bits))) - 2)));
				}
				if (mask4 == 0) {
					numSubnetsField.setText("0");	
				} else {
					numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, bits)))));	
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Valor inválido!");
			}
		} else if (ipClass == "B") {
			if (isValidMask(mask3)) {
				if (mask3 < 255 && mask3 >= 0) {
					if (mask4 == 0) {
						cidrField.setText(Integer.toString(16 + (bits)));
						numHostsField.setText(Integer.toString((int) ((Math.pow(2, 8 + (8 - bits))) - 2)));
						if (mask3 == 0) {
							numSubnetsField.setText("0");	
						} else {
							numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, bits)))));
						}
					} else {
						JOptionPane.showMessageDialog(null, "Valor inválido!");
					}
				} else if (mask3 == 255) {
					if (mask4 <= 255 && mask4 >= 0 && isValidMask(mask4)) {
						cidrField.setText(Integer.toString(24 + (bits)));
						if (mask4 == 255) {
							numHostsField.setText("0");
						} else {
							numHostsField.setText(Integer.toString((int) ((Math.pow(2, 8 - bits)) - 2)));
						}
						numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, 8 + bits)))));
					}
				}
			}
		} else if (ipClass == "A") {
			if (isValidMask(mask2)) {
				if (mask2 < 255 && mask2 >= 0) {
					if (mask3 == 0 && mask4 == 0) {
						cidrField.setText(Integer.toString(8 + (bits)));
						numHostsField.setText(Integer.toString((int) ((Math.pow(2, 16 + (8 - bits))) - 2)));
						if (mask2 == 0) {
							numSubnetsField.setText("0");	
						} else {
							numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, bits)))));
						}
					} else {
						JOptionPane.showMessageDialog(null, "Valor inválido!");
					}
				} else if (mask2 == 255 && mask4 == 0) {
					if (mask3 <= 255 && mask3 >= 0 && isValidMask(mask3)) {
						cidrField.setText(Integer.toString(16 + (bits)));
						numHostsField.setText(Integer.toString((int) ((Math.pow(2, 8 + (8 - bits))) - 2)));
						numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, (8 + bits))))));
					}
				} else if (mask2 == 255 && mask3 == 255) {
					if (mask4 <= 255 && mask4 >= 0 && isValidMask(mask4)) {
						cidrField.setText(Integer.toString(24 + (bits)));
						if (mask4 == 255) {
							numHostsField.setText("0");
						} else {
							numHostsField.setText(Integer.toString((int) ((Math.pow(2, 8 - bits)) - 2)));
						}
						numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, 8 + (8 + bits))))));
					}
				}
			}
		}
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Você Digitou um valor inválido!");
		}
	}

	public void calculateSubnetByCIDR() {
		try {
		int cidr = Integer.parseInt(cidrField.getText());
		int bit;
		if (ipClass == "C") {
			bit = cidr - 24;
			if (cidr >= 24 && cidr <= 32 && cidrCalculate(bit)) {
				subnetMaskField4.setText(Integer.toString(mask));
				if (bit == 8) {
					numHostsField.setText("0");
				} else {
					numHostsField.setText(Integer.toString((int) ((Math.pow(2, (8 - bit))) - 2)));
				}
				if (bit == 0) {
					numSubnetsField.setText("0");
				} else {
					numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, bit)))));
				}				

			} else {
				JOptionPane.showMessageDialog(null, "Valor inválido!");
			}
		} else if (ipClass == "B") {
			if (cidr >= 16 && cidr <= 24) {
				bit = cidr - 16;
				if (cidrCalculate(bit)) {
					subnetMaskField3.setText(Integer.toString(mask));
					subnetMaskField4.setText("0");
					numHostsField.setText(Integer.toString((int) ((Math.pow(2, 8 + (8 - bit))) - 2)));
					if (bit == 0) {
						numSubnetsField.setText("0");
					} else {
						numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, bit)))));
					}
				}
			}
			else if (cidr > 24 && cidr <= 32) {
			bit = cidr - 24;
			if (cidrCalculate(bit)) {
				subnetMaskField3.setText("255");
				subnetMaskField4.setText(Integer.toString(mask));
				if (bit == 8) {
					numHostsField.setText("0");
				} else {
				numHostsField.setText(Integer.toString((int) ((Math.pow(2, 8 - bit)) - 2)));
				}
				numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, 8 + bit)))));
			}
		}
		}
		else if (ipClass == "A") {
			if (cidr >= 8 && cidr <= 16) {
				bit = cidr - 8;
				if (cidrCalculate(bit)) {
					subnetMaskField2.setText(Integer.toString(mask));
					subnetMaskField3.setText("0");
					subnetMaskField4.setText("0");
					numHostsField.setText(Integer.toString((int) ((Math.pow(2,16 + (8 - bit))) - 2)));
					if (bit == 0) {
						numSubnetsField.setText("0");
					} else {
						numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, bit)))));
					}
				}
			} else if (cidr > 16 && cidr <= 24) {
				bit = cidr - 16;
				if (cidrCalculate(bit)) {
					subnetMaskField2.setText("255");
					subnetMaskField3.setText(Integer.toString(mask));
					subnetMaskField4.setText("0");
					numHostsField.setText(Integer.toString((int) ((Math.pow(2,8 + (8 - bit))) - 2)));
					numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, 8 + bit)))));
				}
			} else if (cidr > 24 && cidr <= 32) {
				bit = cidr - 24;
				if (cidrCalculate(bit)) {
					subnetMaskField2.setText("255");
					subnetMaskField3.setText("255");
					subnetMaskField4.setText(Integer.toString(mask));
					if (bit == 8) {
						numHostsField.setText("0");
					} else {
					numHostsField.setText(Integer.toString((int) ((Math.pow(2,8 - bit)) - 2)));
					}
					numSubnetsField.setText(Integer.toString((int) ((Math.pow(2, 16 + bit)))));
				}
			}
		}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Você Digitou um valor inválido!");
		}
	}

	public Boolean intervaloIsValid(String value, Boolean firstIp) {
		try {
		if (firstIp) {
			if (Integer.parseInt(value) > 0 && Integer.parseInt(value) <= 223) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Você Digitou um valor inválido!");
				clearFields();
				return false;
			}
		}
		if (Integer.parseInt(value) >= 0 && Integer.parseInt(value) < 256) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Você Digitou um valor inválido!");
			clearFields();
			return false;
		}
		}
		 catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Você Digitou um valor inválido");
			return false;
		}
	}

	public void clearFields() {
		subnetMaskField1.setEditable(false);
		subnetMaskField1.setText("");
		subnetMaskField2.setEditable(false);
		subnetMaskField2.setText("");
		subnetMaskField3.setEditable(false);
		subnetMaskField3.setText("");
		subnetMaskField4.setEditable(false);
		subnetMaskField4.setText("");
		cidrField.setEditable(false);
		cidrField.setText("");
		numHostsField.setEditable(false);
		numHostsField.setText("");
		numSubnetsField.setEditable(false);
		numSubnetsField.setText("");
	}

	public void setEditableSubnetMask() {
		if (ipClass == "A") {
			subnetMaskField1.setEditable(false);
			subnetMaskField1.setText("255");
			subnetMaskField2.setEditable(true);
			subnetMaskField3.setEditable(true);
			subnetMaskField4.setEditable(true);
			cidrField.setEditable(true);
		} else if (ipClass == "B") {
			subnetMaskField1.setEditable(false);
			subnetMaskField1.setText("255");
			subnetMaskField2.setEditable(false);
			subnetMaskField2.setText("255");
			subnetMaskField3.setEditable(true);
			subnetMaskField4.setEditable(true);
			cidrField.setEditable(true);
		} else {
			subnetMaskField1.setEditable(false);
			subnetMaskField1.setText("255");
			subnetMaskField2.setEditable(false);
			subnetMaskField2.setText("255");
			subnetMaskField3.setEditable(false);
			subnetMaskField3.setText("255");
			subnetMaskField4.setEditable(true);
			cidrField.setEditable(true);
		}
	}

	public Boolean defineClass(int value) {
		if (value > 0 && value < 127) {
			ipClass = "A";
			return true;
		} else if (value > 127 && value < 192) {
			ipClass = "B";
			return true;
		} else if (value >= 192 && value <= 223) {
			ipClass = "C";
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "A classe informada é inválida!");
			return false;
		}
	}

	public Boolean isValidMask(int value) {
		int j = 0;
		int z = 0;
		int i = 128;
		for (; j <= 8; j++) {
			if (value == z) {
				bits = j;
				return true;
			}
			z += i;
			i = i / 2;
		}
		JOptionPane.showMessageDialog(null, "Valor da máscara inválido!");
		return false;
	}

	public Boolean cidrCalculate(int bit) {
		int j = 0;
		int z = 0;
		int i = 128;
		for (; j <= bit; j++) {
			if (j == bit) {
				mask = z;
				return true;
			}
			z += i;
			i = i / 2;
		}
		return false;
	}

	public Boolean isPowerOfTwo(int value) {
		return (value > 0) && ((value & (value - 1)) == 0);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SubnetCalculator calculator = new SubnetCalculator();
			calculator.setVisible(true);
		});
	}
}
