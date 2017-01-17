package ua.com.parser.htmlparser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.parser.htmlparser.rule.Rule;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class LinkParser extends Parser implements Callable<Map<Integer, String>> {
    private String url;
    private List<Rule> rules;

    public LinkParser(String url, List<Rule> rules) {
        this.url = url;
        this.rules = rules;
    }

    @Override
    public Map<Integer, String> call() throws Exception {
        Map<Integer, String> result = new HashMap<>();

        String urlNextPage = url + "/page%s";

        try {
            result.putAll(getLinks(url));

            int start = 2; // start index to parse next page;
            for (int i = start; i <= getMaxPageNumber(url); i++) {
                String nextUrl = String.format(urlNextPage, i);
                result.putAll(getLinks(nextUrl));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to get a list of hubs: " + e.getMessage());
        }
        return result;
    }

    private Map<Integer, String> getLinks(String url) throws IOException {

        Map<Integer, String> result = new HashMap<>();

        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByAttributeValue("class", "post post_teaser shortcuts_item");

        elements.forEach(element -> {

            Integer id = Integer.parseInt(element.attr("id").replace("post_", ""));
            Element aElement = element.child(0).child(1).child(0);
            String link = aElement.attr("href");

            for (Rule rule : rules) {
                if(checkRule(rule, element)) {
                    result.put(id, link);
                    break;
                }
            }
        });

        return result;
    }

    private boolean checkRule(Rule rule, Element element) {
        Elements elements = element.getElementsByAttributeValue("class", getClassValue(rule));
        elements.forEach(innerElement -> {
            String value = innerElement.text();


        });

        return false;
    }

    private String getClassValue(Rule rule) {

        String result = "views-count_post";
        if (rule.getKey().equals("vote")) {
            result = "voting-wjt__counter-score js-score";
        } else if (rule.getKey().equals("favorite")) {
            result = "favorite-wjt__counter js-favs_count";
        }

        return result;
    }
}


// 1) Получаем Elements статей на странице.
//  вычитываем ссылку и id статьи
// 2) в цикле прогоняем статью по всем правилам:
//      - если правило key = vote, то ищем внутри element блок <span class="voting-wjt__counter-score js-score" title="Общий рейтинг 52: &uarr;47 и &darr;5">+42</span>
//            - сравниваем число в блоке с числом в Rule возвращаем true|false
//      - если правило key = favorite, то внутри element ищем блок <span class="favorite-wjt__counter js-favs_count" title="Количество пользователей, добавивших публикацию в избранное">91</span>
//            - сравниваем число в блоке с числом в Rule возвращаем true|false
//      - если правило key = view, то внутри element ищем блок <div class="views-count_post" title="Просмотры публикации">9,5k</div>
//            - преобразовываем 9,5k в 9500
//            - сравниваем число в блоке с числом в Rule возвращаем true|false