package fr.iandeveseleer.testingframework.enums;

import lombok.Getter;

public enum BrowserType {

    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("MicrosoftEdge");

    @Getter
    private final String capabilityName;

    BrowserType(String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
