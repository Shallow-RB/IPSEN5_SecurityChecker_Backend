package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import java.util.ArrayList;

public class Scan {
    private String name;
    private String email;
    private String website;
    private boolean owners;
    private ArrayList<ScanCategory> scanCategories;

    public Scan() {
    }

    public Scan(String name, String email, String website, boolean owners, ArrayList<ScanCategory> scanCategories) {
        this.name = name;
        this.email = email;
        this.website = website;
        this.owners = owners;
        this.scanCategories = scanCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isOwners() {
        return owners;
    }

    public void setOwners(boolean owners) {
        this.owners = owners;
    }

    public ArrayList<ScanCategory> getScanCategories() {
        return scanCategories;
    }

    public void setScanCategories(ArrayList<ScanCategory> scanCategories) {
        this.scanCategories = scanCategories;
    }
}
