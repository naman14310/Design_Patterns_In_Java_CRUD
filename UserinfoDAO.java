package com.crud.dao;

import com.crud.dto.UserinfoDTO;
import com.crud.util.DButil;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserinfoDAO {

	private static final String Q_INSERT_INTO_USERINFO = "insert into userinfo (id, first_name, last_name, dob, email, contact_no, gender, address, city, pincode, hobbies) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private static final String Q_UPDATE_INTO_USERINFO = "update userinfo set first_name=?, last_name=?, dob=?, email=?, contact_no=?, gender=?, address=?, city=?, pincode=?, hobbies=? where id = ?";

	private static final String Q_SELECT_ALL_USERINFO_BY_ID = "select * from userinfo where id=?";

	private static final String Q_SELECT_ALL_USER = "select * from userinfo";

	private static final String Q_DELETE_USER_BY_ID = "delete from userinfo where id=?";


	public static int insertUser(UserinfoDTO userinfoDTO) throws Exception {
		int count = 0;
		int id = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = DButil.getLocalDBConnection();
		pstmt = conn.prepareStatement(Q_INSERT_INTO_USERINFO);

		pstmt.setInt(1, id);
		pstmt.setString(2, userinfoDTO.getFirst_name());
		pstmt.setString(3, userinfoDTO.getLast_name());
		pstmt.setString(4, userinfoDTO.getDob());
		pstmt.setString(5, userinfoDTO.getEmail());
		pstmt.setString(6, userinfoDTO.getContact_no());
		pstmt.setString(7, userinfoDTO.getGender());
		pstmt.setString(8, userinfoDTO.getAddress());
		pstmt.setString(9, userinfoDTO.getCity());
		pstmt.setInt(10, userinfoDTO.getPincode());
		pstmt.setString(11, userinfoDTO.getHobbies());

		id++;

		count = pstmt.executeUpdate();
		DButil.close(rs, pstmt, conn);
		return count;

	}

	public static UserinfoDTO searchUserinfoById(int id) throws Exception {

		UserinfoDTO userinfoDTO = new UserinfoDTO();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DButil.getLocalDBConnection();

		pstmt = conn.prepareStatement(Q_SELECT_ALL_USERINFO_BY_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			userinfoDTO.setFirst_name(rs.getString("first_name"));
			userinfoDTO.setLast_name(rs.getString("last_name"));
			userinfoDTO.setDob(rs.getString("dob"));
			userinfoDTO.setEmail(rs.getString("Email"));
			userinfoDTO.setContact_no(rs.getString("contact_no"));
			userinfoDTO.setGender(rs.getString("gender"));
			userinfoDTO.setAddress(rs.getString("address"));
			userinfoDTO.setCity(rs.getString("city"));
			userinfoDTO.setPincode(rs.getInt("pincode"));
			userinfoDTO.setHobbies(rs.getString("hobbies"));
		}
		DButil.close(rs, pstmt, conn);

		return userinfoDTO;

	}

	public static List<UserinfoDTO> getAllUsers() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DButil.getLocalDBConnection();
		List<UserinfoDTO> userinfoDTOList = new ArrayList<>();
		pstmt = conn.prepareStatement(Q_SELECT_ALL_USER);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			UserinfoDTO userinfoDTO = new UserinfoDTO();
			userinfoDTO.setId(rs.getInt("id"));
			userinfoDTO.setFirst_name(rs.getString("first_name"));
			userinfoDTO.setLast_name(rs.getString("last_name"));
			userinfoDTO.setDob(rs.getString("dob"));
			userinfoDTO.setEmail(rs.getString("Email"));
			userinfoDTO.setContact_no(rs.getString("contact_no"));
			userinfoDTO.setGender(rs.getString("gender"));
			userinfoDTO.setAddress(rs.getString("address"));
			userinfoDTO.setCity(rs.getString("city"));
			userinfoDTO.setPincode(rs.getInt("pincode"));
			userinfoDTO.setHobbies(rs.getString("hobbies"));
			userinfoDTOList.add(userinfoDTO);
		}
		DButil.close(rs, pstmt, conn);

		return userinfoDTOList;

	}


	public static int updateUser(UserinfoDTO userinfoDTO) throws Exception {
		int count = 0;
		boolean comma = false;

		String T_update = "update userinfo set ";

                //update using different logics

		if (userinfoDTO.getFirst_name() != null) {
			T_update.concat(" first_name = ?");
			T_update.concat(", ");
			comma = true;
		}

		if (userinfoDTO.getLast_name() != null) {
			T_update.concat(" last_name = ? ");
			if (!comma) {
				T_update.concat(", ");
				comma = true;
			}
		}
		if (userinfoDTO.getGender() != null) {
			T_update.concat(" gender = ? ");
			if (!comma) {
				T_update.concat(", ");
				comma = true;
			}
		}
		T_update.concat(" where id = ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = DButil.getLocalDBConnection();
		pstmt = conn.prepareStatement(Q_UPDATE_INTO_USERINFO);

		pstmt.setString(1, userinfoDTO.getFirst_name());
		pstmt.setString(2, userinfoDTO.getLast_name());
		pstmt.setString(3, userinfoDTO.getDob());
		pstmt.setString(4, userinfoDTO.getEmail());
		pstmt.setString(5, userinfoDTO.getContact_no());
		pstmt.setString(6, userinfoDTO.getGender());
		pstmt.setString(7, userinfoDTO.getAddress());
		pstmt.setString(8, userinfoDTO.getCity());
		pstmt.setInt(9, userinfoDTO.getPincode());
		pstmt.setString(10, userinfoDTO.getHobbies());
		pstmt.setInt(11, userinfoDTO.getId());

		count = pstmt.executeUpdate();
		DButil.close(rs, pstmt, conn);
		return count;

	}

	public static int deleteUserById(int id) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DButil.getLocalDBConnection();

		pstmt = conn.prepareStatement(Q_DELETE_USER_BY_ID);
		pstmt.setInt(1, id);
		int i = pstmt.executeUpdate();

		DButil.close(rs, pstmt, conn);

		return i;

	}

}
