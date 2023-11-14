package dao;

import java.util.List;
import java.util.Map;

import controller.Controller;
import util.ConvertUtils;
import util.JDBCUtil;
import vo.MemberVo;

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

	public void join(List<Object> param) {
		String sql = "insert into member(mem_no, mem_id, mem_pw, mem_name, mem_phone, mem_nick, mem_idQue, mem_idAns)\r\n" + 
				"values(lpad((select max(mem_no)+1 from member),5,'0'), ? , ? , ? , ? , ? , ? , ? )";
		jdbc.update(sql, param);
	}

	public MemberVo log_in(List<Object> param) {
		String sql = "select * from member where mem_id = ? and mem_pw = ?";
		Map<String,Object> mem = jdbc.selectOne(sql, param);
		if(mem==null) return null;
		return ConvertUtils.convertToVo(mem, MemberVo.class);
	}

	public void update_pw(String pw, String mem_no) {
		String sql = "update member\r\n" + 
				"set mem_pw = '" + pw + 
				"' where mem_no = '" + mem_no + "'";
		jdbc.update(sql);
	}

	public void update_name(String name, String mem_no) {
		String sql = "update member\r\n" + 
				"set mem_name = '" + name + 
				"' where mem_no = '" + mem_no + "'";
		jdbc.update(sql);
	}

	public void update_phone(String phone, String mem_no) {
		String sql = "update member\r\n" + 
				"set mem_phone = '" + phone + 
				"' where mem_no = '" + mem_no + "'";
		jdbc.update(sql);
	}

	public void update_nick(String nick, String mem_no) {
		String sql = "update member\r\n" + 
				"set mem_nick = '" + nick + 
				"' where mem_no = '" + mem_no + "'";
		jdbc.update(sql);
	}

	public void update_idque(String idque, String mem_no) {
		String sql = "update member\r\n" + 
				"set mem_idque = '" + idque + 
				"' where mem_no = '" + mem_no + "'";
		jdbc.update(sql);
	}

	public void update_idans(String idans, String mem_no) {
		String sql = "update member\r\n" + 
				"set mem_idans = '" + idans + 
				"' where mem_no = '" + mem_no + "'";
		jdbc.update(sql);
	}

	public void byebye(String mem_no) {
		String sql = "update member\r\n" + 
				"set mem_delyn = 'Y' " +
				" where mem_no = '" + mem_no + "'";
		jdbc.update(sql);
		
	}

	public MemberVo update(String mem_no) {
		String sql = "select * from member where mem_no = '"+mem_no+"'";
		Map<String,Object> mem = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(mem, MemberVo.class);
	}

	public MemberVo find(List<Object> param) {
		String sql = "select * from member\r\n" + 
				"where mem_name = ?\r\n" + 
				"and mem_phone = ?";
		Map<String,Object> mem = jdbc.selectOne(sql,param);
		if(mem == null) return null;
		return ConvertUtils.convertToVo(mem, MemberVo.class);
	}

	public MemberVo find_pw(String id) {
		String sql = "select * from member\r\n" + 
				"where mem_id = '" + id + "'";
		Map<String,Object> mem = jdbc.selectOne(sql);
		if(mem == null) return null;
		return ConvertUtils.convertToVo(mem, MemberVo.class);
	}


}
