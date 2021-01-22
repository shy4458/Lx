package com.shy.lunbotu.mvp2.bean;

import java.util.List;

public class ListBean {


    /**
     * data : {"title":{"name":"白菜","id":"001"},"content":[{"id":"001","value":"你好 白菜"},{"id":"002","value":"你好 萝卜"}]}
     * message : success
     * status : 0000
     */

    private DataBean data;
    private String message;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * title : {"name":"白菜","id":"001"}
         * content : [{"id":"001","value":"你好 白菜"},{"id":"002","value":"你好 萝卜"}]
         */

        private TitleBean title;
        private List<ContentBean> content;

        public TitleBean getTitle() {
            return title;
        }

        public void setTitle(TitleBean title) {
            this.title = title;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class TitleBean {
            /**
             * name : 白菜
             * id : 001
             */

            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class ContentBean {
            /**
             * id : 001
             * value : 你好 白菜
             */

            private String id;
            private String value;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
