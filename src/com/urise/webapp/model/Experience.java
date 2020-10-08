package com.urise.webapp.model;



import java.time.YearMonth;

public class Experience {

    private YearMonth start;
    private YearMonth finish;
    private Link organizationLink;
    private String position;
    private String duties;

    public Experience(YearMonth start, YearMonth finish, Link organizationLink, String position, String duties) {
        this.start = start;
        this.finish = finish;
        this.organizationLink = organizationLink;
        this.position = position;
        this.duties = duties;
    }
}
