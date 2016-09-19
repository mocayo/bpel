package com.lm.test;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class Test {
	
	private static Logger logger = Logger.getLogger(Test.class);
	private static Logger console = Logger.getLogger("consoleLog");
	
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, ParseException {
		/*Set<Define> in = new HashSet<Define>();
		Set<Define> kill = new HashSet<Define>();
		Define d1 = new Define("PO","receive_5");
		Define d2 = new Define("PO","receive_6");
		in.add(d1);
		kill.add(d1);
		kill.add(d2);
		System.out.println(in);
		System.out.println(in.removeAll(kill));
		System.out.println(in);*/
		//LinkedHashMap
		/*Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("s1", 1);
		map.put("s2", 2);
		map.put("s3", 3);
		map.put("s4", 4);
		for(Entry<String,Integer> entry:map.entrySet())
			System.out.println(entry);*/
		
//		int i = 0;
//		while(i<80){
//			getScreen("screen" + i);
//			i++;
//		}
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		try {
			Date date1 = sdf.parse("2012-05-31");
			Date date2 = sdf.parse("2016-04-08");
			start.setTime(date1);
			end.setTime(date2);
			while(start.before(end)){
				start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
				System.out.println(sdf.format(start.getTime()));
			}
		} catch (ParseException e1) {}*/
		
		/*int total = 11;
		int pageindex = 1;int pagesize = 10;
		int pagemax = (total - 1) /10 + 1;
		for(int i=0; i<pagemax; i++){
			for(int j=0; j<pagesize && j<total; j++){
				int index = i*pagesize + j;
				if(index<total)
					System.out.println("第" + (i+1) +  "页:" + index);
			}
		}*/
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String day = "2016-04-16";
//		System.out.println("/*test*/");
//		Calendar next = Calendar.getInstance();
//		next.setTime(sdf.parse(day));
//		next.add(Calendar.DAY_OF_MONTH, next.get(Calendar.DAY_OF_MONTH) + 1);
//		System.out.println(sdf.format(next.getTime()));
//		System.out.println("/*test*/");
		/*// 记录debug级别的信息  
        logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message.");  
		System.out.println("aaa");*/
		//console.debug("syyyyyy");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar start = Calendar.getInstance();
//		Calendar end = Calendar.getInstance();
//		try {
//			Date date1 = sdf.parse("2016-06-03");
//			Date date2 = sdf.parse("2016-08-01");
//			start.setTime(date1);
//			end.setTime(date2);
//			while(start.before(end)){
//				start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
//				System.out.println(sdf.format(start.getTime()));
//			}
//		} catch (ParseException e1) {}
		
		/*System.setOut(new PrintStream("D://threshold-logs//stdout-log.log"));
		System.out.println(addDays("2016-07-31",1));
		System.out.println(addDays("2016-08-01",1));
		System.setOut(System.out);
		System.out.println(addDays("2016-08-02",1));
		System.setOut(new PrintStream(new File("D://threshold-logs//stdout-log.log")));
		System.out.println(addDays("2016-08-03",1));*/
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar now = Calendar.getInstance();
//		now.setTime(sdf.parse("2016-08-01 23:23:21"));
//		System.out.println(now.get(Calendar.HOUR_OF_DAY) + "时" + now.get(Calendar.MINUTE) + "分" + now.get(Calendar.SECOND) + "秒");
		String s1 = "2016-09-04";
		String s2 = "2016-09-05";
		System.out.println(s1.compareTo(s2));
	}
	
	public static void getScreen(String fileName){
		try {  
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();  
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0,  
                    0,(int)dimension.getWidth(),(int)dimension.getHeight()));  
            File file = new File("D:/eeeeeee/" + fileName + ".jpg");  
            ImageIO.write(screenshot, "jpg", file);  
        } catch (HeadlessException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (AWTException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}
	
	public static String addDays(String day,int n){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(day));
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
			return sdf.format(cal.getTime());
		} catch (ParseException e) {
			System.out.println("格式转换异常");
			return null;
		}
	}
	
}
