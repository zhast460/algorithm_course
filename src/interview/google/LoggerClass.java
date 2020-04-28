package interview.google;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// given a logger class, implement two methods:
//   start(int jobId, int startTime)
//   end(int jobId, int endTime)
//
// when end a job, need print the job like "Job #id started at #startTime, finished at #endTime.",
// if the job has a job started earlier than itself not yet finished, do not print the log,
// wait till all its preceding job finish, then print it. print jobs in ascending startTime.
public class LoggerClass {
    Queue<Integer> queue;
    Map<Integer, Integer[]> map;

    public LoggerClass(){
        queue = new LinkedList<>();
        map = new HashMap<>();
    }

    public void start(int jobId, int startTime) {
        Integer[] time = new Integer[2];
        time[0] = startTime;
        map.put(jobId, time);
        queue.offer(jobId);
    }

    public void end(int jobId, int endTime) {
        map.get(jobId)[1] = endTime;
        while (!queue.isEmpty() && map.get(queue.peek())[1] != null) {
            print(queue.poll());
        }
    }

    private void print(int jobId) {
        System.out.printf("Job %d started at %d, finished at %d.%n", jobId, map.get(jobId)[0], map.get(jobId)[1]);
        map.remove(jobId);
    }

    public static void main(String[] args) {
        LoggerClass lc = new LoggerClass();
        lc.start(1, 0);
        lc.start(2, 1);
        lc.end(1, 2);
        lc.start(3, 3);
        lc.end(3, 4); // get blocked by job 2
        lc.end(2, 5);
    }
}
