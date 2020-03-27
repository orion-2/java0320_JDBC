package Basket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

public class Main extends JFrame {
	String contents[][] = null; //Data
	String header[] = null; //ColumnName
	
	JTabbedPane tabPane = new JTabbedPane();
	DefaultTableModel tableModel = new DefaultTableModel(contents,header);
	
	JTable table = new JTable(tableModel);
	JScrollPane tableScroll = new JScrollPane(table);
		
	JPanel tab_1 = new JPanel();
	JPanel tab_1_inputP = new JPanel();
	JTextField[] indata = new JTextField[6];
	JPopupMenu popup;
	JMenuItem m_del, m_mod;

	int modIntRow = -1;
	
	String fileName = "data.txt";
	BasketDAO sqlDAO = BasketDAO.getInstance();
	ArrayList<String[]> initList = new ArrayList<>();
	
	public static void main(String[] args) {
		new Main();
	}
	
	Main(){
		Dimension size = new Dimension(600,400);
		tableSetting();
		createTabbedP();
		init();
		this.setLocation(300, 600);
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
	public void createTabbedP() {
		tab_1.setLayout(new BorderLayout());
		tab_1.add(tableScroll, "Center");
		tab_1.add(tab_1_inputP,"South");
		tabPane.add("장바구니", tab_1);
		
	}
	
	
	public void tableSetting() {
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0); 
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		table.add(popup); 
//		table.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getButton() == 1) { 
//				}
//				if (e.getClickCount() == 2) {
//				}
//				if (e.getButton() == 3) {
//					int column = table.columnAtPoint(e.getPoint());//열의인덱스를 반환
//					int row = table.rowAtPoint(e.getPoint());//행의인덱스를 반환
//					table.changeSelection(row, column, false, false);//두 플래그의 상태에 따라 테이블의 선택모델을 갱신
//					popup.show(table, e.getX(), e.getY());
//				}
//			}
//		});
		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();
		ts.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = table.getColumnModel();
		for(int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}
	
	}
	
	
	public void delTableRow(int row) {
		tableModel.removeRow(row);
	}
	
	public void saveToDB(String[]in) {
//		int q = Integer.parseInt(in[4]);
//		int p = Integer.parseInt(in[5]);
//		String t = String.valueOf(q*p);
		
		BasketDTO newDTO = new BasketDTO();
		newDTO.setNo(in[0]);
		newDTO.setId(in[1]);
		newDTO.setName(in[2]);
		newDTO.setItem(in[3]);
		newDTO.setQuantity(in[4]);
		newDTO.setPrice(in[5]);
//		newDTO.setTotal(in[6]);
		sqlDAO.insertOne(newDTO);
	}
	
	
	

}
