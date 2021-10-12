package com.zh.Record;

import java.io.*;
import java.util.Vector;

import com.zh.Entity.EnemyTank;
import com.zh.Entity.Hero;
import com.zh.Ui.MyGamePanel;

public class Recorder {
	
	//记录每一关有多少敌人
	private static int enNum=Integer.parseInt(MyGamePanel.tankLife);
	
	//设置我有多少可用的人
	private static int myLife=Integer.parseInt(MyGamePanel.heroLife);
	
	//记录总共消灭了多少敌人
	private static int allEnNum=0;
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	private Vector<EnemyTank> ets=new Vector<EnemyTank>();
	private Vector<Hero> heros=new Vector<Hero>();
	
	//private Hero hero=new Hero();
	
	//从文件中恢复记录
	static Vector<Node> nodes=new Vector<Node>();
	
	//完成读取文件
	public Vector<Node> getNodesAndEnNums()
	{
		try{
			fr=new FileReader("myRecording.txt");
			br=new BufferedReader(fr);
			//第一行，已经消灭敌人的数量
			String n="";
			n=br.readLine();
			allEnNum=Integer.parseInt(n);
			
			//第二行，剩余敌人的数量
			String m="";
			m=br.readLine();
			enNum=Integer.parseInt(m);
			
			//第三行，自身坦克的寿命
			String k="";
			k=br.readLine();
			myLife=Integer.parseInt(k);
			
			while((n=br.readLine())!=null)
			{
				//逐行读出数据，加入节点
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
	
	//保存击毁敌人的数量和，剩余坦克的数量，以及自身坦克的寿命，以及自身坦克的坐标和方向，敌人坦克的坐标以及方向
	public void keepRecAndEnemyTank()
	{
		try{
			fw=new FileWriter("myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			bw.write(enNum+"\r\n");
			bw.write(myLife+"\r\n");
			
			//保存当前自身坦克的坐标与方向
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
			
			//保存当前活动的敌人的坐标和方向
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
	
	//从文件中读取记录，单独读取击毁敌人的数量
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
	
	//把玩家击毁的敌人数量保存到文件中，单独操作一下
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
	
	//减少敌人数量
	public static void reduceEnNum()
	{
		System.out.println("enNum="+enNum);
		enNum--;
	}
	
	//消灭的敌人
	public static void addEnNumRec()
	{
		allEnNum++;
	}
	
	//自己坦克的寿命减少
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
