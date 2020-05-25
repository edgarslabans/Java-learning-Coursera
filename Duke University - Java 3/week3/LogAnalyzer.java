
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         // complete constructor
     }
     
     public void readFile(String filename) {
         FileResource resource = new FileResource(filename);
         for(String cont : resource.lines()){
             LogEntry le = WebLogParser.parseEntry(cont);
             records.add(le);
            }
     }
     public void countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry ips : records){
             String ip = ips.getIpAddress();
             if (!uniqueIps.contains(ip)){
             uniqueIps.add(ip);
            }
         }
         System.out.println("The file contains "+uniqueIps.size()+ " unique ip adresses");
     }
     public void printAllHigherThanNum(int num){        //prints ipswith status code greater than num
         System.out.println("Log entries withstatus greater than  "+num);
         for(LogEntry entry : records){
            int code = entry.getStatusCode();
             if (code > num){
             System.out.println(entry.toString());  
            }
         }
     }
     public void uniqueIPVisitsOnDay (String someday){        //prints ipswith status code greater than num
         System.out.println("Log entries at "+someday);
         ArrayList<String> uniqueIps = new ArrayList<String>();
         HashMap<String,Integer> map = new HashMap<String,Integer>();
         int sum = 0;
         for(LogEntry entry : records){
            String date = entry.getAccessTime().toString(); 
            String ip = entry.getIpAddress();
             if (date.indexOf(someday) != -1 && !uniqueIps.contains(ip)){
                //System.out.println(entry.toString());
                uniqueIps.add(ip);
                sum++; 
            } else if(date.indexOf(someday) != -1){
               if (map.keySet().contains(ip)){
                    map.put(ip,map.get(ip)+1);
                }else {
                    map.put(ip,1);
               }
            }
         }
         System.out.println(map);
         System.out.println("In total  "+sum+" entries at "+someday+" with "+uniqueIps.size()+ " unique ip adresses");
         System.out.println(mostNumberVisitsByIP(map));
     }     
     
     public void countUniqueIPsInRange(int min, int max){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         System.out.println("Unique ip`s ses in the range of "+min+"-"+max);
         for(LogEntry ips : records){
             String ip = ips.getIpAddress();
             int code = ips.getStatusCode();
             if (!uniqueIps.contains(ip) && code >= min && code <= max){
             uniqueIps.add(ip);
             //System.out.println(ips.toString());
            }
         } 
         System.out.println("The file contains "+uniqueIps.size()+ " unique ip adresses in the range of "+min+"-"+max);
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        for(LogEntry ips : records){
            String ip = ips.getIpAddress();
            if (map.keySet().contains(ip)){
                map.put(ip,map.get(ip)+1);
            }else {
                map.put(ip,1);
            }
        }
        return map;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> map){
        int maxValue = 0;
        String mKey = "";
         for (String name: map.keySet()){
            String key = name.toString();
            int value = map.get(name);  
            if(value > maxValue){
                maxValue = value;
                mKey = key;
            }
        }
        System.out.println("The most popular entry: "+mKey+ " appears "+maxValue+" times.");
        return maxValue;
     }
     
     
     
     public void dayWithMostIPVisits(){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int sum = 0;
        for(LogEntry entry : records){
            String date = entry.getAccessTime().toString(); 
            date = date.substring(4,10);
            
            String ip = entry.getIpAddress();
            sum++;
            if (map.keySet().contains(date)){
                map.put(date,map.get(date)+1);
            }else {
                map.put(date,1);
            }
         } 
        System.out.println("date map: "+map);
        System.out.println(mostNumberVisitsByIP(map));
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le.toString());
         }
     }
     
     
}
