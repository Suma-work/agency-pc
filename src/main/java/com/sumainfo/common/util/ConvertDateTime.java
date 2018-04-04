package com.sumainfo.common.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 日期时间工具类
 * 
 * @version 1.0 2016-05-13
 * @since 1.0
 */
@Slf4j
public class ConvertDateTime{
	// 获取打印权限
	private static Logger log = LoggerFactory.getLogger(ConvertDateTime.class);
	
	final static int oneDayContHor = 24;
	// 预定义格式字符串
	public static final String FORMAT_ONLY_DATE_EN = "yyyy-MM-dd";
	public static final String FORMAT_ONLY_TIME_EN = "HH:mm:ss";
	public static final String FORMAT_MONTH_DAY_EN = "MM-dd";
	public static final String FORMAT_MONTH_DAY_EN2 = "MM/dd";
	public static final String FORMAT_MONTH_DAY_EN3 = "yyyy/MM/dd";
	public static final String FORMAT_MONTH_DAY_CHS = "MM月dd日";
	public static final String FORMAT_YEAR_MONTH_CHS = "yy年MM月";
	public static final String FORMAT_ONLY_DATE_CHS = "yyyy年MM月dd日";
	public static final String FORMAT_FULL_DATETIME_EN = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_FULL_DATETIME_EN2 = "yyyy.MM.dd HH:mm";
	public static final String FORMAT_FULL_DATETIME_EN3 = "yyyy/MM/dd HH:mm:ss";
	public static final String FORMAT_YMDHM_DATETIME_EN = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_FULL_DATETIME_LONG_EN = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FORMAT_FULL_DATETIME_CHS = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String FORMAT_HOUR_MIN_SEC_EN = "HH:mm:ss";
	public static final String FORMAT_MDHM_DATETIME_EN = "MM-dd HH:mm";
	public static final String FORMAT_HOUR_MIN_EN = "HH:mm";
	public static final String FORMAT_HOUR_EN = "HH";
	public static final String FORMAT_MONTH = "MM";
	public static final String FORMAT_YEAR = "yyyy";
	
	
	public static final String SERIAL_LONG = "yyyyMMddHHmmssSSS";
	public static final String SERIAL_ONLY_YEAR = "yyyy";
	public static final String SERIAL_ONLY_DATE = "yyyyMMdd";
	public static final String SERIAL_ONLY_MONTH = "yyyyMM";
	public static final String SERIAL_FULL_DATETIME = "yyyyMMddHHmmss";
	public static final String SERIAL_YMDHM_DATETIME = "yyyyMMddHHmm";
	public static final String SERIAL_MONTH_DAY = "MMdd";
	public static final String SERIAL_HOUR_MIN_SEC = "HHmmss";

	public static final String SERIAL_NOW = "yyyyMMddHHmmss";
	// 预定义控制参数
	public static final int FIRST_DAY_OF_WEEK = 1; // 星期的第一天
	public static final int FIRST_DAY_OF_MONTH = 2; // 月份的第一天
	public static final int FIRST_DAY_OF_QUARTER = 3; // 季度的第一天
	public static final int FIRST_DAY_OF_YEAR = 4; // 年份的第一天
	public static final int LAST_DAY_OF_WEEK = 5; // 星期的最后一天
	public static final int LAST_DAY_OF_MONTH = 6; // 月份的最后一天
	public static final int LAST_DAY_OF_QUARTER = 7; // 季度的最后一天
	public static final int LAST_DAY_OF_YEAR = 8; // 年份的最后一天
	public static final int DAY_OF_NEXT_WEEK = 9; // 下周今日
	public static final int DAY_OF_NEXT_MONTH = 10; // 下月今日
	public static final int DAY_OF_NEXT_YEAR = 11; // 明年今日

	public static final int YEAR = 21; // 年
	public static final int MONTH = 22; // 月
	public static final int QUARTER = 23; // 季度
	public static final int DAY_OF_YEAE = 24; // 年中天数
	public static final int DAY_OF_WEEK_CHS = 25; // 周中天数 周一为第一天
	public static final int DAY_OF_WEEK_EN = 26; // 周中天数 西方习惯 周日为第一天
	
