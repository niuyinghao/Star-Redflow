package my.util;

import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;

/**
 */
@ManagedBean
@Component
public class Utils {
	        public String get(boolean b,String value,String altValue)    {
				return  b?value:altValue;
			}

	public boolean value() {
		return false;
	}

}
