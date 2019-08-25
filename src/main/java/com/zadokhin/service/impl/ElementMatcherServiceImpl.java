package com.zadokhin.service.impl;

import java.util.Optional;

import com.zadokhin.service.ElementMatcherService;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ElementMatcherServiceImpl implements ElementMatcherService {

    @Override
    public Optional<Element> findElementBySample(final Element sample, final String filename) {
        return null;
    }
}
