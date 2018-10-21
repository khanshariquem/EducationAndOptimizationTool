import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Expression extends JPanel implements ActionListener
{
	public TextField t=new TextField(50);
	Button b1=new Button("submit");
	Button b2=new Button("Go");
	String s1="";
	Interface i;
	
	
	Expression(Interface _interface)
	{
		
		this.add(t);
		this.add(b1);
		this.add(b2);
	   i=_interface;
		
		b1.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
System.out.println("same class    "+t.getText());
this.s1=t.getText();
b2.addActionListener(new ActionClass(11,i,s1));
//new Interface(ActionClass.data,i.p, i,t.getText());
//ActionClass a=new ActionClass(11,i,this);
	}

}
