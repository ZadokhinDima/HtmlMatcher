package com.zadokhin.service.matchers.impl;

import java.util.HashSet;

import com.google.common.collect.Sets;
import com.zadokhin.service.matchers.HtmlElementsMatcher;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class HtmlAttributesMatcher implements HtmlElementsMatcher {

    @Override
    public int matchingScore(final Element first, final Element second) {
        return Sets.intersection(new HashSet(first.attributes().asList()), new HashSet(second.attributes().asList())).size();
    }
}
