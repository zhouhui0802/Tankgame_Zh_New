package com.zh.Entity;

import java.util.Vector;

import com.zh.Bullet.Shot;
import com.zh.Ui.MyGamePanel;

//�����Լ���̹��
public class Hero extends Tank{

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	//��ȡ�õ�����ϵ�����̹������
	public Vector<Hero> heroV=new Vector<Hero>();
	
	//����ӵ���
	public Shot s=null;
	
	//����ӵ�����
	public Vector<Shot> ss=new Vector<Shot>();
	
	public void setHeroV(Vector<Hero> heroV)
	{
		this.heroV=heroV;
	}
	
	
	//�ӵ��������Ϊ
	public void shotEnemy()
	{
		//ͨ��̹�˵ķ����ж��ӵ��ķ���
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
		
		//�����ӵ��߳�
		Thread t=new Thread(s);
		t.start();
	}
	
	//̹�������ƶ�
	public void moveUp()
	{
		y-=speed;
	}
	
	//̹�������ƶ�
	public void moveRight()
	{
		x+=speed;
	}
	
	//̹�������ƶ�
	public void moveDown()
	{
		y+=speed;
	}
	
	//̹�������ƶ�
	public void moveLeft()
	{
		x-=speed;
	}
}
