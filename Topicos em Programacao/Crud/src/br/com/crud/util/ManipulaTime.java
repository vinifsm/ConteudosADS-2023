package br.com.crud.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ManipulaTime {


	public static boolean isDate(String dateStr) {
		// JOptionPane.showMessageDialog(null, dateStr.length());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();

		String dia;
		String mes;
		String ano;
		try {
			String[] data = dateStr.split("/");
			dia = data[0];
			mes = data[1];
			ano = data[2];
			Integer.parseInt(dia);
			Integer.parseInt(mes);
			Integer.parseInt(ano);
		} catch (NumberFormatException e) {
			return false;
		} catch (Exception e) {
			return false;
		}

		try {
			cal.setTime(df.parse(dateStr));
		} catch (ParseException e) {
			return false;
		}
		if ((new Integer(dia)).intValue() != (new Integer(cal.get(Calendar.DAY_OF_MONTH))).intValue()) {
			// dia nao casou
			return (false);
		} else if ((new Integer(mes)).intValue() != (new Integer(cal.get(Calendar.MONTH) + 1)).intValue()) {
			// mes nao casou
			return (false);
		} else if ((new Integer(ano)).intValue() != (new Integer(cal.get(Calendar.YEAR))).intValue()) {
			// ano nao casou
			return (false);
		}

		return (true);
	}

	
/***
 * "dd/MM/yyyy HH:mm:ss"
 * @param data
 * @return
 */
	public Date convertStringToDate(String data) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = (Date) formatter.parse(data);
		} catch (ParseException e) {
		}
		return date;
	}
	
	public Date convertStringToDate(String data, String modelo) {
		DateFormat formatter = new SimpleDateFormat(modelo);
		Date date = null;
		try {
			date = (Date) formatter.parse(data);
		} catch (ParseException e) {
		}
		return date;
	}
	
	
	/**
	 * @return retorna uma string com a data no formato dd/MM/yyyy
	 */
	public String convertDatatoString(Date data) {
		String strdata;
		java.text.SimpleDateFormat formata = new java.text.SimpleDateFormat("dd/MM/yyyy");
		strdata = formata.format(data);

		return strdata;
	}
	
	
	public String convertDatatoString(Date data, String modelo) {
		String strdata;
		java.text.SimpleDateFormat formata = new java.text.SimpleDateFormat(modelo);
		strdata = formata.format(data);

		return strdata;
	}
 }