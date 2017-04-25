package cambio.precriptionrecord.model;

import java.util.Date;

public class Patient {
	String name;
	String address;
	Date birthday;
	String tp;
	public Patient(String name,String address,Date birthday,String tp){
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.tp = tp;
	}
}
