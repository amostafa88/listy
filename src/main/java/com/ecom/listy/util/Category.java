package com.ecom.listy.util;

import java.util.List;

public class Category {
	
	private String name_en;
	private String name_ar;
	private List<Item> items;
	
	
	
	
	@Override
	public String toString() {
		return "Category [name_en=" + name_en + ", name_ar=" + name_ar
				+ ",\n items=" + items + "]";
	}



	public String getName_en() {
		return name_en;
	}



	public void setName_en(String name_en) {
		this.name_en = name_en;
	}



	public String getName_ar() {
		return name_ar;
	}



	public void setName_ar(String name_ar) {
		this.name_ar = name_ar;
	}



	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}



	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Category(String name_en, String name_ar, List<Item> items) {
		super();
		this.name_en = name_en;
		this.name_ar = name_ar;
		this.items = items;
	}
	
	
	

}
