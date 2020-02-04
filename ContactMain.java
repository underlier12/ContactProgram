package edu.java.contact06;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

public class ContactMain {
	
	private static ContactDAO dao;
	private static int size;

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtSearch;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtIndex;	
	private JTextArea txtAreaResult;
	
	private JTable table;
	private String[] colNames = {"No", "�̸�", "��ȭ��ȣ", "�̸���"};
	private Object[] records = new Object[colNames.length];
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		dao = ContactDAOImple.getInstance();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactMain window = new ContactMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContactMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 653);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblConPro = new JLabel("Contact Program");
		lblConPro.setHorizontalAlignment(SwingConstants.CENTER);
		lblConPro.setFont(new Font("����", Font.PLAIN, 30));
		lblConPro.setBounds(268, 10, 415, 48);
		frame.getContentPane().add(lblConPro);
		
		JLabel lblConManage = new JLabel("����ó ����");
		lblConManage.setFont(new Font("����", Font.PLAIN, 24));
		lblConManage.setHorizontalAlignment(SwingConstants.CENTER);
		lblConManage.setBounds(89, 59, 197, 33);
		frame.getContentPane().add(lblConManage);

		
		JLabel lblName = new JLabel("��      ��");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("����", Font.PLAIN, 18));
		lblName.setBounds(42, 102, 108, 36);
		frame.getContentPane().add(lblName);
		
		JLabel lblPhone = new JLabel("��ȭ��ȣ");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("����", Font.PLAIN, 18));
		lblPhone.setBounds(42, 148, 108, 36);
		frame.getContentPane().add(lblPhone);
		
		JLabel lblEmail = new JLabel("�� �� ��");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("����", Font.PLAIN, 18));
		lblEmail.setBounds(42, 194, 108, 36);
		frame.getContentPane().add(lblEmail);
		
		txtName = new JTextField();
		txtName.setBounds(180, 102, 255, 36);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(180, 148, 255, 36);
		frame.getContentPane().add(txtPhone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(180, 196, 255, 36);
		frame.getContentPane().add(txtEmail);		
		
		JButton btnRegister = new JButton("��      ��");
		ActionListener il = new InsertListener();
		btnRegister.addActionListener(il);
		btnRegister.setFont(new Font("����", Font.PLAIN, 18));
		btnRegister.setBounds(42, 240, 393, 42);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblIndex = new JLabel("����/������ �ε���");
		lblIndex.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndex.setFont(new Font("����", Font.PLAIN, 18));
		lblIndex.setBounds(42, 292, 197, 36);
		frame.getContentPane().add(lblIndex);
		
		txtIndex = new JTextField();
		txtIndex.setColumns(10);
		txtIndex.setBounds(250, 293, 185, 36);
		frame.getContentPane().add(txtIndex);
		
		JButton btnModify = new JButton("��      ��");
		ActionListener ml = new ModifyListener();
		btnModify.addActionListener(ml);
		btnModify.setFont(new Font("����", Font.PLAIN, 18));
		btnModify.setBounds(42, 339, 197, 42);
		frame.getContentPane().add(btnModify);
		
		JButton btnDelete = new JButton("��      ��");
		ActionListener dl = new DeleteListener();
		btnDelete.addActionListener(dl);
		btnDelete.setFont(new Font("����", Font.PLAIN, 18));
		btnDelete.setBounds(250, 339, 185, 42);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblResult = new JLabel("�� �� �� ��");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("����", Font.PLAIN, 14));
		lblResult.setBounds(26, 391, 108, 22);
		frame.getContentPane().add(lblResult);
		
		JScrollPane sclPaneResult = new JScrollPane();
		sclPaneResult.setBounds(42, 423, 393, 182);
		frame.getContentPane().add(sclPaneResult);
		
		txtAreaResult = new JTextArea();
		sclPaneResult.setViewportView(txtAreaResult);
		
		JLabel lblConSearch = new JLabel("����ó �˻�");
		lblConSearch.setFont(new Font("����", Font.PLAIN, 24));
		lblConSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblConSearch.setBounds(629, 59, 175, 33);
		frame.getContentPane().add(lblConSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(500, 102, 253, 33);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("��      ��");
		ActionListener sl = new SearchListener();
		btnSearch.addActionListener(sl);
		btnSearch.setBounds(788, 102, 147, 36);
		frame.getContentPane().add(btnSearch);
		
		JScrollPane sclPaneSearch = new JScrollPane();
		sclPaneSearch.setBounds(500, 160, 435, 445);
		frame.getContentPane().add(sclPaneSearch);
	
		tableModel = new DefaultTableModel(colNames, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("����", Font.PLAIN, 15));
		sclPaneSearch.setViewportView(table);
	}
	
	class InsertListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			txtAreaResult.append("����ó ���� ���\n");
			String name = txtName.getText();
			String phone = txtPhone.getText();
			String email = txtEmail.getText();
			
			if(name.equals("") || phone.equals("") || email.equals("")) {
				txtAreaResult.append("���� �� �ϳ��� ��������� �ȵ˴ϴ�.\n");
				return;
			}
			
			ContactVO vo = new ContactVO(name, phone, email);
			int result = dao.insert(vo);
			if(result == 1) {
				txtAreaResult.append("����ó ��� ����\n");
			} else {
				txtAreaResult.append("����ó ��� ����\n");
			}			
		}
	}
	
	class SearchListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {			
			size = ((ContactDAOImple)dao).getSize();
			if (size == 0) {
				txtAreaResult.append("����ó ����� ���� ���ּ���\n");
				return;
			}
			
			if(txtSearch.getText().equals("")) {
				txtAreaResult.append("Total search\n");
				tableModel.setNumRows(0);
				
				ArrayList<ContactVO> list = dao.search();
				for (int i = 0; i < size; i++) {
					records[0] = i + 1;
					records[1] = list.get(i).getName();
					records[2] = list.get(i).getPhone();
					records[3] = list.get(i).getEmail();
					tableModel.addRow(records);
				}
				
			} else {
				txtAreaResult.append("Detail search\n");
				
				String index = txtSearch.getText();
				try {
					int idx = Integer.parseInt(index);
					
					if (!(idx >= 1 && idx < size)) {
						txtAreaResult.append("�ùٸ��� ���� �ε����Դϴ�.\n");
						return;
					}
					tableModel.setNumRows(0);
					
					ContactVO vo = dao.search(idx);					
					records[0] = idx;
					records[1] = vo.getName();
					records[2] = vo.getPhone();
					records[3] = vo.getEmail();
					tableModel.addRow(records);

				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}		
	}
	
	class ModifyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {			
			size = ((ContactDAOImple)dao).getSize();
			if (size == 0) {
				txtAreaResult.append("����ó ����� ���� ���ּ���\n");
				return;
			}
			
			String index = txtIndex.getText();
			try {
				int idx = Integer.parseInt(index);
				
				if (!(idx >= 1 && idx < size)) {
					txtAreaResult.append("�ùٸ��� ���� �ε����Դϴ�.\n");
					return;
				}
				
				String name = txtName.getText();
				String phone = txtPhone.getText();
				String email = txtEmail.getText();
				
				if(name.equals("") || phone.equals("") || email.equals("")) {
					txtAreaResult.append("���� �� �ϳ��� ��������� �ȵ˴ϴ�.\n");
					return;
				}
				
				ContactVO vo = new ContactVO(name, phone, email);
				
				int result = ((ContactDAOImple)dao).modify(idx, vo);
				if (result == 1) {
					txtAreaResult.append("����ó [" + idx + "] ���� ����\n");
				} else {
					txtAreaResult.append("����ó [" + idx + "] ���� ����\n");
				}
				
			} catch (Exception e2) {
				System.out.println(e2);
			}			
		}		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			size = ((ContactDAOImple)dao).getSize();
			if (size == 0) {
				txtAreaResult.append("����ó ����� ���� ���ּ���\n");
				return;
			}
			
			String index = txtIndex.getText();
			try {
				int idx = Integer.parseInt(index);
				
				if (!(idx >= 0 && idx < size)) {
					txtAreaResult.append("�ùٸ��� ���� �ε����Դϴ�.\n");
					return;
				}
				
				int result = ((ContactDAOImple)dao).delete(idx);
				if (result == 1) {
					txtAreaResult.append("����ó [" + idx + "] ���� ����\n");
				} else {
					txtAreaResult.append("����ó [" + idx + "] ���� ����\n");
				}
				
			} catch (Exception e2) {
				System.out.println(e2);
			}			
		}		
	}	
}







