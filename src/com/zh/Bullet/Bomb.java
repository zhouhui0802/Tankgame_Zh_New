package com.zh.Bullet;

//ը����
public class Bomb {
	
	//����ը��������
	public int x,y;
	
	//����ը��������
	public int life=9;
	
	//�ж�ը���Ƿ���
	public boolean isLive=true;
	
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	//��������ֵ
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}else
		{
			this.isLive=false;
		}
	}
}
