 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pad extends JFrame
{
	JPanel mainPanel;
	JButton launcherButtons[];
	String buttonText[] = {"Finite State Machine", "Mealy Machine", "Moore Machine","PushDown Automata" , "Regular Expression","Grammar","Turing machine","Exit"};
	Pad()
	{
		super("Launcher");
		mainPanel=new JPanel(new GridLayout(8,1));
		launcherButtons = new JButton[8];
		this.add(mainPanel);
		for(int i=0;i<8;i++)
		{
			launcherButtons[i] = new JButton(buttonText[i]);
			mainPanel.add(launcherButtons[i]);
			launcherButtons[i].addActionListener(new ActionClass(i, this));
		}
		Dimension d = new Dimension(300, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(d);
		this.setResizable(false);
		this.setVisible(true);
	
	}

	public static void main(String ar[])
	{
		new Pad();
	}
}
