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

//这是一个游戏开始的面板
public class MyGamePanel extends JPanel implements KeyListener,Runnable{
	
	//用于再度刷新
	public static boolean repaintNew=false;
	
	//调用boolean变脸控制暂停
	public static boolean isPause=false;
	
	//读取配置文件
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	//读取配置文件包括自身坦克和敌人的坦克以及暂停键
	public static String heroLife=null;
	public static String heroSpeed=null;
	public static String heroShot=null;
	public static String tankLife=null;
	public static String tankSpeed=null;
	public static String tankShot=null;
	
	//直接加入得分表格
	private JTable jtable=null;
	private JScrollPane jscrollpane=null;
	JButton button1=null;
	//计算用户的总得分
	public static double sumPoint=0;
	
	//第一目标，画出目标坦克
	Hero hero=null;
	Vector<Hero> heros=new Vector<Hero>();
	
	//初始化敌人的坦克数量
	int enSize=0;
	//定义一个坦克数组
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//定义炸弹的集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	//定义三张炸弹效果图片
	Image image1=null;
	Image image2=null;
	Image image3=null;
	

	//定义敌人与自身的数据节点
	Vector<Node> nodes=new Vector<Node>();

	public MyGamePanel(String flag)
	{
		
		if(flag.equals("newGame"))
		{
			initSetting();
			
			//定义自身坦克的位置
			hero=new Hero(100,250);
			
			//调用坦克成绩

			


			hero.speed=Integer.parseInt(heroSpeed);
			
			heros.add(hero);
			hero.setHeroV(heros);
			enSize=Integer.parseInt(tankLife);
			
			//初始化敌人的坦克
			for(int i=0;i<enSize;i++)
			{
				EnemyTank et=new EnemyTank((i+1)*35,0);
				et.speed=Integer.parseInt(tankSpeed);
				
				et.setDirect(2);
				
				//将MyGamePanel的敌人数量交给该敌人坦克
				et.setEts(ets);
				
				//启动敌人的坦克线程
				Thread t=new Thread(et);
				t.start();
				
				//给敌人添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				s.speed=Integer.parseInt(tankShot);
				//加入敌人的子弹容器中
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//将敌人坦克加入到数组中
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
					
					//将MyPanel的敌人数量交给该敌人坦克
					et.setEts(ets);
					
					//启动敌人线程
					Thread t=new Thread(et);
					t.start();
					
					//给敌人添加一颗子弹
					Shot s=new Shot(et.x+10,et.y+30,2);
					
					//加入敌人子弹容器中
					et.ss.add(s);
					Thread t2=new Thread(s);
					t2.start();
					
					//加入坦克数组容器中
					ets.add(et);
				}

			}
		}
		
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		//播放开战声音
		AePlayWave awp=new AePlayWave("111.wav");
		awp.start();
	}
	
	public void initSetting()
	{
		try {
			System.out.println("进入读取键盘的函数");
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
		
		//画出提示信息
		this.showInfo(g);
		
		ResultTank(g);
//		button1=new JButton("测试位置111");
//		button1.setBounds(480, 260, 20, 20);
//		button1.setBackground(Color.yellow);
//		this.add(button1);
//		
//		ResutlTank();
//		jscrollpane.setLocation(440, 200);
//		this.add(jscrollpane);
		
		//画出自己的坦克
		if(hero.isLive)
		{
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		}
		
		//画出自身坦克发射的子弹
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			//画出每一颗子弹
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			
			if(myShot.isLive==false)
			{
				//从ss中删除该子弹
				hero.ss.remove(myShot);
			}
		}
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				//画出敌人的子弹
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
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
		
		//画出炸弹
		for(int i=0;i<bombs.size();i++)
		{
			//取出炸弹
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
	
	//画出提示下信息的坦克
	public void showInfo(Graphics g)
	{
		//画出敌人的坦克信息
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 110, 350);
		//画出自身坦克的信息
		this.drawTank(130, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"", 165, 350);
		
		//画出玩家的总成绩
		g.setColor(Color.black);
		Font f=new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("您的总成绩是", 420, 30);
		
		this.drawTank(420, 60, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
		
		g.drawString("总得分: ", 420, 120);
		String sumPoint1=String.valueOf(sumPoint);
		g.drawString(sumPoint1, 500, 120);
		
		g.drawString("得分排行榜", 420, 160);
		
	}
	
	//写一个函数用来判断子弹是否击中坦克
	public boolean hitTank(Shot s,Tank et)
	{
		boolean b2=false;
		
		//判断坦克的方向
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
	
	//判断我的子弹是否击中
	public void hitEnemyTank()
	{
		//判断是否击中敌人的坦克
		for(int i=0;i<hero.ss.size();i++)
		{
			//取出子弹
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive)
			{
				//取出敌人的每个坦克
				for(int j=0;j<ets.size();j++)
				{
					EnemyTank et=ets.get(j);
					if(et.isLive)
					{
						if(this.hitTank(myShot, et))
						{
							System.out.println("我击中坦克");
							//减少敌人的数量
							Recorder.reduceEnNum();
							
							//增加我的记录
							Recorder.addEnNumRec();
							
							//击毁一架坦克得5分
							sumPoint=Recorder.getAllEnNum()*10;
							//System.out.println("sumPoint="+sumPoint);
						}
					}
				}
			}
		}
	}
	
	//判断敌人的子弹是否击中我
	public void hitMe()
	{
		//取出每一个敌人的坦克
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
						System.out.println("敌人击中我");
					
						Recorder.reduceMylife();

					}
				}
			}
		}
	}
	
	//画出总得分的游戏排行榜
	public void ResultTank(Graphics g)
	{
		Font f=new Font("宋体",Font.BOLD,15);
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
		
		
//		String[] dataTitle={"姓名","分数"};
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
	//定义画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//判断坦克的类型
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//判断坦克的方向
		switch(direct)
		{
		case 0: //坦克向上
			//画出左边的矩形
			g.fill3DRect(x, y, 5, 30, false);
			//画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30, false);
			//画出中间的矩形
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//画出线段
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1://坦克向右
			//画出上面的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//画出下面的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间的矩形
			g.fill3DRect(x+5, y+5,20, 10, false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线段
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2:
			//炮筒向下
			//画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边的边的矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间的矩形
			g.fill3DRect(x+5,y+5, 10, 20,false);
			//画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//画出线段
			g.drawLine(x+10,y+15,x+10,y+30);
			break;
		case 3:
			//炮筒向左
			//画出左边的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//画出右边的边的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//画出中间的矩形
			g.fill3DRect(x+5,y+5, 20, 10,false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线段
			g.drawLine(x+15,y+10,x,y+10);
			break;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//对坦克的移动进行设置
		
		if(arg0.getKeyChar()==MyTankGame.up)
		{
			//向上
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyChar()==MyTankGame.right)
		{
			//向右
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyChar()==MyTankGame.left)
		{
			//向左
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}else if(arg0.getKeyChar()==MyTankGame.down)
		{
			//向下
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyChar()==MyTankGame.shoot)
		{
			//开火
			if(this.hero.ss.size()<=5)
			{
				this.hero.shotEnemy();
			}
		}else if(arg0.getKeyChar()==MyTankGame.pause)
		{
			isPause=!isPause;
		}
		
		//重新对坦克进行绘制
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
				
				//暂停的话不执行主线程
				if(isPause==true)
				{
					continue;
				}
			}catch(Exception e)
			{
				
			}
			
			//判断我是否击中敌人的坦克
			this.hitEnemyTank();
			
			//判断敌人是否击中我
			this.hitMe();
			
			//重新绘制
			this.repaint();
			
			if(repaintNew==true)
			{
				this.repaint();
				repaintNew=!repaintNew;
			}
		}
	}
	
}
