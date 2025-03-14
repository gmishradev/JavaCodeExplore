package com.code.heap;

import java.util.Comparator;

public class Cpu {
    static class CpuEnTimeAndPtime {
        int enTime;
        int pTime;

        public int getEnTime() {
            return enTime;
        }

        public void setEnTime(int enTime) {
            this.enTime = enTime;
        }

        public int getpTime() {
            return pTime;
        }

        public void setpTime(int pTime) {
            this.pTime = pTime;
        }
    }
    public int[] getOrder(int[][] tasks) {

        Comparator<CpuEnTimeAndPtime> comprator = new Comparator<CpuEnTimeAndPtime>()
        {
            @Override
            public int compare(CpuEnTimeAndPtime o1, CpuEnTimeAndPtime o2) {
                return o2.getpTime() -  o1.getpTime();
            }
        };


    return new int[4];

    }
}
