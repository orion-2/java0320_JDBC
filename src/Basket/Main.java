package Basket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class Main extends JFrame {
	String header[] = {"ID","이름","상품명","수량","가격"}; //ColumnName
	String contents[][] = {{"aaa","이","양념","2","2000"}}; //Data
	
	JTabbedPane tabPane = new JTabbedPane();
	DefaultTableModel tableModel = new DefaultTableModel(contents,header);
	
	JTable table = new JTable(tableModel);
	JScrollPane tableScroll = new JScrollPane(table);
		
	JPanel tab_1 = new JPanel();
	JPanel tab_1_inputP = new JPanel();
	JPanel tab_2 = new JPanel();
	
	JTextField[] indata = new JTextField[5];
	
	JPopupMenu popup;
	JMenuItem m_del, m_mod;

	int modIntRow = -1;
	
	BDTO newDTO = new BDTO();
	BDAO sqlDAO = BDAO.getInstance();
	ArrayList<String[]> initList = new ArrayList<>();
	
	
	@SuppressWarnings("static-access")
	Main(){
		Dimension size = new Dimension(600,400);
		menuLayout();
		tableSetting();
		creatInputP();
		createTabbedP();
		
		init();
		
		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabPane);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
	}
	
	private void init() {
		initList = sqlDAO.selAll();
		for(int i = 0; i < initList.size(); i++) {
			tableModel.addRow(initList.get(i));
		}
	}
	
	private void menuLayout() {
		popup = new JPopupMenu();
		m_mod = new JMenuItem("수정");
		m_mod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modIntRow = table.getSelectedRow();
				for (int i = 0; i < indata.length; i++) {
					indata[i].setText((String) table.getValueAt(modIntRow, i));
				}
			}
		});
		m_del = new JMenuItem("삭제");
		m_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;
				}else {
					delTableRow(table.getSelectedRow());
					
				}
			}
		});
		popup.add(m_mod);
		popup.add(m_del);
	}
	
	public void delTableRow(int row) {
		tableModel.removeRow(row);
	}
	
	public void tableSetting() {
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0); 
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.add(popup); 
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) { 
				}
				if (e.getClickCount() == 2) {
				}
				if (e.getButton() == 3) {
					int column = table.columnAtPoint(e.getPoint());//열의인덱스를 반환
					int row = table.rowAtPoint(e.getPoint());//행의인덱스를 반환
					table.changeSelection(row, column, false, false);//두 플래그의 상태에 따라 테이블의 선택모델을 갱신
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();
		ts.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = table.getColumnModel();
		for(int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}
		
	}
	
	private void creatInputP() {
		tab_1_inputP.setLayout(new BoxLayout(tab_1_inputP,BoxLayout.X_AXIS));
		for (int i = 0; i <indata.length; i++) {
			tab_1_inputP.add(indata[i] = new JTextField());
		}
		
		JButton addB = new JButton("Add");
		tab_1_inputP.add(addB);
		addB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String in[] = new String[5];
				for (int i = 0; i < indata.length; i++) {
					in[i] = indata[i].getText();
					indata[i].setText("");
				}
				tableModel.addRow(in);
				saveToDB(in);
				
			}
		});
		
		JButton modB = new JButton("Mod");
		tab_1_inputP.add(modB);
		modB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modIntRow != -1) {
					String in[] = new String[5];
					for(int i = 0; i < indata.length; i++) { //데이터의 개수만큼
						in[i] = indata[i].getText(); //텍스트를 호출
						delTableRow(modIntRow);
						tableModel.insertRow(modIntRow, in);
						delToDB(in[0]);
						saveToDB(in);
					}
					
					modIntRow = -1;
				}
			}
		});
		
		JButton delB = new JButton("Del");
		tab_1_inputP.add(delB);
		delB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					return;
				}else {
					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
					delToDB(id);
					delTableRow(table.getSelectedRow());
				}
				
			}
		});
		
		JButton saveB = new JButton("Save");
		tab_1_inputP.add(saveB);
		saveB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				saveToDB();
			}
		});
		
	}
	
	public void delToDB (String id) {
		newDTO.setId(id);
		sqlDAO.delOne(newDTO);
	}
	
	public void saveToDB(String[]in) {
		int quantity = Integer.parseInt(in[3]);
		int price = Integer.parseInt(in[4]);
		int total = quantity*price;
		String totals = Integer.toString(total);
		
		newDTO.setId(in[0]);
		newDTO.setName(in[1]);
		newDTO.setItem(in[2]);
		newDTO.setQuantity(in[3]);
		newDTO.setPrice(in[4]);
		sqlDAO.insertOne(newDTO);
	}
	

	public void createTabbedP() {
		tab_1.setLayout(new BorderLayout());
		tab_1.add(tableScroll, "Center");
		tab_1.add(tab_1_inputP,"South");
		tabPane.add("장바구니", tab_1);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}