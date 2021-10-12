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
	
	//���ȶԲ˵������в���
	JMenuBar jmb=null;
	
	//��ʼ��Ϸ
	JMenu jm1=null;
	
	//�ֱ��ǿ�ʼ�µ���Ϸ���˳�ϵͳ�������˳���������һ�ѵ���Ϸ������
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	
	//����һ�����ò˵���Ŀ
	JMenu jm2=null;
	//�Լ��̰�ť�������
	JMenuItem jmi5=null;
	//����Ϸ���׽�������
	JMenuItem jmi6=null;
	//�Ա���ķ�����������
	JMenuItem jmi7=null;
	

	//����һ����ʼ��˸�����
	MyStartPanel msp=null;
	//����һ����Ϸ�����
	MyGamePanel mgp=null;
	
	//���÷ֱ���ʼ������
	recordTable rt=null;
	JLabel label1=null;
	JButton button1=null;
	
	//���캯��
	public MyTankGame()
	{
		jmb=new JMenuBar();
		jm1=new JMenu("��Ϸ(G)");
		jm1.setMnemonic('G');
		jm2=new JMenu("��Ϸ����(A)");
		jm2.setMnemonic('A');
		
		
		jmi1=new JMenuItem("��ʼ�µ���Ϸ(N)");
		jmi2=new JMenuItem("�˳���Ϸ(E)");
		jmi3=new JMenuItem("�����˳���Ϸ(C)");
		jmi4=new JMenuItem("�����Ͼ���Ϸ(S)");
		jmi5=new JMenuItem("��Ϸ��������(B)");
		jmi6=new JMenuItem("��Ϸ��������(C)");
		jmi7=new JMenuItem("�����������");
		
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
		
		//��ʼ������˸��壬�����߳�
		msp=new MyStartPanel();
		Thread t=new Thread(msp);
		t.start();
		

		
		this.setJMenuBar(jmb);
		
		//������˸�����߳�
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
			System.out.println("��ʼ�µ���Ϸ");		
			initKey();
			
			
			//�����µ���Ϸս��
			mgp=new MyGamePanel("newGame");
			
			//������Ϸ����߳�
			Thread t=new Thread(mgp);
			t.start();
			
			//ɾ���ɵ����
			this.remove(msp);
			this.add(mgp);

			//ע�����
			this.addKeyListener(mgp);
			
			this.setVisible(true);
			
		}else if(arg0.getActionCommand().equals("exit"))
		{
			//������ٵ�������
			Recorder.keepRecording();
			System.out.println("�˳���Ϸ");
			
		}else if(arg0.getActionCommand().equals("saveExit"))
		{
			Recorder rd=new Recorder();
		
			rd.setEts(mgp.ets);
			rd.setHeros(mgp.heros);
			rd.keepRecAndEnemyTank();
			System.out.println("������Ϸ");
			
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
			
			System.out.println("��������Ϸ");
		}else if(arg0.getActionCommand().equals("KeyInstall"))
		{
			System.out.println("������û����ð�ť");
			KeyInstall ki=new KeyInstall();
			
		}else if(arg0.getActionCommand().equals("LevelSetting"))
		{
			System.out.println("�û��������Ϸ��������");
			LevelSetting ls=new LevelSetting();
		}else if(arg0.getActionCommand().equals("SavePoint"))
		{
			System.out.println("�û�����˱��������ť");
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
			System.out.println("�����ȡ���̵ĺ���");
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
