package edu.java.contact03;

import java.util.ArrayList;

public interface ContactDAO {
	// ����ó ��� ���
	public abstract int insert(ContactVO vo);
	
	// ����ó ��ü �˻� ���
	public abstract ArrayList<ContactVO> search();
	
	// ����ó �˻� ���
	public abstract ContactVO search(int index);
	
	// ����ó ���� ���
	public abstract int modify(int index, ContactVO vo);
	
	// ����ó ���� ���
	public abstract int delete(int index);
}
