package com.fashionstore.util;

import java.sql.SQLException;

public class TestDBConn {
	public static void main(String[] args) {
		try {
			DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
