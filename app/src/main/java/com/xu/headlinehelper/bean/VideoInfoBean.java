package com.xu.headlinehelper.bean;

/**
 * @author 言吾許
 *         视频信息类
 */

public class VideoInfoBean {
    /**
     * definition : 360p
     * vtype : mp4
     * vwidth : 640
     * vheight : 360
     * bitrate : 320804
     * size : 4616245
     * codec_type : h264
     * logo_type : xigua
     * encrypt : false
     * file_hash : 812cfb43ebdb9448654e414b1e8ba85b
     * main_url : aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vODIzNDlmNWQ3MDRhOThjNzVjMmRiMWUyM2RmMTZjNTkvNWFkMDI1MzkvdmlkZW8vbS8yMjA5MmI0OTgxYmNkNzc0ZWYwOGRlNjc0NmQ0NWUxNzlkMjExNTU1Y2UzMDAwMDMwNjA1ZTgxNTA3NS8=
     * backup_url_1 : aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS85MDNlNTg3YmYzNzNhZTU3ZWJlODE5YWIwMWRjZDcxNi81YWQwMjUzOS92aWRlby9tLzIyMDkyYjQ5ODFiY2Q3NzRlZjA4ZGU2NzQ2ZDQ1ZTE3OWQyMTE1NTVjZTMwMDAwMzA2MDVlODE1MDc1Lw==
     * user_video_proxy : 1
     * socket_buffer : 7218000
     * preload_size : 327680
     * preload_interval : 60
     * preload_min_step : 5
     * preload_max_step : 10
     * spade_a :
     */

    private String definition;
    private String vtype;
    private int vwidth;
    private int vheight;
    private int bitrate;
    private int size;
    private String codec_type;
    private String logo_type;
    private boolean encrypt;
    private String file_hash;
    private String main_url;
    private String backup_url_1;
    private int user_video_proxy;
    private int socket_buffer;
    private int preload_size;
    private int preload_interval;
    private int preload_min_step;
    private int preload_max_step;
    private String spade_a;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public int getVwidth() {
        return vwidth;
    }

    public void setVwidth(int vwidth) {
        this.vwidth = vwidth;
    }

    public int getVheight() {
        return vheight;
    }

    public void setVheight(int vheight) {
        this.vheight = vheight;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCodec_type() {
        return codec_type;
    }

    public void setCodec_type(String codec_type) {
        this.codec_type = codec_type;
    }

    public String getLogo_type() {
        return logo_type;
    }

    public void setLogo_type(String logo_type) {
        this.logo_type = logo_type;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public String getFile_hash() {
        return file_hash;
    }

    public void setFile_hash(String file_hash) {
        this.file_hash = file_hash;
    }

    public String getMain_url() {
        return main_url;
    }

    public void setMain_url(String main_url) {
        this.main_url = main_url;
    }

    public String getBackup_url_1() {
        return backup_url_1;
    }

    public void setBackup_url_1(String backup_url_1) {
        this.backup_url_1 = backup_url_1;
    }

    public int getUser_video_proxy() {
        return user_video_proxy;
    }

    public void setUser_video_proxy(int user_video_proxy) {
        this.user_video_proxy = user_video_proxy;
    }

    public int getSocket_buffer() {
        return socket_buffer;
    }

    public void setSocket_buffer(int socket_buffer) {
        this.socket_buffer = socket_buffer;
    }

    public int getPreload_size() {
        return preload_size;
    }

    public void setPreload_size(int preload_size) {
        this.preload_size = preload_size;
    }

    public int getPreload_interval() {
        return preload_interval;
    }

    public void setPreload_interval(int preload_interval) {
        this.preload_interval = preload_interval;
    }

    public int getPreload_min_step() {
        return preload_min_step;
    }

    public void setPreload_min_step(int preload_min_step) {
        this.preload_min_step = preload_min_step;
    }

    public int getPreload_max_step() {
        return preload_max_step;
    }

    public void setPreload_max_step(int preload_max_step) {
        this.preload_max_step = preload_max_step;
    }

    public String getSpade_a() {
        return spade_a;
    }

    public void setSpade_a(String spade_a) {
        this.spade_a = spade_a;
    }
}
