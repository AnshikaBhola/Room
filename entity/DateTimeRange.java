package entity;

import java.time.LocalDateTime;

public class DateTimeRange {
    private LocalDateTime start;
    private LocalDateTime end;

    public DateTimeRange(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) throw new IllegalArgumentException("Start must be before end");
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(DateTimeRange other) {
        return !(this.end.isBefore(other.start) || this.start.isAfter(other.end));
    }

    public long getHours() {
        return java.time.Duration.between(start, end).toHours();
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }

    @Override
    public String toString() {
        return "[" + start + " to " + end + "]";
    }
}
