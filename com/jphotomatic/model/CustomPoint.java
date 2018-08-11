package com.jphotomatic.model;

class CustomPoint {

    int x;
    int y;
    int id;

    CustomPoint() {
        x = 0;
        y = 0;
    }

    CustomPoint(int i, int j) {
        x = i;
        y = j;
    }

    CustomPoint(int i, int j, int k) {
        x = i;
        y = j;
        id = k;
    }
}