package sms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Test {
	public static Date calculateNextTime(Day day, String startTime) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		int total = 0;

		if (day.toValue() < calendar.get(Calendar.DAY_OF_WEEK)) {
			total = (7 + day.toValue()) - calendar.get(Calendar.DAY_OF_WEEK);
		} else if (day.toValue() == calendar.get(Calendar.DAY_OF_WEEK)) {
			Date our = parser.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + startTime);
			Date system = parser.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
			if (our.before(system)) {
				total = (7 + day.toValue()) - calendar.get(Calendar.DAY_OF_WEEK);
			}
		} else {
			total = day.toValue() - calendar.get(Calendar.DAY_OF_WEEK);
		}
		calendar.add(Calendar.DAY_OF_MONTH, total);
		return parser.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"+ calendar.get(Calendar.DAY_OF_MONTH) + " " + startTime);
	}

	public static void main(String[] args) throws ParseException {
		TimePeriod t4 = new TimePeriod();
		t4.setStartTime("17:07");
		t4.setEndTime("17:07");

		Availability a4 = new Availability();
		a4.setName(Day.FRI);
		a4.setTimePeriods(t4);
		
		List<Date> dates = new ArrayList<Date>();
		dates.add(calculateNextTime(Day.MON, t4.getStartTime()));
		dates.add(calculateNextTime(Day.TUE, t4.getStartTime()));
		dates.add(calculateNextTime(Day.FRI, t4.getStartTime()));
		dates.add(calculateNextTime(Day.SAT, t4.getStartTime()));
		dates.add(calculateNextTime(Day.TUE, t4.getStartTime()));
		dates.add(calculateNextTime(Day.WED, t4.getStartTime()));
		
		dates.sort(new Comparator<Date>() {
			@Override
			public int compare(Date o1, Date o2) {
				System.out.println("--");
				return o1.before(o2)? o1.hashCode(): 1;
			}
		});
		
		for (Date d : dates){
			System.out.println(d);
		}
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
