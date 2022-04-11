public class TrackOrder {
    int track;
    int code;
    String message;

    public TrackOrder(int track, int code, String message) {
        this.track = track;
        this.code = code;
        this.message = message;
    }

    public TrackOrder(int track) {
        this.track = track;
        this.message = null;
    }

    public TrackOrder() {
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
