import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

    public int countUniqueIPs() { //This method return an integer representing of unique IP addresses
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {  //This method examine all the records and print which have
        // a status code greater than num.
        ArrayList<Integer>statusCode = new ArrayList<Integer>();
        for (LogEntry le: records) {
            int statCode = le.getStatusCode();
            if (statCode > num) {
                statusCode.add(le.getStatusCode());
            }
        }
        System.out.println("Higher status codes than " + num + " are numbers: " + statusCode);
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){  //This method return list
        // of IP's visited on a given day.
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

        }
        return myIPs;
    }

    public void countUniqueIPsInRange(int low, int high) { // This method print IP's visited between a given status code.
        ArrayList<String>uniqueIPsInRange = new ArrayList<>();
        int countIp = 0;
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high && !uniqueIPsInRange.contains(ipAddress)) {
                uniqueIPsInRange.add(ipAddress);
            }
        }
        System.out.println("Unique IPs in range is: " + uniqueIPsInRange.size());
    }
    public HashMap<String, Integer> countVisitsPerIP() {    //This method count how many times IP appears in the weblog file.
        HashMap<String, Integer> countVisit = new HashMap<String, Integer>();
        for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
            if (!countVisit.containsKey(ipAddress)) {
                countVisit.put(ipAddress, 1);
            } else {
                countVisit.put(ipAddress, countVisit.get(ipAddress) + 1);
            }
        }
        return countVisit;


    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> IP) { // This method return maximum appear of IP
        //in weblog file.
       int max = 0;
        for (String key : IP.keySet()) {
            int currValue = IP.get(key);
            if (currValue > max) {
                max = currValue;
            }
        }
        return max;
    }

    public ArrayList iPsMostVisits(HashMap<String, Integer> ip) { //This method return number of IP which appear
        // in the weglog file the most common.
        ArrayList<String> iPList = new ArrayList<>();
        HashMap<String, Integer> iPMap;
        iPMap = countVisitsPerIP();
        int max = mostNumberVisitsByIP(iPMap);
        for (String key : iPMap.keySet()) {
            if (iPMap.get(key).equals(max)) {
                iPList.add(key);
            }
        }

        return iPList;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        //This method return map of days and IP addresses that occurred on the day.
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (LogEntry le : records) {
            ArrayList<String> IP = new ArrayList<>();
            String ipAddress = le.getIpAddress();
            String date = le.getAccessTime().toString();
            date = date.substring(4, 10);
            if (!map.containsKey(date)) {
                IP.add(ipAddress);
                map.put(date, IP);
            } else {
                ArrayList<String> tempIP = map.get(date);
                tempIP.add(ipAddress);
                map.put(date, tempIP);
            }
        }
        return map;

    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
        //Thi method return the day when was most visited in weblog file.
        String day ="";
        int max =0;
        for(String key : map.keySet()){
            int size = map.get(key).size();
            if(size > max){
                max = size;
                day = key;
            }
        }
        return day;
    }
    public ArrayList iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day){
        //This method return IP which was the common visited on a given day.
        ArrayList<String> list = map.get(day);
        HashMap<String, Integer> countIP = new HashMap<String, Integer>();
        int maxCount = 0;
        for (int count : countIP.values())
            if (count > maxCount) maxCount = count;

        // fill output list
        for (String ip : countIP.keySet())
            if (countIP.get(ip) == maxCount) {
                list.add(ip);
            }
        return list;
    }

    public void printAll() {  //This method print all records from weblog file
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
