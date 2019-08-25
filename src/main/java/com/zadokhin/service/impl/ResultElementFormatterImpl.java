package com.zadokhin.service.impl;

import com.zadokhin.service.ResultElementFormatter;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ResultElementFormatterImpl implements ResultElementFormatter {

    @Override
    public String format(final Element element) {
        final StringBuilder accumulator = new StringBuilder();
        formatRecursively(accumulator, element);
        accumulator.append(element.html());
        return accumulator.toString();
    }

    private void formatRecursively(final StringBuilder accumulator, final Element element) {
        if (element.parent() != null) {
            formatRecursively(accumulator, element.parent());
        }
        accumulator.append(" > ").append(element.tagName()).append("[").append(element.siblingIndex()).append("]");
        if (element.id() != null) {
            accumulator.append(" ").append(element.id());
        }
    }
}
