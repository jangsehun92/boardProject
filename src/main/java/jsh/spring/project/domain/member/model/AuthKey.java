package jsh.spring.project.domain.member.model;

import java.util.Random;

public class AuthKey {

	private int size;
	private boolean lowerCheck;
	
	public String getKey(int size, boolean lowerCheck) {
		this.size = size;
		this.lowerCheck = lowerCheck;
		
		return makeKey();
	}
	
	private String makeKey() {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		int num = 0;
		
		while(sb.length() < size) {
			num = random.nextInt(75) + 48;
			
			if( (num >= 48 && num <=57) || (num >= 65 && num <= 90) || (num >= 97 && num <=122) ) {
				sb.append((char)num);
			}
		}
		
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}
		
		return sb.toString();
	}
}
