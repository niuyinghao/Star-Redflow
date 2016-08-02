#!/usr/bin/bash
listFile="mailList";
content=$(cat mailContent);
num=$(wc -l $listFile | awk '{print $1}')

pageSize=10;

page=$(( ($num/$pageSize) + 1));

for  i in `seq 1 $page`
do 
echo $i
start=$(( ($i-1)*$pageSize + 1 ));
end=$(( $start + $pageSize ));
sed -n "$start,${end}p" $listFile > _tempFile

 for j in `cat _tempFile`
 do 
 echo $content | mail  -a "Content-type: text/html"  -s "Territory spiritual - Star Redflow" $j  
 done 
done

