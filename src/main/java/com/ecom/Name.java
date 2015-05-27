package com.ecom;

import java.io.Serializable;

public class Name implements Serializable{

	public String en;
	private String ar;
	
	public Name(String en, String ar) {
		super();
		this.en = en;
		this.ar = ar;
	}

	public Name() {
		super();
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	@Override
	public String toString() {
		return "Name [en=" + en + ", ar=" + ar + "]";
	}

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}
	
	
	
}
