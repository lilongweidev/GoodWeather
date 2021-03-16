package com.llw.mvplibrary.bean;

import org.litepal.crud.LitePalSupport;

/**
 * 应用版本更新
 * @author llw
 */
public class AppVersion extends LitePalSupport {


    /**
     * name : 好天气
     * version : 1
     * changelog : 新增地图天气
     * updated_at : 1598581575
     * versionShort : 2.1
     * build : 1
     * installUrl : https://download.jappstore.com/apps/5e8c37e90d81cc0db2645c1c/install?download_token=dccf478d29848e406f371890ac761bfb&source=update
     * install_url : https://download.jappstore.com/apps/5e8c37e90d81cc0db2645c1c/install?download_token=dccf478d29848e406f371890ac761bfb&source=update
     * direct_install_url : https://download.jappstore.com/apps/5e8c37e90d81cc0db2645c1c/install?download_token=dccf478d29848e406f371890ac761bfb&source=update
     * update_url : http://jappstore.com/h4sg
     * binary : {"fsize":31038590}
     */

    private String name;
    private String version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String installUrl;
    private String install_url;
    private String direct_install_url;
    private String update_url;
    private String appSize;

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    private BinaryBean binary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getDirect_install_url() {
        return direct_install_url;
    }

    public void setDirect_install_url(String direct_install_url) {
        this.direct_install_url = direct_install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public BinaryBean getBinary() {
        return binary;
    }

    public void setBinary(BinaryBean binary) {
        this.binary = binary;
    }

    public static class BinaryBean {
        /**
         * fsize : 31038590
         */

        private int fsize;

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }
    }
}
