import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.*;
import static org.junit.Assert.*;


public class WordPuzzleTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  // Integration Tests
  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Word Puzzle");
  }

  @Test
  public void convertWord() {
    goTo("http://localhost:4567");
    fill("#phrase").with("hello");
    submit(".btn");
    assertThat(pageSource()).contains("h-ll-");
  }

  @Test
  public void convertPhrase() {
    goTo("http://localhost:4567");
    fill("#phrase").with("Believe you can and you're halfway there. Theodore Roosevelt:");
    submit(".btn");
    assertThat(pageSource()).contains("B-l--v- y-- c-n -nd y--'r- h-lfw-y th-r-. Th--d-r- R--s-v-lt:");
  }

  // Unit Tests
  @Test
  public void convertWordToPuzzle_returnsHello_puzzleized() {
    WordPuzzle phrase = new WordPuzzle();
    String puzzlePhrase = "h-ll-";
    assertEquals(puzzlePhrase, phrase.convertToPuzzle("hello"));
  }

  @Test
  public void convertPhraseToPuzzle_returnPhrase_puzzleized() {
    WordPuzzle phrase = new WordPuzzle();
    String puzzlePhrase =
    "B-l--v- y-- c-n -nd y--'r- h-lfw-y th-r-. Th--d-r- R--s-v-lt:";
    assertEquals(puzzlePhrase, phrase.convertToPuzzle("Believe you can and you're halfway there. Theodore Roosevelt:"));
  }

}
