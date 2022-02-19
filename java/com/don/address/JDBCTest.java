package com.don.address;

import java.util.ArrayList;

public class JDBCTest {

	public static void main(String[] args) {
		AddrDB db = new AddrDB();

		ArrayList<Addr> addrList = db.selectDatas();

		for (int i = 0; i < addrList.size(); i++) {
			Addr addr = addrList.get(i);
			System.out.println("번호 : " + addr.getIdx());
			System.out.println("이름 : " + addr.getName());
			System.out.println("주소 : " + addr.getAddress());
			System.out.println("번호 : " + addr.getPhone());
			System.out.println("======================");
		}
	}

}
