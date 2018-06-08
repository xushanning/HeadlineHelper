package com.xu.headlinehelper.bean;

/**
 * @author 言吾許
 *         视频播放地址
 */

public class VideoAddressBean {


    /**
     * data : {"status":10,"user_id":"toutiao","video_id":"b701f1eabfbc44c9ad053edf8618c1c4","validate":"","enable_ssl":false,"poster_url":"http://p3.pstatp.com/origin/6e30000cd51163a4a599","video_duration":94.72,"auto_definition":"360p","video_list":{"video_1":{"definition":"360p","vtype":"mp4","vwidth":640,"vheight":360,"bitrate":320804,"size":4616245,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"812cfb43ebdb9448654e414b1e8ba85b","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vODIzNDlmNWQ3MDRhOThjNzVjMmRiMWUyM2RmMTZjNTkvNWFkMDI1MzkvdmlkZW8vbS8yMjA5MmI0OTgxYmNkNzc0ZWYwOGRlNjc0NmQ0NWUxNzlkMjExNTU1Y2UzMDAwMDMwNjA1ZTgxNTA3NS8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS85MDNlNTg3YmYzNzNhZTU3ZWJlODE5YWIwMWRjZDcxNi81YWQwMjUzOS92aWRlby9tLzIyMDkyYjQ5ODFiY2Q3NzRlZjA4ZGU2NzQ2ZDQ1ZTE3OWQyMTE1NTVjZTMwMDAwMzA2MDVlODE1MDc1Lw==","user_video_proxy":1,"socket_buffer":7218000,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""},"video_2":{"definition":"480p","vtype":"mp4","vwidth":854,"vheight":480,"bitrate":504276,"size":6787860,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"1947814d76b7f35b256b0873dec7b2c2","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vNTQ3NGY3YzVjMWFiZTM5YjZlN2EzYTEzMDRhOGEwNGYvNWFkMDI1MzkvdmlkZW8vbS8yMjA4ZGMxMWQwZTM5YmI0ZDc5OTNlYzFjMjk1ZDAwYmFhOTExNTU2NzA4MDAwMDdjOWRkMGNhM2Y0OS8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS9kZTM2ZDM1NjFiNjFkOWFhM2VkNmQ4YzcyOWI1N2YyMC81YWQwMjUzOS92aWRlby9tLzIyMDhkYzExZDBlMzliYjRkNzk5M2VjMWMyOTVkMDBiYWE5MTE1NTY3MDgwMDAwN2M5ZGQwY2EzZjQ5Lw==","user_video_proxy":1,"socket_buffer":11346120,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""},"video_3":{"definition":"720p","vtype":"mp4","vwidth":1280,"vheight":720,"bitrate":1212146,"size":15163876,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"92ec2077c9be6f621d06ffe01a4185df","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vMDBlODEwZTMwNmM3YWFiZjEzNjVjMmFhZDNhNjI4ZmIvNWFkMDI1MzkvdmlkZW8vbS8yMjBmZGU5MWRiNjcwY2Q0MzYyYTA4OTdlNWQ5Mzk4ZDM1OTExNTUzY2MxMDAwMDM5ZWU0ZDI5ODU3My8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS80MWRjZjg1MWNkZTBkNmExMTEwMjNkZTQ3MGE2MGExOS81YWQwMjUzOS92aWRlby9tLzIyMGZkZTkxZGI2NzBjZDQzNjJhMDg5N2U1ZDkzOThkMzU5MTE1NTNjYzEwMDAwMzllZTRkMjk4NTczLw==","user_video_proxy":1,"socket_buffer":27273240,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""}}}
     * message : success
     * code : 0
     * total : 3
     */

