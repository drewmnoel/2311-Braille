package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.List;

import events.Event;

public class ErrorManagement{
	public static void errorManage(List<Event> eventList){
	String line;

	int cells, buttons, lineNum = 1000;
	boolean found = false;
	LineNumberReader lineNumReader = null;
	try{
	File inputFile = new File("test.txt");
	FileReader inputReader = new FileReader(inputFile);
	BufferedReader bufferedInput = new BufferedReader(inputReader);
	lineNumReader = new LineNumberReader(inputReader);

	//testing if INIT is present
	while ((line = bufferedInput.readLine()) != null) {
		if(line.split(" ")[0].equals("INIT"))
		{
			found = true;
			lineNum = lineNumReader.getLineNumber();
			cells = Integer.parseInt(line.split(" ")[1]);
			buttons = Integer.parseInt(line.split(" ")[2]);
			if(cells > 8 || cells < 1){
				System.out.println("Cells not in valid range[1-8]. Please enter again.");
				System.exit(0);
			}
			if(buttons > 10 || buttons < 1){
				System.out.println("Buttons not in valid range[1-10]. Please enter again.");
				System.exit(0);
			}
			System.out.println("Found at " + lineNum + " line");
		}

		if((found == false)||(lineNum != 0))
		{
			System.out.println("Initiliziation failed");
			System.exit(0);
		}

	}
	inputReader.close();
	bufferedInput.close();
	}
	catch(Exception e){};
	System.out.println("Starting testing list");
	Iterator<Event> itr = eventList.iterator();
	while(itr.hasNext()){
		Event array = itr.next(); // You were just missing saving the value for reuse
	    System.out.println(array.getClass());
	}
	}
	//System.out.println(eventList.listIterator(0));
}
