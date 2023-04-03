package br.com.crud.util;

import java.text.DecimalFormat;

public class ManipulaValor {

	public ManipulaValor() {

	}

	public String formataValortoString(double valor) {
		DecimalFormat deci = new DecimalFormat("0.00");
		String num = String.valueOf(deci.format(valor));
		num = num.replace(".", ",");
		return num;
	}

	public double formataStringtoValor(String valor) {
		double num = 0;
		valor = valor.replaceAll(",", ".");
		DecimalFormat deci = new DecimalFormat("0.00");

		num = Double.parseDouble(valor);
		valor = deci.format(num);

		valor = valor.replaceAll(",", ".");
		num = Double.parseDouble(valor);

		return num;
	}

}
