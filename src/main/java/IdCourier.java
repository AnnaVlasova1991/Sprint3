public class IdCourier {
    int id;
    int code;
    String message;

    public IdCourier(int id, int code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }


    public IdCourier(int id) {
        this.id = id;
        this.message = null;
    }

    public IdCourier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
