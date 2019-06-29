package com.wopi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 属性名首字母大写
 *
 * @author CaoYongCheng
 */
@Data
public class FileInfo {
    @JsonProperty("BaseFileName")
    private String baseFileName;
    @JsonProperty("Size")
    private Long size;
    @JsonProperty("OwnerId")
    private String ownerId;
    @JsonProperty("Version")
    private Long version;
    @JsonProperty("Sha256")
    private String sha256;
    @JsonProperty("AllowExternalMarketplace")
    private Boolean allowExternalMarketplace;
    @JsonProperty("UserCanWrite")
    private Boolean userCanWrite;
    @JsonProperty("SupportsUpdate")
    private Boolean supportsUpdate;
    @JsonProperty("SupportsLocks")
    private Boolean supportsLocks;
}
