package org.vitu.jpa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HaveFunWithDate {

	public static void main(String[] args) throws ParseException {

		String s = "04/03/1951";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateDeNaissance = dateFormat.parse(s);
		System.out.println("Date de naissance = " +  dateDeNaissance);
		
		DateFormat format = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
		String dateFormatee = format.format(dateDeNaissance);
		System.out.println("Date formatée = " +  dateFormatee);

	}

}
