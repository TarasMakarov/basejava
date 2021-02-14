package com.urise.webapp.model;

public enum ContactType {

    PHONE("Тел."),
    MOBILE("Мобильный"),
    SKYPE("Skype:") {
        @Override
        public String toHtmlNotNull(String value) {
            return "<a href= skype:" + value + ">" + value + "</a>";
        }
    },
    EMAIL("Почта:") {
        @Override
        public String toHtmlNotNull(String value) {
            return "<a href= mailto:" + value + ">" + value + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn: "),
    GITHUB("Профиль GitHub: "),
    STACKOVERFLOW("Профиль Stackoverflow: "),
    HOMEPAGE("Домашняя страница: ");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtmlNotNull(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtmlNotNull(value);
    }
}