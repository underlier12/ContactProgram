package edu.java.contact01;

import java.util.Scanner;

public class Main {
	
	public static final int MENU_QUIT = 0; // 종료
	public static final int MENU_INSERT = 1; // 등록
	public static final int MENU_TOTAL_SEARCH = 2; // 전체 검색
	public static final int MENU_DETAIL_SEARCH = 3; // 상세 검색
	public static final int MENU_MODIFY = 4; // 수정
	
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
				System.out.println("올바른 메뉴를 선택해주세요.");
			}
		}
	}
	
	public static void version() {
		System.out.println("연락처 version 0.1");
	}
	
	public static void quit() {
		run = false;
		System.out.println("프로그램이 종료됩니다.");
		sc.close();
	}
	
	public static void menuBar() {
		System.out.println("================================================");
		System.out.println("  1. 등록 | 2. 전체검색 | 3. 상세검색 | 4. 수정 | 0. 종료 ");
		System.out.println("================================================");
	}
	
	public static int askMenuNumber() {
		System.out.println("선택 > ");
		int amn = sc.nextInt();
		sc.nextLine(); // 버퍼 비우기
		return amn;
	}
	
	public static void insertContact() {
		contactList[index] = new Contact();
		
		System.out.println("이름 > ");
		String name = sc.nextLine();
		contactList[index].setName(name);
		System.out.println("전화번호 > ");
		String phone = sc.nextLine();
		contactList[index].setPhone(phone);
		System.out.println("이메일 > ");
		String email = sc.nextLine();
		contactList[index].setEmail(email);
		index++;
		System.out.println("등록이 완료되었습니다.");
		System.out.println();
	}
	
	// 다른 방법
	// Contact c = new Contact(name, phone, email); 
	// contactList[index] = c;
	
	public static void totalSearch() {
		if (index == 0) {
			System.out.println("학생 정보를 먼저 입력해 주십시오.");
			System.out.println();
		} else {
			for (int i = 0; i < index; i++) {
				System.out.println("=============== " + i + "번째 연락처 정보 =================");
				System.out.println("이름 : " + contactList[i].getName());
				System.out.println("전화번호 : " + contactList[i].getPhone());
				System.out.println("이메일 : " + contactList[i].getEmail());
				System.out.println();
			}
		}
		
	}
	
	public static void detailSearch() {
		if (index == 0) {
			System.out.println("학생 정보를 먼저 입력해 주십시오.");
			System.out.println();
		} else {
			System.out.println("인덱스 > ");
			searchIndex = sc.nextInt();
			sc.nextLine(); // 버퍼 비우기
			
			if (searchIndex >= index) {
				System.out.println("찾고자 하는 인덱스가 존재하지 않습니다.");
				System.out.println();
			} else {
				System.out.println("============== " + searchIndex + "번째 연락처 정보 ================");
				System.out.println("이름 : " + contactList[searchIndex].getName());
				System.out.println("전화번호 : " + contactList[searchIndex].getPhone());
				System.out.println("이메일 : " + contactList[searchIndex].getEmail());
				System.out.println();
			}
		}
	}
	
	public static void modify() {
		if (index == 0) {
			System.out.println("학생 정보를 먼저 입력해 주십시오.");
			System.out.println();
		} else {
			System.out.println("인덱스 > ");
			modifyIndex = sc.nextInt();
			sc.nextLine(); // 버퍼 비우기
			
			if (modifyIndex >= index) {
				System.out.println("수정하고자 하는 인덱스가 존재하지 않습니다.");
				System.out.println();
			} else {
				System.out.println("============== " + modifyIndex + "번째 연락처 수정 ================");
				System.out.println("이름 > ");
				contactList[modifyIndex].setName(sc.nextLine());
				System.out.println("전화번호 > ");
				contactList[modifyIndex].setPhone(sc.nextLine());
				System.out.println("이메일 > ");
				contactList[modifyIndex].setEmail(sc.nextLine());
				System.out.println("연락처 수정이 완료되었습니다.");
				System.out.println();
			}
		}
	}	
}

