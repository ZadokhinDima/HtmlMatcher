package com.zadokhin.service.matchers.impl;

import java.util.Objects;

import com.zadokhin.service.matchers.HtmlElementsMatcher;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class InnerDataMatcher implements HtmlElementsMatcher {

    @Override
    public int matchingScore(final Element first, final Element second) {
        if (Objects.equals(first.data(), second.data())) {
            return 5;
        } else {
            return 0;
        }
    }
}
