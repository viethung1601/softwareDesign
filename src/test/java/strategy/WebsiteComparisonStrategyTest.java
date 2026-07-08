package strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebsiteComparisonStrategyTest {

    @Test
    void contentSizeComparisonReturnsFalseForSameSizeAndSameContent() {
        ContentSizeComparison strategy = new ContentSizeComparison();

        boolean result = strategy.hasChanged("abc", "abc");

        assertFalse(result);
    }

    @Test
    void contentSizeComparisonReturnsFalseForSameSizeButDifferentContent() {
        ContentSizeComparison strategy = new ContentSizeComparison();

        boolean result = strategy.hasChanged("abc", "xyz");

        assertFalse(result);
    }

    @Test
    void contentSizeComparisonReturnsTrueForDifferentSize() {
        ContentSizeComparison strategy = new ContentSizeComparison();

        boolean result = strategy.hasChanged("abc", "abcd");

        assertTrue(result);
    }

    @Test
    void contentSizeComparisonReturnsFalseForTwoEmptyStrings() {
        ContentSizeComparison strategy = new ContentSizeComparison();

        boolean result = strategy.hasChanged("", "");

        assertFalse(result);
    }

    @Test
    void contentSizeComparisonReturnsTrueForEmptyAndNonEmptyContent() {
        ContentSizeComparison strategy = new ContentSizeComparison();

        boolean result = strategy.hasChanged("", "abc");

        assertTrue(result);
    }

    @Test
    void htmlContentComparisonReturnsFalseForIdenticalHtml() {
        HtmlContentComparison strategy = new HtmlContentComparison();

        boolean result = strategy.hasChanged(
                "<html><body>Hello</body></html>",
                "<html><body>Hello</body></html>"
        );

        assertFalse(result);
    }

    @Test
    void htmlContentComparisonReturnsTrueForDifferentHtmlWithSameText() {
        HtmlContentComparison strategy = new HtmlContentComparison();

        boolean result = strategy.hasChanged(
                "<html><body>Hello</body></html>",
                "<html><div>Hello</div></html>"
        );

        assertTrue(result);
    }

    @Test
    void htmlContentComparisonReturnsTrueForDifferentVisibleText() {
        HtmlContentComparison strategy = new HtmlContentComparison();

        boolean result = strategy.hasChanged(
                "<html><body>Hello</body></html>",
                "<html><body>Hi</body></html>"
        );

        assertTrue(result);
    }

    @Test
    void htmlContentComparisonReturnsFalseForTwoEmptyStrings() {
        HtmlContentComparison strategy = new HtmlContentComparison();

        boolean result = strategy.hasChanged("", "");

        assertFalse(result);
    }

    @Test
    void textContentComparisonReturnsFalseForSameTextAndSameTags() {
        TextContentComparison strategy = new TextContentComparison();

        boolean result = strategy.hasChanged(
                "<html><body>Hello</body></html>",
                "<html><body>Hello</body></html>"
        );

        assertFalse(result);
    }

    @Test
    void textContentComparisonReturnsFalseForSameVisibleTextAndDifferentTags() {
        TextContentComparison strategy = new TextContentComparison();

        boolean result = strategy.hasChanged(
                "<html><body>Hello</body></html>",
                "<html><div>Hello</div></html>"
        );

        assertFalse(result);
    }

    @Test
    void textContentComparisonReturnsTrueForDifferentVisibleText() {
        TextContentComparison strategy = new TextContentComparison();

        boolean result = strategy.hasChanged(
                "<html><body>Hello</body></html>",
                "<html><body>Hi</body></html>"
        );

        assertTrue(result);
    }

    @Test
    void textContentComparisonReturnsFalseForEmptyVisibleText() {
        TextContentComparison strategy = new TextContentComparison();

        boolean result = strategy.hasChanged(
                "<html><body></body></html>",
                "<html><div></div></html>"
        );

        assertFalse(result);
    }
}