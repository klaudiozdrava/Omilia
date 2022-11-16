
package com.example.Omilia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OmiliaApplicationTests {

	@Test
	 void Test1() {
		NumberRecognition num = new NumberRecognition();
		num.setNumber("30 2 5 58");
		String get_num = num.number_without_spaces();
		boolean val = num.validate(get_num);
		assertFalse(val);
	}
	
	@Test
	public void Test2() {
		NumberRecognition num = new NumberRecognition();
		num.setNumber("2 10 69 30 6 6 4");
		String get_num = num.number_without_spaces();
		boolean val = num.validate(get_num);
		assertTrue(val);
	}
	
	@Test
	public void Test3() {
		NumberRecognition num = new NumberRecognition();
		num.setNumber("269 30 431 22");
		String get_num = num.number_without_spaces();
		boolean val = num.validate(get_num);
		assertTrue(val);
	}
	
	@Test
	public void Test4() {
		NumberRecognition num = new NumberRecognition();
		num.setNumber("0 0 3 0 2 6 7 8 9 1 12 0 1");
		String get_num = num.number_without_spaces();
		boolean val = num.validate(get_num);
		assertTrue(val);
	}
	
	@Test
	public void Test5() {
		NumberRecognition num = new NumberRecognition();
		num.setNumber("0 0 3 0 2 6 7 8 9 1 12 0 1");
		Map<String,String> nums_ambyguity = num.getValid_and_not();
		int count = nums_ambyguity.size(); //Counts elements in hashmap
		assertEquals(count,1);
	}
	
	@Test
	public void Test6() {
		NumberRecognition num = new NumberRecognition();
		num.setNumber("000 06 35 127 10 6 800 90 1");//000 06 30 5 100 20 7 10 6 800 90 1 -> 64
		Map<String,String> nums_ambyguity = num.getValid_and_not();
		int count = nums_ambyguity.size(); //Counts elements in hashmap
		assertEquals(count,64);
	}
	
	@Test
	public void Test7() {
		NumberRecognition num = new NumberRecognition();
		num.setNumber("00 60 2 36 720 60 11 800 31");//00 60 2 30 6 700 20 60 11 800 30 1 ->32
		Map<String,String> nums_ambyguity = num.getValid_and_not();
		int count = nums_ambyguity.size(); //Counts elements in hashmap
		assertEquals(count,32);
	}
	
	
	
	
	

}
