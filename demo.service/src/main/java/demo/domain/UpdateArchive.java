package demo.domain;

import java.util.Date;

public class UpdateArchive {
    private Long id;

    private String url;

    private Date lastUpdateTime;

    private Long relationUpdateStaffId;

    private Long relationArchiveId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getRelationUpdateStaffId() {
        return relationUpdateStaffId;
    }

    public void setRelationUpdateStaffId(Long relationUpdateStaffId) {
        this.relationUpdateStaffId = relationUpdateStaffId;
    }

    public Long getRelationArchiveId() {
        return relationArchiveId;
    }

    public void setRelationArchiveId(Long relationArchiveId) {
        this.relationArchiveId = relationArchiveId;
    }
}