import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonitoredData {
	public String startTime;
	public String endTime;
	private String activityLabel;
	
	public MonitoredData (String s, String e, String a){
		startTime = s;
		endTime = e;
		activityLabel = a;
	}
	
	public int returnDay (){
		String number = new StringBuilder().append(startTime.charAt(8)).append(startTime.charAt(9)).toString();
		return Integer.parseInt(number);
	}
	
	public String getAL(){
		return activityLabel;
	}
	
	public Long duration(){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss" );
		LocalDateTime ldt1 = LocalDateTime.parse(startTime,formatter);
		LocalDateTime ldt2 = LocalDateTime.parse(endTime,formatter);
		Duration d= Duration.between(ldt1, ldt2);
	//	System.out.println(d);
		return d.toMillis();
		
	}
}
