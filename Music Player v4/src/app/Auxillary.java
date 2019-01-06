package app;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Auxillary {
	public static LocalDate makeDate(int d, int m, int y) throws DateTimeException
	{
		LocalDate date = LocalDate.of(y, m, d);
		return date;
	}
}
