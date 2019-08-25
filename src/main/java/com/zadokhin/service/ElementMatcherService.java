package com.zadokhin.service;

import java.util.Optional;

import org.jsoup.nodes.Element;

public interface ElementMatcherService {

    Optional<Element> findElementBySample(Element sample, String filename);

    void printElementScore(Element sample, Element foundElement);

}
