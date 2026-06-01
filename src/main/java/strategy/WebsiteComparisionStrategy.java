package strategy;

public interface WebsiteComparisionStrategy {
    boolean hasChanged(String oldContent, String newContent);
}

