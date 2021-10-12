package com.zh.Ui;

import java.awt.*;

import javax.swing.*;
//这是一个开始的闪烁面板

public class MyStartPanel extends JPanel implements Runnable{
	

	int times=1;
	
	public void paint(Graphics g)
	{

		
		super.paint(g);

		g.fillRect(0, 0, 400, 300);
		
		if(times%2==0)
		{
			//给出提示信息
			g.setColor(Color.yellow);
			Font mtFont=new Font("华文新魏",Font.BOLD,30);
			g.setFont(mtFont);
			g.drawString("Stage: 1", 150, 150);
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try{
				Thread.sleep(1000);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			times++;
			//重画
			this.repaint();
		}
	}
}
