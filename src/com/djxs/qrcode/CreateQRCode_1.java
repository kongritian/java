package com.djxs.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode_1 {
	public static void CreatQrCode(int ver,char qrcodeErrorCorrect, String logoPath, 
			int logoSize,String qrCodeImagePath,String startColor,String endColor) throws IOException {
	
		String str = "BEGIN:VCARD\n" + 
		  "N:孔\n"+
		  "FN:昊\n" + 
		  "ORG:河北科技师范学院\n" + 
		  "TITLE:java开发工程师\n" + 
		  "ADR;WORK:河北科技师范学院\n" + 
		  "ADR;HOME:秦皇岛\n" + 
		  "TEL;CELL,VOICE:110\n" + 
		  "TEL;WORK,VOICE:0335-1111111\r\n" + 
		  "URL;WORK;:http://www.baidu.com\n" + 
		  "EMAIL;INTERNET,HOME:1054081517@qq.com\n" + 
		  "END:VCARD";
		Qrcode qrcdoe = new Qrcode();
		qrcdoe.setQrcodeVersion(ver);
	
		int  imageSize = 67+12*(ver-1);
	
		BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
	
	Graphics2D gs = bufferedImage.createGraphics();
	Image image=ImageIO.read(new File(logoPath));
		gs.setBackground(Color.WHITE);

		gs.setColor(Color.BLACK);
	
		gs.clearRect(0, 0, imageSize, imageSize);
		int startR = Integer.parseInt(startColor.split(",")[0]);
		int startG = Integer.parseInt(startColor.split(",")[1]);
		int startB = Integer.parseInt(startColor.split(",")[2]);
		int endR = Integer.parseInt(endColor.split(",")[0]);
		int endG = Integer.parseInt(endColor.split(",")[1]);
		int endB = Integer.parseInt(endColor.split(",")[2]);
        boolean[][] calQrcode = qrcdoe.calQrcode(str.getBytes("UTF-8"));
		int x = 2;
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode.length; j++) {
				if(calQrcode[i][j]){
					int num1 = startR + (endR - startR) * (i+1)/calQrcode.length;
					int num2 = startG + (endG - startG) * (i+1)/calQrcode.length;
					int num3 = startB + (endB - startB) * (i+1)/calQrcode.length;
					
					Color color = new Color(num1, num2, num3);
					
					gs.setColor(color);
					
					gs.fillRect(i*3+x, j*3+x, 3, 3);
					
				}
			}
		}
		
		
	int size=(imageSize-logoSize)/2;
	gs.drawImage(image, size, size, logoSize, logoSize,null);
	qrcdoe.setQrcodeErrorCorrect(qrcodeErrorCorrect);
		gs.dispose();
		bufferedImage.flush();

		ImageIO.write(bufferedImage, "png", new File(qrCodeImagePath));
	}
}
