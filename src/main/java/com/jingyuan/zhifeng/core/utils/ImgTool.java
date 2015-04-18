package com.jingyuan.zhifeng.core.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImgTool {
	private BufferedImage subImg;

	/**
	 * 截图
	 * @param srcPath
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */

	public void cut(String srcPath, int startX, int startY, int width, int height) {
		try {
			BufferedImage bufImg = ImageIO.read(new File(srcPath));
			subImg = bufImg.getSubimage(startX, startY, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 保存截图。
	 * @param bufImg
	 * @param imgType
	 * @param tarPath
	 */
	public void save(String imgType, String imgName, String tarPath, int width,
			int height) {
		try {
			/** 压缩图片为指定尺寸 */
			if (subImg.getWidth() != width || subImg.getHeight() != height) {
				BufferedImage tempImg = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				tempImg.getGraphics().drawImage(
						subImg.getScaledInstance(width, height,
								Image.SCALE_SMOOTH), 0, 0, null);
				ImageIO.write(tempImg, imgType, new File(tarPath + "/"
						+ imgName));
			} else {
				ImageIO.write(subImg, imgType,
						new File(tarPath + "/" + imgName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		ImgTool imgTool  = new ImgTool();
		imgTool.cut("C:\\Users\\cz\\Desktop\\sharework photo\\aa.jpg", 0, 0, 100, 100);
		imgTool.save("jpg", "bb.jpg", "C:\\zhifeng", 100, 100);
		/**
		 * HttpSession session = req.getSession();
		User user = RequestContext.get().getUser();	
		
		ImgTool imgTool = new ImgTool();
		int startX = Integer.parseInt(req.getParameter("startX"));
		int startY = Integer.parseInt(req.getParameter("startY"));
		int width = Integer.parseInt(req.getParameter("width"));
		int height = Integer.parseInt(req.getParameter("height"));
		int pwidth = Integer.parseInt(req.getParameter("w"));
		int pheight = Integer.parseInt(req.getParameter("h"));
		
		if (startX == 0 && startY == 0 && width == 0 && height ==0) {
			String img = req.getParameter("img1src");	
			if (img.isEmpty()) {
				img = session.getServletContext().getRealPath("/")+"img\\default.png";
				user.setImg(img);
				userService.editUser(user);
			}else{
				user.setImg(img);
				userService.editUser(user);
			}
			
		}else {
			String path = Constants.apps.get("uploadpath") + "/head";
			String img = req.getParameter("img1src");
			if (user.getImg()==null || user.getImg().isEmpty()) {
				if (img.isEmpty()) {
					img = session.getServletContext().getRealPath("/")+"img\\default.png";
				}			
			}
			
			String type = img.substring(img.lastIndexOf(".") +1);
			File picture = new File(img);  
	        BufferedImage sourceImg =ImageIO.read(new FileInputStream(picture));   

	        startX = startX * sourceImg.getWidth() / pwidth;
	        startY = startY * sourceImg.getHeight() / pheight;
	        width = width * sourceImg.getWidth() / pwidth;
	        height = height * sourceImg.getHeight() / pheight;
			
			imgTool.cut(img, startX, startY, width, height);
			imgTool.save(type, img.substring(img.lastIndexOf("/")+1), path, width, height);
			
			user.setImg(img);
			userService.editUser(user);
		}
		 */
		
	}
	
	

}
