package com.zadokhin.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.zadokhin.service.ElementMatcherService;
import com.zadokhin.service.HtmlElementFinder;
import com.zadokhin.service.matchers.HtmlElementsMatcher;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementMatcherServiceImpl implements ElementMatcherService {

    @Autowired
    private List<HtmlElementsMatcher> matchers;

    @Autowired
    private HtmlElementFinder htmlElementFinder;

    @Override
    public Optional<Element> findElementBySample(final Element sample, final String filename) {
        final List<Element> suspiciousElements = getSuspiciousElements(sample, filename);
        return suspiciousElements.stream().max(Comparator.comparingInt(element -> checkElement(sample, element)));
    }

    private List<Element> getSuspiciousElements(final Element sample, final String filename) {
        return htmlElementFinder.findNodesBySelector(sample.tagName(), filename).orElse(new Elements());
    }

    private int checkElement(final Element sample, final Element toCheck) {
        return matchers.stream()
                .mapToInt(matcher -> matcher.matchingScore(sample, toCheck))
                .sum();
    }
}
