package edu.java.contact02;

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
	
	public static final int MAX = 100;
	private ContactVO[] list = new ContactVO[MAX];
	private int count;
	
	public int getCount() {
		return count;
	}
	
	@Override
	public int insert(ContactVO vo) {
		list[count] = vo;
		count++;
		return 1;
	}

	@Override
	public ContactVO[] search() {
		return list;
	}
	
	@Override
	public ContactVO search(int index) {
		return list[index];
	}

	@Override
	public int modify(int index, ContactVO vo) {
		list[index].setEmail(vo.getEmail());
		list[index].setPhone(vo.getPhone());
		return 1;
	}
	
}
