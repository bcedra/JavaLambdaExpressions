import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	private static Map<String, Long> map = new HashMap<String,Long>();
	public static void main (String args[]){
		List<MonitoredData> monitoredData = new ArrayList<MonitoredData>();
		
		try(Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
			monitoredData =
				stream.map(line->{
				String[] s = line.split("		");
		
				return new MonitoredData(s[0], s[1], s[2]);
			}).collect(Collectors.toList());
		}
		catch(IOException e){
			e.printStackTrace();
		}
		//monitoredData.stream().forEach(element->System.out.println(element.returnDay()));
		//acuma calculez zilele distincte folosind lambda 
		int ceva = (int) monitoredData.stream()
				.map(element->element.returnDay())
				.distinct()
				.count();
		System.out.println(ceva);
		
		map = monitoredData.stream()
				.collect(Collectors
						.groupingBy(MonitoredData::getAL,Collectors
								.counting()));
		//map.forEach((k,v)->System.out.println(k+" "+v ));
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("cedra1.txt"))){
			map.entrySet().stream().forEach(element->{
				try {
					bw.write(element.getKey() + " " + element.getValue());
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});}
			catch(IOException e){
				e.printStackTrace();
			}
		
		Map<Integer, Map<String,Long>> map2 = new HashMap<Integer, Map<String,Long>>();
		map2= monitoredData.stream().
				collect(Collectors
						.groupingBy(MonitoredData::returnDay,Collectors
								.groupingBy(MonitoredData::getAL,Collectors
										.counting())));
		//map2.forEach((k,v)->System.out.println(k+" "+v));
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("cedra2.txt"))){
			map2.entrySet().stream().forEach(element->{
				try {
					bw.write(element.getKey() + " " + element.getValue());
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});}
			catch(IOException e){
				e.printStackTrace();
			}
		//monitoredData.stream().forEach(element->System.out.println(element.duration()));
		Map<String, LongSummaryStatistics> map3 = new HashMap<String,LongSummaryStatistics>();
		
		map3 = monitoredData.stream()
				.collect(Collectors
						.groupingBy(MonitoredData::getAL,Collectors
								.summarizingLong(MonitoredData::duration)));
		
		Map<String, Duration> map33 = new HashMap<String,Duration>();
		map3.entrySet().stream().filter(entry->entry.getValue().getSum()/3600000>10).forEach(element->{
			map33.put(element.getKey(), Duration.ofMillis(element.getValue().getSum()));
		});
		//map33.entrySet().stream().forEach(element->System.out.println(element.getKey() + " " + element.getValue().toHours()));
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("cedra3.txt"))){
			map33.entrySet().stream().forEach(element->{
				try {
					bw.write(element.getKey() + " " + element.getValue());
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});}
			catch(IOException e){
				e.printStackTrace();
			}
		List <String> cedra = new ArrayList<String>();
		
		Map<String, Long> aux = monitoredData.stream().filter(element->element.duration()<300000).collect(Collectors.groupingBy(MonitoredData::getAL,Collectors.counting()));
		aux.entrySet().stream().forEach(entry->{
			for(Entry<String, Long> e:map.entrySet()){
				if (entry.getValue().equals(e.getValue())){
					if (entry.getValue()>=90/100*e.getValue()){
						cedra.add(entry.getKey());
					}
				}
			}
		});
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("cedra5.txt"))){
		cedra.stream().forEach(element->{
			try {
				bw.write(element);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
