package com.zh.Record;

import java.io.*;
import java.util.*;

import com.zh.User.*;

public class DataDisk {

	private static String filePath="PersonRecorder.dat";
	
	
	public List<Player>  loadData()
	{
		ObjectInputStream ois=null;
		List<Player> players=null;
		
		try{
			ois=new ObjectInputStream(new FileInputStream(filePath));
			players=(List<Player>)ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			try{
				ois.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if(players.size()==0)
		{
			System.out.println(0);
		}
		
		for(int i=0;i<players.size();i++)
		{
			Player p=players.get(i);
			p.getName();
			p.getPoint();
		}
		
		return players;
	}
	
	public void saveData(Player pla) {
		// TODO Auto-generated method stub
		
		//取出数据
		List<Player> players=this.loadData();
		//List<Player> players=new ArrayList<Player>();
		
		//追加新的记录
		players.add(pla);
		
		//重新写入
		ObjectOutputStream oos = null;
		try{
			oos=new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(players);
			System.out.println("写入成功");
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				oos.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DataDisk dd=new DataDisk();

		//dd.loadData();
//		Player p1=new Player("1",1);
//		dd.saveData(p1);
//		Player p2=new Player("2",2);
//		dd.saveData(p2);
//		Player p3=new Player("3",3);
//		dd.saveData(p3);
//		dd.loadData();
		
	}

}
