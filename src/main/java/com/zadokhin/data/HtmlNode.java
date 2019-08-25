package com.zadokhin.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HtmlNode {

    private String id;
    private String htmlClass;

}
