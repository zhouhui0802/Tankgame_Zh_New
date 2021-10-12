package com.zh.Ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import com.zh.Record.DataDisk;
import com.zh.User.Player;

public class recordTable extends JPanel{

	 
	private JTable jtable=null;
	private JScrollPane jscrollpane=null;
	
	
	public recordTable()
	{
		String[] dataTitle={"姓名","分数"};
		DataDisk dbDisk=new DataDisk();
		List<Player> players=new ArrayList<Player>();
	    players=dbDisk.loadData();
		Collections.sort(players);
	    
		String[][] data=new String[players.size()][2];
		for(int i=0;i<data.length;i++)
		{
			Player pla=players.get(i);
			String temp=String.valueOf(pla.getPoint());
			data[i][0]=pla.getName();
			data[i][1]=temp;
		}
		
		jtable = new JTable(data, dataTitle);
		jscrollpane = new JScrollPane(jtable);
		//setTitle("得分表格");
		this.setBounds(450, 320,100, 100);
		//this.setSize(100, 100);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		add(jscrollpane,BorderLayout.CENTER);
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		recordTable rt=new recordTable();
//	}

}
