/**
@file ImgScalrWrapper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | ImgScalrWrapper.java |    
| Package | com.paintee.common.util |    
| Project name | paintee-admin |    
| Type name | ImgScalrWrapper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 30. 오후 11:14:34 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
@class ImgScalrWrapper
com.paintee.common.util \n
   ㄴ ImgScalrWrapper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 30. 오후 11:14:34 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 이미지 처리 wrapper
*/
@Component(value="com.paintee.common.util.ImgScalrWrapper")
public class ImgScalrWrapper {
	private final static Logger logger = LoggerFactory.getLogger(ImgScalrWrapper.class);

	/**
	 @fn writeJpegImage
	 @brief 함수 간략한 설명 : 주어진 BufferedImage 객체를 file로 기록
	 @remark
	 - 함수의 상세 설명 : 주어진 BufferedImage 객체를 file로 기록
	 @param bufferedImage - BufferedImage 객체
	 @param resultFile - BufferedImage 객체가 기록될 file
	 @param quality - JPEG 압축률(0.0f ~ 1.0f)
	 @throws IOException 
	*/
	public void writeJpegImage(BufferedImage bufferedImage, File resultFile, float quality) throws IOException {
		ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(resultFile);
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpeg");

		if (iterator.hasNext() == false) {
			logger.debug("# ImageWriter not available.");
			return;
		}

		ImageWriter imageWriter = iterator.next();
	
		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriteParam.setCompressionQuality(quality);
	
		imageWriter.setOutput(imageOutputStream);
		imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
		imageWriter.dispose();
	}
	public void writeJpegImage(BufferedImage bufferedImage, File resultFile, float quality, int mode) throws IOException {
		ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(resultFile);
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpeg");

		if (iterator.hasNext() == false) {
			logger.debug("# ImageWriter not available.");
			return;
		}

		ImageWriter imageWriter = iterator.next();
	
		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
		imageWriteParam.setCompressionMode(mode);
		imageWriteParam.setCompressionQuality(quality);
	
		imageWriter.setOutput(imageOutputStream);
		imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
		imageWriter.dispose();
	}

	/**
	 @fn resize
	 @brief 함수 간략한 설명 : 이미지 resize
	 @remark
	 - 함수의 상세 설명 : 이미지 resize
	 @param sourceFile 원본 이미지
	 @param resultFile 결과 이미지
	 @param width 넓이(단위: pixel)
	 @param height 높이(단위: pixel)
	 @throws IOException 
	*/
	public void resize(File sourceFile, File resultFile, int width, int height) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(sourceFile);
		BufferedImage doneImage = Scalr.resize(bufferedImage, Mode.AUTOMATIC, width, height, Scalr.OP_ANTIALIAS);

		writeJpegImage(doneImage, resultFile, 0.75f);
	}

	/**
	 @fn crop
	 @brief 함수 간략한 설명 : 이미지 crop
	 @remark
	 - 함수의 상세 설명 : 이미지 crop
	 @param sourceFile 원본 이미지
	 @param resultFile 결과 이미지
	 @param x crop 시작 X 좌표
	 @param y crop 시작 Y 좌표
	 @param width 넓이(단위: pixel)
	 @param height 높이(단위: pixel)
	 @throws IOException 
	*/
	public void crop(File sourceFile, File resultFile, int x, int y, int width, int height) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(sourceFile);
		BufferedImage doneImage = Scalr.crop(bufferedImage, x, y, width, height, Scalr.OP_ANTIALIAS);
	
		writeJpegImage(doneImage, resultFile, 1);
	}

	/**
	 @fn cropCenter
	 @brief 함수 간략한 설명 : 이미지 중앙을 중심으로 이미지 crop
	 @remark
	 - 함수의 상세 설명 : 이미지 중앙을 중심으로 이미지 crop
	 @param sourceFile
	 @param resultFile
	 @param width
	 @param height
	 @throws IOException 
	*/
	public void cropCenter(File sourceFile, File resultFile, int width, int height) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(sourceFile);

		int count = 0;
		if(bufferedImage.getWidth() > width) {
			count++;
		}
		if(bufferedImage.getHeight() > height) {
			count++;
		}

		if(count > 0) {
			int x = (bufferedImage.getWidth()-width)/2;
			int y = (bufferedImage.getHeight()-height)/2;

			BufferedImage doneImage = Scalr.crop(bufferedImage, x, y, width, height, Scalr.OP_ANTIALIAS);

			writeJpegImage(doneImage, resultFile, 1);
		}
	}
	public void cropCenter(File sourceFile, File resultFile, int width, int height, int mode) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(sourceFile);

		int count = 0;
		if(bufferedImage.getWidth() > width) {
			count++;
		}
		if(bufferedImage.getHeight() > height) {
			count++;
		}

		if(count > 0) {
			int x = (bufferedImage.getWidth()-width)/2;
			int y = (bufferedImage.getHeight()-height)/2;

			BufferedImage doneImage = Scalr.crop(bufferedImage, x, y, width, height, Scalr.OP_ANTIALIAS);

			writeJpegImage(doneImage, resultFile, 1, mode);
		}
	}
}
