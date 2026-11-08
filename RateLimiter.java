// below is incomplete check for correctness


enum STRATEGY {
    FIXED_WINDOW = "fixed-window-counter"
    SLIDINGWINDOW = 'sliding-window-counter'
}


public class RateLimiter {

    HashMap<String, StrategyI> resourceStrategry = new HashMap<>();

    public static void main(String[] args) {
        
        
    }

    public void addResource(String resouceId, STRATEGY s, String limitAndTimeWindow) {
        string[] limitAndTimeWindowList = limitAndTimeWindow.split(',');
        int reqLimit = Integer.parase(limitAndTimeWindowList[0]);
        int timeWindow = Integer.parase(limitAndTimeWindowList[1]);
        
        StrategyI s = getStrategyObject(s, reqLimit, timeWindow);
        resourceStrategry.put(resource, s);
    }

    public boolean isAllowed(String resouceId, int time) {
        if (resourceStrategry.get(resouceId) == null) {
            return true;
        }

        StrategyI s = resourceStrategry.get(resouceId); 
        return s.isAllowed(resouceId, time);
    }


    void getStrategyObject(STRATEGY s,  int reqLimit, int windowTime) {
      if (s==STRATEGY.FIXED_WINDOW) {   
        return new FixedWindow(reqLimit, windowTime);
      } else if (s==STRATEGY.SLIDINGWINDOW) {
        return new SlidingWindow(reqLimit, windowTime);
      } 
    }

}

abstract class StrategyI {
    boolean isAllowed(String resouceId, int time);
}


class Node {
    String resourceId;
    int time;
}

class SlidingWindow implements StrategyI {
    Queue<Node> queue = new LinkedList<Node>();
    int reqLimit;
    int windowTime;

    FixedWindow(int reqLimit, int windowTime) {
        this.reqLimit = reqLimit;
        this.windowTime = windowTime;
    }
    
    public boolean isAllowed(String resouceId, int time) {
            if (queue.size() >= reqLimit ) {
                while(!queue.isEmpty() && (time - queue.peek().time > windowTime)) { // check
                    queue.poll();
                }

                return queue.size() < reqLimit;                
            }
            else {
                return true;
            }
    }
}


class FixedWindow implements StrategyI {

    int reqLimit;
    int windowTime;

    SlidingWindow(int reqLimit, int windowTime) {
        this.reqLimit = reqLimit;
        this.windowTime = windowTime;
    }
    
    public boolean isAllowed(String resouceId, int time) {
          // divide time / windowTime  then we get if we are in current window or not. if not increase
    }
}

