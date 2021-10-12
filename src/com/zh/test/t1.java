package com.zh.test;

import javax.swing.*;
import java.awt.*;
public class t1 extends JFrame{
private String[][] data = {{"編號一","資料一"},{"編號二","資料二"},{"編號三","資料三"},{"編號四","資料四"},{"編號五","資料五"},{"編號六","資料六"},{"編號七","資料七"},{"編號八","資料八"}};
private String[] dataTitle = {"編號","內容"};
private JTable jtable = new JTable(data, dataTitle);
private JScrollPane jscrollpane = new JScrollPane(jtable);
public t1() {
setTitle("建立表格");
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
