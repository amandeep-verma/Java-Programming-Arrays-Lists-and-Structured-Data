package WebLogProgram;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String s : fr.lines()) {
            records.add(WebLogParser.parseEntry(s));
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for(LogEntry re :records)
        {
            String currIP = re.getIpAddress();
            if(!uniqueIP.contains(currIP))
            {
                uniqueIP.add(currIP);
            }
        }
        return uniqueIP.size();
    }

    public void printAllHigherThanNum(int num)
    {
        for(LogEntry re :records)
        {
            if(re.getStatusCode()>num)
            {
                System.out.println(re);
            }
        }
    }

    public ArrayList uniqueIPVisitsOnDay(String someday) 
    {
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for(LogEntry re :records)
        {
            String currDate = re.getAccessTime().toString().substring(4,10);
            if (currDate.equals(someday))
            {
                String currIP = re.getIpAddress();
                if(!uniqueIP.contains(currIP))
                {
                    uniqueIP.add(currIP);
                }
            }

        }
        return uniqueIP;
    }

    public int countUniqueIPsInRange(int low,int high)
    {
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for(LogEntry re :records)
        {
            String currIP = re.getIpAddress();
            if(re.getStatusCode()>= low && re.getStatusCode()<=high && !uniqueIP.contains(currIP))
            {
                uniqueIP.add(currIP);
            }
        }
        return uniqueIP.size();
    }

    public HashMap<String,Integer> countVisitsPerIP()
    {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry le: records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip))
            {
                counts.put(ip,1);
            }
            else
            {
                int count = counts.get(ip);
                counts.put(ip,count+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts)
    {
        int max = 0;
        for(String s: counts.keySet())
        {
            if(max<counts.get(s))
            {
                max = counts.get(s);
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts)
    {
        int maxVisit = mostNumberVisitsByIP(counts);
        ArrayList<String> elements = new ArrayList<String>();
        for(String s: counts.keySet())
        {
            if(maxVisit==counts.get(s))
            {
                elements.add(s);
            }
        }
        return elements;

    }

    public HashMap<String, ArrayList<String>> iPsForDays()
    {
        HashMap<String, ArrayList<String>> collector = new HashMap<String, ArrayList<String>>();

        for(LogEntry re :records)
        {
            String currDate = re.getAccessTime().toString().substring(4,10);
            if(!collector.containsKey(currDate))
            {
                ArrayList<String> curr = new ArrayList<String>();
                curr.add(re.getIpAddress());
                collector.put(currDate,curr);
            }
            else
            {
                ArrayList<String> curr = collector.get(currDate);
                curr.add(re.getIpAddress());
                collector.put(currDate,curr);
            }
        }
        return collector;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts)
    {
        int maxDays =0;
        String maxDate = null;
        for(String s:counts.keySet())
        {
            if(maxDays<counts.get(s).size())
            {
                maxDays = counts.get(s).size();
                maxDate=s;
            }
        }
        return maxDate;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String date)
    {
        ArrayList<String> curr = null;
        for(String s:counts.keySet())
        {
            if(s.equals(date))
            {
                curr = counts.get(s);
                break;
            }
        }
        
        HashMap<String,Integer> ab = new HashMap<String,Integer>();
        for(String s: curr)
        {
            if(!ab.containsKey(s))
            {
                ab.put(s,1);
            }
            else
            {
                ab.put(s,1+ ab.get(s));
            }
        }
        
        int maxDays = mostNumberVisitsByIP(ab);
        
        curr = new ArrayList<String>();
        for(String s : ab.keySet())
        {
            if(ab.get(s)== maxDays)
            {
                curr.add(s);
            }
        }
        
        return curr;
    }

}
