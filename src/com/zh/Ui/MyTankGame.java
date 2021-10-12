package com.zh.Ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;

import com.zh.Record.Recorder;
import com.zh.Util.FrameUtil;

public class MyTankGame extends JFrame implements ActionListener{
	
	
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	public static char up='0';
	public static char down='0';
	public static char left='0';
	public static char right='0';
	public static char shoot='0';
	public static char pause='0';
	
	//首先对菜单栏进行部署
	JMenuBar jmb=null;
	
	//开始游戏
	JMenu jm1=null;
	
	//分别是开始新的游戏，退出系统，存盘退出，接着上一把的游戏接着玩
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	
	//设置一个设置菜单项目
	JMenu jm2=null;
	//对键盘按钮进行设计
	JMenuItem jmi5=null;
	//对游戏难易进行设置
	JMenuItem jmi6=null;
	//对保存的分数进行设置
	JMenuItem jmi7=null;
	

	//定义一个开始闪烁的面板
	MyStartPanel msp=null;
	//定义一个游戏的面板
	MyGamePanel mgp=null;
	
	//将得分表格初始化出来
	recordTable rt=null;
	JLabel label1=null;
	JButton button1=null;
	
	//构造函数
	public MyTankGame()
	{
		jmb=new JMenuBar();
		jm1=new JMenu("游戏(G)");
		jm1.setMnemonic('G');
		jm2=new JMenu("游戏设置(A)");
		jm2.setMnemonic('A');
		
		
		jmi1=new JMenuItem("开始新的游戏(N)");
		jmi2=new JMenuItem("退出游戏(E)");
		jmi3=new JMenuItem("存盘退出游戏(C)");
		jmi4=new JMenuItem("继续上局游戏(S)");
		jmi5=new JMenuItem("游戏按键设置(B)");
		jmi6=new JMenuItem("游戏难易设置(C)");
		jmi7=new JMenuItem("保存分数设置");
		
		jmi1.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('C');
		jmi4.setMnemonic('S');
		jmi5.setMnemonic('B');
		jmi6.setMnemonic('C');
		jmi7.setMnemonic('D');
		
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newGame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		jmi5.addActionListener(this);
		jmi5.setActionCommand("KeyInstall");
		jmi6.addActionListener(this);
		jmi6.setActionCommand("LevelSetting");
		jmi7.addActionListener(this);
		jmi7.setActionCommand("SavePoint");
		
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jm2.add(jmi5);
		jm2.add(jmi6);
		jm2.add(jmi7);
		
		jmb.add(jm1);
		jmb.add(jm2);
		
		//开始调用闪烁面板，启动线程
		msp=new MyStartPanel();
		Thread t=new Thread(msp);
		t.start();
		

		
		this.setJMenuBar(jmb);
		
		//启动闪烁面板的线程
		this.add(msp);
		
		
		
		this.setSize(600,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrameUtil.setFrameCenter(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("newGame"))
		{
			System.out.println("开始新的游戏");		
			initKey();
			
			
			//创建新的游戏战场
			mgp=new MyGamePanel("newGame");
			
			//启动游戏面板线程
			Thread t=new Thread(mgp);
			t.start();
			
			//删除旧的面板
			this.remove(msp);
			this.add(mgp);

			//注册监听
			this.addKeyListener(mgp);
			
			this.setVisible(true);
			
		}else if(arg0.getActionCommand().equals("exit"))
		{
			//保存击毁敌人数量
			Recorder.keepRecording();
			System.out.println("退出游戏");
			
		}else if(arg0.getActionCommand().equals("saveExit"))
		{
			Recorder rd=new Recorder();
		
			rd.setEts(mgp.ets);
			rd.setHeros(mgp.heros);
			rd.keepRecAndEnemyTank();
			System.out.println("保存游戏");
			
		}else if(arg0.getActionCommand().equals("conGame"))
		{
			initKey();
			mgp=new MyGamePanel("con");
			
			Thread t=new Thread(mgp);
			t.start();
			
			this.remove(msp);
			this.add(mgp);
			
			this.addKeyListener(mgp);
			
			this.setVisible(true);
			
			System.out.println("接着玩游戏");
		}else if(arg0.getActionCommand().equals("KeyInstall"))
		{
			System.out.println("点击了用户设置按钮");
			KeyInstall ki=new KeyInstall();
			
		}else if(arg0.getActionCommand().equals("LevelSetting"))
		{
			System.out.println("用户点击了游戏难易设置");
			LevelSetting ls=new LevelSetting();
		}else if(arg0.getActionCommand().equals("SavePoint"))
		{
			System.out.println("用户点击了保存分数按钮");
			MyGamePanel.isPause=!MyGamePanel.isPause;
			SavePoint sp=new SavePoint();
			String sumPoints=String.valueOf(MyGamePanel.sumPoint);
			sp.jf2.setText(sumPoints);
			sp.jf2.setEditable(false);
		}
	}
	
	
	
	public void initKey()
	{

		try {
			System.out.println("进入读取键盘的函数");
			fr=new FileReader("KeyInstall.txt");
			br=new BufferedReader(fr);
			
			up=br.readLine().charAt(0);
			down=br.readLine().charAt(0);
			left=br.readLine().charAt(0);
			right=br.readLine().charAt(0);
			shoot=br.readLine().charAt(0);
			pause=br.readLine().charAt(0);
			
			System.out.println(up+" "+down+" "+left+" "+right+" "+shoot+" "+pause);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				br.close();
				fr.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
