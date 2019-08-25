package com.zadokhin.service.matchers;

import org.jsoup.nodes.Element;

public interface HtmlElementsMatcher {

    int matchingScore(Element first, Element second);

}
