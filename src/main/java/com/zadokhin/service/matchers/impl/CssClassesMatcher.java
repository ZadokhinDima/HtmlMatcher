package com.zadokhin.service.matchers.impl;

import com.google.common.collect.Sets;
import com.zadokhin.service.matchers.HtmlElementsMatcher;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CssClassesMatcher implements HtmlElementsMatcher {

    @Override
    public int matchingScore(final Element first, final Element second) {
        return Sets.intersection(first.classNames(), second.classNames()).size();
    }
}
