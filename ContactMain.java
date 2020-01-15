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
		System.out.println("연락처 version 0.2");
		System.out.println();
	}

	private static void showMenu() {
		System.out.println("================================================");
		System.out.println("  1. 등록 | 2. 전체검색 | 3. 상세검색 | 4. 수정 | 0. 종료 ");
		System.out.println("================================================");
		System.out.println("메뉴 선택 > ");
	}

	private static void quit() {
		run = false;
		System.out.println("프로그램이 종료됩니다.");
	}

	private static void insertContact() {
		System.out.println("============= 연락처 정보 등록 =============");
		System.out.println(" 이름 입력 > ");
		String name = sc.nextLine();
		System.out.println(" 전화번호 입력 > ");
		String phone = sc.nextLine();
		System.out.println(" 이메일 입력 > ");
		String email = sc.nextLine();
		System.out.println("======================================");
		
		// 입력받은 데이터로 ContactVO 클래스의 인스턴스 생성
		ContactVO vo = new ContactVO(name, phone, email);
		int result = dao.insert(vo);
		if(result == 1) {
			System.out.println("연락처 등록 성공");
		} else {
			System.out.println("연락처 등록 실패");
		}
		System.out.println();
	}

	private static void totalSearch() {
		ContactVO[] list = dao.search();
		int count = ((ContactDAOImple)dao).getCount();
		
		for (int i = 0; i < count; i++) {
			System.out.println("============= 연락처 정보 [" + i + "] =============");
			System.out.println(list[i]);
			System.out.println("=======================================");
			System.out.println();
		}
	}

	private static void detailSearch() {
		System.out.println(" 검색할 연락처의 인덱스를 입력 > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		int count = ((ContactDAOImple)dao).getCount();
		ContactVO vo = dao.search(index);
		
		if (index >= 0 && index < count) {
			System.out.println("============= 연락처 정보 [" + index + "] =============");
			System.out.println(vo);
			System.out.println("=======================================");
		} else {
			System.out.println("올바르지 않은 인덱스입니다.");
		}
		System.out.println();
	}

	private static int modifyContact() {
		System.out.println(" 수정할 연락처의 인덱스를 입력 > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		int count = ((ContactDAOImple)dao).getCount();
		
		if (!(index >= 0 && index < count)) {
			System.out.println("올바르지 않은 인덱스입니다.");
			return 0;
		} 
		System.out.println("============= 연락처 정보 수정 =============");
		System.out.println(" 전화번호 입력 > ");
		String phone = sc.nextLine();
		System.out.println(" 이메일 입력 > ");
		String email = sc.nextLine();
		System.out.println("======================================");
		
		ContactVO vo = new ContactVO();
		vo.setPhone(phone);
		vo.setEmail(email);
		
		int result = ((ContactDAOImple)dao).modify(index, vo);
		if (result == 1) {
			System.out.println(" 연락처 수정 성공");
		} else {
			System.out.println(" 연락처 수정 실패");
		}
		return 1;
	}

	private static void plzRechoice() {
		System.out.println("메뉴 선택을 다시 해주십시오.");
		System.out.println();
	}

}
