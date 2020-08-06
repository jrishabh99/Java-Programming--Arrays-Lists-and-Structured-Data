
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("Number of Unique IPs: "+la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int num=200;
        System.out.println("Unique IPs with status code greater than "+num+":");
        la.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String day = "Sep 27";
        System.out.println("The unique IPs on day "+day+": ");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay(day);
        for(String ip : uniqueIPs)
            System.out.println(ip);
    }
    
    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200, high = 299;
        int count = la.countUniqueIPsInRange(low,high);
        System.out.println("Unique IPs with status code in range "+low+" and "+high+" is "+count);
    }
    
    public void testCountVisitsPerIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> map = la.countVisitsPerIP();
        System.out.println("count of visits by IP Adrdress:");
        for(String key : map.keySet())
        {
            System.out.println(key+" : "+map.get(key));
        }
    }
    
    public void testMostNumberVisitsByIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> map = la.countVisitsPerIP();
        int count = la.mostNumberVisitsByIP(map);
        System.out.println("Most visits by any IP: "+count);
    }
    
    public void testIPsMostVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> map = la.countVisitsPerIP();
        System.out.println("Most visits by IPs: ");
        ArrayList<String> ips = la.iPsMostVisits(map);
        for(String ip : ips)
        {
            System.out.println(ip);
        }
    }
    
    
    public void testIPsForDays()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        for(String key : map.keySet())
        {
            System.out.println("Visits on "+key);
            for(String ip : map.get(key))
                System.out.println(ip);
        }
    }
    public void testDayWithMostIPVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        String day = la.dayWithMostIPVisits(map);
        System.out.println("Day with most IP visits :"+day);
    }
    
    public void testIPsWithMostVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        String day = "Sep 29";
        System.out.println("IP with most visits on day "+day);
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(map,day);
        for(String ip : ips)
            System.out.println(ip);
    }
}
