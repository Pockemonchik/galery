package com.example.galery.ui.home;

public class Picture {


        private String name;
        private String disc;
        private int image;


        public Picture(String name, String disc, int image){

            this.name=name;
            this.disc = disc;
            this.image = image;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getdisc() {
            return this.disc;
        }

        public void setdisc(String disc) {
            this.disc = disc;
        }

        public int getImage() {
            return this.image;
        }

        public void setImage(int image) {
            this.image = image;
        }

}
