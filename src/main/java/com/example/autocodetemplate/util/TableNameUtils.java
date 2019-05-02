package com.example.autocodetemplate.util;

import com.example.autocodetemplate.sharding.Enum.EnumDateShardTableType;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TableNameUtils {
	
	/**
	 * 获取输入日期的所属周第一天的日期
	 * @return _xxxx_xx_xx
	 */
	public static String getMondayOfThisWeekSuffix(Date day) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = sdf.format(c.getTime());
		return "_"+result.replace('-', '_');
	}
	
//	/**
//	 * 获取输入日期的所属月
//	 * @return _xxxx_xx
//	 */
//	public static String getYearMonthSuffix(Date day, EnumDateShardTableType tableType) {
//		Calendar c = Calendar.getInstance();
//		c.setTime(day);
//		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH) + 1;
//		return "_" + year + "_" + StringUtil.addLeftZeroForNum(String.valueOf(month), 2);
//	}


	/**
	 * 获取当前日期生成的后缀
	 * @return _xxxx_xx_xx
	 */
	public static String getDateSuffix(Date date, EnumDateShardTableType tableType) {
		String dataStr = TimeUtil.dateConvertDateStr(date, DateTimeFormatter.ofPattern(tableType.getValue()));

		if (StringUtils.isBlank(dataStr)) {
			return "";
		}

		return "_" + dataStr;
	}
	
	/**
	 * 获取输入字串的hash处理后缀
	 * @return _x
	 */
	public static String getHashKeySuffix(String key){
		try{
			int value=Math.abs(key.hashCode());
			return "_"+String.valueOf(value%16);
		}catch(Exception e){
			
		}
		return "";
	}
	
}
