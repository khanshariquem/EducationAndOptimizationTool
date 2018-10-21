import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
public class ToolBox extends JPanel implements ActionListener
{
	JButton items[];
	String itemLabel[] = {"State", "Init state","Final state","Transition","Run"};
	public static int choice=-1; 
	public static String inp;
	
	ToolBox(DrawingArea da)
	{
		super(new FlowLayout());
		items = new JButton[5];
		for(int i=0; i<5; i++)
		{
			items[i] = new JButton(itemLabel[i]);
			this.add(items[i]);
			items[i].addActionListener(this);
		}
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==items[0])            // for state Drawing
		{
			choice=0;
		}
		else if(ae.getSource()==items[1])
		{
			choice=3;                          // for initial state
		}
		else if(ae.getSource()==items[2])
		{
			choice=4;                          // for final state
		}
		else if(ae.getSource()==items[3])          
		{
			choice=1;
		}
		else if(ae.getSource()==items[4])           // Execution
		{
			choice=2;
			inp =  JOptionPane.showInputDialog(this,"Enter input string");
			if(Interface.machine==0)(new MainArea()).simulate_fsm();
			if(Interface.machine==1)(new MainArea()).simulate_mealy();
			if(Interface.machine==2)(new MainArea()).simulate_moore();
			if(Interface.machine==3)(new MainArea()).simulate_pda();
		
		}
		else
			choice =-1;
	}

}
