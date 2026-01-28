package TestCase;

import org.testng.annotations.Test;

public class POST {

        private String title;
        private String body;
        private int userId;

        public POST(String title, String body, int userId){
            this.title=title;
            this.body=body;
            this.userId=userId;
        }

        public String getTitle(){
            return title;
        }

        public void setTitle(String title){
            this.title=title;
        }

        public String getBody(){
            return body;
        }

        public void setBody(String body){
            this.body=body;
        }

        public int getuserId(){
            return userId;
        }

        public void setuserId(int userId){
            this.userId=userId;
        }
}
