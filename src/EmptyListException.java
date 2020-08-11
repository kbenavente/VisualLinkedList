public class EmptyListException extends RuntimeException {

    private String methodName;

    public EmptyListException() {

        super("The list is empty.");
        this.methodName = "";

    }

    public EmptyListException(String methodName) {

        super("Cannot execute the method \"" + methodName + "\" because the list is empty.");
        this.methodName = methodName;

    }

    public String getMethodName() {

        return this.methodName;

    }

}
