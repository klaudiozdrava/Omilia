package com.example.Omilia;



import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmiliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmiliaApplication.class, args);
		
		String regex = "[^\\d\\ ]| \\.|\\.$";
		System.out.println("=========================================Process=============================================================");
		
		System.out.println("Insert a number: ");
		Scanner input_number = new Scanner(System.in);
		
		String digits = input_number.nextLine();
		digits = digits.replaceAll(regex, "");
		input_number.close();
		
		NumberRecognition numbers = new NumberRecognition();
		numbers.setNumber(digits);
		
		String input_without_spaces = numbers.number_without_spaces();
		String message = numbers.validationMessage(input_without_spaces);
		
		System.out.println();
		System.out.println("NUMBER :"+numbers.getNumber()+" WAS CONVERTED TO :"+
		input_without_spaces + " WITH MESSAGE :"+message+"\n");
		
		
		System.out.println("Below are all possible combinations of the user input :\n");
		Map<String,String> nums_ambyguity = numbers.getValid_and_not();
		int interpretation = 1;
		for(Map.Entry mp: nums_ambyguity.entrySet()){
			System.out.println("Interpretation "+ interpretation + " :"+ mp.getKey()+ " "+mp.getValue());
			interpretation++;
		}
		
	}
}
