FROM openjdk:8-jdk-alpine
MAINTAINER mibloom <1047857047@qq.com>
VOLUME /tmp
ADD ../../../target/demo.jar /data/server/app/app.jar
RUN ln -sf /Users/perfect/Documents/springbootDemo /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone
ENTRYPOINT ["java", "-jar", "/data/server/app/app.jar"]
