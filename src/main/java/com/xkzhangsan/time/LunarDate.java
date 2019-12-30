package com.xkzhangsan.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;

/**
 * 农历日期
* @ClassName: LunarDate 
* @Description: LunarDate
* @author xkzhangsan
* @date 2019年12月30日
* @version 0.1 ，初版，试用
 */
public final class LunarDate {
	
	/**
	 * 农历信息
	 */
	private static final long[] lunarInfo = new long[] { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554,
			0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0,
			0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566,
			0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550,
			0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0,
			0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
			0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0,
			0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5,
			0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0,
			0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9,
			0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0,
			0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520,
			0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 };

	/**
	 * 农历月份列表
	 */
	public static final String[] lunarMonth = new String[] { "", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬",
			"腊" };
	/**
	 * 天干列表
	 */
	private static final String[] tianGan = new String[] { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };

	/**
	 * 地支列表
	 */
	private static final String[] diZhi = new String[] { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };

	/**
	 * 生肖列表
	 */
	private static final String[] animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };

	/**
	 * 中文数字0-9
	 */
	public static final String[] numStr = new String[] { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};	

	/**
	 * 标准日期
	 */
	private final LocalDate localDate;

	/**
	 * 农历日期，中文
	 */
	private String lDateCn;

	/**
	 * 岁次
	 */
	private String suiCi;

	/**
	 * 生肖
	 */
	private String lAnimal;

	/**
	 * 农历年
	 */
	private int lYear;

	/**
	 * 农历月
	 */
	private int lMonth;

	/**
	 * 农历日
	 */
	private int lDay;

	/**
	 * 农历年，中文
	 */
	private String lYearCn;

	/**
	 * 农历月，中文
	 */
	private String lMonthCn;

	/**
	 * 农历日，中文
	 */
	private String lDayCn;

	/**
	 * 星期，中文
	 */
	private String weekCn;

	private LunarDate(LocalDate localDate) {
		super();
		this.localDate = localDate;
		initialize();
	}
	
	/**
	 * 初始化农历日期
	 */
	public void initialize() {
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		long[] l = calElement(year, month, day);
		
		this.suiCi = cyclical(year);
		this.lAnimal = animalsYear(year);

		this.lYear = (int) l[0];
		this.lMonth = (int) l[1];
		this.lDay = (int) l[2];

		this.lYearCn = getChinaYear(this.lYear);
		this.lMonthCn = lunarMonth[this.lMonth];
		this.lDayCn = getChinaDay(this.lDay);

		this.weekCn = getWeekCn(localDate.getDayOfWeek().getValue());
		this.lDateCn = this.lYearCn + "年" + this.lMonthCn + "月" + this.lDayCn;
	}
	
	/**
	 * 通过LocalDateTime创建LunarDate
	 * @param localDateTime
	 * @return
	 */
	public static LunarDate from(LocalDateTime localDateTime) {
		return new LunarDate(DateTimeConverterUtil.toLocalDate(localDateTime));
	}

	/**
	 * 通过LocalDate创建LunarDate
	 * @param localDate
	 * @return
	 */
	public static LunarDate from(LocalDate localDate) {
		return new LunarDate(localDate);
	}
	
	/**
	 * 通过Instant创建LunarDate
	 * @param localDate
	 * @return
	 */
	public static LunarDate from(Instant instant) {
		return new LunarDate(DateTimeConverterUtil.toLocalDate(instant));
	}	

	/**
	 * 通过Date创建LunarDate
	 * @param date
	 * @return
	 */
	public static LunarDate from(Date date) {
		return new LunarDate(DateTimeConverterUtil.toLocalDate(date));
	}

	/**
	 * 传回农历year年的总天数
	 *
	 * @param year
	 * @return
	 */
	private static final int lunarYearDays(int year) {
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((lunarInfo[year - 1900] & i) != 0)
				sum += 1;
		}
		return (sum + leapMonthDays(year));
	}

	/**
	 * 传回农历 year年闰月的天数
	 *
	 * @param year
	 * @return
	 */
	private static final int leapMonthDays(int year) {
		if (leapMonth(year) != 0) {
			if ((lunarInfo[year - 1900] & 0x10000) != 0)
				return 30;
			else
				return 29;
		} else
			return 0;
	}

	/**
	 * 传回农历 year年闰哪个月 1-12 , 没闰传回 0
	 *
	 * @param year
	 * @return
	 */
	private static final int leapMonth(int year) {
		return (int) (lunarInfo[year - 1900] & 0xf);
	}

	/**
	 * 传回农历 year年month月的总天数
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	private static final int monthDays(int year, int month) {
		if ((lunarInfo[year - 1900] & (0x10000 >> month)) == 0)
			return 29;
		else
			return 30;
	}

	/**
	 * 传回农历 year年的生肖
	 *
	 * @param year
	 * @return
	 */
	public static final String animalsYear(int year) {
		return animals[(year - 4) % 12];
	}

	/**
	 * 传入 月日的offset 传回干支,0=甲子
	 *
	 * @param num
	 * @return
	 */
	private static final String cyclicalm(int num) {
		return (tianGan[num % 10] + diZhi[num % 12]);
	}

	/**
	 * 传入 offset 传回干支, 0=甲子
	 *
	 * @param year
	 * @return
	 */
	public static final String cyclical(int year) {
		int num = year - 1900 + 36;
		return (cyclicalm(num));
	}

	/**
	 * 传出year年month月day日对应的农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5
	 * .isLeap6
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static final long[] calElement(int year, int month, int day) {
		long[] nongDate = new long[7];
		int i = 0, temp = 0, leap = 0;
		LocalDate baseDate = LocalDate.of(0 + 1900, 1, 31);
		LocalDate objDate = LocalDate.of(year, month, day);
		long offset = (DateTimeConverterUtil.toEpochMilli(objDate) - DateTimeConverterUtil.toEpochMilli(baseDate))
				/ 86400000L;
		nongDate[5] = offset + 40;
		nongDate[4] = 14;
		for (i = 1900; i < 2050 && offset > 0; i++) {
			temp = lunarYearDays(i);
			offset -= temp;
			nongDate[4] += 12;
		}
		if (offset < 0) {
			offset += temp;
			i--;
			nongDate[4] -= 12;
		}
		nongDate[0] = i;
		nongDate[3] = i - 1864;
		leap = leapMonth(i); // 闰哪个月
		nongDate[6] = 0;
		for (i = 1; i < 13 && offset > 0; i++) {
			// 闰月
			if (leap > 0 && i == (leap + 1) && nongDate[6] == 0) {
				--i;
				nongDate[6] = 1;
				temp = leapMonthDays((int) nongDate[0]);
			} else {
				temp = monthDays((int) nongDate[0], i);
			}
			// 解除闰月
			if (nongDate[6] == 1 && i == (leap + 1))
				nongDate[6] = 0;
			offset -= temp;
			if (nongDate[6] == 0)
				nongDate[4]++;
		}
		if (offset == 0 && leap > 0 && i == leap + 1) {
			if (nongDate[6] == 1) {
				nongDate[6] = 0;
			} else {
				nongDate[6] = 1;
				--i;
				--nongDate[4];
			}
		}
		if (offset < 0) {
			offset += temp;
			--i;
			--nongDate[4];
		}
		nongDate[1] = i;
		nongDate[2] = offset + 1;
		return nongDate;
	}

	/**
	 * 获取农历中文年
	 * @param year
	 * @return
	 */
	public final static String getChinaYear(int year) {
		String ge = numStr[year % 10];
		String shi = numStr[year / 10 % 10];
		String bai = numStr[year / 100 % 10];
		String qian = numStr[year / 1000 % 10];
		return qian + bai + shi + ge;
	}
	/**
	 * 获取农历中文日期
	 * @param day
	 * @return
	 */
	public final static String getChinaDay(int day) {
		String a = "";
		if (day == 10)
			return "初十";
		if (day == 20)
			return "二十";
		if (day == 30)
			return "三十";
		int two = (int) ((day) / 10);
		if (two == 0)
			a = "初";
		if (two == 1)
			a = "十";
		if (two == 2)
			a = "廿";
		if (two == 3)
			a = "三";
		int one = (int) (day % 10);
		switch (one) {
		case 1:
			a += "一";
			break;
		case 2:
			a += "二";
			break;
		case 3:
			a += "三";
			break;
		case 4:
			a += "四";
			break;
		case 5:
			a += "五";
			break;
		case 6:
			a += "六";
			break;
		case 7:
			a += "七";
			break;
		case 8:
			a += "八";
			break;
		case 9:
			a += "九";
			break;
		default:
			a += "";
			break;
		}
		return a;
	}
	
	/**
	 * 获取中文星期
	 * @param week
	 * @return
	 */
	public final static String getWeekCn(int week) {
		String weekCn = "";
		switch (week) {
		case 1:
			weekCn = "星期一";
			break;
		case 2:
			weekCn = "星期二";
			break;
		case 3:
			weekCn = "星期三";
			break;
		case 4:
			weekCn = "星期四";
			break;
		case 5:
			weekCn = "星期五";
			break;
		case 6:
			weekCn = "星期六";
			break;
		case 7:
			weekCn = "星期日";
			break;
		default:
			weekCn = "";
			break;
		}
		return weekCn;
	}

	/**
	 * 以当前时间创建农历日期LunarDate
	 * @return
	 */
	public static LunarDate now() {
		LocalDate today = LocalDate.now();
		return new LunarDate(today);
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public String getlDateCn() {
		return lDateCn;
	}

	public String getSuiCi() {
		return suiCi;
	}

	public String getlAnimal() {
		return lAnimal;
	}

	public int getlYear() {
		return lYear;
	}

	public int getlMonth() {
		return lMonth;
	}

	public int getlDay() {
		return lDay;
	}

	public String getlYearCn() {
		return lYearCn;
	}

	public String getlMonthCn() {
		return lMonthCn;
	}

	public String getlDayCn() {
		return lDayCn;
	}

	public String getWeekCn() {
		return weekCn;
	}

	@Override
	public String toString() {
		return "LunarDate [localDate=" + localDate + ",lDateCn=" + lDateCn + ", suiCi=" + suiCi + ", lAnimal=" + lAnimal + ", lYear=" + lYear
				+ ", lMonth=" + lMonth + ", lDay=" + lDay + ", lYearCn=" + lYearCn + ", lMonthCn=" + lMonthCn
				+ ", lDayCn=" + lDayCn + ", weekCn=" + weekCn + "]";
	}
	
	/**
	 * 格式化输出，如： 庚子鼠年 二〇一九年腊月初七 星期三
	 * @return
	 */
	public String format(){
		return suiCi + lAnimal + "年 " + lDateCn + " " + weekCn;
	}


	public static void main(String[] args) {
		String str =LunarDate.now().toString();
		System.out.println(str);
		System.out.println(LunarDate.now().format());
	}
}