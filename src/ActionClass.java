import java.awt.event.*;

import javax.swing.JOptionPane;
public class ActionClass implements ActionListener 
{
	 int data;
	Pad p=null;//=new Pad();
	Interface if1=null;
	String regex;
	String[][] reg;
	int nop;
	ActionClass(int i, Pad p)
	{
		data = i;
		this.p = p;
	}
	
	ActionClass(int i, Interface inf,String ex)
	{
		data = i;
		this.if1=inf;
		this.regex=ex;
		System.out.print(regex+"   postreg: in action");
	}
	ActionClass(int i, Interface inf,String[][] ex,int nop)
	{
		data = i;
		this.if1=inf;
		this.reg=ex;
		this.nop=nop;
		System.out.print(data+"   postreg: in action");
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(data<10)p.setVisible(false);
		
		
		if(data==7) System.exit(0);
		if(data==11 || data== 12)if1.setVisible(false);
	
		if(data==12)new Interface(data, p , if1,reg,nop);
		else if(data==6)
		{
			Object[] possibilities = {"Binary Adder","Binary Substractor","String Copier","Power Of 2 length String","Palindrome","Delete A Symbol","Eraser","Binary Incrementer","Binary Decrementer","Equal Number Of 1's And 0's"};
			String s = (String)JOptionPane.showInputDialog(
			                    p,
			                    "Select",
			                    "Choose Machine",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    "");
			if(s=="Binary Adder")
			{
				new Interface(21, p , if1,regex);
			}
			else if (s=="Binary Substractor")
			{
				new Interface(22, p , if1,regex);
			}
			else if (s=="String Copier")
			{
				new Interface(23, p , if1,regex);
			}
			else if (s=="Power Of 2 length String")
			{
				new Interface(24,p,if1,regex);
			}
			else if (s=="Palindrome")
			{
				new Interface(25,p,if1,regex);
			}
			else if (s=="Delete A Symbol")
			{
				new Interface(26,p,if1,regex);
			}
			else if(s=="Eraser")
			{
				new Interface(27,p,if1,regex);
			}
			else if(s=="Binary Incrementer")
			{
				new Interface(28,p,if1,regex);
			}
			else if(s=="Binary Decrementer")
			{
				new Interface(29,p,if1,regex);
			}
			else if(s=="Equal Number Of 1's And 0's")
			{
				new Interface(30,p,if1,regex);
			}
			

		}
		else new Interface(data, p , if1,regex);
		
		
	}
}
