package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import db.DbConnector;

public class Panel implements ActionListener{
	
	private JFrame f;
	private JTextField input;
	private JButton execButton, connectButton;
	private JTextArea resultArea;
//	private JTable resultTable;
	private DbConnector db = new DbConnector();
	
	Panel(){
		f=new JFrame();//creating instance of JFrame  
        
		execButton =new JButton("Exec");//creating instance of JButton  
		execButton.setBounds(500,110,100, 40);//x axis, y axis, width, height
		execButton.addActionListener(this);
		connectButton =new JButton("Connect DB");//creating instance of JButton  
		connectButton.setBounds(100,110,100, 40);//x axis, y axis, width, height  
		connectButton.addActionListener(this);
		input = new JTextField("Input SQL query here:");  
		input.setBounds(50,20, 900,80);
	    
        resultArea = new JTextArea("Status :\n");  
        resultArea.setBounds(50,160, 900,400);
        		          
		f.add(execButton);//adding button in JFrame  
		f.add(connectButton);//adding button in JFrame  
		f.add(input);
		f.add(resultArea);
		          
		f.setSize(1000,800);//1000 width and 800 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Panel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String query = "";
		
		if(e.getSource() == execButton) {
			query = input.getText();
			
			try {
				ResultSet rs = db.exec(query,resultArea);
				List<String[]> list = new ArrayList<>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				String[] title = new String[columnsNumber];
				for(int i = 1; i <= columnsNumber; i++) {
					title[i-1] = rsmd.getColumnName(i);
				}
//				pri(title);
				list.add(title);
				String[] temp = new String[columnsNumber];
				while(rs.next()) {
					temp = new String[columnsNumber];
					for(int i = 1; i <= columnsNumber; i++) {
						temp[i-1] = rs.getString(i);
					}
//					pri(temp);
					list.add(temp);
				}
				String[][] res = new String[list.size()][];
				for(int i = 0; i < list.size(); i++) {
					res[i] = list.get(i);
				}

				JFrame resultFrame = new JFrame();
				JTable resultTable = new JTable(res,title);
				resultTable.setBounds(20,20,960,780);
			    JScrollPane sp = new JScrollPane(resultTable);    
			    resultFrame.add(sp);
			    resultFrame.setSize(1000,800);
			    resultFrame.setVisible(true);
//			    System.out.println("add success");
			    
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(e.getSource() == connectButton) {
//			System.out.println("ConnectButton");
			resultArea.append(db.connectDb());
		}
		
	}
	
	public static void pri(String[] str) {
		for(String ele : str) {
			System.out.print(ele + " ");
		}
	}

}
