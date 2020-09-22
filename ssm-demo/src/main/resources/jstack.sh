#!/bin/bash
num=0
log="/tmp/jstack_thread_log/thread_info"

cd /tmp
if [ ! -d "jstack_thread_log" ]; then
 mkdir jstack_thread_log
fi


while((num <= 10000));

	do
		ID=`ps -ef | grep java | grep gaea | grep -v "grep" | awk '{print $2}'`

		if[ -n "$ID" ]; then
			jstack $ID >> ${log}
		fi

		num=$(( $num + 1 ))

		mod=$(( $num%100 ))

		if[ $mod -eq 0 ]; then
			back=$log$num
			mv $log $back
		fi

		sleep 5

done