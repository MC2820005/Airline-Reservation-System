public class Flight {

    private String flight_number;
    private String origin;
    private String destination;
    private double lof;
    private String departure_date;
    private String departure_time;
    private int seats_available;
    private int adult_price;
    private int child_price;

     public Flight(String flight_number,String origin,String destination,double lof,String departure_date,
        String departure_time,int seats_available,int adult_price,int child_price) {
        this.flight_number = flight_number;
        this.origin = origin;
        this.destination = destination;
        this.lof = lof;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.seats_available = seats_available;
        this.adult_price = adult_price;
        this.child_price = child_price;
    }

    // Getter Methods
    public String getFlightNumber() {return flight_number;}
    public String getOrigin() {return origin;}
    public String getDestination() {return destination;}
    public double getLengthOfFlight() {return lof;}
    public String getDepartureDate() {return departure_date;}
    public String getDepartureTime() {return departure_time;}
    public int getSeatsAvailable() {return seats_available;}
    public double getAdultPrice() {return adult_price;}
    public double getChildPrice() {return child_price;}
}
