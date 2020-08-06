
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
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines())
         {
             records.add(WebLogParser.parseEntry(line));
         }
     }
     public int countUniqueIPs()
     {
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for(LogEntry e : records)
         {
             String ip = e.getIpAddress();
             if(!uniqueIPs.contains(ip))
                uniqueIPs.add(ip);
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num)
     {
         for (LogEntry e : records) {
             if(e.getStatusCode() > num)
                System.out.println(e);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for(LogEntry e : records)
         {
             Date d = e.getAccessTime();
             String day = d.toString().substring(4,10);
             if(someday.equals(day) && !uniqueIPs.contains(e.getIpAddress()))
                uniqueIPs.add(e.getIpAddress());
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high)
     {
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for(LogEntry e : records)
         {
             int code = e.getStatusCode();
             if(code >= low && code <= high && !uniqueIPs.contains(e.getIpAddress()))
                uniqueIPs.add(e.getIpAddress());
         }
         
         return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP()
     {
         HashMap<String,Integer> myMap = new HashMap<>();
         for(LogEntry e : records)
         {
             String ip = e.getIpAddress();
             myMap.put(ip,myMap.getOrDefault(ip,0)+1);
         }
         return myMap;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> map)
     {
         int max=-1;
         for(String key : map.keySet())
         {
             int visits = map.get(key);
             if(visits > max)
             {
                 max = visits;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map)
     {
         ArrayList<String> ips = new ArrayList<>();
         int max = mostNumberVisitsByIP(map);
         for(String key : map.keySet())
         {
             if(map.get(key) == max)
                ips.add(key);
         }
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays()
     {
         HashMap<String, ArrayList<String>> map = new HashMap<>();
         for(LogEntry e : records)
         {
             Date d = e.getAccessTime();
             String day = d.toString().substring(4,10);
             if(!map.containsKey(day))
                map.put(day,new ArrayList<>());
             ArrayList<String> temp = map.get(day);
             temp.add(e.getIpAddress());
             map.put(day,temp);
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map)
     {
         String day="";
         int max=0;
         for(String key : map.keySet())
         {
             int iPCount = map.get(key).size();
             if(iPCount > max)
             {
                 max = iPCount;
                 day = key;
             }
         }
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day)
     {
         ArrayList<String> ips = new ArrayList<>();
         ArrayList<String> temp = map.getOrDefault(day,new ArrayList<String>());
         HashMap<String, Integer> ipCount = new HashMap<>();
         for(String ip : temp)
            ipCount.put(ip, ipCount.getOrDefault(ip,0)+1);
         return iPsMostVisits(ipCount);
     }
     
     public void printAll() {
         for (LogEntry e : records) {
             System.out.println(e);
         }
     }
     
     
}
