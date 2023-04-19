package model.Routes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
// public class Stop {
//	 String name;
//	 ArrayList<Combination> routestop = new ArrayList<>(); // List with Bus Name and its next Stop 
//	 Stop(String name)
//	 {this.name = name;
//	 }
//}
class Combination
{
	String busName;
	Stop nextStop; // next stop for a station
	Stop prevStop; // previous stop of a station
	Combination(String busName, Stop nextStop, Stop prevStop)
	{
		this.busName = busName;
		this.nextStop = nextStop;
		this.prevStop = prevStop;
	}
}
public class Graph
{	
private LinkedHashMap<String, Stop>stoplookup = new LinkedHashMap(); // Holds station name along with reference to the Stop class Object
public LinkedHashMap<String, Stop> getStoplookup() {
	return stoplookup;
}
public void setStoplookup(LinkedHashMap<String, Stop> stoplookup) {
	this.stoplookup = stoplookup;
}

private LinkedHashMap<Stop, List<Combination>> adjVertices = new LinkedHashMap(); // Adjaceny list for each vertex of the graph. Each vertex is a station here 	
public void addStops()
	{
		 JSONParser jsonParser = new JSONParser();
         
	        try
	        {
	        	FileReader reader = new FileReader("routes.json");	// load the data about each bus
	            Object obj = jsonParser.parse(reader);
	            
	            JSONArray stops = (JSONArray) obj;
	            for (int i = 0 ; i < stops.size(); i++)
	            {
	                JSONObject obj1 = (JSONObject) stops.get(i);
	                Iterator<String> itr = obj1.keySet().iterator();
	                while(itr.hasNext())  // Loading data for each bus
	                {
	                	String key = itr.next();
	                	ArrayList<String> val = (ArrayList<String>) obj1.get(key);
	                for(int j=0;j<val.size();j++)
	                {
	                	Stop stop = new Stop(val.get(j));
	                	stoplookup.putIfAbsent(val.get(j).toLowerCase(), stop);
	                	addStop(stop); // adding every stop
	                	
	                }
	      
	                }
	                addCombinations(obj1); // add the arraylist of adjacent stations for each stop
	            }
	        }
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	}
	private void addCombinations(JSONObject obj1)
	{
		Iterator<String> itr = obj1.keySet().iterator();
		while(itr.hasNext())
    {
    	String key = itr.next();
    	ArrayList<String> val = (ArrayList<String>) obj1.get(key);
    	for(int j=0;j<val.size();j++)
    {		
    		if(j==0)
    		{
    	Stop thistop = stoplookup.get(val.get(j).toLowerCase());
    	Stop afterstop = stoplookup.get(val.get(j+1).toLowerCase());
    	Combination combination = new Combination(key,afterstop,null); // The previous stop for an origin station is null
    	adjVertices.get(thistop).add(combination); // add stop to the into adjaceny list
    	continue;
    		}
    if(j==val.size()-1)
    {
    	Stop thistop = stoplookup.get(val.get(j).toLowerCase());
    	Stop beforestop = stoplookup.get(val.get(j-1).toLowerCase());
    	Combination combination = new Combination(key,null,beforestop); // if its an Last stop then next stop is null
    	adjVertices.get(thistop).add(combination); // add stop into adjacency list 
    	continue;
    }
    	Stop thistop = stoplookup.get(val.get(j).toLowerCase());
    	Stop afterstop = stoplookup.get(val.get(j+1).toLowerCase());
    	Stop beforeStop = stoplookup.get(val.get(j-1).toLowerCase());
    	Combination combination = new Combination(key,afterstop,beforeStop); //  Create a combination to add to adjacency list with next and previous stop
    	adjVertices.get(thistop).add(combination); // add stop into adjacency list
    	continue;
    }	
    }	
	}

