package strategy;

public class HtmlContentComparision implements WebsiteComparisionStrategy {

    @Override
    public boolean hasChanged(String oldContent, String newContent) {
        if(!oldContent.equals(newContent)) {
            return true;
        }
        return false;
    }
}
