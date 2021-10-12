package com.zh.Ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.zh.Bullet.Bomb;
import com.zh.Bullet.Shot;
import com.zh.Entity.EnemyTank;
import com.zh.Entity.Hero;
import com.zh.Entity.Tank;
import com.zh.Record.DataDisk;
import com.zh.Record.Node;
import com.zh.Record.Recorder;
import com.zh.User.Player;
import com.zh.Util.AePlayWave;

//����һ����Ϸ��ʼ�����
public class MyGamePanel extends JPanel implements KeyListener,Runnable{
	
	//�����ٶ�ˢ��
	public static boolean repaintNew=false;
	
	//����boolean����������ͣ
	public static boolean isPause=false;
	
	//��ȡ�����ļ�
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	//��ȡ�����ļ���������̹�˺͵��˵�̹���Լ���ͣ��
	public static String heroLife=null;
	public static String heroSpeed=null;
	public static String heroShot=null;
	public static String tankLife=null;
	public static String tankSpeed=null;
	public static String tankShot=null;
	
	//ֱ�Ӽ���÷ֱ��
	private JTable jtable=null;
	private JScrollPane jscrollpane=null;
	JButton button1=null;
	//�����û����ܵ÷�
	public static double sumPoint=0;
	
	//��һĿ�꣬����Ŀ��̹��
	Hero hero=null;
	Vector<Hero> heros=new Vector<Hero>();
	
	//��ʼ�����˵�̹������
	int enSize=0;
	//����һ��̹������
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//����ը���ļ���
	Vector<Bomb> bombs=new Vector<Bomb>();
	//��������ը��Ч��ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	

	//�����������������ݽڵ�
	Vector<Node> nodes=new Vector<Node>();

	public MyGamePanel(String flag)
	{
		
		if(flag.equals("newGame"))
		{
			initSetting();
			
			//��������̹�˵�λ��
			hero=new Hero(100,250);
			
			//����̹�˳ɼ�

			


			hero.speed=Integer.parseInt(heroSpeed);
			
			heros.add(hero);
			hero.setHeroV(heros);
			enSize=Integer.parseInt(tankLife);
			
			//��ʼ�����˵�̹��
			for(int i=0;i<enSize;i++)
			{
				EnemyTank et=new EnemyTank((i+1)*35,0);
				et.speed=Integer.parseInt(tankSpeed);
				
				et.setDirect(2);
				
				//��MyGamePanel�ĵ������������õ���̹��
				et.setEts(ets);
				
				//�������˵�̹���߳�
				Thread t=new Thread(et);
				t.start();
				
				//���������һ���ӵ�
				Shot s=new Shot(et.x+10,et.y+30,2);
				s.speed=Integer.parseInt(tankShot);
				//������˵��ӵ�������
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//������̹�˼��뵽������
				ets.add(et);
			}
		}else{
			
			nodes=new Recorder().getNodesAndEnNums();
			for(int i=0;i<nodes.size();i++)
			{
				if(i==0)
				{
					Node node=nodes.get(i);
					if(node.x==0&&node.y==0&&node.direct==0)
					{
						continue;
					}
					hero=new Hero(node.x,node.y);
					hero.setDirect(node.direct);
					heros.add(hero);
					hero.setHeroV(heros);
					
				}else{
					Node node=nodes.get(i);
					EnemyTank et=new EnemyTank(node.x,node.y);
					et.setDirect(node.direct);
					
					//��MyPanel�ĵ������������õ���̹��
					et.setEts(ets);
					
					//���������߳�
					Thread t=new Thread(et);
					t.start();
					
					//���������һ���ӵ�
					Shot s=new Shot(et.x+10,et.y+30,2);
					
					//��������ӵ�������
					et.ss.add(s);
					Thread t2=new Thread(s);
					t2.start();
					
					//����̹������������
					ets.add(et);
				}

			}
		}
		
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		//���ſ�ս����
		AePlayWave awp=new AePlayWave("111.wav");
		awp.start();
	}
	
