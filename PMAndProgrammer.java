/*

    PM proposes Ideas with time, priority;
    Programmers realize each of them;
    report the time each Idea is realized.

*/
import java.util.*;
import java.io.*;

public class PMAndProgrammer {
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      int n = in.nextInt();
      int m = in.nextInt();
      int p = in.nextInt();
      
      // construct ideas
      Idea[] ideas = new Idea[p];
      for (int i = 0; i < p; i++) {
         ideas[i] = new Idea(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
      }
      PriorityQueue<Idea> unproposed = new PriorityQueue<>(
         new Comparator<Idea>(){
            public int compare(Idea i1, Idea i2) {
               return i1.when - i2.when;
            }
         });
      for (int i = 0; i < p; i++) {
         unproposed.add(ideas[i]);
      }
      PriorityQueue<Idea> unrealized = new PriorityQueue<>(
         new Comparator<Idea>(){
            public int compare(Idea i1, Idea i2) {
               if (i1.pri != i2.pri) {
                  return i2.pri - i1.pri;
               } else if (i1.time != i2.time) {
                  return i1.time - i2.time;
               } else if (i1.when != i2.when) {
                  return i1.when - i2.when;
               } else {
                  return i1.pm - i2.pm;
               }
            }
         });
      
      // construct programmers
      Programmer[] prgs = new Programmer[m];
      for (int i = 0; i < m; i++) {
         prgs[i] = new Programmer();
      }
      
      int currTime = 0;
      
      while (!unproposed.isEmpty() || !unrealized.isEmpty()) {
         currTime ++;
         // add idea to pq
         while (!unproposed.isEmpty() && unproposed.peek().when <= currTime) {
            unrealized.add(unproposed.poll());
         }
         // work on idea
         for (Programmer prog: prgs) {
            prog.work(currTime);
         }
         // pick another idea
         for (Programmer prog: prgs) {
            if (!unrealized.isEmpty() && prog.workingOn == null) {
               prog.pick(unrealized.poll(), currTime);
            }
         }
         
      }
      // finish remaining work
      for (Programmer prog: prgs) {
         prog.finishWork();
      }
      
      for (Idea idea : ideas) {
         System.out.println(idea.finished);
      }
   }
}

class Idea {
   int pm;
   int when;
   int pri;
   int time;
   int finished;
   
   public Idea(int pm, int when, int pri, int time) {
      this.pm = pm;
      this.pri = pri;
      this.when = when;
      this.time = time;
   }
   
   public void finish(int time) {
      finished = time;
   }
   
   public String toString() {
      return when + " " + pri + " " + time + " " + finished;
   }
}

class Programmer {
   Idea workingOn;
   int startTime;
   
   public void work(int time) {
      if (workingOn != null) {
         if (time - startTime == workingOn.time) {
            workingOn.finish(time);
            workingOn = null;
            startTime = -1;
         }
      }
   }
   
   public void pick(Idea idea, int time) {
      startTime = time;
      workingOn = idea;
   }
   
   public void finishWork() {
      if (workingOn != null) {
         workingOn.finish(startTime + workingOn.time);
      }
   }
}