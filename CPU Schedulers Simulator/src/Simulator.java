import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Simulator {

	public static void main(String[] args) {
		
		//*********Taking PreInput*************//
		
	      Scanner in = new Scanner(System.in);
	      
	      System.out.print("Enter # Of Processes: ");
	      int NumberofProcesses = in.nextInt();
	      
	      System.out.print("Enter RR Time Quantum: ");
	      int TimeQuantum = in.nextInt();
	      
	    //*******Processes information********//
	      
	      ArrayList<Process> queue = new ArrayList<Process>();
	      
	      System.out.println( "Now Enter Processes Details\n(Name - Arrival Time - Burst Time ) in Order" );
	      System.out.println( "**************************************************\n\n" );
	      
	      for ( int i=0 ; i<NumberofProcesses ; i++){
	    	  
	    	  Process tmp = new Process() ;
	    	  System.out.println( "Process#"+ (i+1) +": " );
	    	  System.out.println( "Name: " );
	    	  tmp.Name = in.next();
	    	  System.out.println("arrivalTime: ");
	    	  tmp.arrivalTime = in.nextDouble();
	    	  System.out.println("burstTime: ");
	    	  tmp.burstTime = in.nextDouble();
	    	  
	    	  
	    	  queue.add( tmp );
	      }
	      
	      SJF ( queue ) ;
	      
	      in.close();
	    
	}
	
	public static void SJF ( ArrayList<Process> q ){
		
		ArrayList<Process> tempQueue = new ArrayList<Process>(q) ;
		
		double currTime = 0 , avgRT = 0 ;
		int i = 0 ;
		String execOrder = "" ;
		
		// Sort Queue according to arrival time & if arrival times are equal sort according to burst time
		Collections.sort( tempQueue, new Comparator<Process>(){
			
	        public int compare(Process x, Process y) {
	        	
	        	if ( (int)( x.arrivalTime - y.arrivalTime ) == 0 )
	        		return (int)( x.burstTime - y.burstTime ) ;
	        	
	        	return (int)( x.arrivalTime - y.arrivalTime );
	        }
	    });
		
		// Set CurrTime with the arrival time of first process
		
		currTime = tempQueue.get(0).arrivalTime ;
		
		// The First Process enters and then removed from the queue
		
		currTime += tempQueue.get(0).burstTime ;
		tempQueue.get(0).finishTime = currTime ; 
		execOrder += tempQueue.get(0).Name ;
		tempQueue.remove(0) ;
		
		// Sort Queue according to burst time after the completion of the first process
		
		Collections.sort( tempQueue, new Comparator<Process>(){
	        public int compare(Process x, Process y) {
	            return (int)( x.burstTime - y.burstTime );
	        }
	    }
		);
		
		// choose the next process if it's arrival time < current time
		
		while ( tempQueue.size() != 0 ){
			
			if ( tempQueue.get(i).arrivalTime <= currTime ){
				
				currTime += tempQueue.get(i).burstTime ;
				tempQueue.get(i).finishTime = currTime ; 
				execOrder += " , " + tempQueue.get(i).Name ;
				tempQueue.remove(i) ;
				i = 0 ;
			}
			
			else 
				i ++ ;
		}
		
		//********************** Output Section ********************//
		
		System.out.println ( "Execution Order : " + execOrder ) ;
		System.out.println ( "Response Time : " ) ;
		
		for ( int j=0 ; j<q.size(); j++ ){
			
			avgRT += q.get(j).finishTime - q.get(j).arrivalTime ;
			
			System.out.println ( q.get(j).Name + " = " + 
								(q.get(j).finishTime - q.get(j).arrivalTime) ) ;
			
		}
		
		System.out.println ( "Average Response Time = " + (avgRT / q.size()) ) ;
		
	}
}