    private DataBean data;
    private String message;
    private int code;
    private int total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        /**
         * status : 10
         * user_id : toutiao
         * video_id : b701f1eabfbc44c9ad053edf8618c1c4
         * validate :
         * enable_ssl : false
         * poster_url : http://p3.pstatp.com/origin/6e30000cd51163a4a599
         * video_duration : 94.72
         * auto_definition : 360p
         * video_list : {"video_1":{"definition":"360p","vtype":"mp4","vwidth":640,"vheight":360,"bitrate":320804,"size":4616245,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"812cfb43ebdb9448654e414b1e8ba85b","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vODIzNDlmNWQ3MDRhOThjNzVjMmRiMWUyM2RmMTZjNTkvNWFkMDI1MzkvdmlkZW8vbS8yMjA5MmI0OTgxYmNkNzc0ZWYwOGRlNjc0NmQ0NWUxNzlkMjExNTU1Y2UzMDAwMDMwNjA1ZTgxNTA3NS8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS85MDNlNTg3YmYzNzNhZTU3ZWJlODE5YWIwMWRjZDcxNi81YWQwMjUzOS92aWRlby9tLzIyMDkyYjQ5ODFiY2Q3NzRlZjA4ZGU2NzQ2ZDQ1ZTE3OWQyMTE1NTVjZTMwMDAwMzA2MDVlODE1MDc1Lw==","user_video_proxy":1,"socket_buffer":7218000,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""},"video_2":{"definition":"480p","vtype":"mp4","vwidth":854,"vheight":480,"bitrate":504276,"size":6787860,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"1947814d76b7f35b256b0873dec7b2c2","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vNTQ3NGY3YzVjMWFiZTM5YjZlN2EzYTEzMDRhOGEwNGYvNWFkMDI1MzkvdmlkZW8vbS8yMjA4ZGMxMWQwZTM5YmI0ZDc5OTNlYzFjMjk1ZDAwYmFhOTExNTU2NzA4MDAwMDdjOWRkMGNhM2Y0OS8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS9kZTM2ZDM1NjFiNjFkOWFhM2VkNmQ4YzcyOWI1N2YyMC81YWQwMjUzOS92aWRlby9tLzIyMDhkYzExZDBlMzliYjRkNzk5M2VjMWMyOTVkMDBiYWE5MTE1NTY3MDgwMDAwN2M5ZGQwY2EzZjQ5Lw==","user_video_proxy":1,"socket_buffer":11346120,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""},"video_3":{"definition":"720p","vtype":"mp4","vwidth":1280,"vheight":720,"bitrate":1212146,"size":15163876,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"92ec2077c9be6f621d06ffe01a4185df","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vMDBlODEwZTMwNmM3YWFiZjEzNjVjMmFhZDNhNjI4ZmIvNWFkMDI1MzkvdmlkZW8vbS8yMjBmZGU5MWRiNjcwY2Q0MzYyYTA4OTdlNWQ5Mzk4ZDM1OTExNTUzY2MxMDAwMDM5ZWU0ZDI5ODU3My8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS80MWRjZjg1MWNkZTBkNmExMTEwMjNkZTQ3MGE2MGExOS81YWQwMjUzOS92aWRlby9tLzIyMGZkZTkxZGI2NzBjZDQzNjJhMDg5N2U1ZDkzOThkMzU5MTE1NTNjYzEwMDAwMzllZTRkMjk4NTczLw==","user_video_proxy":1,"socket_buffer":27273240,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""}}
         */

        private int status;
        private String user_id;
        private String video_id;
        private String validate;
        private boolean enable_ssl;
        private String poster_url;
        private double video_duration;
        private String auto_definition;
        private VideoListBean video_list;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public boolean isEnable_ssl() {
            return enable_ssl;
        }

        public void setEnable_ssl(boolean enable_ssl) {
            this.enable_ssl = enable_ssl;
        }

        public String getPoster_url() {
            return poster_url;
        }

        public void setPoster_url(String poster_url) {
            this.poster_url = poster_url;
        }

        public double getVideo_duration() {
            return video_duration;
        }

        public void setVideo_duration(double video_duration) {
            this.video_duration = video_duration;
        }

        public String getAuto_definition() {
            return auto_definition;
        }

        public void setAuto_definition(String auto_definition) {
            this.auto_definition = auto_definition;
        }

        public VideoListBean getVideo_list() {
            return video_list;
        }

        public void setVideo_list(VideoListBean video_list) {
            this.video_list = video_list;
        }

