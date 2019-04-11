
实现一个类:

```$xslt

public class ExceptionTest1 {

    void throwException() {
        if (true) {
            throw new RuntimeException("Hello World");
        }
    }

    void tryCatch(boolean throwFlag) {

        try {
            if (throwFlag) {
                throwException();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```