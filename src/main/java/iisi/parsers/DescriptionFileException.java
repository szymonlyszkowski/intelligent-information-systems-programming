package iisi.parsers;

public class DescriptionFileException extends RuntimeException{

    public DescriptionFileException() {
        super("First line of description.txt file was found empty. Please provide description.txt " + "file in "
                + "correct form.");
    }
}
