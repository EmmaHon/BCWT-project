# BCWT-project
Media-sharing platform UAS project. Basically the next YouTube!

## Getting Started

To use this project, you need to have a server with Glassfish v5 and a MySQL database.
To create correct tables please execute following code in your database server:
```
create table `webdb`.users
(
	USERID INT not null primary key,
	EMAIL VARCHAR(128) not null,
	PASSWORD VARCHAR(128) not null,
	TOKEN CHAR(128),
	ISADMIN NULL,
	USERNAME VARCHAR(64) not null
)

create table `webdb`.video
(
	VIDID INT not null primary key,
	TITLE VARCHAR(128) not null,
	FILEPATH VARCHAR(1028) not null,
	VOTES INT,
	UPLOAD DATE,
	USERID INT,
	DESCR VARCHAR(1028) not null,
	THUMBNAILPATH VARCHAR(512)
)

create table `webdb`.vote
(
	USERID INT default 0 not null,
	VIDID INT default 0 not null,
	SCORE INT,
	primary key (USERID, VIDID)
)

CREATE VIEW hotvideos AS SELECT video.VIDID,
 ((video.VOTES)/(DATEDIFF(NOW(),video.UPLOAD)+1)) karma FROM video ORDER BY karma DESC;

CREATE VIEW topvideos AS SELECT video.VIDID,video.VOTE FROM video ORDER BY video.VOTE DESC;

CREATE VIEW freshvideos AS SELECT video.VIDID, video.UPLOAD FROM video ORDER BY video.UPLOAD DESC;

```

and open the project in NetBeans v8.2.
Then simply deploy the project to your server.

## Trivia
* Best hack: 
```
private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
```
A server sided refresh of front page. Each one hour the front page is refreshed, based on video votes, upload times and other.

* What's lacking:
REST services are simply not working.
Database handling is quirky like an irish woman on a rainy Thursday
Responsive design remained unresponsive to our prayers



## License
This project is using outdated technology, with broken methods. Feel free to use in any means you wish.
