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
        final String idToFind = "make-everything-ok-button";
        final Optional<Element> nodeToFind = htmlElementFinder.findNodeById(idToFind, originalFilename);
        if (!nodeToFind.isPresent()) {
            System.out.printf("Sorry, cannot find element with id %s in original file", idToFind);
        }

        final Optional<Element> elementBySample = elementMatcherService.findElementBySample(nodeToFind.get(),
                diffCaseFilename);

        elementBySample.ifPresent(element -> System.out.println("Found element by sample: " + resultElementFormatter.format(element)));
    }

    private void verifyInputParameters(final String... args) {
        if (args.length < 2) {
            System.out.println("Please specify both original and diff-case html files");
            System.exit(1);
        }
    }
}
