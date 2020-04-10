package com.sayee.sxsy.newModules.filepathutils.entity;

public class TAccessories {
    private String acceId;

    private String itemId;

    private String fileName;//文件名

    private Float fileSize;

    private String uploadDate;

    private Integer showAsImage;

    private String employeeid;//上传人员id

    private String employeename;

    private String filetype;

    private String realpath;

    private String choosefile;

    private String thumbPath;

    private Integer isPic;

    private String fjtype;

    private String filePath;//文件类别(1:医方 2:患方,3:患方笔录,4:患方补充材料,5:医方笔录,6:医方补充材料)',

    public String getAcceId() {
        return acceId;
    }

    public void setAcceId(String acceId) {
        this.acceId = acceId == null ? null : acceId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Float getFileSize() {
        return fileSize;
    }

    public void setFileSize(Float fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate == null ? null : uploadDate.trim();
    }

    public Integer getShowAsImage() {
        return showAsImage;
    }

    public void setShowAsImage(Integer showAsImage) {
        this.showAsImage = showAsImage;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename == null ? null : employeename.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath == null ? null : realpath.trim();
    }

    public String getChoosefile() {
        return choosefile;
    }

    public void setChoosefile(String choosefile) {
        this.choosefile = choosefile == null ? null : choosefile.trim();
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath == null ? null : thumbPath.trim();
    }

    public Integer getIsPic() {
        return isPic;
    }

    public void setIsPic(Integer isPic) {
        this.isPic = isPic;
    }

    public String getFjtype() {
        return fjtype;
    }

    public void setFjtype(String fjtype) {
        this.fjtype = fjtype == null ? null : fjtype.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }
}