import java.util.*;

public class Tester
{
    public static void main(String[] args) {

        Tester test = new Tester();
        test.testLogEntry();
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
        System.out.println(uniqueip);

    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer ah = new LogAnalyzer();
        ah.readFile("weblog3-short_log");
        int g = 50;
        ah.printAllHigherThanNum(g);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer uipod = new LogAnalyzer();
        uipod.readFile("weblog3-short_log");
        int sizeArray = uipod.uniqueIPVisitsOnDay("Sep 24").size();
        System.out.println(uipod.uniqueIPVisitsOnDay("Sep 24") + "\n " + "size is:" + sizeArray);
    }

}