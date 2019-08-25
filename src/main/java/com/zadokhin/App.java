package com.zadokhin;

import java.util.Optional;

import com.zadokhin.service.ElementMatcherService;
import com.zadokhin.service.HtmlElementFinder;
import com.zadokhin.service.ResultElementFormatter;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static final String DEFAULT_ELEMENT_ID = "make-everything-ok-button";

    @Autowired
    private HtmlElementFinder htmlElementFinder;
    @Autowired
    private ElementMatcherService elementMatcherService;
    @Autowired
    private ResultElementFormatter resultElementFormatter;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        verifyInputParameters(args);
        final String originalFilename = args[0];
        final String diffCaseFilename = args[1];
        final String elementIdToSearch = getElementIdToSearch(args);
        final Optional<Element> nodeToFind = htmlElementFinder.findNodeById(elementIdToSearch, originalFilename);
        if (nodeToFind.isPresent()) {
            final Element sampleElement = nodeToFind.get();
            final Optional<Element> elementBySample = elementMatcherService.findElementBySample(sampleElement,
                    diffCaseFilename);
            elementBySample.ifPresent(element -> showResults(sampleElement, element));
        } else {
            System.out.printf("Sorry, cannot find element with id %s in original file", elementIdToSearch);
        }
    }

    private void verifyInputParameters(final String... args) {
        if (args.length < 2) {
            System.out.println("Please specify both original and diff-case html files");
            System.exit(1);
        }
    }

    private String getElementIdToSearch(final String... args) {
        if (args.length > 2) {
            return args[2];
        } else {
            return DEFAULT_ELEMENT_ID;
        }
    }

    private void showResults(final Element sample, final Element result) {
        System.out.println("Found element by sample: " + resultElementFormatter.format(result));
        System.out.println("=========SCORING============");
        elementMatcherService.printElementScore(sample, result);
    }
}
