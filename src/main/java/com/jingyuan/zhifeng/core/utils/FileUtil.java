package com.jingyuan.zhifeng.core.utils;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件绝对路径
	 */
	public static void delFile(String filePath) {
		File myDelFile = new File(filePath);
		if (myDelFile.exists()) {
			myDelFile.delete();
		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static long getFileSize(File file) throws IOException {
		long s = 0;
		if (file.exists()) {
			return file.length();
		}
		return s;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param files
	 * @return
	 */
	public static String formateFileSize(long files) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (files < 1024) {
			fileSizeString = df.format((double) files) + "B";
		} else if (files < 1048576) {
			fileSizeString = df.format((double) files / 1024) + "K";
		} else if (files < 1073741824) {
			fileSizeString = df.format((double) files / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) files / 1073741824) + "G";
		}
		return fileSizeString;
	}


	public static void copy(File src, File dest) throws IOException {
		if (!dest.exists())
			dest.createNewFile();
		transfer(new FileInputStream(src), new FileOutputStream(dest));
	}

	public static void transfer(FileInputStream src, FileOutputStream dest) throws IOException {
		FileChannel source = null;
		FileChannel target = null;
		try {
			source = src.getChannel();
			target = dest.getChannel();
			long position = 0, size = source.size();
			while ((position += source.transferTo(position, size - position, target)) < size)
				;
		} finally {
			if (src != null) {
				try {
					source.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dest != null) {
				try {
					target.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public static void copy(InputStream _in, OutputStream _out) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(_in);
			out = new BufferedOutputStream(_out);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b, 0, b.length)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
		} catch (Exception e) {
		} finally {
			close(in, out);
		}
	}

	/**
	 * add by panmg copy一个文件夹下面的文件到另一个文件夹
	 * 
	 * @param source
	 *            必须是文件夹
	 * @param target
	 *            必须是文件夹
	 * @throws IOException
	 */
	public static void copyFolder(String source, String target) throws IOException {
		new File(target).mkdirs(); // 目标文件夹
		File fsource = new File(source);
		String[] fNames = fsource.list();
		for (String name : fNames) {
			File temp = new File(source + "/" + name);
			if (temp.isFile()) {
				FileInputStream in = null;
				FileOutputStream out = null;
				try {
					in = new FileInputStream(temp);
					out = new FileOutputStream(target + "/" + name);
					byte[] b = new byte[10240];
					int len;
					while ((len = in.read(b)) != -1) {
						out.write(b, 0, len);
					}
				} finally {
					if (out != null)
						out.close();
					if (in != null)
						in.close();
				}
			} else {
				copyFolder(source + "/" + name, target + "/" + name);
			}

		}
	}

	/**
	 * add by panmg 删除某个文件夹中的文件
	 * 
	 * @param ObjectPath
	 */
	public static void deleteDirectory(String ObjectPath) {
		File file = new File(ObjectPath);
		if(!file.exists()) return;
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				f.delete();
			} else {
				/* 先删除文件夹的内文件, 在删文件夹 */
				deleteDirectory(ObjectPath + "/" + f.getName());
				f.delete();
			}
		}
	}


	public static void close(InputStream in, OutputStream out) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				in = null;
			}
		}
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				in = null;
			}
		}
	}
	


	/**
	 * add by panmg
	 * 
	 * @param src
	 * @param desc
	 * @param width
	 * @param height
	 * @param proportion
	 * @throws IOException
	 */
	public static void compressPic(InputStream in, OutputStream out, int width, int height, boolean proportion) throws IOException {
		// 获得源文件
		java.awt.Image img = ImageIO.read(in);
		if(img.getWidth(null)==width && img.getHeight(null)==height)  return;
		int newWidth, newHeight;
		// 判断是否是等比缩放
		if (proportion == true) {
			// 为等比缩放计算输出的图片宽度及高度
			double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
			double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
			// 根据缩放比率大的进行缩放控制
			double rate = rate1 > rate2 ? rate1 : rate2;
			newWidth = (int) ((img.getWidth(null)) / rate);
			newHeight = (int) ((img.getHeight(null)) / rate);
		} else {
			newWidth = width; // 输出的图片宽度
			newHeight = height; // 输出的图片高度
		}

		BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		/*
		 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		 */
		tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		// JPEGImageEncoder可适用于其他图片类型的转换
		
		out.close();
	}
	
	/**
     * 将上传的文件进行重命名 
     * @param name
     * @return
     */
    public static String rename(String name) {  
  
        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));  
        Long random = (long) (Math.random() * now);  
        String fileName = now + "" + random;  
  
        if (name.indexOf(".") != -1) {  
            fileName += name.substring(name.lastIndexOf("."));  
        }  
  
        return fileName;  
    }  
	
	//单文件上传
	public static String upload(MultipartFile myfile, HttpServletRequest request, Integer type){
		
		//上传文件的路径
		String path = null;
		//存入数据库文件的路径
		String modelpath = null;
		//0表示为上传用户头像
		if(type==0){
//			path = request.getSession().getServletContext().getRealPath("static/images/user_image");
			path = GlobalStatic.uploadpath + "user_image";
			modelpath = "user_image/";
		}
		logger.info("path: {}",path);
		//获取上传文件原名
		String originalFilename = null;
		originalFilename = myfile.getOriginalFilename();
		//上传文件重命名再存储
		String storeName = rename(originalFilename);
		File targetFile = new File(path, storeName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		try{
			myfile.transferTo(targetFile);
		}catch(IOException e){
			System.out.println("文件上传失败");
			e.printStackTrace();
		}
		modelpath += storeName;//存入数据库文件后面拼接文件名
		logger.info("modelpath: {}",modelpath);
		return modelpath;
	}
	/**
	 * 
	 * 单文件上传，同时传入路径参数
	 * @param myfile
	 * @param request
	 * @param type
	 * @return
	 */
	public static String upload(MultipartFile myfile, HttpServletRequest request, String outputPath){
		
		//上传文件的路径
		String path = null;
		//存入数据库文件的路径
		String modelpath = null;
//			path = request.getSession().getServletContext().getRealPath("static/images/user_image");
		path = GlobalStatic.uploadpath + outputPath;
		modelpath = "/"+outputPath+"/";
		logger.info("path: {}",path);
		//获取上传文件原名
		String originalFilename = null;
		originalFilename = myfile.getOriginalFilename();
		//上传文件重命名再存储
		String storeName = rename(originalFilename);
		File targetFile = new File(path, storeName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		try{
			myfile.transferTo(targetFile);
		}catch(IOException e){
			System.out.println("文件上传失败");
			e.printStackTrace();
		}
		modelpath += storeName;//存入数据库文件后面拼接文件名
		logger.info("modelpath: {}",modelpath);
		return modelpath;
	}
	//多文件上传
	public static String MultiUpload(MultipartFile[] file, HttpServletRequest request, Integer type){
		
		//上传文件的路径
		String path = null;
		//服务器路径
		String serverPath = null;
		//存入数据库文件的路径
		String modelPath = "";
		//临时路径
		String tempPath = null;
		//1表示为普通用户上传
		if(type==1){
			
			path = GlobalStatic.uploadpath + "images/person";
			File fileP = new File(path);
			if(! fileP.exists())
			{
				fileP.mkdir();
			}
			serverPath = "/images/person/";
		}
		//2表示为中介机构上传
		if(type==2){
			path = GlobalStatic.uploadpath + "images/agency";
			File fileP = new File(path);
			if(! fileP.exists()){
				fileP.mkdir();
			}
			serverPath = "/images/agency/";
		}
		System.out.println("path: "+path);
		System.out.println("文件长度："+file.length);
		for (int i = 0; i < file.length; i++) {
			
			//获取上传文件原名
			String originalFilename = file[i].getOriginalFilename();
			//上传文件重命名再存储
			String storeName = rename(originalFilename);
			File targetFile = new File(path, storeName);
			try{
				file[i].transferTo(targetFile);
			}catch(IOException e){
				System.out.println("文件上传失败");
				e.printStackTrace();
			}
			tempPath = serverPath + storeName +",";//存入数据库文件后面拼接文件名,路径之间以逗号隔开
			modelPath +=tempPath;
		}
		System.out.println("modelPath: "+modelPath);
		return modelPath;
	}
	/**
	 * 多文件上传，同时传入路径参数
	 * @param file
	 * @param request
	 * @param type
	 * @return
	 */
	public static String MultiUpload(MultipartFile[] file, HttpServletRequest request, String outputPath){
		
		//上传文件的路径
		String path = null;
		//服务器路径
		String serverPath = null;
		//存入数据库文件的路径
		String modelPath = "";
		//临时路径
		String tempPath = null;
		path = GlobalStatic.uploadpath + outputPath;
		File fileP = new File(path);
		if(! fileP.exists()){
			fileP.mkdir();
		}
		serverPath = "/"+outputPath+"/";
		System.out.println("path: "+path);
		System.out.println("文件长度："+file.length);
		for (int i = 0; i < file.length; i++) {
			
			//获取上传文件原名
			String originalFilename = file[i].getOriginalFilename();
			//上传文件重命名再存储
			String storeName = rename(originalFilename);
			File targetFile = new File(path, storeName);
			try{
				file[i].transferTo(targetFile);
			}catch(IOException e){
				System.out.println("文件上传失败");
				e.printStackTrace();
			}
			tempPath = serverPath + storeName +",";//存入数据库文件后面拼接文件名,路径之间以逗号隔开
			modelPath +=tempPath;
		}
		System.out.println("modelPath: "+modelPath);
		return modelPath;
	}
	
//	/**
//	 * 文件导出 add by panmg
//	 * @param list
//	 * @param sheetName
//	 * @param header
//	 * @return
//	 */
//	public static HSSFWorkbook createExcel(List<Map<String, Object>> list, String sheetName,String... header){
//		
//		HSSFWorkbook excel = new HSSFWorkbook();
//		HSSFSheet sheet = excel.createSheet(sheetName);
//		int rowIndex = 0; // 记录当前列
//		/* 解析标题  begin*/
//		if(header.length>0){ 
//			HSSFRow row = sheet.createRow(rowIndex);
//			for(int i=0;i<header.length;i++){
//				HSSFCell cell = row.createCell(i);
//				cell.setCellValue(new HSSFRichTextString(header[i]));
//			}
//			rowIndex++;
//		}
//		/* 解析标题  end*/
//		
//		for(int i=0; i<list.size(); i++){
//			HSSFRow row = sheet.createRow(rowIndex);
//			Map<String, Object> map = list.get(i);
//			/* 解析列名 begin */
//			if(i==0){  
//				HSSFCellStyle style = excel.createCellStyle();
//				setCellStyle(style);
//				int j=0;
//				for(String key : map.keySet()){
//					HSSFCell cell = row.createCell(j);
//					cell.setCellStyle(style);
//					cell.setCellValue(key);
//					j++;
//				}
//				rowIndex++; //跳过column 那列
//			}
//			/* 解析列名 end */
//			
//			
//			/* 解析列数据 begin*/
//			row = sheet.createRow(rowIndex);
//			int j = 0;
//			for(String key : map.keySet()){
//				sheet.setColumnWidth(j, 2500); //设置固定宽度
//				//sheet.autoSizeColumn(j); //自适应 单元格宽度
//				HSSFCell cell = row.createCell(j);
//				Object value = map.get(key);
//				cell.setCellValue(String.valueOf(value));
//				j++;
//			}
//			rowIndex++;
//			/* 解析列数据 end*/
//		}
//		
//		return excel;
//	}
//	
//	public static void setCellStyle(HSSFCellStyle cellStyle){
//		cellStyle.setBorderTop((short) 1);
//		cellStyle.setBorderLeft((short) 1);
//		cellStyle.setBorderRight((short) 1);
//		cellStyle.setBorderBottom((short) 1);
//		cellStyle.setAlignment((short) 2);
//		cellStyle.setVerticalAlignment((short) 1);
//		cellStyle.setFillForegroundColor((short) 22);
//		cellStyle.setFillPattern((short) 1);
//		cellStyle.setWrapText(true);
//	}
	
}
