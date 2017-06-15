package sms;

public enum Day {
	SUN(1), MON(2), TUE(3), WED(4), THU(5), FRI(6), SAT(7);
	
	private int value;
	Day(int value) {
		this.value = value;
	}
	
	public int toValue() {
		return value;
	}
}

