package strategy;

public class ContentSizeComparision implements WebsiteComparisionStrategy {
    @Override
    public boolean hasChanged(String oldContent, String newContent) {
        if(oldContent.length() != newContent.length()) {
            return true;
        }
        return false;
    }
}
