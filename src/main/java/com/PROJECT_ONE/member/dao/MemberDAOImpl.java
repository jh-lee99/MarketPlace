package com.PROJECT_ONE.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.PROJECT_ONE.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	SqlSession sqlSession;
	
	@Override
	public void insertNewMember(MemberVO memberVO) throws Exception{
		sqlSession.insert("mapper.member.insertNewMember",memberVO);	//mapper폴더에 있는 member.xml의 insertNewMember구문 실행
	}
	
	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String result =  sqlSession.selectOne("mapper.member.selectOverlappedID",id);	//selectOverlappedID 구문 실행
		return result;
	}
}