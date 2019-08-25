package com.zadokhin.service.impl;

import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.zadokhin.service.HtmlElementFinder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class HtmlElementFinderImpl implements HtmlElementFinder {

    private static String CHARSET_NAME = "utf8";

    @Override
    public Optional<Element> findNodeById(final String id, final String filename) {
        return findNodeFromFile(new File(filename), id);
    }

    @Override
    public Optional<Elements> findNodesBySelector(final String selector, final String filename) {
        return findElementsByQuery(new File(filename), selector);
    }

    private Optional<Element> findNodeFromFile(File htmlFile, String targetElementId) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.of(doc.getElementById(targetElementId));

        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private Optional<Elements> findElementsByQuery(File htmlFile, String cssQuery) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.of(doc.select(cssQuery));

        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
