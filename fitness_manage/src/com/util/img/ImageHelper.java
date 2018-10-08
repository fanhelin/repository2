package com.util.img;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * 图片工具类 ,原程序具备按比例压缩，和按指定高度压缩功能。
 * @modify by fhr <add ICompress Interface>
 *
 */
public class ImageHelper {

	private static ImageHelper imageHelper = null;

	public static ImageHelper getImageHelper() {
		if (imageHelper == null) {
			imageHelper = new ImageHelper();
		}
		return imageHelper;
	}

	/***
	 *  * 按指定的比例缩放图片  *  * @param sourceImagePath  *   源地址  * @param
	 *  destinationPath  *   改变大小后图片的地址  * @param scale  *   缩放比例，如1.2  
	 *   @param iCompress 压缩接口（如压缩条件等）add by fhr
	 */
	public static boolean scaleImage(String sourceImagePath,
			String destinationPath, double scale, String format,ICompress iCompress) {
        String[] pathArr = sourceImagePath.split("\\.") ;
        String exe = pathArr[pathArr.length -1] ;
        
		File file = new File(sourceImagePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
			if(iCompress != null && !iCompress.needCompress(bufferedImage,exe)){
				return true;
			}
			
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			
			if(iCompress != null ){
				scale = iCompress.getScale(bufferedImage) ;
			}

			width = parseDoubleToInt(width * scale);
			height = parseDoubleToInt(height * scale);

			Image image = bufferedImage.getScaledInstance(width, height,Image.SCALE_SMOOTH);
			BufferedImage outputImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();

			ImageIO.write(outputImage, format, new File(destinationPath));
			return true ;
		} catch (IOException e) {
			System.out.println("scaleImage方法压缩图片时出错了");
		
			return false ;
		}

	}

	/***
	 *  * 将图片缩放到指定的高度或者宽度  * @param sourceImagePath 图片源地址  * @param
	 * destinationPath 压缩完图片的地址  * @param width 缩放后的宽度  * @param height 缩放后的高度
	 *  * @param auto 是否自动保持图片的原高宽比例  * @param format 图图片格式 例如 jpg  
	 */
	public static boolean scaleImageWithParams(String sourceImagePath,
			String destinationPath, int width, int height, boolean auto,
			String format,ICompress iCompress) {
		try {
			 String[] pathArr = sourceImagePath.split("\\.") ;
		     String exe = pathArr[pathArr.length -1] ;
			File file = new File(sourceImagePath);
			BufferedImage bufferedImage = null;
			bufferedImage = ImageIO.read(file);
			if(iCompress != null && !iCompress.needCompress(bufferedImage,exe)){
				return true;
			}
			if (auto) {
				ArrayList<Integer> paramsArrayList = getAutoWidthAndHeight(
						bufferedImage, width, height);
				width = paramsArrayList.get(0);
				height = paramsArrayList.get(1);
				System.out.println("自动调整比例，width=" + width + " height="+ height);
			}

			Image image = bufferedImage.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			ImageIO.write(outputImage, format, new File(destinationPath));
			return true ;
		} catch (Exception e) {
			System.out.println("scaleImageWithParams方法压缩图片时出错了");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 *  * 将double类型的数据转换为int，四舍五入原则  *  * @param sourceDouble  * @return  
	 */
	private static int parseDoubleToInt(double sourceDouble) {
		int result = 0;
		result = (int) sourceDouble;
		return result;
	}

	/***
	 *  *  * @param bufferedImage 要缩放的图片对象  * @param width_scale 要缩放到的宽度  * @param
	 * height_scale 要缩放到的高度  * @return 一个集合，第一个元素为宽度，第二个元素为高度  
	 */
	private static ArrayList<Integer> getAutoWidthAndHeight(
			BufferedImage bufferedImage, int width_scale, int height_scale) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		double scale_w = getDot2Decimal(width_scale, width);

		System.out.println("getAutoWidthAndHeight width=" + width + "scale_w="+ scale_w);
		double scale_h = getDot2Decimal(height_scale, height);
		if (scale_w < scale_h) {
			arrayList.add(parseDoubleToInt(scale_w * width));
			arrayList.add(parseDoubleToInt(scale_w * height));
		} else {
			arrayList.add(parseDoubleToInt(scale_h * width));
			arrayList.add(parseDoubleToInt(scale_h * height));
		}
		return arrayList;

	}

	/***
	 *  * 返回两个数a/b的小数点后三位的表示  * @param a  * @param b  * @return  
	 */
	public static double getDot2Decimal(int a, int b) {

		BigDecimal bigDecimal_1 = new BigDecimal(a);
		BigDecimal bigDecimal_2 = new BigDecimal(b);
		BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2,
				new MathContext(4));
		Double double1 = new Double(bigDecimal_result.toString());
		System.out.println("相除后的double为：" + double1);
		return double1;
	}

	
	public static void main(String[] args) {
		ImageHelper imageHelper = ImageHelper.getImageHelper() ;
		
		imageHelper.scaleImage("C:\\Users\\HP\\Pictures\\Saved Pictures\\lp1.JPG", "C:\\Users\\HP\\Pictures\\Saved Pictures\\lp12.jpg", 0.2, "jpg" ,new ICompress() {
			
			@Override
			public boolean needCompress(BufferedImage bufferedImage,String exe) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public double getScale(BufferedImage bufferedImage) {
				// TODO Auto-generated method stub
				return 0.5;
			}
		}) ;
		imageHelper.scaleImage("C:\\Users\\HP\\Pictures\\Saved Pictures\\chehuo.gif", "C:\\Users\\HP\\Pictures\\Saved Pictures\\chehuo23.gif", 1, "gif",null) ;
		imageHelper.scaleImage("C:\\Users\\HP\\Pictures\\Saved Pictures\\QQ图片20170525161502.gif", "C:\\Users\\HP\\Pictures\\Saved Pictures\\QQ图片20170525161502.gif", 1, "gif",null) ;
	}

}
