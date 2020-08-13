package abc.eims.exception;

/**
 * @author wangzhe
 * @date 2020/8/8 12:40
 */
public class CustomException extends Exception{
    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
