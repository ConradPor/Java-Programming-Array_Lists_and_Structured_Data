import java.util.*;

public class Tester
{
    public static void main(String[] args) {

        Tester test = new Tester();
        test.testLogEntry();
        test.testPrintAllHigherThanNum();
        test.testUniqueIP();
        test.testUniqueIPVisitsOnDay();
        test.testCountsVisitPerIP();
        test.testCountUniqueIpInRange();
        test.testIPsForDays();
        test.testDayWIthMostIpVisits();
        test.testIPsMostVisits();
        test.testMostNumberVisitsByIP();
        test.testLogAnalyzer();

    }


    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer myLog = new LogAnalyzer();
        myLog.readFile("weblog3-short_log");
        myLog.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer uip = new LogAnalyzer();
        uip.readFile("weblog3-short_log");
        int uniqueip = uip.countUniqueIPs();
        System.out.println("Number of unique IP's in the file: " + uniqueip);

    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer ah = new LogAnalyzer();
        ah.readFile("weblog3-short_log");
        ah.printAllHigherThanNum(50);

    }



    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer uipod = new LogAnalyzer();
        uipod.readFile("weblog3-short_log");
        int sizeArray = uipod.uniqueIPVisitsOnDay("Sep 30").size();
        System.out.println("This IP's was visited 30th of September: " + (uipod.uniqueIPVisitsOnDay("Sep 30")) + "\n" + "Number of IP's visited 30th of September: " + sizeArray);
    }

    public void testCountUniqueIpInRange() {
        LogAnalyzer cuiir = new LogAnalyzer();
        cuiir.readFile("weblog2_log");
        cuiir.countUniqueIPsInRange(100, 400);
    }

    public void testCountsVisitPerIP(){
        LogAnalyzer cv = new LogAnalyzer();
        cv.readFile("weblog1_log");
        HashMap<String,Integer> counts = cv.countVisitsPerIP();
        System.out.println("IP's was visited: " + counts);
    }
    public void testMostNumberVisitsByIP(){
        LogAnalyzer mn = new LogAnalyzer();
        mn.readFile("weblog2_log");
        HashMap<String,Integer> counts = mn.countVisitsPerIP();

        int max = mn.mostNumberVisitsByIP(counts);
        System.out.println("This IP " +  mn.iPsMostVisits(counts) + " was visited " + max + " times.");
    }
    public void testIPsMostVisits(){
        LogAnalyzer mv = new LogAnalyzer();
        mv.readFile("weblog2_log");
        HashMap<String,Integer> counts = mv.countVisitsPerIP();
        System.out.println("The most visits IP's is: " + mv.iPsMostVisits(counts));
    }
    public void testIPsForDays(){
        LogAnalyzer ifd = new LogAnalyzer();
        ifd.readFile("weblog3-short_log");
        System.out.println(ifd.iPsForDays());
    }
    public void testDayWIthMostIpVisits(){
        LogAnalyzer dwm = new LogAnalyzer();
        dwm.readFile("weblog2_log");
        String day = dwm.dayWithMostIPVisits(dwm.iPsForDays());
        System.out.println("The day with the most IP visit is " + day);
    }
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer iwm = new LogAnalyzer();
        iwm.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = iwm.iPsForDays();
        // ArrayList<String> IP = cc.iPsWithMostVisitsOnDay(map,"Mar 17");
        //System.out.println(IP);
        System.out.println(iwm.iPsWithMostVisitsOnDay(map,"Sep 30"));

    }

}