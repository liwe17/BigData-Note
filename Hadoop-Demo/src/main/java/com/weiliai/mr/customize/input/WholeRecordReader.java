package com.weiliai.mr.customize.input;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: 自定义RecordReader类
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable> {

    private Configuration configuration;

    private FileSplit fileSplit;

    private Text k;

    private BytesWritable v;

    private boolean isProgress = true;

    /**
     * 只调用一次
     */
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.fileSplit = (FileSplit) split;
        this.configuration = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {

        if (!isProgress)
            return false;

        FSDataInputStream fis = null;
        try {
            //1. 定义缓存区
            byte[] bytes = new byte[(int) fileSplit.getLength()];
            //2. 获取文件系统
            Path path = fileSplit.getPath();
            //3. 读取数据
            fis = path.getFileSystem(configuration).open(path);
            //4. 获取文件内容
            IOUtils.readFully(fis, bytes, 0, (int) fileSplit.getLength());
            v = new BytesWritable(bytes);
            //5. 输出文件路径
            k = new Text(path.toString());
            isProgress = false;
            return true;
        } finally {
            IOUtils.closeStream(fis);
        }
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return k;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return v;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
