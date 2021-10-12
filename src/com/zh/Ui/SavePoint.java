package com.zh.Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import com.zh.Record.DataDisk;
import com.zh.User.Player;
import com.zh.Util.FrameUtil;

public class SavePoint extends JFrame implements ActionListener{
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	
	JTextField jf1;
	JTextField jf2;
	
	Box box1;
	Box box2;
	Box box3;
	Box box4;
	Box box5;
	
	JButton Okbtn=null;
	JButton Cancelbtn=null;
	
	public SavePoint()
	{
		label1=new JLabel("�������");
		label2=new JLabel("�û���");
		label3=new JLabel("�û��÷�");
		
		jf1=new JTextField(3);
		jf2=new JTextField(3);
		
		Okbtn=new JButton("ȷ��");
		Cancelbtn=new JButton("ȡ��");
		Okbtn.addActionListener(this);
		Cancelbtn.addActionListener(this);
		
		box1=Box.createHorizontalBox();
		box1.add(label1,Box.CENTER_ALIGNMENT);
		box2=Box.createHorizontalBox();
		box2.add(label2);
		box2.add(jf1);
		box3=Box.createHorizontalBox();
		box3.add(label3);
		box3.add(jf2);
		box5=Box.createHorizontalBox();
		box5.add(Okbtn);
		box5.add(Cancelbtn);
		box4=Box.createVerticalBox();
		box4.add(box1);
		box4.add(box2);
		box4.add(box3);
		box4.add(box5);
		
		 this.add(box4);
		 this.setVisible(true);
		 this.setSize(40, 125);
		 this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 FrameUtil.setFrameCenter(this);
		
	}
	
	public static void main(String args[])
	{
		SavePoint sp=new SavePoint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("ȷ��"))
		{
			double temp=Double.parseDouble(jf2.getText());
			int playerPoint=(int)temp;
			System.out.println(playerPoint);
			Player pla=new Player(jf1.getText(),playerPoint);
			DataDisk dbDisk=new DataDisk();
			dbDisk.saveData(pla);
		    List<Player> players=new ArrayList<Player>();
		    
			if(players==null)
			{
				players=new ArrayList<Player>();
			}
			//��μ�¼С��5����ô�ͼӵ�5��Ϊֹ
			while(players.size()<5)
			{
				players.add(new Player("No Data",0));
			}
			

		    players=dbDisk.loadData();
			Collections.sort(players);
			MyGamePanel.repaintNew=!MyGamePanel.repaintNew;
//		    for(int i=0;i<players.size();i++)
//		    {
//		    	Player play=players.get(i);
//		    	System.out.println(play.getName());
//		    	System.out.println(play.getPoint());
//		    }
		    //�Ա�����»���

		}else if(arg0.getActionCommand().equals("ȡ��")){
			System.out.println("���ȡ����ť");
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
	}
}