	public void initSetting()
	{
		try {
			System.out.println("�����ȡ���̵ĺ���");
			fr=new FileReader("LevelSetting.txt");
			br=new BufferedReader(fr);
			
			heroLife=br.readLine();
			heroSpeed=br.readLine();
			heroShot=br.readLine();
			tankLife=br.readLine();
			tankSpeed=br.readLine();
			tankShot=br.readLine();
			
			System.out.println(heroLife+" "+heroSpeed+" "+heroShot+" "+tankLife+" "+tankSpeed+" "+tankShot);
			
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
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
		//������ʾ��Ϣ
		this.showInfo(g);
		
		ResultTank(g);
//		button1=new JButton("����λ��111");
//		button1.setBounds(480, 260, 20, 20);
//		button1.setBackground(Color.yellow);
//		this.add(button1);
//		
//		ResutlTank();
//		jscrollpane.setLocation(440, 200);
//		this.add(jscrollpane);
		
		//�����Լ���̹��
		if(hero.isLive)
		{
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		}
		
		//��������̹�˷�����ӵ�
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			//����ÿһ���ӵ�
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			
			if(myShot.isLive==false)
			{
				//��ss��ɾ�����ӵ�
				hero.ss.remove(myShot);
			}
		}
		
		//�������˵�̹��
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				//�������˵��ӵ�
				for(int j=0;j<et.ss.size();j++)
				{
					//ȡ���ӵ�
					Shot enemyShot=et.ss.get(j);
					if(enemyShot.isLive)
					{
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
					}else
					{
						et.ss.remove(enemyShot);
					}
				}
			}
		}
		
		//����ը��
		for(int i=0;i<bombs.size();i++)
		{
			//ȡ��ը��
			Bomb b=bombs.get(i);
			
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>3)
			{
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			}else
			{
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			
			b.lifeDown();
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
	}
	
	//������ʾ����Ϣ��̹��
	public void showInfo(Graphics g)
	{
		//�������˵�̹����Ϣ
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 110, 350);
		//��������̹�˵���Ϣ
		this.drawTank(130, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"", 165, 350);
		
		//������ҵ��ܳɼ�
		g.setColor(Color.black);
		Font f=new Font("����",Font.BOLD,20);
		g.setFont(f);
		g.drawString("�����ܳɼ���", 420, 30);
		
		this.drawTank(420, 60, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
		
		g.drawString("�ܵ÷�: ", 420, 120);
		String sumPoint1=String.valueOf(sumPoint);
		g.drawString(sumPoint1, 500, 120);
		
		g.drawString("�÷����а�", 420, 160);
		
	}
	
	//дһ�����������ж��ӵ��Ƿ����̹��
	public boolean hitTank(Shot s,Tank et)
	{
		boolean b2=false;
		
		//�ж�̹�˵ķ���
		switch(et.direct)
		{
		case 0:
		case 2:
			
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				s.isLive=false;
				et.isLive=false;
				b2=true;
				Bomb b=new Bomb(et.x,et.y);
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				s.isLive=false;
				et.isLive=false;
				b2=true;
				Bomb b=new Bomb(et.x,et.y);
				bombs.add(b);
			}
			break;
		}
		return b2;
	}
	
	//�ж��ҵ��ӵ��Ƿ����
	public void hitEnemyTank()
	{
		//�ж��Ƿ���е��˵�̹��
		for(int i=0;i<hero.ss.size();i++)
		{
			//ȡ���ӵ�
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive)
			{
				//ȡ�����˵�ÿ��̹��
				for(int j=0;j<ets.size();j++)
				{
					EnemyTank et=ets.get(j);
					if(et.isLive)
					{
						if(this.hitTank(myShot, et))
						{
							System.out.println("�һ���̹��");
							//���ٵ��˵�����
							Recorder.reduceEnNum();
							
							//�����ҵļ�¼
							Recorder.addEnNumRec();
							
							//����һ��̹�˵�5��
							sumPoint=Recorder.getAllEnNum()*10;
							//System.out.println("sumPoint="+sumPoint);
						}
					}
				}
			}
		}
	}
	
	//�жϵ��˵��ӵ��Ƿ������
	public void hitMe()
	{
		//ȡ��ÿһ�����˵�̹��
		for(int i=0;i<this.ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			
			for(int j=0;j<et.ss.size();j++)
			{
				Shot enemyShot=et.ss.get(j);
				if(hero.isLive)
				{
					if(this.hitTank(enemyShot, hero))
					{
						System.out.println("���˻�����");
					
						Recorder.reduceMylife();

					}
				}
			}
		}
	}
	
	//�����ܵ÷ֵ���Ϸ���а�
	public void ResultTank(Graphics g)
	{
		Font f=new Font("����",Font.BOLD,15);
		g.setFont(f);
		
		DataDisk dbDisk=new DataDisk();
		List<Player> players=new ArrayList<Player>();
	    players=dbDisk.loadData();
		Collections.sort(players);
		
		for(int i=0;i<5;i++)
		{
			Player player=players.get(i);
			g.drawString(player.getName(), 440, 200+i*20);
			String temp=String.valueOf(player.getPoint());
			g.drawString(temp, 480, 200+i*20);
		}
		
		
//		String[] dataTitle={"����","����"};
//		DataDisk dbDisk=new DataDisk();
//		List<Player> players=new ArrayList<Player>();
//	    players=dbDisk.loadData();
//		Collections.sort(players);
//	    
//		String[][] data=new String[players.size()][2];
//		for(int i=0;i<data.length;i++)
//		{
//			Player pla=players.get(i);
//			String temp=String.valueOf(pla.getPoint());
//			System.out.println(temp);
//			data[i][0]=pla.getName();
//			data[i][1]=temp;
//		}
//		
//		jtable = new JTable(data, dataTitle);
//		jscrollpane = new JScrollPane(jtable);
		
	}
	//���廭��̹�˵ĺ���
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//�ж�̹�˵�����
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//�ж�̹�˵ķ���
		switch(direct)
		{
		case 0: //̹������
			//������ߵľ���
			g.fill3DRect(x, y, 5, 30, false);
			//�����ұߵľ���
			g.fill3DRect(x+15, y, 5, 30, false);
			//�����м�ľ���
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//�����߶�
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1://̹������
			//��������ľ���
			g.fill3DRect(x, y, 30, 5, false);
			//��������ľ���
			g.fill3DRect(x, y+15, 30, 5, false);
			//�����м�ľ���
			g.fill3DRect(x+5, y+5,20, 10, false);
			//����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//�����߶�
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2:
			//��Ͳ����
			//������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұߵıߵľ���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м�ľ���
			g.fill3DRect(x+5,y+5, 10, 20,false);
			//����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//�����߶�
			g.drawLine(x+10,y+15,x+10,y+30);
			break;
		case 3:
			//��Ͳ����
			//������ߵľ���
			g.fill3DRect(x, y, 30, 5,false);
			//�����ұߵıߵľ���
			g.fill3DRect(x, y+15, 30, 5,false);
			//�����м�ľ���
			g.fill3DRect(x+5,y+5, 20, 10,false);
			//����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//�����߶�
			g.drawLine(x+15,y+10,x,y+10);
			break;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//��̹�˵��ƶ���������
		
		if(arg0.getKeyChar()==MyTankGame.up)
		{
			//����
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyChar()==MyTankGame.right)
		{
			//����
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyChar()==MyTankGame.left)
		{
			//����
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}else if(arg0.getKeyChar()==MyTankGame.down)
		{
			//����
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyChar()==MyTankGame.shoot)
		{
			//����
			if(this.hero.ss.size()<=5)
			{
				this.hero.shotEnemy();
			}
		}else if(arg0.getKeyChar()==MyTankGame.pause)
		{
			isPause=!isPause;
		}
		
		//���¶�̹�˽��л���
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try{
				Thread.sleep(100);
				
				//��ͣ�Ļ���ִ�����߳�
				if(isPause==true)
				{
					continue;
				}
			}catch(Exception e)
			{
				
			}
			
			//�ж����Ƿ���е��˵�̹��
			this.hitEnemyTank();
			
			//�жϵ����Ƿ������
			this.hitMe();
			
			//���»���
			this.repaint();
			
			if(repaintNew==true)
			{
				this.repaint();
				repaintNew=!repaintNew;
			}
		}
	}
	
}
