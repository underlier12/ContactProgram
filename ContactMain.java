package edu.java.contact02;

import java.util.Scanner;

public class ContactMain {

	private static Scanner sc;
	private static ContactDAO dao;
	private static boolean run = true;
	
	public static void main(String[] args) {
		version();
		sc = new Scanner(System.in);
		dao = ContactDAOImple.getInstance();
		
		int choice = 0;
		
		while(run) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
			case Menu.MENU_QUIT:
				quit();
				break;
			case Menu.MENU_INSERT:
				insertContact();
				break;
			case Menu.MENU_TOTAL_SEARCH:
				totalSearch();
				break;
			case Menu.MENU_DETAIL_SEARCH:
				detailSearch();
				break;
			case Menu.MENU_MODIFY:
				modifyContact();
				break;
			default:
				plzRechoice();
				break;
			}
			
		}
		
	}

	private static void version() {
		System.out.println("����ó version 0.2");
		System.out.println();
	}

	private static void showMenu() {
		System.out.println("================================================");
		System.out.println("  1. ��� | 2. ��ü�˻� | 3. �󼼰˻� | 4. ���� | 0. ���� ");
		System.out.println("================================================");
		System.out.println("�޴� ���� > ");
	}

	private static void quit() {
		run = false;
		System.out.println("���α׷��� ����˴ϴ�.");
	}

	private static void insertContact() {
		System.out.println("============= ����ó ���� ��� =============");
		System.out.println(" �̸� �Է� > ");
		String name = sc.nextLine();
		System.out.println(" ��ȭ��ȣ �Է� > ");
		String phone = sc.nextLine();
		System.out.println(" �̸��� �Է� > ");
		String email = sc.nextLine();
		System.out.println("======================================");
		
		// �Է¹��� �����ͷ� ContactVO Ŭ������ �ν��Ͻ� ����
		ContactVO vo = new ContactVO(name, phone, email);
		int result = dao.insert(vo);
		if(result == 1) {
			System.out.println("����ó ��� ����");
		} else {
			System.out.println("����ó ��� ����");
		}
		System.out.println();
	}

	private static void totalSearch() {
		ContactVO[] list = dao.search();
		int count = ((ContactDAOImple)dao).getCount();
		
		for (int i = 0; i < count; i++) {
			System.out.println("============= ����ó ���� [" + i + "] =============");
			System.out.println(list[i]);
			System.out.println("=======================================");
			System.out.println();
		}
	}

	private static void detailSearch() {
		System.out.println(" �˻��� ����ó�� �ε����� �Է� > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		int count = ((ContactDAOImple)dao).getCount();
		ContactVO vo = dao.search(index);
		
		if (index >= 0 && index < count) {
			System.out.println("============= ����ó ���� [" + index + "] =============");
			System.out.println(vo);
			System.out.println("=======================================");
		} else {
			System.out.println("�ùٸ��� ���� �ε����Դϴ�.");
		}
		System.out.println();
	}

	private static int modifyContact() {
		System.out.println(" ������ ����ó�� �ε����� �Է� > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		int count = ((ContactDAOImple)dao).getCount();
		
		if (!(index >= 0 && index < count)) {
			System.out.println("�ùٸ��� ���� �ε����Դϴ�.");
			return 0;
		} 
		System.out.println("============= ����ó ���� ���� =============");
		System.out.println(" ��ȭ��ȣ �Է� > ");
		String phone = sc.nextLine();
		System.out.println(" �̸��� �Է� > ");
		String email = sc.nextLine();
		System.out.println("======================================");
		
		ContactVO vo = new ContactVO();
		vo.setPhone(phone);
		vo.setEmail(email);
		
		int result = ((ContactDAOImple)dao).modify(index, vo);
		if (result == 1) {
			System.out.println(" ����ó ���� ����");
		} else {
			System.out.println(" ����ó ���� ����");
		}
		return 1;
	}

	private static void plzRechoice() {
		System.out.println("�޴� ������ �ٽ� ���ֽʽÿ�.");
		System.out.println();
	}

}
