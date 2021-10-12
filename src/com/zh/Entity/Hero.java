package com.zh.Entity;

import java.util.Vector;

import com.zh.Bullet.Shot;
import com.zh.Ui.MyGamePanel;

//画出自己的坦克
public class Hero extends Tank{

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	//获取得到面板上的自身坦克数据
	public Vector<Hero> heroV=new Vector<Hero>();
	
	//添加子弹类
	public Shot s=null;
	
	//添加子弹向量
	public Vector<Shot> ss=new Vector<Shot>();
	
	public void setHeroV(Vector<Hero> heroV)
	{
		this.heroV=heroV;
	}
	
	
	//子弹开火的行为
	public void shotEnemy()
	{
		//通过坦克的方向判断子弹的方向
		switch(this.direct)
		{
		case 0:
			s=new Shot(x+10,y,0);
			s.speed=Integer.parseInt(MyGamePanel.heroShot);
			ss.add(s);
			break;
		case 1:
			s=new Shot(x+30,y+10,1);
			s.speed=Integer.parseInt(MyGamePanel.heroShot);
			ss.add(s);
			break;
		case 2:
			s=new Shot(x+10,y+30,2);
			s.speed=Integer.parseInt(MyGamePanel.heroShot);
			ss.add(s);
			break;
		case 3:
			s=new Shot(x,y+10,3);
			s.speed=Integer.parseInt(MyGamePanel.heroShot);
			ss.add(s);
			break;
		}
		
		//启动子弹线程
		Thread t=new Thread(s);
		t.start();
	}
	
	//坦克向上移动
	public void moveUp()
	{
		y-=speed;
	}
	
	//坦克向右移动
	public void moveRight()
	{
		x+=speed;
	}
	
	//坦克向下移动
	public void moveDown()
	{
		y+=speed;
	}
	
	//坦克向左移动
	public void moveLeft()
	{
		x-=speed;
	}
}
