package memberDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import jdbctest.testjdbcDatasource;

public class MemberDaoJdbcImpl implements IMemberDao {
	private testjdbcDatasource jdbc1;
	private Connection conn;
	private Scanner sca;

	public MemberDaoJdbcImpl() throws Exception {
		jdbc1 = new testjdbcDatasource();
		sca = new Scanner(System.in);
	}

	@Override
	public void signUp() throws SQLException {
		Member m = new Member();
		String sqlstr = "Insert Into member(userName,upwd)Values(?,?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		m.setUserName(sca.nextLine());
		m.setUpwd(sca.nextLine());
		lock(m);
		state.setString(1, m.getUserName());
		state.setString(2, m.getUpwd());
		state.executeUpdate();
	}

	@Override
	public void signin() throws SQLException {
		Member m = new Member();
		String strsql = "Select * From member Where userName=? and upwd=?";
		PreparedStatement state = conn.prepareStatement(strsql);
		
		ResultSet rs;
		do {
			m.setUserName(sca.nextLine());
			m.setUpwd(sca.nextLine());
			lock(m);
			state.setString(1, m.getUserName());
			state.setString(2, m.getUpwd());
			rs=state.executeQuery();
		} while (! rs.next());
		
		System.out.println("welcome back user:"+rs.getInt(1));

	}

	@Override
	public void verified() {
		// TODO Auto-generated method stub

	}

	@Override
	public void lock(Member m) {
		String user = m.getUserName();
		String pwd = m.getUpwd();
		StringBuffer userstb = new StringBuffer();
		StringBuffer pwdstb = new StringBuffer();
		for (int i = 0; i < user.length(); i++) {
			int userint = user.charAt(i) + (i + 1);
			char userchar = (char) userint;
			userstb.append(userchar);
		}
		for (int i = 0; i < pwd.length(); i++) {
			int pwdint = pwd.charAt(i) + (i + 1);
			char pwdchar = (char) pwdint;
			pwdstb.append(pwdchar);
		}
		m.setUserName(userstb.toString());
		m.setUpwd(pwdstb.toString());
	}

	@Override
	public void unlock(Member m) {
		String user = m.getUserName();
		String pwd = m.getUpwd();
		StringBuffer userstb = new StringBuffer();
		StringBuffer pwdstb = new StringBuffer();
		for (int i = 0; i < user.length(); i++) {
			int userint = user.charAt(i) - (i + 1);
			char userchar = (char) userint;
			userstb.append(userchar);
		}
		for (int i = 0; i < pwd.length(); i++) {
			int pwdint = pwd.charAt(i) - (i + 1);
			char pwdchar = (char) pwdint;
			pwdstb.append(pwdchar);
		}
		System.out.println("userName:" + userstb.toString());
		System.out.println("password:" + pwdstb.toString());
	}

	@Override
	public void createConn() throws SQLException {
		conn = jdbc1.getConnection();
//		System.out.println(!conn.isClosed());
	}

	@Override
	public void closeConn() throws SQLException {
		conn.close();
	}

}
