package test1;
// Java program to read JSON from a file

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReadExample
{
	public static void main(String[] args) throws Exception
	{
		  try (FileReader fileReader = new FileReader("src/main/resources/dataa.json"); 
	        	     BufferedReader reader = new BufferedReader(fileReader)) {
	        	    String inputStream = reader.lines()
	        	      .collect(Collectors.joining(System.lineSeparator()));
		
//		String filePath = "src/main/resources/dataa.json";
//		// parsing file "JSONExample.json"
		Object obj = new JSONParser().parse(inputStream);
		
		// typecasting obj to JSONObject
		JSONObject jo = (JSONObject) obj;
		
		// getting firstName and lastNam
		String firstName = (String) jo.get("product_name");
		String lastName = (String) jo.get("product_memory");
		
		System.out.println(firstName);
		System.out.println(lastName);
		
		// getting age
		String age = (String) jo.get("product_color");
		System.out.println(age);
		
		// getting address
		String address = (String)jo.get("product_price");
		System.out.println(address);
		
//		// iterating address Map
//		Iterator<Map.Entry> itr1 = address.entrySet().iterator();
//		while (itr1.hasNext()) {
//			Map.Entry pair = itr1.next();
//			System.out.println(pair.getKey() + " : " + pair.getValue());
//		}
		
		// getting phoneNumbers
		String ja = (String) jo.get("product_url");
		
		// iterating phoneNumbers
//		Iterator itr2 = ja.iterator();
//		
//		while (itr2.hasNext())
//		{
//			itr1 = ((Map) itr2.next()).entrySet().iterator();
//			while (itr1.hasNext()) {
//				Map.Entry pair = itr1.next();
//				System.out.println(pair.getKey() + " : " + pair.getValue());
//			}
//		}
	}
		  catch(Exception e) {
			  System.out.println(e);
		  }
}
}
