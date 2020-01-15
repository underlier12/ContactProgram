package edu.java.contact02;

public interface ContactDAO {
	// 연락처 등록 기능
	public abstract int insert(ContactVO vo);
	
	// 연락처 전체 검색 기능
	public abstract ContactVO[] search();
	
	// 연락처 검색 기능
	public abstract ContactVO search(int index);
	
	// 연락처 수정 기능
	public abstract int modify(int index, ContactVO vo);
	
}
