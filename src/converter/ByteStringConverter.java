package converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class ByteStringConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		// TODO Auto-generated method stub
		String s=arg1[0];
		System.out.println(s);
		byte[] b=s.getBytes();
		return b;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		byte[] b=(byte[])arg1;
		String s=new String(b);
		System.out.println(s);
		return s;
	}

}
