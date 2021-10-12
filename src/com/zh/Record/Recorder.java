package com.zh.Record;

import java.io.*;
import java.util.Vector;

import com.zh.Entity.EnemyTank;
import com.zh.Entity.Hero;
import com.zh.Ui.MyGamePanel;

public class Recorder {
	
	//��¼ÿһ���ж��ٵ���
	private static int enNum=Integer.parseInt(MyGamePanel.tankLife);
	
	//�������ж��ٿ��õ���
	private static int myLife=Integer.parseInt(MyGamePanel.heroLife);
	
	//��¼�ܹ������˶��ٵ���
	private static int allEnNum=0;
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	private Vector<EnemyTank> ets=new Vector<EnemyTank>();
	private Vector<Hero> heros=new Vector<Hero>();
	
	//private Hero hero=new Hero();
	
	//���ļ��лָ���¼
	static Vector<Node> nodes=new Vector<Node>();
	
	//��ɶ�ȡ�ļ�
	public Vector<Node> getNodesAndEnNums()
	{
		try{
			fr=new FileReader("myRecording.txt");
			br=new BufferedReader(fr);
			//��һ�У��Ѿ�������˵�����
			String n="";
			n=br.readLine();
			allEnNum=Integer.parseInt(n);
			
			//�ڶ��У�ʣ����˵�����
			String m="";
			m=br.readLine();
			enNum=Integer.parseInt(m);
			
			//�����У�����̹�˵�����
			String k="";
			k=br.readLine();
			myLife=Integer.parseInt(k);
			
			while((n=br.readLine())!=null)
			{
				//���ж������ݣ�����ڵ�
				String []xyz=n.split(" ");
				Node node=new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				br.close();
				fr.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return nodes;
	}
	
	//������ٵ��˵������ͣ�ʣ��̹�˵��������Լ�����̹�˵��������Լ�����̹�˵�����ͷ��򣬵���̹�˵������Լ�����
	public void keepRecAndEnemyTank()
	{
		try{
			fw=new FileWriter("myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			bw.write(enNum+"\r\n");
			bw.write(myLife+"\r\n");
			
			//���浱ǰ����̹�˵������뷽��
			for(int i=0;i<heros.size();i++)
			{
				System.out.println("1111111111");
				Hero hero=heros.get(i);
				
				if(hero.isLive&&myLife>0)
				{
					String recode=hero.x+" "+hero.y+" "+hero.direct;
					bw.write(recode+"\r\n");
				}else
				{
					String recode=0+" "+0+" "+0;
					bw.write(recode+"\r\n");
				}
			}
			
			//���浱ǰ��ĵ��˵�����ͷ���
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
				
				if(et.isLive)
				{
					String recode=et.x+" "+et.y+" "+et.direct;
					bw.write(recode+"\r\n");
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				bw.close();
				fw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//���ļ��ж�ȡ��¼��������ȡ���ٵ��˵�����
	public static void getRecording()
	{
		try{
			fr=new FileReader("myRecording.txt");
			br=new BufferedReader(fr);
			String n=br.readLine();
			allEnNum=Integer.parseInt(n);
		}catch(Exception e)
		{
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
	
	//����һ��ٵĵ����������浽�ļ��У���������һ��
	public static void keepRecording()
	{
		try{
			fw=new FileWriter("myRecording.txt");
			bw=new BufferedWriter(fw);
			bw.write(allEnNum+"\r\n");			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				bw.close();
				fw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//���ٵ�������
	public static void reduceEnNum()
	{
		System.out.println("enNum="+enNum);
		enNum--;
	}
	
	//����ĵ���
	public static void addEnNumRec()
	{
		allEnNum++;
	}
	
	//�Լ�̹�˵���������
	public static void reduceMylife()
	{
		myLife--;
	}
	

	public static int getEnNum() {
		return enNum;
	}

	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}

	public Vector<Hero> getHeros() {
		return heros;
	}

	public void setHeros(Vector<Hero> heros) {
		this.heros = heros;
	}

	public static int getMyLife() {
		return myLife;
	}

	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}

	public static int getAllEnNum() {
		return allEnNum;
	}

	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}

	public Vector<EnemyTank> getEts() {
		return ets;
	}

	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}
	

	
}
