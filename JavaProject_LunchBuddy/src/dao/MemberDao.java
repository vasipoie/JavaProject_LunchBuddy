package dao;

import java.util.Map;

import util.JDBCUtil;

public class MemberDao {
	JDBCUtil jdbc = JDBCUtil.getInstance();
	private static MemberDao singleTon = null;

	public MemberDao() {
	};

	public static MemberDao getInstance() {
		if (singleTon == null) {
			singleTon = new MemberDao();
		}
		return singleTon;
	}

	public Map<String,Object> idcheck(String id) {
		String sql = "select * from member\r\n" + 
				"where mem_id = '"+id+"'";
		Map<String,Object> mem = jdbc.selectOne(sql);
		return mem;
	}

}
