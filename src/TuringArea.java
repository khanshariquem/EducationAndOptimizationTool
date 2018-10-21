import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class TuringArea extends JPanel implements ActionListener
{
	
	MainArea mainArea = new MainArea(); 
	int flag=0;
	int data;
	Button B1= new Button("Run");
	TuringArea(Interface _interface, int i)
	{
		this.data=i;
		this.setLayout(null);
		B1.addActionListener(this);
		mainArea.add(B1);
		
		int height=_interface.getHeight(), width = _interface.getWidth();
		//this.setBounds(0,0,width, height);
		//this.setBackground(Color.GRAY);
		mainArea.setBackground(Color.WHITE);
		mainArea.setBounds(0,0, width, height);
		this.add(mainArea);
		System.out.println(i+"");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String x,y;
		int a,b;
			if(e.getSource()==B1 && (data==21 || data== 22))
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input Larger Number",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				y=(String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input Smaller Number",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				
				a=Integer.parseInt(x);
				b=Integer.parseInt(y);
				
				if(b>a)
				{
					a+=b;
					b=a-b;
					a=a-b;					
				}
				x=""+a;
				y=""+b;
				
				if(data==21)mainArea.simulateAddTuringMachine(x,y);
				if(data==22)mainArea.simulateSubTuringMachine(x,y);
				
			}
			else if(e.getSource()==B1 && data==23)
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input String to copy",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				mainArea.TuringCopier(x);
			}
			else if(e.getSource()==B1 && data==24)
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input string of 0's",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				mainArea.simulatePower2TuringMachine(x);
			}
			else if(e.getSource()==B1 && data==25)
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input string of a's and b's",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				mainArea.simulatePalindromeTuring(x);
			}
			else if(e.getSource()==B1 && data==26)
			{
				char c;
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input string of a's and b's",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				y = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input Character to be deleted",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				c=y.charAt(0);
				mainArea.simulateDeleteTuring(x,c);
				
			}
			else if(e.getSource()==B1 && data==27)
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input string of a's and b's",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				mainArea.simulateEraserTuring(x);
				
			}
			else if(e.getSource()==B1 && data==28)
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input Number To Increment",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				mainArea.simulateIncrementerTuring(x);
				
			}
			else if(e.getSource()==B1 && data==29)
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input Number To Increment",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				mainArea.simulateDecrementerTuring(x);
				
			}
			else if(e.getSource()==B1 && data==30)
			{
				x = (String)JOptionPane.showInputDialog(
		                this                ,
		                "Input",
		                "Input string of 0's and 1's",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				mainArea.simulateEqual0and1Turing(x);
				
			}
		
	}
	
	
	
	
}