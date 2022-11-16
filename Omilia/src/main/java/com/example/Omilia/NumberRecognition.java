package com.example.Omilia;


import java.util.HashMap;
import java.util.Map;

public class NumberRecognition {

	private String number;
	private static final String StartWithTwo="2";
	private static final String StartWith69="69";
	private static final String endtWithTwo="00302";
	private static final String endtWith69="003069";
	private Map<String,String> valid_and_not;
	private char search = '0';//char that will be searched in the previous number
	
	public NumberRecognition() {
		valid_and_not = new HashMap<String,String>();//create new map object
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public int getNumberLength() {
		return this.number.length();
	}
	
	public String number_without_spaces() {
		return number.replaceAll("\\s+","");//Remove white spaces from input;
	}
	
	
	//Used for the first question to validate the input number
	public boolean validate(String input) {
		if(input.length()==14) {//If input number has 14 digits and starts with 00302 or 003069 means number is valid
			if(input.startsWith(endtWithTwo) || input.startsWith(endtWith69)) {
				return true;
			}
		}else if(input.length()==10) {//If input number has 10 digits and starts with 2 or 69 means number is valid
			if(input.startsWith(StartWith69) || input.startsWith(StartWithTwo)) {
				return true;
			}
		}
		return false;
	}
	
	public String validationMessage(String input) {
		String message = validate(input) ? "[phone number: VALID]" : "[phone number: INVALID]";
		return message;
	}
	
	//Used to create all possible numbers
	//A recursive call that combine numbers to create all possibilities.
	private void possibleNumbers(String processed,String remaining) {
		
		if(remaining.isEmpty()) {
			String remove_spaces = processed.replaceAll("\\s+","");
			String validation = validationMessage(remove_spaces);
			valid_and_not.put(remove_spaces, validation);
			return;
		}
		
		int position_of_first_remaining_number = remaining.indexOf(" ");//get the position of current number after the space char
		String current_number = remaining.substring(0,position_of_first_remaining_number);//get the current number
		
		if(processed.length()>3) {
			
			int position_of_prev_number =processed.lastIndexOf(" ");
			String previous_number =processed.substring(position_of_prev_number+1);//get the last previous number
			int prev = Integer.valueOf(previous_number);
			previous_number = String.valueOf(prev);
			
			long count = previous_number.chars().filter(ch -> ch == search).count();//Counts number of zeros in the previous number
			
			if(previous_number.length()>current_number.length() && current_number.length()==count) {//if zeros in previous number are the same as the length of current number
				//then put current number in the positions of zeros.
				//Example : 650 9 --> 659
				String concatenate = previous_number.substring(0,previous_number.length()-current_number.length())
						+current_number;
				String previous_numbers = processed.substring(0,position_of_prev_number);
				possibleNumbers(" "+previous_numbers+" "+concatenate,remaining.substring(current_number.length() + 1));
			}
		}
		possibleNumbers(" "+processed+" "+current_number,remaining.substring(current_number.length() + 1));
	}
	

	//This function is used to extract numbers as units dozens or hundreds
	//For example if input number is 236 number will be converted as 200 30 6
	private String StretchNumbers() {
		
		StringBuilder sb = new StringBuilder();
		String[] splitted_number = this.number.split(" ");
		for(String digit:splitted_number) {
			if(digit.length()==3) {
				int integer_part_at_hundred = Integer.valueOf(digit)/100;
				int remaining_part_at_hundred = Integer.valueOf(digit)%100;
				String h = integer_part_at_hundred > 0 ? digit.charAt(0)+"00 " : "0 ";
				String if_remaining_part_at_hundred_not_zero = remaining_part_at_hundred > 0 ? String.valueOf(remaining_part_at_hundred) : "";
				
				if_remaining_part_at_hundred_not_zero = if_remaining_part_at_hundred_not_zero.length()!=0 ?
						digit.endsWith("11") || digit.endsWith("12") || Integer.valueOf(if_remaining_part_at_hundred_not_zero)%10 == 0 ||
						Integer.valueOf(if_remaining_part_at_hundred_not_zero)/10 == 0 ? if_remaining_part_at_hundred_not_zero + " " 
								: if_remaining_part_at_hundred_not_zero.charAt(0)+"0 "+if_remaining_part_at_hundred_not_zero.charAt(1)+" "
								:"";
				String whole_number = Integer.valueOf(digit)==0 ? "000 " : h + if_remaining_part_at_hundred_not_zero;
				
				sb.append(whole_number);
					
			}else if(digit.length()==2) {
				int mod = Integer.valueOf(digit)%10;
				int integer_numb = Integer.valueOf(digit)/10;
				String units_dozens = digit.startsWith("11") || digit.startsWith("12") || mod == 0 || integer_numb==0 ? 
						digit + " ": digit.charAt(0)+"0 "+digit.charAt(1)+" ";
				
				sb.append(units_dozens);
			}else {
				sb.append(digit+" ");
			}
		}
		
		return sb.toString();
	}
	
	//Return all  possible ambiguities in number spelling.
	public Map<String, String> getValid_and_not() {
		String stretch_nums = StretchNumbers();
		possibleNumbers("",stretch_nums);
		return valid_and_not;
	}
	
}



