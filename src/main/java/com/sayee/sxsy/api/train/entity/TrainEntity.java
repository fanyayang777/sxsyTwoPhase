package com.sayee.sxsy.api.train.entity;

import com.sayee.sxsy.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * @Description
 */
public class TrainEntity extends DataEntity<TrainEntity> {

    private static final long serialVersionUID = 1L;
    private String trainingId;		// training_id
    private String title;		// 标题
    private String vidioType;		// 视频类型
    private String send;		// 发往：小程序 网站 后台
    private String path;		// path

    public TrainEntity() {
        super();
    }

    public TrainEntity(String id){
        super(id);
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    @Length(min=0, max=500, message="标题长度必须介于 0 和 500 之间")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Length(min=0, max=10, message="视频类型长度必须介于 0 和 1 之间")
    public String getVidioType() {
        return vidioType;
    }

    public void setVidioType(String vidioType) {
        this.vidioType = vidioType;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    @Length(min=0, max=1000, message="path长度必须介于 0 和 1000 之间")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}