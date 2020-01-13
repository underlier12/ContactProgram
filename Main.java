package edu.java.contact01;

import java.util.Scanner;

public class Main {
	
	public static final int MENU_QUIT = 0; // ����
	public static final int MENU_INSERT = 1; // ���
	public static final int MENU_TOTAL_SEARCH = 2; // ��ü �˻�
	public static final int MENU_DETAIL_SEARCH = 3; // �� �˻�
	public static final int MENU_MODIFY = 4; // ����
	
	private static int index;
	private static int menuNumber;
	private static int searchIndex;
	private static int modifyIndex;
	private static boolean run = true;
	
	public static Scanner sc = new Scanner(System.in);
	
	private static Contact[] contactList = new Contact[100];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		version();
		
		while(run) {
			menuBar();
			menuNumber = askMenuNumber();
			
			switch(menuNumber) {
			case MENU_QUIT:
				quit();
				break;
			case MENU_INSERT:
				insertContact();
				break;
			case MENU_TOTAL_SEARCH:
				totalSearch();
				break;
			case MENU_DETAIL_SEARCH:
				detailSearch();
				break;
			case MENU_MODIFY:
				modify();
				break;
			default:
				System.out.println("�ùٸ� �޴��� �������ּ���.");
			}
		}
	}
	
	public static void version() {
		System.out.println("����ó version 0.1");
	}
	
	public static void quit() {
		run = false;
		System.out.println("���α׷��� ����˴ϴ�.");
		sc.close();
	}
	
	public static void menuBar() {
		System.out.println("================================================");
		System.out.println("  1. ��� | 2. ��ü�˻� | 3. �󼼰˻� | 4. ���� | 0. ���� ");
		System.out.println("================================================");
	}
	
	public static int askMenuNumber() {
		System.out.println("���� > ");
		int amn = sc.nextInt();
		sc.nextLine(); // ���� ����
		return amn;
	}
	
	public static void insertContact() {
		contactList[index] = new Contact();
		
		System.out.println("�̸� > ");
		String name = sc.nextLine();
		contactList[index].setName(name);
		System.out.println("��ȭ��ȣ > ");
		String phone = sc.nextLine();
		contactList[index].setPhone(phone);
		System.out.println("�̸��� > ");
		String email = sc.nextLine();
		contactList[index].setEmail(email);
		index++;
		System.out.println("����� �Ϸ�Ǿ����ϴ�.");
		System.out.println();
	}
	
	// �ٸ� ���
	// Contact c = new Contact(name, phone, email); 
	// contactList[index] = c;
	
	public static void totalSearch() {
		if (index == 0) {
			System.out.println("�л� ������ ���� �Է��� �ֽʽÿ�.");
			System.out.println();
		} else {
			for (int i = 0; i < index; i++) {
				System.out.println("=============== " + i + "��° ����ó ���� =================");
				System.out.println("�̸� : " + contactList[i].getName());
				System.out.println("��ȭ��ȣ : " + contactList[i].getPhone());
				System.out.println("�̸��� : " + contactList[i].getEmail());
				System.out.println();
			}
		}
		
	}
	
	public static void detailSearch() {
		if (index == 0) {
			System.out.println("�л� ������ ���� �Է��� �ֽʽÿ�.");
			System.out.println();
		} else {
			System.out.println("�ε��� > ");
			searchIndex = sc.nextInt();
			sc.nextLine(); // ���� ����
			
			if (searchIndex >= index) {
				System.out.println("ã���� �ϴ� �ε����� �������� �ʽ��ϴ�.");
				System.out.println();
			} else {
				System.out.println("============== " + searchIndex + "��° ����ó ���� ================");
				System.out.println("�̸� : " + contactList[searchIndex].getName());
				System.out.println("��ȭ��ȣ : " + contactList[searchIndex].getPhone());
				System.out.println("�̸��� : " + contactList[searchIndex].getEmail());
				System.out.println();
			}
		}
	}
	
	public static void modify() {
		if (index == 0) {
			System.out.println("�л� ������ ���� �Է��� �ֽʽÿ�.");
			System.out.println();
		} else {
			System.out.println("�ε��� > ");
			modifyIndex = sc.nextInt();
			sc.nextLine(); // ���� ����
			
			if (modifyIndex >= index) {
				System.out.println("�����ϰ��� �ϴ� �ε����� �������� �ʽ��ϴ�.");
				System.out.println();
			} else {
				System.out.println("============== " + modifyIndex + "��° ����ó ���� ================");
				System.out.println("�̸� > ");
				contactList[modifyIndex].setName(sc.nextLine());
				System.out.println("��ȭ��ȣ > ");
				contactList[modifyIndex].setPhone(sc.nextLine());
				System.out.println("�̸��� > ");
				contactList[modifyIndex].setEmail(sc.nextLine());
				System.out.println("����ó ������ �Ϸ�Ǿ����ϴ�.");
				System.out.println();
			}
		}
	}	
}

