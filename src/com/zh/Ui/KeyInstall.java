package com.zh.Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import com.zh.Util.FrameUtil;

public class KeyInstall extends JFrame implements ActionListener{

	JLabel label1=null;
	JLabel label2=null;
	JLabel label3=null;
	JLabel label4=null;
	JLabel label5=null;
	JLabel label6=null;
	JLabel label7=null;
	
	JTextField jf1=null;
	JTextField jf2=null;
	JTextField jf3=null;
	JTextField jf4=null;
	JTextField jf5=null;
	JTextField jf6=null;
	
	JButton Okbtn=null;
	JButton Cancelbtn=null;
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
		
	public KeyInstall()
	{
		label1=new JLabel("坦克按键设置");
		label2=new JLabel("坦克向上");
		label3=new JLabel("坦克向下");
		label4=new JLabel("坦克向左");
		label5=new JLabel("坦克向右");
		label6=new JLabel("坦克发射");
		label7=new JLabel("游戏暂停键");
		
		 jf1=new JTextField(3);
		 jf2=new JTextField(3);
		 jf3=new JTextField(3);
		 jf4=new JTextField(3);
		 jf5=new JTextField(3);
		 jf6=new JTextField(3);
		 
		 Okbtn=new JButton("确认");
		 Cancelbtn=new JButton("取消");
		 
		 Box hbox=Box.createHorizontalBox();
		 hbox.add(label1,Box.CENTER_ALIGNMENT);
		 Box hbox1=Box.createHorizontalBox();
		 hbox1.add(label2);
		 hbox1.add(jf1);
		 Box hbox2=Box.createHorizontalBox();
		 hbox2.add(label3);
		 hbox2.add(jf2);
		 Box hbox3=Box.createHorizontalBox();
		 hbox3.add(label4);
		 hbox3.add(jf3);
		 Box hbox4=Box.createHorizontalBox();
		 hbox4.add(label5);
		 hbox4.add(jf4);
		 Box hbox5=Box.createHorizontalBox();
		 hbox5.add(label6);
		 hbox5.add(jf5);
		 Box hbox7=Box.createHorizontalBox();
		 hbox7.add(label7);
		 hbox7.add(jf6);
		 Box hbox6=Box.createHorizontalBox();
		 hbox6.add(Okbtn);
		 hbox6.add(Cancelbtn);
		 
		 Box vbox=Box.createVerticalBox();
		 vbox.add(hbox);
		 vbox.add(hbox1);
		 vbox.add(hbox2);
		 vbox.add(hbox3);
		 vbox.add(hbox4);
		 vbox.add(hbox5);
		 vbox.add(hbox7);
		 vbox.add(hbox6);
		 
		 Okbtn.addActionListener(this);
		 Cancelbtn.addActionListener(this);
		 
		 this.add(vbox);
		 this.setVisible(true);
		 this.setSize(40, 250);
		 this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 FrameUtil.setFrameCenter(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeyInstall ki=new KeyInstall();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("确认"))
		{
			System.out.println("点击确认按钮");
			String up=jf1.getText();
			String down=jf2.getText();
			String left=jf3.getText();
			String right=jf4.getText();
			String shoot=jf5.getText();
			String isPause=jf6.getText();
			System.out.println(up+" "+down+" "+left+" "+right+" "+shoot+" "+isPause);
			
			try {
				
				fw=new FileWriter("KeyInstall.txt");
				bw=new BufferedWriter(fw);
				bw.write(up+"\r\n");
				bw.write(down+"\r\n");
				bw.write(left+"\r\n");
				bw.write(right+"\r\n");
				bw.write(shoot+"\r\n");
				bw.write(isPause+"\r\n");
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

		}else if(arg0.getActionCommand().equals("取消")){
			System.out.println("点击取消按钮");
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
	}

}
