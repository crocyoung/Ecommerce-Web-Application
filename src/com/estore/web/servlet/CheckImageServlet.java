package com.estore.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*  
 * 		generate the captcha in Session
 * 
 */
public class CheckImageServlet extends HttpServlet {
	
	//变大小写 
	private final int WIDTH =120;  // ctrl+shift+x / y 
	private final int HEIGHT =30;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// generate a picture
		BufferedImage bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = (Graphics2D) bf.getGraphics();
		
		// set the background color
		Color color = new Color(203, 222, 225);
		graphics.setColor(color);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		String base ="ABCDEFGHIJKLMN";
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();

		 
        // String str ="";
		
		// is there a performance issue with code like this ?
		
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("楷体",Font.BOLD,18));
		int m = 13;
		// draw 4 letters 
		for (int i = 0; i < 4; i++) {
			
			int index = random.nextInt(base.length());
			char charAt = base.charAt(index);
			
			//  -30 --- 30      15
			int jiaodu = random.nextInt(60)-30;
			
            // change gradients
			double theta = jiaodu*Math.PI/180;
			
			//radian 
			graphics.rotate(theta,  m, 15);
			sb.append(charAt);
			graphics.drawString(charAt+"", m, 19);
			graphics.rotate(-theta,  m, 15);
			m+=20;
		}
		
	/***************************************************************************/
		request.getSession().setAttribute("checkcode_session", sb.toString());
		
	/***************************************************************************/
		
		// disturbed line
		graphics.setColor(Color.BLUE);
		for (int i = 0; i < 4; i++) {
			//random coordinates
			int x1 = random.nextInt(WIDTH);
			int x2 = random.nextInt(WIDTH);
			int y1 = random.nextInt(HEIGHT);
			int y2 = random.nextInt(HEIGHT);
			graphics.drawLine(x1, y1, x2, y2);
		}
		
		// release
		graphics.dispose();
		
		ImageIO.write(bf, "png", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
