package sms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Test {
	static List<Availability> availabilities = new ArrayList<Availability>();
	static {
		try {
		TimePeriod t1 = new TimePeriod();
		t1.setStartTime("11");
		t1.setEndTime("11:30");

		TimePeriod t2 = new TimePeriod();
		t2.setStartTime("12:30");
		t2.setEndTime("4:30");

		TimePeriod t3 = new TimePeriod();
		t3.setStartTime("8");
		t3.setEndTime("1:30");

		TimePeriod t4 = new TimePeriod();
		t4.setStartTime("16:50");
		t4.setEndTime("16:30");

		/*Availability a1 = new Availability();
		a1.setName(Day.MON);
		a1.setTimePeriods(t1);
		calculateNextTime(Day.MON, a1.getTimePeriods().getStartTime());
		
		
		Availability a2 = new Availability();
		a2.setName(Day.MON);
		a2.setTimePeriods(t2);
		calculateNextTime(Day.MON, a2.getTimePeriods().getStartTime());
		
		Availability a3 = new Availability();
		a3.setName(Day.FRI);
		a3.setTimePeriods(t3);
		calculateNextTime(Day.FRI, a3.getTimePeriods().getStartTime());
*/
		Availability a4 = new Availability();
		a4.setName(Day.FRI);
		a4.setTimePeriods(t4);
		calculateNextTime(Day.THU, a4.getTimePeriods().getStartTime());

		/*availabilities.add(a1);
		availabilities.add(a2);
		availabilities.add(a3);
		availabilities.add(a4);*/
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	public static void calculateNextTime(Day day, String startTime) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		int total = 0;
		if (day.toValue() < calendar.get(Calendar.DAY_OF_WEEK)) {
			total = (7 + day.toValue()) - calendar.get(Calendar.DAY_OF_WEEK);
		} else if (day.toValue() == calendar.get(Calendar.DAY_OF_WEEK)){
			Date our = parser.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " +startTime);
			Date system = parser.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
			if(our.before(system)) {
				total = (7 + day.toValue()) - calendar.get(Calendar.DAY_OF_WEEK);;
			} 
		} else {
			total = day.toValue() - calendar.get(Calendar.DAY_OF_WEEK);
		}
		calendar.add(Calendar.DAY_OF_MONTH, total);
		Date nextRunTime = parser.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) +1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + startTime);
		System.out.println(nextRunTime);
	}

	public static void main(String[] args) {
		availabilities.stream().forEach((Availability t) -> {
			System.out.println(t);
		});
	}

	public static void nextTime() {

	}
}

class Availability {

	private Date pre;

	private Date next;

	private Day Name;

	private TimePeriod timePeriods;

	public TimePeriod getTimePeriods() {
		return timePeriods;
	}

	public void setTimePeriods(TimePeriod timePeriods) {
		this.timePeriods = timePeriods;
	}

	public Day getName() {
		return Name;
	}

	public void setName(Day name) {
		Name = name;
	}

	public Date getPre() {
		return pre;
	}

	public void setPre(Date pre) {
		this.pre = pre;
	}

	public Date getNext() {
		return next;
	}

	public void setNext(Date next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Availability [pre=" + pre + ", next=" + next + ", Name=" + Name
				+ ", timePeriods=" + timePeriods + "]";
	}
}

class TimePeriod {
	private String startTime;
	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TimePeriod [startTime=" + startTime + ", endTime=" + endTime
				+ "]";
	}
}
