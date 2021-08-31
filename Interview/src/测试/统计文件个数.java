package 测试;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * @author pxf
 * @create 2021-08-27 12:48
 */
public class 统计文件个数 {
    @Test
    public void test1() {
//        String pathName = "E:\\音乐";
        String pathName = "E:\\Java\\LeetCode--算法";
        FileCounter counter = new FileCounter(pathName);
        counter.searchFiles();
        counter.countFiles();
    }

    public class FileCounter {
        //用于储存找到的每一个文件
        ArrayList<File> fileList;
        //根目录
        File root;

        public FileCounter(String pathName) {
            root = new File(pathName);
            fileList = new ArrayList<>();
        }

        //递归算法查找文件
        public void searchFiles() {
            File[] files = root.listFiles();
            int length = files.length;
            for (int i = 0; i < length; i++) {
                if (files[i].isDirectory()) {
                    root = files[i];
                    searchFiles();
                } else {
                    fileList.add(files[i]);
                }
            }
        }

        //统计文件个数和总的大小
        public void countFiles() {
            long totalSize = 0;
            System.out.println("文件数:" + fileList.size());
            for (int i = 0; i < fileList.size(); i++) {
                totalSize += fileList.get(i).length();//返回由此文件的长度，字节为单位
            }
            System.out.println("文件总大小:" + totalSize);
        }
    }
}
