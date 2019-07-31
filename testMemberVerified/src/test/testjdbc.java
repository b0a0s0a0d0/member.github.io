package test;

import java.sql.Connection;

import memberDao.MemberDaoJdbcImpl;

public class testjdbc {

	public static void main(String[] args) throws Exception {
		MemberDaoJdbcImpl jdbc = new MemberDaoJdbcImpl();
		jdbc.createConn();
//		sjdbc.signUp();
		jdbc.signin();
		jdbc.closeConn();
	}

}
