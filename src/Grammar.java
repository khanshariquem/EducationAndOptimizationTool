import java.applet.Applet;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


public class Grammar extends JPanel implements ActionListener
{
	
	
	TextField t[][]=new TextField[10][3];
	Button b1=new Button("Next Production");
	Button b2=new Button("Submit");
	String prod[][]=new String[10][2];
	Interface i;
	int nop=0;
	
	JPanel jtp=new JPanel();
	
	
	Grammar(Interface _interface)
	{
		
		this.setLayout(new GridLayout(10,1));
		for(int a=0;a<3;a++)
		{
			t[0][a]=new TextField(null,10);
			if(a==1)
			{
				t[0][a].setText("----------->");
				t[0][a].setEditable(false);
			}
			jtp.add(t[0][a]);
		}
		jtp.add(b1);
		jtp.add(b2);
		this.add(jtp);
	   i=_interface;
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==b1)
		{
			if(t[nop][2].getText()!="")
			{
				jtp=new JPanel();
			nop++;
			
			for(int a=0;a<3;a++)
			{
				t[nop][a]=new TextField(null,10);
				if(a==1)
				{
					t[nop][a].setText("---------------->");
					t[nop][a].setEditable(false);
				}
				jtp.add(t[nop][a]);
			}
			this.add(jtp);
		}
		}
		
		if(e.getSource()==b2)
		{
			for(int j=0;j<=nop;j++)
			{
				prod[j][0]=new String(t[j][0].getText());
				prod[j][1]=new String(t[j][2].getText());
			}
			
			for(int j=0;j<=nop;j++)
			{
				System.out.println(prod[j][0]+"--->"+prod[j][1]);
			}
			 Interface.machine=12;
			new ActionClass(12,i,prod,nop).actionPerformed(e);
			
		}
	}

}
