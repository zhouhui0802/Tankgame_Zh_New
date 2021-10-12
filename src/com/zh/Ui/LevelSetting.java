package com.zh.Ui;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.*;

import com.zh.Util.FrameUtil;

public class LevelSetting extends JFrame implements ActionListener{

	JLabel label1=null;
	JLabel label2=null;
	JLabel label3=null;
	JLabel label4=null;
	JLabel label5=null;
	JLabel label6=null;
	JLabel label7=null;
	JLabel label8=null;
	
	JComboBox combox1=null;
	JComboBox combox2=null;
	JComboBox combox3=null;
	JComboBox combox4=null;
	JComboBox combox5=null;
	JComboBox combox6=null;
	
	JButton Okbtn=null;
	JButton CancelBtn=null;
	
	Box box1=null;
	Box box2=null;
	Box box3=null;
	Box box4=null;
	Box box5=null;
	Box box6=null;
	Box box7=null;
	Box box8=null;
	Box box9=null;
	Box box10=null;
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	
	public LevelSetting()
	{
		String select[]={"1","2","3","4","5"};
		String select1[]={"3","6","9","12","15"};
		String select2[]={"2","4","6","8","10"};
		
		 label1=new JLabel("游戏难易设置");
		 
		 label2=new JLabel("英雄坦克寿命");
		 label3=new JLabel("英雄坦克速度");
		 label4=new JLabel("英雄坦克子弹速度");
		 label5=new JLabel("敌人坦克数量");
		 label6=new JLabel("敌人坦克速度");
		 label7=new JLabel("敌人坦克子弹速度");
		 
		 combox1=new JComboBox();
		 combox1.setModel(new DefaultComboBoxModel(select2));
		 combox2=new JComboBox();
		 combox2.setModel(new DefaultComboBoxModel(select));
		 combox3=new JComboBox();
		 combox3.setModel(new DefaultComboBoxModel(select));
		 combox4=new JComboBox();
		 combox4.setModel(new DefaultComboBoxModel(select1));
		 combox5=new JComboBox();
		 combox5.setModel(new DefaultComboBoxModel(select));
		 combox6=new JComboBox();
		 combox6.setModel(new DefaultComboBoxModel(select));
		 
		 Okbtn=new JButton("确定");
		 CancelBtn=new JButton("取消");
		 
		 box1=Box.createHorizontalBox();
		 box1.add(label1,Box.CENTER_ALIGNMENT);
		 box2=Box.createHorizontalBox();
		 box2.add(label2);
		 box2.add(combox1);
		 box3=Box.createHorizontalBox();
		 box3.add(label3);
		 box3.add(combox2);
		 box4=Box.createHorizontalBox();
		 box4.add(label4);
		 box4.add(combox3);
		 box5=Box.createHorizontalBox();
		 box5.add(label5);
		 box5.add(combox4);
		 box6=Box.createHorizontalBox();
		 box6.add(label5);
		 box6.add(combox4);
		 box7=Box.createHorizontalBox();
		 box7.add(label6);
		 box7.add(combox5);
		 box8=Box.createHorizontalBox();
		 box8.add(label7);
		 box8.add(combox6);
		 box10=Box.createHorizontalBox();
		 box10.add(Okbtn);
		 box10.add(CancelBtn);
		 
		 box9=Box.createVerticalBox();
		 box9.add(box1);
		 box9.add(box2);
		 box9.add(box3);
		 box9.add(box4);
		 box9.add(box5);
		 box9.add(box6);
		 box9.add(box7);
		 box9.add(box8);
		 box9.add(box10);
		 

		 
		 Okbtn.addActionListener(this);
		 CancelBtn.addActionListener(this);
		 
		 this.add(box9);
		 this.setVisible(true);
		 this.setSize(160, 260);
		 this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 FrameUtil.setFrameCenter(this);
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelSetting ls=new LevelSetting();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("确定"))
		{
			String heroLife=(String)combox1.getSelectedItem();
			String heroSpeed=(String)combox2.getSelectedItem();
			String heroShot=(String)combox3.getSelectedItem();
			
			String tankLife=(String)combox4.getSelectedItem();
			String tankSpeed=(String)combox5.getSelectedItem();
			String tankShot=(String)combox6.getSelectedItem();
			
			try {				
				fw=new FileWriter("LevelSetting.txt");
				bw=new BufferedWriter(fw);
				bw.write(heroLife+"\r\n");
				bw.write(heroSpeed+"\r\n");
				bw.write(heroShot+"\r\n");
				bw.write(tankLife+"\r\n");
				bw.write(tankSpeed+"\r\n");
				bw.write(tankShot+"\r\n");
				System.out.println("写入成功");
				Okbtn.setEnabled(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					bw.close();
					fw.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}

	}

}