	void addStop(Stop stop) // Add a vertex into the graph
	{ List<Combination> adjStop = new ArrayList();
		adjVertices.putIfAbsent(stop, adjStop); // add vertex into the adjacency matrix
	}
	private void addEdge(Stop stop, Combination combination)  // add an edge for graph
	{
		System.out.println(adjVertices.get(stop));
		adjVertices.get(stop).add(combination); // add combination into adjacency list
	}
	public HashMap<String, List<String>> search(String origin, String destination)
	{  	Stop originstop = stoplookup.get(origin); // Get the object that points to Origin stop
		Stop destinationstop = stoplookup.get(destination); // Get object that points to the Destination Stop
		HashMap<String, List<String>> routes = new HashMap(); // Get multiple routes to go from stop to destination
		List<Combination> combination = adjVertices.get(originstop);
		List<String> passedStations = new ArrayList<>();
		String busName;
		Stop nextStop;
		Stop middleStop=null;
		int i =0;
		
		for(Combination v : combination) // Try one bus to  check if it goes to the destination
		{
			busName = v.busName;

			nextStop = v.nextStop;	
			if(nextStop == null)
			{break;}
			passedStations.add(nextStop.name);
			if((nextStop.name).equalsIgnoreCase(destinationstop.name))
			{
		    	List<String> route = new ArrayList();
		    	route.addAll(passedStations); // Adding passed stations into the route
		    	routes.putIfAbsent(v.busName, route); // Add the Bus Name and add the route
		    	passedStations.clear();
				break;
			}
			// While condition to find path from origin stop to destination
		    while(!(nextStop.name).equalsIgnoreCase(destinationstop.name)||adjVertices.get(middleStop).isEmpty()== false)
		    {
		    	middleStop = getStop(nextStop,busName,i);
		    if(middleStop!= null)
		    {
		    	if(middleStop.isActive)
		    	{
		    	passedStations.add(middleStop.name);
		    		}
//		    passedStations.add(middleStop.name);
	    	nextStop = middleStop;
		    if((nextStop.name).equalsIgnoreCase( destinationstop.name))
		    { 
		    	List<String> route = new ArrayList();
		    	route.addAll(passedStations); // Adding passed stations into the route
		    	routes.putIfAbsent(v.busName, route); // Add the Bus Name and add the route
		    	passedStations.clear();
		    	break;
		    	
		    }
		    if(adjVertices.get(nextStop).isEmpty()== true)
		    {
		    	passedStations.clear(); // if the next stop is empty and the destination is not reached then this path is not feasible to reach the destination
		    	continue;
		    }
		}
		    if(adjVertices.get(middleStop)== null ||adjVertices.get(middleStop).isEmpty()== true)
		    {
		    break;	 // break if the end of the stop is reached or not 
		    }
		}
	}
		passedStations.clear();
		for(Combination v : combination) // Try one bus to  check if it goes to the destination
		{
			busName = v.busName;

			nextStop = v.prevStop;	
			if(nextStop == null)
			{break;}
			passedStations.add(nextStop.name);
			if((nextStop.name).equalsIgnoreCase(destinationstop.name))
			{
		    	List<String> route = new ArrayList();
		    	route.addAll(passedStations); // Adding passed stations into the route
		    	routes.putIfAbsent(v.busName, route); // Add the Bus Name and add the route
		    	passedStations.clear();
				break;
			}
			// While condition to find path from origin stop to destination
		    while(!(nextStop.name).equalsIgnoreCase(destinationstop.name)||adjVertices.get(middleStop).isEmpty()== false)
		    {
		    	middleStop = getPrevStop(nextStop,busName,i);
		    if(middleStop!= null)
		    {
		    	if(middleStop.isActive)
		    	{
		    	passedStations.add(middleStop.name);
		    		}
//		    passedStations.add(middleStop.name);
	    	nextStop = middleStop;
		    if((nextStop.name).equalsIgnoreCase( destinationstop.name))
		    { 
		    	List<String> route = new ArrayList();
		    	route.addAll(passedStations); // Adding passed stations into the route
		    	routes.putIfAbsent(v.busName, route); // Add the Bus Name and add the route
		    	passedStations.clear();
		    	break;
		    	
		    }
		    if(adjVertices.get(nextStop).isEmpty()== true)
		    {
		    	passedStations.clear(); // if the next stop is empty and the destination is not reached then this path is not feasible to reach the destination
		    	continue;
		    }
		}
		    if(adjVertices.get(middleStop)== null ||adjVertices.get(middleStop).isEmpty()== true)
		    {
		    break;	 // break if the end of the stop is reached or not 
		    }
		}
	}
		return routes; // return all the routes to reach destination 
	}
	private Stop getStop(Stop stop, String busName, int flag) {
	    if (stop == null) {
	        return null; // Base case: stop is null, return null
	    }

	    List<Combination> combination = adjVertices.get(stop);
	    Stop nextStop = null;

	    for (Combination v : combination) {
	        if (v.busName.equals(busName)) {
	            nextStop = v.nextStop; // Found the next stop, update nextStop
	            break;
	        }
	    }

	    if (nextStop == null) {
	        return null; // Base case: nextStop is null, return null
	    }

	    if (flag == 1) {
	        return getStop(nextStop, busName, flag); // Recursively call getStop with nextStop as the new input
	    } else {
	        return nextStop; // Return nextStop without further recursion
	    }
	}
	private Stop getPrevStop(Stop stop, String busName, int flag) {
	    if (stop == null) {
	        return null; // Base case: stop is null, return null
	    }

	    List<Combination> combination = adjVertices.get(stop);
	    Stop nextStop = null;

	    for (Combination v : combination) {
	        if (v.busName.equals(busName)) {
	            nextStop = v.prevStop; // Found the next stop, update nextStop
	            break;
	        }
	    }

	    if (nextStop == null) {
	        return null; // Base case: nextStop is null, return null
	    }

	    if (flag == 1) {
	        return getPrevStop(nextStop, busName, flag); // Recursively call getStop with nextStop as the new input
	    } else {
	        return nextStop; // Return nextStop without further recursion
	    }
	}

	public HashMap<String,String> getStationInfo(Stop getstop) // Returns Info about a single station
	{
		HashMap<String, String> map = new HashMap<>();
		adjVertices.get(getstop);
		List<Combination> combinations = adjVertices.get(getstop);
		for(Combination v : combinations)
		{
			if(v.busName!=null)
			{
				if(v.nextStop!=null)
				{
					map.put(v.busName, v.nextStop.name);
				}
				if(v.prevStop!=null)
				{
					map.put(v.busName,v.prevStop.name);
				}
			}
		}
		return map;
	}
}