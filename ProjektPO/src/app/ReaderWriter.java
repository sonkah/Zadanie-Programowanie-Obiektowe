package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReaderWriter {

	List<Record> records = new ArrayList<Record>();
	
	private FileReader reader;
	private BufferedReader input;

	public void parse() throws IOException {
		
		try {
			reader = new FileReader("BestScores.txt");
			input = new BufferedReader(reader);
			String str;
			while ((str = input.readLine()) != null) {
				
				String tmp[] = str.split(";");
				if(tmp.length > 1){
		//		for(int i = 0; i < tmp.length; i++){
		//			System.out.println(tmp[1] + records.size());
		//		}
				Record r = new Record(Integer.valueOf(tmp[1]), tmp[0]);
			    records.add(r);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			input.close();
			reader.close();
		}
	}

	public void save(Record r, boolean s) throws IOException {				//s - zapis do pliku czy nadpisanie
		String path = "BestScores.txt";

		FileWriter writer = new FileWriter(path,s);
		BufferedWriter output = new BufferedWriter(writer);
		output.write(r.getName() + ";" + r.getPoints());
		output.newLine();
		output.close();
		writer.close();
	}
	
	public void erase() throws IOException {
		
	}

	public void checkBest(Record r) {
		Collections.sort(records);
		if (records.size() < 10) {
			records.add(r);
			Collections.sort(records);
			try {
				save(r, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (records.get(0).getPoints() < r.getPoints()) {
			records.remove(0);
			records.add(r);
			int i = 0;
			for (Record rec : records) {
				
				try {
					if (i==0) save(rec,false);
					else save(rec,true);
					
					System.out.println(rec.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
	}
}
