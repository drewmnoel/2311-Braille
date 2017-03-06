package common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.List;

import events.*;


public class ErrorManagement{
	
	/**
	 * Checks for errors associated with INIT events
	 * If there is an INIT event is on the first line
	 * If the INIT event declares a number of buttons and cells in the accepted range
	 * There are no INIT events anywhere else in the player file
	 * Throws an exception if any of the above is false
	 * 
	 * @param eventList list of events parsed from player file
	 * @throws Exception
	 */
	public static void checkInit(List<Event> eventList) throws Exception{		
		//Get the first event
		Event iterEvent = eventList.get(0);
		
		int buttons;
		int cells;
		
		//Check if first event is an INIT event
		if(iterEvent instanceof events.InitEvent) {
			//parse declared number of buttons and cells 
			buttons = Integer.parseInt(iterEvent.getDetails().split(" ", -1)[0]);
			cells = Integer.parseInt(iterEvent.getDetails().split(" ", -1)[1]);
			
			//check if declared buttons is out of 1-8 range
			if(buttons < 1 || buttons > 8) {
				throw new Exception("Error: INIT must declare a number of buttons between 1-8");
			}
			//check if declared braille cells is out of 1-10 range
			if(cells < 1 || cells > 10) {
				throw new Exception("Error: INIT must declare a number of braille cells between 1-10");
			}
		}
		//Throw an exception if first event is not INIT
		else {
			throw new Exception("Error: First event is not an INIT event");
		}
		
		//iterate over events to see if there are other INIT events
		for(int i = 1; i < eventList.size(); i++) {
			//If another INIT event is found
			iterEvent = eventList.get(i);
			System.out.println(iterEvent.getDetails());
			if(iterEvent instanceof events.InitEvent) {
				throw new Exception("Error: Can only have one INIT event in a player file");
			}
		}
		
	}
	
	/**
	 * Checks if any BUTTON Events use a button that hasn't been initialized in the simulator
	 * If one or more is found, throws an exception with a description that a ButtonEvent was not properly defined
	 * @param eventList List of events parsed from player file
	 * @throws Exception
	 */
	public static void checkButtons(List<Event> eventList) throws Exception {
		//parse the number of buttons in simulator from InitEvent
		int buttons = Integer.parseInt(eventList.get(0).getDetails().split(" ", -1)[0]);
		
		//Iterate over the event list
		for(int i = 1; i < eventList.size(); i++) {
			//If the event is a ButtonEvent
			if(eventList.get(i).getClass() == events.ButtonEvent.class) {
				//Check the number of buttons referenced in the description is greater than in the simulator
				//If so, throw an exception
				if(eventList.get(i).getDetails().split(" ", -1).length > buttons) {
					throw new Exception("BUTTON Event declared with an illegal number of buttons.");
				}
			}
		}
		
	}
	
	/**
	 * Checks if any BRAILLE Event use a braille cell that hasn't been initialized in the simulator
	 * If one or more is found, throws an exception with a description that a BrailleEvent was not properly defined
	 * @param eventList
	 * @throws Exception
	 */
	public static void checkCells(List<Event> eventList) throws Exception {
		//Parse the number of cells in simulator from InitEvent
		int cells = Integer.parseInt(eventList.get(0).getDetails().split(" ", -1)[1]);
		
		//Iterate over the event list
		for(int i = 1; i < eventList.size(); i++) {
			//If the event is a ButtonEvent
			if(eventList.get(i).getClass() == events.BrailleEvent.class) {
				//Check the number of buttons referenced in the description is greater than in the simulator
				//If so, throw an exception
				if(eventList.get(i).getDetails().length() > cells) {
					throw new Exception("BRAILLE Event declared with an illegal number of cells.");					}
			}
		}
		
	}
}
