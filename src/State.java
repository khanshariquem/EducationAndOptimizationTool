import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;

public class State
{
	int x,y;
	int cc,no;
	char tag='\0';
	boolean isfinal=false;
	String output=new String("");
	MainArea m;
	boolean isaccepting,isrejecting;
	//(cc)current colour 1=current state red  0=initial colour black     
	State(){}
	State(int x,int y,int no,MainArea ma,int color,String out)
	{
	   Graphics g=ma.getGraphics();
	   this.x=x;
	   this.y=y;
	   m=ma;
	   this.cc=color;
	   this.no=no;
	   output=out;
	  if(Interface.machine<20)
	  {
	   g.fillOval(x,y,40,40);
	   g.setColor(Color.WHITE);
	   g.drawString("Q"+no, x+15, y+25);
	   
	   
	   if(output!=""){g.setColor(Color.GREEN); g.drawString(output, x+15, y+65);}
	   g.setColor(Color.BLACK);
	  }
	   
	}
	
	State(int x,int y,char tag,MainArea ma,int color,String out)
	{
	   Graphics g=ma.getGraphics();
	   this.x=x;
	   this.y=y;
	   m=ma;
	   this.cc=color;
	   this.tag=tag;
	  
	   g.fillOval(x,y,40,40);
	   g.setColor(Color.WHITE);
	   g.drawString(""+this.tag, x+15, y+25);
	   
	   output=out;
	   if(output!=""){g.setColor(Color.GREEN); g.drawString(output, x+15, y+65);}
	   g.setColor(Color.BLACK);
	   
	   
	}
	
	void drawit(int color)
	{
		if(color==0)
		{
			
			Graphics g=m.getGraphics();
			g.setColor(Color.BLACK);
			g.fillOval(x,y,40,40);
			g.setColor(Color.WHITE);
			g.drawString("Q"+no, x+15, y+25);
			if(output!=""){g.setColor(Color.GREEN); g.drawString(output, x+15, y+65);}
			g.setColor(Color.BLACK);
		}
		
		if(color==3)
		{
			Graphics g=m.getGraphics();
			g.setColor(Color.BLUE);
			g.fillOval(x,y,40,40);
			g.setColor(Color.WHITE);
			g.drawString("Q"+no, x+15, y+25);
			if(output!=""){g.setColor(Color.GREEN); g.drawString(output, x+15, y+65);}
			g.setColor(Color.BLACK);
		}
	}
	
	void drawfinal()
	{
		Graphics g=m.getGraphics();
		g.setColor(Color.BLACK);
		g.drawOval(x,y,43,43);
		if(output!=""){g.setColor(Color.GREEN); g.drawString(output, x+15, y+65);}
		g.setColor(Color.BLACK);
	}
	
	void drawCurrent(String inp1,int inplength,String stackarray)
	{
		Graphics g=m.getGraphics();
		Font f=g.getFont();	
		g.setFont(new Font("TimesRoman", Font.PLAIN,40));
		g.drawString(inp1.substring(0, inp1.length()-inplength),0,40);
		
		
		g.setFont(new Font("Times New Roman",Font.PLAIN,20));
	    
	
		if(Interface.machine==3)
			{
			g.drawString("________________________________________",200,200);
			g.drawChars(stackarray.toCharArray(),0,stackarray.length(), 200, 20);
			}
		g.setFont(f);
		
		
		
		g.setColor(Color.MAGENTA);
		g.fillOval(x,y,40,40);
		g.setColor(Color.white);
		g.drawString("Q"+no, x+15, y+25);
		if(output!=""){g.setColor(Color.GREEN); g.drawString(output, x+15, y+65);}
		g.setColor(Color.black);
		
		
		
		
	}
	
	void removecurrent(String out)
	{
		Graphics g=m.getGraphics();
		
		Font f=g.getFont();	
		g.setFont(new Font("TimesRoman", Font.PLAIN,40));
		g.drawString(out,200,40);
		g.setFont(f);
		
		g.setColor(Color.black);
		g.fillOval(x,y,40,40);
		//g.drawOval(x,y,40,40);
		g.setColor(Color.white);
		g.drawString("Q"+no, x+15, y+25);
		if(output!=""){g.setColor(Color.GREEN); g.drawString(output, x+15, y+65);}
		g.setColor(Color.black);
		
		
		
		
	}

}
