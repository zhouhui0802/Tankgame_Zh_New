package com.zh.test;

import javax.swing.*;
import java.awt.*;
public class t1 extends JFrame{
private String[][] data = {{"��̖һ","�Y��һ"},{"��̖��","�Y�϶�"},{"��̖��","�Y����"},{"��̖��","�Y����"},{"��̖��","�Y����"},{"��̖��","�Y����"},{"��̖��","�Y����"},{"��̖��","�Y�ϰ�"}};
private String[] dataTitle = {"��̖","����"};
private JTable jtable = new JTable(data, dataTitle);
private JScrollPane jscrollpane = new JScrollPane(jtable);
public t1() {
setTitle("�������");
setBounds(300,300,100,100);
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
add(jscrollpane,BorderLayout.CENTER);
}
public static void main(String[] args) {
// TODO Auto-generated method stub
t1 T = new t1(); 
}
}
