package strategy;

public class HtmlContentComparison implements WebsiteComparisonStrategy {

    @Override
    public boolean hasChanged(String oldContent, String newContent) {
        if(!oldContent.equals(newContent)) {
            return true;
        }
        return false;
    }
}
