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
		// ������ ���� ���� ������ ���� ContactDAOImple 
		// �����ڸ� �ҷ����� ���� ��ο� ������ ����
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
	
	// list ��� �� �ν��Ͻ� ����
	public ArrayList<ContactVO> list; 
	
	// �����͸� ������ ������ ���� �̸� ����
	private static final String DATA_DIR = "data";
	private static final String DATA_FILE = "contact.data";
	
	// ������ ������ ������ ����ϴ� File ��ü ����
	private File dataDir;
	private File dataFile;
	
	public int getSize() {
		return list.size();
	}
	
	// ������ ������ �ִ��� �˻�, ������ ����
	private void initDataDir() {
		dataDir = new File(DATA_DIR);
		
		if(!dataDir.exists()) {
			System.out.println("�ش� ���丮 ����");
			if(dataDir.mkdir()) {
				System.out.println("���丮 ���� ����");
			}
		} else {
			System.out.println("�ش� ���丮 ����");
		}
		System.out.println();
	}
	
	// ������ ���� �˻�, ������ ArraryList �ν��Ͻ� ����/ ������ �����͸� �о� ArrayList ä��
	private void initDataFile() {
		String filePath = DATA_DIR + File.separator + DATA_FILE;
		dataFile = new File(filePath);
		System.out.println("���� ��� : " + dataFile.getPath());
		
		if(!dataFile.exists()) {
			list = new ArrayList<ContactVO>();
			System.out.println("������ ���� ����, ArrayList ����");
		} else {
			readDataFromFile();
			System.out.println("������ ���� ����, �ҷ����� ����");
		}
		System.out.println();
	}
	
	// ���Ϸ� ���� ������ �б�
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
	
	// ���Ͽ� ������ ����
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
