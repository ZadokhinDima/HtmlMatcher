package com.zadokhin.service;

import java.util.Optional;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface HtmlElementFinder {

    Optional<Element> findNodeById(String id, String filename);

    Optional<Elements> findNodesBySelector(String selector, String filename);
}
