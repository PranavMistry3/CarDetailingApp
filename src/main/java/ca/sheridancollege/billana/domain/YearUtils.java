package ca.sheridancollege.billana.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class YearUtils {
	
	public static List<Integer> getAllYears() {
        List<Integer> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 1995; year <= currentYear; year++) {
            years.add(year);
        }
        return years;
    }
	
}
