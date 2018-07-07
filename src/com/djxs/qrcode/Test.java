package com.djxs.qrcode;

import java.io.IOException;

public class Test {
public static void main(String[] args) throws IOException {
	CreateQRCode_1 qrcode = new CreateQRCode_1();
	
	qrcode.CreatQrCode(20, 'Q', "tupian.jpg", 24, "MyTest.jpg", "0,0,255", "255,0,0");
	System.out.println("ok");
}
}
