#!/bin/sh

PATH=/home/pi/Public/jfx/jre/lib:/home/pi/Public/jfx/jre/lib/arm
PATH=$PATH:/home/pi/Public/midicontrollerstandalone
PATH=$PATH:/home/pi/Public/midicontrollerstandalone/lib
PATH=$PATH:/home/pi/Public/midicontrollerstandalone/lib/ext
PATH=$PATH:/home/pi/Public/midicontrollerstandalone/lib/config
PATH=$PATH:/home/pi/Public/midicontrollerstandalone/res
PATH=$PATH:/home/pi/Public/midicontrollerstandalone/res/i18n
PATH=$PATH:/home/pi/Public/midicontrollerstandalone/config/playlist
export PATH

MYCP=/home/pi/Public/midicontrollerstandalone/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/apache-log4j-2.8.1-bin/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/commons-beanutils-1.9.3/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/commons-configuration2-2.1.1/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/commons-io-2.5/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/commons-lang3-3.5/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/commons-logging-1.2/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/javazoom/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/oracle-java-tablelayout/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/jaudiotagger-2.2.6-SNAPSHOT.jar
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/ext/spring-framework-4.3.6.RELEASE/*
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/config
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/config/playlist
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/res
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/res/i18n
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/res/images
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/res/images/mp
MYCP=$MYCP:/home/pi/Public/midicontrollerstandalone/lib/patch/*

JAVAFX_DEBUG=1
/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/bin/java -version
/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/bin/java -cp "$MYCP" com.tntdjs.midicontroller.standalone.StandaloneApp
