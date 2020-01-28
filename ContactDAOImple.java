package edu.java.contact04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ContactDAOImple implements ContactDAO{
	
	// ========Singleton Pattern====================
	private static ContactDAOImple instance = null;
	
	private ContactDAOImple() {
		// 데이터 변경 사항 저장을 위해 ContactDAOImple 
		// 생성자를 불러오면 폴더 경로와 파일을 생성
		initDataDir();
		initDataFile();
	}
	
	public static ContactDAOImple getInstance() {
		if (instance == null) {
			instance = new ContactDAOImple();
		}
		return instance;
	}
	// =============================================
	
	// list 사용 전 인스턴스 생성
	public ArrayList<ContactVO> list; 
	
	// 데이터를 저장할 폴더와 파일 이름 정의
	private static final String DATA_DIR = "data";
	private static final String DATA_FILE = "contact.data";
	
	// 데이터 폴더와 파일을 사용하는 File 객체 선언
	private File dataDir;
	private File dataFile;
	
	public int getSize() {
		return list.size();
	}
	
	// 데이터 폴더가 있는지 검사, 없으면 생성
	private void initDataDir() {
		dataDir = new File(DATA_DIR);
		
		if(!dataDir.exists()) {
			System.out.println("해당 디렉토리 없음");
			if(dataDir.mkdir()) {
				System.out.println("디렉토리 생성 성공");
			}
		} else {
			System.out.println("해당 디렉토리 존재");
		}
		System.out.println();
	}
	
	// 데이터 파일 검사, 없으면 ArraryList 인스턴스 생성/ 있으면 데이터를 읽어 ArrayList 채움
	private void initDataFile() {
		String filePath = DATA_DIR + File.separator + DATA_FILE;
		dataFile = new File(filePath);
		System.out.println("파일 경로 : " + dataFile.getPath());
		
		if(!dataFile.exists()) {
			list = new ArrayList<ContactVO>();
			System.out.println("데이터 파일 없음, ArrayList 생성");
		} else {
			readDataFromFile();
			System.out.println("데이터 파일 있음, 불러오기 성공");
		}
		System.out.println();
	}
	
	// 파일로 부터 데이터 읽기
	private void readDataFromFile() {
		InputStream in = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;
		
		try {
			in = new FileInputStream(dataFile);
			bin = new BufferedInputStream(in);
			oin = new ObjectInputStream(bin);
			
			list = (ArrayList<ContactVO>) oin.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oin.close();
			} catch (Exception e) {
				e.printStackTrace();			}
		}
	}
	
	// 파일에 데이터 쓰기
	private void writeDataToFile() {
		OutputStream out = null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout = null;
		
		try {
			out = new FileOutputStream(dataFile);
			bout = new BufferedOutputStream(out);
			oout = new ObjectOutputStream(bout);		

			oout.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int insert(ContactVO vo) {
		list.add(vo);
		writeDataToFile();
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
		writeDataToFile();
		return 1;
	}

	@Override
	public int delete(int index) {
		list.remove(index);
		writeDataToFile();
		return 1;
	}
	
}
