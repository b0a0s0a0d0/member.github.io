package memberDao;

import java.sql.SQLException;

public interface IMemberDao {
	public void createConn() throws SQLException;

	public void closeConn() throws SQLException;

	public void signUp() throws SQLException;

	public void signin() throws SQLException;

	public void verified();

	public void lock(Member m);

	public void unlock(Member m);
}
