package Model;

import Model.Utils.SeverityNivel;

public abstract class Emergency {

    private String type;
    private String location;
    private SeverityNivel severity; // Alto, Medio, Bajo
    private int responseTime; // Tiempo de respuesta en minutos
    private boolean attended;
    private long startTime;
    private long endTime;

    public Emergency(String type, String location, SeverityNivel severity, int responseTime) {
        this.type = type;
        this.location = location;
        this.severity = severity;
        this.responseTime = Math.max(responseTime, 0);
        this.attended = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SeverityNivel getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityNivel severity) {
        this.severity = severity;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public boolean attended() {
        return attended;
    }

    public void isAttended(boolean isAtended) {
        this.attended = isAtended;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void startAttention() {
        this.startTime = System.currentTimeMillis();
    }

    public void endAttention() {
        this.endTime = System.currentTimeMillis();
        this.attended = true;
    }

    public long calculateAttentionTime() {
        return this.endTime - this.startTime;
    }

    public String getDescription() {
        return String.format("%s en %s (gravedad: %s)", type, location, severity);
    }

    public String toString() {
        return getDescription() + " - Tiempo de respuesta: " + responseTime + " minutos";
    }

}
