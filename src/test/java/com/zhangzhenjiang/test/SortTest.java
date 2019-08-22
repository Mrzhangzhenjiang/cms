package com.zhangzhenjiang.test;

public class SortTest {
	public static void main(String[] args) {
		int [] src= {1,3,2,5,7,6,9,8,11,10,12};
		for(int i=0;i<src.length-1;i++) {
			int mid=0;
			if(src[i]>src[i+1]) {
				mid=src[i+1];
				src[i+1]=src[i];
				src[i]=mid;
			}
		}
		for(int j=0;j<src.length;j++) {
			System.out.println(src[j]);
		}
	}
}
