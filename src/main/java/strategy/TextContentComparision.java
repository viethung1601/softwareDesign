package strategy;

public class TextContentComparision implements WebsiteComparisionStrategy{

    @Override
    public boolean hasChanged(String oldContent, String newContent) {
        String oldText = oldContent.replaceAll("<[^>]*>", "");
        String newText = newContent.replaceAll("<[^>]*>", "");

        if(!oldText.equals(newText)){
            return true;
        }
        return false;
    }
}
