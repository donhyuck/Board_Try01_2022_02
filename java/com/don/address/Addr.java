package com.don.address;

public class Addr {

	private int idx;
	private String name;
	private String address;
	private String phone;

	public Addr(int idx, String name, String address, String phone) {
		super();
		this.idx = idx;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
