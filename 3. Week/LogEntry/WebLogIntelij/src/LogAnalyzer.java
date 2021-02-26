import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource resource = new FileResource(filename);
        for(String line: resource.lines()) {
            WebLogParser.parseEntry(line);
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        ArrayList<Integer>statusCode = new ArrayList<Integer>();
        for (LogEntry le: records) {
            int statCode = le.getStatusCode();
            if (statCode > num) {
                System.out.println (statCode);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> myIPs = new ArrayList<String>();
        String myString = null;
        for(LogEntry le: records) {
            Date d = le.getAccessTime();
            String ipAddr = le.getIpAddress();
            myString = d.toString();
            int index = myIPs.indexOf(ipAddr);
            if((myString.contains(someday)) && (!myIPs.contains(ipAddr))){
                myIPs.add(ipAddr);

            }

            for (int k =0; k < myIPs.size();k++) {
                System.out.println(myIPs.get(k)+"\t");
            }

            System.out.println("Array size: "+myIPs.size());
        }
        return myIPs;
    }


    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


}
