
import java.io.File;
import java.lang.management.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {

        // Disk information
        List<Map<String, Object>> diskInfoList = new ArrayList<>();
        for (File file : File.listRoots()) {
            Map<String, Object> diskInfo = new HashMap<>();
            diskInfo.put("Path", file.getAbsolutePath());
            diskInfo.put("TotalSpace(GB)", file.getTotalSpace() / (1024 * 1024 * 1024));
            diskInfo.put("FreeSpace(GB)", file.getFreeSpace() / (1024 * 1024 * 1024));
            diskInfo.put("UsableSpace(GB)", file.getUsableSpace() / (1024 * 1024 * 1024));
            diskInfoList.add(diskInfo);
        }
        System.out.println("Disk information:");
        for (Map<String, Object> info : diskInfoList) {
            System.out.println(info);
        }
        System.out.println();

        // Executor information
        Map<String, Object> executorInfo = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) executorService;
        executorInfo.put("CorePoolSize", executor.getCorePoolSize());
        executorInfo.put("MaxPoolSize", executor.getMaximumPoolSize());
        executorInfo.put("PoolSize", executor.getPoolSize());
        executorInfo.put("ActiveCount", executor.getActiveCount());
        executorInfo.put("TaskCount", executor.getTaskCount());
        executorInfo.put("CompletedTaskCount", executor.getCompletedTaskCount());
        executorInfo.put("QueueSize", executor.getQueue().size());

        System.out.println("Executor information\n" + executorInfo + "\n");

        // JVM information
        Map<String,Object> jvmInfo = new HashMap<>();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        jvmInfo.put("HeapMemoryUsed(MB)", memoryMXBean.getHeapMemoryUsage().getUsed() / (1024 * 1024));
        jvmInfo.put("HeapMemoryMax(MB)", memoryMXBean.getHeapMemoryUsage().getMax() / (1024 * 1024));
        jvmInfo.put("NonHeapMemoryUsed(MB)", memoryMXBean.getNonHeapMemoryUsage().getUsed() / (1024 * 1024));

        System.out.println("JVM information\n" + jvmInfo + "\n");

        // Thread information
        Map<String,Object> threadInfo = new HashMap<>();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        threadInfo.put("ThreadCount", threadMXBean.getThreadCount());
        threadInfo.put("PeakThreadCount", threadMXBean.getPeakThreadCount());
        threadInfo.put("DaemonThreadCount", threadMXBean.getDaemonThreadCount());

        System.out.println("Thread information\n" + threadInfo + "\n");

        // Class information
        Map<String,Object> classInfo = new HashMap<>();
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        classInfo.put("LoadedClassCount", classLoadingMXBean.getLoadedClassCount());
        classInfo.put("TotalLoadedClassCount", classLoadingMXBean.getTotalLoadedClassCount());
        classInfo.put("UnloadedClassCount", classLoadingMXBean.getUnloadedClassCount());

        System.out.println("Class information\n" + classInfo + "\n");

        executorService.shutdown();
    }
}