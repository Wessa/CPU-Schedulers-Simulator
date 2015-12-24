
public class Process {
	
	String Name;
	double arrivalTime;
	double burstTime;
	double finishTime ;
	
	@Override
	public String toString(){
		
		return Name + " " + arrivalTime + " " + burstTime ;
	}
	
	
}
