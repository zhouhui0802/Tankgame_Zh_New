package com.zh.Bullet;

//�ӵ���
public class Shot implements Runnable{
	
	//�ӵ��ĺ�����
	public int x;
	//�ӵ���������
	public int y;
	//�ӵ��ķ���
	public int direct;
	//�ӵ����ٶ�
	public int speed=1;
	//�ж��ӵ��Ƿ���
	public boolean isLive=true;
	
	public Shot(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try{
				Thread.sleep(50);
			}catch(Exception e)
			{
				
			}
			switch(direct)
			{
			case 0:
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;
			case 2:
				y+=speed;
				break;
			case 3:
				x-=speed;
				break;
			}
			
			//�ж��ӵ��Ƿ������߽�
			if(x<0||x>400||y<0||y>300)
			{
				this.isLive=false;
				break;
			}
		}
	}
	
	
}
