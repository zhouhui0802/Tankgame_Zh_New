package com.zh.Entity;

public class Tank {
	
	//表示坦克的横坐标
	public int x=0;
	
	//表示坦克的纵坐标
	public int y=0;
	
	//坦克方向    0向上    1表示右    2表示向下    3表示向左
	public int direct=0;
	
	//表示坦克的速度
	public int speed=1;
	
	//表示坦克的颜色
	public int color;
	
	//表示坦克是否存活
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
