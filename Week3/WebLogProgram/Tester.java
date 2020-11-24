package WebLogProgram;


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
    
    public void testLogAnalyzer() 
    {
        LogAnalyzer test = new LogAnalyzer();
        
        test.readFile("short-test_log.txt");
        test.printAll();
    }
    
    public void testUniqueIP()
    {
        LogAnalyzer test = new LogAnalyzer();
        
        test.readFile("/Users/amandeepverma/Downloads/Week3/WebLogProgram/weblog2_log");
        System.out.println("unique ips = "+test.countUniqueIPs());
    }
    
    public void printHigherThanNum()
    {
        LogAnalyzer test = new LogAnalyzer();
        
        test.readFile("/Users/amandeepverma/Downloads/Week3/WebLogProgram/weblog1_log");
        test.printAllHigherThanNum(400);
    }
    
    public void UniqueIpforDay()
    {
        LogAnalyzer test = new LogAnalyzer();
        
        test.readFile("/Users/amandeepverma/Downloads/Week3/WebLogProgram/weblog2_log");
        System.out.println(test.uniqueIPVisitsOnDay("Sep 24").size()); 
        
    }
    
    public void UniqueIpforRange()
    {
        LogAnalyzer test = new LogAnalyzer();
        
        test.readFile("/Users/amandeepverma/Downloads/Week3/WebLogProgram/weblog2_log");
        System.out.println(test.countUniqueIPsInRange(200,299)); 
        
    }
    
    public void testCounts()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("/Users/amandeepverma/Downloads/Week3/WebLogProgram/weblog2_log");
        System.out.println(test.countVisitsPerIP());
        System.out.println("most counts are = "+ test.mostNumberVisitsByIP(test.countVisitsPerIP()));
        System.out.println(test.iPsMostVisits(test.countVisitsPerIP() ));
        System.out.println("ip for the day "+ test.iPsForDays());
        
        System.out.println("day with Most iP visit is "+ test.dayWithMostIPVisits(test.iPsForDays()));
        
        System.out.println("iPsWithMostVisitsOnDay "+ test.iPsWithMostVisitsOnDay(test.iPsForDays(),"Sep 30"));
    }
    
}   
