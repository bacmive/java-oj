[TOC]
## Dev Tools

### git
http://www.ruanyifeng.com/blog/2015/12/git-workflow.html (GitHub流程)
http://www.ruanyifeng.com/blog/2015/08/git-use-process.html
https://blog.carbonfive.com/always-squash-and-rebase-your-git-commits/
https://www.yiibai.com/git/git_pull.html
https://blog.csdn.net/u014643282/article/details/103598756
https://www.jianshu.com/p/684a8ae9dcf1
#### git Server
git init --bare sample.git

#### git Remote add
git remote add kfj ssh://tansj@zj035x.corp.youdao.com:4321/disk2/tansijun/gitServer/kolProj.git
git clone ssh://git@127.0.0.1:xx/yy.git(本地文件路径)

#### git 远程分支操作
https://blog.csdn.net/ljj_9/article/details/79386306
https://www.cnblogs.com/qyf404/p/git_push_local_branch_to_remote.html
https://www.cnblogs.com/luosongchao/p/3408365.html
https://blog.csdn.net/tterminator/article/details/52225720

#### git encoding
https://blog.csdn.net/qq_43356428/article/details/105192940

### ssh
https://zhuanlan.zhihu.com/p/74193910

https://www.jianshu.com/p/b3dcd55ddc9d

### swagger ui
https://www.httpbin.org/

### IDEA

#### debug
https://blog.csdn.net/fly910905/article/details/80013391

#### terminal
https://segmentfault.com/a/1190000012717033


## Java
https://blog.csdn.net/sinat_30160727/article/details/78434704
https://stackoverflow.com/questions/545957/java-split-method-strips-empty-strings-at-the-end

### lombok
https://projectlombok.org/api/

### Serialization
https://juejin.cn/post/7003220566698098695

### Maven
https://blog.csdn.net/zp357252539/article/details/80392101(mvn archetype:generate -DgroupId=com.tsj.oj -DartifactId=javaOj -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false)
https://blog.csdn.net/ibmeye/article/details/50705663 

### jUnit
https://www.jianshu.com/p/e72c5595710a

### logback
https://www.cnblogs.com/quchunhui/p/5783172.html

### httpclient
https://blog.csdn.net/weixin_39758956/article/details/112623281

### Mybatis
https://blog.csdn.net/u010502101/article/details/79053980
https://blog.csdn.net/u010502101/article/details/79053980
https://blog.csdn.net/weixin_43822795/article/details/96587755
https://blog.csdn.net/erlian1992/article/details/78218977
https://blog.csdn.net/chengsi101/article/details/78804224
https://blog.csdn.net/l848168/article/details/91451534
https://blog.csdn.net/qq_36951116/article/details/85082716

### Springboot
https://zhuanlan.zhihu.com/p/33157558

### Zookeeper

## System Logic

### Cookie记录首次登录
https://www.jianshu.com/p/df53fb89da90
https://www.jianshu.com/p/5f13383b47de

### 账号绑定
http://blog.itpub.net/31557372/viewspace-2671003/


## Experience in Dev
https://blog.csdn.net/sinat_15155817/article/details/115214679


## System Design

### api design
https://zhuanlan.zhihu.com/p/56955812



## Network, Security and Protocol
https://zhuanlan.zhihu.com/p/147768058






## Mysql

### single table
ALTER TABLE `tbname`
	ADD COLUMN `state` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '0为添加1为编辑' AFTER `column_name`;

https://blog.csdn.net/Pruett/article/details/72675005

### multi table
https://blog.csdn.net/helloxiaozhe/article/details/82261521

### store process
```sql
delimiter $$
drop procedure if exists insert_english_name$$
create procedure insert_english_name()
begin 
	declare typeIds text;
   declare cooperationTypes text;
   declare quantities text;
	declare i int default 1;
	set typeIds = "1,2,3";
   set cooperationTypes = "Feed - Per Picture,Feed - Per Video,Story-Video/min";
   set quantities = "min,,";
   select length(typeIds) - length(REPLACE(typeIds, ',', '')) + 1 into @num;
   while i <= @num do
   		select substring_index(substring_index(typeIds,',',i),',', -1) into @uid;
      select substring_index(substring_index(cooperationTypes,',',i),',', -1) into @cts;
      select substring_index(substring_index(quantities,',',i),',', -1) into @qts;
      update student set coo=@cts, qua=@qts where student_id = @uid;
      # insert into student (student_name) values (@cts);
      set i = i+1;                        
	end while;
end$$
delimiter ;
call insert_english_name();
```

### foreign key constraint(不需要)
constraint fk_CooperationTypeUnit_CooperationType_On_ID
        foreign key (COOPERATION_TYPE_ID) 
		references CooperationType (ID)

### Cursor
https://dev.mysql.com/doc/refman/8.0/en/cursors.html
```sql
delimiter $$
drop procedure if exists fillCooperatonTypeUnit$$
create procedure fillCooperatonTypeUnit()
begin
	declare done tinyint default false;
   declare cid int;
   declare str varchar(255) default '';
   declare cs cursor for (select id from test);
   declare continue handler for sqlstate '02000' set done=true;
   open cs;
   insert_loop:loop
   		fetch cs into cid;
      if done then
      		leave insert_loop;
    	end if;
      select d into str from test where id = cid; 
      insert into fun (h,k) values (cid, str);
   end loop;
   close cs;
end$$
delimiter ;
call fillCooperatonTypeUnit();
```


## Linux
https://linuxtools-rst.readthedocs.io/zh_CN/latest/index.html
https://zhongqi2513.blog.csdn.net/article/details/78613768

```sql
select t1.user_id as kolId, t1.likes, t1.dislikes, t1.view as views, t1.comment as `comment-count`, concat('https://www.youtube.com/watch?v=', t1.id) as vedio_url 
from kol_media t1 inner join (
    select max(timestamp) as timestamp, id
    from kol_media
    group by id
) t2 on t1.timestamp = t2.timestamp and t1.id = t2.id
order by t1.user_id
```


