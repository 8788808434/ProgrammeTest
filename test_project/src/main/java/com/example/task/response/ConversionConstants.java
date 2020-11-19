package com.example.task.response;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;



public class ConversionConstants {

	
	public static String byteToString(String files) throws IOException
	{
		String ext1 = FilenameUtils.getExtension(files);
		File file = new File(files); 
		BufferedImage image = ImageIO.read(file);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, ext1, baos );
		byte[] bytes=baos.toByteArray();
		byte[] bytes1=Cmpress.compressBytes(bytes);
		String encodedString = Base64.getEncoder().encodeToString(bytes1);
		return encodedString;
	}
	
	public static byte[] StringToByte(String image)
	{
		byte[] decodedBytes = Base64.getDecoder().decode(image);
		
		return Cmpress.decompressBytes(decodedBytes);
	}
}
