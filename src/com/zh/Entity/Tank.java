package com.zh.Entity;

public class Tank {
	
	//��ʾ̹�˵ĺ�����
	public int x=0;
	
	//��ʾ̹�˵�������
	public int y=0;
	
	//̹�˷���    0����    1��ʾ��    2��ʾ����    3��ʾ����
	public int direct=0;
	
	//��ʾ̹�˵��ٶ�
	public int speed=1;
	
	//��ʾ̹�˵���ɫ
	public int color;
	
	//��ʾ̹���Ƿ���
	public boolean isLive=true;
	
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	
}
