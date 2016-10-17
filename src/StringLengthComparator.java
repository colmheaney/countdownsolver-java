

import java.util.Comparator;

public class StringLengthComparator implements Comparator<Object> {

	@Override
	public int compare(Object a, Object b) {
		if(a.toString().length() > b.toString().length())
			return 1;
		if(a.toString().length() < b.toString().length())
			return -1;
		return 0;
	}
}
