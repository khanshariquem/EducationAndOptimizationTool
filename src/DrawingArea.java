import java.applet.Applet;
import java.awt.*;

import javax.swing.*;
public class DrawingArea extends JPanel
{
	ToolBox toolbox=new ToolBox(this);
	MainArea mainArea = new MainArea(); 
	int flag=0;
	DrawingArea(Interface _interface)
	{
		
		this.setLayout(null);
		
		
		
		
		int height=_interface.getHeight(), width = _interface.getWidth();
		
		toolbox.setBounds(0,0,width, height/15);
		System.out.println(width+" <><> "+height);
		toolbox.setBackground(Color.GRAY);
		
		this.add(toolbox);
		mainArea.setBackground(Color.WHITE);
		mainArea.setBounds(0, height/15, width, 14*(height/15));
		this.add(mainArea);
		
	}
	
	DrawingArea(Interface _interface,String regex)
	{
		
		this.setLayout(null);
		
		
		
		
		int height=_interface.getHeight(), width = _interface.getWidth();
		
		toolbox.setBounds(0,0,width, height/15);
		System.out.println(width+" <><> "+height);
		toolbox.setBackground(Color.GRAY);
		
		this.add(toolbox);
		mainArea.setBackground(Color.WHITE);
		mainArea.setBounds(0, height/15, width, 14*(height/15));
		this.add(mainArea);
		System.out.print(regex+"   postreg: in DA");
		mainArea.makedfa(regex,mainArea);
		
		
	}
	DrawingArea(Interface _interface,String[][] regex,int nop)
	{
		
		this.setLayout(null);
		
		
		
		
		int height=_interface.getHeight(), width = _interface.getWidth();
		
		toolbox.setBounds(0,0,width, height/15);
		System.out.println(width+" <><> "+height);
		toolbox.setBackground(Color.GRAY);
		
		this.add(toolbox);
		mainArea.setBackground(Color.WHITE);
		mainArea.setBounds(0, height/15, width, 14*(height/15));
		this.add(mainArea);
		System.out.print(regex+"   postreg: in DA");
		mainArea.makeAutoMata(regex,mainArea,nop);
		
		
	}
	
}
