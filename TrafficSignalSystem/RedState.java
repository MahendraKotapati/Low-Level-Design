public class RedState implements SignalState {

    @Override
    public String getName() {
        return "RED";
    }

    @Override 
    public void handle(TrafficLight light, TrafficSignalSystemController controller, Direction direction) {
        System.out.println("Direction: " + direction + " | State: RED");
        // if we reach here it means one cycle is completed so, after RED move to next direction's GREEN
        TrafficLight nextDirectionLight = controller.getTrafficLight(controller.getNextDirection(direction));
        int waitDuration = controller.getSignalDuration(direction, this);

        controller.scheduleStateChange(waitDuration, nextDirectionLight, new GreenState());
    }
}
