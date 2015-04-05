package ca.carleton.comp3005;

public interface DialogClient {

    public static enum operation {
        UPDATE,
        DELETE,
    }

    public void dialogFinished(operation anOperation);

    public void dialogCancelled();
}