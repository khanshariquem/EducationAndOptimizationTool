import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Interface extends JFrame implements WindowListener
{
	Pad p;
	JMenuBar mainMenu;
	JMenu file, edit, help;
	static int machine;
	Interface if1;
	Interface(int i, Pad p, Interface if1 , String regex)
	{
		
		super("Interface");
		this.p = p;
		Dimension d = new Dimension(900, 600);
		this.setSize(d);
		this.addWindowListener(this);
		JTabbedPane editor = new JTabbedPane();
		
		System.out.println(regex+"   postreg: in Inter");
		if(i>20)editor.addTab("Turing Area", new TuringArea(this,i));
		else if(i==11)editor.addTab("Drawing Area", new DrawingArea(this,regex));
		else if(i==4)editor.addTab("Expression",new Expression(this));
		else if(i==5)editor.addTab("Grammar", new Grammar(this));
		else if(i<10)editor.addTab("Drawing Area", new DrawingArea(this));
		
		this.add(editor);
		mainMenu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		this.setResizable(false);
		mainMenu.add(file);
		mainMenu.add(edit);
		mainMenu.add(help);
		file.addSeparator();
		edit.addSeparator();
		help.addSeparator();
		this.setJMenuBar(mainMenu);
		machine=i;
		this.if1=if1;
		this.setVisible(true);
		
	}
	Interface(int i, Pad p, Interface if1 , String[][] regex,int nop)
	{
		
		super("Interface");
		System.out.println("interface constructor called");
		this.p = p;
		Dimension d = new Dimension(900, 600);
		this.setSize(d);
		this.addWindowListener(this);
		JTabbedPane editor = new JTabbedPane();
		editor.addTab("Drawing Area", new DrawingArea(this,regex,nop));
		this.add(editor);
		mainMenu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		this.setResizable(false);
		mainMenu.add(file);
		mainMenu.add(edit);
		mainMenu.add(help);
		file.addSeparator();
		edit.addSeparator();
		help.addSeparator();
		this.setJMenuBar(mainMenu);
		machine=i;
		this.if1=if1;
		this.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) 
	{
	//MainArea ma=new MainArea();
    //ma.repaintit();
		}
	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		//MainArea ma=new MainArea();
	
    //ma.repaintit();
		}
	@Override
	
	public void windowClosing(WindowEvent arg0) 
	{
		System.out.println("<><>Closing interface now<><>");
		MainArea.transcount=0;
		MainArea.initialstate=-1;
		if(machine<4)
			p.setVisible(true);
		else if(machine==4)
			if1.p.setVisible(true);
		else
		{
		this.setVisible(false);
		//if1.setVisible(true);	
		}
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) 
	{
	//MainArea ma1=new MainArea();
    //ma1.repaintit(); 
    }
	@Override
	public void windowDeiconified(WindowEvent arg0) 
	{//MainArea ma=new MainArea();
    //ma.repaintit();
	}
	@Override
	public void windowIconified(WindowEvent arg0) 
	{ //    MainArea ma=new MainArea();
	 //     ma.repaintit();
	}
	@Override
	public void windowOpened(WindowEvent arg0) 
	{//MainArea ma=new MainArea();
    //ma.repaintit();
	}
	
	

}
