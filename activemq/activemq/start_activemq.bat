@echo off
set ACTIVEMQ_OPTS=-Dorg.apache.activemq.SERIALIZABLE_PACKAGES=*
set ACTIVEMQ_HOME=.
bin\activemq.bat start
