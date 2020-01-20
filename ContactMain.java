package edu.java.contact03;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactMain {

	private static Scanner sc;
	private static ContactDAO dao;
	private static boolean run = true;
	private static int size;
	
	public static void main(String[] args) {
		version();
		sc = new Scanner(System.in);
		dao = ContactDAOImple.getInstance();
		
		int choice = 0;
		
		while(run) {
			showMenu();
			choice = askChoice();
			size = ((ContactDAOImple)dao).getSize();
			
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
			case Menu.MENU_DELETE:
				deleteContact();
				break;
			default:
				plzRechoice();
				break;
			}
			
		}
		
	}

	private static void version() {
		System.out.printf("����ó version %.1f", Menu.VERSION * 0.1, "\n");
		System.out.println();
	}

	private static void showMenu() {
		System.out.println("=======================================================");
		System.out.println("  1. ��� | 2. ��ü�˻� | 3. �󼼰˻� | 4. ���� | 5. ���� | 0. ���� ");
		System.out.println("=======================================================");
		System.out.println("�޴� ���� > ");
	}
	
	private static int askChoice() {
		String str = sc.nextLine();
		
		try {
			int parsedInt = Integer.parseInt(str);
			return parsedInt;
		} catch (Exception e){
			return 9;
		}
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
		if (size == 0) {
			System.out.println(" ============= ����ó ����� ���� ���ּ���  ============= ");
			return;
		}

		ArrayList<ContactVO> list = dao.search();
		for (int i = 0; i < size; i++) {
			System.out.println("============= ����ó ���� [" + i + "] =============");
			System.out.println(list.get(i));
			System.out.println("=======================================");
			System.out.println();
		}
	}

	private static void detailSearch() {
		if (size == 0) {
			System.out.println(" ============= ����ó ����� ���� ���ּ���  ============= ");
			return;
		}
		
		System.out.println(" �˻��� ����ó�� �ε����� �Է� > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		if (!(index >= 0 && index < size)) {
			System.out.println("�ùٸ��� ���� �ε����Դϴ�.");
			return;
		} 
		ContactVO vo = dao.search(index);
		System.out.println("============= ����ó ���� [" + index + "] =============");
		System.out.println(vo);
		System.out.println("=======================================");
		System.out.println();
	}

	private static void modifyContact() {
		if (size == 0) {
			System.out.println(" ============= ����ó ����� ���� ���ּ���  ============= ");
			return;
		}
		
		System.out.println(" ������ ����ó�� �ε����� �Է� > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		if (!(index >= 0 && index < size)) {
			System.out.println("�ùٸ��� ���� �ε����Դϴ�.");
			return;
		} 
		System.out.println("============= ����ó ���� ���� =============");
		System.out.println(" �̸� �Է� > ");
		String name = sc.nextLine();
		System.out.println(" ��ȭ��ȣ �Է� > ");
		String phone = sc.nextLine();
		System.out.println(" �̸��� �Է� > ");
		String email = sc.nextLine();
		System.out.println("======================================");
		
		ContactVO vo = new ContactVO(name, phone, email);
		
		int result = ((ContactDAOImple)dao).modify(index, vo);
		if (result == 1) {
			System.out.println("����ó ���� ����");
			System.out.println();
		} else {
			System.out.println("����ó ���� ����");
			System.out.println();
		}
	}
	

	private static void deleteContact() {
		if (size == 0) {
			System.out.println(" ============= ����ó ����� ���� ���ּ���  ============= ");
			return;
		}
		
		System.out.println(" ������ ����ó�� �ε����� �Է� > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		if(!(index >= 0 && index < size)) {
			System.out.println("�ùٸ��� ���� �ε����Դϴ�.");
			return;
		} 
		
		int result = ((ContactDAOImple)dao).delete(index);
		if (result == 1) {
			System.out.println(" ============= ����ó [" + index + "] ���� ����  ============= ");
		} else {
			System.out.println(" ============= ����ó [" + index + "] ���� ����  ============= ");
		}
	}


	private static void plzRechoice() {
		System.out.println("�޴� ������ �ٽ� ���ֽʽÿ�.");
		System.out.println();
	}

}
