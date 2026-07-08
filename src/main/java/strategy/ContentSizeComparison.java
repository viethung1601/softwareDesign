package strategy;

public class ContentSizeComparison implements WebsiteComparisonStrategy {
    @Override
    public boolean hasChanged(String oldContent, String newContent) {
        if(oldContent.length() != newContent.length()) {
            return true;
        }
        return false;
    }
}
