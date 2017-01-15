package ua.com.parser.htmlparser;

import org.junit.Test;

/**
 * Created by vadim on 15.01.17.
 */
public class GetPagesCountTest {

    @Test
    public void getPagesAmount() {
        //given
        String html = "<div class=\"page-nav\">\n" +
                "         <ul class=\"next-prev\">\n" +
                "         <li><span>&larr;</span>&nbsp;сюда</li>\n" +
                "         <li><a title=\"На страницу вперед (Alt + &rarr;)\" class=\"next\" id=\"next_page\" href=\"/hub/infosecurity/page2/\" rel=\"\">туда</a>&nbsp;<span>&rarr;</span></li>\n" +
                "         </ul>\n" +
                "         <ul id=\"nav-pages\">\n" +
                "            <li><em>1</em></li>\n" +
                "            <li><a href=\"/hub/infosecurity/page2/\">2</a></li>\n" +
                "            <li><a href=\"/hub/infosecurity/page3/\">3</a></li>\n" +
                "            <li><a title=\"Последняя страница\" href=\"/hub/infosecurity/page581/\" rel=\"\"><span>&rarr;</span></a></li>\n" +
                "         </ul>\n" +
                "       </div>";
        //when

        //then
    }
}
