package com.zh.Ui;

import java.awt.*;

import javax.swing.*;
//����һ����ʼ����˸���

public class MyStartPanel extends JPanel implements Runnable{
	

	int times=1;
	
	public void paint(Graphics g)
	{

		
		super.paint(g);

		g.fillRect(0, 0, 400, 300);
		
		if(times%2==0)
		{
			//������ʾ��Ϣ
			g.setColor(Color.yellow);
			Font mtFont=new Font("������κ",Font.BOLD,30);
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
			//�ػ�
			this.repaint();
		}
	}
}