	/**
	 *  时间格式转换   2017-08-08 转 2017年08月
	 * @return
	 */
	public static String getChianDate() {
		return "";
	}
	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String getCurrentTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("yyyy-MM-dd HH:mm:ss");
		return dateStr;
	}
	public static String getTodayTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("yyyy-MM-dd");
		return dateStr;
	}
	public static String getTodayTimeHours(){
		DateTime date = new DateTime();
		String dateStr = date.toString(FORMAT_HOUR_EN);
		return dateStr;
	}
	public static String getTomorrow(){
		Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        System.out.println(dateString);
		return dateString;
	}
	public static String getTodayTimeOnLunar(){
		DateTime date = new DateTime();
		String dateStr = date.toString(FORMAT_MONTH_DAY_EN3);
		return dateStr;
	}
	public static String getDDTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("dd");
		return dateStr;
	}
	
	public static String getDMTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("MM");
		return dateStr;
	}
	
	public static String getYearTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("yyyy");
		return dateStr;
	}
	
	public static String getDDHour(){
		DateTime date = new DateTime();
		String dateStr = date.toString("HH");
		return dateStr;
	}
	
	/**
	 * 获取当前时间
	 * yyyyMMddHHmmss
	 * @return 
	 */
	public static String getCurrentTimeNow(){
		DateTime date = new DateTime();
		String dateStr = date.toString(SERIAL_NOW);
		return dateStr;
	}
	public static String getCurrentTimeNow2(){
		DateTime date = new DateTime();
		String dateStr = date.toString(FORMAT_YMDHM_DATETIME_EN);
		return dateStr;
	}
	
	
	/**
	 * 时间格式转换（yyyy-MM-dd HH:mm:ss or yyyy-MM-dd）（String 转 String）
	 * @param {format1：传入的日期格式，format2：传出的日期格式，dateTimeStr：日期字符串}（日期格式从“预定义格式字符串”中取）
	 */
	public static String dateFormatStrToStr(String format1,String format2,String dateTimeStr)throws Exception {
		if (format1 == null || format2 == null || dateTimeStr == null)
			throw new Exception("日期格式化失败：参数为空");
		String timeStr = null;
		DateTimeFormatter df = DateTimeFormat.forPattern(format1);
		DateTime dateFormatTime = DateTime.parse( dateTimeStr, df);
		timeStr = dateFormatTime.toString(format2);
		return  timeStr;
	}
	
	/** 
	 * 根据指定模式格式化日期时间（yyyy-MM-dd HH:mm:ss）（DateTime 转 String）
	 * @param format
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String dateFormatTimeToStr(String format,DateTime date) throws Exception{
		String dateFormatted = null;
		if (date == null)
			throw new Exception("日期格式化失败：参数为空");
		DateTimeFormatter df = DateTimeFormat.forPattern(format);
		dateFormatted = date.toString(df);
		return dateFormatted;
	}
	
	/**
	 * 时间格式转化（yyyy-MM-dd HH:mm:ss）（DateTime 转 DateTime）
	 * @param dateTime
	 * @return dateTime
	 */
	public static DateTime dateFormatTimeToTime(String format, DateTime date)throws Exception {
		DateTime dateFormatted = null;
		if (format == null || date == null)
			throw new Exception("日期格式化失败：参数为空");
		DateTimeFormatter df = DateTimeFormat.forPattern(format);
		String startDateStr = date.toString(df);
		dateFormatted = DateTime.parse(startDateStr, df);
		return dateFormatted;
	} 
	
	/**s
	 * 时间格式转化（yyyy-MM-dd HH:mm:ss）（String 转 DateTime）
	 * @param String
	 * @return dateTime
	 */
	public static DateTime dateFormatStrToTime(String format, String dateTimeStr)throws Exception {
		DateTime dateFormatted = null;
		if (format == null || dateTimeStr == null)
			throw new Exception("日期格式化失败：参数为空");
		DateTimeFormatter df = DateTimeFormat.forPattern(format);
		dateFormatted = DateTime.parse(dateTimeStr, df);
		return dateFormatted;
	}
	
	/**
	 * 两个日期之间的相差(eDate - sDate)(type:1.年  2.月  3.日  4.时  5.分  6.秒  7.毫秒)
	 * @param sDate
	 * @param eDate
	 * @return 小时数
	 */
	public static int subDate(DateTime sDate, DateTime eDate,int type) throws Exception{
		if(sDate == null || eDate == null ){
			throw new Exception("日期格式化失败：参数为空");
		}
		int subRes = 0;
		switch(type){
		case 1:
			Period p1 = new Period(sDate, eDate, PeriodType.years());
			subRes = p1.getYears();
			break;
		case 2:
			Period p2 = new Period(sDate, eDate, PeriodType.months());
			subRes = p2.getMonths();
			break;
		case 3: 
			Period p3 = new Period(sDate, eDate, PeriodType.days());
			subRes = p3.getDays();
			break;		
		case 4:
			Period p4 = new Period(sDate, eDate, PeriodType.hours());
			subRes = p4.getHours();
			break;
		case 5:
			Period p5 = new Period(sDate, eDate, PeriodType.minutes());
			subRes = p5.getMinutes();
			break;
		case 6:
			Period p6 = new Period(sDate, eDate, PeriodType.seconds());
			subRes = p6.getSeconds();
			break;
		case 7:
			Period p7 = new Period(sDate, eDate, PeriodType.millis());
			subRes = p7.getMillis();
			break;
		};
		return subRes;
	}
	
	/**获取传入的日期星期几
	 * 
	 * @param dt
	 * @return{"星期一" or  "星期二" or  "星期三" or  "星期四" or  "星期五" or  "星期六" or "星期日"}
	 */
	public static String getWeekOfDate(DateTime dt)
	{
		String result = "";
		String[] weekDays = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日" };
		switch(dt.getDayOfWeek()){
		case DateTimeConstants.SUNDAY: 
			result = weekDays[DateTimeConstants.SUNDAY - 1];
			break;
		case DateTimeConstants.MONDAY: 
			result = weekDays[DateTimeConstants.MONDAY - 1];
			break;
		case DateTimeConstants.TUESDAY: 
			result = weekDays[DateTimeConstants.TUESDAY - 1];
			break;
		case DateTimeConstants.WEDNESDAY: 
			result = weekDays[DateTimeConstants.WEDNESDAY - 1];
			break;
		case DateTimeConstants.THURSDAY: 
			result = weekDays[DateTimeConstants.THURSDAY - 1];
			break;
		case DateTimeConstants.FRIDAY: 
			result = weekDays[DateTimeConstants.FRIDAY - 1];
			break;
		case DateTimeConstants.SATURDAY: 
			result = weekDays[DateTimeConstants.SATURDAY - 1];
			break;
		}
		return result;
	}
	
	/**获取传入的日期星期几
	 * 
	 * @param dt
	 * @return {1 or 2 or 3 or 4 or 5 or 6 or 7}
	 */
	public static String getWeekOfDateWithNo(DateTime dt)
	{
		
		return Integer.toString(dt.getDayOfWeek());
	}
	
	
	/**
	 * 获取当前日期所在的年份、月份、季度数等要素
	 */
	public static int getCurrentDateElement(int console) throws Exception{
		int result = -1;
		DateTime currTime = new DateTime();
		switch (console)
		{
		case YEAR:
			result = currTime.getYear();
			break; // 当前日期所在年份
		case MONTH:
			result = currTime.getMonthOfYear();
			break; // 当前日期所在月份
		case QUARTER:
			result = getCurrentDateQuarter();
			break; // 当前日期所在季度
		case DAY_OF_YEAE:
			result = currTime.getDayOfYear();
			break; // 当前日期所在年中为第N天
		case DAY_OF_WEEK_CHS:
			result = currTime.getDayOfWeek();
			break; // 当前日期所在周中为第N天 周日为第七天
		}

		if (result == -1)
			throw new Exception("getCurrentDateElement()执行失败，控制参数非法");

		return result;

	}
	
	/** 
	 * 当前日期所在季度
	 * @return
	 * @throws Exception
	 */
	private static int getCurrentDateQuarter() throws Exception{
		int quarter = -1;
		Calendar c = Calendar.getInstance();
	
		switch (c.get(Calendar.MONTH))
		{
		case 0:
		case 1:
		case 2:
			quarter = 1;
			break;
		case 3:
		case 4:
		case 5:
			quarter = 2;
			break;
		case 6:
		case 7:
		case 8:
			quarter = 3;
			break;
		case 9:
		case 10:
		case 11:
			quarter = 4;
			break;
		}
		if (quarter == -1)
			throw new Exception("getCurrentDateQuarter()执行失败，未能正确获取季度");
	
		return quarter;
	}
	
	/**
	 *  格式化日期为英文完整格式(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return 
	 * @throws Exception
	 */
	public static String dateTimeFormattedEN(DateTime date) throws Exception
	{
		return dateFormatTimeToStr(FORMAT_FULL_DATETIME_EN, date);
	}
	
	/**
	 *  格式化日期为yyyy-MM-dd的格式
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String dateTimeFormattedENDate(DateTime date){
		int yearStr = date.getYear();
		int monthStr = date.getMonthOfYear();
		int dayStr = date.getDayOfMonth();
		String dateTimeStr = yearStr + "-" + (monthStr < 10 ? "0" + monthStr : monthStr) + "-" + (dayStr < 10 ? "0" + dayStr : dayStr);
		return dateTimeStr;
	}

	/**
	 *  当天日期简单格式(yyyyMMddHHmmssSSS)
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentSingleDate() throws Exception{
		DateTime currTime = new DateTime();
		int yearStr = currTime.getYear();
		int monthStr = currTime.getMonthOfYear();
		int dayStr = currTime.getDayOfMonth();
		int hourStr = currTime.getHourOfDay();
		int minStr = currTime.getMinuteOfHour();
		int secStr = currTime.getSecondOfMinute();
		String dateTimeStr = yearStr + "" + (monthStr < 10 ? "0" + monthStr : monthStr) + "" + (dayStr < 10 ? "0" + dayStr : dayStr) + "" 
							+ (hourStr < 10 ? "0" + hourStr : hourStr) + "" + (minStr < 10 ? "0" + minStr : minStr ) + "" + (secStr < 10 ? "0" + secStr : secStr);
		return dateTimeStr;
	}	
		
	/**
	 * 获取一段日期对应的（日期+星期几）list
	 * @param startTime （开始时间）
	 * @param days（持续时间）
	 * @return [{curDateStr:xxx,curdayofWeek:xxx},{curDateStr:xxx,curdayofWeek:xxx},...]
	 * @throws Exception
	 */
	public  static List<Map<String, Object>> getDateAndWeekDayData(DateTime startTime, int days) throws Exception{
		List<Map<String, Object>> paramsList = new ArrayList<>();
		for(int i = 0; i < days; i++ ){
			Map<String, Object> params = new HashMap<>();
			if(i != 0){
				startTime = startTime.plusDays(1);
			}
			DateTime curDateTime = startTime;
			String curDateStr = ConvertDateTime.dateFormatTimeToStr(ConvertDateTime.FORMAT_FULL_DATETIME_EN, curDateTime);
			String curdayofWeekStr = ConvertDateTime.getWeekOfDateWithNo(curDateTime);
			params.put("curDateStr", curDateStr);
			params.put("curdayofWeek", curdayofWeekStr);
			paramsList.add(params);
		}
		return paramsList;
	}	
	
	/**
	 * 获取当前日期（DateTime类型）
	 */
	public static DateTime getCurTime(){
		return new DateTime();
	}
	
	/**
	 * 获取当前日期（String）[yyyy-MM-dd]
	 */
	public static String getCurrentDate(){
		return new DateTime().toString(FORMAT_ONLY_DATE_EN);
	}
	/**
	 * 获取当前日期（String）[yyyy-MM-dd]
	 */
	public static String getCurrentDateTime(){
		return new DateTime().toString(FORMAT_ONLY_TIME_EN);
	}
	/**
	 * 获取当前日期（DateTime）[yyyy-MM-dd]
	 */
	public static DateTime getCurDate(){
		DateTime dateFormatted = null;
		DateTimeFormatter df = DateTimeFormat.forPattern(FORMAT_ONLY_DATE_EN);
		String startDateStr = new DateTime().toString(df);
		dateFormatted = DateTime.parse(startDateStr, df);
		return dateFormatted;
	}
	
	/**
	 * 获取当前日期（String）
	 * @param {formatstr}时间格式
	 * @return string
	 */
	public static String getCurrentDateTime(String format){
		return new DateTime().toString(format);
	}
	
	/**
	 * 日期加减加数后，获得新的日期
	 * @param oldTime(传入的日期) addend(加数) type(类型： 1.秒   2.分   3.时   4.天  5.月  6.年)
	 * @param newTime(加后的日期)
	 */
	public static DateTime addDateTime(DateTime oldTime, int addend, int type)throws Exception {
		if(type > 6){
			throw new Exception("传入的类型不对");
		}
		DateTime newTime = null;
		switch(type){
			case 1:newTime = oldTime.plusSeconds(addend);break;
			case 2:newTime = oldTime.plusMinutes(addend);break;
			case 3:newTime = oldTime.plusHours(addend);break;
			case 4:newTime = oldTime.plusDays(addend);break;
			case 5:newTime = oldTime.plusMonths(addend);break;
			case 6:newTime = oldTime.plusYears(addend);break;
		}
		return newTime;
	}
	
	
	public static String getCurTime(DateTime dateTime)throws Exception {
	    String timeStr = null;
	    String dateTimeStr = dateFormatTimeToStr(FORMAT_FULL_DATETIME_EN, dateTime);
	    int subIndex = dateTimeStr.indexOf(" ");
	    timeStr = dateTimeStr.substring(subIndex + 1);
	    return timeStr;
	}
	
	/**
	 * 传入Second，然后返回“*时*分*秒”字符串。
	 * @param seconds
	 * @return str
	 */
	public static String convertSecondToStr(int seconds){
		int tmp = 0;
		String remainTmStr = "";
		if(seconds > 3600){
			tmp = seconds / 3600;
			remainTmStr += tmp + "时";
			seconds = seconds - tmp * 3600;
		}
		if(seconds > 60){
			tmp = seconds / 60;
			remainTmStr += tmp + "分";
			seconds = seconds - tmp * 60;
		}
		if(seconds < 60){
			remainTmStr += seconds + "秒";
		}
		return remainTmStr;
	}
	
	public static List<Map<String, Object>> gettodaymotch()throws Exception {
		System.out.println("getDDTime--->"+getDDTime());
		return getlistdatelaster(Integer.parseInt(getDDTime()));
	}
	/**
	 * 从今天往前倒退多少天  int就传入多少
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getlistdatelaster(int i) throws Exception{
		List<Map<String, Object>> dateList=new ArrayList<Map<String,Object>>();
		DateTime startDate = new DateTime();
		for (int j = 0; j < i; j++) {
			Map<String, Object> map=new HashMap<String, Object>();
			if (j==0) { 
				map.put("date", dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,startDate));
				dateList.add(map);
				continue;
			}
			DateTime newDate = ConvertDateTime.addDateTime(startDate, j*-1, 4);
			map.put("date", dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,newDate));
			dateList.add(map);
			
		}
		return dateList;
	}
	

	public static List<Map<String, Object>> getMonthsmotch()throws Exception {
		return getlistMonthslaster(Integer.parseInt(getDMTime()));
	}
	
	/**
	 * 当前属于几月，往后倒推，  int就传入多少
	 * @param i
	 * @return
	 * @throws Exception 
	 */
	public static List<Map<String, Object>> getlistMonthslaster(int i) throws Exception{
		List<Map<String, Object>> dateList=new ArrayList<Map<String,Object>>();
		DateTime startDate = new DateTime();
		for (int j = 0; j < i; j++) {
			Map<String, Object> map=new HashMap<String, Object>();
			if (j==0) { 
				map.put("date", dateFormatTimeToStr(FORMAT_MONTH,startDate));
				dateList.add(map);
				continue;
			}
			DateTime newDate = ConvertDateTime.addDateTime(startDate, j*-1, 5);
			map.put("date", dateFormatTimeToStr(FORMAT_MONTH,newDate));
			dateList.add(map);
			
		}
		return dateList;
	}
	
    
    public static List<Map<String, Object>> getmonthDays(int month)throws Exception {
		System.out.println("getYearTime--->"+getYearTime());
		return getMonthFullDay(Integer.parseInt(getYearTime()),month);
	}
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * 手动输入月份获取当月的每一天的日期
     * @param year,month
     * @return
     */
    public static List<Map<String, Object>> getMonthFullDay(int year , int month){
        List<Map<String, Object>> fullDayList = new ArrayList<Map<String, Object>>();
        // 当前月份
        int nowMonth = Integer.parseInt(ConvertDateTime.getDMTime());
        if(month > 0 && month != nowMonth){	// 接收到的月份不属于当前月份则计算整月的每一天日期
        	//if(day <= 0 ) day = 1;
        	Calendar cal = Calendar.getInstance(Locale.CHINA);// 获得当前日期对象
        	cal.clear();// 清除信息
        	cal.set(Calendar.YEAR, year);
        	cal.set(Calendar.MONTH, month - 1);// 1月从0开始
        	//cal.set(Calendar.DAY_OF_MONTH, day);// 设置为1号,当前日期既为本月第一天
        	int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        	for (int j = 0; j <= (count-1);) {
        		Map<String, Object> map=new HashMap<String, Object>();
        		if(sdf.format(cal.getTime()).equals(getLastDay(year, month)))
        			break;
        		cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : +1);
        		map.put("mday", sdf.format(cal.getTime()));
        		j++;
        		fullDayList.add(map);
        	}
        }else{	// 接收到的月份属于当前月份则计算当前日期到本月1日的日期
        	int nowDay = Integer.parseInt(ConvertDateTime.getDDTime());
        	//if(day <= 0 ) day = 1;
        	Calendar cal = Calendar.getInstance(Locale.CHINA);// 获得当前日期对象
        	cal.clear();// 清除信息
        	cal.set(Calendar.YEAR, year);
        	cal.set(Calendar.MONTH, month - 1);// 1月从0开始
        	cal.set(Calendar.DAY_OF_MONTH, nowDay);// 设置为本月第一天（或最后一天）,当前日期既为本月第一天
        	//int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        	for (int j = 0; j <= (nowDay-1);) {
        		Map<String, Object> map=new HashMap<String, Object>();
        		if(sdf.format(cal.getTime()).equals(getLastDay(year, month)))
        			break;
        		cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : -1);
        		map.put("mday", sdf.format(cal.getTime()));
        		j++;
        		fullDayList.add(map);
        	}
        }
        return fullDayList;
    }
    
    public static String getLastDay(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return sdf.format(cal.getTime());
    }
    
    public static List<Map<String, Object>> getSumDaysDate(String StartDate,int svcday) throws Exception{
    	System.out.println("StartDate:"+StartDate+"---"+"svcday:"+svcday);
    	List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
    	for (int i = 0; i < svcday; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("date", dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,addDateTime(dateFormatStrToTime(FORMAT_ONLY_DATE_EN,StartDate),i,4)));
			resultList.add(map);
		}
    	return resultList;
	}
    
	public static int compareDate(String DATE1, String DATE2) throws Exception {
        DateFormat df = new SimpleDateFormat(FORMAT_ONLY_DATE_EN);
        Date dt1 = df.parse(DATE1);
        Date dt2 = df.parse(DATE2);
        System.out.println("比较参数："+DATE1+":"+DATE2);
        if (dt1.getTime() > dt2.getTime()) {
            System.out.println("dt1 在dt2前");
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            System.out.println("dt1在dt2后");
            return -1;
        } else {
            return 0;
        }
    }

	public static int compareDateTime(String DATE1, String DATE2) throws Exception {
        DateTimeFormatter df = DateTimeFormat.forPattern(FORMAT_ONLY_TIME_EN);
		DateTime dt1 = DateTime.parse( DATE1, df);
		DateTime dt2 = DateTime.parse( DATE2, df);
		System.out.println("小时比较参数："+dt1.getHourOfDay()+":"+dt2.getHourOfDay());
        if (dt1.getHourOfDay() > dt2.getHourOfDay()) {
        	System.out.println("DATE1 大");
            return 1;
        }else if (dt1.getHourOfDay() < dt2.getHourOfDay()) {
        	System.out.println("DATE2 大");
        	 return -1;
		}else {
			System.out.println("DATE1DATE2 小时数一样 大");
			System.out.println("分钟比较参数："+dt1.getMinuteOfHour()+":"+dt2.getMinuteOfHour());
			if (dt1.getMinuteOfHour() > dt2.getMinuteOfHour()) {
	        	System.out.println("DATE1 大");
	            return 1;
	        }else if (dt1.getMinuteOfHour() < dt2.getMinuteOfHour()) {
	        	System.out.println("DATE2 大");
	        	 return -1;
			}else {
				System.out.println("DATE1DATE2 小时数一样 大");
				System.out.println("秒钟比较参数："+dt1.getSecondOfMinute()+":"+dt2.getSecondOfMinute());
				if (dt1.getSecondOfMinute() > dt2.getSecondOfMinute()) {
		        	System.out.println("DATE1 大");
		            return 1;
		        }else if (dt1.getSecondOfMinute() < dt2.getSecondOfMinute()) {
		        	System.out.println("DATE2 大");
		        	 return -1;
				}else {
					System.out.println("DATE1DATE2 秒钟数一样 大");
					return 0;
				}
			}
		}
    }
	
	/**
	 * 比较当前的日期 如果当前日期大于传入参数返回正数，小于则返回负数
	 * 
	 * @param strDate
	 *            传入比较的日期
	 * @return 返回传入的日期和当前日期相差的毫秒数
	 */
	public static long compareCurrentDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_ONLY_DATE_EN);
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(strDate);
			d2 = sdf.parse(getCurrentDate());
		} catch (ParseException e) {
			log.error("Exception : " , e);
		}
		return d2.getTime() - d1.getTime();
	}

	/**
	 * 算出和当前日期格式的天数差
	 * 
	 * @param strDate
	 *            传入的比较日期
	 * @return
	 */
	public static long dayDifference(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMDHM_DATETIME_EN);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = 0;
		String currentDate = sdf.format(new Date());
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(strDate);
			d2 = sdf.parse(currentDate);
			diff = d2.getTime() - d1.getTime();
		} catch (ParseException e) {
			log.error("Exception : " , e);
		}
		long day = diff / nd; // 计算差多少天
		return day;
	}
	public static long dayDifferenceHours(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMDHM_DATETIME_EN);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = 0;
		String currentDate = sdf.format(new Date());
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(strDate);
			d2 = sdf.parse(currentDate);
			diff = d2.getTime() - d1.getTime();
		} catch (ParseException e) {
			log.error("Exception : " , e);
		}
		long day = (diff * 24) / nd ; // 计算差多少小时
		return day;
	}
	/**
	 * 算出和当前日期格式的小时差
	 * 
	 * @param strDate
	 *            传入的比较日期
	 * @return
	 */
	public static long hourDifference(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMDHM_DATETIME_EN);
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = 0;
		String currentDate = sdf.format(new Date());
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(strDate);
			d2 = sdf.parse(currentDate);
			diff = d1.getTime() - d2.getTime();
		} catch (ParseException e) {
			log.error("Exception : " , e);
		}
		long hour = diff / nh;// 计算差多少小时
		return hour;
	}

	/**
	 * 算出和当前日期格式的分钟差
	 * 
	 * @param strDate
	 *            传入的比较日期
	 * @return
	 */
	public static long minuteDifference(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMDHM_DATETIME_EN);
		long nm = 1000 * 60;// 一分钟的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = 0;
		String currentDate = sdf.format(new Date());
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(strDate);
			d2 = sdf.parse(currentDate);
			diff = d1.getTime() - d2.getTime();
		} catch (ParseException e) {
			log.error("Exception : " , e);
		}
		long min = diff / nm;// 计算差多少分钟
		return min;
	}
	
	/**
	 * 格式化数据库查出来的日期 eg:2017-11-10 16:59:30.0   ===》 2017-11-10 16:59:45
	 * @param str
	 * @return
	 */
	public static String formatDateStr(String str){
		if(str.indexOf(".")>0){
			return str.substring(0, str.indexOf("."));
		}
		return str;
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) throws Exception {
//		System.out.println(dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,addDateTime(dateFormatStrToTime(FORMAT_ONLY_DATE_EN,"2017-12-14"),20,4)));
//		System.out.println(ConvertDateTime.subDate(ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_FULL_DATETIME_EN, "2017-12-28"+" 00:00:00"), 
//				ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_FULL_DATETIME_EN, formatDateStr("2017-12-28 17:05:00.0")), 3));
//		
//		System.out.println(getTomorrow());
//		System.out.println(dayDifferenceHours("2017-12-18 00:00:00"));
//		System.out.println(dayDifferenceHours("2017-12-20 00:00:00"));
//		System.out.println(dateFormatStrToTime(FORMAT_YEAR,"2017-11-30 18:11:06"));
//		System.out.println(compareCurrentDate("2017-11-10"));
//		System.out.println(ConvertDateTime.compareDate(ConvertDateTime.getCurrentDate(),"2017-11-09"));
//		System.out.println(dateTimeFormattedEN(new DateTime()));
//		System.out.println(getDateAndWeekDayData(dateFormatStrToTime(FORMAT_ONLY_DATE_EN, "2017-04-30"), 10));
//		System.out.println(compareDate("2017-05-02","2017-05-01"));
//		System.out.println(compareDate("2017-05-02","2017-05-02"));
//		System.out.println(compareDate("2017-05-02","2017-05-03"));
//		System.out.println(compareDateTime("13:00:00","12:00:00"));
//		System.out.println(getSumDaysDate("2017-05-02",10));
//		System.out.println(ConvertDateTime.subDate(ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_ONLY_DATE_EN,ConvertDateTime.getTodayTime()), 
//				ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_ONLY_DATE_EN, "2017-11-08"), 3));
//		System.out.println(ConvertDateTime.subDate(ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_ONLY_DATE_EN,"2017-10-11"), dateFormatStrToTime(FORMAT_ONLY_DATE_EN, "2017-10-23"), 3));
//		System.out.println(Integer.parseInt(getDDTime()));
//		System.out.println(getlistdatelaster(40));
//		System.out.println(dateFormatTimeToStr(FORMAT_FULL_DATETIME_EN,addDateTime(getCurDate(),-10,4)));
//		System.out.println(dateFormatStrToTime(FORMAT_ONLY_DATE_EN, "2016-06-20"));
//		DateTime startDate1 = dateFormatStrToTime(FORMAT_FULL_DATETIME_EN, "2016-06-21 18:00:00");
//		DateTime startDate2 = dateFormatStrToTime(FORMAT_FULL_DATETIME_EN, "2016-06-20 20:00:00");
//		int subhour = subDate(new DateTime(), startDate, 5);
//		System.out.println("subhour:---->" + subhour);
//		String phonestr = "1232";
//		phonestr = subhour >= -2 * 60 ? phonestr : "tess";
//		System.out.println(subhour);
//		System.out.println(phonestr);
//		subhour = subDate(startDate1, new DateTime(), 5);
//		phonestr = subhour >= -2 * 60 ? phonestr : "tess";
//		System.out.println(subhour);
//		System.out.println(phonestr);
//		subhour = subDate(startDate2, new DateTime(), 5);
//		phonestr = subhour >= -2 * 60 ? phonestr : "test";
//		System.out.println(subhour);
//		System.out.println(phonestr);
//		System.err.println(subDate(getCurTime(), getCurTime(), 1));
//		System.out.println(getlistdatefirster(5,"2017-12-27"));
//	}
	/**
	 * 从指定日期往后多少天 的date list
	 * @param i
	 * @return so easy 。
	 * @throws Exception
	 */
	public static List<String> getlistdatefirster(int i,String startday) throws Exception{
		List<String> dateList=new ArrayList<String>();
		DateTime startDate = dateFormatStrToTime(FORMAT_ONLY_DATE_EN, startday);
		for (int j = 0; j < i; j++) {
			if (j==0) { 
				dateList.add(dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,startDate));
				continue;
			}
			DateTime newDate = ConvertDateTime.addDateTime(startDate, j, 4);
			dateList.add(dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,newDate));
		}
		return dateList;
	}
	
}
