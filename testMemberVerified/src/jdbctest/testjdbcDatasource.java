package jdbctest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class testjdbcDatasource implements DataSource {
	private String url;
	private String user;
	private String pwd;
	private int maxConn;
	private List<Connection> connpools;
	private Connection conn;

	public testjdbcDatasource() throws Exception {
		this("mysqlserverjdbc.properties");

	}

	public testjdbcDatasource(String config) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(config));
		url = prop.getProperty("mySQLUrl");
		user = prop.getProperty("myUser");
		pwd = prop.getProperty("myPwd");
		maxConn = Integer.parseInt(prop.getProperty("maxConn"));
		connpools = Collections.synchronizedList(new ArrayList<Connection>());

	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		if (connpools.isEmpty()) {
			conn = DriverManager.getConnection(url, user, pwd);
			return conn;
		} else {
			return connpools.remove(connpools.size() - 1);

		}

	}

	@Override
	public synchronized Connection getConnection(String username, String password) throws SQLException {
		if (connpools.isEmpty()) {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} else {
			return connpools.remove(connpools.size() - 1);

		}

	}

	public void close() throws SQLException {
		if (connpools.size() == maxConn) {
			conn.close();
		} else {
			connpools.add(conn);
		}
	}

}
