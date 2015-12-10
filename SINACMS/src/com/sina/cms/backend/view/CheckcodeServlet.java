package com.sina.cms.backend.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.backend.tools.NumberTool;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@WebServlet(urlPatterns = "/backend/images/checkcode.jpg", initParams = 
				{@WebInitParam(name="width", value="44"),
				@WebInitParam(name="height", value="20"),
				@WebInitParam(name="number", value="4"),
				@WebInitParam(name="codes", value="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"),
				})
public class CheckcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	private int number;
	private String codes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckcodeServlet() {
        super();
       
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	width = Integer.parseInt(config.getInitParameter("width"));
    	height = Integer.parseInt(config.getInitParameter("height"));
    	number = Integer.parseInt(config.getInitParameter("number"));
    	codes = config.getInitParameter("codes");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("image/jpeg");
		this.generateImage(response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private void generateImage(OutputStream out) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		int x = (width - 1) / number;
		int y = height - 4;
		
		Random random = new Random();
		for(int i = 0 ; i < number; i++) {
			int index = random.nextInt(codes.length());
			String c = "" +  codes.charAt(index);
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.setFont(new Font("Arial", Font.PLAIN, NumberTool.random(height/2, height)));
			g.drawString(c, i*x + 3, y);
		}
		
		//随机生成点
		for(int i = 0; i < 50 ; i++) {
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		try {
			encoder.encode(image);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
