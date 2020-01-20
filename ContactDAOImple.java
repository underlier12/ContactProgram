package edu.java.contact03;

import java.util.ArrayList;

public class ContactDAOImple implements ContactDAO{
	
	// ========Singleton Pattern====================
	private static ContactDAOImple instance = null;
	
	private ContactDAOImple() {}
	
	public static ContactDAOImple getInstance() {
		if (instance == null) {
			instance = new ContactDAOImple();
		}
		return instance;
	}
	// =============================================

	private ArrayList<ContactVO> list = new ArrayList<>();
	
	public int getSize() {
		return list.size();
	}
	
	@Override
	public int insert(ContactVO vo) {
		list.add(vo);
		return 1;
	}

	@Override
	public ArrayList<ContactVO> search() {
		return list;
	}
	
	@Override
	public ContactVO search(int index) {
		return list.get(index);
	}

	@Override
	public int modify(int index, ContactVO vo) {
		list.set(index, vo);
		return 1;
	}

	@Override
	public int delete(int index) {
		list.remove(index);
		return 1;
	}
	
}
