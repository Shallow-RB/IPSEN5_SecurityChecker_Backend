package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "scan")
public class Scan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scan_id")
    private long scanId;
    @ManyToOne(targetEntity = ScanUser.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ScanUser user;
    @Column(name = "url")
    private String url;
    @OneToOne(mappedBy = "scan", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ScanResult scanResult;
    @Column(name = "update_date")
    private String updateDate;
//
////    Constructors


    public Scan(ScanUser user, String url, ScanResult scanResult, String updateDate) {
        this.user = user;
        this.url = url;
        this.scanResult = scanResult;
        this.updateDate = updateDate;
    }

    public Scan() {
    }


    //    Getters Setters

    public ScanResult getScanResult() {
        return scanResult;
    }

    public void setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    public long getScanId() {
        return scanId;
    }

    public void setScanId(long scanId) {
        this.scanId = scanId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String link) {
        this.url = link;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public ScanUser getUser() {
        return user;
    }

    public void setUser(ScanUser user) {
        this.user = user;
    }

}
