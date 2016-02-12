import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;



public class WordPuzzle {
  public static void main( String[] args ) {
    // front-end

    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/results.vtl");

      String phrase = request.queryParams("phrase");
      String puzzlePhrase = convertToPuzzle(phrase);

      model.put("puzzlePhrase", puzzlePhrase);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
  // Back-end

  public static String convertToPuzzle(String phrase) {
    return phrase.replaceAll("[aeiou]", "-");
  }


}
