package com.zh.Entity;

import java.util.Vector;

import com.zh.Bullet.Shot;
import com.zh.Ui.MyGamePanel;



//���˵�̹��
public class EnemyTank extends Tank implements Runnable
{


	public int times=0;
	
	//����һ�����������Է��ʵ�MyPanel�����еĵ���̹��
	public Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//����һ�����������Դ�ŵ��˵��ӵ�
	public Vector<Shot> ss=new Vector<Shot>();

	
	public EnemyTank(int x, int y) {
		super(x, y);
	}
	
	//�õ�MyPanel�ĵ��˵�̹������
	public void setEts(Vector<EnemyTank> vv)
	{
		this.ets=vv;
	}
	
	//�ж��Ƿ������˵��˵�̹��
	public boolean  isTouchOtherEnemy()
	{
		//System.out.println("��ʼ�жϵ���֮���Ƿ���ײ");
		boolean b=false;
		
		switch(this.direct)
		{
		case 0:
			//�ҵ�̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		}
		//System.out.println("�жϵ���֮���Ƿ���ײ����");
		return b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			//��������̹�˵��̣߳������Զ��ƶ�
			switch(this.direct)
			{
			case 0:
				//˵��̹����������
				for(int i=0;i<30;i++)
				{
					if(y>0&&!this.isTouchOtherEnemy())
					{
						y-=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			case 1:
				//����
				for(int i=0;i<30;i++)
				{
					if(x<370&&!this.isTouchOtherEnemy())
					{
						x+=speed;
					}

					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			case 2:
				//����
				for(int i=0;i<30;i++)
				{
					if(y<270&&!this.isTouchOtherEnemy())
					{
						y+=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			case 3:
				//����
				for(int i=0;i<30;i++)
				{
					if(x>0&&!this.isTouchOtherEnemy())
					{
						x-=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			}
			
			
			this.times++;
			//if(times%2==0)
			//{
				if(isLive)
				{
					if(ss.size()<5)
					{
						Shot s=null;
						
						//û���ӵ�
						//���
						switch(direct)
						{
						case 0:
							//����һ���ӵ�
							s=new Shot(x+10,y,0);
							s.speed=Integer.parseInt(MyGamePanel.tankShot);
							//���ӵ���������
							ss.add(s);
							break;
						case 1:
							s=new Shot(x+30,y+10,1);
							s.speed=Integer.parseInt(MyGamePanel.tankShot);
							ss.add(s);
							break;
						case 2:
							s=new Shot(x+10,y+30,2);
							s.speed=Integer.parseInt(MyGamePanel.tankShot);
							ss.add(s);
							break;
						case 3:
							s=new Shot(x,y+10,3);
							s.speed=Integer.parseInt(MyGamePanel.tankShot);
							ss.add(s);
							break;
						}
						
						//�����ӵ�
						Thread t=new Thread(s);
						t.start();
					}
				}
			//}
			
			//����̹����ײ֮����̹���������һ���µķ���
			this.direct=(int)(Math.random()*4);
			
			//�жϵ���̸���Ƿ�����
			if(this.isLive==false)
			{
				//�õ���̹���������˳��߳�
				break;
			}
		}//while(true)��������
	}//�߳�run��������
	
}
