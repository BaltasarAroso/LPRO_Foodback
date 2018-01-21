package com.foodback.foodback.logic;

/**
 * Created by Foodback.
 */

public class Report {

    /**
     * ID of Report
     */
    private long id;

    /**
     * Type of Report
     */
    private String type;

    /**
     *  Report text
     */
    private String report;

    /**
     * ID of reported comment
     */
    private long comment_id;

    /**
     * ID of reported establishment
     */
    private long establishment_id;

    /**
     * ID of user that is reporting
     */
    private long reporter_id;

    /**
     * @param id ID of Report
     * @param type Type of Report
     * @param report Report text
     * @param comment_id ID of reported comment
     * @param establishment_id ID of reported establishment
     * @param reporter_id ID of user that is reporting
     */
    public Report(long id, String type, String report, long comment_id, long establishment_id, long reporter_id) {
        super();
        this.id = id;
        this.type = type;
        this.report = report;
        this.comment_id = comment_id;
        this.establishment_id = establishment_id;
        this.reporter_id = reporter_id;
    }

    /**
     * @return ID of report
     */
    public long getId() {
        return id;
    }

    /**
     * @param id ID of report
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Type of report
     */
    public String getType() {
        return type;
    }

    /**
     * @param type Type of report
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Report text
     */
    public String getReport() {
        return report;
    }

    /**
     * @param report Report text
     */
    public void setReport(String report) {
        this.report = report;
    }

    /**
     * @return ID of reported comment
     */
    public long getComment_id() {
        return comment_id;
    }

    /**
     * @param comment_id ID of reported comment
     */
    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    /**
     * @return ID of reported establishment
     */
    public long getEstablishment_id() {
        return establishment_id;
    }

    /**
     * @param establishment_id ID of reported establishment
     */
    public void setEstablishment_id(long establishment_id) {
        this.establishment_id = establishment_id;
    }

    /**
     * @return ID of user that is reporting
     */
    public long getReporter_id() {
        return reporter_id;
    }

    /**
     * @param reporter_id ID of user that is reporting
     */
    public void setReporter_id(long reporter_id) {
        this.reporter_id = reporter_id;
    }

    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Report)) return false;

        Report report = (Report)o;
        return this.id == report.getId() &&
                this.type.equals(report.getType()) &&
                this.report.equals(report.getReport()) &&
                this.comment_id == report.getComment_id() &&
                this.establishment_id == report.getEstablishment_id() &&
                this.reporter_id == report.getReporter_id();
    }
}

