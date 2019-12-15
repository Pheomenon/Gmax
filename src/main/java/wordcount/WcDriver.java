package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

/**
 * @Author:Gao
 * @Date:2019-12-14 13:44
 */
//Driver就是对MapReduce任务进行设置
public class WcDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取一个job实例
        Job job = Job.getInstance(new Configuration());

        //设置类路径(Classpath)
        job.setJarByClass(WcDriver.class);

        //设置Mapper和Reducer
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);

        //设置Mapper和Reducer输出的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入输出数据
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //提交Job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}