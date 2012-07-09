


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;

public class GraphicProcess extends JPanel {

	private Color[] color= new Color[MAX_SIZE];
	private Rectangle2D.Float[] myRect = new Rectangle2D.Float[MAX_SIZE] ;
	JLabel CPU;
	JLabel Disk;
	int size=0;
	int lastXCPU=30;
	int lastXDisk=30;
	int PID_CPU=-1;
	int PID_DISK=-1;
	public static final int MAX_SIZE=200;
	public static final int CPU_Y=40;
	public static final int DISK_Y=100;
	public static final int WIDTH=20;
	public static final int TICK_LENGTH=30;

	

	public GraphicProcess() {
		Arrays.fill(color, null);
		Arrays.fill(myRect, null);
		CPU = new JLabel("CPU");
		Disk = new JLabel("Disk");
		add(CPU);
		add(Disk);
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		CPU.setBounds(0, CPU_Y-10, 40, 40);
		Disk.setBounds(0, DISK_Y-10, 40, 40);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		for(int i=0; i<size; i++)
		{	
			g2d.setColor(color[i]);
			g2d.fill(myRect[i]);
		}
		
	}

	public void redraw(int index, int x, int y, int length, Color color)
	{
		myRect[index].width=length;
		myRect[index].y=y;
		myRect[index].x=x;
		this.color[index] = color;
		repaint();
	}
	public void addRect( int x, int y, int length, Color color)
	{
		myRect[size]=new Rectangle2D.Float(x, y, length, WIDTH);
		this.color[size]= color;
		size++;
		repaint();
	}

	public void processOnCPU(int PID)
	{
		PID_CPU=PID;
	}
	public void processLostCPU (int PID)
	{
		addRect(lastXCPU, CPU_Y, TICK_LENGTH, getPIDcolor(PID));
		lastXCPU+=TICK_LENGTH;
	}

	private Color getPIDcolor(int PID) {
		if(PID%2==0){
			if(PID%3==0)
				return Color.RED;
			else		
				return Color.GREEN;
		}
		else{
			if(PID%3==0)		
				return Color.BLUE;
			else
				return Color.PINK;
		}
	}

	public void processOnDisk(int PID)
	{
		PID_DISK=PID;
	}
	public void processDoneDisk (int PID, int ticks)
	{
		addRect(lastXDisk, DISK_Y, TICK_LENGTH*ticks, getPIDcolor(PID));
		lastXDisk+=(TICK_LENGTH*ticks);
	
	}

	public void initGraphicProcessWindow(GraphicProcess m)
	{
		
		JFrame frame = new JFrame("processGraphic");
		JScrollPane scrollBar=new JScrollPane(m,
	            JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scrollBar);  
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frame.setSize(maxBounds.width, 200); //maximiza na horizontal
		
		frame.setLayout(new GridLayout(1, 0));
		frame.setVisible(true);
	}


	//set ui visible//
//	public static void main(String args[]) {
//
//		GraphicProcess m = new GraphicProcess();
//		m.initGraphicProcessWindow(m);
//
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String user_input;
//		while(true){
//			try {
//				user_input = reader.readLine();
//				if(user_input.equals("redraw")){
//					user_input = reader.readLine();
//					m.processLostCPU(Integer.parseInt(user_input));
//				}
//				if(user_input.equals("add")){
//					user_input = reader.readLine();
//					int PID =Integer.parseInt(user_input);
//					user_input = reader.readLine();
//					int ticks =Integer.parseInt(user_input);
//					m.processDoneDisk(PID, ticks);
//				}
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//		}


	//}









}



