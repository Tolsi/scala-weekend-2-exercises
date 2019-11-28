package com.binarydistrict.scala.exercise6.sql

import scalikejdbc._
import com.github.tototoshi.csv.CSVReader

import scala.util.Try

// https://www.kaggle.com/datasnaek/youtube-new/
object SQL {
  object VideoInfo {
    def fromLists(list: List[String]): Try[VideoInfo] =
      Try(VideoInfo(list(0),list(1), list(2), list(3), list(4).toInt, list(5), list(6), list(7).toInt, list(8).toInt, list(9).toInt, list(10).toInt, list(11), list(12).toBoolean, list(13).toBoolean, list(14).toBoolean, list(15)))
  }

  case class VideoInfo(videoId: String,
                       trendingDate: String,
                       title: String,
                       channelTitle: String,
                       categoryId: Int,
                       publishTime: String,
                       tags: String,
                       views: Int,
                       likes: Int,
                       dislikes: Int,
                       commentCount: Int,
                       thumbnailLink: String,
                       commentsDisabled: Boolean,
                       ratingsDisabled: Boolean,
                       videoErrorOrRemoved: Boolean,
                       description: String)

  object VideoInfoSQL extends SQLSyntaxSupport[VideoInfo] {
    override val tableName = "videos"

    override val columnNames = Seq("video_id","trending_date","title","channel_title","category_id","publish_time","tags","views","likes","dislikes","comment_count","thumbnail_link","comments_disabled","ratings_disabled","video_error_or_removed","description")

    private val v = syntax("m")

    def read(rs: WrappedResultSet) = VideoInfo(
      rs.string(1),
      rs.string(2),
      rs.string(3),
      rs.string(4),
      rs.int(5),
      rs.string(6),
      rs.string(7),
      rs.int(8),
      rs.int(9),
      rs.int(10),
      rs.int(11),
      rs.string(12),
      rs.boolean(13),
      rs.boolean(14),
      rs.boolean(15),
      rs.string(16))

    def insert(vi: VideoInfo)(implicit session: DBSession = autoSession): Boolean = {
      import vi._
      sql"insert into videos (video_id,trending_date,title,channel_title,category_id,publish_time,tags,views,likes,dislikes,comment_count,thumbnail_link,comments_disabled,ratings_disabled,video_error_or_removed,description) values ($videoId, $trendingDate, $title, $channelTitle, $categoryId, $publishTime, $tags, $views, $likes, $dislikes, $commentCount, $thumbnailLink, $commentsDisabled, $ratingsDisabled, $videoErrorOrRemoved, $description)".update().apply() == 1
    }

    def all()(implicit session: DBSession = AutoSession): List[VideoInfo] = {
      sql"select * from videos".map(rs => VideoInfoSQL.read(rs)).list.apply()
    }

    def countVideosInEachCategory()(implicit session: DBSession = AutoSession): Map[String, Int] = {
      sql"""
         select c.category_name, count(*) from videos as v left join category as c on v.category_id = c.category_id group by c.category_name;
         """.map(rs => (rs.string(1), rs.int(2))).list().apply().toMap
    }

    def countLikesEachCategory()(implicit session: DBSession = AutoSession): Map[String, Int] = {
      sql"""
         select c.category_name, sum(likes) from videos as v left join category as c on v.category_id = c.category_id group by c.category_name;
         """.map(rs => (rs.string(1), rs.int(2))).list().apply().toMap
    }

    def mostLiked(limit: Int)(implicit session: DBSession = AutoSession): List[VideoInfo] = {
      sql"""
         select v.* from videos as v order by v.likes DESC limit $limit;
         """.map(VideoInfoSQL.read).list().apply()
    }

  }

  def mostViewedCopyOfEachVideo(videos: Seq[VideoInfo]): Seq[VideoInfo] = {
    videos.groupBy(_.videoId).mapValues(_.maxBy(_.views)).values.toSeq
  }

  def initDb()(implicit session: DBSession = AutoSession): Unit = {
    val videosStrings = CSVReader.open(getClass.getResource("/exercise6/sql/RUvideos.csv").getFile).all()
    val videos = videosStrings.drop(1).flatMap(v => VideoInfo.fromLists(v).toOption)

    // table creation
    sql"""
        create table videos (
          video_id varchar(12) not null,
          trending_date text not null,
          title text not null,
          channel_title text not null,
          category_id int not null,
          publish_time text not null,
          tags text not null,
          views int not null,
          likes int not null,
          dislikes int not null,
          comment_count int not null,
          thumbnail_link text not null,
          comments_disabled bool not null,
          ratings_disabled bool not null,
          video_error_or_removed bool not null,
          description text not null
        );
        create table category (
          category_id int not null primary key,
          category_name text not null
        );
        insert into category values
         (1,'Film & Animation'),
         (2,'Autos & Vehicles'),
         (10,'Music'),
         (15,'Pets & Animals'),
         (17,'Sports'),
         (18,'Short Movies'),
         (19,'Travel & Events'),
         (20,'Gaming'),
         (21,'Videoblogging'),
         (22,'People & Blogs'),
         (23,'Comedy'),
         (24,'Entertainment'),
         (25,'News & Politics'),
         (26,'Howto & Style'),
         (27,'Education'),
         (28,'Science & Technology'),
         (30,'Movies'),
         (31,'Anime/Animation'),
         (32,'Action/Adventure'),
         (33,'Classics'),
         (34,'Comedy'),
         (35,'Documentary'),
         (36,'Drama'),
         (37,'Family'),
         (38,'Foreign'),
         (39,'Horror'),
         (40,'Sci-Fi/Fantasy'),
         (41,'Thriller'),
         (42,'Shorts'),
         (43,'Shows'),
         (44,'Trailers');
    """.execute.apply()

    mostViewedCopyOfEachVideo(videos).foreach(VideoInfoSQL.insert)
  }

  def main(args: Array[String]): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:example;DB_CLOSE_DELAY=-1", "", "")

    DB.autoCommit(session => {
      initDb()
    })

    val res = VideoInfoSQL.mostLiked(10)
    println(res)
  }
}