        public static class VideoListBean {
            /**
             * video_1 : {"definition":"360p","vtype":"mp4","vwidth":640,"vheight":360,"bitrate":320804,"size":4616245,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"812cfb43ebdb9448654e414b1e8ba85b","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vODIzNDlmNWQ3MDRhOThjNzVjMmRiMWUyM2RmMTZjNTkvNWFkMDI1MzkvdmlkZW8vbS8yMjA5MmI0OTgxYmNkNzc0ZWYwOGRlNjc0NmQ0NWUxNzlkMjExNTU1Y2UzMDAwMDMwNjA1ZTgxNTA3NS8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS85MDNlNTg3YmYzNzNhZTU3ZWJlODE5YWIwMWRjZDcxNi81YWQwMjUzOS92aWRlby9tLzIyMDkyYjQ5ODFiY2Q3NzRlZjA4ZGU2NzQ2ZDQ1ZTE3OWQyMTE1NTVjZTMwMDAwMzA2MDVlODE1MDc1Lw==","user_video_proxy":1,"socket_buffer":7218000,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""}
             * video_2 : {"definition":"480p","vtype":"mp4","vwidth":854,"vheight":480,"bitrate":504276,"size":6787860,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"1947814d76b7f35b256b0873dec7b2c2","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vNTQ3NGY3YzVjMWFiZTM5YjZlN2EzYTEzMDRhOGEwNGYvNWFkMDI1MzkvdmlkZW8vbS8yMjA4ZGMxMWQwZTM5YmI0ZDc5OTNlYzFjMjk1ZDAwYmFhOTExNTU2NzA4MDAwMDdjOWRkMGNhM2Y0OS8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS9kZTM2ZDM1NjFiNjFkOWFhM2VkNmQ4YzcyOWI1N2YyMC81YWQwMjUzOS92aWRlby9tLzIyMDhkYzExZDBlMzliYjRkNzk5M2VjMWMyOTVkMDBiYWE5MTE1NTY3MDgwMDAwN2M5ZGQwY2EzZjQ5Lw==","user_video_proxy":1,"socket_buffer":11346120,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""}
             * video_3 : {"definition":"720p","vtype":"mp4","vwidth":1280,"vheight":720,"bitrate":1212146,"size":15163876,"codec_type":"h264","logo_type":"xigua","encrypt":false,"file_hash":"92ec2077c9be6f621d06ffe01a4185df","main_url":"aHR0cDovL3YzLXR0Lml4aWd1YS5jb20vMDBlODEwZTMwNmM3YWFiZjEzNjVjMmFhZDNhNjI4ZmIvNWFkMDI1MzkvdmlkZW8vbS8yMjBmZGU5MWRiNjcwY2Q0MzYyYTA4OTdlNWQ5Mzk4ZDM1OTExNTUzY2MxMDAwMDM5ZWU0ZDI5ODU3My8=","backup_url_1":"aHR0cDovL3YxLXR0Lml4aWd1YXZpZGVvLmNvbS80MWRjZjg1MWNkZTBkNmExMTEwMjNkZTQ3MGE2MGExOS81YWQwMjUzOS92aWRlby9tLzIyMGZkZTkxZGI2NzBjZDQzNjJhMDg5N2U1ZDkzOThkMzU5MTE1NTNjYzEwMDAwMzllZTRkMjk4NTczLw==","user_video_proxy":1,"socket_buffer":27273240,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10,"spade_a":""}
             */

            private VideoInfoBean video_1;
            private VideoInfoBean video_2;
            private VideoInfoBean video_3;

            public VideoInfoBean getVideo_1() {
                return video_1;
            }

            public void setVideo_1(VideoInfoBean video_1) {
                this.video_1 = video_1;
            }

            public VideoInfoBean getVideo_2() {
                return video_2;
            }

            public void setVideo_2(VideoInfoBean video_2) {
                this.video_2 = video_2;
            }

            public VideoInfoBean getVideo_3() {
                return video_3;
            }

            public void setVideo_3(VideoInfoBean video_3) {
                this.video_3 = video_3;
            }
        }
    }
}
