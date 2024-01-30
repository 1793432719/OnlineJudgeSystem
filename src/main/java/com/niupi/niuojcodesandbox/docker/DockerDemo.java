package com.niupi.niuojcodesandbox.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import java.util.List;

/**
 * @author jillion
 * @create com.niupi.niuojcodesandbox.docker - the name of the target package where the new class or interface will
 * be created. niuoj-code-sandbox - the name of the current project. null.java - the name of the PHP file that will be created. DockerDemo - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/11 - the current system date. 9:56 - the current system time. 2024 - the current year. 01 - the current month. 11 - the current day of the month. 09 - the current hour. 56 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
public class DockerDemo {
    public static void main(String[] args) throws InterruptedException {
//        获取默认的DockerClient
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
//        拉取镜像
        String image = "nginx:latest";
//        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
//        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
//            @Override
//            public void onNext(PullResponseItem item) {
//                System.out.println("下载镜像：" + item.getStatus());
//                super.onNext(item);
//            }
//        };
//        pullImageCmd.exec(pullImageResultCallback).awaitCompletion();
//        System.out.println("下载完成");
//        创建容器
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(image);
        CreateContainerResponse createContainerResponse = containerCmd
                .withCmd("echo", "Hello Docker")
                .exec();
//        输出容器id10474eaf5f3f3f48f7833521829f1ff3e63ecd47cc6e7752d315ee69db20918b
        System.out.println(createContainerResponse);
        String containerId = createContainerResponse.getId();

//        查看容器状态
        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
//        获取容器列表
        List<Container> listContainers = listContainersCmd
                .withShowAll(true)
                .exec();
        for (Container container : listContainers) {
            System.out.println(container);
        }
//        启动容器
        StartContainerCmd startContainerCmd = dockerClient.startContainerCmd(containerId);
        startContainerCmd.exec();
//      查看日志
        LogContainerResultCallback logContainerResultCallback = new LogContainerResultCallback() {
            @Override
            public void onNext(Frame item) {
                System.out.println("日志：" + new String(item.getPayload()));
                super.onNext(item);
            }
        };
//        awaitCompletion阻塞等待日志输出
        dockerClient.logContainerCmd(containerId)
                .withStdErr(true)
                .withStdOut(true)
                .exec(logContainerResultCallback)
                .awaitCompletion();

//      删除容器,withForce强制删除
        dockerClient.removeContainerCmd(containerId)
                .withForce(true)
                .exec();
//      删除镜像
//        dockerClient.removeImageCmd(image)
//                .withForce(true)
//                .exec();

    }
}
