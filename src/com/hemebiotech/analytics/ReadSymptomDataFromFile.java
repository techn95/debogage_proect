package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<String>();
		
		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath));
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	/**
	 *
	 * @param symptoms  is the list of symptoms present in the file, ignoring duplicates.
	 * @return A list sorted in alphabetical order of key-value elements, where the key represents a symptom and the value represents the number of times it appears in our list of symptoms.
	 */
	public Map<String, Long> getCountSymptoms(List<String> symptoms) {
		Map<String, Long> result = new TreeMap<>(symptoms.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		return result;
	}

	/**
	 *
	 * @param countSymptoms  is the list sorted in alphabetical order of key-value elements, where the key represents a symptom and the value represents the number of times it appears in our list of symptoms.
	 * @return Create the file results.out (if it does not already exist) which will contain our different symptoms and their frequency of occurrence
	 */
	public void createResultFile(Map<String, Long> countSymptoms) {
		try{
			FileWriter writer = new FileWriter ("results.out");
			countSymptoms.forEach((key, value)->{
                try {
                    writer.write(key + ", " + value);
					writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
