package ua.com.parser.htmlparser;

import java.util.List;

/**
 * Created by vadim on 15.01.17.
 */
public class LinkCollector {

    private List<String> conditions;
    private List<String> links;

    public LinkCollector(List<String> conditions) {
        this.conditions = conditions;
    }

    public List<String> getLinks() {
        //Парсим страницу
        //Находим блок отображающий количество страниц
        //Определяем номер общего количества страниц
        //Возвращаем найденное количество

        return links;
    }
}
