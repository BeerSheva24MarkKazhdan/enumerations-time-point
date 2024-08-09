package telran.time;

public class TimePoint implements Comparable<TimePoint>{
    private float amount;
    private TimeUnit timeUnit;
    public TimePoint(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }
    public float getAmount(){
        return amount;
    }
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
    @Override
    public int compareTo(TimePoint arg0) {
        float amountInSeconds = this.convert(TimeUnit.SECOND).getAmount();
        float arg0AmountInSeconds = arg0.convert(TimeUnit.SECOND).getAmount();

        return Float.compare(amountInSeconds, arg0AmountInSeconds);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TimePoint newTimePoint = (TimePoint) obj;
        float amountInSeconds = this.convert(TimeUnit.SECOND).getAmount();
        float newAmountInSeconds = newTimePoint.convert(TimeUnit.SECOND).getAmount();

        return Float.compare(amountInSeconds, newAmountInSeconds) == 0;
    }
      
    public TimePoint convert(TimeUnit timeUnit) {
        //returns new TimePoint object equaled to the "this" object but
        //with a given timeUnit
        float newAmount = switch(this.timeUnit){
            case SECOND -> switch (timeUnit) {
                case SECOND -> getAmount(); 
                case MINUTE -> getAmount() / 60;
                case HOUR -> getAmount() / 3600;
                default -> throw new IllegalArgumentException("Unsupported TimeUnit: " + timeUnit);
            };
            case MINUTE -> switch (timeUnit) {
                case SECOND -> getAmount() * 60;
                case MINUTE -> getAmount();
                case HOUR -> getAmount() / 60;
                default -> throw new IllegalArgumentException("Unsupported TimeUnit: " + timeUnit);
            };
            case HOUR -> switch(timeUnit){
                case SECOND -> getAmount() * 3600;
                case MINUTE -> getAmount() * 60;
                case HOUR -> getAmount();
                default -> throw new IllegalArgumentException("Unsupported TimeUnit: " + timeUnit);
            };
};
return new TimePoint(newAmount, timeUnit);
    }

    public TimePoint with(TimePointAdjuster adjuster) {
        return adjuster.adjust(this);
    }

}