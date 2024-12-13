package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalyticsCounter {
	
	public static void main(String args[]) throws Exception {

		ReadSymptomDataFromFile readSymptomDataFromFile = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> symptoms = readSymptomDataFromFile.GetSymptoms();

		Map<String, Long> symptomsCount = readSymptomDataFromFile.getCountSymptoms(symptoms);

		readSymptomDataFromFile.createResultFile(symptomsCount);

		symptomsCount.forEach((key,value)->{
            System.out.println(key+", "+value);
        });
	}


}
