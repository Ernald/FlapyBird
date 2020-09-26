package com.company;

import java.util.ArrayList;

public class PersonDAO {
	public static ArrayList<Person> fillIn() {
		ArrayList<Person> list = new ArrayList<>();
		
		list.add(new Person("tom", "hanks", 55));
		list.add(new Person("edi", "rama", 51));
		list.add(new Person("Ernald", "Zykaj", 20));
		
		return list;
		
	}

}
