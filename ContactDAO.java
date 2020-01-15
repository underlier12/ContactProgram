package edu.java.contact02;

public interface ContactDAO {
	// ����ó ��� ���
	public abstract int insert(ContactVO vo);
	
	// ����ó ��ü �˻� ���
	public abstract ContactVO[] search();
	
	// ����ó �˻� ���
	public abstract ContactVO search(int index);
	
	// ����ó ���� ���
	public abstract int modify(int index, ContactVO vo);
	
}
