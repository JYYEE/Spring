package kr.or.ddit.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public class MemberDAOImpl implements IMemberDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertMember(MemberVO memberVO) {
		return sqlSession.insert("Member.insertMember", memberVO);
	}
	
	@Override
	public MemberVO loginCheck(MemberVO memberVO) {
		return sqlSession.selectOne("Member.loginCheck", memberVO);
	}
	
	@Override
	public List<MemberVO> selectMember() {
		return sqlSession.selectList("Member.selectMember");
	}
}
