
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
        la.readFile("weblog2_log");
        //la.printAll();
        //la.countUniqueIPs();
        //la.printAllHigherThanNum(400);
        //la.uniqueIPVisitsOnDay("Mar 17");
        //la.countUniqueIPsInRange(200,299);
        //System.out.println(la.countVisitsPerIP());
        //System.out.println("Most visits from one ip: "+la.mostNumberVisitsByIP(la.countVisitsPerIP()));
       
        
        la.countUniqueIPs();
        la.uniqueIPVisitsOnDay("Sep 27");
        la.countUniqueIPsInRange(400,499);
        System.out.println("Most visits from one ip: "+la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        la.dayWithMostIPVisits();
        la.uniqueIPVisitsOnDay("Sep 30");
        
    }
}
